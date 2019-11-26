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
@RequestMapping("/ps_transport")
public class ps_transportController 
{
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
	        	  ps_transportModel ModelAlembic=new ps_transportModel();	
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;

	          }
	          
	          Connection1.close();
	          
	          return ListUser1;

}
}
