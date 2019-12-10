package com.example.demo.controller;

import com.example.demo.Enum.YesNo_Values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.Date;
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
@RequestMapping("/users")
public class UsersController
{
	PreparedStatement querydelete_users = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	SettingProfilQuery query_string = new SettingProfilQuery();
	PreparedStatement queryselect_users = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_users = null;

	@PostMapping("/getUserId")
	public ArrayList<UserModel> getUserId(@RequestBody UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(query_string.query_setting_profil);
		a.setString(1, cfm.user_id);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)

		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			UserModel Modelusers = new UserModel();

			Modelusers.nama = Cursor1.getString(1);
			Modelusers.username = Cursor1.getString(2);
			Modelusers.password = Cursor1.getString(3);
//			Modelusers.phone_number = Cursor1.getString(4);
			Modelusers.status = Cursor1.getString(5);
			Modelusers.avatar = Cursor1.getString(6);

			ListUser1.add(Modelusers);

		}
		Connection1.close();
		return ListUser1;
	}

}
