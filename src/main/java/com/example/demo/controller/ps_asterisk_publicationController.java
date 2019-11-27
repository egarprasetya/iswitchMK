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
@RequestMapping("/ps_asterisk")
public class ps_asterisk_publicationController
{PreparedStatement querydelete_alembic_version_config=null;
AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_asterisk=null;
	@GetMapping("/Getps_asterisk")
	public ArrayList<ps_asterisk_publicationsModel> Tampilasterisk_publication() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_ps_asterisk=Connection1.prepareStatement(query_string.query_select_ps_asterisk_publications);
	        ResultSet Cursor1 = queryselect_ps_asterisk.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_asterisk_publicationsModel> ListUser1 = new ArrayList<ps_asterisk_publicationsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_asterisk_publicationsModel ModelPs_asterisk=new ps_asterisk_publicationsModel();
	        	  ModelPs_asterisk.id=Cursor1.getString(1);
	        	  ModelPs_asterisk.devicestate_publish=Cursor1.getString(2);
	        	  ModelPs_asterisk.mailboxstate_publish=Cursor1.getString(3);
	        	  ModelPs_asterisk.device_state=Cursor1.getBoolean(4);
	        	  ModelPs_asterisk.device_state_filter=Cursor1.getString(5);
	        	  ModelPs_asterisk.mailbox_state=Cursor1.getBoolean(6);
	        	  ModelPs_asterisk.mailbox_state_filter=Cursor1.getString(7);
	         ListUser1.add(ModelPs_asterisk);  
	         
	          }          
	          Connection1.close();
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostpsAsterisk")
	public int DeletePostAlembicVersionConfig(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_asterisk_publications);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@PostMapping("/DeleteGetpsAsterisk")
	public int DeleteGetAlembicVersionConfig(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_asterisk_publications);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
