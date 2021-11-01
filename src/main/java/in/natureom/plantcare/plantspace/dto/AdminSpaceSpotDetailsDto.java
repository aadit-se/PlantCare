package in.natureom.plantcare.plantspace.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AdminSpaceSpotDetailsDto {
    private Long spaceId;

    private String spaceName;

    private Long plantMasterId;

    private String plantMasterName;

    private String spaceDescription ;

    private ArrayList<AdminSpotDetails> spots;
    
}
