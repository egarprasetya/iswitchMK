package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.Ps_AuthsModel;
import com.example.demo.model.StatusModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;

@RestController

@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/status")
public class StatusController
{
	AllSelectParameterQuery select_query = new AllSelectParameterQuery();
	AllQuery select_query2 = new AllQuery();
	stringkoneksi sk = new stringkoneksi();

	@GetMapping("/getStatus")
	public ResponseEntity<List<StatusModel>> getStatus ()
	{
		try
		{
			List<StatusModel> result = doGetDashboardStatus();
			return new ResponseEntity<List<StatusModel>>(result, HttpStatus.OK);
		}
		catch (SQLException error_sql)
		{
			error_sql.printStackTrace();
			return new ResponseEntity<List<StatusModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ArrayList<StatusModel> doGetDashboardStatus() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(select_query2.query_select_status);

		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<StatusModel> ListUser1 = new ArrayList<StatusModel>();
		while(Cursor1.next())
		{
			StatusModel ModelStatus = new StatusModel();
			ModelStatus.status_id = Cursor1.getString(1);
			ModelStatus.nama_status = Cursor1.getString(2);
			ListUser1.add(ModelStatus);	
		}
		Connection1.close();
		
		//
		return ListUser1;
	}
}
