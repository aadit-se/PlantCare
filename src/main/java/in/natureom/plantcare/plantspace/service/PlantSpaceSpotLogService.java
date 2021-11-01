package in.natureom.plantcare.plantspace.service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotLogRepo;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpot;
import in.natureom.plantcare.plantspace.dao.PlantSpaceSpotLog;
import in.natureom.plantcare.plantspace.dto.PlantSpaceSpotDTO;

@Service
public class PlantSpaceSpotLogService {
    @Autowired
    PlantSpaceSpotLogRepo dao;

    @Autowired
    PlantSpaceSpotService spotService;

    public void addLong(Long spotId, String actionName,Date date)
    {
        PlantSpaceSpotLog log = new PlantSpaceSpotLog();
        PlantSpaceSpot spot=spotService.PlantSpaceSpotDtoToModel(spotService.getPlantSpaceSpot(spotId));
        log.setLogId(Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
        log.setPlantMasterId(spot.getPlantSpaceId().getPlantSpacePlantmasterId());
        log.setActionTiming(date);
        log.setActionDone(actionName);
        log.setSpotName(spot.getPlantSpaceSpotName());
        log.setPlantSpotId(spot);
        log.setCustomerId(spot.getPlantSpaceId().getPlantSpaceCustomerId());
        dao.save(log);
    }
}
