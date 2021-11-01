package in.natureom.plantcare.usermgmt.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CustomerCustomerSpaceDto {
    
    Long userCustomerId;

    String customerName;

    String customerImgUrl;
    
    ArrayList<CustomerSpaceDto> plantSpaces;
}
