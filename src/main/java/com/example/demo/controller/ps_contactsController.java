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
@RequestMapping("/ps_contact")
public class ps_contactsController 
{PreparedStatement querydelete_alembic_version_config=null;
AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_contact=null;
	@GetMapping("/GETpscontact")
	public ArrayList<ps_contactsModel> TampilAlembicVersionConfig() throws SQLException
	{
		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		   queryselect_ps_contact=Connection1.prepareStatement(query_string.query_select_ps_contacts);
	        ResultSet Cursor1 = queryselect_ps_contact.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_contactsModel> ListUser1 = new ArrayList<ps_contactsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_contactsModel ModelContact=new ps_contactsModel();	
	        	  ModelContact.id=Cursor1.getString(1);
	        	  ModelContact.uri=Cursor1.getString(2);
	        	  ModelContact.expiration_time=Cursor1.getInt(3);
	        	  ModelContact.qualify_frequency=Cursor1.getInt(4);
	        	  ModelContact.outbond_proxy=Cursor1.getString(5);
	        	  ModelContact.path=Cursor1.getString(6);
	        	  ModelContact.user_agent=Cursor1.getString(7);
	        	  ModelContact.qualify_timeout=Cursor1.getInt(8);
	        	  ModelContact.reg_server=Cursor1.getString(9);
	        	  ModelContact.authenticate_qualify=Cursor1.getBoolean(10);
	        	  ModelContact.via_addr=Cursor1.getString(11);
	        	  ModelContact.via_port=Cursor1.getInt(12);
	        	  ModelContact.call_id=Cursor1.getString(13);
	        	  ModelContact.endpoint=Cursor1.getString(14);
	        	  ModelContact.prune_on_boot=Cursor1.getBoolean(15);
	        	  
	         ListUser1.add(ModelContact);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_contactsModel ModelContact=new ps_contactsModel();	
		         ListUser1.add(ModelContact);  
	          return ListUser1;

}
	
	@PostMapping("/DeletePostPsContacts")
	public int DeletePostAlembicVersionConfig(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_contacts);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetPsContacts")
	public int DeleteGetAlembicVersionConfig(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_contacts);
		 querydelete_alembic_version_config.setString(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
