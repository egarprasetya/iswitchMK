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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces="application/json",path="/ps_outbound_publishes")
public class Ps_Outbound_PublishesController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psoutbond = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_outbound_publishes = null;

	@PutMapping("/putPsOutbound")
	public String putPsOutbound(@RequestBody Ps_OutboundModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_outbound_publishes = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_outbound_publishes);
		queryinsert_ps_outbound_publishes.setString(1, cfm.id);
		queryinsert_ps_outbound_publishes.setInt(2, cfm.expiration);
		queryinsert_ps_outbound_publishes.setString(3, cfm.outbound_auth);
		queryinsert_ps_outbound_publishes.setString(4, cfm.outbound_proxy);
		queryinsert_ps_outbound_publishes.setString(5, cfm.server_uri);
		queryinsert_ps_outbound_publishes.setString(6, cfm.from_uri);
		queryinsert_ps_outbound_publishes.setString(7, cfm.to_uri);
		queryinsert_ps_outbound_publishes.setString(8, cfm.event);
		queryinsert_ps_outbound_publishes.setInt(9, cfm.max_auth_attemps);
		queryinsert_ps_outbound_publishes.setString(10, cfm.transport);
		queryinsert_ps_outbound_publishes.setString(11, cfm.multi_user.toString());
		queryinsert_ps_outbound_publishes.setString(12, cfm._body);
		queryinsert_ps_outbound_publishes.setString(13, cfm._context);
		queryinsert_ps_outbound_publishes.setString(14, cfm._exten);

		int Cursor1 = queryinsert_ps_outbound_publishes.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsOutbound")
	public ArrayList<Ps_OutboundModel> TampilOutbound() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psoutbond = Connection1.prepareStatement(query_string.query_select_ps_outbound_publishes);
		ResultSet Cursor1 = queryselect_psoutbond.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_OutboundModel> ListUser1 = new ArrayList<Ps_OutboundModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_OutboundModel ModelPs_outbound = new Ps_OutboundModel();
			ModelPs_outbound.id = Cursor1.getString(1);
			ModelPs_outbound.expiration = Cursor1.getInt(2);
			ModelPs_outbound.outbound_auth = Cursor1.getString(3);
			ModelPs_outbound.outbound_proxy = Cursor1.getString(4);
			ModelPs_outbound.server_uri = Cursor1.getString(5);
			ModelPs_outbound.from_uri = Cursor1.getString(6);
			ModelPs_outbound.to_uri = Cursor1.getString(7);
			ModelPs_outbound.event = Cursor1.getString(8);
			ModelPs_outbound.max_auth_attemps = Cursor1.getInt(9);
			ModelPs_outbound.transport = Cursor1.getString(10);
			ModelPs_outbound.multi_user = YesNo_Values.valueOf(Cursor1.getString(11));
			ModelPs_outbound._body = Cursor1.getString(12);
			ModelPs_outbound._context = Cursor1.getString(13);
			ModelPs_outbound._exten = Cursor1.getString(14);

			ListUser1.add(ModelPs_outbound);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsOutbound", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsOutbound(@RequestBody Ps_OutboundModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_outbound_publishes);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}