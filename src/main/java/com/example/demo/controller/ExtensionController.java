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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extensions")
public class ExtensionController
{
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_extension = null;
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_extension = null;

	@PutMapping(produces="application/json",path="/putExtension")
	public String putExtension(@RequestBody extensionModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_extension = Connection1.prepareStatement(query_string_insert.query_insert_extension);
//		queryinsert_extension.setInt(1, cfm.id);
		queryinsert_extension.setString(1, cfm.context);
		queryinsert_extension.setString(2, cfm.exten);
		queryinsert_extension.setInt(3, cfm.priority);
		queryinsert_extension.setString(4, cfm.app);
		queryinsert_extension.setString(5, cfm.appdata);
		int Cursor1 = queryinsert_extension.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "0";
		Connection1.close();
		return a;
	}

	@GetMapping(produces="application/json",path="/getExtensions")
	public ArrayList<extensionModel> getExtensions() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_extension = Connection1.prepareStatement(query_string.query_select_extension);
		ResultSet Cursor1 = queryselect_extension.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<extensionModel> ListUser1 = new ArrayList<extensionModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			extensionModel Modelextension = new extensionModel();
			Modelextension.id = Cursor1.getInt(1);
			Modelextension.context = Cursor1.getString(2);
			Modelextension.exten = Cursor1.getString(3);
			Modelextension.priority = Cursor1.getInt(4);
			Modelextension.app = Cursor1.getString(5);
			Modelextension.appdata = Cursor1.getString(6);
			ListUser1.add(Modelextension);
		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteExtension", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostExtension(@RequestBody extensionModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_extension);
		querydelete_alembic_version_config.setInt(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

}
