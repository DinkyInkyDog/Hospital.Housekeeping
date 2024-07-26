package hospital.housekeeping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.housekeeping.service.HospitalService;


@RestController
@RequestMapping("/hospital")
public class HospitalHousekeepingController {

	
	@Autowired
	private HospitalService hs = new HospitalService();
	
	
}
