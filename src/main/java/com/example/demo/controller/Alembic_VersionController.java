package com.example.demo.controller;

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

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AlembicVersion")
public class Alembic_VersionController
{
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_alembic_version = null;
	PreparedStatement querydelete_alembic_version = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_alembic_version = null;

	@PutMapping(path = "/putAlembicVersion", produces = "application/json")
	public String putAlembicVersion(@RequestBody Alembic_VersionModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_alembic_version = Connection1.prepareStatement(query_string_insert.query_insert_alembic_version);
		queryinsert_alembic_version.setString(1, cfm.version_num);
		int Cursor1 = queryinsert_alembic_version.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping(path = "/getAlembicVersion", produces = "application/json")
	public ArrayList<Alembic_VersionModel> getAlembicVersion() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_alembic_version = Connection1.prepareStatement(query_string.query_select_alembic_version);
		ResultSet Cursor1 = queryselect_alembic_version.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Alembic_VersionModel> ListUser1 = new ArrayList<Alembic_VersionModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Alembic_VersionModel ModelAlembic = new Alembic_VersionModel();
			ModelAlembic.version_num = Cursor1.getString(1);
			ListUser1.add(ModelAlembic);
		}
		Connection1.close();
		return ListUser1;
	}

//	@PostMapping(path = "/PostAlembicVersionParameter", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ArrayList<alembic_versionModel> TampilAlembicVersionParameterSelect(@RequestBody alembic_versionModel cfm)
//			throws SQLException {
//		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
//		queryselect_alembic_version = Connection1
//				.prepareStatement(query_String_param.query_select_alembic_version_param_version_num);
//
//		queryselect_alembic_version.setString(1, cfm.version_num);
//		ResultSet Cursor1 = queryselect_alembic_version.executeQuery();// Evaluate (Connected_Expression1)
//		ArrayList<alembic_versionModel> ListUser1 = new ArrayList<alembic_versionModel>();
//		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
//		{
//			alembic_versionModel ModelAlembic = new alembic_versionModel();
//			ModelAlembic.version_num = Cursor1.getString(1);
//			ListUser1.add(ModelAlembic);
//
//		}
//		Connection1.close();
//
//		return ListUser1;
//
//	}

	@DeleteMapping(path = "/deleteAlembicVersion", produces = "application/json")
	public int deleteAlembicVersion(@RequestBody Alembic_VersionModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version = Connection1.prepareStatement(query_string_delete.query_delete_alembic_version);
		querydelete_alembic_version.setString(1, cfm.version_num);

		int Cursor1 = querydelete_alembic_version.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;

		Connection1.close();
		return a;
	}

}
