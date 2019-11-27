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
@RequestMapping("/psendpointsid")
public class ps_endpoints_id_ipsController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_psendpointsid=null;
	@GetMapping("/Getpsendpointsid")
	public ArrayList<ps_endpoints_id_ipsModel> TampilAlembicVersionConfig() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		  queryselect_psendpointsid=Connection1.prepareStatement(query_string.query_select_ps_endpoints_id);
	        ResultSet Cursor1 = queryselect_psendpointsid.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_endpoints_id_ipsModel> ListUser1 = new ArrayList<ps_endpoints_id_ipsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_endpoints_id_ipsModel ModelPs_enpoints_id=new ps_endpoints_id_ipsModel();	
	        	  ModelPs_enpoints_id.id=Cursor1.getString(1);
	        	  ModelPs_enpoints_id.endpoint=Cursor1.getString(2);
	        	  ModelPs_enpoints_id.match=Cursor1.getString(3);
	        	  ModelPs_enpoints_id.srv_lookups=Cursor1.getBoolean(4);
	        	  ModelPs_enpoints_id.match_header=Cursor1.getString(5);
	        	  
	         ListUser1.add(ModelPs_enpoints_id);  
	       
	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostPsEndpointsId")
	public int DeletePostPsEndpoints(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_endpoints);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetPsEndpointsId")
	public int DeleteGetPsEndpoints(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_endpoints);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
