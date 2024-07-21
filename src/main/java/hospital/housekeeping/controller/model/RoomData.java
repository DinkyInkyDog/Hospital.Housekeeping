package hospital.housekeeping.controller.model;

import lombok.Data;


@Data
public class RoomData {
	private Long roomId;
	
	private String roomName;
	private boolean roomCleanedToday;
	
	private HousekeeperData roomCleanedBy;
	
	private DepartmentData roomDepartment;
}
