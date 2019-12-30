package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.User_ActivityModel;

@RestController
@CrossOrigin("*")
@RequestMapping("/user_activity")
public class User_ActivityController
{
	stringkoneksi sk = new stringkoneksi();
	
	
	@GetMapping("/getAllActivity")
	public List<User_ActivityModel> getAll (@RequestBody User_ActivityModel ua) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection.prepareStatement ("SELECT * FROM user_activity");

		ResultSet Cursor1 = query.executeQuery ();
		List<User_ActivityModel> ls = new ArrayList<User_ActivityModel>();
		while(Cursor1.next ())
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

}
