package com.example.demo.controller;

import java.sql.Connection;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.Ps_AuthsModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.LoginQuery;
import com.example.demo.query.MenuUtamaQuery;
import com.example.demo.query.query_select_parameter;
import com.fasterxml.jackson.annotation.JsonFormat;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/user")
public class UserController
{
	LoginQuery loginquery = new LoginQuery();
	stringkoneksi sk = new stringkoneksi();
	MenuUtamaQuery menuUtamaQuery = new MenuUtamaQuery();

	@PostMapping("/login")
	public int postAuthsId(@RequestParam(name = "username") String username,
			@RequestParam(value = "password") String password)
	{
		int flag = 0;
		try
		{
			Connection connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement(loginquery.query_login0);
			query.setString(1, username);
			query.setString(2, password);
			ResultSet Cursor1 = query.executeQuery();
			// Cursor1.next();

			ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
			while (Cursor1.next())
			{
				UserModel Modeluser = new UserModel();
				Modeluser.user_id = Cursor1.getString(1);
				Modeluser.username = Cursor1.getString(2);
				Modeluser.extensions_user = Cursor1.getString(3);
				ListUser1.add(Modeluser);
			}

			if (ListUser1.size() > 0)
			{
				flag = 1;
			} else
			{
				flag = 0;
			}
			connection1.close();
		} catch (SQLException error)
		{
			error.printStackTrace();
		}
		return flag;
	}

	@PostMapping("/changeStatusId")
	public int changeStatusId(@RequestParam(name = "id") String id, @RequestParam(value = "status") String status)
	{
		int flag = 0;
		try
		{
			Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = Connection1.prepareStatement("SELECT * FROM users WHERE user_id=?");

			a.setString(1, id);
			ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
			ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
			while (Cursor1.next())
			{
				UserModel Modeluser = new UserModel();
				Modeluser.user_id = Cursor1.getString(1);
				ListUser1.add(Modeluser);
			}
			Connection1.close();
			
			if (ListUser1.size() > 0)
			{
				updateStatus(id, status);
				flag = 1;
			}
			else
			{
				flag = 0;
			}
			
		}
		catch (SQLException error)
		{
			error.printStackTrace();
			flag = 0;
		}

		return flag;
	}

	@PostMapping("/loginBody")
	public String postAuthsIdBody(@RequestBody UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(loginquery.query_login);

		a.setString(1, cfm.username);
		a.setString(2, cfm.password);
		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		while (Cursor1.next())
		{
			UserModel Modeluser = new UserModel();
			Modeluser.user_id = Cursor1.getString(1);
			Modeluser.username = Cursor1.getString(2);
			Modeluser.extensions_user = Cursor1.getString(3);
			ListUser1.add(Modeluser);
		}
		Connection1.close();

		if (ListUser1.size() > 0)
		{
			try
			{
				updateStatus(ListUser1.get(0).user_id, "1");
			} catch (Exception error)
			{
				error.printStackTrace();
			}
			return "{ " + "\"user_id\":"+"\""+ListUser1.get(0).user_id+"\" }";
		} else
		{
			return "{ " + "\"user_id\":"+"\""+"0"+"\" }";
		}

	}

	public void updateStatus(String id, String status) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement(loginquery.query_login2);

		a.setString(1, status);
		a.setString(2, id);
		a.executeUpdate();
		System.out.println(id);
	}

	@GetMapping("/getInitData")
	public ArrayList<UserModel> getInitData(@RequestBody UserModel cfm)
	{
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();
		try
		{
			Connection connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement(loginquery.query_login);
			ResultSet Cursor1 = query.executeQuery();
			while (Cursor1.next())
			{
				UserModel Modeluser = new UserModel();
				Modeluser.user_id = Cursor1.getString(1);
				Modeluser.username = Cursor1.getString(2);
				Modeluser.extensions_user = Cursor1.getString(3);
				ListUser1.add(Modeluser);
			}
			connection1.close();
		} catch (SQLException error)
		{
			error.printStackTrace();
		}
		return ListUser1;
	}

	@PostMapping("/logout")
	public String postAuthsLogout(@RequestBody UserModel cfm)
	{

		try
		{
				Connection connection = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
				PreparedStatement a = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");

				a.setString(1, cfm.user_id);
				ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
				List<UserModel> listModel = new ArrayList<UserModel>();
				
				while(Cursor1.next())
				{
					UserModel userModel = new UserModel();
					userModel.user_id = Cursor1.getString(1);
					listModel.add(userModel);
				}
				
				if (listModel.size() > 0)
				{
					updateStatus(cfm.user_id, "0");
					return "{ " + "\"response\":"+"\""+ "1" +"\" }";
				}
				else
				{
					return "{ " + "\"response\":"+"\""+ "0" +"\" }";
				}
				
				

		} catch (SQLException error)
		{
			error.printStackTrace();
			return "{ " + "\"response\":"+"\""+ "0" +"\" }";
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
		Modeluser.user_id = Cursor1.getString(1);
		Modeluser.nama = Cursor1.getString(2);
		Modeluser.username = Cursor1.getString(3);
		Modeluser.password = Cursor1.getString(4);
		Modeluser.created = Cursor1.getDate(5);
		Modeluser.modified = Cursor1.getDate(6);
		Modeluser.email = Cursor1.getString(7);
		Modeluser.password_email = Cursor1.getString(8);
		Modeluser.phone_number = Cursor1.getString(9);
		Modeluser.extensions_user = Cursor1.getString(10);
		Modeluser.skill = Cursor1.getString(11);
		Modeluser.status = Cursor1.getString(12);
		Modeluser.avatar = Cursor1.getString(13);
		ListUser1.add(Modeluser);
		Connection1.close();

		//
		return ListUser1;
	}

	@PostMapping("/editUserId")
	public int editUserId(@RequestBody UserModel cfm) // throws SQLException
	{
		int flag = 0;
		try
		{
			Connection connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement("UPDATE users "
					+ "SET nama=?, username=?, \"password\"=?, modified=?, email=?, password_email=?, phone_number=?, extensions_user=?, skill=?, status=?, avatar=? "
					+ "WHERE user_id=?;");
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
		} catch (SQLException error)
		{
			error.printStackTrace();
		}
		return flag;
	}

}
