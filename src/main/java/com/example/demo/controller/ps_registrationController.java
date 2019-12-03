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
@RequestMapping(produces="application/json",path="/ps_registration")
public class ps_registrationController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_registration = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_registration = null;

	@PutMapping("/putPsRegistration")
	public String putPsRegistration(@RequestBody ps_registrationsModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_registration = Connection1.prepareStatement(query_string_insert.query_insert_ps_registrations);
		queryinsert_ps_registration.setString(1, cfm.id);
		queryinsert_ps_registration.setString(2, cfm.auth_rejection_permanent.toString());
		queryinsert_ps_registration.setString(3, cfm.client_uri);
		queryinsert_ps_registration.setString(4, cfm.contact_user);
		queryinsert_ps_registration.setInt(5, cfm.expiration);
		queryinsert_ps_registration.setInt(6, cfm.max_retries);
		queryinsert_ps_registration.setString(7, cfm.outbound_auth);
		queryinsert_ps_registration.setString(8, cfm.outbound_proxy);
		queryinsert_ps_registration.setInt(9, cfm.retry_interval);
		queryinsert_ps_registration.setInt(10, cfm.forbidden_retry_interval);
		queryinsert_ps_registration.setString(11, cfm.server_uri);
		queryinsert_ps_registration.setString(12, cfm.transport);
		queryinsert_ps_registration.setString(13, cfm.support_path);
		queryinsert_ps_registration.setInt(14, cfm.fatal_retry_interval);
		queryinsert_ps_registration.setString(15, cfm.line.toString());
		queryinsert_ps_registration.setString(16, cfm.endpoint);
		int Cursor1 = queryinsert_ps_registration.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsRegistration")
	public ArrayList<ps_registrationsModel> getPsRegistration() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_registration = Connection1.prepareStatement(query_string.query_select_ps_registrations);
		ResultSet Cursor1 = queryselect_ps_registration.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_registrationsModel> ListUser1 = new ArrayList<ps_registrationsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_registrationsModel ModelPs_registration = new ps_registrationsModel();

			ModelPs_registration.id = Cursor1.getString(1);
			ModelPs_registration.auth_rejection_permanent = YesNo_Values.valueOf(Cursor1.getString(2));
			ModelPs_registration.client_uri = Cursor1.getString(3);
			ModelPs_registration.contact_user = Cursor1.getString(4);
			ModelPs_registration.expiration = Cursor1.getInt(5);
			ModelPs_registration.max_retries = Cursor1.getInt(6);
			ModelPs_registration.outbound_auth = Cursor1.getString(7);
			ModelPs_registration.outbound_proxy = Cursor1.getString(8);
			ModelPs_registration.retry_interval = Cursor1.getInt(9);
			ModelPs_registration.forbidden_retry_interval = Cursor1.getInt(10);
			ModelPs_registration.server_uri = Cursor1.getString(11);
			ModelPs_registration.transport = Cursor1.getString(12);
			ModelPs_registration.support_path = Cursor1.getString(13);
			ModelPs_registration.fatal_retry_interval = Cursor1.getInt(14);
			ModelPs_registration.line = YesNo_Values.valueOf(Cursor1.getString(15));
			ModelPs_registration.endpoint = Cursor1.getString(16);

			ListUser1.add(ModelPs_registration);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsRegistration", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsRegistration(@RequestBody ps_registrationsModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_registrations);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
