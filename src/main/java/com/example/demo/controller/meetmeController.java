package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.Date;
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

@RequestMapping("/meetme")
public class meetmeController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();

	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_meetme=null;
	@GetMapping("/Getmeetme")
	public ArrayList<meetmeModel> Tampilmetmee() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_meetme=Connection1.prepareStatement(query_string.query_select_meetme);
	        ResultSet Cursor1 = queryselect_meetme.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<meetmeModel> ListUser1 = new ArrayList<meetmeModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  meetmeModel ModelAlembic=new meetmeModel();	
	        // ModelAlembic.version_num=Cursor1.getString(1);
	        	  ModelAlembic.bookid=Cursor1.getInt(1);
	        	  ModelAlembic.confno=Cursor1.getString(2);
	        	  ModelAlembic.starttime=Cursor1.getDate(3);		// Timestamp value/type.
	        	  ModelAlembic.endtime=Cursor1.getDate(4);		// Timestamp value/type.
	        	  ModelAlembic.pin=Cursor1.getString(5);
	        	  ModelAlembic.adminpin=Cursor1.getString(6);
	        	  ModelAlembic.opts=Cursor1.getString(7);
	        	  ModelAlembic.adminopts=Cursor1.getString(8);
	        	  ModelAlembic.recordingfilename=Cursor1.getString(9);
	        	  ModelAlembic.recordingformat=Cursor1.getString(10);
	        	  ModelAlembic.maxusers=Cursor1.getInt(11);
	        	  ModelAlembic.members=Cursor1.getInt(12);
	         ListUser1.add(ModelAlembic);  
	         
	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostmeetmess")
	public int DeletePostmeetme(@RequestBody int bookid) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_meetme);
		 querydelete_alembic_version_config.setInt(1, bookid);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetmeetme")
	public int DeleteGetmeetme(@RequestBody int bookid) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_meetme);
		 querydelete_alembic_version_config.setInt(1, bookid);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
