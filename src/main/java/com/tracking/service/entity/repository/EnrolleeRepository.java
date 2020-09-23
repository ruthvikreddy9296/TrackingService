package com.tracking.service.entity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tracking.service.entity.Enrollee;

@Repository
public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {

}