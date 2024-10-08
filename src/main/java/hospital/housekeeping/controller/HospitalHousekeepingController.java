package hospital.housekeeping.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hospital.housekeeping.controller.model.DepartmentData;
import hospital.housekeeping.controller.model.HousekeeperData;
import hospital.housekeeping.controller.model.RoomData;
import hospital.housekeeping.service.HospitalService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/hospital")
@Slf4j
public class HospitalHousekeepingController {
	public enum Entity{
		HOUSEKEEPER,
		ROOM,
		DEPARTMENT
	}
	
	@Autowired
	private HospitalService hs = new HospitalService();
	
	
// -----------Housekeeper---------------
	@PostMapping("/keeper")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HousekeeperData saveHousekeeper(
			@RequestBody HousekeeperData hkd) {
		log.info("creating a new housekeeper");
		return (HousekeeperData) hs.saveEntity(hkd, Entity.HOUSEKEEPER);
	}
	
	
	@PutMapping("/keeper/{keeperId}")
	public HousekeeperData updateHousekeeper(
			@PathVariable Long keeperId,
			@RequestBody HousekeeperData hkd) {
		log.info("updating housekeeper with ID={}", keeperId);
		return (HousekeeperData) hs.saveEntity(hkd, Entity.HOUSEKEEPER);
	}
	
	
	@PutMapping("/department/{departId}/keeper/{keeperId}")
	public HousekeeperData assignHousekeeperToDepartment(
			@PathVariable Long departId,
			@PathVariable Long keeperId) {
		log.info("updating join table where housekeeper with ID={} works in department with ID={}", keeperId, departId);
		return hs.linkKeeperToDepartment(departId, keeperId);
	}
	
	@GetMapping("/keeper/{keeperId}")
	public HousekeeperData viewHousekeeper(
			@PathVariable Long keeperId) {
		log.info("retriveing the housekeeper with ID={}", keeperId);
		return (HousekeeperData) hs.findById(keeperId, Entity.HOUSEKEEPER);
	}
	
	@GetMapping("/keeper")
	public Set<HousekeeperData> viewAllHousekeeper(){
		log.info("retrieveing all housekeepers");
		Set<HousekeeperData> allHousekeepers = new HashSet<>();
		allHousekeepers = (Set<HousekeeperData>) hs.findAll(Entity.HOUSEKEEPER);
		return allHousekeepers;
	}
	
	@DeleteMapping("/keeper")
	public void deleteAllHousekeepers(){
		log.info("Attempting to delete all housekeepers");
		throw new UnsupportedOperationException("Sorry, we can't delete everything.");
	}
	
	@DeleteMapping("/keeper/{keeperId}")
	public Map<String, String> deleteHousekeeperById(
			@PathVariable Long keeperId){
		log.info("deleting housekeeper with ID={}", keeperId);
		hs.deleteHousekeeperById(keeperId);
		return Map.of("Message", "Deletion of Housekeeper with ID=" + keeperId + " was successful.");
	}
	
	
	//--------------Rooms--------------
	@PutMapping("/room/{roomId}/keeper/{keeperId}")
	public RoomData updateRoomById(
			@PathVariable Long roomId,
			@PathVariable Long keeperId) {
		log.info("updating room with ID={} with cleaned by housekeeper with ID={}", roomId, keeperId);
		return hs.updateRoom(roomId, keeperId);
	}
	
	@PostMapping("/room/{departmentId}")
	public Set<RoomData> bulkAddRooms(
			@PathVariable Long departmentId) {
		log.info("creating batch of rooms for department with ID={}", departmentId);
		return hs.bulkRoomAdd(departmentId);
	}
	
	//-----------Departments-----------------
	@GetMapping("/department/{departId}")
	public DepartmentData retrieveDepartmentById(
			@PathVariable Long departId) {
		log.info("retrieving department with ID={}", departId);
		return (DepartmentData) hs.findById(departId, Entity.DEPARTMENT);
	}
	
	@GetMapping("/department")
	public Set<DepartmentData> viewAllDepartments(){
		log.info("retrieving all departments");
		Set<DepartmentData> allDepartments = new HashSet<>();
		allDepartments = (Set<DepartmentData>) hs.findAll(Entity.DEPARTMENT);
		return allDepartments;
	}
	
}
