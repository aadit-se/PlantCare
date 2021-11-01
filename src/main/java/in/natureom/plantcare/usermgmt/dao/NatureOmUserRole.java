package in.natureom.plantcare.usermgmt.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "natureom_user_role")
public class NatureOmUserRole {
	@Id
	@Column(name = "user_role_id")
	private Long user_role_id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private NatureOmUser user;

	@Column(name = "user_name")
	private String userName;

	@OneToOne
	@JoinColumn(name = "role_id")
	public NatureOmRole role;

	@Column(name = "role_name")
	public String roleName;
	
}
