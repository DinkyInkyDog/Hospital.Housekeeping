package hospital.housekeeping.controller;

import org.springframework.data.repository.CrudRepository;

import hospital.housekeeping.entity.Room;

public abstract class RoomsRepository implements CrudRepository<Room, Long> {

}
