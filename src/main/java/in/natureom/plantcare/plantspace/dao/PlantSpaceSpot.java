package in.natureom.plantcare.plantspace.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.natureom.plantcare.plantcare.dao.PlantCareInterval;
import lombok.Data;

@Data
@Entity
@Table(name = "plant_space_spot")
public class PlantSpaceSpot {
    
    @Id
    @Column(name = "plant_space_spot_id")
    private Long plantSpaceSpotId;
    
    @Column(name = "plant_space_spot_name")
    private String plantSpaceSpotName;
    
    @Column(name = "plant_space_spot_desc")
    private String plantSpaceSpotDesc;
    
    @OneToOne
    @JoinColumn(name = "plant_space_id")
    private PlantSpace plantSpaceId;
    
    @Column(name = "plant_space_name")
    private String plantSpaceName;
    
    @OneToOne
    @JoinColumn(name = "plant_category_id")
    private PlantCareInterval plantCategoryId;
    
    @Column(name = "plant_category_name")
    private String plantCategoryName;

    @Column(name = "plan_spot_status")
    private String spotStatus;
}
