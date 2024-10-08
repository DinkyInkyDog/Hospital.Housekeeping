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
public class DepartmentData {
	
	private Long departmentId;
	
	private String departmentName;
	private String departmentFloor;
	
	private Set<HousekeeperResponse> housekeepers = new HashSet<>();
	
	private Set<RoomResponse> rooms = new HashSet<>();
	
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
	@NoArgsConstructor
	public class HousekeeperResponse {
		private Long housekeeperId;
		private String housekeeperFirstName;
		private String housekeeperLastName;
		private String housekeeperPager;
		
		
		public HousekeeperResponse(Housekeeper hk) {
			housekeeperId = hk.getHousekeeperId();
			housekeeperFirstName = hk.getHousekeeperFirstName();
			housekeeperLastName = hk.getHousekeeperLastName();
			housekeeperPager = hk.getHousekeeperPager();
		}
	}
	
	@Data
	@NoArgsConstructor
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
