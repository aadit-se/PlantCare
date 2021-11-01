package in.natureom.plantcare.plantspace.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdminSpotDetails {
    
    private Long spotId;

    private String spotName;

    private String categoryName;

    private String spotStatus;

    private String spotDescription;

    // service details

    private Date lastWatered;

    private Date nextWater;

    private Date lastFertilized;

    private Date nextFertilize;

    private Date lastPesticideSprayed;

    private Date nextPesticideSpraye;

    private Date lastSoilCultivated;

    private Date nextSoilCultivate;

    private Date lastTrimmed;

    private Date nextTrim;

    private Date lastCleaned;

    private Date nextClean;
}

