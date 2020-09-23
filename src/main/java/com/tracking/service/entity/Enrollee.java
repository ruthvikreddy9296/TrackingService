package com.tracking.service.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "enrollees")
public class Enrollee {
	
	@Id
	@Field
	private String id;
	
	@Field
	private String name;
	
	@Field
	private boolean activationStatus;
	
	@Field
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty
	private LocalDateTime dob;
	
	@Field
	private String phoneNumber;
	
	@DBRef
	@Field
	private Set<Dependent> dependents;
	
	public Enrollee() {
		this.activationStatus = false;
		this.dependents = new HashSet<Dependent>();
	}
	
	public Enrollee(String name, boolean activationStatus, LocalDateTime dob,
			String phoneNumber) {
		this.name = name;
		this.activationStatus = activationStatus;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.dependents = new HashSet<Dependent>();
	}
	
	public void addDependent(Dependent dependent) {
		this.dependents.add(dependent);
	}
	
}


