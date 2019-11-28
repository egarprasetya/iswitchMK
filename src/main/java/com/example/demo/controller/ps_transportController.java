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
@RequestMapping("/ps_transport")
public class ps_transportController 
{PreparedStatement querydelete_alembic_version_config=null;
AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_transport=null;
	@GetMapping("/Getpstransport")
	public ArrayList<ps_transportModel> TampilAlembicVersionConfig() throws SQLException
	{		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_ps_transport=Connection1.prepareStatement(query_string.query_select_ps_transports);
	        ResultSet Cursor1 = queryselect_ps_transport.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_transportModel> ListUser1 = new ArrayList<ps_transportModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_transportModel ModelPs_transport=new ps_transportModel();
	        	  
	        	  ModelPs_transport.id=Cursor1.getString(1);
	        	  ModelPs_transport.async_operations=Cursor1.getInt(2);
	        	  ModelPs_transport.bind=Cursor1.getString(3);
	        	  ModelPs_transport.ca_list_file=Cursor1.getString(4);
	        	  ModelPs_transport.cert_file=Cursor1.getString(5);
	        	  ModelPs_transport.cipher=Cursor1.getString(6);
	        	  ModelPs_transport.domain=Cursor1.getString(7);
	        	  ModelPs_transport.external_media_address=Cursor1.getString(8);
	        	  ModelPs_transport.external_signaling_address=Cursor1.getString(9);
	        	  ModelPs_transport.external_signaling_port=Cursor1.getInt(10);
	        	  ModelPs_transport.method=Cursor1.getString(11);
	        	  ModelPs_transport.local_net=Cursor1.getString(12);
	        	  ModelPs_transport.password=Cursor1.getString(13);
	        	  ModelPs_transport.priv_key_file=Cursor1.getString(14);

	        	  ModelPs_transport.protocol=Cursor1.getString(15);
	        	  ModelPs_transport.require_client_cert=Cursor1.getBoolean(16);
	        	  ModelPs_transport.verify_client=Cursor1.getBoolean(17);
	        	  ModelPs_transport.verify_server=Cursor1.getBoolean(18);
	        	  ModelPs_transport.tos=Cursor1.getString(19);
	        	  ModelPs_transport.cos=Cursor1.getInt(20);
	        	  ModelPs_transport.allow_reload=Cursor1.getBoolean(21);
	        	  ModelPs_transport.symmetric_transport=Cursor1.getBoolean(22);
	        	  
	         ListUser1.add(ModelPs_transport);  
	        
	          }	          
	          Connection1.close();
	          return ListUser1;

}
	
	@DeleteMapping(path="/DeletePostPsTransport",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostPsTransport(@RequestBody ps_transportModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_transports);
		 querydelete_alembic_version_config.setString(1, cfm.id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	
}
