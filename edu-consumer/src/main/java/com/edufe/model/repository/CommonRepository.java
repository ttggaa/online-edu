package com.edufe.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonRepository {
	
	@Autowired
	protected NamedParameterJdbcTemplate njdbc;
  
	@Autowired
	protected JdbcTemplate jdbc;
	
	
	

}