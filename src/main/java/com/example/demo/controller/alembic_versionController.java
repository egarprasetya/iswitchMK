package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.*;

@RestController
@RequestMapping("/AlembicVersion")
public class alembic_versionController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	query_select_parameter query_String_param = new query_select_parameter();
	PreparedStatement queryselect_alembic_version=null;
	@GetMapping("/GetAlembicVersion")
	public ArrayList<alembic_versionModel> TampilAlembicVersion() throws SQLException
	{		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_alembic_version=Connection1.prepareStatement(query_string.query_select_alembic_version);
	        ResultSet Cursor1 = queryselect_alembic_version.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<alembic_versionModel> ListUser1 = new ArrayList<alembic_versionModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        alembic_versionModel ModelAlembic=new alembic_versionModel();	
	         ModelAlembic.version_num=Cursor1.getString(1);
	         ListUser1.add(ModelAlembic);  
	         
	          }	          
	          Connection1.close();
	          return ListUser1;

}
	
	@PostMapping("/GetAlembicVersionParameter")
	public ArrayList<alembic_versionModel> TampilAlembicVersionParameterSelect(@RequestBody String version_num) throws SQLException
	{		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_alembic_version=Connection1.prepareStatement(query_String_param.query_select_alembic_version_param_version_num);
	       
	        queryselect_alembic_version.setString(1, version_num);
	        ResultSet Cursor1 = queryselect_alembic_version.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<alembic_versionModel> ListUser1 = new ArrayList<alembic_versionModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        alembic_versionModel ModelAlembic=new alembic_versionModel();	
	         ModelAlembic.version_num=Cursor1.getString(1);
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
		        alembic_versionModel ModelAlembic=new alembic_versionModel();	
		         ListUser1.add(ModelAlembic);  
	          return ListUser1;

}
	
	
	@PostMapping("/DeletePostAlembicVersionParameter")
	public int DeletePostAlembicVersion(@RequestBody String version_num) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		 querydelete_alembic_version_config.setString(1, version_num);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetAlembicVersionParameter")
	public int DeleteGetAlembicVersion(@RequestBody String version_num) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		 querydelete_alembic_version_config.setString(1, version_num);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0;  
		Connection1.close();
		return a;    	         
}
	
	
}
