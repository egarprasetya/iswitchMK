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
@RequestMapping("/cdr")
public class cdrController {
	PreparedStatement querydelete_cdr=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_cdr=null;
	@GetMapping("/Getcdr")
	public ArrayList<cdrModel> TampilAlembicVersionConfig() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_cdr=Connection1.prepareStatement(query_string.query_select_alembic_version);
	        ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<cdrModel> ListUser1 = new ArrayList<cdrModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  cdrModel ModelAlembic=new cdrModel();	
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
        	  cdrModel ModelAlembic=new cdrModel();	
 	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostcdrParameter")
	public int DeletePostcdr(@RequestBody String accountcode) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_cdr=Connection1.prepareStatement(query_string_delete.query_delete_cdr);
		 querydelete_cdr.setString(1, accountcode);   
		int Cursor1 = querydelete_cdr.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetcdrParameter")
	public int DeleteGetCdr(@RequestBody String accountcode) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_cdr=Connection1.prepareStatement(query_string_delete.query_delete_cdr);
		 querydelete_cdr.setString(1, accountcode);   
		int Cursor1 = querydelete_cdr.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
