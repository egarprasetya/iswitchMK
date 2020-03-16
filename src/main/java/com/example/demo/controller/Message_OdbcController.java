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
import com.example.demo.model.Message_OdbcModel;
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
@RequestMapping(produces = "application/json", path = "/message")
public class Message_OdbcController
{
	AllSelectParameterQuery select_query = new AllSelectParameterQuery();
	AllQuery select_query2 = new AllQuery();
	AllUpdateQuery select_query3 = new AllUpdateQuery();
	stringkoneksi sk = new stringkoneksi();

	@Autowired
	private DataSource dataSource;

	@PostMapping("/endChat")
	public String endChat(@RequestBody Message_OdbcModel message) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection();
		PreparedStatement query = connection.prepareStatement("UPDATE message_odbc " + "SET flag_status='end' "
				+ "WHERE src=? or dst=?;" + "UPDATE users " + "SET status='2' " + "WHERE extension_user=?;");
		query.setString(1, message.src);
		query.setString(2, message.src);
		query.setString(3, message.src);

		int flag = query.executeUpdate();

		query.close();
		connection.close();

		return String.valueOf("{\"response\": \"sukses\"}");
	}

	@PostMapping("/historyChat")
	public String historyChat(@RequestBody Message_OdbcModel message) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection();
		PreparedStatement query = connection.prepareStatement("select * from message_odbc where (src = ? or dst = ?) "
				+ "and calldate between (now() - interval '12 hour') and now() order by calldate");
		query.setString(1, message.src);
		query.setString(2, message.src);

		ResultSet Cursor1 = query.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Message_OdbcModel> ListUser1 = new ArrayList<Message_OdbcModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Message_OdbcModel ModelCdr = new Message_OdbcModel();
			ModelCdr.serial_id = Cursor1.getString(1);
			ModelCdr.uniqueid = Cursor1.getString(2);
			ModelCdr.calldate = Cursor1.getString(3);
			ModelCdr.src = Cursor1.getString(4);
			ModelCdr.dst = Cursor1.getString(5);
			ModelCdr.msg_context = Cursor1.getString(6);
			ModelCdr.flag_status = Cursor1.getString(7);

			ListUser1.add(ModelCdr);
		}
		query.close();
		connection.close();

		Connection connection2 = dataSource.getConnection();
		PreparedStatement query2 = connection2
				.prepareStatement("select src from message_odbc where (src = ? or dst = ?) "
						+ "and calldate between (now() - interval '12 hour') and now() group by src");
		query2.setString(1, message.src);
		query2.setString(2, message.src);

		ResultSet Cursor12 = query2.executeQuery();// Evaluate (Connected_Expression1)
		int size = 0;
		while (Cursor12.next()) {
			size++;
		}
		query2.close();
		connection2.close();

//		Connection connection3 = dataSource.getConnection();
//		PreparedStatement query3 = connection3
//				.prepareStatement("select src from message_odbc where (src = ? or dst = ?) "
//						+ "and calldate between (now() - interval '12 hour') and now() group by src");
//		query3.setString(1, message.src);
//		query3.setString(2, message.src);
//		
//		ResultSet Cursor13 = query3.executeQuery();// Evaluate (Connected_Expression1)
//
//		
		int count = 0;
		String[] customer = new String[size - 1];
//		while (Cursor13.next()) // while there_is_next_record_in (Cursor1)
//		{
//			if (!Cursor13.getString(1).equalsIgnoreCase(message.src)) {
//				customer[count] = Cursor13.getString(1);
//				count++;
//			}
//		}
//		query3.close();
//		connection3.close();

		Connection connection4 = dataSource.getConnection();
		PreparedStatement query4 = connection4
				.prepareStatement("select src from message_odbc where (src = ? or dst = ?) "
						+ "and calldate between (now() - interval '12 hour') and now() order by calldate desc");
		query4.setString(1, message.src);
		query4.setString(2, message.src);

		ResultSet Cursor14 = query.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Message_OdbcModel> ListUser14 = new ArrayList<Message_OdbcModel>();
		while (Cursor14.next()) // while there_is_next_record_in (Cursor1)
		{
			Message_OdbcModel ModelCdr = new Message_OdbcModel();

			ModelCdr.src = Cursor1.getString(1);
			if (!ModelCdr.src.equalsIgnoreCase(message.src)) {
				for (int i = 0; i < customer.length; i++) {
					if (!ModelCdr.src.equalsIgnoreCase(customer[i])) {
						customer[count] = ModelCdr.src;

						count++;
					}
				}

			}

		}

		query.close();
		connection.close();
		String result = "{\n\t";
		for (int j = 0; j < customer.length; j++) {
			result += "\"" + customer[j] + "\": [\n\t\t";
			for (int i = 1; i < ListUser1.size(); i++) {
				Message_OdbcModel ModelCdr = new Message_OdbcModel();
				ModelCdr = ListUser1.get(i);
				System.out.println(ListUser1.size());

				System.out.println(ModelCdr.src);
				System.out.println(customer[j]);
				System.out.println(customer.length);

				if (customer[j].equalsIgnoreCase(ModelCdr.src)) {
					result += "{\n\t\t\t\"targetId\" : \"" + customer[j] + "\",";
					result += "\n\t\t\t\"message\" : \"" + ModelCdr.msg_context + "\",";
					result += "\n\t\t\t\"timeString\" : \"" + ModelCdr.calldate + "\",";
					result += "\n\t\t\t\"type\" : \"" + "RECEIVED" + "\"\n\t\t},";

				}
				if (customer[j].equalsIgnoreCase(ModelCdr.dst)) {
					result += "{\n\t\t\t\"targetId\" : \"" + customer[j] + "\",";
					result += "\n\t\t\t\"message\" : \"" + ModelCdr.msg_context + "\",";
					result += "\n\t\t\t\"timeString\" : \"" + ModelCdr.calldate + "\",";
					result += "\n\t\t\t\"type\" : \"" + "SENT" + "\"\n\t\t},";

				}
				if (ListUser1.size() - 1 == i) {
					result = result.substring(0, result.length() - 1);
				}

			}
			if (customer.length - 1 == j) {
				result += "\n\t]\n\t";
			} else {

				result += "\n\t],\n\t";
			}
		}
		result += "}";
		return result;
	}
}