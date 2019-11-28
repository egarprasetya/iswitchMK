package com.example.demo.controller;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
@RestController
@RequestMapping("/psAuthVersion")
public class ps_authsController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
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
	         
	          }	          
	          Connection1.close();
	          return ListUser1;

}
	@DeleteMapping(path="/DeletePostPsAuths",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostAlembicVersionConfig(@RequestBody ps_authsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_auths);
		 querydelete_alembic_version_config.setString(1, cfm.id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =1; 
		Connection1.close();
		return a;    	         
}
	
	
	
}
