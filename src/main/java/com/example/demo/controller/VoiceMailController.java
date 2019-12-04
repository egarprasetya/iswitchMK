package com.example.demo.controller;

import com.example.demo.Enum.YesNo_Values;
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
public class VoiceMailController {
	PreparedStatement querydelete_voicemail = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_voicemail = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_voicemail = null;

	@PutMapping("/putVoiceMail")
	public String putVoiceMail(@RequestBody VoiceMailModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_voicemail = Connection1.prepareStatement(query_string_insert.query_insert_voicemail);
		
		query_insert_voicemail.setString(1, cfm.context);
		query_insert_voicemail.setString(2, cfm.mailbox);
		query_insert_voicemail.setString(3, cfm.password);
		query_insert_voicemail.setString(4, cfm.fullname);
		query_insert_voicemail.setString(5, cfm.alias);
		query_insert_voicemail.setString(6, cfm.email);
		query_insert_voicemail.setString(7, cfm.pager);
		query_insert_voicemail.setString(8, cfm.attach.toString());
		query_insert_voicemail.setString(9, cfm.attachfmt);
		query_insert_voicemail.setString(10, cfm.servermail);
		query_insert_voicemail.setString(11, cfm.language);
		query_insert_voicemail.setString(12, cfm.tz);
		query_insert_voicemail.setString(13, cfm.deletevoicemail.toString());
		query_insert_voicemail.setString(14, cfm.saycid.toString());
		query_insert_voicemail.setString(15, cfm.sendvoicemail.toString());
		query_insert_voicemail.setString(16, cfm.review.toString());
		query_insert_voicemail.setString(17, cfm.tempgreetwarn.toString());
		query_insert_voicemail.setString(18, cfm.operator.toString());
		query_insert_voicemail.setString(19, cfm.envelope.toString());
		query_insert_voicemail.setInt(20, cfm.sayduration);
		query_insert_voicemail.setString(21, cfm.forcename.toString());
		query_insert_voicemail.setString(22, cfm.forcegreetings.toString());
		query_insert_voicemail.setString(23, cfm.callback);
		query_insert_voicemail.setString(24, cfm.dialout);
		query_insert_voicemail.setString(25, cfm.exitcontext);
		query_insert_voicemail.setInt(26, cfm.maxmsg);
		query_insert_voicemail.setInt(27, cfm.volgain);
		query_insert_voicemail.setString(28, cfm.imapuser);
		query_insert_voicemail.setString(29, cfm.imappassword);
		query_insert_voicemail.setString(30, cfm.imapserver);
		query_insert_voicemail.setString(31, cfm.imapport);
		query_insert_voicemail.setString(32, cfm.imapflags);
		query_insert_voicemail.setDate(33, cfm.stamp);
		
		int Cursor1 = query_insert_voicemail.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getVoiceMail")
	public ArrayList<VoiceMailModel> getVoiceMail() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_voicemail = Connection1.prepareStatement(query_string.query_select_voicemail);
		ResultSet Cursor1 = queryselect_voicemail.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<VoiceMailModel> ListUser1 = new ArrayList<VoiceMailModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			VoiceMailModel ModelVoiceMail = new VoiceMailModel();

			ModelVoiceMail.uniqueid = Cursor1.getInt(1);
			ModelVoiceMail.context = Cursor1.getString(2);
			ModelVoiceMail.mailbox = Cursor1.getString(3);
			ModelVoiceMail.password = Cursor1.getString(4);
			ModelVoiceMail.fullname = Cursor1.getString(5);
			ModelVoiceMail.alias = Cursor1.getString(6);
			ModelVoiceMail.email = Cursor1.getString(7);
			ModelVoiceMail.pager = Cursor1.getString(8);
			ModelVoiceMail.attach = YesNo_Values.valueOf(Cursor1.getString(9)); // YesNo value/Type.
			ModelVoiceMail.attachfmt = Cursor1.getString(10);
			ModelVoiceMail.servermail = Cursor1.getString(11);
			ModelVoiceMail.language = Cursor1.getString(12);
			ModelVoiceMail.tz = Cursor1.getString(13);
			ModelVoiceMail.deletevoicemail = YesNo_Values.valueOf(Cursor1.getString(14)); // YesNo value/Type.
			ModelVoiceMail.saycid = YesNo_Values.valueOf(Cursor1.getString(15)); // YesNo value/Type.
			ModelVoiceMail.sendvoicemail = YesNo_Values.valueOf(Cursor1.getString(16)); // YesNo value/Type.
			ModelVoiceMail.review = YesNo_Values.valueOf(Cursor1.getString(17)); // YesNo value/Type.
			ModelVoiceMail.tempgreetwarn = YesNo_Values.valueOf(Cursor1.getString(18)); // YesNo value/Type.
			ModelVoiceMail.operator = YesNo_Values.valueOf(Cursor1.getString(19)); // YesNo value/Type.
			ModelVoiceMail.envelope = YesNo_Values.valueOf(Cursor1.getString(20)); // YesNo value/Type.
			ModelVoiceMail.sayduration = Cursor1.getInt(21);
			ModelVoiceMail.forcename = YesNo_Values.valueOf(Cursor1.getString(22)); // YesNo value/Type.
			ModelVoiceMail.forcegreetings = YesNo_Values.valueOf(Cursor1.getString(23)); // YesNo value/Type.
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

	@DeleteMapping(path = "/deleteVoiceMail", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteVoiceMail(@RequestBody VoiceMailModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_voicemail = Connection1.prepareStatement(query_string_delete.query_delete_voicemail);
		querydelete_voicemail.setInt(1, cfm.uniqueid);
		int Cursor1 = querydelete_voicemail.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
