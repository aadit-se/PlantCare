package in.natureom.plantcare.plantspace.dto;

import lombok.Data;

@Data
public class UpdateSpotDetailsDto {

    private Long spotId;

    private String actionPerformed;

    private Boolean actionCompleted=false;
}
