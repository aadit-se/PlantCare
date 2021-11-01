package in.natureom.plantcare.plantspace.controller;

import in.natureom.plantcare.plantspace.service.PlantSpaceService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import in.natureom.plantcare.plantspace.dto.PlantSpaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static in.natureom.plantcare.util.CommonConstants.Success;

import java.util.List;

/**
 * Plant Space -  this term is used for garden or farm
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/plant-space")
public class PlantSpaceController {
    
    @Autowired
    PlantSpaceService plantSpaceService;

    @PostMapping("/")
    public ResponseEntity<String> addPlantSpace(@RequestBody PlantSpaceDTO plantSpaceDto) {
        log.info("in addPlantScape, object being addded");
        String response = plantSpaceService.addPlantSpace(plantSpaceDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlantSpaceDTO>> getPlantSpaces() {
        log.info("in getPlantSpaces");
        List<PlantSpaceDTO> plantSpaces = plantSpaceService.getPlantSpaces();
        return new ResponseEntity<>(plantSpaces, NatureOmUtil.isObjectValid(plantSpaces) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{plantSpaceId}")
    public ResponseEntity<PlantSpaceDTO> getPlantSpace(@PathVariable("plantSpaceId") Long plantSpaceId) {
        log.info("in getPlantSpace");
        PlantSpaceDTO plantSpace = plantSpaceService.getPlantSpace(plantSpaceId);
        return new ResponseEntity<>(plantSpace, NatureOmUtil.isObjectValid(plantSpace) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updatePlantSpace(@RequestBody PlantSpaceDTO plantSpaceDto) {
        log.info("in updatePlantSpace");
        String response = plantSpaceService.updatePlantSpace(plantSpaceDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{plantSpaceId}")
    public ResponseEntity<String> deletePlantSpace(@PathVariable("plantSpaceId") Long plantSpaceId) {
        log.info("in deletePlantSpace");
        String response = plantSpaceService.deletePlantSpace(plantSpaceId);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<String> nullUserValue(Long userId) {
        log.info("in nullUserValue");
        String response = plantSpaceService.nullUserValue(userId);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


}
