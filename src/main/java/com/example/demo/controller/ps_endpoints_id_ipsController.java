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
@RequestMapping("/psendpointsid")
public class ps_endpoints_id_ipsController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_psendpointsid=null;
	@GetMapping("/Getpsendpointsid")
	public ArrayList<ps_endpoints_id_ipsModel> TampilAlembicVersionConfig() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		  queryselect_psendpointsid=Connection1.prepareStatement(query_string.query_select_ps_endpoints_id);
	        ResultSet Cursor1 = queryselect_psendpointsid.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_endpoints_id_ipsModel> ListUser1 = new ArrayList<ps_endpoints_id_ipsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_endpoints_id_ipsModel ModelPs_enpoints_id=new ps_endpoints_id_ipsModel();	
	        	  ModelPs_enpoints_id.id=Cursor1.getString(1);
	        	  ModelPs_enpoints_id.endpoint=Cursor1.getString(2);
	        	  ModelPs_enpoints_id.match=Cursor1.getString(3);
	        	  ModelPs_enpoints_id.srv_lookups=Cursor1.getBoolean(4);
	        	  ModelPs_enpoints_id.match_header=Cursor1.getString(5);
	        	  
	         ListUser1.add(ModelPs_enpoints_id);  
	          return ListUser1;
	          }	          
	          Connection1.close();
	          ps_endpoints_id_ipsModel ModelPs_enpoints_id=new ps_endpoints_id_ipsModel();	
		         ListUser1.add(ModelPs_enpoints_id);  
	          return ListUser1;
	}
}
