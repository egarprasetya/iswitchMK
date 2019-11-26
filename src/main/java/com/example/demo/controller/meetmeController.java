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

@RequestMapping("/meetme")
public class meetmeController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_meetme=null;
	@GetMapping("/Getmeetme")
	public ArrayList<meetmeModel> Tampilmetmee() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_meetme=Connection1.prepareStatement(query_string.query_select_meetme);
	        ResultSet Cursor1 = queryselect_meetme.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<meetmeModel> ListUser1 = new ArrayList<meetmeModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  meetmeModel ModelAlembic=new meetmeModel();	
	        // ModelAlembic.version_num=Cursor1.getString(1);
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
        	  meetmeModel ModelAlembic=new meetmeModel();	
		         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	}
}
