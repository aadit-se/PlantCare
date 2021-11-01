package in.natureom.plantcare.plantspace.dto;

import java.util.ArrayList;

import lombok.Data;

//space details
@Data
public class PlantSpaceUpcomingDetailSpaceDetails {

    private Long spaceId;

    private String spaceName;

    private Long plantMasterId;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> waterSpot;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> fertilizeSpot;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> trimSpot;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> pesticideSpot;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> soilCultivation;

    private ArrayList<PlantSpaceUpcomingDetailSpotDetails> cleaning;
    
}
