package in.natureom.plantcare.plantspace.service;

import in.natureom.plantcare.plantspace.dao.PlantSpace;
import in.natureom.plantcare.plantspace.dao.PlantSpaceRepo;
import in.natureom.plantcare.plantspace.dto.PlantSpaceDTO;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRoleRepo;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static in.natureom.plantcare.util.CommonConstants.Failed;
import static in.natureom.plantcare.util.CommonConstants.Success;

@Service
@Slf4j
public class PlantSpaceService {

    @Autowired
    PlantSpaceSpotDetailService detailService;

    @Autowired
    PlantSpaceSpotService spotService;

    @Autowired
    PlantSpaceRepo dao;

    @Autowired
    NatureOmUserRoleRepo userRoleDao;


    public String addPlantSpace(PlantSpaceDTO plantSpaceDto) {
        try {
            log.info("in addPlantSpace,object being added");
            plantSpaceDto.setPlantSpaceId(Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
            dao.save(PlantSpaceDtoToModel(plantSpaceDto));
            log.info("in addPlantSpace,object added");
            log.debug("added object=", plantSpaceDto);
          return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }

    }

    public List<PlantSpaceDTO> getPlantSpaces() {

        try {
            log.info("in getPlantSpaces");
            List<PlantSpace> plantSpaces = (List<PlantSpace>) dao.findAll();
            if(!NatureOmUtil.isObjectValid(plantSpaces))
                throw new Exception("no such object");
            List<PlantSpaceDTO> plantSpaceDtos = new ArrayList<PlantSpaceDTO>();
            plantSpaces.forEach((space) -> {
                plantSpaceDtos.add(PlantSpaceModelToDto(space));
            });
            log.info("list was fetched");
            log.debug("list of object=", plantSpaceDtos);
            return plantSpaceDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

	public PlantSpaceDTO getPlantSpace(Long plantSpaceId) {
		try {
			log.info("in getPlantSpace");
			PlantSpace plantSpace = dao.findById(plantSpaceId).get();
			if (!NatureOmUtil.isObjectValid(plantSpace))
				throw new Exception("no scuh object");

			log.debug("the object retreived=", plantSpace);
			return PlantSpaceModelToDto(plantSpace);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

    public String updatePlantSpace(PlantSpaceDTO plantSpaceDto) {
        try {
            log.info("in updatePlantSpace, object being updated");
            dao.save(PlantSpaceDtoToModel(plantSpaceDto));
            log.info("object was updated");
            log.debug("updated oject=", plantSpaceDto);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String updatePlantSpaceStatus(PlantSpaceDTO plantSpaceDto) {
        try { 
            log.debug("in updatePlantSpaceStatus");
            dao.save(PlantSpaceDtoToModel(plantSpaceDto));
            log.info("plantStatus was updated");
            log.debug("updated oject=", plantSpaceDto);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String deletePlantSpace(Long plantSpaceId) {
        try {
            log.info("in deletePlantSpace");
            spotService.deleteSpotsOfSpacec(plantSpaceId);
            dao.deleteById(plantSpaceId);
            log.info("obj deleted");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String nullUserValue(Long userId) {
        try {
            log.info("in nullUserValue");
            ArrayList<PlantSpace> spaces=dao.nullUserValue(userId);
            spaces.forEach((space)->{
                if(space.getPlantSpaceCustomerId().getUser_role_id().equals(userId))
                {   
                    space.setPlantSpaceCustomerId(null);
                    dao.save(space);
                }
                if(space.getPlantSpacePlantmasterId().getUser_role_id().equals(userId))
                {   
                    System.out.println("in plnatmaster");
                    space.setPlantSpacePlantmasterId(null);
                    dao.save(space);
                    System.out.println("after making null"+space);
                }
            });
            log.info("in nullUserValue, foreign key in spaces with that entery is made null");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public ArrayList<PlantSpace> getPlantSpaceByPlantMasterId(NatureOmUserRole plantMaster) {
        log.info("in getPlantSpaceByPlantMasterId ");
        ArrayList<PlantSpace> plantSpaces = dao.findByPlantSpacePlantmasterId(plantMaster);
        log.info("list of plant spaces fetched");
        return plantSpaces;
    }

    public ArrayList<PlantSpace> getPlantSpaceByCustomerId(NatureOmUserRole customer) {
        log.info("in getPlantSpaceByCustomerId ");
        ArrayList<PlantSpace> plantSpaces = dao.findByPlantSpaceCustomerId(customer);
        log.info("list of plant spaces fetched");
        return plantSpaces;
    }

    public void updateSpaceStatus(Long spaceId)
    {    	
    	PlantSpaceDTO spaceDto =getPlantSpace(spaceId);
    	String status[]=detailService.checkSpotStatus(spaceId);  
    	Boolean flagRed=false;
    	Boolean flagGreen=false;
    	Boolean flagYellow=false;
    	for(int i=0;i<status.length;i++)
    	{
    		if(status[i]=="red")
    			flagRed=true;
    		else if (status[i]=="yellow")
        			flagYellow=true;
    		else if(status[i]=="green")
    			flagGreen=true;
    	}
    	if(flagRed==true)
        {
    		spaceDto.setPlantSpaceStatus("red");
        }
        else if(flagYellow==true)
    	{
            spaceDto.setPlantSpaceStatus("yellow");
        }
        else if(flagGreen==true)
    	{
            	spaceDto.setPlantSpaceStatus("green");
        }
        updatePlantSpace(spaceDto);
    }

    public PlantSpace PlantSpaceDtoToModel(PlantSpaceDTO plantSpaceDto) {
        PlantSpace plantSpace = new PlantSpace();
        System.out.println(plantSpaceDto.getPlantSpaceId());
        plantSpace.setPlantSpaceId(plantSpaceDto.getPlantSpaceId());
        plantSpace.setPlantSpaceStatus(plantSpaceDto.getPlantSpaceStatus());
        plantSpace.setPlantSpaceName(plantSpaceDto.getPlantSpaceName());
        plantSpace.setPlantSpaceDesc(plantSpaceDto.getPlantSpaceDesc());
        plantSpace.setPlantSpaceAddress(plantSpaceDto.getPlantSpaceAddress());
        plantSpace.setPlantSpaceLandmark(plantSpaceDto.getPlantSpaceLandmark());
        plantSpace.setPlantSpaceCustomerId(userRoleDao.findById(plantSpaceDto.getUserCustomerId()).get());
        plantSpace.setPlantSpaceCustomerName(plantSpaceDto.getCustomerName());
        plantSpace.setPlantSpacePlantmasterId(userRoleDao.findById(plantSpaceDto.getUserPlantMasterId()).get());
        plantSpace.setPlantSpacePlantmasterName(plantSpaceDto.getPlantMasterName());
        log.debug("inside PlantSpaceDtoToModel obj converted from dto tomodel");
        return plantSpace;
    }

	public PlantSpaceDTO PlantSpaceModelToDto(PlantSpace plantSpace) {
		PlantSpaceDTO plantSpaceDto = new PlantSpaceDTO();
		plantSpaceDto.setPlantSpaceId(plantSpace.getPlantSpaceId());
		plantSpaceDto.setPlantSpaceName(plantSpace.getPlantSpaceName());
		plantSpaceDto.setPlantSpaceDesc(plantSpace.getPlantSpaceDesc());
		plantSpaceDto.setPlantSpaceAddress(plantSpace.getPlantSpaceAddress());
		plantSpaceDto.setPlantSpaceLandmark(plantSpace.getPlantSpaceLandmark());
		if (plantSpace.getPlantSpaceCustomerId() != null)
			plantSpaceDto.setUserCustomerId(plantSpace.getPlantSpaceCustomerId().getUser_role_id());
		plantSpaceDto.setCustomerName(plantSpace.getPlantSpaceCustomerName());
		if (plantSpace.getPlantSpacePlantmasterId() != null)
			plantSpaceDto.setUserPlantMasterId(plantSpace.getPlantSpacePlantmasterId().getUser_role_id());
		log.debug("inside PlantSpaceModelToDto obj converted from Modle to dto");
		return plantSpaceDto;
	}
}
