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

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.AllQuery;
@RestController
@RequestMapping("/psAuthVersion")
public class ps_authsController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	
	PreparedStatement queryselect_ps_auths=null;
	
	@GetMapping("/psAuths")
	public ArrayList<ps_authsModel> TampilAlembicVersionConfig() throws SQLException
	{
		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_ps_auths=Connection1.prepareStatement(query_string.query_select_ps_auths);
	        ResultSet Cursor1 = queryselect_ps_auths.executeQuery();// Evaluate (Connected_Expression1)

	      	  ArrayList<ps_authsModel> ListUser1 = new ArrayList<ps_authsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_authsModel Modelpsauths=new ps_authsModel();	
	        	 Modelpsauths.id=Cursor1.getString(1);
	        	 Modelpsauths.auth_type=Cursor1.getString(2);
	        	 Modelpsauths.nonce_lifetime=Cursor1.getInt(3);
	        	 Modelpsauths.md5_cred=Cursor1.getString(4);
	        	 Modelpsauths.password=Cursor1.getString(5);
	        	 Modelpsauths.realm=Cursor1.getString(6);
	        	 Modelpsauths.username=Cursor1.getString(7);
	        	  
	        	  
	         ListUser1.add(Modelpsauths);  
	          return ListUser1;

	          }
	          
	          Connection1.close();
	          
	          return ListUser1;

}
	
}
