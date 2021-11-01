package in.natureom.plantcare.plantspace.service;

import in.natureom.plantcare.plantcare.dao.PlantCareIntervalRepo;
import in.natureom.plantcare.plantspace.controller.PlantSpaceSpotDetailsController;
import in.natureom.plantcare.plantspace.dao.PlantSpaceRepo;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpot;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotRepo;
import in.natureom.plantcare.plantspace.dto.PlantSpaceSpotDTO;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static in.natureom.plantcare.util.CommonConstants.Failed;
import static in.natureom.plantcare.util.CommonConstants.Success;

@Service
@Slf4j
public class PlantSpaceSpotService {

    @Autowired
    PlantSpaceService spaceService;

    @Autowired
    PlantSpaceSpotDetailService detailService;

    @Autowired
    PlantSpaceSpotDetailsController detailsController;

    @Autowired
    PlantSpaceSpotRepo dao;

    @Autowired
    PlantSpaceRepo SpaceDao;

    @Autowired
    PlantCareIntervalRepo categoryDao;

    @Autowired
    PlantSpaceSpotLogService logService;  

    public String addPlantSpaceSpot(PlantSpaceSpotDTO plantSpaceSpotDto) {
        try {
            log.info("in addPlantSpaceSpot");
            plantSpaceSpotDto.setPlantSpaceSpotId(Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
            dao.save(PlantSpaceSpotDtoToModel(plantSpaceSpotDto));
            detailsController.addSpotDetails(plantSpaceSpotDto);
            Date date = new Date();
            logService.addLong(plantSpaceSpotDto.getPlantSpaceSpotId(), "spot created", date); 
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public List<PlantSpaceSpotDTO> getPlantSpaceSpots() {
        try {
            log.info("in getPlantSpaceSpots");
            List<PlantSpaceSpot> plantSpaceSpots = (List<PlantSpaceSpot>) dao.findAll();
            if(!NatureOmUtil.isObjectValid(plantSpaceSpots))
                throw new Exception("no such object");
            List<PlantSpaceSpotDTO> plantSpaceSpotDtos = new ArrayList<PlantSpaceSpotDTO>();
            plantSpaceSpots.forEach((spot) -> {
                plantSpaceSpotDtos.add(PlantSpaceSpotModelToDto(spot));
            });
            log.info("list of object fetched");
            log.debug("fetched list object=", plantSpaceSpotDtos);
            return plantSpaceSpotDtos;
        } catch (Exception e) {
            return null;
        }
    }

    public PlantSpaceSpotDTO getPlantSpaceSpot(Long plantSpaceSpotId) {
        try {
            log.info("in getPlantSpaceSpot, object being fetched");
            PlantSpaceSpotDTO plantSpaceSpot = PlantSpaceSpotModelToDto(dao.findById(plantSpaceSpotId).get());
            if(!NatureOmUtil.isObjectValid(plantSpaceSpot))
                throw new Exception("no such object");
            log.info("object was fetched");
            log.debug("the fetched object=" + plantSpaceSpot);
            return plantSpaceSpot;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String updatePlantSpaceSpot(PlantSpaceSpotDTO plantSpaceSpot) {

        try {
            log.info("in updatePlantSpaceSpot, object being updated");
            dao.save(PlantSpaceSpotDtoToModel(plantSpaceSpot));
            log.info("object was updated");
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public String updatePlantSpaceSpotStatus(PlantSpaceSpotDTO plantSpaceSpot) {

        try {
            log.info("inside updatePlantSpaceSpotStatus");
            dao.save(PlantSpaceSpotDtoToModel(plantSpaceSpot));
            log.info("object status was updated");
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public String deleteSpotsOfSpacec(Long spaceId) {
        try {
            log.info("in deleteSpotsOfSpacec");
            detailService.deleteDetailsOfSpaces(spaceId);
            dao.deleteSpotsOfSpace(spaceId);
            log.info("in deleteSpotsOfSpacec obj Was deleted");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String deletePlantSpaceSpot(Long plantSpaceSpotId) {
        try {
            log.info("in deletePlantSpaceSpot");
            detailService.deleteDetailsOfSpot(plantSpaceSpotId);
            dao.deleteById(plantSpaceSpotId);
            log.info("object was deleted");
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public PlantSpaceSpotDTO PlantSpaceSpotModelToDto(PlantSpaceSpot plantSpaceSpot) {
        PlantSpaceSpotDTO plantSpaceSpotDto = new PlantSpaceSpotDTO();
        plantSpaceSpotDto.setPlantSpaceSpotName(plantSpaceSpot.getPlantSpaceSpotName());
        plantSpaceSpotDto.setPlantMasterId(plantSpaceSpot.getPlantSpaceId().getPlantSpacePlantmasterId().getUser_role_id());
        plantSpaceSpotDto.setPlantSpaceSpotId(plantSpaceSpot.getPlantSpaceSpotId());
        plantSpaceSpotDto.setSpotStatus(plantSpaceSpot.getSpotStatus());
        plantSpaceSpotDto.setPlantSpaceSpotDesc(plantSpaceSpot.getPlantSpaceSpotDesc());
        plantSpaceSpotDto.setPlantSpaceId(plantSpaceSpot.getPlantSpaceId().getPlantSpaceId());
        plantSpaceSpotDto.setPlantSpaceName(plantSpaceSpot.getPlantSpaceId().getPlantSpaceName());
        plantSpaceSpotDto.setCategoryId(plantSpaceSpot.getPlantCategoryId().getPlantCategoryId());
        plantSpaceSpotDto.setPlantCategoryName(plantSpaceSpot.getPlantCategoryId().getPlantCategoryName());
        log.debug("inside PlantSpaceSpotDtoToModel obj converted from model to dto");
        return plantSpaceSpotDto;
    }

    public PlantSpaceSpot PlantSpaceSpotDtoToModel(PlantSpaceSpotDTO plantSpaceSpotDto) {
        PlantSpaceSpot plantSpaceSpot = new PlantSpaceSpot();
        plantSpaceSpot.setSpotStatus(plantSpaceSpotDto.getSpotStatus());
        plantSpaceSpot.setPlantSpaceSpotId(plantSpaceSpotDto.getPlantSpaceSpotId());
        plantSpaceSpot.setPlantSpaceSpotName(plantSpaceSpotDto.getPlantSpaceSpotName());
        plantSpaceSpot.setPlantSpaceSpotDesc(plantSpaceSpotDto.getPlantSpaceSpotDesc());
        plantSpaceSpot.setPlantSpaceId(SpaceDao.findById(plantSpaceSpotDto.getPlantSpaceId()).get());
        plantSpaceSpot.setPlantSpaceName(plantSpaceSpotDto.getPlantSpaceName());
        plantSpaceSpot.setPlantCategoryId(categoryDao.findById(plantSpaceSpotDto.getCategoryId()).get());
        plantSpaceSpot.setPlantCategoryName(plantSpaceSpotDto.getPlantCategoryName());
        log.debug("inside PlantSpaceSpotDtoToModel obj converted from dto to model");
        return plantSpaceSpot;
    }

	public ArrayList<PlantSpaceSpotDTO> getPlantSpotsOfSpace(Long spaceId) {
        try{
        log.info("in getPlantSpotsOfSpace");
		ArrayList<PlantSpaceSpot> spotsOfSpace =dao.getPlantSpotsOfSpace(spaceId);
        if(!NatureOmUtil.isObjectValid(spotsOfSpace))
            throw new Exception("no such object");
        ArrayList<PlantSpaceSpotDTO> spotsOfSpaceDto = new ArrayList<>();
        spotsOfSpace.forEach((spot)->{spotsOfSpaceDto.add(PlantSpaceSpotModelToDto(spot));});
        return spotsOfSpaceDto;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
	}
}
