package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/ps_domain_id")
public class ps_domain_aliasesController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_domain_id=null;
	@GetMapping("/Getdomain")
	public ArrayList<ps_domain_aliasesModel> TampilAlembicVersionConfig() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		  queryselect_ps_domain_id=Connection1.prepareStatement(query_string.query_select_domain_aliases);
	        ResultSet Cursor1 = queryselect_ps_domain_id.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_domain_aliasesModel> ListUser1 = new ArrayList<ps_domain_aliasesModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_domain_aliasesModel ModelDomain=new ps_domain_aliasesModel();	
	        	  ModelDomain.id=Cursor1.getString(1);
	        	  ModelDomain.domain=Cursor1.getString(2);
	        	  
	         ListUser1.add(ModelDomain);  
	         

	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@DeleteMapping(path="/DeletePostPsDomain",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostAlembicVersionConfig(@RequestBody ps_domain_aliasesModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_domain_aliases);
		 querydelete_alembic_version_config.setString(1, cfm.id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	

	
}
