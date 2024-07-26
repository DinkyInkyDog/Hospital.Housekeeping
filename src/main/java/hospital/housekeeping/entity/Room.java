package hospital.housekeeping.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import hospital.housekeeping.service.HospitalService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Room {
	
	@Transient
	@Autowired
	private HospitalService hs = new HospitalService();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	@EqualsAndHashCode.Exclude
	private String roomName;
	@EqualsAndHashCode.Exclude
	private boolean roomCleanedToday;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "housekeeper_id")
	private Housekeeper roomCleanedBy;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JoinColumn(name = "department_id", nullable = false)
	private Department roomDepartment;
	
	
	public Room(boolean cleanedToday, Long housekeeperId, Long departmentId, int index) {
		this.roomCleanedToday = cleanedToday;
		
		if(housekeeperId != null) {
			Housekeeper housekeeper = hs.getHousekeeperById(housekeeperId);
		}
		
		if(departmentId != null) {
			Department department = hs.getDepartmentById(departmentId);
		}
		
		this.roomName = "" + roomDepartment .getDepartmentFloorNumber() + index;
	}
}
