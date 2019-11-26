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
@RequestMapping("/ps_domain_id")
public class ps_domain_aliasesController {
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_ps_domain_id=null;
	@GetMapping("/Getdomain")
	public ArrayList<ps_domain_aliasesModel> TampilAlembicVersionConfig() throws SQLException
	{
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		  queryselect_ps_domain_id=Connection1.prepareStatement(query_string.query_select_domain_aliases);
	        ResultSet Cursor1 = queryselect_ps_domain_id.executeQuery();// Evaluate (Connected_Expression1)

	      	  ArrayList<ps_domain_aliasesModel> ListUser1 = new ArrayList<ps_domain_aliasesModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_domain_aliasesModel ModelAlembic=new ps_domain_aliasesModel();	
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;

	          }
	          
	          Connection1.close();
	          
	          return ListUser1;
	}
}
