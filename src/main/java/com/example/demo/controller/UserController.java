package com.example.demo.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.example.demo.Enum.pjsip_auth_type_values;
import com.example.demo.config.RestTempleteConfig;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.CdrModel;
import com.example.demo.model.Queue_MemberModel;
import com.example.demo.model.Queue_MemberModel2;
import com.example.demo.model.UserModel;
import com.example.demo.model.User_HistoryModel;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.demo.controller.User_HistoryController;

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
	
	@Autowired
	private DataSource dataSource;
//	
//	public UserController(DataSource ds)
//	{
//		this.ds = ds;
//	}
	
	@PostMapping("/registerAgent")
	public String register (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (
				
				"INSERT INTO users (nama, username, password, password_email, \"skill\", status, extension_user) VALUES (?,?,?,?,?,?,?); "
						+ "INSERT INTO public.ps_aors " + "(id, max_contacts, remove_existing) " + "VALUES(?, '1', 'yes'); "
						+ "INSERT INTO public.ps_endpoints "
						+ "(id, transport, aors, auth, context, disallow, allow, direct_media, dtmf_mode, ice_support, use_avpf, media_encryption,  dtls_verify, dtls_cert_file, dtls_ca_file, dtls_setup, message_context, media_use_received_transport, rtcp_mux,max_audio_streams,max_video_streams,rewrite_contact,rtp_symmetric,force_rport) "
						+ "VALUES(?, 'transport-wss-lokal', ?, ?, 'testing', 'all', 'ulaw', 'no', 'auto', 'yes', 'yes', 'dtls', 'fingerprint', '/etc/asterisk/keys/asterisk.pem', '/etc/asterisk/keys/ca.crt', 'actpass', 'messaging', 'yes', 'yes',10,10,'no', 'no','no'); "
						+ "INSERT INTO public.ps_auths " + "(id, auth_type, \"password\", username) "
						+ "VALUES(?, 'userpass', 'mk1234', ?);");
		String rawPassword = akun.password;
		// String encodedPassword = bCryptPasswordEncoder.encode(akun.getPassword());
		akun.password = bCryptPasswordEncoder.encode (akun.password);
		query.setString (1, akun.nama);
		query.setString (2, akun.username);
		query.setString (3, akun.password);
		query.setString (4, akun.password_email);
		query.setInt (5, akun.skill);
		query.setString (6, akun.status);
		query.setString (7, akun.extensions_user);
		
		query.setString (8, akun.extensions_user);
		query.setString (9, akun.extensions_user);
		query.setString (10, akun.extensions_user);
		query.setString (11, akun.extensions_user);
		query.setString (12, akun.extensions_user);
		query.setString (13, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		Queue_MemberController qmc = new Queue_MemberController (dataSource);
		Queue_MemberModel qm = new Queue_MemberModel ();
		UserModel agent = new UserModel ();
		
		agent = getUserData (akun);
		qm._interface = "PJSIP/" + agent.extensions_user;
		qm.extension = "PJSIP/" + agent.extensions_user;
		qm.queue_name = agent.queue;
		qm.state_interface = agent.status;
		qmc.deleteQueueMember (qm);
		qmc.addQueueMember (qm);
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna ditambahkan!.");
	}
	@PostMapping("/registerAgent2")
	public String registerAgent2 (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (
				
				"INSERT INTO users (\"skill\",extension_user) VALUES (?,?); "
						+ "INSERT INTO public.ps_aors " + "(id, max_contacts, remove_existing) " + "VALUES(?, '1', 'yes'); "
						+ "INSERT INTO public.ps_endpoints "
						+ "(id, transport, aors, auth, context, disallow, allow, direct_media, dtmf_mode, ice_support, use_avpf, media_encryption,  dtls_verify, dtls_cert_file, dtls_ca_file, dtls_setup, message_context, media_use_received_transport, rtcp_mux,max_audio_streams,max_video_streams,rewrite_contact,rtp_symmetric,force_rport) "
						+ "VALUES(?, 'transport-wss-lokal', ?, ?, 'testing', 'all', 'ulaw', 'no', 'auto', 'yes', 'yes', 'dtls', 'fingerprint', '/etc/asterisk/keys/asterisk.pem', '/etc/asterisk/keys/ca.crt', 'actpass', 'messaging', 'yes', 'yes',10,10,'no', 'no','no'); "
						+ "INSERT INTO public.ps_auths " + "(id, auth_type, \"password\", username) "
						+ "VALUES(?, 'userpass', 'mk1234', ?);");

		query.setInt (1, akun.skill);
		query.setString (2, akun.extensions_user);
		
		query.setString (3, akun.extensions_user);
		query.setString (4, akun.extensions_user);
		query.setString (5, akun.extensions_user);
		query.setString (6, akun.extensions_user);
		query.setString (7, akun.extensions_user);
		query.setString (8, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		Queue_MemberController qmc = new Queue_MemberController (dataSource);
		Queue_MemberModel qm = new Queue_MemberModel ();
		UserModel agent = new UserModel ();
		
		agent = getUserData (akun);
		qm._interface = "PJSIP/" + agent.extensions_user;
		qm.extension = "PJSIP/" + agent.extensions_user;
		qm.queue_name = agent.queue;
		qmc.deleteQueueMember (qm);
		qmc.addQueueMember (qm);
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna ditambahkan!.");
	}
	
	@PostMapping("/deleteAgent")
	public String deleteAgent (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (
				"DELETE FROM public.users " + "WHERE extension_user=?; " + "DELETE FROM public.ps_aors " + "WHERE id=?; "
						+ "DELETE FROM  public.ps_auths " + "WHERE id=?; " + "DELETE FROM public.ps_endpoints " + "WHERE id=?; "
		
		);
		
		query.setString (1, akun.extensions_user);
		
		query.setString (2, akun.extensions_user);
		query.setString (3, akun.extensions_user);
		query.setString (4, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();

		Queue_MemberController qmc = new Queue_MemberController (dataSource);
		Queue_MemberModel qm = new Queue_MemberModel ();
		UserModel agent = new UserModel ();
		
		agent = getUserData (akun);
		qm._interface = "PJSIP/" + agent.extensions_user;
		qm.extension = "PJSIP/" + agent.extensions_user;
		qm.queue_name = agent.queue;
		qm.state_interface = agent.status;
		qmc.deleteQueueMember (qm);
		qmc.addQueueMember (qm);
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna delete!.");
	}
	
	@PostMapping("/updateAgent")
	public String updateAgent (@RequestBody UserModel akun) throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection
				.prepareStatement ("UPDATE public.users " + "SET extension_user=? " + "WHERE extension_user=?; " +
						
						"UPDATE public.ps_aors " + "SET id=? " + "WHERE id=?; " +
						
						"UPDATE public.ps_auths " + "SET id=?, username=?" + "WHERE id=?;" +
						
						"UPDATE public.ps_endpoints " + "SET id=?, aors=?, auth=? " + "WHERE id=?; ");
		
		query.setString (1, akun.extensions_user_baru);
		
		query.setString (2, akun.extensions_user);
		query.setString (3, akun.extensions_user_baru);
		query.setString (4, akun.extensions_user);
		query.setString (5, akun.extensions_user_baru);
		query.setString (6, akun.extensions_user_baru);
		query.setString (7, akun.extensions_user);
		query.setString (8, akun.extensions_user_baru);
		query.setString (9, akun.extensions_user_baru);
		query.setString (10, akun.extensions_user_baru);
		query.setString (11, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		Queue_MemberController qmc = new Queue_MemberController (dataSource);
		Queue_MemberModel qm = new Queue_MemberModel ();
		UserModel agent = new UserModel ();
		
		agent = getUserData (akun);
		qm._interface = "PJSIP/" + agent.extensions_user;
		qm.extension = "PJSIP/" + agent.extensions_user;
		qm.queue_name = agent.queue;
		qm.state_interface = agent.status;
		qmc.deleteQueueMember (qm);
		qmc.addQueueMember (qm);
		getPjsipReload ();
		return String.valueOf (String.valueOf (flag) + " - Data pengguna extension update!.");
	}
	
	public UserModel getUserData (@RequestBody UserModel cfm) throws SQLException
	{
		UserModel Modeluser = new UserModel ();
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement a = connection.prepareStatement (select_query.query_agent);
			
			a.setString (1, cfm.extensions_user);
			ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
			
			while (Cursor1.next ())
			{
				// System.out.println (Cursor1.getString (1));
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				Modeluser.websocket = Cursor1.getString (14);
				Modeluser.url_websocket = Cursor1.getString (15);
				Modeluser.queue = Cursor1.getString (16);
				
			}
			
			if (Modeluser.user_id != 0)
			{
				// System.out.println (Modeluser.user_id + "ssddas");
				updateStatus (cfm.user_id, "0");
				a.close ();
				Cursor1.close ();
				connection.close ();
				return Modeluser;
			} else
			{
				// System.out.println (Modeluser.extensions_user);
				a.close ();
				Cursor1.close ();
				connection.close ();
				return null;
			}
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return Modeluser;
		}
		
	}
	
	@PostMapping("/registerCustomer")
	public String registerCustomer (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement ("INSERT INTO customers (extension) VALUES (?); "
				+ "INSERT INTO public.ps_aors " + "(id, max_contacts, remove_existing) " + "VALUES(?, '1', 'yes'); "
				+ "INSERT INTO public.ps_endpoints "
				+ "(id, aors, auth, context, disallow, allow, direct_media, dtmf_mode, ice_support, tos_video,cos_video,force_rport,rewrite_contact,rtp_symmetric) "
				+ "VALUES(?, ?, ?, 'testing', 'all', 'opus,ulaw,vp8', 'no', 'rfc4733', 'yes','af41',4,'no','no','no'); "
				+ "INSERT INTO public.ps_auths " + "(id, auth_type, \"password\", username) "
				+ "VALUES(?, 'userpass', 'mk1234', ?);");
		String rawPassword = akun.password;
		// String encodedPassword = bCryptPasswordEncoder.encode(akun.getPassword());
		akun.password = bCryptPasswordEncoder.encode (akun.password);
		query.setString (1, akun.extensions_user);
		
		query.setString (2, akun.extensions_user);
		query.setString (3, akun.extensions_user);
		query.setString (4, akun.extensions_user);
		query.setString (5, akun.extensions_user);
		query.setString (6, akun.extensions_user);
		query.setString (7, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna ditambahkan!.");
	}
	
	@PostMapping("/deleteCustomer")
	public String deleteCustomer (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (
				"DELETE FROM public.customers " + "WHERE extension=?; " + "DELETE FROM public.ps_aors " + "WHERE id=?; "
						+ "DELETE FROM  public.ps_auths " + "WHERE id=?; " + "DELETE FROM public.ps_endpoints " + "WHERE id=?; "
		
		);
		
		query.setString (1, akun.extensions_user);
		
		query.setString (2, akun.extensions_user);
		query.setString (3, akun.extensions_user);
		query.setString (4, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna delete!.");
	}
	
	@PostMapping("/updateCustomer")
	public String updateCustomer (@RequestBody UserModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection
				.prepareStatement ("UPDATE public.customers " + "SET extension=? " + "WHERE extension=?; " +
						
						"UPDATE public.ps_aors " + "SET id=? " + "WHERE id=?; " +
						
						"UPDATE public.ps_auths " + "SET id=?, username=?" + "WHERE id=?;" +
						
						"UPDATE public.ps_endpoints " + "SET id=?, aors=?, auth=? " + "WHERE id=?; ");
		
		query.setString (1, akun.extensions_user_baru);
		
		query.setString (2, akun.extensions_user);
		query.setString (3, akun.extensions_user_baru);
		query.setString (4, akun.extensions_user);
		query.setString (5, akun.extensions_user_baru);
		query.setString (6, akun.extensions_user_baru);
		query.setString (7, akun.extensions_user);
		query.setString (8, akun.extensions_user_baru);
		query.setString (9, akun.extensions_user_baru);
		query.setString (10, akun.extensions_user_baru);
		query.setString (11, akun.extensions_user);
		int flag = query.executeUpdate ();
		
		query.close ();
		connection.close ();
		getPjsipReload ();
		
		return String.valueOf (String.valueOf (flag) + " - Data pengguna extension update!.");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody UserModel cfm)
	{
		try
		{
			UserModel result = doLogin (cfm);
			
			ArrayList<String> formatedResultField = new ArrayList<String> ();
			formatedResultField.add ("user_id");
			formatedResultField.add ("extension_user");
			formatedResultField.add ("websocket");
			formatedResultField.add ("url_websocket");
			
			ArrayList<String> formatedResultValues = new ArrayList<String> ();
			formatedResultValues.add (String.valueOf (result.user_id));
			formatedResultValues.add (result.extensions_user);
			formatedResultValues.add (result.websocket);
			formatedResultValues.add (result.url_websocket);
			
			String parsedResult = parseToStringJSON (formatedResultField, formatedResultValues);
			
			if (!result.equals (null))
			{
				Queue_MemberController qmc = new Queue_MemberController (dataSource);
				User_HistoryController uhc = new User_HistoryController (dataSource);
				User_ActivityController uac = new User_ActivityController (dataSource);
				UserModel um = new UserModel ();
				um = result;
				Queue_MemberModel qm = new Queue_MemberModel ();
				qm._interface = "PJSIP/" + um.extensions_user;
				qm.extension = "PJSIP/" + um.extensions_user;
				qm.queue_name = um.queue;
				qm.state_interface = um.status;
				
				qmc.deleteQueueMember (qm);
				qmc.addQueueMember (qm);
				um.date_end = cfm.date_begin;
				uhc.updateUserHistory (um);
				
				um.date_begin = cfm.date_begin;
				uhc.addUserHistory (um);
//				
//				System.out.
				
				uac.postUpdateUserActivity (um);
				
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
	
	public UserModel doLogin (UserModel cfm) throws SQLException
	{
		boolean hasil = false;
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement a = Connection1.prepareStatement (select_query.query_login);
		
		a.setString (1, cfm.username);
		
		ResultSet Cursor1 = a.executeQuery ();
		if (Cursor1.next ())
		{
			String encodedPassword = Cursor1.getString (4);
			hasil = bCryptPasswordEncoder.matches (cfm.password, encodedPassword);
			if (hasil)
			{
				
				// System.out.println (cfm.password + encodedPassword);
				UserModel Modeluser = new UserModel ();
				System.out.println (Cursor1.getInt (1));
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				Modeluser.websocket = Cursor1.getString (14);
				Modeluser.url_websocket = Cursor1.getString (15);
				Modeluser.queue = Cursor1.getString (16);
				
				a.close ();
				Cursor1.close ();
				Connection1.close ();
				// System.out.println (cfm.password + encodedPassword);
				return Modeluser;
			} else
			{
				a.close ();
				Cursor1.close ();
				Connection1.close ();
				return null;
			}
		} else
		{
			a.close ();
			Cursor1.close ();
			Connection1.close ();
			return null;
		}
		
	}
	
	@PostMapping("/changeStatusId")
	public ResponseEntity<String> postChangeStatusId (@RequestBody UserModel cfm)
	{
		try
		{
			UserModel result = doChangeStatusId (cfm);
			if (!result.equals (null))
			{
				User_HistoryController uhc = new User_HistoryController (dataSource);
				User_ActivityController uac = new User_ActivityController (dataSource);
				UserModel um = new UserModel ();
				
				um = result;
				um.date_end = cfm.date_begin;
				uhc.updateUserHistory (um);
				
				um.date_begin = cfm.date_begin;
				uhc.addUserHistory (um);
				
				uac.postUpdateUserActivity (um);
				
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.NOT_FOUND);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			error_null.printStackTrace ();
			return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public UserModel doChangeStatusId (@RequestBody UserModel cfm) throws SQLException
	{
		int flag = 0;
		UserModel Modeluser = new UserModel ();
		try
		{
			// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection ();
			PreparedStatement a = Connection1.prepareStatement (select_query3.query_changeStatus);
			
			a.setString (1, cfm.status);
			a.setInt (2, cfm.user_id);
			flag = a.executeUpdate ();
			
			a.close ();
			Connection1.close ();
			
			// Connection Connection2 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection2 = dataSource.getConnection ();
			PreparedStatement b = Connection2.prepareStatement (select_query.query_logout);
			
			b.setInt (1, cfm.user_id);
			ResultSet Cursor1 = b.executeQuery ();// Evaluate (Connected_Expression1)
			
			while (Cursor1.next ())
			{
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				Modeluser.websocket = Cursor1.getString (14);
				Modeluser.url_websocket = Cursor1.getString (15);
				
			}
			b.close ();
			Cursor1.close ();
			
			Connection2.close ();
			
			if (Modeluser.status.equals ("0") || Modeluser.status.equals ("2") || Modeluser.status.equals ("3")
					|| Modeluser.status.equals ("4"))
			{
				Queue_MemberController qmc = new Queue_MemberController (dataSource);
				Queue_MemberModel qm = new Queue_MemberModel ();
				
				qm.extension = Modeluser.extensions_user;
				qm.paused = 1;
				
				qmc.updatePaused (qm);
			} else
			{
				Queue_MemberController qmc = new Queue_MemberController (dataSource);
				Queue_MemberModel qm = new Queue_MemberModel ();
				
				qm.extension = Modeluser.extensions_user;
				qm.paused = 0;
				
				qmc.updatePaused (qm);
			}
			
			return Modeluser;
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return Modeluser;
		}
	}
	
	public void updateStatus (int id, String status) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login2);
		
		a.setString (1, status);
		a.setInt (2, id);
		a.executeUpdate ();
		
		a.close ();
		Connection1.close ();
		// System.out.println (id);
	}
	
	public void updateStatus2 (String username, String status) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement a = Connection1.prepareStatement (select_query3.query_login3);
		
		a.setString (1, status);
		a.setString (2, username);
		a.executeUpdate ();
		
		a.close ();
		Connection1.close ();
		// System.out.println (status);
	}
	
	@GetMapping("/getUser")
	public ArrayList<UserModel> getUser (@RequestBody UserModel cfm)
	{
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		try
		{
			// Connection connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection1 = dataSource.getConnection ();
			PreparedStatement query = connection1.prepareStatement (select_query2.query_select_users);
			ResultSet Cursor1 = query.executeQuery ();
			while (Cursor1.next ())
			{
				UserModel Modeluser = new UserModel ();
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				ListUser1.add (Modeluser);
			}
			
			query.close ();
			Cursor1.close ();
			connection1.close ();
		} catch (SQLException error)
		{
			error.printStackTrace ();
		}
		return ListUser1;
	}
	
	@GetMapping("/getUserById")
	public ArrayList<UserModel> getUserById (@RequestBody UserModel cfm)
	{
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		try
		{
			// Connection connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection1 = dataSource.getConnection ();
			PreparedStatement query = connection1.prepareStatement ("select * from users where user_id=?");
			query.setInt (1, cfm.user_id);
			
			ResultSet Cursor1 = query.executeQuery ();
			while (Cursor1.next ())
			{
				UserModel Modeluser = new UserModel ();
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				ListUser1.add (Modeluser);
			}
			
			query.close ();
			Cursor1.close ();
			connection1.close ();
		} catch (SQLException error)
		{
			error.printStackTrace ();
		}
		return ListUser1;
	}
	
	@GetMapping("/getUserByExtension")
	public ArrayList<UserModel> getUserByExtension (@RequestBody UserModel cfm)
	{
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		try
		{
			// Connection connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection1 = dataSource.getConnection ();
			PreparedStatement query = connection1.prepareStatement ("select * from users where extension_user=?");
			query.setString (1, cfm.extensions_user);
			
			ResultSet Cursor1 = query.executeQuery ();
			while (Cursor1.next ())
			{
				UserModel Modeluser = new UserModel ();
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				ListUser1.add (Modeluser);
			}
			
			query.close ();
			Cursor1.close ();
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
			UserModel result = postAuthsLogout (cfm);
			
			if (!result.equals (null))
			{
				Queue_MemberController qmc = new Queue_MemberController (dataSource);
				User_HistoryController uhc = new User_HistoryController (dataSource);
				User_ActivityController uac = new User_ActivityController (dataSource);
				UserModel um = new UserModel ();
				
				um = result;
				
				Queue_MemberModel qm = new Queue_MemberModel ();
				qm.extension = "PJSIP/" + um.extensions_user;
				qm.queue_name = um.queue;
				qm.state_interface = um.status;
				
				qmc.deleteQueueMember (qm);
				// System.out.print (cfm.extensions_user + "sslalalal");
				um.date_end = cfm.date_begin;
				uhc.updateUserHistory (um);
				
				um.date_begin = cfm.date_begin;
				uhc.addUserHistory (um);
				
				uac.postUpdateUserActivity (um);
				
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public UserModel postAuthsLogout (@RequestBody UserModel cfm) throws SQLException
	{
		UserModel Modeluser = new UserModel ();
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement a = connection.prepareStatement (select_query.query_logout);
			
			a.setInt (1, cfm.user_id);
			ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
			
			while (Cursor1.next ())
			{
				// System.out.println (Cursor1.getString (1));
				Modeluser.user_id = Cursor1.getInt (1);
				Modeluser.nama = Cursor1.getString (2);
				Modeluser.username = Cursor1.getString (3);
				Modeluser.password = Cursor1.getString (4);
				Modeluser.created = Cursor1.getTimestamp (5);
				Modeluser.modified = Cursor1.getTimestamp (6);
				Modeluser.email = Cursor1.getString (7);
				Modeluser.password_email = Cursor1.getString (8);
				Modeluser.phone_number = Cursor1.getString (9);
				Modeluser.extensions_user = Cursor1.getString (10);
				Modeluser.skill = Cursor1.getInt (11);
				Modeluser.status = Cursor1.getString (12);
				Modeluser.avatar = Cursor1.getString (13);
				Modeluser.websocket = Cursor1.getString (14);
				Modeluser.url_websocket = Cursor1.getString (15);
				
			}
			
			if (Modeluser.user_id != 0)
			{
				// System.out.println (Modeluser.user_id + "ssddas");
				updateStatus (cfm.user_id, "0");
				a.close ();
				Cursor1.close ();
				connection.close ();
				return Modeluser;
			} else
			{
				// System.out.println (Modeluser.extensions_user);
				a.close ();
				Cursor1.close ();
				connection.close ();
				return null;
			}
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return Modeluser;
		}
		
	}
	
	@PostMapping("/getProfil")
	public ResponseEntity<String> postAuthsProfil (@RequestBody UserModel cfm)
	{
		try
		{
			ArrayList<UserModel> result;
			if (cfm.user_id == 0)
				throw new NullPointerException ();
			else
				result = postProfil (cfm);
			
			String parsedResult = "[\n\t ";
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
					parsedResult += ",\n ";
				}
			}
			parsedResult += " ]";
			
			if (result.size () > 0)
				return new ResponseEntity<String> (parsedResult, HttpStatus.OK);
			else
				return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NullPointerException error_id)
		{
			error_id.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ArrayList<UserModel> postProfil (@RequestBody UserModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement a = Connection1.prepareStatement (select_query.query_profil);
		
		a.setInt (1, cfm.user_id);
		ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		Cursor1.next ();
		UserModel Modeluser = new UserModel ();
		Modeluser.nama = Cursor1.getString (1);
		Modeluser.status = Cursor1.getString (2);
		Modeluser.avatar = Cursor1.getString (3);
		ListUser1.add (Modeluser);
		
		a.close ();
		Cursor1.close ();
		Connection1.close ();
		
		return ListUser1;
	}
	
	public ArrayList<UserModel> postUserData (@RequestBody UserModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement a = Connection1.prepareStatement (select_query.query_profil);
		
		a.setInt (1, cfm.user_id);
		ResultSet Cursor1 = a.executeQuery ();// Evaluate (Connected_Expression1)
		ArrayList<UserModel> ListUser1 = new ArrayList<UserModel> ();
		Cursor1.next ();
		UserModel Modeluser = new UserModel ();
		Modeluser.user_id = Cursor1.getInt (1);
		Modeluser.nama = Cursor1.getString (2);
		Modeluser.username = Cursor1.getString (3);
		Modeluser.password = Cursor1.getString (4);
		Modeluser.created = Cursor1.getTimestamp (5);
		Modeluser.modified = Cursor1.getTimestamp (6);
		Modeluser.email = Cursor1.getString (7);
		Modeluser.password_email = Cursor1.getString (8);
		Modeluser.phone_number = Cursor1.getString (9);
		Modeluser.extensions_user = Cursor1.getString (10);
		Modeluser.skill = Cursor1.getInt (11);
		Modeluser.status = Cursor1.getString (12);
		Modeluser.avatar = Cursor1.getString (13);
		ListUser1.add (Modeluser);
		
		a.close ();
		Cursor1.close ();
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
			// Connection connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection1 = dataSource.getConnection ();
			PreparedStatement query = connection1.prepareStatement (select_query3.query_updateUserById);
			
			query.setString (1, cfm.nama);
			query.setString (2, cfm.username);
			query.setString (3, cfm.password);
			query.setString (4, cfm.email);
			query.setString (5, dtf.format (now).toString ());
			query.setString (6, cfm.password_email);
			query.setString (7, cfm.phone_number);
			query.setInt (8, cfm.skill);
			query.setString (9, cfm.status);
			query.setString (10, cfm.avatar);
			query.setInt (11, cfm.user_id);
			
			flag = query.executeUpdate ();
			
			query.close ();
			connection1.close ();
			
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
			if (doUpdatePasswordSingleBody (data))
			{
				result = "{ " + "\"response\":" + "\"" + "1" + "\" }";
				return new ResponseEntity<String> (result, HttpStatus.OK);
			} else
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
	
	public boolean doUpdatePasswordSingleBody (UserModel userModel) throws SQLException
	{
		UserModel usr = new UserModel ();
		usr.user_id = userModel.user_id;
		usr.password = userModel.old_password;
		
		if (passwordChecker (usr))
		{
			// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection ();
			PreparedStatement query = Connection1.prepareStatement (select_query3.query_updatePassword);
			
			userModel.password = bCryptPasswordEncoder.encode (userModel.password);
			
			query.setString (1, userModel.password);
			query.setInt (2, usr.user_id);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			Connection1.close ();
			
			return true;
		} else
		{
			return false;
		}
		
	}
	
	public boolean passwordChecker (UserModel userModel)
	{
		String encodedPassword = "";
		try
		{
			// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection ();
			PreparedStatement a = Connection1.prepareStatement (select_query.query_password);
			
			a.setInt (1, userModel.user_id);
			
			ResultSet Cursor1 = a.executeQuery ();
			if (Cursor1.next ())
			{
				encodedPassword = Cursor1.getString (1);
				// System.out.println(userModel.password + " | "+ encodedPassword);
				
				a.close ();
				Cursor1.close ();
				Connection1.close ();
				return bCryptPasswordEncoder.matches (userModel.password, encodedPassword);
			} else
			{
				Connection1.close ();
				throw new SQLException ("Not Found");
			}
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return false;
		}
		
	}
	
	@PostMapping(produces = "application/json", path = "/postUserByStatusSkill")
	public ResponseEntity<String> postUserCdr (@RequestBody Queue_MemberModel cfm)
	{
		try
		{
			List<UserModel> result = doUserByStatusSkill (cfm);
			String parsedResult = "[\n\t";
			Locale locale = new Locale ("us", "US");
			DateFormat dateFormat = DateFormat.getTimeInstance (DateFormat.DEFAULT, locale);
			for (int i = 0; i < result.size (); i++)
			{
				
				ArrayList<String> formatedResultField = new ArrayList<String> ();
				formatedResultField.add ("user_id");
				formatedResultField.add ("nama");
				formatedResultField.add ("username");
				formatedResultField.add ("password");
				formatedResultField.add ("created");
				formatedResultField.add ("modified");
				formatedResultField.add ("email");
				formatedResultField.add ("password_email");
				formatedResultField.add ("phone_number");
				formatedResultField.add ("extensions_user");
				formatedResultField.add ("skill");
				formatedResultField.add ("status");
				formatedResultField.add ("avatar");
				
				ArrayList<String> formatedResultValues = new ArrayList<String> ();
				formatedResultValues.add (String.valueOf (result.get (i).user_id));
				formatedResultValues.add (result.get (i).nama);
				formatedResultValues.add (result.get (i).username);
				formatedResultValues.add (result.get (i).password);
				formatedResultValues.add (result.get (i).created.toString ());
				formatedResultValues.add (result.get (i).modified.toString ());
				formatedResultValues.add (result.get (i).email);
				formatedResultValues.add (result.get (i).password_email);
				formatedResultValues.add (result.get (i).phone_number);
				formatedResultValues.add (result.get (i).extensions_user);
				formatedResultValues.add (String.valueOf (result.get (i).skill));
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
	
	public List<UserModel> doUserByStatusSkill (Queue_MemberModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection ();
		PreparedStatement queryselect_cdr = Connection1.prepareStatement (select_query.query_user_by_status_skill);
		queryselect_cdr.setString (1, cfm.queue_name);
		
		ResultSet Cursor1 = queryselect_cdr.executeQuery ();// Evaluate (Connected_Expression1)
		List<UserModel> ListUser1 = new ArrayList<UserModel> ();
		while (Cursor1.next ()) // while there_is_next_record_in (Cursor1)
		{
			UserModel Modeluser = new UserModel ();
			Modeluser.user_id = Cursor1.getInt (1);
			Modeluser.nama = Cursor1.getString (2);
			Modeluser.username = Cursor1.getString (3);
			Modeluser.password = Cursor1.getString (4);
			Modeluser.created = Cursor1.getTimestamp (5);
			Modeluser.modified = Cursor1.getTimestamp (6);
			Modeluser.email = Cursor1.getString (7);
			Modeluser.password_email = Cursor1.getString (8);
			Modeluser.phone_number = Cursor1.getString (9);
			Modeluser.extensions_user = Cursor1.getString (10);
			Modeluser.skill = Cursor1.getInt (11);
			Modeluser.status = Cursor1.getString (12);
			Modeluser.avatar = Cursor1.getString (13);
			ListUser1.add (Modeluser);
		}
		
		queryselect_cdr.close ();
		Cursor1.close ();
		Connection1.close ();
		return ListUser1;
	}
	
	@GetMapping("/pjsipReload")
	public ResponseEntity<String> getPjsipReload ()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// RestTempleteConfig.disableSslVerification();
		RestTemplate restTemplate = new RestTempleteConfig ().getRestTemplate ();
		String uri = "https://127.0.0.1:8089/amxml?action=command&Command=pjsip reload";
		ResponseEntity<String> entity = restTemplate.exchange (uri, HttpMethod.GET, null, String.class);
		
		String str_result = entity.getBody ();
		
		if (str_result.contains ("Success"))
			return new ResponseEntity<String> (HttpStatus.OK);
		else if (str_result.contains ("Error"))
			return new ResponseEntity<String> (HttpStatus.EXPECTATION_FAILED);
		else
		{
			System.out.println (str_result);
			return new ResponseEntity<String> (HttpStatus.GONE);
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
		// System.out.println(parsedJSON);
		return parsedJSON;
	}
	
}
