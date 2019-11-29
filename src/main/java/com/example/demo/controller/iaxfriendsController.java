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

import com.example.demo.Enum.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iaxfriends")
public class iaxfriendsController {
	iax_encryption_values ix;
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_iaxfriends = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_iaxfriend = null;

	@PutMapping(produces="application/json",path="/Putiaxfirends")
	public String putalembicversionconfig(@RequestBody iaxfriendsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_iaxfriend = Connection1.prepareStatement(query_string_insert.query_insert_iaxfriends);
		queryinsert_iaxfriend.setInt(1, cfm.id);
		queryinsert_iaxfriend.setString(1, cfm.name);
		queryinsert_iaxfriend.setString(2, cfm.type); // type_value Type.
		queryinsert_iaxfriend.setString(3, cfm.username);
		queryinsert_iaxfriend.setString(4, cfm.mailbox);
		queryinsert_iaxfriend.setString(5, cfm.secret);
		queryinsert_iaxfriend.setString(6, cfm.dbsecret);
		queryinsert_iaxfriend.setString(7, cfm.context);
		queryinsert_iaxfriend.setString(8, cfm.regcontext);
		queryinsert_iaxfriend.setString(9, cfm.host);
		queryinsert_iaxfriend.setString(10, cfm.ipaddr);
		queryinsert_iaxfriend.setInt(11, cfm.port);
		queryinsert_iaxfriend.setString(12, cfm.defaultip);
		queryinsert_iaxfriend.setString(13, cfm.sourceaddress);
		queryinsert_iaxfriend.setString(14, cfm.mask);
		queryinsert_iaxfriend.setString(15, cfm.regexten);
		queryinsert_iaxfriend.setInt(16, cfm.regseconds);
		queryinsert_iaxfriend.setString(17, cfm.accountcode);
		queryinsert_iaxfriend.setString(18, cfm.mohinterpret);
		queryinsert_iaxfriend.setString(19, cfm.mohsuggest);
		queryinsert_iaxfriend.setString(20, cfm.inkeys);
		queryinsert_iaxfriend.setString(21, cfm.outkeys);
		queryinsert_iaxfriend.setString(22, cfm.language);
		queryinsert_iaxfriend.setString(23, cfm.callerid);
		queryinsert_iaxfriend.setString(24, cfm.cid_number);
		queryinsert_iaxfriend.setBoolean(25, cfm.sendani); // YesNo Value/Type.
		queryinsert_iaxfriend.setString(26, cfm.fullname); // YesNo Value/Type
		queryinsert_iaxfriend.setBoolean(27, cfm.trunk);
		queryinsert_iaxfriend.setString(28, cfm.auth);
		queryinsert_iaxfriend.setInt(29, cfm.maxauthreq);
		queryinsert_iaxfriend.setString(30, cfm.requirecalltoken); // iax_requirecalltoken value/Type.
		queryinsert_iaxfriend.setString(31, cfm.encryption); // iax_encryption value/Type.
		queryinsert_iaxfriend.setString(32, cfm.transfer); // iax_transfer value/Type.
		queryinsert_iaxfriend.setBoolean(33, cfm.jitterbuffer); // YesNo Value/Type.
		queryinsert_iaxfriend.setBoolean(34, cfm.forcejitterbuffer); // YesNo Value/Type.
		queryinsert_iaxfriend.setString(35, cfm.disallow);
		queryinsert_iaxfriend.setString(36, cfm.allow);
		queryinsert_iaxfriend.setString(37, cfm.codecpriority);
		queryinsert_iaxfriend.setString(38, cfm.qualify);
		queryinsert_iaxfriend.setBoolean(39, cfm.qualifysmoothing); // YesNo Value/Type
		queryinsert_iaxfriend.setString(40, cfm.qualifyfreqok);
		queryinsert_iaxfriend.setString(41, cfm.qualifyfreqnotok);
		queryinsert_iaxfriend.setString(42, cfm.timezone);
		queryinsert_iaxfriend.setBoolean(43, cfm.adsi); // YesNo Value/Type.
		queryinsert_iaxfriend.setString(44, cfm.amaflags);
		queryinsert_iaxfriend.setString(45, cfm.setvar);

		int Cursor1 = queryinsert_iaxfriend.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "0";
		Connection1.close();
		return a;
	}

	@GetMapping(produces="application/json",path="/Getiaxfriends")
	public ArrayList<iaxfriendsModel> Tampiliaxfriends() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_iaxfriends = Connection1.prepareStatement(query_string.query_select_iaxfriends);
		ResultSet Cursor1 = queryselect_iaxfriends.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<iaxfriendsModel> ListUser1 = new ArrayList<iaxfriendsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			iaxfriendsModel ModelIaxfriends = new iaxfriendsModel();
			ModelIaxfriends.id = Cursor1.getInt(1);
			ModelIaxfriends.name = Cursor1.getString(2);
			ModelIaxfriends.type = Cursor1.getString(3); // type_value Type.
			ModelIaxfriends.username = Cursor1.getString(4);
			ModelIaxfriends.mailbox = Cursor1.getString(5);
			ModelIaxfriends.secret = Cursor1.getString(6);
			ModelIaxfriends.dbsecret = Cursor1.getString(7);
			ModelIaxfriends.context = Cursor1.getString(8);
			ModelIaxfriends.regcontext = Cursor1.getString(9);
			ModelIaxfriends.host = Cursor1.getString(10);
			ModelIaxfriends.ipaddr = Cursor1.getString(11);
			ModelIaxfriends.port = Cursor1.getInt(12);
			ModelIaxfriends.defaultip = Cursor1.getString(13);
			ModelIaxfriends.sourceaddress = Cursor1.getString(14);
			ModelIaxfriends.mask = Cursor1.getString(15);
			ModelIaxfriends.regexten = Cursor1.getString(16);
			ModelIaxfriends.regseconds = Cursor1.getInt(17);
			ModelIaxfriends.accountcode = Cursor1.getString(18);
			ModelIaxfriends.mohinterpret = Cursor1.getString(19);
			ModelIaxfriends.mohsuggest = Cursor1.getString(20);
			ModelIaxfriends.inkeys = Cursor1.getString(21);
			ModelIaxfriends.outkeys = Cursor1.getString(22);
			ModelIaxfriends.language = Cursor1.getString(23);
			ModelIaxfriends.callerid = Cursor1.getString(24);
			ModelIaxfriends.cid_number = Cursor1.getString(25);
			ModelIaxfriends.sendani = Cursor1.getBoolean(26); // YesNo Value/Type.
			ModelIaxfriends.fullname = Cursor1.getString(27); // YesNo Value/Type
			ModelIaxfriends.trunk = Cursor1.getBoolean(28);
			ModelIaxfriends.auth = Cursor1.getString(29);
			ModelIaxfriends.maxauthreq = Cursor1.getInt(30);
			ModelIaxfriends.requirecalltoken = Cursor1.getString(31); // iax_requirecalltoken value/Type.
			ModelIaxfriends.encryption = Cursor1.getString(32); // iax_encryption value/Type.
			ModelIaxfriends.transfer = Cursor1.getString(33); // iax_transfer value/Type.
			ModelIaxfriends.jitterbuffer = Cursor1.getBoolean(34); // YesNo Value/Type.
			ModelIaxfriends.forcejitterbuffer = Cursor1.getBoolean(35); // YesNo Value/Type.
			ModelIaxfriends.disallow = Cursor1.getString(36);
			ModelIaxfriends.allow = Cursor1.getString(37);
			ModelIaxfriends.codecpriority = Cursor1.getString(38);
			ModelIaxfriends.qualify = Cursor1.getString(39);
			ModelIaxfriends.qualifysmoothing = Cursor1.getBoolean(40); // YesNo Value/Type
			ModelIaxfriends.qualifyfreqok = Cursor1.getString(41);
			ModelIaxfriends.qualifyfreqnotok = Cursor1.getString(42);
			ModelIaxfriends.timezone = Cursor1.getString(43);
			ModelIaxfriends.adsi = Cursor1.getBoolean(44); // YesNo Value/Type.
			ModelIaxfriends.amaflags = Cursor1.getString(45);
			ModelIaxfriends.setvar = Cursor1.getString(46);
			ListUser1.add(ModelIaxfriends);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/DeletePostiaxfriends", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostiaxfriends(@RequestBody extensionModel cfm) throws SQLException {

		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_iaxfriends);
		querydelete_alembic_version_config.setInt(1, cfm.id);

		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;

		Connection1.close();
		return a;
	}

}
