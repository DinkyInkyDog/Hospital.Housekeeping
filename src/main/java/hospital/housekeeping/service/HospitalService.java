package hospital.housekeeping.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.housekeeping.controller.HospitalHousekeepingController.Entity;
import hospital.housekeeping.controller.model.HousekeeperData;
import hospital.housekeeping.dao.DepartmentDao;
import hospital.housekeeping.dao.HousekeeperDao;
import hospital.housekeeping.dao.RoomDao;
import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;

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
			return;
		case ROOM:
			return;
		default:
			throw new 
		}
		
	}

	@Transactional(readOnly = false)
	public HousekeeperData linkKeeperToDepartment(Long departId, Long keeperId) {
		// TODO Auto-generated method stub
		return ;
	}

	@Transactional(readOnly = true)
	public Object findById(Long keeperId, Entity housekeeper) {
		// TODO Auto-generated method stub
		return ;
	}

	@Transactional(readOnly = false)
	public void deleteHousekeeperById(Long keeperId) {
		
		
	}
	
	
	

}
