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
import com.example.demo.Enum.yesenum.yesno_values;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(produces="application/json",path="/ps_aors")
public class Ps_AorsController {

	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psaors = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_aor = null;
	
	YesNo_Values enumyesno;

	@PutMapping("/putPsAors")
	public String putPsAors(@RequestBody Ps_AorsModel cfm) throws SQLException {

		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_aor = Connection1.prepareStatement(query_string_insert.query_insert_ps_aors);
		queryinsert_ps_aor.setString(1, cfm.id);
		queryinsert_ps_aor.setString(2, cfm.contact);
		queryinsert_ps_aor.setInt(3, cfm.default_expiration);
		queryinsert_ps_aor.setString(4, cfm.mailboxes);
		queryinsert_ps_aor.setInt(5, cfm.max_contacts);
		queryinsert_ps_aor.setInt(6, cfm.minimum_expiration);
		queryinsert_ps_aor.setString(7, cfm.remove_existing.toString());
		queryinsert_ps_aor.setInt(8, cfm.qualify_frequency);
		queryinsert_ps_aor.setString(9, cfm.authenticate_qualify.toString());
		queryinsert_ps_aor.setInt(10, cfm.maximum_expiration);
		queryinsert_ps_aor.setString(11, cfm.outbound_proxy);
		queryinsert_ps_aor.setString(12, cfm.support_path.toString());
		queryinsert_ps_aor.setDouble(13, cfm.qualify_timeout);
		queryinsert_ps_aor.setString(14, cfm.voicemail_extension);
		int Cursor1 = queryinsert_ps_aor.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsAors")

	public ArrayList<Ps_AorsModel> getPsAors() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psaors = Connection1.prepareStatement(query_string.query_select_ps_aors);
		ResultSet Cursor1 = queryselect_psaors.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_AorsModel> ListUser1 = new ArrayList<Ps_AorsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_AorsModel ModelPs_aors = new Ps_AorsModel();
			ModelPs_aors.id = Cursor1.getString(1);
			ModelPs_aors.contact = Cursor1.getString(2);
			ModelPs_aors.default_expiration = Cursor1.getInt(3);
			ModelPs_aors.mailboxes = Cursor1.getString(4);
			ModelPs_aors.max_contacts = Cursor1.getInt(5);
			ModelPs_aors.minimum_expiration = Cursor1.getInt(6);
			ModelPs_aors.remove_existing=YesNo_Values.valueOf(Cursor1.getString("remove_existing"));
			ModelPs_aors.qualify_frequency = Cursor1.getInt(8);
			ModelPs_aors.authenticate_qualify=YesNo_Values.valueOf(Cursor1.getString("authenticate_qualify").toString());
			ModelPs_aors.maximum_expiration = Cursor1.getInt(10);
			ModelPs_aors.outbound_proxy = Cursor1.getString(11);
			ModelPs_aors.support_path=	YesNo_Values.valueOf(Cursor1.getString("support_path").toString());
			ModelPs_aors.qualify_timeout = Cursor1.getDouble(13);
			ModelPs_aors.voicemail_extension = Cursor1.getString(14);
			ListUser1.add(ModelPs_aors);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsAors", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsAors(@RequestBody Ps_AorsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_aors);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

}
