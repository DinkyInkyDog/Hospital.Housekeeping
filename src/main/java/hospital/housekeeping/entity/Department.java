package hospital.housekeeping.entity;

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

@Entity
@Data
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	private String departmentName;
	private String departmentFloor;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "department_housekeeper", joinColumns = 
			@JoinColumn(name = "department_id"),
			inverseJoinColumns = 
				@JoinColumn(name = "housekeeper_id"))
	private Set<Housekeeper> housekeepers;
	
	@OneToMany(mappedBy = "roomDepartment", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Set<Room> rooms;
}
