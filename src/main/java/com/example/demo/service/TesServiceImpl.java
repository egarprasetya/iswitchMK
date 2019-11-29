package com.example.demo.service;
import java.util.List;

import javax.sql.DataSource;
import com.example.demo.connection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.model.*;

@Service
public class TesServiceImpl implements TesService
{	

stringkoneksi s = new stringkoneksi();
	@Autowired
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TesUserModel> getDurationHold() {
		String sql = "Select*from users";
		return jdbcTemplate.query(sql, new TesUserRowMapper());
	}
}
	
