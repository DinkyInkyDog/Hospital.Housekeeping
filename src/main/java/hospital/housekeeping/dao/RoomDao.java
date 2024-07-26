package hospital.housekeeping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.housekeeping.entity.Room;

public interface RoomDao extends JpaRepository<Room, Long> {

}
