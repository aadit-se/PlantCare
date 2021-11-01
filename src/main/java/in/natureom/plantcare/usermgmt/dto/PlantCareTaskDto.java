package in.natureom.plantcare.usermgmt.dto;

import lombok.Data;
import java.util.List;

public class PlantCareTaskDto {

    String plantCareActionId;

    String plantCareActionName;

    String plantCareActionDesc;

    List<PlantSpotActionDto> plantSpotActions;
}
