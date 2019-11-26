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
@RequestMapping("/queuelog")
public class queue_logController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_queuelog=null;
	@GetMapping("/Getqueuelog")
	public ArrayList<queue_logModel> TampilAlembicVersionConfig() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		 queryselect_queuelog=Connection1.prepareStatement(query_string.query_select_queue_log);
	        ResultSet Cursor1 = queryselect_queuelog.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<queue_logModel> ListUser1 = new ArrayList<queue_logModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  queue_logModel ModelAlembic=new queue_logModel();	
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          queue_logModel ModelAlembic=new queue_logModel();	
		         ListUser1.add(ModelAlembic);
	          return ListUser1;
	}
}
