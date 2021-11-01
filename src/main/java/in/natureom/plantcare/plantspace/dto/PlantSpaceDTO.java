package in.natureom.plantcare.plantspace.dto;

import lombok.Data;

@Data
public class PlantSpaceDTO {
    // garden or farm
    Long plantSpaceId;

    String plantSpaceName;

    String plantSpaceStatus;

    String plantSpaceDesc;

    String plantSpaceAddress;

    String plantSpaceLandmark;

    Long userCustomerId;

    String customerName;

    Long userPlantMasterId;

    String plantMasterName;

}
