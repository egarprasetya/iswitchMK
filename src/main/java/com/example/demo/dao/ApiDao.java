package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DAOApi;

@Repository
public interface ApiDao extends CrudRepository<DAOApi, Integer> {
	
	DAOApi findByApiId(String apiId);
	
}