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
				.prepareStatement ("INSERT INTO users (nama, user_id, username, password, password_email, extension_user) VALUES (?,?,?,?,?,?);"
						+ "	INSERT INTO ps_auths (id, password) VALUES (?,?);");
		String rawPassword = akun.password;
		// String encodedPassword = bCryptPasswordEncoder.encode(akun.getPassword());
		akun.password = bCryptPasswordEncoder.encode (akun.password);
		query.setString (1, akun.nama);
		query.setString (2, akun.user_id);
		query.setString (3, akun.username);
		query.setString (4, akun.password);
		query.setString (5, akun.password_email);
		query.setString (6, akun.extensions_user);
		
		query.setString (7, akun.extensions_user);
		query.setString (8, rawPassword);
		int flag = query.executeUpdate ();
		return String.valueOf (String.valueOf (flag) + " - Data pengguna ditambahkan!.");
	}

	@PostMapping("/login")

	public ResponseEntity<String> login2 (@RequestBody UserModel cfm)
	{
		try
		{
			UserModel result = doLogin (cfm);

			ArrayList<String> formatedResultField = new ArrayList<String> ();
			formatedResultField.add ("user_id");
			formatedResultField.add ("websocket");
			formatedResultField.add ("url_websocket");

			ArrayList<String> formatedResultValues = new ArrayList<String> ();
			formatedResultValues.add (result.user_id);
			formatedResultValues.add (result.websocket);
			formatedResultValues.add (result.url_websocket);

			String parsedResult = parseToStringJSON (formatedResultField, formatedResultValues);

			if (!result.equals (null))
			{
				return new ResponseEntity<String> (parsedResult, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			error_null.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.UNAUTHORIZED);
		}
	}

//	public ResponseEntity<UserModel> login2 (@RequestBody UserModel cfm)
//	{
//		try
//		{
//			UserModel result = doLogin (cfm);
//			if (!result.equals (null))
//			{
//				return new ResponseEntity<UserModel> (result, HttpStatus.OK);
//			} else
//			{
//				return new ResponseEntity<UserModel> (HttpStatus.INTERNAL_SERVER_ERROR);
//			} 
//		} catch (SQLException | NullPointerException error_null)
//		{
//			return new ResponseEntity<UserModel> (HttpStatus.UNAUTHORIZED);
//		}
//	}

	public UserModel doLogin (UserModel cfm) throws SQLException
	{
		boolean hasil = false;
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query.query_login);

		a.setString (1, cfm.username);

		ResultSet Cursor1 = a.executeQuery ();
		if (Cursor1.next ())
		{
			String encodedPassword = Cursor1.getString (4);
			hasil = bCryptPasswordEncoder.matches (cfm.password, encodedPassword);
			if (hasil)
			{
				try
				{
					updateStatus2 (cfm.username, "1");

				} catch (Exception error)
				{
					error.printStackTrace ();
				}
				//System.out.println (cfm.password + encodedPassword);
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
				Modeluser.websocket = Cursor1.getString (14);
				Modeluser.url_websocket = Cursor1.getString (15);
				Connection1.close ();
				//System.out.println (cfm.password + encodedPassword);
				return Modeluser;
			} else
			{
				return null;
			}
		} else
		{
			return null;
		}

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

	public void updateStatus (String id, String status) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login2);

		a.setString (1, status);
		a.setString (2, id);
		a.executeUpdate ();
		//System.out.println (id);
	}

	public void updateStatus2 (String username, String status) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login3);

		a.setString (1, status);
		a.setString (2, username);
		a.executeUpdate ();
		//System.out.println (status);
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
	public ResponseEntity<String> postAuthsProfil (@RequestBody UserModel cfm)
	{
		try
		{
			ArrayList<UserModel> result = postProfil (cfm);
			String parsedResult = "[\n\t";
			for (int i = 0; i < result.size (); i++)
			{
				
				ArrayList<String> formatedResultField = new ArrayList<String> ();
				formatedResultField.add ("nama");
				formatedResultField.add ("status");
				formatedResultField.add ("avatar");
				
				ArrayList<String> formatedResultValues = new ArrayList<String> ();
				formatedResultValues.add (result.get (i).nama);
				formatedResultValues.add (result.get (i).status);
				formatedResultValues.add (result.get (i).avatar);
				parsedResult += parseToStringJSON (formatedResultField, formatedResultValues);
				if (result.size () - 1 > i)
				{
					parsedResult += ",\n";
				}
			}
			parsedResult += "]";
			
			if (result.size () > 0)
				return new ResponseEntity<String> (parsedResult, HttpStatus.OK);
			else
				return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
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
		Modeluser.status = Cursor1.getString (2);
		Modeluser.avatar = Cursor1.getString (3);
		ListUser1.add (Modeluser);
		Connection1.close ();

		return ListUser1;
	}
	
	public ArrayList<UserModel> postUserData (@RequestBody UserModel cfm) throws SQLException
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
			// //System.out.println (dtf.format (now));
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
	
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword (@RequestBody UserModel data)
	{
		try
		{
			String result;
			if(doUpdatePasswordSingleBody (data))
			{
				result = "{ " + "\"response\":" + "\"" + "1" + "\" }";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			}
			else
			{
				result = "{ " + "\"response\":" + "\"" + "0" + "\" }";
				return new ResponseEntity<String> (result, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("/updatePasswordMix")
	public ResponseEntity<String> updatePasswordMix (@RequestBody UserModel dataLama, @RequestParam String passBaru)
	{
		try
		{
			String result;
			if(doUpdatePasswordMix (dataLama, passBaru))
			{
				result = "Password diubah!.";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			}
			else
			{
				result = "Password tidak cocok!.";
				return new ResponseEntity<String> (result, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("/updatePasswordMix2")
	public ResponseEntity<String> updatePasswordMix2 (@RequestParam String user_id, @RequestParam String passLama, @RequestBody UserModel userModel)
	{
		try
		{
			String result;
			if(doUpdatePasswordMix2 (user_id, passLama, userModel))
			{
				result = "Password diubah!.";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			}
			else
			{
				result = "Password tidak cocok!.";
				return new ResponseEntity<String> (result, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@GetMapping("/updatePasswordParam")
	public ResponseEntity<String> updatePasswordParam (@RequestParam String id, @RequestParam String passLama, @RequestParam String passBaru)
	{
		try
		{
			String result;
			if(doUpdatePasswordParam (id, passLama, passBaru))
			{
				result = "Password diubah!.";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			}
			else
			{
				result = "Password tidak cocok!.";
				return new ResponseEntity<String> (result, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/updatePasswordBody")
	public ResponseEntity<String> updatePasswordBody (@RequestBody UserModel data[])
	{
		try
		{
			String result;
			if(doUpdatePasswordBody (data[0], data[1]))
			{
				result = "Password diubah!.";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			}
			else
			{
				result = "Password tidak cocok!.";
				return new ResponseEntity<String> (result, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	public boolean doUpdatePasswordSingleBody(UserModel userModel) throws SQLException
	{
		UserModel usr = new UserModel();
		usr.user_id = userModel.user_id;
		usr.password = userModel.old_password;
		
		if(passwordChecker (usr))
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);

			userModel.password = bCryptPasswordEncoder.encode (userModel.password);
			
			query.setString (1, userModel.password);
			query.setString (2, usr.user_id);
			int flag = query.executeUpdate ();
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	public boolean doUpdatePasswordMix(UserModel dataLama, String passBaru) throws SQLException
	{
		
		if(passwordChecker (dataLama))
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);

			passBaru = bCryptPasswordEncoder.encode (passBaru);
			
			query.setString (1, passBaru);
			query.setString (2, dataLama.user_id);
			int flag = query.executeUpdate ();
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public boolean doUpdatePasswordMix2(String user_id, String passLama, UserModel userModel) throws SQLException
	{
		UserModel usr = new UserModel();
		usr.user_id = user_id;
		usr.password = passLama;
		
		if(passwordChecker (usr))
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);

			userModel.password = bCryptPasswordEncoder.encode (userModel.password);
			
			query.setString (1, userModel.password);
			query.setString (2, usr.user_id);
			int flag = query.executeUpdate ();
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public boolean doUpdatePasswordParam(String user_id, String passLama, String passBaru) throws SQLException
	{
		UserModel usr = new UserModel();
		usr.user_id = user_id;
		usr.password = passLama;
		
		if(passwordChecker (usr))
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);

			passBaru = bCryptPasswordEncoder.encode (passBaru);
			
			query.setString (1, passBaru);
			query.setString (2, usr.user_id);
			int flag = query.executeUpdate ();
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public boolean doUpdatePasswordBody(UserModel dataLama, UserModel dataBaru) throws SQLException
	{
		if(passwordChecker (dataLama))
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);

			dataBaru.password = bCryptPasswordEncoder.encode (dataBaru.password);
			
			query.setString (1, dataBaru.password);
			query.setString (2, dataBaru.user_id);
			int flag = query.executeUpdate ();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean passwordChecker (UserModel userModel)
	{
		String encodedPassword = "";
		try
		{
			Connection Connection1 = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement a = Connection1.prepareStatement (select_query.query_password);

			a.setString (1, userModel.user_id);

			ResultSet Cursor1 = a.executeQuery ();
			if (Cursor1.next ())
			{
				encodedPassword = Cursor1.getString (1);
				//System.out.println(userModel.password + "  |  "+ encodedPassword);
				return bCryptPasswordEncoder.matches (userModel.password, encodedPassword);
			}
			else
			{
				throw new SQLException ("Not Found");
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return false;
		}

	}

	private String parseToStringJSON (ArrayList<String> field, ArrayList<String> values)
	{
		String JSONHeader = "{\n\t";
		String JSONFooter = "\n}";
		String parsedJSON = "";
		String endLineJSON = ",\n\t";
		String quote = "\"";
		String equal = " : ";

		for (int i = 0; i < field.size (); i++)
		{
			if (i < field.size () - 1)
				parsedJSON += quote + field.get (i) + quote + equal + quote + values.get (i) + quote + endLineJSON;
			else
				parsedJSON += quote + field.get (i) + quote + equal + quote + values.get (i) + quote;
		}

		parsedJSON = JSONHeader + parsedJSON + JSONFooter;
		//System.out.println(parsedJSON);
		return parsedJSON;
	}

}
