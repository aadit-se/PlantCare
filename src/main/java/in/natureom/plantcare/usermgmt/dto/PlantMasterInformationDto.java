package in.natureom.plantcare.usermgmt.dto;

import java.time.LocalDate;

import javax.persistence.Transient;

import in.natureom.plantcare.usermgmt.dao.State;
import lombok.Data;

@Data
public class PlantMasterInformationDto {

	private Long plantMasterId;

	private String plantMasterName;

	private LocalDate plantMasterDob;

	private String plantMasterGender;

	private Long plantMasterContact;

	private String plantMasterEmail;

	private String plantMasterHome;

	private String plantMasterLocality;

	private String plantMasterLandMark;

	private String plantMasterCity;

	private State plantMasterState;

	private int plantMasterPin;

	@Transient
	private Long roleId;
}
