package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.AllQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psresourcelist")
public class ps_resourcelistController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_psresourcelist=null;
	@GetMapping("/Getpsresourcelist")
	public ArrayList<ps_Resource_listModel> TampilAlembicVersionConfig() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_psresourcelist=Connection1.prepareStatement(query_string.query_select_ps_resource_list);
	        ResultSet Cursor1 = queryselect_psresourcelist.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_Resource_listModel> ListUser1 = new ArrayList<ps_Resource_listModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_Resource_listModel ModelPs_resource_list=new ps_Resource_listModel();
	        	  
	        	  ModelPs_resource_list.id=Cursor1.getString(1);
	        	  ModelPs_resource_list.list_item=Cursor1.getString(2);
	        	  ModelPs_resource_list.event=Cursor1.getString(3);
	        	  ModelPs_resource_list.full_state=Cursor1.getBoolean(4);
	        	  ModelPs_resource_list.notification_batch_interval=Cursor1.getInt(5);
	         ListUser1.add(ModelPs_resource_list);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_Resource_listModel ModelPs_resource_list=new ps_Resource_listModel();	
		         ListUser1.add(ModelPs_resource_list);  
	          return ListUser1;
	}
}
