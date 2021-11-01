package in.natureom.plantcare.plantspace.controller;

import in.natureom.plantcare.plantspace.dto.PlantSpaceSpotDTO;
import in.natureom.plantcare.plantspace.service.PlantSpaceSpotDetailService;
import in.natureom.plantcare.plantspace.service.PlantSpaceSpotService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static in.natureom.plantcare.util.CommonConstants.Success;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents garden or farm where plant are and needs to be taken care of
 * where one or more similar plants are planted
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/plant-space/spot")
public class PlantSpaceSpotController {

    @Autowired
    PlantSpaceSpotDetailService detailDao;

    @Autowired
    PlantSpaceSpotService plantSpaceSpotService;

    @Autowired
    PlantSpaceSpotDetailsController detailController;

    @PostMapping("/")
    public ResponseEntity<String> addPlantSpaceSpot(@RequestBody PlantSpaceSpotDTO plantSpaceSpotDto) {
        log.info("in addPlantSpaceSpot");
        String response = plantSpaceSpotService.addPlantSpaceSpot(plantSpaceSpotDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlantSpaceSpotDTO>> getPlantSpaceSpots() {
        log.info("in getPlantSpaceSpots");
        List<PlantSpaceSpotDTO> plantSpaces = plantSpaceSpotService.getPlantSpaceSpots();
        return new ResponseEntity<>(plantSpaces, NatureOmUtil.isObjectValid(plantSpaces) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{spotId}")
    public ResponseEntity<PlantSpaceSpotDTO> getPlantSpaceSpot(@PathVariable("spotId") Long spotId) {
        log.info("in getPlantSpaceSpot");
        PlantSpaceSpotDTO plantSpace = plantSpaceSpotService.getPlantSpaceSpot(spotId);
        return new ResponseEntity<>(plantSpace, NatureOmUtil.isObjectValid(plantSpace) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/spotsOfSpace/{spaceId}")
    public ResponseEntity<ArrayList<PlantSpaceSpotDTO>> getPlantSpaceSpots(@PathVariable("spaceId") Long spaceId) {
        log.info("in getPlantSpaceSpots");
            ArrayList<PlantSpaceSpotDTO> SpotsOfSpace = plantSpaceSpotService.getPlantSpotsOfSpace(spaceId);
        return new ResponseEntity<>(SpotsOfSpace, NatureOmUtil.isObjectValid(SpotsOfSpace) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updatePlantSpaceSpot(PlantSpaceSpotDTO plantSpaceSpotDto) {
        log.info("in updatePlantSpaceSpot");
        String response = plantSpaceSpotService.updatePlantSpaceSpot(plantSpaceSpotDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{spotId}")
    public ResponseEntity<String> deletePlantSpaceSpot(@PathVariable("spotId") Long spotId) {
        log.info("in deletePlantSpaceSpot");
        String response = plantSpaceSpotService.deletePlantSpaceSpot(spotId);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

}
