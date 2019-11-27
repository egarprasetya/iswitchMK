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
@RequestMapping("/ps_subscription_persistence")
public class ps_subscription_persistenceController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_subscription_persistence=null;
	@GetMapping("/Getps_subscription_persistence")
	public ArrayList<ps_subscription_persistenceModel> Tampilps_subscription_persistence() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_subscription_persistence=Connection1.prepareStatement(query_string.query_select_ps_subscription_persistence);
        ResultSet Cursor1 = queryselect_ps_subscription_persistence.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<ps_subscription_persistenceModel> ListUser1 = new ArrayList<ps_subscription_persistenceModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  ps_subscription_persistenceModel ModelPs_subscription_persistance=new ps_subscription_persistenceModel();	
        	  ModelPs_subscription_persistance.id=Cursor1.getString(1);
        	  ModelPs_subscription_persistance.packet=Cursor1.getString(2);
        	  ModelPs_subscription_persistance.src_name=Cursor1.getString(3);
        	  ModelPs_subscription_persistance.src_port=Cursor1.getInt(4);
        	  ModelPs_subscription_persistance.transport_key=Cursor1.getString(5);
        	  ModelPs_subscription_persistance.local_name=Cursor1.getString(6);
        	  ModelPs_subscription_persistance.local_port=Cursor1.getInt(7);
        	  ModelPs_subscription_persistance.cseq=Cursor1.getInt(8);
        	  ModelPs_subscription_persistance.tag=Cursor1.getString(9);
        	  ModelPs_subscription_persistance.endpoint=Cursor1.getString(10);
        	  ModelPs_subscription_persistance.expires=Cursor1.getInt(11);
        	  ModelPs_subscription_persistance.contact_uri=Cursor1.getString(12);
        	  ModelPs_subscription_persistance.prune_on_boot=Cursor1.getBoolean(13);
         
          }          
          Connection1.close();
          return ListUser1;
	}
	
	@PostMapping("/DeletePostPsSubscript")
	public int DeletePostPsSubscript(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_subscription_persistence);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetPsSubscript")
	public int DeleteGetPsSubscript(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_subscription_persistence);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
