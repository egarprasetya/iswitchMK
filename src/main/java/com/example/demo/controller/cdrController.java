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
	        	  cdrModel ModelCdr=new cdrModel();
	        	  ModelCdr.accountcode=Cursor1.getString(1);
	        		ModelCdr.src=Cursor1.getString(2);
	        		ModelCdr.dst=Cursor1.getString(3);
	        		ModelCdr.dcontext=Cursor1.getString(4);
	        		ModelCdr.clid=Cursor1.getString(5);
	        		ModelCdr.channel=Cursor1.getString(6);
	        		ModelCdr.dstchannel=Cursor1.getString(7);
	        		ModelCdr.lastapp=Cursor1.getString(8);			// YesNo value / Type.
	        		ModelCdr.lastdata=Cursor1.getString(9);	// pjsip_connected_line_method value/type.
	        		ModelCdr.start=Cursor1.getDate(10);		// pjsip_connected_line_method value/type.
	        		ModelCdr.answer=Cursor1.getDate(11);		// pjsip_direct_media_glare_mitigation value/Type.
	        		ModelCdr.end=Cursor1.getDate(12);
	        		ModelCdr.duration=Cursor1.getInt(13);				// pjsip_dtmf_mode value/Type.
	        		ModelCdr.billsec=Cursor1.getInt(14);
	        		ModelCdr.disposition=Cursor1.getString(15);
	        		ModelCdr.amaflags=Cursor1.getString(16);
	        		ModelCdr.userfield=Cursor1.getString(17);
	        		ModelCdr.uniqueid=Cursor1.getString(18);
	        		ModelCdr.linkedid=Cursor1.getString(19);
	        		ModelCdr.peeraccount=Cursor1.getString(20);
	        		ModelCdr.sequence=Cursor1.getInt(21);
	         ListUser1.add(ModelCdr);  
	         
	          }	          
	          Connection1.close();
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
