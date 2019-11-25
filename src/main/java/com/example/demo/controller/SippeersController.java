package com.example.demo.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.query.*;
import com.example.demo.model.UserModel;
import com.example.demo.model.alembic_versionModel;
import com.example.demo.connection.*;
@RestController
@RequestMapping("/Sippeers")
public class SippeersController {
	
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_alembic_version=null;
//	@GetMapping("/GetSippeers")
//	public ArrayList<alembic_versionModel> TampilSippeers() throws SQLException
//	{
//		
//	}
}
