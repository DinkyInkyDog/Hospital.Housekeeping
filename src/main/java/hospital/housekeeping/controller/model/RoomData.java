package hospital.housekeeping.controller.model;

import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RoomData {
	
	
	private Long roomId;
	
	private String roomName;
	private boolean roomCleanedToday;
	
	private HousekeeperResponse roomCleanedBy;
	
	private DepartmentResponse roomDepartment;
	
	
	public RoomData(Room room) {
		roomId = room.getRoomId();
		roomName = room.getRoomName();
		roomCleanedToday = room.isRoomCleanedToday();

		if (room.getRoomCleanedBy() != null) {
			roomCleanedBy = new HousekeeperResponse(room.getRoomCleanedBy());
		}
		try {
		roomDepartment = new DepartmentResponse(room.getRoomDepartment());
		}catch (NullPointerException e) {
			throw new NullPointerException("Room with ID=" + roomId +" must have a department it's assigned to.");
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
}
