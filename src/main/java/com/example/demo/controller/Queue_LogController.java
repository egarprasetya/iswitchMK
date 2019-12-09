package com.example.demo.controller;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.Date;
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
@RequestMapping(produces = "application/json", path = "/queue_log")
public class Queue_LogController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_queuelog = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_queue_log = null;

	@PutMapping("/putQueueLog")
	public String putQueueLog(@RequestBody Queue_LogModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_queue_log = Connection1.prepareStatement(query_string_insert.query_insert_queue_log);

		query_insert_queue_log.setDate(1, cfm.calldatetime);
		query_insert_queue_log.setString(2, cfm.time);
		query_insert_queue_log.setString(3, cfm.callid);
		query_insert_queue_log.setString(4, cfm.queuename);
		query_insert_queue_log.setString(5, cfm.agent);
		query_insert_queue_log.setString(6, cfm.event);
		query_insert_queue_log.setString(7, cfm.data);
		query_insert_queue_log.setString(8, cfm.data1);
		query_insert_queue_log.setString(9, cfm.data2);
		query_insert_queue_log.setString(10, cfm.data3);
		query_insert_queue_log.setString(11, cfm.data4);
		query_insert_queue_log.setString(12, cfm.data5);

		int Cursor1 = query_insert_queue_log.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getQueueLog")
	public ArrayList<Queue_LogModel> getQueueLog() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_queuelog = Connection1.prepareStatement(query_string.query_select_queue_log);
		ResultSet Cursor1 = queryselect_queuelog.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Queue_LogModel> ListUser1 = new ArrayList<Queue_LogModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Queue_LogModel ModelQueue_log = new Queue_LogModel();
			ModelQueue_log.id = Cursor1.getInt(1);
			ModelQueue_log.calldatetime = Cursor1.getDate(2);
			ModelQueue_log.time = Cursor1.getString(3);
			ModelQueue_log.callid = Cursor1.getString(4);
			ModelQueue_log.queuename = Cursor1.getString(5);
			ModelQueue_log.agent = Cursor1.getString(6);
			ModelQueue_log.event = Cursor1.getString(7);
			ModelQueue_log.data = Cursor1.getString(8);
			ModelQueue_log.data1 = Cursor1.getString(9);
			ModelQueue_log.data2 = Cursor1.getString(10);
			ModelQueue_log.data3 = Cursor1.getString(11);
			ModelQueue_log.data4 = Cursor1.getString(12);
			ModelQueue_log.data5 = Cursor1.getString(13);

			ListUser1.add(ModelQueue_log);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteQueueLog", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteQueueLog(@RequestBody Queue_LogModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_queue_log);
		querydelete_alembic_version_config.setInt(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
