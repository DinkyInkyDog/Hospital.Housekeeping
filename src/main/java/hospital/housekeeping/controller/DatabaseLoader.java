package hospital.housekeeping.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private RoomsRepository roomsRepo;
	
	 public DatabaseLoader(RoomsRepository roomsRepo) {
	        this.roomsRepo = roomsRepo;
	    }
	
	@Override
	public void run(String... String) throws Exception {
		
		for()

	}

}
