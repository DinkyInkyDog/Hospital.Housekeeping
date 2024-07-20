package hospital.housekeeping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
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
	private Department roomDepartment;
}
