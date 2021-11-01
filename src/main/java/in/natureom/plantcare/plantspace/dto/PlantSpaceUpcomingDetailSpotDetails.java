package in.natureom.plantcare.plantspace.dto;

import lombok.Data;

//spot details
@Data
public class PlantSpaceUpcomingDetailSpotDetails {

    private Long spotId;

    private String spotName;

    private Boolean actionCompleted=false;

    private String spotStatus;
}
