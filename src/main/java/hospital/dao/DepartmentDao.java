package hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.housekeeping.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

}
