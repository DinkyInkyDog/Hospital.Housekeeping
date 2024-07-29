package hospital.housekeeping.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.housekeeping.controller.HospitalHousekeepingController.Entity;
import hospital.housekeeping.controller.model.DepartmentData;
import hospital.housekeeping.controller.model.HousekeeperData;
import hospital.housekeeping.controller.model.RoomData;
import hospital.housekeeping.dao.DepartmentDao;
import hospital.housekeeping.dao.HousekeeperDao;
import hospital.housekeeping.dao.RoomDao;
import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;
import hospital.housekeeping.entity.Room;

@Service
public class HospitalService {
	
	
	
	@Autowired
	private DepartmentDao dd;
	
	@Autowired
	private HousekeeperDao hd;
	
	@Autowired
	private RoomDao rd;

	
	@Transactional(readOnly = true)
	public Department getDepartmentById(Long departmentId) {
		return dd.findById(departmentId).orElseThrow(() -> new NoSuchElementException(
				"Department with ID=" + departmentId + " was not found."));
		
	}

	@Transactional(readOnly = true)
	public Housekeeper getHousekeeperById(Long housekeeperId) {
		return hd.findById(housekeeperId).orElseThrow(() -> new NoSuchElementException(
				"Department with ID=" + housekeeperId + " was not found."));
		
	}

	@Transactional(readOnly = false)
	public Object saveEntity(Object data, Entity entity) {
		switch(entity) {
		case HOUSEKEEPER:
			HousekeeperData keeper = (HousekeeperData) data;
			Long keeperId = keeper.getHousekeeperId();
			 
			Housekeeper houseKeeper = findOrCreateHousekeeper(keeperId);
			setFeildsInHousekeeper(houseKeeper, keeper);
			return new HousekeeperData (hd.save(houseKeeper));
		case ROOM:
			RoomData room = (RoomData) data;
			Long roomId = room.getRoomId();
			
			Room rm = findOrCreateRoom(roomId);
			setFeildsInRoom(rm, room);
			return new RoomData (rd.save(rm));
		default:
			throw new IllegalArgumentException("entity= " + entity.toString() + "Isn't a valid entity. Enter either ROOM or HOUSEKEEPER.");
		}
		
	}

	private void setFeildsInRoom(Room rm, RoomData room) {
		rm.setRoomId(room.getRoomId());
		rm.setRoomName(room.getRoomName());
		rm.setRoomCleanedToday(room.isRoomCleanedToday());
		rm.setRoomCleanedBy(findHousekeeperById(room.getRoomCleanedBy().getHousekeeperId()));
		rm.setRoomDepartment(findDepartmentById(room.getRoomDepartment().getDepartmentId()));
	}

	private Room findOrCreateRoom(Long roomId) {
		Room room;
		if(Objects.isNull(roomId)) {
			room = new Room();
		} else {
			room = findRoomById(roomId);
		}
		return room;
	}

	private Room findRoomById(Long roomId) {
		return rd.findById(roomId).orElseThrow(() -> new NoSuchElementException(
				"Room with ID=" + roomId + " was not found."));
	}

	private void setFeildsInHousekeeper(Housekeeper hk, HousekeeperData hkd) {
		hk.setHousekeeperId(hkd.getHousekeeperId());
		hk.setHousekeeperFirstName(hkd.getHousekeeperFirstName());
		hk.setHousekeeperLastName(hkd.getHousekeeperLastName());
		hk.setHousekeeperPager(hkd.getHousekeeperPager());
		
	}

	private Housekeeper findOrCreateHousekeeper(Long keeperId) {
		Housekeeper keeper;
		if(Objects.isNull(keeperId)) {
			keeper = new Housekeeper();
		} else {
			keeper = findHousekeeperById(keeperId);
		}
		return keeper;
	}

	private Housekeeper findHousekeeperById(Long keeperId) {
		return hd.findById(keeperId).orElseThrow(() -> 
		new NoSuchElementException("Housekeeper with Id=" + keeperId + " was not found."));
	}

	
	
	
	
	
	
	
	
	@Transactional(readOnly = false)
	public HousekeeperData linkKeeperToDepartment(Long departId, Long keeperId) {
		Department depart = findDepartmentById(departId);
		Housekeeper keeper = findHousekeeperById(keeperId);
		keeper.getAssignedDepartments().add(depart);
		return new HousekeeperData (hd.save(keeper));
	}

//	private Department findOrCreateDepartment(Long departId) {
//		Department depart;
//		if(Objects.isNull(departId)) {
//			depart = new Department();
//		} else {
//			depart = findDepartmentById(departId);
//		}
//		return depart;
//	}

	@Transactional(readOnly = true)
	public Object findById(Long Id, Entity entity) {
		switch (entity) {
		case DEPARTMENT:
			Department dd = findDepartmentById(Id);
			DepartmentData depart = new DepartmentData(dd);
			return depart;
		case HOUSEKEEPER:
			Housekeeper hk = findHousekeeperById(Id);
			HousekeeperData hkd = new HousekeeperData(hk);
			return hkd;
		default:
			throw new IllegalArgumentException("entity= " + entity.toString() + "Isn't a valid entity. Enter either HOUSEKEEPER or DEPARTMENT.");
			
		}
		
	}


	@Transactional(readOnly = false)
	public void deleteHousekeeperById(Long keeperId) {
		if (hd.existsById(keeperId)) {
			hd.deleteById(keeperId);
			} else {
				throw new NoSuchElementException("There is no Housekeeper with ID=" + keeperId);
			}
		
	}
	
	
	private Department findDepartmentById(Long departmentId) {
		return dd.findById(departmentId).orElseThrow(() -> new NoSuchElementException(
				"department with ID=" + departmentId + " was not found."));
	}

}
