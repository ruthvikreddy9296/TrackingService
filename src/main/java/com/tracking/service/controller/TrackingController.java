package com.tracking.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.service.entity.Dependent;
import com.tracking.service.entity.Enrollee;
import com.tracking.service.entity.repository.DependentRepository;
import com.tracking.service.entity.repository.EnrolleeRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/tracking/enrollees")
public class TrackingController {
	
	@Autowired
	private EnrolleeRepository enrolleeRepository;
	
	@Autowired
	private DependentRepository dependentRepository;
	
    public TrackingController() {
    	
    }
    
    @GetMapping(value = "")
    public ResponseEntity<?> getAllEnrollees() {
    	return ResponseEntity.ok(enrolleeRepository.findAll());
    }
    
    @PostMapping(value = "")
    public ResponseEntity<?> createEnrollee(
    		@RequestBody Enrollee enrolleeData) {
    	
    	try {
    		enrolleeData = enrolleeRepository.save(enrolleeData);
    		return ResponseEntity.ok().body(enrolleeData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    
    @GetMapping(value = "/{enrolleeId}")
    public ResponseEntity<?> getEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId) {
    	
    	try {
    		Optional<Enrollee> originalEnrollee = enrolleeRepository.findById(enrolleeId);
    		if(originalEnrollee.isPresent()) {
    			Enrollee enrollee = originalEnrollee.get();
    			return ResponseEntity.ok(enrollee);
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee found with id: "+enrolleeId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    
    @PutMapping(value = "/{enrolleeId}")
    public ResponseEntity<?> updateEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@RequestBody Enrollee enrolleeData) {
    	
    	try {
    		Optional<Enrollee> originalEnrollee = enrolleeRepository.findById(enrolleeId);
    		if(originalEnrollee.isPresent()) {
    			Enrollee enrollee = originalEnrollee.get();
    			enrolleeData.setId(enrollee.getId());
    			enrolleeData = enrolleeRepository.save(enrolleeData);
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee found with id: "+enrolleeId);
    		}
    		return ResponseEntity.ok().body(enrolleeData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    

    @DeleteMapping(value = "/{enrolleeId}")
    public ResponseEntity<?> deleteEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@RequestBody Enrollee enrolleeData) {
    	
    	try {
    		Optional<Enrollee> originalEnrollee = enrolleeRepository.findById(enrolleeId);
    		if(originalEnrollee.isPresent()) {
    			Enrollee enrollee = originalEnrollee.get();
    			enrolleeRepository.delete(enrollee);
    			return ResponseEntity.noContent().build();
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee found with id: "+enrolleeId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    
    @GetMapping(value = "/{enrolleeId}/dependents")
    public ResponseEntity<?> getAllDependentsByEnrolleeId(
    		@PathVariable("enrolleeId") String enrolleeId) {
    	try {
    		Optional<Enrollee> originalEnrollee = enrolleeRepository.findById(enrolleeId);
    		if(originalEnrollee.isPresent()) {
    			Enrollee enrollee = originalEnrollee.get();
    			
    			return ResponseEntity.ok(enrollee.getDependents());
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee found with id: "+enrolleeId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @PostMapping(value = "/{enrolleeId}/dependents")
    public ResponseEntity<?> createDependentForEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@RequestBody Dependent dependentData) {
    	
    	try {
    		Optional<Enrollee> originalEnrollee = enrolleeRepository.findById(enrolleeId);
    		if(originalEnrollee.isPresent()) {
    			Enrollee enrollee = originalEnrollee.get();
    			dependentData = dependentRepository.save(dependentData);
    			enrollee.addDependent(dependentData);
    			enrollee = enrolleeRepository.save(enrollee);
    			return ResponseEntity.ok(enrollee);
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee found with id: "+enrolleeId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    
    @GetMapping(value = "/{enrolleeId}/dependents/{dependentId}")
    public ResponseEntity<?> getDependentForEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@PathVariable("dependentId") String dependentId) {
    	
    	try {
    		Optional<Dependent> originalDependent = dependentRepository.findById(dependentId);
    		if(originalDependent.isPresent()) {
    			return ResponseEntity.ok(originalDependent.get());
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee or dependent found with id: "+dependentId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    
    @PutMapping(value = "/{enrolleeId}/dependents/{dependentId}")
    public ResponseEntity<?> updateDependentForEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@PathVariable("dependentId") String dependentId,
    		@RequestBody Dependent dependentData) {
    	
    	try {
    		Optional<Dependent> originalDependent = dependentRepository.findById(dependentId);
    		if(originalDependent.isPresent()) {
    			dependentData.setId(dependentId);
    			
    			dependentData = dependentRepository.save(dependentData);
    			
    			return ResponseEntity.ok(dependentData);
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee or dependent found with id: "+dependentId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    

    @DeleteMapping(value = "/{enrolleeId}/dependents/{dependentId}")
    public ResponseEntity<?> deleteDependentForEnrollee(
    		@PathVariable("enrolleeId") String enrolleeId,
    		@PathVariable("dependentId") String dependentId,
    		@RequestBody Enrollee enrolleeData) {
    	
    	try {
    		Optional<Dependent> originalDependent = dependentRepository.findById(dependentId);
    		if(originalDependent.isPresent()) {
    			Dependent dependent = originalDependent.get();
    			dependentRepository.delete(dependent);
    			return ResponseEntity.noContent().build();
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("No enrollee or dependent found with id: "+dependentId);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

}
