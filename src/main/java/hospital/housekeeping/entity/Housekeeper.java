package hospital.housekeeping.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	private String housekeeperPager;
	
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "department_housekeeper", joinColumns = 
	@JoinColumn(name = "department_id"),
	inverseJoinColumns = 
		@JoinColumn(name = "housekeeper_id"))
	private Set<Department> assignedDepartments = new HashSet<>();
	
	@OneToMany(mappedBy = "roomCleanedBy", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Room> roomsCleaned = new HashSet<>();
}
