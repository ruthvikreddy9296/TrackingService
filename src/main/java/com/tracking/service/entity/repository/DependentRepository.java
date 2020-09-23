package com.tracking.service.entity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tracking.service.entity.Dependent;

@Repository
public interface DependentRepository extends MongoRepository<Dependent, String> {
	
}