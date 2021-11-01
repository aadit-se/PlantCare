package in.natureom.plantcare.usermgmt.service;

import in.natureom.plantcare.plantspace.controller.PlantSpaceController;
import in.natureom.plantcare.usermgmt.dao.NatureOmRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmRoleRepo;
import in.natureom.plantcare.usermgmt.dao.NatureOmUser;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRepo;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRoleRepo;
import in.natureom.plantcare.usermgmt.dao.State;
import in.natureom.plantcare.usermgmt.dao.StateRepo;
import in.natureom.plantcare.usermgmt.dto.UserInformationDto;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static in.natureom.plantcare.util.CommonConstants.Failed;
import static in.natureom.plantcare.util.CommonConstants.Success;
import static in.natureom.plantcare.util.CommonConstants.*;

@Service
@Slf4j
public class NomUserService {

    @Autowired
    PlantSpaceController spaceController;

    @Autowired
    NatureOmUserRepo dao;

    @Autowired
    NomUserRoleService userRoleService;

    @Autowired
    NatureOmRoleRepo roleDao;
    
    @Autowired
    StateRepo stateRepo;
    
    @Autowired
    NatureOmUserRoleRepo userRoleDao;


    public NatureOmUser userInformationDtoToModel(UserInformationDto userDto) {
        NatureOmUser user = new NatureOmUser();
        user.setUserId(Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
        user.setUserContact(Long.valueOf(userDto.getUserContact()));
        user.setUserHome(userDto.getUserHome());
        user.setUserLocality(userDto.getUserLocality());
        user.setUserLandMark(userDto.getUserLandMark());
        user.setUserCity(userDto.getUserCity());
        user.setUserState(getState(userDto).get());
        user.setUserPin(userDto.getUserPin());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserGender(userDto.getUserGender());
        user.setUserName(userDto.getUserName());
        user.setUserDob(userDto.getUserDob());
        //user.setRoleId(userDto.getRoleId());
        log.debug("in NomUserService inside NomUserDtoToModel converting Dto  to Model object");
        return user;
    }

	private Optional<State> getState(UserInformationDto userDto) {
		
		Optional<State> state = stateRepo.findById(userDto.getUserState().getStateid());
		return state;
	}

    public UserInformationDto UserInformationModelTODto(NatureOmUser user) {
        UserInformationDto userDto = new UserInformationDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserContact(user.getUserContact());
        userDto.setUserHome(user.getUserHome());
        userDto.setUserLocality(user.getUserLocality());
        userDto.setUserLandMark(user.getUserLandMark());
        userDto.setUserCity(user.getUserCity());
        userDto.setUserState(user.getUserState());
        userDto.setUserPin(user.getUserPin());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserGender(user.getUserGender());
        userDto.setUserName(user.getUserName());
        userDto.setUserDob(user.getUserDob());
        userDto.setRoleId(user.getRoleId());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return userDto;
    }

    public String addUser(UserInformationDto userDto) {

        try {
            NatureOmUser user = userInformationDtoToModel(userDto);
            log.info("inside addUser,object being added");
            dao.save(user);
            log.debug("object={}", user);
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public List<UserInformationDto> getUsers() {

        try {
            log.info("inside getUsers, fetching list");
            List<NatureOmUser> users = (List<NatureOmUser>) dao.findAll();
            List<UserInformationDto> userDtos = new ArrayList<UserInformationDto>();
            if(!NatureOmUtil.isObjectValid(users))
                throw new Exception("no such object");
            users.forEach((user) -> {
            userDtos.add(UserInformationModelTODto(user));
            });
            log.info("list of users was fetched");
            log.debug("list={}", userDtos);
            return userDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public UserInformationDto getUser(Long userId) {
        try {
            log.info("in getUser");
            NatureOmUser user = dao.findByUserId(userId);

            if(!NatureOmUtil.isObjectValid(user))
            throw new Exception("no such object");
            log.info("object was fetched");
            log.debug("object fetched=", user);
            return UserInformationModelTODto(user);
        } catch (Exception e) {
            return null;
        }
    }

    public String updateUser(UserInformationDto userDto) {
        try {
            log.info("inside updateUser");
            NatureOmUser user = userInformationDtoToModel(userDto);
            dao.save(user);
            log.info("object was updated");
            log.debug("updated object=", user);
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public String deleteUser(Long userId) {
    try {
            log.info("in deleteUser");
            spaceController.nullUserValue(userId);
            userRoleService.deleteUserWithUserId(userId);
            dao.deleteById(userId);
            log.info("in deleteUser object was deleted");
            log.debug("deleted object with id={}", userId);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String addCustomer(UserInformationDto nomUserDto) {
    	try {
            NatureOmUser user = userInformationDtoToModel(nomUserDto);
            NatureOmRole role=roleDao.findByRoleName(ROLE_CUSTOMER);
            user.setRoleId(role.getRoleId());
            log.info("inside addUser, object being added");
            dao.save(user);
            userRoleService.addUserRole(user, ROLE_CUSTOMER);
            log.info("object was added");
            log.debug("object={}", user);
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }

    public String addPlantMaster(UserInformationDto nomUserDto) {
        try {
        	 NatureOmUser user = userInformationDtoToModel(nomUserDto);
        	 NatureOmRole role=roleDao.findByRoleName(ROLE_PLANT_MASTER);
        	 user.setRoleId(role.getRoleId());
        	 log.info("inside addUser, object being added");
             dao.save(user); 
             userRoleService.addUserRole(user, ROLE_PLANT_MASTER);
             log.info("object was added");
             log.debug("object={}", user);
            return Success;
        } catch (Exception e) {
            return Failed;
        }
    }
}
