package hospital.housekeeping.controller.model;

import java.util.Set;

import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;
import lombok.Data;

@Data
public class HousekeeperData {

	private Long housekeeperId;
	
	private String housekeeperFirstName;
	private String housekeeperLastName;
	
	private Long housekeeperPager;
	
	private Set<DepartmentResponse> assignedDepartments;
	
	private Set<RoomResponse> roomsCleaned;
	
	public HousekeeperData(Housekeeper hk) {
		housekeeperId = hk.getHousekeeperId();
		housekeeperFirstName = hk.getHousekeeperFirstName();
		housekeeperLastName = hk.getHousekeeperLastName();
		housekeeperPager = hk.getHousekeeperPager();
		
		for(Department dep : hk.getAssignedDepartments()) {
			assignedDepartments.add(new DepartmentResponse(dep));
		}
		
		for(Room room : hk.getRoomsCleaned()) {
			roomsCleaned.add(new RoomResponse(room));
		}
	}
	
	@Data
	public class DepartmentResponse {
		private Long departmentId;
		private String departmentName;
		private String departmentFloor;
		
		public DepartmentResponse(Department dd) {
			departmentId = dd.getDepartmentId();
			departmentName = dd.getDepartmentName();
			departmentFloor = dd.getDepartmentFloor();
		}
	}
	
	@Data
	public class RoomResponse {
		private Long roomId;
		private String roomName;
		private boolean roomCleanedToday;
		
		public RoomResponse(Room room) {
			roomId = room.getRoomId();
			roomName = room.getRoomName();
			roomCleanedToday = room.isRoomCleanedToday();
		}
	}
}
