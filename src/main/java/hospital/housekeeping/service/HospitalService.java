package hospital.housekeeping.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.housekeeping.controller.HospitalHousekeepingController.Entity;
import hospital.housekeeping.controller.model.DepartmentData;
import hospital.housekeeping.controller.model.HousekeeperData;
import hospital.housekeeping.controller.model.HousekeeperData.DepartmentResponse;
import hospital.housekeeping.controller.model.HousekeeperData.RoomResponse;
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

		if (findHousekeeperById(room.getRoomCleanedBy().getHousekeeperId()) != null) {
			rm.setRoomCleanedBy(findHousekeeperById(room.getRoomCleanedBy().getHousekeeperId()));
		}
		
		
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
	
	@Transactional(readOnly = true)
	private Department findDepartmentById(Long departmentId) {
		return dd.findById(departmentId).orElseThrow(() -> new NoSuchElementException(
				"department with ID=" + departmentId + " was not found."));
	}

	@Transactional(readOnly = true)
	public Object findAll(Entity entity) {
		switch(entity) {
		case DEPARTMENT:
			Set<DepartmentData> allDepartments = new HashSet<>();
			List<Department> departments = dd.findAll();
			for(Department department : departments) {
				allDepartments.add( new DepartmentData(department));
			}
			
			return allDepartments;
		case HOUSEKEEPER:
			Set<HousekeeperData> allHousekeepers = new HashSet<>();
			List<Housekeeper> housekeepers = hd.findAll();
			for(Housekeeper housekeeper : housekeepers) {
				allHousekeepers.add(new HousekeeperData(housekeeper));
			}
			return allHousekeepers;
		default:
			throw new IllegalArgumentException("entity= " + entity.toString() + "Isn't a valid entity. Enter either HOUSEKEEPER or DEPARTMENT.");
		}
	}

	@Transactional(readOnly = false)
	public Set<RoomData> bulkRoomAdd(Long departmentId) {
		Set<RoomData> createdRooms = new HashSet<>();
		for(int i = 25; i < 75; i++) {
			Room newRoom = new Room();
			newRoom.setRoomName(""+ departmentId + i);
			newRoom.setRoomDepartment(dd.findById(departmentId).orElseThrow(() -> new NoSuchElementException(""
					+ "department with ID = " + departmentId + " was not found.")));
			createdRooms.add(new RoomData(rd.save(newRoom)));
		}
		return createdRooms;
	}

	
	@Transactional(readOnly = false)
	public RoomData updateRoom(Long roomId, Long keeperId) {
		Room room = rd.findById(roomId).orElseThrow(() ->  new NoSuchElementException("Room with ID=" +roomId+ " was not found"));
		Housekeeper housekeeper = hd.findById(keeperId).orElseThrow(()-> new NoSuchElementException("Housekeeper with ID=" +keeperId+ " was not found"));
		
		room.setRoomCleanedToday(true);
		room.setRoomCleanedBy(housekeeper);
		
		housekeeper.getRoomsCleaned().add(room);
		hd.save(housekeeper);
		return new RoomData(rd.save(room));
	}



}
