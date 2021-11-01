package in.natureom.plantcare.plantspace.dto;

import lombok.Data;

/*
 * Spot - a physical place within a garden / farm where one or more similar types of plants are planted
 */
@Data
public class PlantSpaceSpotDTO {

    private Long plantSpaceId;

    private Long plantMasterId;

    private String plantSpaceSpotName;

    private String plantSpaceSpotDesc;

    private Long plantSpaceSpotId;

    private String plantSpaceName;

    private int CategoryId;

    private String plantCategoryName;

    private String spotStatus;
  
}
