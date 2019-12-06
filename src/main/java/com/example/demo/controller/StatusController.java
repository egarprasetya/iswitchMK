package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.query.MenuUtamaQuery;
import com.example.demo.query.MenuUtamaQuery;
import com.example.demo.query.query_select_parameter;

@RestController
@RequestMapping(produces = "application/json", path = "/dashboard")
public class StatusController
{
	MenuUtamaQuery menuUtamaQuery = new MenuUtamaQuery();
	stringkoneksi sk = new stringkoneksi();

	@GetMapping("/getStatus")
	public ArrayList<StatusModel> getDashboardStatus() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(menuUtamaQuery.query_status);

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
