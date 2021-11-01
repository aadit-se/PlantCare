package in.natureom.plantcare.usermgmt.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.natureom.plantcare.usermgmt.dao.State;
import in.natureom.plantcare.usermgmt.service.StateService;
import in.natureom.plantcare.util.CommonConstants;
import in.natureom.plantcare.util.NatureOmUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("State")
public class StateController {	
	
    @Autowired
	StateService Stateservice;
	
	@PostMapping(value = "/")
	public ResponseEntity<String> addState(@RequestBody State state) {
		log.info("in addState");
		String response=Stateservice.addState(state);
		return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED); 
	}
	
	@GetMapping(value="/{stateId}")
	public ResponseEntity<State> viewState(@PathVariable(name="stateId") int stateId) {
		log.info("in viewState");
		State response= Stateservice.viewState(stateId);
		return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED); 
	}
	@GetMapping("/states")  
	private ResponseEntity<ArrayList<State>> getAllStates()   
	{  
		log.info("inside getAllStates");
		ArrayList<State> response= Stateservice.viewAllStates();
		return new ResponseEntity<>(response, NatureOmUtil.isObjectValid(response) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED); 
	}  
	
	@DeleteMapping(value="/{stateid}/")
	public ResponseEntity<String> deleteState(@PathVariable("stateid") int stateId) {
		log.info("in deleteState");
		String response=Stateservice.deleteState(stateId);
		return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED); 
	}

	@PatchMapping("/")
	public ResponseEntity<String> updateState(@RequestBody State state)
	{
		log.info("in updateState");
		String response=Stateservice.updateState(state);
		return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED); 
	}
}

