package in.natureom.plantcare.plantspace.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlantSpaceSpotDetailsDto {
    
    private Date date;

    private String spotStatus;

    private Long DetailId;

    private Long spotId;

    private Long spaceId;

    private Long plantMasterId;

    private String spaceName;

    private String SpotName;

    private String CategoryName;

    private Date lastWatered;

    private Date lastFertilized;

    private Date lastPesticide;

    private Date lastTrimmed;

    private Date lastSoilCultivated;

    private Date lastCleaned;

    private Date nextWatered;

    private Date nextFertilize;

    private Date nextPesticide;

    private Date nextTrim;

    private Date nextSoilCultivated;

    private Date nextClean;
       
}
