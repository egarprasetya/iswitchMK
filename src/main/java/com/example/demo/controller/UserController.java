package com.example.demo.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.Ps_AuthsModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.LoginQuery;
import com.example.demo.query.MenuUtamaQuery;
import com.example.demo.query.query_select_parameter;

@RestController
@RequestMapping(produces = "application/json", path = "/user")
public class UserController
{
	LoginQuery loginquery = new LoginQuery();
	stringkoneksi sk = new stringkoneksi();
	MenuUtamaQuery menuUtamaQuery = new MenuUtamaQuery();
	
	@PostMapping("/login")
	public ArrayList<UserModel> postAuthsId(@RequestBody UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(loginquery.query_login);

		a.setString(1, cfm.username);
		a.setString(2, cfm.password);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		Cursor1.next();
		UserModel Modeluser = new UserModel();
		Modeluser.user_id = Cursor1.getString(1);
		Modeluser.username = Cursor1.getString(2);
		Modeluser.extensions_user = Cursor1.getString(3);
		ListUser1.add(Modeluser);
		Connection1.close();
		try
		{
			updateStatus(ListUser1.get(0).user_id, "1");
		} catch (Exception error)
		{
			error.printStackTrace();
		}
		//
		return ListUser1;
	}

	public void updateStatus(String id, String status)
	{
		try
		{
			Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = Connection1.prepareStatement(loginquery.query_login2);

			a.setString(1, status);
			a.setString(2, id);
			a.executeUpdate();
		} catch (SQLException error)
		{
			error.printStackTrace();
		}

	}
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

	@PostMapping("/profil")
	public ArrayList<UserModel> postDashboardProfil(@RequestBody UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(menuUtamaQuery.query_profil);

		a.setString(1, cfm.user_id);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		Cursor1.next();
		UserModel Modeluser = new UserModel();
		Modeluser.nama = Cursor1.getString(1);
		Modeluser.status = Cursor1.getString(2);
		Modeluser.avatar = Cursor1.getString(3);
		ListUser1.add(Modeluser);
		Connection1.close();
		
		//
		return ListUser1;
	}
	
	@PostMapping("/editUserStatus")
	public int editUserStatus (@RequestBody UserModel cfm) //throws SQLException
	{
		int flag = 0;
		try
		{
			Connection connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement("UPDATE users " + 
					"SET nama=?, username=?, \"password\"=?, modified=?, email=?, password_email=?, phone_number=?, extensions_user=?, skill=?, status=?, avatar=? " + 
					"WHERE user_id=?;");
			query.setString(1, cfm.nama);
			query.setString(2, cfm.username);
			query.setString(3, cfm.password);
			query.setDate(4, cfm.modified);
			query.setString(5, cfm.email);
			query.setString(6, cfm.password_email);
			query.setLong(7, Long.parseLong(cfm.phone_number));
			query.setString(8, cfm.extensions_user);
			query.setString(9, cfm.skill);
			query.setString(10, cfm.status);
			query.setString(11, cfm.avatar);
			query.setString(12, cfm.user_id);
			
			flag = query.executeUpdate();
			return flag;
		}
		catch (SQLException error)
		{
			error.printStackTrace();
		}
		return flag;
	}
	
}
