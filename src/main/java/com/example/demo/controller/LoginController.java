package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.Ps_AuthsModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.LoginQuery;
import com.example.demo.query.query_select_parameter;

@RestController
@RequestMapping(produces="application/json",path="/login_user")
public class LoginController
{
	LoginQuery loginquery = new LoginQuery();
	stringkoneksi sk = new stringkoneksi();
	
	@PostMapping("/login")
	public ArrayList<UserModel> postAuthsId(@RequestBody UserModel cfm) throws SQLException 
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(loginquery.query_login);
		
		a.setString(1, cfm.username);
		a.setString(2, cfm.password);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			UserModel Modeluser = new UserModel();
			Modeluser.user_id = Cursor1.getString(1);
			Modeluser.username = Cursor1.getString(2);
			Modeluser.extensions_user = Cursor1.getString(3);
			ListUser1.add(Modeluser);

		}
		Connection1.close();
		return ListUser1;

	}
}
