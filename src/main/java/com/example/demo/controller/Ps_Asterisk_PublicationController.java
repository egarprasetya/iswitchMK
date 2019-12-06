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
@RequestMapping(produces = "application/json", path = "/ps_asterisk")
public class Ps_Asterisk_PublicationController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_asterisk = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_asterisk_publication = null;

	@PutMapping("/putPsAsterisk")
	public String putPsAsterisk(@RequestBody Ps_Asterisk_PublicationsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_asterisk_publication = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_asterisk_publications);
		queryinsert_ps_asterisk_publication.setString(1, cfm.id);
		queryinsert_ps_asterisk_publication.setString(2, cfm.devicestate_publish);
		queryinsert_ps_asterisk_publication.setString(3, cfm.mailboxstate_publish);
		queryinsert_ps_asterisk_publication.setString(4, cfm.device_state);
		queryinsert_ps_asterisk_publication.setString(5, cfm.device_state_filter);
		queryinsert_ps_asterisk_publication.setString(6, cfm.mailbox_state);
		queryinsert_ps_asterisk_publication.setString(7, cfm.mailbox_state_filter);

		int Cursor1 = queryinsert_ps_asterisk_publication.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsAsterisk")
	public ArrayList<Ps_Asterisk_PublicationsModel> getPsAsterisk() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_asterisk = Connection1.prepareStatement(query_string.query_select_ps_asterisk_publications);
		ResultSet Cursor1 = queryselect_ps_asterisk.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_Asterisk_PublicationsModel> ListUser1 = new ArrayList<Ps_Asterisk_PublicationsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_Asterisk_PublicationsModel ModelPs_asterisk = new Ps_Asterisk_PublicationsModel();
			ModelPs_asterisk.id = Cursor1.getString(1);
			ModelPs_asterisk.devicestate_publish = Cursor1.getString(2);
			ModelPs_asterisk.mailboxstate_publish = Cursor1.getString(3);
			ModelPs_asterisk.device_state = Cursor1.getString(4);
			ModelPs_asterisk.device_state_filter = Cursor1.getString(5);
			ModelPs_asterisk.mailbox_state = Cursor1.getString(6);
			ModelPs_asterisk.mailbox_state_filter = Cursor1.getString(7);
			ListUser1.add(ModelPs_asterisk);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsAsterisk", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsAsterisk(@RequestBody Ps_Asterisk_PublicationsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_asterisk_publications);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

	@GetMapping("/DeleteGetpsAsterisk")
	public int DeleteGetpsasteriskpublication(@RequestBody String id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_asterisk_publications);
		querydelete_alembic_version_config.setString(1, id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}
}
