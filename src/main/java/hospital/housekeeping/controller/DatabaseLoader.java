package hospital.housekeeping.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hospital.housekeeping.entity.Room;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private RoomsRepository roomsRepo;
	
	 public DatabaseLoader(RoomsRepository roomsRepo) {
	        this.roomsRepo = roomsRepo;
	    }
	
	@Override
	public void run(String... String) throws Exception {
		
		for(int i = 30; i < 100; i++ ) {
			this.roomsRepo.save(new Room(false, 1L, 3L, i));
		}

	}

}
