package in.natureom.plantcare.usermgmt.dto;


import java.util.ArrayList;

import lombok.Data;

@Data
public class PlantMasterCustomerSpaceDto {

    Long plantMasterId;

    Long userCustomerId;

    String customerName;

    String customerImgUrl;
    
    ArrayList<PlantMasterSpaceDto> plantSpaces;
}
