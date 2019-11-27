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
@RequestMapping("/ps_registration")
public class ps_registrationController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_registration=null;
	@GetMapping("/Getpsregistration")
	public ArrayList<ps_registrationsModel> TampilAlembicVersionConfig() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_ps_registration=Connection1.prepareStatement(query_string.query_select_ps_registrations);
	        ResultSet Cursor1 = queryselect_ps_registration.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_registrationsModel> ListUser1 = new ArrayList<ps_registrationsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_registrationsModel ModelPs_registration=new ps_registrationsModel();	
	        	  
	        	  ModelPs_registration.id=Cursor1.getString(1);
	        	  ModelPs_registration.auth_rejection_permanent=Cursor1.getBoolean(2);
	        	  ModelPs_registration.client_uri=Cursor1.getString(3);
	        	  ModelPs_registration.contact_user=Cursor1.getString(4);
	        	  ModelPs_registration.expiration=Cursor1.getInt(5);
	        	  ModelPs_registration.max_retries=Cursor1.getInt(6);
	        	  ModelPs_registration.outbound_auth=Cursor1.getString(7);
	        	  ModelPs_registration.outbound_proxy=Cursor1.getString(8);
	        	  ModelPs_registration.retry_interval=Cursor1.getInt(9);
	        	  ModelPs_registration.forbidden_retry_interval=Cursor1.getInt(10);
	        	  ModelPs_registration.server_uri=Cursor1.getString(11);
	        	  ModelPs_registration.transport=Cursor1.getString(12);
	        	  ModelPs_registration.support_path=Cursor1.getBoolean(13);
	        	  ModelPs_registration.fatal_retry_interval=Cursor1.getInt(14);
	        	  ModelPs_registration.line=Cursor1.getBoolean(15);
	        	  ModelPs_registration.endpoint=Cursor1.getString(16);
	        	  
	         ListUser1.add(ModelPs_registration);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_registrationsModel ModelPs_registration=new ps_registrationsModel();	
		         ListUser1.add(ModelPs_registration);  
	          return ListUser1;
	}
}
