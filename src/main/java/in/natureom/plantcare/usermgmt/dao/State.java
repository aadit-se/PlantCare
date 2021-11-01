package in.natureom.plantcare.usermgmt.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="state_table")
public class State {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="stateid")
	int stateid;
	
	@Column(name="state_name")
	String state_name;

	public State(String state_name) {
		this.state_name = state_name;
	}

	public State() {}
}