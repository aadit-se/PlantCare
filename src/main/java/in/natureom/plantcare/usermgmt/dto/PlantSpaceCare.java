package in.natureom.plantcare.usermgmt.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlantSpaceCare {

	Long plantMasterId;

	Long customerId;

	String customerName;

	String customerImgUrl;

	List<PlantSpaces> plantSpaces;

}
