package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.AllQuery;
import java.sql.Connection;
import java.sql.Date;
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
	        	  queue_logModel ModelQueue_log=new queue_logModel();
	        	  ModelQueue_log.id=Cursor1.getInt(1);
	        	  ModelQueue_log.calldatetime=Cursor1.getDate(2);
	        	  ModelQueue_log.time=Cursor1.getString(3);
	        	  ModelQueue_log.callid=Cursor1.getString(4);
	        	  ModelQueue_log.queuename=Cursor1.getString(5);
	        	  ModelQueue_log.agent=Cursor1.getString(6);
	        	  ModelQueue_log.event=Cursor1.getString(7);
	        	  ModelQueue_log.data=Cursor1.getString(8);
	        	  ModelQueue_log.data1=Cursor1.getString(9);
	        	  ModelQueue_log.data2=Cursor1.getString(10);
	        	  ModelQueue_log.data3=Cursor1.getString(11);
	        	  ModelQueue_log.data4=Cursor1.getString(12);
	        	  ModelQueue_log.data5=Cursor1.getString(13);
	 	         
	         ListUser1.add(ModelQueue_log);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          queue_logModel ModelQueue_log=new queue_logModel();	
		         ListUser1.add(ModelQueue_log);
	          return ListUser1;
	}
}
