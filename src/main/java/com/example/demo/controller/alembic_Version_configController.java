package com.example.demo.controller;
import com.example.demo.query.*;
import com.example.demo.model.*;

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

import com.example.demo.connection.*;

@RestController
@RequestMapping("/AlembicVersionConfig")
public class alembic_Version_configController {

	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	
	PreparedStatement queryselect_alembic_version_config=null;
	
	@GetMapping("/GetAlembicVersionConfig")
	public ArrayList<alembic_version_configModel> TampilAlembicVersionConfig() throws SQLException
	{
		
		   Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_alembic_version_config=Connection1.prepareStatement(query_string.query_select_alembic_version_config);
	        ResultSet Cursor1 = queryselect_alembic_version_config.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<alembic_version_configModel> ListUser1 = new ArrayList<alembic_version_configModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        alembic_version_configModel ModelAlembic=new alembic_version_configModel();	
	         ModelAlembic.version_num=Cursor1.getString(1);
	         ListUser1.add(ModelAlembic);  
	          return ListUser1;
	          }	          
	          Connection1.close();
		        alembic_version_configModel ModelAlembic=new alembic_version_configModel();	
		         ListUser1.add(ModelAlembic);  
	          return ListUser1;

}
	
}
