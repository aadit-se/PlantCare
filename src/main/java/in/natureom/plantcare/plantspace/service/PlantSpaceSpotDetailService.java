package in.natureom.plantcare.plantspace.service;

import in.natureom.plantcare.plantcare.dao.PlantCareInterval;
import in.natureom.plantcare.plantcare.dao.PlantCareIntervalRepo;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpot;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotDetails;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotDetailsRepo;
import in.natureom.plantcare.plantspace.dto.*;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static in.natureom.plantcare.util.CommonConstants.Failed;
import static in.natureom.plantcare.util.CommonConstants.Success;

@Service
@Slf4j
public class PlantSpaceSpotDetailService {

    @Autowired
    PlantSpaceService spaceService;

    @Autowired
    PlantSpaceSpotDetailsRepo dao;

    @Autowired
    PlantSpaceSpotService spotService;

    @Autowired
    PlantCareIntervalRepo categoryDao;

    @Autowired
    PlantSpaceSpotLogService logService;

    public String addSpotDetails(PlantSpaceSpotDTO SpotDto) {

        try {
            log.info("in addSpotDetails");
            PlantSpaceSpotDetails detail = new PlantSpaceSpotDetails();
            PlantCareInterval category = categoryDao.findById(SpotDto.getCategoryId()).get();
            detail.setCategory(category);
            detail.setSpace(spotService.PlantSpaceSpotDtoToModel(SpotDto).getPlantSpaceId());
            detail.setSpot(spotService.PlantSpaceSpotDtoToModel(SpotDto));
            detail.setSpotName(spotService.PlantSpaceSpotDtoToModel(SpotDto).getPlantSpaceSpotName());
            detail.setDetailId(SpotDto.getPlantSpaceSpotId());
            Date nextWFPTSC[] = new Date[6];
            int wfptsc[] = new int[6];
            wfptsc[0] = category.getWateringInterval();
            wfptsc[1] = category.getFertilizationInterval();
            wfptsc[2] = category.getPesticideSprayInterval();
            wfptsc[3] = category.getTrimmingInterval();
            wfptsc[4] = category.getSoilCultivationInterval();
            wfptsc[5] = category.getCleaningInterval();
            for (int i = 0; i < 6; i++) {
                Calendar cd = Calendar.getInstance();
                cd.add(Calendar.DATE, wfptsc[i]);
                nextWFPTSC[i] = cd.getTime();
            }
            Date today = new Date();
            detail.setLastWatered(today);
            detail.setNextWatered(nextWFPTSC[0]);
            detail.setLastFertilized(today);
            detail.setNextFertilize(nextWFPTSC[1]);
            detail.setLastPesticide(today);
            detail.setNextPesticide(nextWFPTSC[2]);
            detail.setLastTrimmed(today);
            detail.setNextTrim(nextWFPTSC[3]);
            detail.setLastSoilCultivated(today);
            detail.setNextSoilCultivated(nextWFPTSC[4]);
            detail.setLastcleaned(today);
            detail.setNextClean(nextWFPTSC[5]);
            dao.save(detail);
            log.info("object was added");
            log.debug("added object=", detail);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public PlantSpaceSpotDetails viewSpotDetails(Long spotId)
    {
        try{
        log.info("in viewSpotDetails, fetching object");
        PlantSpaceSpotDetails spotDetail=dao.getSpotDetails(spotId);
        if(!NatureOmUtil.isObjectValid(spotDetail))
        throw new Exception("no such object");
        log.info("object was fetched");
        log.debug("fetched object={}",spotDetail);
        return spotDetail;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    //TODO: Reduce the cyclomatic complexity
    public ArrayList<viewUpcomingDetailsDto> viewUpcomingDetails(Long id,String idBelongsTo) {
        try {
            log.info(":in viewUpcomingDetails");
            // date and calender for calling query in dao
            Calendar cdDao = Calendar.getInstance();
            cdDao.add(Calendar.DATE, 3);
            Date dt = new Date();

            // dates and calender for sorting object received
            dt = cdDao.getTime();
            Calendar cd = Calendar.getInstance();
            Date dates[] = new Date[3];
            dates[0] = cd.getTime();
            cd.add(Calendar.DATE, 1);
            dates[1] = cd.getTime();
            cd.add(Calendar.DATE, 1);
            dates[2] = cd.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<PlantSpaceSpotDetails> details = dao.viewUpcomingDetails(sdf.format(dt));
            ArrayList<PlantSpaceSpotDetailsDto> detailDtos = new ArrayList<>();
            
            if(id==null && idBelongsTo==null)
            details.forEach((detail) -> {
                detailDtos.add(PlantSpaceSpotDetailsModelToDto(detail));
            });
            else if(id!=null && idBelongsTo=="plantMaster")
            {
                details.forEach((detail)->
                {
                    if(detail.getSpace().getPlantSpacePlantmasterId().getUser_role_id().equals(id))
                    {
                        detailDtos.add(PlantSpaceSpotDetailsModelToDto(detail));
                        log.info("added into plantMastdetails");
                    }
                });
            }
            
            if(id!=null && idBelongsTo=="customer")
            {
                details.forEach((detail)->
                {
                    if(detail.getSpace().getPlantSpaceCustomerId().getUser_role_id().equals(id))
                    {
                        detailDtos.add(PlantSpaceSpotDetailsModelToDto(detail));
                        log.info("added into plantMastdetails");
                    }
                });
            }

            else if(id!=null && idBelongsTo=="spot")
            {
                details.forEach((detail)->
                {
                    if(detail.getSpot().getPlantSpaceSpotId().equals(id))
                    {
                        detailDtos.add(PlantSpaceSpotDetailsModelToDto(detail));
                        log.info("added into plantMastdetails");
                    }
                });
            }
            // starting to sort object into different dates

            viewUpcomingDetailsDto todayDto = new viewUpcomingDetailsDto();
            todayDto.setDate(dates[0]);
            viewUpcomingDetailsDto tomorrowDto = new viewUpcomingDetailsDto();
            tomorrowDto.setDate(dates[1]);
            viewUpcomingDetailsDto afterTomorrowDto = new viewUpcomingDetailsDto();
            afterTomorrowDto.setDate(dates[2]);

            ArrayList<viewUpcomingDetailsDto> viewDetailsDtos = new ArrayList<>();
            viewUpcomingDetailsDto todayDtos = new viewUpcomingDetailsDto();
            ArrayList<PlantSpaceUpcomingDetailSpaceDetails> spaceToday = new ArrayList<PlantSpaceUpcomingDetailSpaceDetails>();
            viewUpcomingDetailsDto tomorrowDtos = new viewUpcomingDetailsDto();
            ArrayList<PlantSpaceUpcomingDetailSpaceDetails> spaceTomorrow = new ArrayList<PlantSpaceUpcomingDetailSpaceDetails>();
            viewUpcomingDetailsDto afterTomorrowDtos = new viewUpcomingDetailsDto();
            ArrayList<PlantSpaceUpcomingDetailSpaceDetails> spaceAfterTomorrow = new ArrayList<PlantSpaceUpcomingDetailSpaceDetails>();
            System.out.println(detailDtos);

            for (int k = 0; k < detailDtos.size(); k++) {

                for (int i = 0; i <= 2; i++) {
                    PlantSpaceUpcomingDetailSpaceDetails space = new PlantSpaceUpcomingDetailSpaceDetails();
                    space.setSpaceId(detailDtos.get(k).getSpaceId());
                    space.setSpaceName(detailDtos.get(k).getSpaceName());
                    space.setPlantMasterId(detailDtos.get(k).getPlantMasterId());
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> waterArray = new ArrayList<>();
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> fertilizeArray = new ArrayList<>();
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> trimArray = new ArrayList<>();
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> pesticideArray = new ArrayList<>();
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> cleaningArray = new ArrayList<>();
                    ArrayList<PlantSpaceUpcomingDetailSpotDetails> soilCultivationArray = new ArrayList<>();
                    // adding object at kth index
                    boolean addedFlage = false;
                    boolean waterFlag = false;
                    boolean fertilizerFlag = false;
                    boolean pesticideFlag = false;
                    boolean trimmingFlag = false;
                    boolean cleaningFlag = false;
                    boolean soilCultivationFlag = false;
                    if (detailDtos.get(k).getNextWatered() != null && sdf.format(detailDtos.get(k).getNextWatered()).equals(sdf.format(dates[i]))) {
                        waterFlag = true;
                        detailDtos.get(k).setNextWatered(null);
                        addedFlage = true;
                    }
                    if (detailDtos.get(k).getNextFertilize() != null && sdf.format(detailDtos.get(k).getNextFertilize()).equals(sdf.format(dates[i]))) {
                        fertilizerFlag = true;
                        detailDtos.get(k).setNextFertilize(null);
                        addedFlage = true;
                    }
                    if (detailDtos.get(k).getNextTrim() != null && sdf.format(detailDtos.get(k).getNextTrim()).equals(sdf.format(dates[i]))) {
                        trimmingFlag = true;
                        detailDtos.get(k).setNextTrim(null);
                        addedFlage = true;
                    }
                    if (detailDtos.get(k).getNextPesticide() != null && sdf.format(detailDtos.get(k).getNextPesticide()).equalsIgnoreCase(sdf.format(dates[i]))) {
                        pesticideFlag = true;
                        detailDtos.get(k).setNextPesticide(null);
                        addedFlage = true;
                    }

                    if (detailDtos.get(k).getNextSoilCultivated()!= null && sdf.format(detailDtos.get(k).getNextSoilCultivated()).equals(sdf.format(dates[i]))) {
                        soilCultivationFlag = true;
                        detailDtos.get(k).setNextSoilCultivated(null);
                        addedFlage = true;
                    }

                    if (detailDtos.get(k).getNextClean() != null && sdf.format(detailDtos.get(k).getNextClean()).equals(sdf.format(dates[i]))) {
                        cleaningFlag = true;
                        detailDtos.get(k).setNextClean(null);
                        addedFlage = true;
                    }

                    if (waterFlag == true || fertilizerFlag == true || pesticideFlag == true || trimmingFlag == true || cleaningFlag ==true || soilCultivationFlag ==true) {
                        PlantSpaceUpcomingDetailSpotDetails spot = new PlantSpaceUpcomingDetailSpotDetails();
                        spot.setSpotId(detailDtos.get(k).getSpotId());
                        spot.setSpotName(detailDtos.get(k).getSpotName());
                        spot.setSpotStatus(detailDtos.get(k).getSpotStatus());
                        if (waterFlag == true) {
                            waterArray.add(spot);
                        }

                        if (cleaningFlag == true) {
                            cleaningArray.add(spot);
                        }

                        if (soilCultivationFlag==true) {
                            soilCultivationArray.add(spot);
                        }

                        if (fertilizerFlag == true) {
                            fertilizeArray.add(spot);
                        }

                        if (pesticideFlag == true) {
                            pesticideArray.add(spot);
                        }

                        if (trimmingFlag == true) {
                            trimArray.add(spot);
                        }
                        if (k < detailDtos.size() - 1)
                            for (int j = k + 1; j < detailDtos.size(); j++) {
                                if (detailDtos.get(j).getSpaceId() == detailDtos.get(k).getSpaceId()) {

                                    waterFlag = false;
                                    fertilizerFlag = false;
                                    pesticideFlag = false;
                                    trimmingFlag = false;
                                    cleaningFlag = false;
                                    soilCultivationFlag = false;
                    
                                    if (detailDtos.get(j).getNextWatered() != null && sdf.format(detailDtos.get(j).getNextWatered()).equals(sdf.format(dates[i]))) {
                                        waterFlag = true;
                                        detailDtos.get(j).setNextWatered(null);
                                    }
                                    if (detailDtos.get(j).getNextFertilize() != null && sdf.format(detailDtos.get(j).getNextFertilize()).equals(sdf.format(dates[i]))) {
                                        fertilizerFlag = true;
                                        detailDtos.get(j).setNextFertilize(null);
                                    }
                                    if (detailDtos.get(j).getNextTrim() != null && sdf.format(detailDtos.get(j).getNextTrim()).equals(sdf.format(dates[i]))) {
                                        trimmingFlag = true;
                                        detailDtos.get(j).setNextTrim(null);
                                    }
                                    if (detailDtos.get(j).getNextPesticide() != null && sdf.format(detailDtos.get(j).getNextPesticide()).equalsIgnoreCase(sdf.format(dates[i]))) {
                                        pesticideFlag = true;
                                        detailDtos.get(j).setNextPesticide(null);
                                    }

                                    if (detailDtos.get(j).getNextSoilCultivated()!= null && sdf.format(detailDtos.get(j).getNextSoilCultivated()).equals(sdf.format(dates[i]))) {
                                        soilCultivationFlag = true;
                                        detailDtos.get(j).setNextSoilCultivated(null);
                                    }
                
                                    if (detailDtos.get(j).getNextClean() != null && sdf.format(detailDtos.get(j).getNextClean()).equals(sdf.format(dates[i]))) {
                                        cleaningFlag = true;
                                        detailDtos.get(j).setNextClean(null);
                                    }
                

                                    if (waterFlag == true || fertilizerFlag == true || pesticideFlag == true || trimmingFlag == true || cleaningFlag ==true || soilCultivationFlag ==true) {
                                        PlantSpaceUpcomingDetailSpotDetails spotB = new PlantSpaceUpcomingDetailSpotDetails();
                                        spotB.setSpotId(detailDtos.get(j).getSpotId());
                                        spotB.setSpotName(detailDtos.get(j).getSpotName());
                                        spotB.setSpotStatus(detailDtos.get(j).getSpotStatus());
                                        if (waterFlag == true) {
                                            waterArray.add(spotB);
                                        }

                                        if (fertilizerFlag == true) {
                                            fertilizeArray.add(spotB);
                                        }

                                        if (pesticideFlag == true) {
                                            pesticideArray.add(spotB);
                                        }

                                        if (trimmingFlag == true) {
                                            trimArray.add(spotB);
                                        }

                                        if (cleaningFlag == true) {
                                            cleaningArray.add(spotB);
                                        }
                
                                        if (soilCultivationFlag==true) {
                                            soilCultivationArray.add(spotB);
                                        }
                
                                        if (i == 3)
                                            detailDtos.remove(j);
                                    }

                                }
                            }
                        space.setWaterSpot(waterArray);
                        space.setFertilizeSpot(fertilizeArray);
                        space.setTrimSpot(trimArray);
                        space.setPesticideSpot(pesticideArray);
                        space.setCleaning(cleaningArray);
                        space.setSoilCultivation(soilCultivationArray);
                        if (i == 0) {
                            spaceToday.add(space);
                        }
                        if (i == 1) {
                            spaceTomorrow.add(space);
                        }
                        if (i == 2) {
                            spaceAfterTomorrow.add(space);
                        }
                        if (i == 3)
                            detailDtos.remove(k);
                    }

                }
            }
            // System.out.println("spacetoday=" + spaceToday);
            todayDto.setSpace(spaceToday);
            tomorrowDto.setSpace(spaceTomorrow);
            afterTomorrowDto.setSpace(spaceAfterTomorrow);

            viewDetailsDtos.add(todayDto);
            viewDetailsDtos.add(tomorrowDto);
            viewDetailsDtos.add(afterTomorrowDto);
            return viewDetailsDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public PlantSpaceSpotDetailsDto PlantSpaceSpotDetailsModelToDto(PlantSpaceSpotDetails detail) {
        PlantSpaceSpotDetailsDto detailDto = new PlantSpaceSpotDetailsDto();
        detailDto.setSpaceId(detail.getSpot().getPlantSpaceId().getPlantSpaceId());
        detailDto.setDetailId(detail.getDetailId());
        detailDto.setSpotStatus(detail.getSpot().getSpotStatus());
        detailDto.setPlantMasterId(detail.getSpace().getPlantSpacePlantmasterId().getUser_role_id());
        detailDto.setSpaceName(detail.getSpot().getPlantSpaceId().getPlantSpaceName());
        detailDto.setSpotId(detail.getSpot().getPlantSpaceSpotId());
        detailDto.setSpotName(detail.getSpot().getPlantSpaceSpotName());
        detailDto.setCategoryName(detail.getSpot().getPlantCategoryName());
        detailDto.setLastWatered(detail.getLastWatered());
        detailDto.setLastFertilized(detail.getLastFertilized());
        detailDto.setLastPesticide(detail.getLastPesticide());
        detailDto.setLastTrimmed(detail.getLastTrimmed());
        detailDto.setNextWatered(detail.getNextWatered());
        detailDto.setNextFertilize(detail.getNextFertilize());
        detailDto.setNextPesticide(detail.getNextPesticide());
        detailDto.setLastSoilCultivated(detail.getLastSoilCultivated());
        detailDto.setNextSoilCultivated(detail.getNextSoilCultivated());
        detailDto.setNextTrim(detail.getNextTrim());
        detailDto.setNextClean(detail.getNextClean());
        detailDto.setLastCleaned(detail.getLastcleaned());
        log.debug("in PlantSpaceSpotDetailsDtoToModel, object was converted from DTO to model object");
        return detailDto;
    }

    public PlantSpaceSpotDetails PlantSpaceSpotDetailsDtoToModel(PlantSpaceSpotDetailsDto detailDto) {
        PlantSpaceSpotDetails detail = new PlantSpaceSpotDetails();
        detail.setDetailId(detailDto.getDetailId());
        detail.setSpot(spotService.PlantSpaceSpotDtoToModel(spotService.getPlantSpaceSpot(detailDto.getSpotId())));
        detail.setSpotName(spotService.PlantSpaceSpotDtoToModel(spotService.getPlantSpaceSpot(detailDto.getSpotId())).getPlantSpaceSpotName());
        detail.setLastWatered(detailDto.getLastWatered());
        detail.setLastFertilized(detailDto.getLastFertilized());
        detail.setLastPesticide(detailDto.getLastPesticide());
        detail.setLastTrimmed(detailDto.getLastTrimmed());
        detail.setNextWatered(detailDto.getNextWatered());
        detail.setNextFertilize(detailDto.getNextFertilize());
        detail.setNextPesticide(detailDto.getNextPesticide());
        detail.setNextTrim(detailDto.getNextTrim());
        log.debug("in PlantSpaceSpotDetailsDtoToModel, object was converted from DTO to model object");
        return detail;
    }

    public String deleteDetailsOfSpaces(Long spaceId)
    {
        try{
            log.info("in deleteDetailsOfSpaces");
            dao.deleteDetailsOfSpace(spaceId);
            log.info("object was deleted");
            return Success;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String deleteDetailsOfSpot(Long spotId)
    {
        try{
            log.info("in deleteDetailsOfSpot");
            dao.deleteDetailsOfSpot(spotId);
            log.info("object was deleetd");
            return Success;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String[] checkSpotStatus(Long spaceId) {
        try{
        	log.info("in checkSpotStatus");
            ArrayList<PlantSpaceSpotDetails> spotDetails = dao.selectAllSpaceSpots(spaceId);
            int size = spotDetails.size();
            String SpotsColor[] = new String[size];
            for(int i=0; i<spotDetails.size();i++)
            {
                int WFPTSC[] = new int[6];
                PlantSpaceSpotDetails currentSpot=spotDetails.get(i);
                WFPTSC[0] =currentSpot.getCategory().getWateringInterval();
                WFPTSC[1] =currentSpot.getCategory().getFertilizationInterval();
                WFPTSC[2] =currentSpot.getCategory().getPesticideSprayInterval();
                WFPTSC[3] =currentSpot.getCategory().getTrimmingInterval();
                WFPTSC[4] =currentSpot.getCategory().getSoilCultivationInterval();
                WFPTSC[5] =currentSpot.getCategory().getCleaningInterval();
                Date nextWFPTSC[] = new Date[6];
                nextWFPTSC[0]= currentSpot.getNextWatered();
                nextWFPTSC[1]= currentSpot.getNextFertilize();
                nextWFPTSC[2]= currentSpot.getNextPesticide();
                nextWFPTSC[3]= currentSpot.getNextTrim();
                nextWFPTSC[4]= currentSpot.getNextSoilCultivated();
                nextWFPTSC[5]= currentSpot.getNextClean();
                String color[] = new String[6];
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                for(int j=0;j<6;j++)
                {
                    Calendar today= Calendar.getInstance();
                    Calendar cd= Calendar.getInstance();
                    cd.setTime(nextWFPTSC[j]);
                    cd.add(Calendar.DATE,WFPTSC[j]);
                    // if today>next water==one deadline, and today>nextwater+nextInterval==2 dead line
                    if(sdf.format(today.getTime()).compareTo(sdf.format(nextWFPTSC[j]))>=1 && sdf.format(today.getTime()).compareTo(sdf.format(cd.getTime()))>=1)
                    {
                        color[j]="red";
                    }
                    // if today>next=1 dead line && today<next+int= not 2 deadline
                    else if(sdf.format(today.getTime()).compareTo(sdf.format(nextWFPTSC[j]))>=1 && (sdf.format(today.getTime()).compareTo(sdf.format(cd.getTime()))<=-1)|| sdf.format(today.getTime()).compareTo(sdf.format(cd.getTime()))==0)
                    {
                    	color[j]="yellow";
                    }
                    // today<next=no deadlines or today=next
                    else if(sdf.format(today.getTime()).compareTo(sdf.format(nextWFPTSC[j]))<=-1 || sdf.format(today.getTime()).compareTo(sdf.format(nextWFPTSC[j]))==0 )
                    {
                        color[j]="green";
                    }
                }
                 PlantSpaceSpot spot = currentSpot.getSpot();
                Boolean flagRed=false;
                Boolean flagYellow=false;
                Boolean flagGreen=false;
                for(int k=0; k<6;k++)
                {
                    if(color[k]=="red")
                    flagRed=true;
                    if(color[k]=="yellow")
                    flagYellow=true;
                    if(color[k]=="green")
                    flagGreen=true;
                }
                // spot color = color of all spots of space
                if(flagRed==true)
                {
                spot.setSpotStatus("red");
                SpotsColor[i]="red";
                }
                else if(flagYellow==true) 
                {
                spot.setSpotStatus("yellow");
                SpotsColor[i]="yellow";
                }
                else if(flagGreen==true)
                {
                spot.setSpotStatus("green");
                SpotsColor[i]="green";
                }
                spotService.updatePlantSpaceSpot(spotService.PlantSpaceSpotModelToDto(spot));
            }
            log.info("spot status was updated");
        return SpotsColor;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    public String updateSpotDeatils(UpdateSpotDetailsDto UpdateSpotDetailsDto) {
        try {
            log.info("in updateSpotDeatils");
            PlantSpaceSpotDetails detail = dao.getSpotDetails(UpdateSpotDetailsDto.getSpotId());
            PlantSpaceSpotDTO spotDto=spotService.getPlantSpaceSpot(UpdateSpotDetailsDto.getSpotId());
            System.out.println("spotDto="+spotDto);
            PlantCareInterval category = categoryDao.findById(spotDto.getCategoryId()).get();
            detail.setCategory(category);
            detail.setSpace(spotService.PlantSpaceSpotDtoToModel(spotDto).getPlantSpaceId());
            detail.setSpot(spotService.PlantSpaceSpotDtoToModel(spotDto));
            detail.setSpotName(spotService.PlantSpaceSpotDtoToModel(spotDto).getPlantSpaceSpotName());
            Date nextWFPTSC[] = new Date[6];
            int wfptsc[] = new int[6];
            wfptsc[0] = category.getWateringInterval();
            wfptsc[1] = category.getFertilizationInterval();
            wfptsc[2] = category.getPesticideSprayInterval();
            wfptsc[3] = category.getTrimmingInterval();
            wfptsc[4] = category.getSoilCultivationInterval();
            wfptsc[5] = category.getCleaningInterval();
            for (int i = 0; i < 6; i++) {
                Calendar cd = Calendar.getInstance();
                cd.add(Calendar.DATE, wfptsc[i]);
                nextWFPTSC[i] = cd.getTime();
            }
            // action name

            // actionPerformed=SprayingPesticide
            // actionPerformed=Trimming
            // actionPerformed=Fertilizing
            // actionPerformed=Watering  
            Date today = new Date();
            if(UpdateSpotDetailsDto.getActionPerformed().equals("Watering"))
            {
                detail.setLastWatered(today);
                detail.setNextWatered(nextWFPTSC[0]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "watering", today);
            }

            if(UpdateSpotDetailsDto.getActionPerformed().equals("Trimming"))
            {
                detail.setLastTrimmed(today);
                detail.setNextTrim(nextWFPTSC[3]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "trimming", today);
            }

            if(UpdateSpotDetailsDto.getActionPerformed().equals("Fertilizing"))
            {
                detail.setLastFertilized(today);
                detail.setNextFertilize(nextWFPTSC[1]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "Fertilizing", today);
            }

            if(UpdateSpotDetailsDto.getActionPerformed().equals("SprayingPesticide"))
            {
                detail.setLastPesticide(today);
                detail.setNextPesticide(nextWFPTSC[2]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "SprayingSepticide", today);
            
            }

            if(UpdateSpotDetailsDto.getActionPerformed().equals("Cleaning"))
            {
                detail.setLastcleaned(today);
                detail.setNextClean(nextWFPTSC[5]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "Cleaning", today);
            
            }

            if(UpdateSpotDetailsDto.getActionPerformed().equals("SoilCultivation"))
            {
                detail.setLastSoilCultivated(today);
                detail.setNextSoilCultivated(nextWFPTSC[4]);
                logService.addLong(spotDto.getPlantSpaceSpotId(), "SoilCultivated", today);
            
            }
            dao.save(detail);
            log.info("object was updated");
            log.debug("added object=", detail);
            spaceService.updateSpaceStatus(spotDto.getPlantSpaceId());
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public ArrayList<viewUpcomingDetailsDto> customerUpcomingDetails(Long customerId) {
        return null;
    }

    public AdminSpaceSpotDetailsDto adminViewSpaceSpotDeatils(Long spaceId) {
        log.info("inside adminViewSpaceSpotDeatils, fetching obj");
        try{
            ArrayList<PlantSpaceSpotDetails> spaceSpotDetails= dao.selectAllSpaceSpots(spaceId);
            AdminSpaceSpotDetailsDto spaceSpotDetailsDto = PlantSpaceSpotDetailsToadminDto(spaceSpotDetails);
            log.info("object was fetched");
            log.debug("object={}",spaceSpotDetailsDto);
            return spaceSpotDetailsDto;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    public AdminSpaceSpotDetailsDto PlantSpaceSpotDetailsToadminDto(ArrayList<PlantSpaceSpotDetails> spaceSpotDetails)
    {
        log.info("in PlantSpaceSpotDetailsToadminDto");
        AdminSpaceSpotDetailsDto spaceSpotDto = new AdminSpaceSpotDetailsDto();
        ArrayList<AdminSpotDetails> spotDtos = new ArrayList<>();
        spaceSpotDto.setSpaceId(spaceSpotDetails.get(0).getSpace().getPlantSpaceId());
        spaceSpotDto.setPlantMasterId(spaceSpotDetails.get(0).getSpace().getPlantSpacePlantmasterId().getUser_role_id());
        spaceSpotDto.setSpaceName(spaceSpotDetails.get(0).getSpace().getPlantSpaceName());
        spaceSpotDto.setPlantMasterName(spaceSpotDetails.get(0).getSpace().getPlantSpacePlantmasterId().getUserName());
        spaceSpotDto.setSpaceDescription(spaceSpotDetails.get(0).getSpace().getPlantSpaceDesc());
        spaceSpotDetails.forEach((detail)->
        {
            AdminSpotDetails spotDto = new AdminSpotDetails();
            spotDto.setLastWatered(detail.getLastWatered());
            spotDto.setNextWater(detail.getNextWatered());
            spotDto.setLastFertilized(detail.getLastFertilized());
            spotDto.setNextFertilize(detail.getNextFertilize());
            spotDto.setLastSoilCultivated(detail.getLastSoilCultivated());
            spotDto.setNextSoilCultivate(detail.getNextSoilCultivated());
            spotDto.setLastPesticideSprayed(detail.getLastPesticide());
            spotDto.setNextPesticideSpraye(detail.getNextPesticide());
            spotDto.setLastCleaned(detail.getLastcleaned());
            spotDto.setNextClean(detail.getNextClean());
            spotDto.setLastTrimmed(detail.getLastTrimmed());
            spotDto.setNextTrim(detail.getNextTrim());

            spotDto.setCategoryName(detail.getSpot().getPlantCategoryName());
            spotDto.setSpotName(detail.getSpotName());
            spotDto.setSpotStatus(detail.getSpot().getSpotStatus());
            spotDto.setSpotId(detail.getSpot().getPlantSpaceSpotId());
            spotDto.setSpotDescription(detail.getSpot().getPlantSpaceSpotDesc());
            spotDtos.add(spotDto);
        }
        );
        spaceSpotDto.setSpots(spotDtos);
        return spaceSpotDto;
    }
}
