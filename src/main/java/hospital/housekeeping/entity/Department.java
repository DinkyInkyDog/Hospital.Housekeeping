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
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	private String departmentName;
	private String departmentFloor;
	
	@ToString.Exclude
	private Long departmentFloorNumber;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "assignedDepartments", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Housekeeper> housekeepers = new HashSet<>();
	
	@OneToMany(mappedBy = "roomDepartment", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Set<Room> rooms = new HashSet<>();
}
