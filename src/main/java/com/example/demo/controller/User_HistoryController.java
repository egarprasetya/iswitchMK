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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.model.User_ActivityModel;
import com.example.demo.model.User_HistoryModel;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin("*")
@RequestMapping("/user_history")

public class User_HistoryController
{
	stringkoneksi sk = new stringkoneksi ();
	AllInsertQuery insert_query = new AllInsertQuery ();
	AllUpdateQuery update_query = new AllUpdateQuery ();
	
	@GetMapping("/getAllHistory")
	public List<User_HistoryModel> getAll (@RequestBody User_HistoryModel uh) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection.prepareStatement ("SELECT * FROM user_history");
		
		ResultSet Cursor1 = query.executeQuery ();
		List<User_HistoryModel> ls = new ArrayList<User_HistoryModel>();
		while(Cursor1.next ())
		{
			User_HistoryModel uas = new User_HistoryModel ();
			uas.id_user_history = Cursor1.getString (1);
			uas.extension = Cursor1.getString (2);
			uas.nama_user = Cursor1.getString (3);
			uas.reason = Cursor1.getString (4);
			uas.status = Cursor1.getString (5);
			uas.skill = Cursor1.getString (6);
			uas.date_begin = Cursor1.getTimestamp (7);
			uas.date_end = Cursor1.getTimestamp (8);
			
			ls.add (uas);
		}
		
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return ls;
	}
	
	//@PostMapping("/addUserHistory")
	public int addUserHistory (@RequestBody UserModel um) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection.prepareStatement (insert_query.query_insert_user_history);
		
		query.setString (1, um.extensions_user);
		query.setString (2, um.nama);
		query.setString (3, um.status);
		query.setString (4, um.status);
		query.setString (5, um.skill);
		query.setTimestamp (6, um.date_begin);
		query.setTimestamp (7, null);
		
		int result = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		
		return result;
	}
	
	//@PostMapping("/updateUserHistory")
	public int updateUserHistory (@RequestBody UserModel um) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection.prepareStatement (update_query.query_update_user_history);
		
		//System.out.print (um.date_end + "jncjndsjc");
		query.setTimestamp (1, um.date_end);
		query.setString (2, um.extensions_user);
		
		int result = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		
		return result;
	}


}
