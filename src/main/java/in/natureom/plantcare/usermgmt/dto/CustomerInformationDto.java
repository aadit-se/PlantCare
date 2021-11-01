package in.natureom.plantcare.usermgmt.dto;

import java.time.LocalDate;

import in.natureom.plantcare.usermgmt.dao.State;
import lombok.Data;

@Data
public class CustomerInformationDto {
    private Long customerId;

	private String customerName;

	private LocalDate customerDob;

	private String customerGender;

	private Long customerContact;

	private String customerEmail;

	private String customerHome;

	private String customerLocality;

	private String customerLandMark;

	private String customerCity;

	private State customerState;

	private int customerPin;
}
