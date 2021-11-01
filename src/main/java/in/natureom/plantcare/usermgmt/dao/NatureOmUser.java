package in.natureom.plantcare.usermgmt.dao;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "natureom_user")
public class NatureOmUser {
	@Id
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_gender")
	private String userGender;

	@Column(name = "user_dob", columnDefinition = "Date")
	private LocalDate userDob;

	@Column(name = "user_contact", length = 20)
	private Long userContact;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_home", length = 70)
	private String userHome;

	@Column(name = "user_locality", length = 70)
	private String userLocality;

	@Column(name = "user_landmark", length = 70)
	private String userLandMark;

	@Column(name = "user_city", length = 70)
	private String userCity;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "user_state")
	private State userState;

	@Column(name = "user_pin", length = 70)
	private int userPin;

	@Transient
	private Long roleId;

}
