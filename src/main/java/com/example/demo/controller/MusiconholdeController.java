package com.example.demo.controller;

import com.example.demo.Enum.moh_mode_values;
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
@RequestMapping(produces = "application/json", path = "/musiconhold")
public class MusiconholdeController

{
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_musiconholde = null;
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_musiconhold = null;

	@PutMapping("/putMusiconhold")
	public String putMusiconholde(@RequestBody MusiconholdeModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_musiconholde = Connection1.prepareStatement(query_string_insert.query_insert_musiconhold);
		queryinsert_musiconholde.setString(1, cfm.name);
		queryinsert_musiconholde.setString(2, cfm.mode.toString());
		queryinsert_musiconholde.setString(3, cfm.directory);
		queryinsert_musiconholde.setString(4, cfm.application);
		queryinsert_musiconholde.setString(5, cfm.digit);
		queryinsert_musiconholde.setString(6, cfm.sort);
		queryinsert_musiconholde.setString(7, cfm.format);
		queryinsert_musiconholde.setDate(8, cfm.stamp);
		int Cursor1 = queryinsert_musiconholde.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "0";
		Connection1.close();
		return a;
	}

	@GetMapping("/getMusiconhold")
	public ArrayList<MusiconholdeModel> getMusiconhold() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_musiconhold = Connection1.prepareStatement(query_string.query_select_musiconhold);
		ResultSet Cursor1 = queryselect_musiconhold.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<MusiconholdeModel> ListUser1 = new ArrayList<MusiconholdeModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			MusiconholdeModel Modelmusiconhold = new MusiconholdeModel();
			Modelmusiconhold.name = Cursor1.getString(1);
			Modelmusiconhold.mode = moh_mode_values.valueOf(Cursor1.getString(2)); // Moh_mode value/type.
			Modelmusiconhold.directory = Cursor1.getString(3);
			Modelmusiconhold.application = Cursor1.getString(4);
			Modelmusiconhold.digit = Cursor1.getString(5);
			Modelmusiconhold.sort = Cursor1.getString(6);
			Modelmusiconhold.format = Cursor1.getString(7);
			Modelmusiconhold.stamp = Cursor1.getDate(8);
			ListUser1.add(Modelmusiconhold);
		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteMusiconhold", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteMusiconhold(@RequestBody MusiconholdeModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_musiconhold);
		querydelete_alembic_version_config.setString(1, cfm.name);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}
}
