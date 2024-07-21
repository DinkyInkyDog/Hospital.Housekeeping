package hospital.housekeeping.controller.model;

import java.util.Set;

import lombok.Data;

@Data
public class DepartmentData {
	
	private Long departmentId;
	
	private String departmentName;
	private String departmentFloor;
	
	private Set<HousekeeperData> housekeepers;
	
	private Set<RoomData> rooms;
}
