package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voicemail")
public class voicemailController 
{PreparedStatement querydelete_alembic_version_config=null;
AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_voicemail=null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_alembic_version_config=null;
	@PutMapping("/PutVoiceMail")
	public String putalembicversionconfig(@RequestBody VoiceMailModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      queryinsert_alembic_version_config=Connection1.prepareStatement(query_string_insert.query_insert_voicemail);
		 queryinsert_alembic_version_config.setString(1, cfm.version_num);   
		int Cursor1 = queryinsert_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		String a ="1"; 
		Connection1.close();
		return a;  	      
	}
	@GetMapping("/Getvoicemail")
	public ArrayList<VoiceMailModel> TampilvoicemailConfig() throws SQLException
	{	 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		   queryselect_voicemail=Connection1.prepareStatement(query_string.query_select_voicemail);
	        ResultSet Cursor1 = queryselect_voicemail.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<VoiceMailModel> ListUser1 = new ArrayList<VoiceMailModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        VoiceMailModel ModelVoiceMail=new VoiceMailModel();
      	  
	        ModelVoiceMail.uniqueid = Cursor1.getInt(1);
	    	ModelVoiceMail.context = Cursor1.getString(2);
	    	ModelVoiceMail.mailbox = Cursor1.getString(3);
	    	ModelVoiceMail.password = Cursor1.getString(4);
	    	ModelVoiceMail.fullname = Cursor1.getString(5);
	    	ModelVoiceMail.alias = Cursor1.getString(6);
	    	ModelVoiceMail.email = Cursor1.getString(7);
	    	ModelVoiceMail.pager = Cursor1.getString(8);
	    	ModelVoiceMail.attach = Cursor1.getBoolean(9);					// YesNo value/Type.
	    	ModelVoiceMail.attachfmt = Cursor1.getString(10);
	    	ModelVoiceMail.servermail = Cursor1.getString(11);
	    	ModelVoiceMail.language = Cursor1.getString(12);
	    	ModelVoiceMail.tz = Cursor1.getString(13);
	    	ModelVoiceMail.deletevoicemail = Cursor1.getBoolean(14);			// YesNo value/Type.
	    	ModelVoiceMail.saycid = Cursor1.getBoolean(15);					// YesNo value/Type.
	    	ModelVoiceMail.sendvoicemail = Cursor1.getBoolean(16);			// YesNo value/Type.
	    	ModelVoiceMail.review = Cursor1.getBoolean(17);					// YesNo value/Type.
	    	ModelVoiceMail.tempgreetwarn = Cursor1.getBoolean(18);			// YesNo value/Type.
	    	ModelVoiceMail.operator = Cursor1.getBoolean(19);				// YesNo value/Type.
	    	ModelVoiceMail.envelope = Cursor1.getBoolean(20);				// YesNo value/Type.
	    	ModelVoiceMail.sayduration = Cursor1.getInt(21);
	    	ModelVoiceMail.forcename = Cursor1.getBoolean(22);				// YesNo value/Type.
	    	ModelVoiceMail.forcegreetings = Cursor1.getBoolean(23);			// YesNo value/Type.
	    	ModelVoiceMail.callback = Cursor1.getString(24);
	    	ModelVoiceMail.dialout = Cursor1.getString(25);
	    	ModelVoiceMail.exitcontext = Cursor1.getString(26);
	    	ModelVoiceMail.maxmsg = Cursor1.getInt(27);
	    	ModelVoiceMail.volgain = Cursor1.getInt(28);
	    	ModelVoiceMail.imapuser = Cursor1.getString(29);
	    	ModelVoiceMail.imappassword = Cursor1.getString(30);
	    	ModelVoiceMail.imapserver = Cursor1.getString(31);
	    	ModelVoiceMail.imapport = Cursor1.getString(32);
	    	ModelVoiceMail.imapflags = Cursor1.getString(33);
	    	ModelVoiceMail.stamp = Cursor1.getDate(34);
	        
	        ListUser1.add(ModelVoiceMail);
	        
	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@DeleteMapping(path="/DeletePostVoiceMail",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostVoiceMail(@RequestBody VoiceMailModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_voicemail);
		 querydelete_alembic_version_config.setInt(1, cfm.uniqueid);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}

	
}
