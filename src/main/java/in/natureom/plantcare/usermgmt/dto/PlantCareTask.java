package in.natureom.plantcare.usermgmt.dto;

import lombok.Data;
import java.util.List;

@Data
public class PlantCareTask {

    String plantCareActionId;

    String plantCareActionName;

    String plantCareActionDesc;

    List<PlantSpotAction> plantSpotActions;
}
