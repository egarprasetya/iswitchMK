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
@RequestMapping(produces="application/json",path="/ps_system")
public class Ps_SystemController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_systems = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_ps_systems = null;

	@PutMapping("/putPsSystems")
	public String putPsSystems(@RequestBody Ps_SystemsModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_ps_systems = Connection1.prepareStatement(query_string_insert.query_insert_ps_systems);
		
		query_insert_ps_systems.setString(1, cfm.id);
		query_insert_ps_systems.setInt(2, cfm.timer_t1);
		query_insert_ps_systems.setInt(3, cfm.timer_b);
		query_insert_ps_systems.setString(4, cfm.compact_headers.toString());
		query_insert_ps_systems.setInt(5, cfm.threadpool_initial_size);
		query_insert_ps_systems.setInt(6, cfm.threadpool_auto_increment);
		query_insert_ps_systems.setInt(7, cfm.threadpool_idle_timeout);
		query_insert_ps_systems.setInt(8, cfm.threadpool_max_size);
		query_insert_ps_systems.setString(9, cfm.disable_tcp_switch);
		
		int Cursor1 = query_insert_ps_systems.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsSystem")
	public ArrayList<Ps_SystemsModel> getPsSystem() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_systems = Connection1.prepareStatement(query_string.query_select_ps_systems);
		ResultSet Cursor1 = queryselect_ps_systems.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_SystemsModel> ListUser1 = new ArrayList<Ps_SystemsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_SystemsModel ModelPs_system = new Ps_SystemsModel();
			ModelPs_system.id = Cursor1.getString(1);
			ModelPs_system.timer_t1 = Cursor1.getInt(2);
			ModelPs_system.timer_b = Cursor1.getInt(3);
			ModelPs_system.compact_headers = YesNo_Values.valueOf(Cursor1.getString(4));
			ModelPs_system.threadpool_initial_size = Cursor1.getInt(5);
			ModelPs_system.threadpool_auto_increment = Cursor1.getInt(6);
			ModelPs_system.threadpool_idle_timeout = Cursor1.getInt(7);
			ModelPs_system.threadpool_max_size = Cursor1.getInt(8);
			ModelPs_system.disable_tcp_switch = Cursor1.getString(9);

			ListUser1.add(ModelPs_system);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsSystem", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsSystem(@RequestBody String id) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_systems);
		querydelete_alembic_version_config.setString(1, id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

	@GetMapping(path = "/DeleteGetPsSystem ", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeleteGetPsSystems(@RequestBody String id) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_ps_systems);
		querydelete_alembic_version_config.setString(1, id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}
}
