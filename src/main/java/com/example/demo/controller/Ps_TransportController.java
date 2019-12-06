package com.example.demo.controller;

import com.example.demo.Enum.YesNo_Values;
import com.example.demo.Enum.pjsip_transport_method_values;
import com.example.demo.Enum.pjsip_transport_protocol_values;
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
@RequestMapping(produces = "application/json", path = "/ps_transport")
public class Ps_TransportController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_transport = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_ps_transports = null;

	@PutMapping("/putPsTransport")
	public String putPsTransport(@RequestBody Ps_TransportModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_ps_transports = Connection1.prepareStatement(query_string_insert.query_insert_ps_transports);
		query_insert_ps_transports.setString(1, cfm.id);
		query_insert_ps_transports.setInt(2, cfm.async_operations);
		query_insert_ps_transports.setString(3, cfm.bind);
		query_insert_ps_transports.setString(4, cfm.ca_list_file);
		query_insert_ps_transports.setString(5, cfm.cert_file);
		query_insert_ps_transports.setString(6, cfm.cipher);
		query_insert_ps_transports.setString(7, cfm.domain);
		query_insert_ps_transports.setString(8, cfm.external_media_address);
		query_insert_ps_transports.setString(9, cfm.external_signaling_address);
		query_insert_ps_transports.setInt(10, cfm.external_signaling_port);
		query_insert_ps_transports.setString(11, cfm.method.toString());
		query_insert_ps_transports.setString(12, cfm.local_net);
		query_insert_ps_transports.setString(13, cfm.password);
		query_insert_ps_transports.setString(14, cfm.priv_key_file);
		query_insert_ps_transports.setString(15, cfm.protocol.toString());
		query_insert_ps_transports.setString(16, cfm.require_client_cert.toString());
		query_insert_ps_transports.setString(17, cfm.verify_client.toString());
		query_insert_ps_transports.setString(18, cfm.verify_server.toString());
		query_insert_ps_transports.setString(19, cfm.tos);
		query_insert_ps_transports.setInt(20, cfm.cos);
		query_insert_ps_transports.setString(21, cfm.allow_reload.toString());
		query_insert_ps_transports.setString(22, cfm.symmetric_transport.toString());

		int Cursor1 = query_insert_ps_transports.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsTransport")
	public ArrayList<Ps_TransportModel> getPsTransport() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_transport = Connection1.prepareStatement(query_string.query_select_ps_transports);
		ResultSet Cursor1 = queryselect_ps_transport.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_TransportModel> ListUser1 = new ArrayList<Ps_TransportModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_TransportModel ModelPs_transport = new Ps_TransportModel();
			ModelPs_transport.id = Cursor1.getString(1);
			ModelPs_transport.async_operations = Cursor1.getInt(2);
			ModelPs_transport.bind = Cursor1.getString(3);
			ModelPs_transport.ca_list_file = Cursor1.getString(4);
			ModelPs_transport.cert_file = Cursor1.getString(5);
			ModelPs_transport.cipher = Cursor1.getString(6);
			ModelPs_transport.domain = Cursor1.getString(7);
			ModelPs_transport.external_media_address = Cursor1.getString(8);
			ModelPs_transport.external_signaling_address = Cursor1.getString(9);
			ModelPs_transport.external_signaling_port = Cursor1.getInt(10);
			ModelPs_transport.method = pjsip_transport_method_values.valueOf(Cursor1.getString(11));
			ModelPs_transport.local_net = Cursor1.getString(12);
			ModelPs_transport.password = Cursor1.getString(13);
			ModelPs_transport.priv_key_file = Cursor1.getString(14);
			ModelPs_transport.protocol = pjsip_transport_protocol_values.valueOf(Cursor1.getString(15));
			ModelPs_transport.require_client_cert = YesNo_Values.valueOf(Cursor1.getString(16));
			ModelPs_transport.verify_client = YesNo_Values.valueOf(Cursor1.getString(17));
			ModelPs_transport.verify_server = YesNo_Values.valueOf(Cursor1.getString(18));
			ModelPs_transport.tos = Cursor1.getString(19);
			ModelPs_transport.cos = Cursor1.getInt(20);
			ModelPs_transport.allow_reload = YesNo_Values.valueOf(Cursor1.getString(21));
			ModelPs_transport.symmetric_transport = YesNo_Values.valueOf(Cursor1.getString(22));
			ListUser1.add(ModelPs_transport);

		}
		Connection1.close();
		return ListUser1;

	}

	@DeleteMapping(path = "/deletePsTransport", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsTransport(@RequestBody Ps_TransportModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_transports);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
