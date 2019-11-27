package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.*;
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
@RestController
@RequestMapping("/ps_system")
public class ps_systemController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_systems=null;
	@GetMapping("/Getps_systems")
	public ArrayList<ps_SystemsModel> TampilAlembicVersionConfig() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		 queryselect_ps_systems=Connection1.prepareStatement(query_string.query_select_ps_systems);
	        ResultSet Cursor1 = queryselect_ps_systems.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_SystemsModel> ListUser1 = new ArrayList<ps_SystemsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_SystemsModel ModelAlembic=new ps_SystemsModel();	
	         ListUser1.add(ModelAlembic);  
	         
	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostPsSystem")
	public int DeletePostPsSystems(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_systems);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	@GetMapping("/DeleteGetPsSystem")
	public int DeleteGetPsSystems(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_systems);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
