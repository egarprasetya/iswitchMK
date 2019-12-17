package com.example.demo.controller;

import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.UserModel;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/user")
public class UserController
{
	AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
	AllQuery select_query2 = new AllQuery ();
	AllUpdateQuery select_query3 = new AllUpdateQuery ();
	stringkoneksi sk = new stringkoneksi ();
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/register")
	public String register (@RequestBody UserModel akun) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement("INSERT INTO users (nama, user_id, username, password, password_email) VALUES (?,?,?,?,?);");
		
		//String encodedPassword = bCryptPasswordEncoder.encode(akun.getPassword());
		akun.password = bCryptPasswordEncoder.encode(akun.password);
		query.setString (1, akun.nama);
		query.setString(2, akun.user_id);
		query.setString(3, akun.username);
		query.setString(4, akun.password);
		query.setString(5, akun.password_email);
		int flag = query.executeUpdate();
		return String.valueOf(String.valueOf(flag) + " - Data pengguna ditambahkan!.");
	}
	
	@PostMapping("/login2")
	public ResponseEntity<String> login2 (@RequestBody UserModel akun)
	{
		try
		{
			String result = doLogin2(akun);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		catch (SQLException error_sql)
		{
			error_sql.printStackTrace();
			return new ResponseEntity<String>("500 - Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public String doLogin2 (UserModel akun) throws SQLException
	{
		boolean hasil = false;
		String log = "";
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection.prepareStatement("SELECT password FROM users WHERE username=?");
		
		query.setString(1, akun.username);
		
		ResultSet result = query.executeQuery();
		if(result.next())
		{
			String encodedPassword = result.getString(1);
			hasil = bCryptPasswordEncoder.matches(akun.password, encodedPassword);
			if (hasil)
			{
				log = "login berhasil!. selamat menikmati yang mantap-mantap :)";
			}
			else
			{
				log = "login gagal!. password salah!.";
			}
		}
		else
		{
			log = "login gagal!, username tidak ditemukan!.";
		}
		
		return log;
	}


	
	@PostMapping("/changeStatusId")
	public ResponseEntity<String> postChangeStatusId (@RequestBody UserModel cfm)
	{
		try
		{
			String result = doChangeStatusId (cfm);
			if (!result.equals (null))
			{
				return new ResponseEntity<String> (result, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity
//		      .status(HttpStatus.UNAUTHORIZED)
//		      .header("X-Reason", "user-invalid").body(body).build();
		}
		
	}
	
	public String doChangeStatusId (@RequestBody UserModel cfm) throws SQLException
	{
		int flag = 0;
		try
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = Connection1.prepareStatement (select_query3.query_changeStatus);
			
			a.setString (1, cfm.status);
			a.setString (2, cfm.user_id);
			flag = a.executeUpdate ();
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + error.getErrorCode () + "\" }";
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserModel> postLogin (@RequestBody UserModel cfm)
	{
		try
		{
			UserModel result = doLogin (cfm);
			if (!result.equals (null))
			{
				return new ResponseEntity<UserModel> (result, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<UserModel> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<UserModel> (HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity
//		      .status(HttpStatus.UNAUTHORIZED)
//		      .header("X-Reason", "user-invalid").body(body).build();
		}
		
	}
	
	public UserModel doLogin (UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query.query_login);
		
		a.setString (1, cfm.username);
		a.setString (2, cfm.password);
		try
		{
			updateStatus2 (cfm.username, cfm.password, "1");
			
		} catch (Exception error)
		{
			error.printStackTrace ();
		}
		ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		UserModel Modeluser = new UserModel ();
		if (Cursor1.next ())
		{
			Modeluser.nama = Cursor1.getString (1);
			Modeluser.user_id = Cursor1.getString (2);
			Modeluser.username = Cursor1.getString (3);
			Modeluser.password = Cursor1.getString (4);
			Modeluser.created = Cursor1.getTimestamp (5);
			Modeluser.modified = Cursor1.getTimestamp (6);
			Modeluser.email = Cursor1.getString (7);
			Modeluser.password_email = Cursor1.getString (8);
			Modeluser.phone_number = Cursor1.getString (9);
			Modeluser.extensions_user = Cursor1.getString (10);
			Modeluser.skill = Cursor1.getString (11);
			Modeluser.status = Cursor1.getString (12);
			Modeluser.avatar = Cursor1.getString (13);
			Connection1.close ();
			return Modeluser;
		} else
		{
			return null;
		}
		
	}
	
	public void updateStatus (String id, String status) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login2);
		
		a.setString (1, status);
		a.setString (2, id);
		a.executeUpdate ();
		System.out.println (id);
	}
	
	public void updateStatus2 (String username, String password, String status) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login3);
		
		a.setString (1, status);
		a.setString (2, username);
		a.setString (3, password);
		a.executeUpdate ();
		System.out.println (status);
	}
	
	@GetMapping("/getUser")
	public ArrayList<UserModel> getUser (@RequestBody UserModel cfm)
	{
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		try
		{
			Connection connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement (select_query2.query_select_users);
			ResultSet Cursor1 = query.executeQuery ();
			while (Cursor1.next ())
			{
				UserModel Modeluser = new UserModel ();
				Modeluser.nama = Cursor1.getString (1);
				Modeluser.user_id = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getString (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				ListUser1.add (Modeluser);
			}
			connection1.close ();
		} catch (SQLException error)
		{
			error.printStackTrace ();
		}
		return ListUser1;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutRespon (@RequestBody UserModel cfm)
	{
		try
		{
			String result = postAuthsLogout (cfm);
			if (!result.equals (null))
			{
				return new ResponseEntity<String> (result, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity
//		      .status(HttpStatus.UNAUTHORIZED)
//		      .header("X-Reason", "user-invalid").body(body).build();
		}
		
	}
	
	public String postAuthsLogout (@RequestBody UserModel cfm) throws SQLException
	{
		
		try
		{
			Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = connection.prepareStatement (select_query.query_logout);
			
			a.setString (1, cfm.user_id);
			ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
			List<UserModel> listModel = new ArrayList<UserModel> ();
			
			while (Cursor1.next ())
			{
				UserModel userModel = new UserModel ();
				userModel.user_id = Cursor1.getString (1);
				listModel.add (userModel);
			}
			
			if (listModel.size () > 0)
			{
				updateStatus (cfm.user_id, "0");
				return "{ " + "\"response\":" + "\"" + "1" + "\" }";
			} else
			{
				return "{ " + "\"response\":" + "\"" + "0" + "\" }";
			}
			
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	@PostMapping("/profil")
	public ResponseEntity<ArrayList<UserModel>> postAuthsProfil (@RequestBody UserModel cfm)
	{
		try
		{
			ArrayList<UserModel> result = postProfil (cfm);
			if (!result.equals (null))
			{
				return new ResponseEntity<ArrayList<UserModel>> (result, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<ArrayList<UserModel>> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<ArrayList<UserModel>> (HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity
//		      .status(HttpStatus.UNAUTHORIZED)
//		      .header("X-Reason", "user-invalid").body(body).build();
		}
		
	}
	
	public ArrayList<UserModel> postProfil (@RequestBody UserModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query.query_profil);
		
		a.setString (1, cfm.user_id);
		ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		Cursor1.next ();
		UserModel Modeluser = new UserModel ();
		Modeluser.nama = Cursor1.getString (1);
		Modeluser.user_id = Cursor1.getString (2);
		Modeluser.username = Cursor1.getString (3);
		Modeluser.password = Cursor1.getString (4);
		Modeluser.created = Cursor1.getTimestamp (5);
		Modeluser.modified = Cursor1.getTimestamp (6);
		Modeluser.email = Cursor1.getString (7);
		Modeluser.password_email = Cursor1.getString (8);
		Modeluser.phone_number = Cursor1.getString (9);
		Modeluser.extensions_user = Cursor1.getString (10);
		Modeluser.skill = Cursor1.getString (11);
		Modeluser.status = Cursor1.getString (12);
		Modeluser.avatar = Cursor1.getString (13);
		ListUser1.add (Modeluser);
		Connection1.close ();
		
		//
		return ListUser1;
	}
	
	@PostMapping("/editUserId")
	public ResponseEntity<String> editUserId (@RequestBody UserModel cfm)
	{
		try
		{
			String result = doEditUserId (cfm);
			if (!result.equals (null))
			{
				return new ResponseEntity<String> (result, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
//			return ResponseEntity
//		      .status(HttpStatus.UNAUTHORIZED)
//		      .header("X-Reason", "user-invalid").body(body).build();
		}
		
	}
	
	public String doEditUserId (@RequestBody UserModel cfm) throws SQLException
	{
		int flag = 0;
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now ();
			System.out.println (dtf.format (now));
			Connection connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection1.prepareStatement (select_query3.query_updateUserById);
			query.setString (1, cfm.nama);
			query.setString (2, cfm.username);
			query.setString (3, cfm.password);
			query.setString (4, cfm.email);
			query.setString (5, dtf.format (now).toString ());
			query.setString (6, cfm.password_email);
			query.setString (7, cfm.phone_number);
			query.setString (8, cfm.extensions_user);
			query.setString (9, cfm.skill);
			query.setString (10, cfm.status);
			query.setString (11, cfm.avatar);
			query.setString (12, cfm.user_id);
			
			flag = query.executeUpdate ();
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + error.getErrorCode () + "\" }";
		}
		
	}
	
}
