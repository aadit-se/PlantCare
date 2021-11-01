package in.natureom.plantcare.usermgmt.controller;

import in.natureom.plantcare.usermgmt.dto.PlantMasterCustomerSpaceDto;
import in.natureom.plantcare.usermgmt.dto.PlantMasterInformationDto;
import in.natureom.plantcare.usermgmt.dto.PlantMastersListDto;
import in.natureom.plantcare.usermgmt.service.PlantMasterService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Plant Master - generally used for a person who takes care of plant i.e. gardener / farmer
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/plant-master")
public class PlantMasterController {

    @Autowired
    PlantMasterService plantMasterService;

    @GetMapping("/plantMasters/")
    public ResponseEntity<ArrayList<PlantMasterInformationDto>> getPlantMasters()
     {
        log.info("Start: getPlantMasters");
        ArrayList<PlantMasterInformationDto> plantMasterDtos = plantMasterService.getPlantMasters();
        return new ResponseEntity<>(plantMasterDtos, NatureOmUtil.isObjectValid(plantMasterDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("spaces/{plantMasterId}")
    public ResponseEntity<List<PlantMasterCustomerSpaceDto>> getPlantSpaceCare(@PathVariable("plantMasterId") Long plantMasterId) {
        log.info("in getPlantSpaceCare");
        List<PlantMasterCustomerSpaceDto> plantSpaceCare = plantMasterService.getPlantSpaceCare(plantMasterId);
        return new ResponseEntity<>(plantSpaceCare, NatureOmUtil.isObjectValid(plantSpaceCare) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{plantMasterId}")
    public ResponseEntity<PlantMasterInformationDto> getPlantMaster(@PathVariable("plantMasterId") Long plantMasterId) {
        log.info(" getPlantSpaceCare");
        PlantMasterInformationDto plantMaster = plantMasterService.getPlantMaster(plantMasterId);
        return new ResponseEntity<>(plantMaster, NatureOmUtil.isObjectValid(plantMaster) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/plantMastersList/")
    public ResponseEntity<ArrayList<PlantMastersListDto>> getPlantMastersList()
     {
        log.info("in getPlantMasters");
        ArrayList<PlantMastersListDto> plantMasterNameDtos = plantMasterService.getPlantMastersList();
        return new ResponseEntity<>(plantMasterNameDtos, NatureOmUtil.isObjectValid(plantMasterNameDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
}
