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

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.query.LoginQuery;

@RestController
@RequestMapping(produces = "application/json", path = "/logout_user")
public class LogoutController
{
	LoginQuery logoutquery = new LoginQuery();
	stringkoneksi sk = new stringkoneksi();
	
	@PostMapping("/logout")
	public int postAuthsLogout(@RequestBody UserModel cfm)
	{
		
		try
		{
			if (cfm.user_id != null)
			{
				updateStatus(cfm.user_id, "0");
				return 1;
			}
			else
			{
				return 0;
			}
			
		}
		catch (Exception error)
		{
			error.printStackTrace();
			return 0;
		}
	}

	public void updateStatus(String id, String status)
	{
		try
		{
			Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = Connection1.prepareStatement(logoutquery.query_login2);

			a.setString(1, status);
			a.setString(2, id);
			a.executeUpdate();
		} catch (SQLException error)
		{
			error.printStackTrace();
		}

	}
}
