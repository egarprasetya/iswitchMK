package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.User_ActivityModel;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin("*")
@RequestMapping("/user_activity")
public class User_ActivityController
{
	@Autowired
	private DataSource dataSource;
	
	stringkoneksi sk = new stringkoneksi ();
	AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
	AllQuery select_query2 = new AllQuery ();
	AllUpdateQuery select_query3 = new AllUpdateQuery ();
	
	@GetMapping("/getAllActivity")
	public List<User_ActivityModel> getAll (@RequestBody User_ActivityModel ua) throws SQLException
	{
		//Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection();
		PreparedStatement query = connection.prepareStatement ("SELECT * FROM user_activity");
		
		ResultSet Cursor1 = query.executeQuery ();
		List<User_ActivityModel> ls = new ArrayList<User_ActivityModel> ();
		while (Cursor1.next ())
		{
			User_ActivityModel uas = new User_ActivityModel ();
			uas.extension = Cursor1.getString (1);
			uas.nama_user = Cursor1.getString (2);
			uas.reason = Cursor1.getString (3);
			uas.status = Cursor1.getString (4);
			uas.skill = Cursor1.getString (5);
			uas.last_update = Cursor1.getTimestamp (6);
			
			ls.add (uas);
		}
		
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return ls;
	}
	
	@PostMapping("/insertUserActivity")
	public String insertUserActivity (@RequestBody User_ActivityModel cfm) throws SQLException
	{
		
		int flag = 0;
		try
		{
			//Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection();
			PreparedStatement a = Connection1.prepareStatement (select_query3.query_update_user_activity);
			a.setString (1, cfm.extension);
			a.setString (2, cfm.nama_user);
			a.setString (3, cfm.status);
			a.setString (4, cfm.status);
			a.setString (5, cfm.skill);
			a.setTimestamp (6, cfm.last_update);
			
			flag = a.executeUpdate ();// Evaluate (Connected_Expression1)
			
			a.close ();
			Connection1.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + error.getErrorCode () + "\" }";
		}
	}
	
	@PostMapping("/postUpdateUserActivity")
	public String postUpdateUserActivity (@RequestBody UserModel cfm) throws SQLException
	{
		
		int flag = 0;
		try
		{
			//Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection();
			PreparedStatement a = Connection1.prepareStatement (select_query3.query_update_user_activity);
			
			a.setString (1, cfm.status);
			a.setString (2, cfm.status);
			a.setString (3, cfm.skill);
			a.setTimestamp (4, cfm.date_begin);
			a.setString (5, cfm.extensions_user);
			
			flag = a.executeUpdate ();// Evaluate (Connected_Expression1)
			
			a.close ();
			Connection1.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + error.getErrorCode () + "\" }";
		}
	}
}
