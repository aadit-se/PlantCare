package in.natureom.plantcare.plantspace.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.natureom.plantcare.plantcare.dao.PlantCareInterval;
import lombok.Data;

@Entity
@Data
@Table(name = "space_spot_details")
public class PlantSpaceSpotDetails
{
	@Id
    @Column(name = "detail_id")
    private Long detailId;

    @OneToOne
    @JoinColumn(name = "details_Spot_id")
    private PlantSpaceSpot spot;

    @OneToOne
    @JoinColumn(name = "details_space_id")
    private PlantSpace space;

    @Column(name = "details_spot_name")
    private String spotName;
    
    @OneToOne
    @JoinColumn(name = "details_category_id")
    private PlantCareInterval category;
    
    @Column(name = "last_watered",columnDefinition = "Date")
    private Date lastWatered;

    @Column(name = "last_fertilized",columnDefinition = "Date")
    private Date lastFertilized;

    @Column(name = "last_pesticide",columnDefinition = "Date")
    private Date lastPesticide;

    @Column(name = "last_trim",columnDefinition = "Date")
    private Date lastTrimmed;

    @Column(name = "last_soil_cultivated",columnDefinition = "Date")
    private Date lastSoilCultivated;
  
    @Column(name = "last_cleaned",columnDefinition = "Date")
    private Date lastcleaned;
  

    @Column(name = "next_water",columnDefinition = "Date")
    private Date nextWatered;

    @Column(name = "next_fertilize",columnDefinition = "Date")
    private Date nextFertilize;

    @Column(name = "next_pesticide",columnDefinition = "Date")
    private Date nextPesticide;

    @Column(name = "next_trim",columnDefinition = "Date")
    private Date nextTrim;

    @Column(name = "next_soil_cultivated",columnDefinition = "Date")
    private Date nextSoilCultivated;

    @Column(name = "next_clean",columnDefinition = "Date")
    private Date nextClean;
  

}
