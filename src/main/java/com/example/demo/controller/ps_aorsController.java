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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/psaors")
public class ps_aorsController {

	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psaors = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_aor = null;

	@PutMapping("/PutPsAors")
	public String putps_aor(@RequestBody ps_aorsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_aor = Connection1.prepareStatement(query_string_insert.query_insert_ps_aors);
		queryinsert_ps_aor.setString(1, cfm.id);
		queryinsert_ps_aor.setString(2, cfm.contact);
		queryinsert_ps_aor.setInt(3, cfm.default_expiration);
		queryinsert_ps_aor.setString(4, cfm.mailboxes);
		queryinsert_ps_aor.setInt(5, cfm.max_contacts);
		queryinsert_ps_aor.setInt(6, cfm.minimum_expiration);
		queryinsert_ps_aor.setBoolean(7, cfm.remove_existing);
		queryinsert_ps_aor.setInt(8, cfm.qualify_frequency);
		queryinsert_ps_aor.setBoolean(9, cfm.authenticate_qualify);
		queryinsert_ps_aor.setInt(10, cfm.maximum_expiration);
		queryinsert_ps_aor.setString(11, cfm.outbound_proxy);
		queryinsert_ps_aor.setBoolean(12, cfm.support_path);
		queryinsert_ps_aor.setDouble(13, cfm.qualify_timeout);
		queryinsert_ps_aor.setString(14, cfm.voicemail_extension);
		int Cursor1 = queryinsert_ps_aor.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/Getpsaors")

	public ArrayList<ps_aorsModel> Tampilgetpsaors() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psaors = Connection1.prepareStatement(query_string.query_select_ps_aors);
		ResultSet Cursor1 = queryselect_psaors.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_aorsModel> ListUser1 = new ArrayList<ps_aorsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_aorsModel ModelPs_aors = new ps_aorsModel();
			ModelPs_aors.id = Cursor1.getString(1);
			ModelPs_aors.contact = Cursor1.getString(2);
			ModelPs_aors.default_expiration = Cursor1.getInt(3);
			ModelPs_aors.mailboxes = Cursor1.getString(4);
			ModelPs_aors.max_contacts = Cursor1.getInt(5);
			ModelPs_aors.minimum_expiration = Cursor1.getInt(6);
			ModelPs_aors.remove_existing = Cursor1.getBoolean(7); // YesNo value/Type.
			ModelPs_aors.qualify_frequency = Cursor1.getInt(8);
			ModelPs_aors.authenticate_qualify = Cursor1.getBoolean(9); // YesNo value/Type.
			ModelPs_aors.maximum_expiration = Cursor1.getInt(10);
			ModelPs_aors.outbound_proxy = Cursor1.getString(11);
			ModelPs_aors.support_path = Cursor1.getBoolean(12);
			ModelPs_aors.qualify_timeout = Cursor1.getDouble(13);
			ModelPs_aors.voicemail_extension = Cursor1.getString(14);
			ListUser1.add(ModelPs_aors);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/DeletePostPsAors", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostPsAors(@RequestBody ps_aorsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_aors);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

}
