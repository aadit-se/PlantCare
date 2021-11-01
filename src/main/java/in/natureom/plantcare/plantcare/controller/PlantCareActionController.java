package in.natureom.plantcare.plantcare.controller;

import in.natureom.plantcare.usermgmt.dto.PlantCareActionDto;
import in.natureom.plantcare.usermgmt.service.PlantCareActionService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static in.natureom.plantcare.util.CommonConstants.Success;

/**
 * Action needed to take care of plant like watering, cleaning, trimming etc
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/plant-care-action")
public class PlantCareActionController {

    @Autowired
    PlantCareActionService plantCareActionService;

    @PostMapping("/")
    public ResponseEntity<String> addPlantCareAction(@RequestBody PlantCareActionDto plantActionDto) {
        log.info("inside addPlantCareAction");
        String response = plantCareActionService.addPlantCareAction(plantActionDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlantCareActionDto>> getplantCareActions() {
        log.info("inside getplantCareActions");
        List<PlantCareActionDto> plantActionDtos = plantCareActionService.getPlantCareActions();
        return new ResponseEntity<>(plantActionDtos, NatureOmUtil.isObjectValid(plantActionDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<PlantCareActionDto> getUser(@PathVariable("roleId") int roleId) {
        log.info("inside getUser");
        PlantCareActionDto plantActionDto = plantCareActionService.getPlantCareAction(roleId);
        return new ResponseEntity<>(plantActionDto, NatureOmUtil.isObjectValid(plantActionDto) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


}
