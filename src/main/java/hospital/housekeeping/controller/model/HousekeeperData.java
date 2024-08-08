package hospital.housekeeping.controller.model;

import java.util.HashSet;
import java.util.Set;

import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HousekeeperData {

	private Long housekeeperId;
	
	private String housekeeperFirstName;
	private String housekeeperLastName;
	
	private String housekeeperPager;
	
	private Set<DepartmentResponse> assignedDepartments = new HashSet<>();
	
	private Set<RoomResponse> roomsCleaned = new HashSet<>();
	
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
	public static class DepartmentResponse {
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
	public static class RoomResponse {
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
