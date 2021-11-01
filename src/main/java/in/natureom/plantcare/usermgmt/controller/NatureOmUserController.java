package in.natureom.plantcare.usermgmt.controller;

import in.natureom.plantcare.usermgmt.dto.UserInformationDto;
import in.natureom.plantcare.usermgmt.service.NomUserService;
import in.natureom.plantcare.util.CommonConstants;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * This is for NatureOm application user - it may be
 * plant master(gardener/farmer),
 * Customer(who owns garden/farm),
 * plant admin( who look after garden/farm maintenance)
 * super admin / natureom app admin who manages above
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/nom-user")
public class NatureOmUserController {
    @Autowired
    NomUserService nomUserService;

    @PostMapping("/")
    public ResponseEntity<String> addUser(@RequestBody UserInformationDto nomUserDto) {
        log.info(" inside adduser, adding object");
        String response = nomUserService.addUser(nomUserDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/customer/")
    public ResponseEntity<String> addCustomer(@RequestBody UserInformationDto nomUserDto) {
        log.info(" inside adduser, adding object");
        String response = nomUserService.addCustomer(nomUserDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/plant-master/")
    public ResponseEntity<String> addPlantMaster(@RequestBody UserInformationDto nomUserDto) {
        log.info("Start: addPlantMaster, request data: "+nomUserDto);
        String response = nomUserService.addPlantMaster(nomUserDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserInformationDto>> getUsers() {
        log.info(" inside get users");
        List<UserInformationDto> nomUsers = nomUserService.getUsers();
        return new ResponseEntity<>(nomUsers, NatureOmUtil.isObjectValid(nomUsers) ? HttpStatus.OK :HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInformationDto> getUser(@PathVariable("userId") Long userId) {
        log.info(" inside getUser");
        UserInformationDto nomUserDto = nomUserService.getUser(userId);
        return new ResponseEntity<>(nomUserDto, NatureOmUtil.isObjectValid(nomUserDto) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updateUser(@RequestBody UserInformationDto nomUserDto) {
        log.info(" inside updateuser");
        String response = nomUserService.updateUser(nomUserDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        log.info(" inside delete user");
        String response = nomUserService.deleteUser(userId);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


}
