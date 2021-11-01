package in.natureom.plantcare.plantspace.dto;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

//main object
@Data
public class viewUpcomingDetailsDto {

    private Date date;

    private ArrayList<PlantSpaceUpcomingDetailSpaceDetails> space;
}
