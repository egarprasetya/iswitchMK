package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.query.*;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.connection.*;
@RestController
@RequestMapping("/Sippeers")
public class SippeersController {
	
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_sippeers=null;
	@GetMapping("/GetSippeers")
	public ArrayList<SippeersModel> TampilSippeers() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_sippeers=Connection1.prepareStatement(query_string.query_select_sippeers);
        ResultSet Cursor1 = queryselect_sippeers.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<SippeersModel> ListUser1 = new ArrayList<SippeersModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  SippeersModel ModelAlembic=new SippeersModel();	         
         ListUser1.add(ModelAlembic);  
          return ListUser1;
          }          
          Connection1.close();
          SippeersModel ModelAlembic=new SippeersModel();	          
          ListUser1.add(ModelAlembic);  
          return ListUser1;
	}
}
