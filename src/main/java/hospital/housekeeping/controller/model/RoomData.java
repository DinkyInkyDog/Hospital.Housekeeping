package hospital.housekeeping.controller.model;

import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;
import lombok.Data;


@Data
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
		
		roomCleanedBy = new HousekeeperResponse(room.getRoomCleanedBy());
		roomDepartment = new DepartmentResponse(room.getRoomDepartment());
	}
	
	
	public void setRoomName() {
		
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
		private Long housekeeperPager;
		
		public HousekeeperResponse(Housekeeper hk) {
			housekeeperId = hk.getHousekeeperId();
			housekeeperFirstName = hk.getHousekeeperFirstName();
			housekeeperLastName = hk.getHousekeeperLastName();
			housekeeperPager = hk.getHousekeeperPager();
		}
	}
}
