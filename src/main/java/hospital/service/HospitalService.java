package hospital.service;

import java.util.NoSuchElementException;

import hospital.dao.DepartmentDao;
import hospital.dao.HousekeeperDao;
import hospital.dao.RoomDao;
import hospital.housekeeping.entity.Department;
import hospital.housekeeping.entity.Housekeeper;

public class HospitalService {
	DepartmentDao dd;
	HousekeeperDao hd;
	RoomDao rd;

	public Department getDepartmentById(Long departmentId) {
		return dd.findById(departmentId).orElseThrow(() -> new NoSuchElementException(
				"Department with ID=" + departmentId + " was not found."));
		
	}

	public Housekeeper getHousekeeperById(Long housekeeperId) {
		return hd.findById(housekeeperId).orElseThrow(() -> new NoSuchElementException(
				"Department with ID=" + housekeeperId + " was not found."));
		
	}

}
