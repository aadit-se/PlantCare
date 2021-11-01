package in.natureom.plantcare.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.natureom.plantcare.plantspace.dto.AdminSpaceSpotDetailsDto;
import in.natureom.plantcare.plantspace.service.PlantSpaceSpotDetailService;
import in.natureom.plantcare.usermgmt.service.AdminService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping(value = "admin-controller")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    PlantSpaceSpotDetailService detailsService;
    
    @GetMapping("/space-spot-details/{spaceId}")
    public ResponseEntity<AdminSpaceSpotDetailsDto> getSpaceSpotDetails(@PathVariable("spaceId") Long spaceId)
    {
        log.info("in getSpaceSpotDetails");
        AdminSpaceSpotDetailsDto response = detailsService.adminViewSpaceSpotDeatils(spaceId);
        return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
}
