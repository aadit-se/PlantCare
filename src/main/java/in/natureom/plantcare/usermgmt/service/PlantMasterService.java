package in.natureom.plantcare.usermgmt.service;

import static in.natureom.plantcare.util.CommonConstants.ROLE_PLANT_MASTER;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natureom.plantcare.plantspace.dao.PlantSpace;
import in.natureom.plantcare.plantspace.service.PlantSpaceService;
import in.natureom.plantcare.usermgmt.dao.NatureOmRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUser;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRepo;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRoleRepo;
import in.natureom.plantcare.usermgmt.dto.PlantMasterCustomerSpaceDto;
import in.natureom.plantcare.usermgmt.dto.PlantMasterInformationDto;
import in.natureom.plantcare.usermgmt.dto.PlantMasterSpaceDto;
import in.natureom.plantcare.usermgmt.dto.PlantMastersListDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlantMasterService {

    @Autowired
    NomRoleService roleService;

    @Autowired
    PlantSpaceService spaceService;

    @Autowired
    NatureOmUserRoleRepo userRoleDao;

    @Autowired
    NatureOmUserRepo userDao;

    public ArrayList<PlantMasterCustomerSpaceDto> getPlantSpaceCare(Long plantMasterId) {

        try {
            log.info("inside of getPlantSpaceCare");
            NatureOmUserRole plantMaster = userRoleDao.findById(plantMasterId).get();
            List<PlantSpace> plantSpaces = spaceService.getPlantSpaceByPlantMasterId(plantMaster);
            ArrayList<PlantMasterCustomerSpaceDto> customerSpaceDtos = new ArrayList<>();
            ArrayList<PlantMasterSpaceDto> spaceDtos= new ArrayList<>();    
            PlantMasterCustomerSpaceDto customerSpaceDto = new PlantMasterCustomerSpaceDto();
            customerSpaceDto.setPlantMasterId(plantSpaces.get(0).getPlantSpacePlantmasterId().getUser_role_id());
            customerSpaceDto.setUserCustomerId(plantSpaces.get(0).getPlantSpaceCustomerId().getUser_role_id());
            customerSpaceDto.setCustomerName(plantSpaces.get(0).getPlantSpaceCustomerName());
            customerSpaceDto.setCustomerImgUrl(null);
            plantSpaces.forEach((space) -> {
                spaceDtos.add(PlantSpaceModelToDto(space));
            });
            customerSpaceDto.setPlantSpaces(spaceDtos);
            customerSpaceDtos.add(customerSpaceDto);
            return customerSpaceDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public PlantMasterInformationDto getPlantMaster(Long plantMasterId) {
        NatureOmUser plantMaster = userDao.findById(plantMasterId).get();
        return PlantMasterModelTODto(plantMaster);
    }

    public ArrayList<PlantMastersListDto> getPlantMastersList() {
    	log.info("inside getPlantMastersList");
    	try {
	        NatureOmRole role =roleService.getByRoleName(ROLE_PLANT_MASTER);
	        ArrayList<NatureOmUserRole> userRoles= userRoleDao.findByRole(role);
	        ArrayList<PlantMastersListDto> plantMasterNameDtos= new ArrayList<>();
	        for(int i=0; i<userRoles.size();i++)
	        {
	        	plantMasterNameDtos.add(plantMasterModelTOListDto(userDao.findByUserId(userRoles.get(i).getUser_role_id())));  
	        }
	        return plantMasterNameDtos;
    	}
    	catch (Exception e) {
    		log.error(e.getMessage());
    		return null;
    	}
    }

    public PlantMastersListDto plantMasterModelTOListDto(NatureOmUser user) {
        log.info("in plantMasterModelTOListDto");
    	PlantMastersListDto plantMasterNameDto = new PlantMastersListDto();
    	plantMasterNameDto.setPlantMasterId(user.getUserId());
        plantMasterNameDto.setPlantMasterName(user.getUserName());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return plantMasterNameDto;
    }


    public PlantMasterSpaceDto PlantSpaceModelToDto(PlantSpace space) {
        PlantMasterSpaceDto spaceDto = new PlantMasterSpaceDto();
        spaceDto.setPlantSpaceId(space.getPlantSpaceId());
        spaceDto.setPlantSpaceName(space.getPlantSpaceName());
        spaceDto.setPlantSpaceAddress(space.getPlantSpaceAddress());
        spaceDto.setPlantSpaceStatus(space.getPlantSpaceStatus());
        return spaceDto;
    }

    public PlantMasterInformationDto PlantMasterModelTODto(NatureOmUser user) {
        PlantMasterInformationDto plantMasterDto = new PlantMasterInformationDto();
        plantMasterDto.setPlantMasterId(user.getUserId());
        plantMasterDto.setPlantMasterContact(user.getUserContact());
        plantMasterDto.setPlantMasterHome(user.getUserHome());
        plantMasterDto.setPlantMasterLocality(user.getUserLocality());
        plantMasterDto.setPlantMasterLandMark(user.getUserLandMark());
        plantMasterDto.setPlantMasterCity(user.getUserCity());
        plantMasterDto.setPlantMasterState(user.getUserState());
        plantMasterDto.setPlantMasterPin(user.getUserPin());
        plantMasterDto.setPlantMasterEmail(user.getUserEmail());
        plantMasterDto.setPlantMasterGender(user.getUserGender());
        plantMasterDto.setPlantMasterName(user.getUserName());
        plantMasterDto.setPlantMasterDob(user.getUserDob());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return plantMasterDto;
    }

    public ArrayList<PlantMasterInformationDto> getPlantMasters() {
        try{
        log.info("Start: getPlantMasters");
        NatureOmRole role =roleService.getByRoleName(ROLE_PLANT_MASTER);
        ArrayList<NatureOmUserRole> userRoles= userRoleDao.findByRole(role);
        log.debug("NatureOmUserRole: "+userRoles);
        ArrayList<PlantMasterInformationDto> plantMasterDtos= new ArrayList<>();
        for(int i=0; i<userRoles.size();i++)
        {
            plantMasterDtos.add(plantMasterModelTODto(userDao.findByUserId(userRoles.get(i).getUser_role_id())));  
        }
        return plantMasterDtos;
    }
    catch(Exception e)
    {
        log.error(e.getMessage());
        return null;
    }
    }

    public PlantMasterInformationDto plantMasterModelTODto(NatureOmUser user) {
        PlantMasterInformationDto plantMasterDto = new PlantMasterInformationDto();
        plantMasterDto.setPlantMasterId(user.getUserId());
        plantMasterDto.setPlantMasterContact(user.getUserContact());
        plantMasterDto.setPlantMasterHome(user.getUserHome());
        plantMasterDto.setPlantMasterLocality(user.getUserLocality());
        plantMasterDto.setPlantMasterLandMark(user.getUserLandMark());
        plantMasterDto.setPlantMasterCity(user.getUserCity());
        plantMasterDto.setPlantMasterState(user.getUserState());
        plantMasterDto.setPlantMasterPin(user.getUserPin());
        plantMasterDto.setPlantMasterEmail(user.getUserEmail());
        plantMasterDto.setPlantMasterGender(user.getUserGender());
        plantMasterDto.setPlantMasterName(user.getUserName());
        plantMasterDto.setPlantMasterDob(user.getUserDob());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return plantMasterDto;
    }


}
