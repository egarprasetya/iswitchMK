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
@RequestMapping(produces="application/json",path="/ps_domain_aliases")
public class Ps_Domain_AliasesController 
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_ps_domain_id = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_domain_aliases = null;

	@PutMapping("/putPsDomain")
	public String putPsDomain(@RequestBody Ps_Domain_AliasesModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_domain_aliases = Connection1
				.prepareStatement(query_string_insert.query_insert_ps_domain_aliases);
		queryinsert_ps_domain_aliases.setString(1, cfm.id);
		queryinsert_ps_domain_aliases.setString(2, cfm.domain);
		int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsDomain")
	public ArrayList<Ps_Domain_AliasesModel> getPsDomain() throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_ps_domain_id = Connection1.prepareStatement(query_string.query_select_domain_aliases);
		ResultSet Cursor1 = queryselect_ps_domain_id.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_Domain_AliasesModel> ListUser1 = new ArrayList<Ps_Domain_AliasesModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_Domain_AliasesModel ModelDomain = new Ps_Domain_AliasesModel();
			ModelDomain.id = Cursor1.getString(1);
			ModelDomain.domain = Cursor1.getString(2);

			ListUser1.add(ModelDomain);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsDomain", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsDomain(@RequestBody Ps_Domain_AliasesModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_domain_aliases);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}