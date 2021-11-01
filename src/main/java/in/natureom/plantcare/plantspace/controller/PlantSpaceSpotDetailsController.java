package in.natureom.plantcare.plantspace.controller;

import java.util.ArrayList;


import in.natureom.plantcare.util.NatureOmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotDetails;
import in.natureom.plantcare.plantspace.dto.PlantSpaceSpotDTO;
import in.natureom.plantcare.plantspace.dto.UpdateSpotDetailsDto;
import in.natureom.plantcare.plantspace.dto.viewUpcomingDetailsDto;
import in.natureom.plantcare.plantspace.service.PlantSpaceSpotDetailService;
import lombok.extern.slf4j.Slf4j;

import static in.natureom.plantcare.util.CommonConstants.Success;

@RestController
@Slf4j
@RequestMapping("/plant-spot-detail/")
public class PlantSpaceSpotDetailsController {
    @Autowired
    PlantSpaceSpotDetailService detailsService;

    public ResponseEntity<String> addSpotDetails(PlantSpaceSpotDTO SpotDto) {
        log.info("in addSpotDetails");
        String response = detailsService.addSpotDetails(SpotDto);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


    @GetMapping("/allSpaceDetails/")
    public ResponseEntity<ArrayList<viewUpcomingDetailsDto>> viewUpcomingDetails() {
        log.info("in viewUpcomingDetails");
        String idBelongsTo=null;
        Long id=null;
        ArrayList<viewUpcomingDetailsDto> response = detailsService.viewUpcomingDetails(id,idBelongsTo);
        return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


// psot request to be sent on this url when gardener clicks on done
    @PostMapping("/updateSpotDetails/")
    public ResponseEntity<String> updateSpotDetails(@RequestBody UpdateSpotDetailsDto updateSpotDetailsDto) {
        log.info("in updateSpotDetailsDto");
        log.info("updateSpotDetailsDto="+updateSpotDetailsDto);
        String response = detailsService.updateSpotDeatils(updateSpotDetailsDto);
        return new ResponseEntity<>(response,response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    
    @GetMapping("/PlantMasterSpaceDetails/{plantMaster}")
    public ResponseEntity<ArrayList<viewUpcomingDetailsDto>> viewUpcomingDetailsForPlantMaster(@PathVariable("plantMaster") Long plantMaster) {
        log.info("in viewUpcomingDetailsForPlantMaster");
        String idBelongsTO="plantMaster";
        ArrayList<viewUpcomingDetailsDto> response = detailsService.viewUpcomingDetails(plantMaster,idBelongsTO);
        return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/CustomerSpaceDetails/{customerId}")
    public ResponseEntity<ArrayList<viewUpcomingDetailsDto>> viewUpcomingDetailsForCustomer(@PathVariable("customerId") Long customerId) {
        log.info("in viewUpcomingDetailsForCustomer");
        String idBelongsTO="customer";
        ArrayList<viewUpcomingDetailsDto> response = detailsService.viewUpcomingDetails(customerId,idBelongsTO);
        return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/SpotDetails/{spotId}")
    public ResponseEntity<PlantSpaceSpotDetails> viewUpcomingDetailsForSpot(@PathVariable("spotId") Long spotId) {
        log.info("in viewUpcomingDetailsForSpot");
        PlantSpaceSpotDetails response = detailsService.viewSpotDetails(spotId);
        return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
    
}
