package com.example.demo.controller;

import com.example.demo.query.*;
import com.example.demo.service.getCurrentMasterAlembicVersion;
import com.example.demo.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.connection.*;

@RestController
@RequestMapping("/AlembicVersionConfig")
@CrossOrigin(origins = "*")
public class Alembic_Version_ConfigController 
{
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	AllInsertQuery query_string_insert = new AllInsertQuery();
	AllSelectParameterQuery query_string_get_param = new AllSelectParameterQuery();
	PreparedStatement queryselect_alembic_version_config = null;
	PreparedStatement queryselect_alembic_version_config_param = null;
	PreparedStatement querydelete_alembic_version_config = null;
	PreparedStatement queryinsert_alembic_version_config = null;

	@GetMapping("/getAlembicVersionConfig")
	public ArrayList<Alembic_Version_ConfigModel> TampilAlembicVersionConfig() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_alembic_version_config = Connection1
				.prepareStatement(query_string.query_select_alembic_version_config);
		// Statement Connected_Expression1 = Connection1.createStatement(); //
		// Create_Expression (Connection1);
		// Connected_Expression1.setFetchSize(100);
		ResultSet Cursor1 = queryselect_alembic_version_config.executeQuery();
		// ("select*from alembic_version_config");// Evaluate (Connected_Expression1)
		ArrayList<Alembic_Version_ConfigModel> ListUser1 = new ArrayList<Alembic_Version_ConfigModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Alembic_Version_ConfigModel ModelAlembic = new Alembic_Version_ConfigModel();
			ModelAlembic.version_num = Cursor1.getString(1);
			ListUser1.add(ModelAlembic);
		}

		Connection1.close();
		return ListUser1;
	}

//	@PostMapping(path = "/PostAlembicVersionConfigParam", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ArrayList<alembic_version_configModel> TampilAlembicVersionConfigParam(
//			@RequestBody alembic_version_configModel cfm) throws SQLException {
//		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
//		queryselect_alembic_version_config_param = Connection1
//				.prepareStatement(query_string_get_param.query_select_parameter_alembic_version_config);
//		queryselect_alembic_version_config_param.setString(1, cfm.version_num);
//
//		ResultSet Cursor1 = queryselect_alembic_version_config.executeQuery();
//		ArrayList<alembic_version_configModel> ListUser1 = new ArrayList<alembic_version_configModel>();
//		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
//		{
//			alembic_version_configModel ModelAlembic = new alembic_version_configModel();
//			ModelAlembic.version_num = Cursor1.getString(1);
//			ListUser1.add(ModelAlembic);
//		}
//
//		Connection1.close();
//		return ListUser1;
//	}

	@PutMapping("/putAlembicVersionConfig")
	public String putAlembicVersionConfig(@RequestBody Alembic_Version_ConfigModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_alembic_version_config = Connection1
				.prepareStatement(query_string_insert.query_insert_alembic_version_config);
		queryinsert_alembic_version_config.setString(1, cfm.version_num);
		int Cursor1 = queryinsert_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "0";
		Connection1.close();
		return a;
	}

	@DeleteMapping("/deleteAlembicVersionConfig")
	public int deleteAlembicVersionConfig(@RequestBody Alembic_Version_ConfigModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_alembic_version_config);
		querydelete_alembic_version_config.setString(1, cfm.version_num);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

}
