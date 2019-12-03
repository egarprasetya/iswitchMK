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
@RequestMapping(produces="application/json",path="/ps_subscription_persistence")
public class ps_subscription_persistenceController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_subscription_persistence = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_ps_subscription_persistence = null;

	@PutMapping("/putPsSubscriptions")
	public String putPsSubscriptions(@RequestBody ps_subscription_persistenceModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_ps_subscription_persistence = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_subscription_persistence);
		
		query_insert_ps_subscription_persistence.setString(1, cfm.id);
		query_insert_ps_subscription_persistence.setString(2, cfm.packet);
		query_insert_ps_subscription_persistence.setString(3, cfm.src_name);
		query_insert_ps_subscription_persistence.setInt(4, cfm.src_port);
		query_insert_ps_subscription_persistence.setString(5, cfm.transport_key);
		query_insert_ps_subscription_persistence.setString(6, cfm.local_name);
		query_insert_ps_subscription_persistence.setInt(7, cfm.local_port);
		query_insert_ps_subscription_persistence.setInt(8, cfm.cseq);
		query_insert_ps_subscription_persistence.setString(9, cfm.tag);
		query_insert_ps_subscription_persistence.setString(10, cfm.endpoint);
		query_insert_ps_subscription_persistence.setInt(11, cfm.expires);
		query_insert_ps_subscription_persistence.setString(12, cfm.contact_uri);
		query_insert_ps_subscription_persistence.setString(13, cfm.prune_on_boot.toString());
		
		int Cursor1 = query_insert_ps_subscription_persistence.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsSubscriptionPersistence")
	public ArrayList<ps_subscription_persistenceModel> getPsSubscriptionPersistence() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_subscription_persistence = Connection1
				.prepareStatement(query_string.query_select_ps_subscription_persistence);
		ResultSet Cursor1 = queryselect_ps_subscription_persistence.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_subscription_persistenceModel> ListUser1 = new ArrayList<ps_subscription_persistenceModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_subscription_persistenceModel ModelPs_subscription_persistance = new ps_subscription_persistenceModel();
			ModelPs_subscription_persistance.id = Cursor1.getString(1);
			ModelPs_subscription_persistance.packet = Cursor1.getString(2);
			ModelPs_subscription_persistance.src_name = Cursor1.getString(3);
			ModelPs_subscription_persistance.src_port = Cursor1.getInt(4);
			ModelPs_subscription_persistance.transport_key = Cursor1.getString(5);
			ModelPs_subscription_persistance.local_name = Cursor1.getString(6);
			ModelPs_subscription_persistance.local_port = Cursor1.getInt(7);
			ModelPs_subscription_persistance.cseq = Cursor1.getInt(8);
			ModelPs_subscription_persistance.tag = Cursor1.getString(9);
			ModelPs_subscription_persistance.endpoint = Cursor1.getString(10);
			ModelPs_subscription_persistance.expires = Cursor1.getInt(11);
			ModelPs_subscription_persistance.contact_uri = Cursor1.getString(12);
			ModelPs_subscription_persistance.prune_on_boot = YesNo_Values.valueOf(Cursor1.getString(13));
			ListUser1.add(ModelPs_subscription_persistance);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsSubscript", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsSubscript(@RequestBody ps_subscription_persistenceModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_subscription_persistence);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
