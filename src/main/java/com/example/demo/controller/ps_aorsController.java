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
	        	  ps_aorsModel ModelPs_aors=new ps_aorsModel();	
	        	  ModelPs_aors.id=Cursor1.getString(1);
	        		ModelPs_aors.contact=Cursor1.getString(2);
	        		ModelPs_aors.default_expiration=Cursor1.getInt(3);
	        		ModelPs_aors.mailboxes=Cursor1.getString(4);
	        		ModelPs_aors.max_contacts=Cursor1.getInt(5);
	        		ModelPs_aors.minimum_expiration=Cursor1.getInt(6);
	        		ModelPs_aors.remove_existing=Cursor1.getBoolean(7);					// YesNo value/Type.
	        		ModelPs_aors.qualify_frequency=Cursor1.getInt(8);
	        		ModelPs_aors.authenticate_qualify=Cursor1.getBoolean(9);			// YesNo value/Type.
	        		ModelPs_aors.maximum_expiration=Cursor1.getInt(10);
	        		ModelPs_aors.outbound_proxy=Cursor1.getString(11);
	        		ModelPs_aors.support_path=Cursor1.getBoolean(12);
	        		ModelPs_aors.qualify_timeout=Cursor1.getDouble(13);
	        		ModelPs_aors.voicemail_extension=Cursor1.getString(14);
	         ListUser1.add(ModelPs_aors);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_aorsModel ModelPs_aors=new ps_aorsModel();	
		         ListUser1.add(ModelPs_aors);
	          return ListUser1;
	}
}
