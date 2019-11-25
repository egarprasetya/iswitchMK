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
@RequestMapping("/extension")
public class extensionController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_extension=null;
	
	@GetMapping("/Getextension")
	public ArrayList<extensionModel> Tampilextension() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		  queryselect_extension=Connection1.prepareStatement(query_string.query_select_extension);
	        ResultSet Cursor1 = queryselect_extension.executeQuery();// Evaluate (Connected_Expression1)

	      	  ArrayList<extensionModel> ListUser1 = new ArrayList<extensionModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  extensionModel Modelextension=new extensionModel();	
	        	  Modelextension.id=Cursor1.getInt(1);
	        	  Modelextension.context=Cursor1.getString(2);
	        	  Modelextension.exten=Cursor1.getString(3);
	        	  Modelextension.priority=Cursor1.getInt(4);
	        	  Modelextension.app=Cursor1.getString(5);
	        	  Modelextension.appdata=Cursor1.getString(6);
	         ListUser1.add(Modelextension);  
	          return ListUser1;

	          }
	          
	          Connection1.close();
	          
	          return ListUser1;
	}

}
