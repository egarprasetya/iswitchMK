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
@RequestMapping(produces = "application/json", path = "/ps_endpoints_id")
public class Ps_Endpoints_id_ipsController
{
	PreparedStatement query_delete_ps_endpoints_id = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psendpointsid = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_ps_endpoints_id_ip = null;

	@PutMapping("/putPsEndpointsId")
	public String putPsEndpointsId(@RequestBody Ps_Endpoints_Id_IpsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_endpoints_id_ip = Connection1.prepareStatement(query_string_insert.query_insert_ps_endpoints_id);
		queryinsert_ps_endpoints_id_ip.setString(1, cfm.id);
		queryinsert_ps_endpoints_id_ip.setString(2, cfm.endpoint);
		queryinsert_ps_endpoints_id_ip.setString(3, cfm.match);
		queryinsert_ps_endpoints_id_ip.setString(4, cfm.srv_lookups.toString());
		queryinsert_ps_endpoints_id_ip.setString(5, cfm.match_header);
		int Cursor1 = queryinsert_ps_endpoints_id_ip.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getPsEndpointsId")
	public ArrayList<Ps_Endpoints_Id_IpsModel> getPsEndpointsId() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psendpointsid = Connection1.prepareStatement(query_string.query_select_ps_endpoints_id);
		ResultSet Cursor1 = queryselect_psendpointsid.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Ps_Endpoints_Id_IpsModel> ListUser1 = new ArrayList<Ps_Endpoints_Id_IpsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Ps_Endpoints_Id_IpsModel ModelPs_enpoints_id = new Ps_Endpoints_Id_IpsModel();
			ModelPs_enpoints_id.id = Cursor1.getString(1);
			ModelPs_enpoints_id.endpoint = Cursor1.getString(2);
			ModelPs_enpoints_id.match = Cursor1.getString(3);
			ModelPs_enpoints_id.srv_lookups = YesNo_Values.valueOf(Cursor1.getString(4));
			ModelPs_enpoints_id.match_header = Cursor1.getString(5);

			ListUser1.add(ModelPs_enpoints_id);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deletePsEndpointsId", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deletePsEndpointsId(@RequestBody Ps_Endpoints_Id_IpsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_delete_ps_endpoints_id = Connection1.prepareStatement(query_string_delete.query_delete_ps_endpoints_id);
		query_delete_ps_endpoints_id.setString(1, cfm.id);
		int Cursor1 = query_delete_ps_endpoints_id.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
