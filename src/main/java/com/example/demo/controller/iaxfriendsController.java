package com.example.demo.controller;

import com.example.demo.connection.stringkoneksi;
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
@RequestMapping("/iaxfriends")
public class iaxfriendsController 
{PreparedStatement querydelete_alembic_version_config=null;
AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_iaxfriends=null;
	@GetMapping("/Getiaxfriends")
	public ArrayList<iaxfriendsModel> Tampiliaxfriends() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect_iaxfriends=Connection1.prepareStatement(query_string.query_select_iaxfriends);
	        ResultSet Cursor1 = queryselect_iaxfriends.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<iaxfriendsModel> ListUser1 = new ArrayList<iaxfriendsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  iaxfriendsModel ModelIaxfriends=new iaxfriendsModel();
	        	  ModelIaxfriends.id= Cursor1.getInt(1);
	        		ModelIaxfriends.name= Cursor1.getString(2);
	        		ModelIaxfriends.type= Cursor1.getString(3); 		// type_value Type.
	        		ModelIaxfriends.username= Cursor1.getString(4);
	        		ModelIaxfriends.mailbox= Cursor1.getString(5);
	        		ModelIaxfriends.secret= Cursor1.getString(6);
	        		ModelIaxfriends.dbsecret= Cursor1.getString(7);
	        		ModelIaxfriends.context= Cursor1.getString(8);
	        		ModelIaxfriends.regcontext= Cursor1.getString(9);
	        		ModelIaxfriends.host= Cursor1.getString(10);
	        		ModelIaxfriends.ipaddr= Cursor1.getString(11);
	        		ModelIaxfriends.port= Cursor1.getInt(12);
	        		ModelIaxfriends.defaultip= Cursor1.getString(13);
	        		ModelIaxfriends.sourceaddress= Cursor1.getString(14);
	        		ModelIaxfriends.mask= Cursor1.getString(15);
	        		ModelIaxfriends.regexten= Cursor1.getString(16);
	        		ModelIaxfriends.regseconds= Cursor1.getInt(17);
	        		ModelIaxfriends.accountcode= Cursor1.getString(18);
	        		ModelIaxfriends.mohinterpret= Cursor1.getString(19);
	        		ModelIaxfriends.mohsuggest= Cursor1.getString(20);
	        		ModelIaxfriends.inkeys= Cursor1.getString(21);
	        		ModelIaxfriends.outkeys= Cursor1.getString(22);
	        		ModelIaxfriends.language= Cursor1.getString(23);
	        		ModelIaxfriends.callerid= Cursor1.getString(24);
	        		ModelIaxfriends.cid_number= Cursor1.getString(25);
	        		ModelIaxfriends.sendani= Cursor1.getBoolean(26);		// YesNo Value/Type.
	        		ModelIaxfriends.fullname= Cursor1.getString(27);		// YesNo Value/Type
	        		ModelIaxfriends.trunk= Cursor1.getBoolean(28);
	        		ModelIaxfriends.auth= Cursor1.getString(29);
	        		ModelIaxfriends.maxauthreq= Cursor1.getInt(30);
	        		ModelIaxfriends.requirecalltoken= Cursor1.getString(31);		// iax_requirecalltoken value/Type.
	        		ModelIaxfriends.encryption= Cursor1.getString(32);			// iax_encryption value/Type.
	        		ModelIaxfriends.transfer= Cursor1.getString(33);				// iax_transfer value/Type.
	        		ModelIaxfriends.jitterbuffer= Cursor1.getBoolean(34);		// YesNo Value/Type.
	        		ModelIaxfriends.forcejitterbuffer= Cursor1.getBoolean(35);		// YesNo Value/Type.
	        		ModelIaxfriends.disallow= Cursor1.getString(36);
	        		ModelIaxfriends.allow= Cursor1.getString(37);
	        		ModelIaxfriends.codecpriority= Cursor1.getString(38);
	        		ModelIaxfriends.qualify= Cursor1.getString(39);
	        		ModelIaxfriends.qualifysmoothing= Cursor1.getBoolean(40);		// YesNo Value/Type
	        		ModelIaxfriends.qualifyfreqok= Cursor1.getString(41);
	        		ModelIaxfriends.qualifyfreqnotok= Cursor1.getString(42);
	        		ModelIaxfriends.timezone= Cursor1.getString(43);
	        		ModelIaxfriends.adsi= Cursor1.getBoolean(44);				// YesNo Value/Type.
	        		ModelIaxfriends.amaflags= Cursor1.getString(45);
	        		ModelIaxfriends.setvar= Cursor1.getString(46);
	        	  ListUser1.add(ModelIaxfriends);  
	          return ListUser1;
	          }	          
	          Connection1.close();
        	  iaxfriendsModel ModelIaxfriends=new iaxfriendsModel();	
 	         ListUser1.add(ModelIaxfriends);  
	          return ListUser1;
	}
	
	@PostMapping("/DeletePostiaxfriends")
	public int DeletePostiaxfriends(@RequestBody int id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		 querydelete_alembic_version_config.setInt(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeletePostiaxfriends")
	public int DeleteGetiaxfriends(@RequestBody int id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		 querydelete_alembic_version_config.setInt(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
