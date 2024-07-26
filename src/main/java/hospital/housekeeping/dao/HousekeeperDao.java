package hospital.housekeeping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.housekeeping.entity.Housekeeper;

public interface HousekeeperDao extends JpaRepository<Housekeeper, Long> {

}
