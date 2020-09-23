package com.tracking.service.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "dependents")
public class Dependent {
	
	@Id
	@Field
	private String id;
	
	@Field
	private String name;
	
	@Field
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty
	private LocalDateTime dob;
	
	public Dependent() {
		
	}
	
	public Dependent(String name, LocalDateTime dob) {
		this.name = name;
		this.dob = dob;
	}
}


