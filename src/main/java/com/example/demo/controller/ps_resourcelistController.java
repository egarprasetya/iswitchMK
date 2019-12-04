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
@RequestMapping(produces="application/json",path="/ps_resource_list")
public class Ps_ResourcelistController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psresourcelist = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_ps_resource_list = null;

	@PutMapping("/putPsResource")
	public String putPsResource(@RequestBody ps_Resource_listModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_ps_resource_list = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_resource_list);
		
		query_insert_ps_resource_list.setString(1, cfm.id);
		query_insert_ps_resource_list.setString(2, cfm.list_item);
		query_insert_ps_resource_list.setString(3, cfm.event);
		query_insert_ps_resource_list.setString(4, cfm.full_state);
		query_insert_ps_resource_list.setInt(5, cfm.notification_batch_interval);
		
		int Cursor1 = query_insert_ps_resource_list.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsResourceList")
	public ArrayList<ps_Resource_listModel> getPsResourceList() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psresourcelist = Connection1.prepareStatement(query_string.query_select_ps_resource_list);
		ResultSet Cursor1 = queryselect_psresourcelist.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_Resource_listModel> ListUser1 = new ArrayList<ps_Resource_listModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_Resource_listModel ModelPs_resource_list = new ps_Resource_listModel();

			ModelPs_resource_list.id = Cursor1.getString(1);
			ModelPs_resource_list.list_item = Cursor1.getString(2);
			ModelPs_resource_list.event = Cursor1.getString(3);
			ModelPs_resource_list.full_state = Cursor1.getString(4);
			ModelPs_resource_list.notification_batch_interval = Cursor1.getInt(5);
			ListUser1.add(ModelPs_resource_list);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsResource", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostPsResource(@RequestBody ps_Resource_listModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_resource_list);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
