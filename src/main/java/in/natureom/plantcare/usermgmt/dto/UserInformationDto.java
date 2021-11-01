package in.natureom.plantcare.usermgmt.dto;

import java.time.LocalDate;

import javax.persistence.Transient;

import in.natureom.plantcare.usermgmt.dao.State;
import lombok.Data;

@Data
public class UserInformationDto {

	private Long userId;

	private String userName;

	private LocalDate userDob;

	private String userGender;

	private Long userContact;

	private String userEmail;

	private String userHome;

	private String userLocality;

	private String userLandMark;

	private String userCity;

	private State userState;

	private int userPin;

	@Transient
	private Long roleId;
}
