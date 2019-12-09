package com.example.demo.controller;

import com.example.demo.Enum.YesNo_Values;
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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/ps_contact")
public class Ps_ContactsController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_contact = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_contact = null;

	@PutMapping("/putPsContacts")
	public String putPsContacts(@RequestBody Ps_ContactsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_contact = Connection1.prepareStatement(query_string_insert.query_insert_ps_contacts);
		queryinsert_ps_contact.setString(1, cfm.id);
		queryinsert_ps_contact.setString(2, cfm.uri);
		queryinsert_ps_contact.setInt(3, cfm.expiration_time);
		queryinsert_ps_contact.setInt(4, cfm.qualify_frequency);
		queryinsert_ps_contact.setString(5, cfm.outbond_proxy);
		queryinsert_ps_contact.setString(6, cfm.path);
		queryinsert_ps_contact.setString(7, cfm.user_agent);
		queryinsert_ps_contact.setDouble(8, cfm.qualify_timeout);
		queryinsert_ps_contact.setString(9, cfm.reg_server);
		queryinsert_ps_contact.setString(10, cfm.authenticate_qualify.toString());
		queryinsert_ps_contact.setString(11, cfm.via_addr);
		queryinsert_ps_contact.setInt(12, cfm.via_port);
		queryinsert_ps_contact.setString(13, cfm.call_id);
		queryinsert_ps_contact.setString(14, cfm.endpoint);
		queryinsert_ps_contact.setString(15, cfm.prune_on_boot);
		int Cursor1 = queryinsert_ps_contact.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsContact")
	public ArrayList<Ps_ContactsModel> getPsContact() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_contact = Connection1.prepareStatement(query_string.query_select_ps_contacts);
		ResultSet Cursor1 = queryselect_ps_contact.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_ContactsModel> ListUser1 = new ArrayList<Ps_ContactsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_ContactsModel ModelContact = new Ps_ContactsModel();
			ModelContact.id = Cursor1.getString(1);
			ModelContact.uri = Cursor1.getString(2);
			ModelContact.expiration_time = Cursor1.getInt(3);
			ModelContact.qualify_frequency = Cursor1.getInt(4);
			ModelContact.outbond_proxy = Cursor1.getString(5);
			ModelContact.path = Cursor1.getString(6);
			ModelContact.user_agent = Cursor1.getString(7);
			ModelContact.qualify_timeout = Cursor1.getInt(8);
			ModelContact.reg_server = Cursor1.getString(9);
			ModelContact.authenticate_qualify = YesNo_Values.valueOf(Cursor1.getString(10));
			ModelContact.via_addr = Cursor1.getString(11);
			ModelContact.via_port = Cursor1.getInt(12);
			ModelContact.call_id = Cursor1.getString(13);
			ModelContact.endpoint = Cursor1.getString(14);
			ModelContact.prune_on_boot = Cursor1.getString(15);
			ListUser1.add(ModelContact);
		}
		Connection1.close();
		return ListUser1;

	}

	@DeleteMapping(path = "/deletePsContacts", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsContacts(@RequestBody Ps_ContactsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_contacts);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
