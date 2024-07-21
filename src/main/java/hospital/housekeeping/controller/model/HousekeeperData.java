package hospital.housekeeping.controller.model;

import java.util.Set;

import lombok.Data;

@Data
public class HousekeeperData {

	private Long housekeeperId;
	
	private String housekeeperFirstName;
	private String housekeeperLastName;
	
	private Long housekeeperPager;
	
	private Set<DepartmentData> assignedDepartments;
	
	private Set<RoomData> roomsCleaned;
}
