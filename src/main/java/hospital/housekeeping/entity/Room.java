package hospital.housekeeping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	@EqualsAndHashCode.Exclude
	private String roomName;
	@EqualsAndHashCode.Exclude
	private boolean roomCleanedToday;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	private Housekeeper roomCleanedBy;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Column (nullable = false)
	private Department roomDepartment;
	
	public Room(boolean cleanedToday, Long housekeeperId, Long departmentId) {
		String depfloor = roomDepartment.departmentFloor;
		char floor = depfloor.charAt(0);
		
		this.roomName = "" + floor;
	}
}
