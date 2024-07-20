package hospital.housekeeping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Housekeeper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long housekeeperId;
	
	private String housekeeperFirstName;
	private String housekeeperLastName;
	
	@Column(unique = true)
	private Long housekeeperPager;
	
	
	private Department primaryDepartment;
	
	private Department secondaryDepartment;
}
