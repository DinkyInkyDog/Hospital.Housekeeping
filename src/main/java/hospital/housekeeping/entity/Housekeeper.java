package hospital.housekeeping.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "housekeepers")
	private Set<Department> assignedDepartments;
	
	@OneToMany(mappedBy = "roomCleanedBy", cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Room> roomsCleaned;
}
