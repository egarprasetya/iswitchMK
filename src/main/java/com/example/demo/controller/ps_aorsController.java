package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.AllQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

@RequestMapping("/psaors")
public class ps_aorsController {
	
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_psaors=null;
	@GetMapping("/Getpsaors")
	public ArrayList<ps_aorsModel> Tampilgetpsaors() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_psaors=Connection1.prepareStatement(query_string.query_select_ps_aors);
	        ResultSet Cursor1 = queryselect_psaors.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_aorsModel> ListUser1 = new ArrayList<ps_aorsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_aorsModel ModelAlembic=new ps_aorsModel();	
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_aorsModel ModelAlembic=new ps_aorsModel();	
		         ListUser1.add(ModelAlembic);
	          return ListUser1;
	}
}
