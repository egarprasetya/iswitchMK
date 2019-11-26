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
@RequestMapping("/ps_subscription_persistence")
public class ps_subscription_persistenceController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_subscription_persistence=null;
	@GetMapping("/Getps_subscription_persistence")
	public ArrayList<ps_subscription_persistenceModel> Tampilps_subscription_persistence() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_subscription_persistence=Connection1.prepareStatement(query_string.query_select_ps_subscription_persistence);
        ResultSet Cursor1 = queryselect_ps_subscription_persistence.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<ps_subscription_persistenceModel> ListUser1 = new ArrayList<ps_subscription_persistenceModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  ps_subscription_persistenceModel ModelAlembic=new ps_subscription_persistenceModel();	
        	  ListUser1.add(ModelAlembic);  
          return ListUser1;
          }          
          Connection1.close();
          ps_subscription_persistenceModel ModelAlembic=new ps_subscription_persistenceModel();	
    	  ListUser1.add(ModelAlembic); 
          return ListUser1;
	}
}
