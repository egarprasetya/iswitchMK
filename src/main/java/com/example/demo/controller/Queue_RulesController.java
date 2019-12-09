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
@RequestMapping(produces = "application/json", path = "/queue_rules")
public class Queue_RulesController
{

	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();

	PreparedStatement queryselect_queue_rules = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_queue_rules = null;

	@PutMapping("/putQueueRules")
	public String putQueueRules(@RequestBody Queue_RulesModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_queue_rules = Connection1.prepareStatement(query_string_insert.query_insert_queue_rules);

		query_insert_queue_rules.setString(1, cfm.rule_name);
		query_insert_queue_rules.setString(2, cfm.time);
		query_insert_queue_rules.setString(3, cfm.min_penalty); // type_value Type.
		query_insert_queue_rules.setString(4, cfm.max_penalty);

		int Cursor1 = query_insert_queue_rules.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getQueueRules")
	public ArrayList<Queue_RulesModel> getQueueRules() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_queue_rules = Connection1.prepareStatement(query_string.query_select_queue_rules);
		ResultSet Cursor1 = queryselect_queue_rules.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Queue_RulesModel> ListUser1 = new ArrayList<Queue_RulesModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Queue_RulesModel ModelQueue_rules = new Queue_RulesModel();
			ModelQueue_rules.rule_name = Cursor1.getString(1);
			ModelQueue_rules.time = Cursor1.getString(2);
			ModelQueue_rules.min_penalty = Cursor1.getString(3); // type_value Type.
			ModelQueue_rules.max_penalty = Cursor1.getString(4);

			ListUser1.add(ModelQueue_rules);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteQueueRules", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostQueueRules(@RequestBody Queue_RulesModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_queue_rules);
		querydelete_alembic_version_config.setString(1, cfm.rule_name);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
