package hospital.housekeeping.controller.model;

import java.util.Set;


import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;
import lombok.Data;

@Data
public class DepartmentData {
	
	private Long departmentId;
	
	private String departmentName;
	private String departmentFloor;
	
	private Set<HousekeeperResponse> housekeepers;
	
	private Set<RoomResponse> rooms;
	
	public DepartmentData(Department de) {
		departmentId = de.getDepartmentId();
		departmentName = de.getDepartmentName();
		departmentFloor = de.getDepartmentFloor();
		
		for (Housekeeper keeper: de.getHousekeepers()) {
			housekeepers.add(new HousekeeperResponse(keeper));
		}
		
		for (Room room : de.getRooms()) {
			rooms.add(new RoomResponse(room));
		}
	}
	
	@Data
	public class HousekeeperResponse {
		private Long housekeeperId;
		private String housekeeperFirstName;
		private String housekeeperLastName;
		private Long housekeeperPager;
		
		
		public HousekeeperResponse(Housekeeper hk) {
			housekeeperId = hk.getHousekeeperId();
			housekeeperFirstName = hk.getHousekeeperFirstName();
			housekeeperLastName = hk.getHousekeeperLastName();
			housekeeperPager = hk.getHousekeeperPager();
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
