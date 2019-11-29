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
@RequestMapping(produces="application/json",path="/ps_inbound")
public class ps_inbound_publicationsController {
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psinbound = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_inbound_publication = null;

	@PutMapping("/PutPsInbound")
	public String putInboundPublication(@RequestBody ps_inboundModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_inbound_publication = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_inbound_publications);
		queryinsert_inbound_publication.setString(1, cfm.id);
		queryinsert_inbound_publication.setString(2, cfm.endpoint);
		queryinsert_inbound_publication.setString(3, cfm.event_asterisk_devicestate);
		queryinsert_inbound_publication.setString(4, cfm.event_asterisk_mwi);

		int Cursor1 = queryinsert_inbound_publication.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/Getpsinbound")
	public ArrayList<ps_inboundModel> Tampilps_inbound() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psinbound = Connection1.prepareStatement(query_string.query_select_alembic_version);
		ResultSet Cursor1 = queryselect_psinbound.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_inboundModel> ListUser1 = new ArrayList<ps_inboundModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_inboundModel ModelPs_inbound = new ps_inboundModel();
			ModelPs_inbound.id = Cursor1.getString(1);
			ModelPs_inbound.endpoint = Cursor1.getString(2);
			ModelPs_inbound.event_asterisk_devicestate = Cursor1.getString(3);
			ModelPs_inbound.event_asterisk_mwi = Cursor1.getString(4);

			ListUser1.add(ModelPs_inbound);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/DeletePostPsInbound", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostPsInbound(@RequestBody ps_inboundModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_inbound_publications);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
