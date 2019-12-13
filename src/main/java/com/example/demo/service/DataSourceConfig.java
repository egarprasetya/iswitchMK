package com.example.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserModel;
import com.example.demo.query.AllSelectParameterQuery;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/coba")
@Transactional
@Repository
public class DataSourceConfig
{
	public DataSource dataSource = null;

	AllSelectParameterQuery select_query = new AllSelectParameterQuery();
	
	public DataSourceConfig(DataSource dataSource) throws SQLException
	{
		this.dataSource = dataSource;
	}

	@PostMapping("/loginBody")
	public ArrayList<UserModel> postAuthsIdBody(@RequestBody UserModel cfm) throws SQLException
	{
//		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		System.out.print(dataSource);
		Connection Connection1 = dataSource.getConnection();
		PreparedStatement a = Connection1.prepareStatement(select_query.query_login);
		a.setString(1, cfm.username);
		a.setString(2, cfm.password);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		while (Cursor1.next())
		{
			UserModel Modeluser = new UserModel();
			Modeluser.nama = Cursor1.getString(1);
			Modeluser.user_id = Cursor1.getString(2);
			Modeluser.username = Cursor1.getString(3);
			Modeluser.password = Cursor1.getString(4);
			Modeluser.created = Cursor1.getTimestamp(5);
			Modeluser.modified = Cursor1.getTimestamp(6);
			Modeluser.email = Cursor1.getString(7);
			Modeluser.password_email = Cursor1.getString(8);
			Modeluser.phone_number = Cursor1.getString(9);
			Modeluser.extensions_user = Cursor1.getString(10);
			Modeluser.skill = Cursor1.getString(11);
			Modeluser.status = Cursor1.getString(12);
			Modeluser.avatar = Cursor1.getString(13);
			ListUser1.add(Modeluser);
		}
		Connection1.close();

		if (ListUser1.size() > 0)
		{
			try
			{
//				updateStatus(ListUser1.get(0).user_id, "1");

			} catch (Exception error)
			{
				error.printStackTrace();
			}
			return ListUser1;
		} else
		{
			return ListUser1;
		}
	}

}