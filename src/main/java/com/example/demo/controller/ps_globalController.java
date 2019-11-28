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
@RequestMapping("/ps_global")
public class ps_globalController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_globals=null;
	@GetMapping("/Getps_global")
	public ArrayList<ps_globalsModel> TampilAlembicVersionConfig() throws SQLException
	{		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_ps_globals=Connection1.prepareStatement(query_string.query_select_alembic_version);
	        ResultSet Cursor1 = queryselect_ps_globals.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_globalsModel> ListUser1 = new ArrayList<ps_globalsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_globalsModel ModelPs_global=new ps_globalsModel();
	        	  ModelPs_global.id=Cursor1.getString(1);
	        		ModelPs_global.max_forwards = Cursor1.getInt(2);
	        		ModelPs_global.default_outbound_endpoint = Cursor1.getString(3);
	        		ModelPs_global.debug = Cursor1.getString(4);
	        		ModelPs_global.endpoint_identifier_order = Cursor1.getString(5);
	        		ModelPs_global.max_initial_qualify_time = Cursor1.getInt(6);
	        		ModelPs_global.default_from_user = Cursor1.getString(7);
	        		ModelPs_global.keep_alive_interval = Cursor1.getInt(8);
	        		ModelPs_global.regcontext = Cursor1.getString(9);
	        		ModelPs_global.contact_expiration_check_interval = Cursor1.getInt(10);
	        		ModelPs_global.default_voicemail_extension = Cursor1.getString(11);
	        		ModelPs_global.disable_multi_domain = Cursor1.getBoolean(12);				// YesNo value/Type.
	        		ModelPs_global.unidentified_request_count = Cursor1.getInt(13);
	        		ModelPs_global.unidentified_request_period = Cursor1.getInt(14);
	        		ModelPs_global.unidentified_request_prune_interval = Cursor1.getInt(15);
	        		ModelPs_global.default_realm = Cursor1.getString(16);
	        		ModelPs_global.mwi_tps_queue_high = Cursor1.getInt(17);
	        		ModelPs_global.mwi_tps_queue_low = Cursor1.getInt(18);
	        		ModelPs_global.mwi_disable_initial_unsolicited = Cursor1.getBoolean(19);		// YesNo value/Type.
	        		ModelPs_global.ignore_uri_user_options = Cursor1.getBoolean(20);
	         ListUser1.add(ModelPs_global);  
	         
	          }	          
	          Connection1.close();
	          return ListUser1;

}
	
	@DeleteMapping(path="/DeletePostPsGlobal",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostPsGlobal(@RequestBody ps_globalsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_globals);
		 querydelete_alembic_version_config.setString(1, cfm.id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =1; 
		Connection1.close();
		return a;    	         
}
	
	
}
