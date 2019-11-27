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
@RequestMapping("/musiconhold")
public class musiconholdeController 

{
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_musiconhold=null;
	@GetMapping("/Getmusiconhold")
	public ArrayList<musiconholdeModel> Tampilmusiconholde() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
        queryselect_musiconhold=Connection1.prepareStatement(query_string.query_select_musiconhold);
        ResultSet Cursor1 = queryselect_musiconhold.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<musiconholdeModel> ListUser1 = new ArrayList<musiconholdeModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  musiconholdeModel Modelmusiconhold=new musiconholdeModel();
        	  
        	  Modelmusiconhold.name=Cursor1.getString(1);
        	  Modelmusiconhold.mode=Cursor1.getString(2);					// Moh_mode value/type.
        	  Modelmusiconhold.directory=Cursor1.getString(3);
        	  Modelmusiconhold.application=Cursor1.getString(4);
        	  Modelmusiconhold.digit=Cursor1.getString(5);
        	  Modelmusiconhold.sort=Cursor1.getString(6);
        	  Modelmusiconhold.format=Cursor1.getString(7);
        	  Modelmusiconhold.stamp=Cursor1.getDate(8);
         ListUser1.add(Modelmusiconhold);  
          return ListUser1;
          }         
          Connection1.close();
          musiconholdeModel Modelmusiconhold=new musiconholdeModel();	    	  
          ListUser1.add(Modelmusiconhold);
          return ListUser1;

	}
	
	@PostMapping("/DeletePostmusiconhold")
	public int DeletePostmusiconhold(@RequestBody String version_num) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		 querydelete_alembic_version_config.setString(1, version_num);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
