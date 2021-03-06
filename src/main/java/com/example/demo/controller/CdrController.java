package com.example.demo.controller;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;

import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.hibernate.criterion.NullExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cdr")
public class CdrController
{
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement queryinsert_cdr = null;
	PreparedStatement querydelete_cdr = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	AllSelectParameterQuery query_string2 = new AllSelectParameterQuery();
	PreparedStatement queryselect_cdr = null;

	@Autowired
	private DataSource dataSource;

	public CdrController(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@PutMapping(produces = "application/json", path = "/putCdr")
	public ResponseEntity<String> putCdr(@RequestBody CdrModel cfm)
	{
		try {
			String result = doPutCdr(cfm);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String doPutCdr(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryinsert_cdr = Connection1.prepareStatement(query_string_insert.query_insert_cdr);
		queryinsert_cdr.setString(1, cfm.accountcode);
		queryinsert_cdr.setString(2, cfm.src);
		queryinsert_cdr.setString(3, cfm.dst);
		queryinsert_cdr.setString(4, cfm.dcontext);
		queryinsert_cdr.setString(5, cfm.clid);
		queryinsert_cdr.setString(6, cfm.channel);
		queryinsert_cdr.setString(7, cfm.dstchannel);
		queryinsert_cdr.setString(8, cfm.lastapp);
		queryinsert_cdr.setString(9, cfm.lastdata);
		queryinsert_cdr.setTimestamp(10, cfm.start);
		queryinsert_cdr.setDate(11, cfm.answer);
		queryinsert_cdr.setTimestamp(12, cfm.end);
		queryinsert_cdr.setInt(13, cfm.duration);
		queryinsert_cdr.setInt(14, cfm.billsec);
		queryinsert_cdr.setString(15, cfm.disposition);
		queryinsert_cdr.setString(16, cfm.amaflags);
		queryinsert_cdr.setString(17, cfm.userfield);
		queryinsert_cdr.setString(18, cfm.uniqueid);
		queryinsert_cdr.setString(19, cfm.linkedid);
		queryinsert_cdr.setString(20, cfm.peeraccount);
		queryinsert_cdr.setInt(21, cfm.sequence);

		int flag = queryinsert_cdr.executeUpdate();// Evaluate (Connected_Expression1)

		queryinsert_cdr.close();
		Connection1.close();

		return "{ " + "\"response\":" + "\"" + flag + "\" }";

	}

	@GetMapping(produces = "application/json", path = "/getCdr")
	public ArrayList<CdrModel> getCdr() throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryselect_cdr = Connection1.prepareStatement(query_string.query_select_cdr);
		ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<CdrModel> ListUser1 = new ArrayList<CdrModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			CdrModel ModelCdr = new CdrModel();
			ModelCdr.accountcode = Cursor1.getString(1);
			ModelCdr.src = Cursor1.getString(2);
			ModelCdr.dst = Cursor1.getString(3);
			ModelCdr.dcontext = Cursor1.getString(4);
			ModelCdr.clid = Cursor1.getString(5);
			ModelCdr.channel = Cursor1.getString(6);
			ModelCdr.dstchannel = Cursor1.getString(7);
			ModelCdr.lastapp = Cursor1.getString(8); // YesNo value / Type.
			ModelCdr.lastdata = Cursor1.getString(9); // pjsip_connected_line_method value/type.
			ModelCdr.start = Cursor1.getTimestamp(10); // pjsip_connected_line_method value/type.
			ModelCdr.answer = Cursor1.getDate(11); // pjsip_direct_media_glare_mitigation value/Type.
			ModelCdr.end = Cursor1.getTimestamp(12);
			ModelCdr.duration = Cursor1.getInt(13); // pjsip_dtmf_mode value/Type.
			ModelCdr.billsec = Cursor1.getInt(14);
			ModelCdr.disposition = Cursor1.getString(15);
			ModelCdr.amaflags = Cursor1.getString(16);
			ModelCdr.userfield = Cursor1.getString(17);
			ModelCdr.uniqueid = Cursor1.getString(18);
			ModelCdr.linkedid = Cursor1.getString(19);
			ModelCdr.peeraccount = Cursor1.getString(20);
			ModelCdr.sequence = Cursor1.getInt(21);
			ListUser1.add(ModelCdr);

		}

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();

		return ListUser1;
	}

	@DeleteMapping(path = "/deleteCdr", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteCdr(@RequestBody CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		querydelete_cdr = Connection1.prepareStatement(query_string_delete.query_delete_cdr);
		querydelete_cdr.setString(1, cfm.accountcode);
		int Cursor1 = querydelete_cdr.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;

		querydelete_cdr.close();
		Connection1.close();

		return a;
	}

	@PostMapping("/afterCallCase")
	public String updateCustomer(@RequestBody CdrModel akun)
			throws SQLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection();
		PreparedStatement query = connection.prepareStatement(

				"UPDATE public.cdr SET \"case\"=? , detail=? WHERE (src like ? and dstchannel like ?) and \"case\" is null and detail is null and disposition = 'ANSWERED'; ");

		query.setString(1, akun.cdrCase);
		query.setString(2, akun.detail);
		query.setString(3, akun.src + "%");
		query.setString(4, "%" + akun.dst + "%");

		int flag = query.executeUpdate();

		query.close();
		connection.close();

		return "{ " + "\"response\":" + "\"" + "sukses" + "\" }";
	}

	@PostMapping(produces = "application/json", path = "/postUserCdr")
	public ResponseEntity<String> postUserCdr(@RequestBody CdrModel cfm)
	{
		try {
			List<CdrModel> result = doPostUserCdr(cfm);
			String parsedResult = "[\n\t";
			Locale locale = new Locale("us", "US");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			for (int i = 0; i < result.size(); i++) {

				ArrayList<String> formatedResultField = new ArrayList<String>();
				formatedResultField.add("nomor_telepon");
				formatedResultField.add("duration");
				formatedResultField.add("start");
				formatedResultField.add("disposition");

				ArrayList<String> formatedResultValues = new ArrayList<String>();
				formatedResultValues.add(result.get(i).src);
				formatedResultValues.add(String.valueOf(result.get(i).duration));

				formatedResultValues.add(dateFormat.format(result.get(i).start));
				formatedResultValues.add(result.get(i).disposition);
				parsedResult += parseToStringJSON(formatedResultField, formatedResultValues);
				if (result.size() - 1 > i) {
					parsedResult += ",\n";
				}
			}
			parsedResult += "]";

			if (result.size() > 0)
				return new ResponseEntity<String>(parsedResult, HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<CdrModel> doPostUserCdr(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		PreparedStatement queryselect_cdr = Connection1.prepareStatement(query_string2.query_get_user_cdr);
		queryselect_cdr.setString(1, "%" + cfm.dst + "%");

		ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
		List<CdrModel> ListUser1 = new ArrayList<CdrModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			CdrModel ModelCdr = new CdrModel();
			ModelCdr.src = Cursor1.getString(1);
			ModelCdr.duration = Cursor1.getInt(2);
			ModelCdr.start = Cursor1.getTimestamp(3);
			ModelCdr.disposition = Cursor1.getString(4);
			ListUser1.add(ModelCdr);
		}

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();
		return ListUser1;
	}

	@PostMapping(produces = "application/json", path = "/detailLaporanCdr")
	public ResponseEntity<String> laporanCdr(@RequestBody CdrModel cfm)
	{
		try {
			List<CdrModel> result = doLaporanCdr(cfm);
			String parsedResult = "[\n\t";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < result.size(); i++) {

				ArrayList<String> formatedResultField = new ArrayList<String>();
				formatedResultField.add("extension");
				formatedResultField.add("agentName");
				formatedResultField.add("start");
				formatedResultField.add("end");
				formatedResultField.add("duration");
				formatedResultField.add("disposition");
				formatedResultField.add("case");
				formatedResultField.add("detail");

				ArrayList<String> formatedResultValues = new ArrayList<String>();
				formatedResultValues.add(result.get(i).dst);
				formatedResultValues.add(result.get(i).dstchannel);
				formatedResultValues.add(dateFormat.format(result.get(i).start));
				formatedResultValues.add(dateFormat.format(result.get(i).end));
				formatedResultValues.add(String.valueOf(result.get(i).duration));
				formatedResultValues.add(result.get(i).disposition);
				formatedResultValues.add(result.get(i).cdrCase);
				formatedResultValues.add(result.get(i).detail);

				parsedResult += parseToStringJSON(formatedResultField, formatedResultValues);
				if (result.size() - 1 > i) {
					parsedResult += ",\n";
				}
			}
			parsedResult += "]";

			if (result.size() > 0)
				return new ResponseEntity<String>(parsedResult, HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<CdrModel> doLaporanCdr(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);

		if (cfm.tanggal1 == null) {

			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(

					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') " + "order by cdr.\"start\" desc;");
			queryselect_cdr.setString(1, cfm.extensions_user);
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<CdrModel> ListUser1 = new ArrayList<CdrModel>();
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				CdrModel ModelCdr = new CdrModel();
				ModelCdr.dst = Cursor1.getString(1);
				ModelCdr.dstchannel = Cursor1.getString(2);
				ModelCdr.start = Cursor1.getTimestamp(3);
				ModelCdr.end = Cursor1.getTimestamp(4);
				ModelCdr.duration = Cursor1.getInt(5);
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.cdrCase = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);

				ListUser1.add(ModelCdr);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		} else {
			Calendar c = Calendar.getInstance();
			java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
			c.setTime(sqlStartDate);
			c.add(Calendar.DATE, 1);

			cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());
			System.out.print(cfm.tanggal1 + "------" + cfm.tanggal2);

			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(
					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') and cdr.\"start\" between ? and ? "
							+ "order by cdr.\"start\" desc;");

			queryselect_cdr.setString(1, cfm.extensions_user);
			queryselect_cdr.setDate(2, cfm.tanggal1);
			queryselect_cdr.setDate(3, cfm.tanggal2);

			System.out.println("bbbbb");
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<CdrModel> ListUser1 = new ArrayList<CdrModel>();
			int count = 0;
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				CdrModel ModelCdr = new CdrModel();
				ModelCdr.dst = Cursor1.getString(1);
				ModelCdr.dstchannel = Cursor1.getString(2);
				ModelCdr.start = Cursor1.getTimestamp(3);
				ModelCdr.end = Cursor1.getTimestamp(4);
				ModelCdr.duration = Cursor1.getInt(5);
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.cdrCase = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);
				count++;
				ListUser1.add(ModelCdr);
			}
			System.out.println(count);
			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		}
	}

	public List<ACWJasper> doLaporanCdr1(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);

		if (cfm.tanggal1 == null) {

			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(
//					
					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') " + "order by cdr.\"start\" desc;");
			queryselect_cdr.setString(1, cfm.extensions_user);
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				ACWJasper ModelCdr = new ACWJasper();
				ModelCdr.extension = Cursor1.getString(1);
				ModelCdr.agent_name = Cursor1.getString(2);
				ModelCdr.start = String.valueOf(Cursor1.getTimestamp(3));
				ModelCdr.end = String.valueOf(Cursor1.getTimestamp(4));
				ModelCdr.duration = String.valueOf(Cursor1.getInt(5));
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.case1 = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);

				ListUser1.add(ModelCdr);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		} else {

			Calendar c = Calendar.getInstance();
			java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
			c.setTime(sqlStartDate);
			c.add(Calendar.DATE, 1);

			cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());
			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(

					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') and cdr.\"start\" between ? and ? "
							+ "order by cdr.\"start\" desc;");
			queryselect_cdr.setString(1, cfm.extensions_user);
			queryselect_cdr.setDate(2, cfm.tanggal1);
			queryselect_cdr.setDate(3, cfm.tanggal2);

			System.out.print("bbbbb");
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				ACWJasper ModelCdr = new ACWJasper();
				ModelCdr.extension = Cursor1.getString(1);
				ModelCdr.agent_name = Cursor1.getString(2);
				ModelCdr.start = String.valueOf(Cursor1.getTimestamp(3));
				ModelCdr.end = String.valueOf(Cursor1.getTimestamp(4));
				ModelCdr.duration = String.valueOf(Cursor1.getInt(5));
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.case1 = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);

				ListUser1.add(ModelCdr);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		}
	}

	@PostMapping(produces = "application/json", path = "/detailLaporanCdr2")
	public List<ACWJasper> doLaporanCdr2(@RequestBody CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);

		if (cfm.tanggal1 == null) {

			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(

					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') " + "order by cdr.\"start\" desc;");
			queryselect_cdr.setString(1, cfm.extensions_user);
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				ACWJasper ModelCdr = new ACWJasper();
				ModelCdr.extension = Cursor1.getString(1);
				ModelCdr.agent_name = Cursor1.getString(2);
				ModelCdr.start = String.valueOf(Cursor1.getTimestamp(3));
				ModelCdr.end = String.valueOf(Cursor1.getTimestamp(4));
				ModelCdr.duration = String.valueOf(Cursor1.getInt(5));
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.case1 = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);

				ListUser1.add(ModelCdr);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		} else {
			Calendar c = Calendar.getInstance();
			java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
			c.setTime(sqlStartDate);
			c.add(Calendar.DATE, 1);

			cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());

			Connection Connection1 = dataSource.getConnection();
			PreparedStatement queryselect_cdr = Connection1.prepareStatement(

					"SELECT users.extension_user as extensions, users.nama, cdr.\"start\" AS Start_Call, "
							+ "cdr.\"end\" AS End_Call, cdr.duration AS Duration, "
							+ "cdr.disposition AS Answered, case_type.\"case\", after_call_work.detail "
							+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
							+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
							+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
							+ "where cdr.dstchannel like concat('%/',?,'-%') and cdr.\"start\" between ? and ? "
							+ "order by cdr.\"start\" desc;");
			queryselect_cdr.setString(1, cfm.extensions_user);
			queryselect_cdr.setDate(2, cfm.tanggal1);
			queryselect_cdr.setDate(3, cfm.tanggal2);

			System.out.print("bbbbb");
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{
				ACWJasper ModelCdr = new ACWJasper();
				ModelCdr.extension = Cursor1.getString(1);
				ModelCdr.agent_name = Cursor1.getString(2);
				ModelCdr.start = String.valueOf(Cursor1.getTimestamp(3));
				ModelCdr.end = String.valueOf(Cursor1.getTimestamp(4));
				ModelCdr.duration = String.valueOf(Cursor1.getInt(5));
				if (Cursor1.getString(6).equalsIgnoreCase("ANSWERED")) {
					ModelCdr.disposition = "yes";
				} else {
					ModelCdr.disposition = "no";
				}
				ModelCdr.case1 = Cursor1.getString(7);
				ModelCdr.detail = Cursor1.getString(8);

				ListUser1.add(ModelCdr);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();
			return ListUser1;

		}
	}

	@PostMapping(produces = "application/json", path = "/rekapLaporanCdr")
	public ResponseEntity<String> laporanRekapCdr(@RequestBody CdrModel cfm)
	{
		try {
			List<CdrModel> result = doRekapCdr(cfm);
			String parsedResult = "[\n\t";
			System.out.print("ccccc" + cfm.tanggal1);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < result.size(); i++) {

				ArrayList<String> formatedResultField = new ArrayList<String>();
				formatedResultField.add("extension");
				formatedResultField.add("agentName");
				formatedResultField.add("start");
				formatedResultField.add("end");
				formatedResultField.add("duration");
				formatedResultField.add("totalCall");
				formatedResultField.add("totalAnswer");
				formatedResultField.add("totalUnanswer");

				ArrayList<String> formatedResultValues = new ArrayList<String>();
				formatedResultValues.add(result.get(i).dst);
				formatedResultValues.add(result.get(i).dstchannel);
				formatedResultValues.add(dateFormat.format(result.get(i).start));
				formatedResultValues.add(dateFormat.format(result.get(i).end));
				formatedResultValues.add(String.valueOf(result.get(i).duration));
				formatedResultValues.add(result.get(i).accountcode);
				formatedResultValues.add(result.get(i).amaflags);
				formatedResultValues.add(result.get(i).channel);

				parsedResult += parseToStringJSON(formatedResultField, formatedResultValues);
				if (result.size() - 1 > i) {
					parsedResult += ",\n";
				}
			}
			parsedResult += "]";

			if (result.size() > 0)
				return new ResponseEntity<String>(parsedResult, HttpStatus.OK);
			else
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<CdrModel> doRekapCdr(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);

		if (cfm.tanggal1 == null) {
			if (cfm.extensions_user == null) {
				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "GROUP BY users.extension_user, users.nama;");

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? " + "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);

					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "GROUP BY users.extension_user, users.nama;");

				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<CdrModel> ListUser1 = new ArrayList<CdrModel>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					CdrModel ModelCdr = new CdrModel();
					ModelCdr.dst = Cursor3.getString(1);
					ModelCdr.dstchannel = Cursor3.getString(2);
					ModelCdr.start = Timestamp.valueOf(agent[data][1]);
					ModelCdr.end = Timestamp.valueOf(agent[data][2]);
					ModelCdr.duration = duration[data];
					ModelCdr.accountcode = String.valueOf(Cursor3.getInt(3));
					ModelCdr.amaflags = String.valueOf(Cursor3.getInt(4));
					ModelCdr.channel = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			} else {
				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? GROUP BY users.extension_user, users.nama;");
				queryselect_cdr2.setString(1, cfm.extensions_user);
				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? " + "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);

					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setString(1, cfm.extensions_user);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<CdrModel> ListUser1 = new ArrayList<CdrModel>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					CdrModel ModelCdr = new CdrModel();
					ModelCdr.dst = Cursor3.getString(1);
					ModelCdr.dstchannel = Cursor3.getString(2);
					ModelCdr.start = Timestamp.valueOf(agent[data][1]);
					ModelCdr.end = Timestamp.valueOf(agent[data][2]);
					ModelCdr.duration = duration[data];
					ModelCdr.accountcode = String.valueOf(Cursor3.getInt(3));
					ModelCdr.amaflags = String.valueOf(Cursor3.getInt(4));
					ModelCdr.channel = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			}
		} else {
			if (cfm.extensions_user == null) {
				Calendar c = Calendar.getInstance();
				java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
				c.setTime(sqlStartDate);
				c.add(Calendar.DATE, 1);

				cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());

				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user is not null and cdr.\"start\" between ? and ? "
								+ "GROUP BY users.extension_user, users.nama;");

				queryselect_cdr2.setDate(1, cfm.tanggal1);
				queryselect_cdr2.setDate(2, cfm.tanggal2);

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user is not null and users.extension_user = ? and cdr.\"start\" between ? and ?  "
									+ "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					queryselect_cdr.setDate(2, cfm.tanggal1);
					queryselect_cdr.setDate(3, cfm.tanggal2);
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						
						agent[i][0] = Cursor1.getString(1);

						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);
					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user is not null and cdr.\"start\" between ? and ?   "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setDate(1, cfm.tanggal1);
				queryselect_cdr3.setDate(2, cfm.tanggal2);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<CdrModel> ListUser1 = new ArrayList<CdrModel>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					
					CdrModel ModelCdr = new CdrModel();
					ModelCdr.dst = Cursor3.getString(1);
					ModelCdr.dstchannel = Cursor3.getString(2);
					System.out.println(Cursor3.getString(1));
					ModelCdr.start = Timestamp.valueOf(agent[data][1]);
					ModelCdr.end = Timestamp.valueOf(agent[data][2]);
					ModelCdr.duration = duration[data];
					ModelCdr.accountcode = String.valueOf(Cursor3.getInt(3));
					ModelCdr.amaflags = String.valueOf(Cursor3.getInt(4));
					ModelCdr.channel = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			} else {
				Calendar c = Calendar.getInstance();
				java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
				c.setTime(sqlStartDate);
				c.add(Calendar.DATE, 1);

				cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());

				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? and cdr.\"start\" between ? and ? "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr2.setString(1, cfm.extensions_user);
				queryselect_cdr2.setDate(2, cfm.tanggal1);
				queryselect_cdr2.setDate(3, cfm.tanggal2);

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? and cdr.\"start\" between ? and ?  "
									+ "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					queryselect_cdr.setDate(2, cfm.tanggal1);
					queryselect_cdr.setDate(3, cfm.tanggal2);
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);
					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? and cdr.\"start\" between ? and ?   "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setString(1, cfm.extensions_user);
				queryselect_cdr3.setDate(2, cfm.tanggal1);
				queryselect_cdr3.setDate(3, cfm.tanggal2);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<CdrModel> ListUser1 = new ArrayList<CdrModel>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					CdrModel ModelCdr = new CdrModel();
					ModelCdr.dst = Cursor3.getString(1);
					ModelCdr.dstchannel = Cursor3.getString(2);
					ModelCdr.start = Timestamp.valueOf(agent[data][1]);
					ModelCdr.end = Timestamp.valueOf(agent[data][2]);
					ModelCdr.duration = duration[data];
					ModelCdr.accountcode = String.valueOf(Cursor3.getInt(3));
					ModelCdr.amaflags = String.valueOf(Cursor3.getInt(4));
					ModelCdr.channel = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			}
		}
	}

	public List<ACWJasper> doRekapCdr1(CdrModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);

		if (cfm.tanggal1 == null) {
			if (cfm.extensions_user == null) {
				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "GROUP BY users.extension_user, users.nama;");

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? " + "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);

					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "GROUP BY users.extension_user, users.nama;");

				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					ACWJasper ModelCdr = new ACWJasper();
					ModelCdr.extension = Cursor3.getString(1);
					ModelCdr.agent_name = Cursor3.getString(2);
					ModelCdr.start = agent[data][1];
					ModelCdr.end = agent[data][2];
					ModelCdr.duration = String.valueOf(duration[data]);
					ModelCdr.disposition = String.valueOf(Cursor3.getInt(3));
					ModelCdr.case1 = String.valueOf(Cursor3.getInt(4));
					ModelCdr.detail = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;

			} else {
				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? GROUP BY users.extension_user, users.nama;");
				queryselect_cdr2.setString(1, cfm.extensions_user);
				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? " + "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);

					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setString(1, cfm.extensions_user);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{
					ACWJasper ModelCdr = new ACWJasper();
					ModelCdr.extension = Cursor3.getString(1);
					ModelCdr.agent_name = Cursor3.getString(2);
					ModelCdr.start = agent[data][1];
					ModelCdr.end = agent[data][2];
					ModelCdr.duration = String.valueOf(duration[data]);
					ModelCdr.disposition = String.valueOf(Cursor3.getInt(3));
					ModelCdr.case1 = String.valueOf(Cursor3.getInt(4));
					ModelCdr.detail = String.valueOf(Cursor3.getInt(5));
					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			}
		} else {

			if (cfm.extensions_user == null) {
				Calendar c = Calendar.getInstance();
				java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
				c.setTime(sqlStartDate);
				c.add(Calendar.DATE, 1);

				cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());

				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where  users.extension_user is not null and cdr.\"start\" between ? and ? "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr2.setDate(1, cfm.tanggal1);
				queryselect_cdr2.setDate(2, cfm.tanggal2);

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where  users.extension_user is not null and users.extension_user = ? and cdr.\"start\" between ? and ?  "
									+ "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					queryselect_cdr.setDate(2, cfm.tanggal1);
					queryselect_cdr.setDate(3, cfm.tanggal2);
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);
					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where  users.extension_user is not null and cdr.\"start\" between ? and ?   "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setDate(1, cfm.tanggal1);
				queryselect_cdr3.setDate(2, cfm.tanggal2);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{

					ACWJasper ModelCdr = new ACWJasper();
					ModelCdr.extension = Cursor3.getString(1);
					ModelCdr.agent_name = Cursor3.getString(2);
					ModelCdr.start = agent[data][1];
					ModelCdr.end = agent[data][2];
					ModelCdr.duration = String.valueOf(duration[data]);
					ModelCdr.disposition = String.valueOf(Cursor3.getInt(3));
					ModelCdr.case1 = String.valueOf(Cursor3.getInt(4));
					ModelCdr.detail = String.valueOf(Cursor3.getInt(5));

					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			} else {
				Calendar c = Calendar.getInstance();
				java.sql.Date sqlStartDate = new java.sql.Date(cfm.tanggal2.getTime());
				c.setTime(sqlStartDate);
				c.add(Calendar.DATE, 1);

				cfm.tanggal2 = new java.sql.Date(c.getTimeInMillis());

				Connection Connection2 = dataSource.getConnection();
				PreparedStatement queryselect_cdr2 = Connection2
						.prepareStatement("SELECT users.extension_user as extensions, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? and cdr.\"start\" between ? and ? "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr2.setString(1, cfm.extensions_user);
				queryselect_cdr2.setDate(2, cfm.tanggal1);
				queryselect_cdr2.setDate(3, cfm.tanggal2);

				ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)

				int size = 0;
				List<String> ListUser3 = new ArrayList<String>();
				while (Cursor2.next()) // while there_is_next_record_in (Cursor1)
				{
					ListUser3.add(Cursor2.getString(1));
					size++;
				}

				String agent[][] = new String[size][3];
				int[] duration = new int[size];

				queryselect_cdr2.close();
				Cursor2.close();
				Connection2.close();
				int data = 0;
				for (int i = 0; i < size; i++) {
					Connection Connection1 = dataSource.getConnection();
					PreparedStatement queryselect_cdr = Connection1
							.prepareStatement("SELECT users.extension_user as extensions, " + "users.nama, "
									+ "cdr.\"start\" AS Start_Call, " + "cdr.\"end\" AS End_Call, "
									+ "cdr.duration AS Duration "
									+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
									+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
									+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
									+ "where users.extension_user = ? and cdr.\"start\" between ? and ?  "
									+ "order by cdr.\"start\" asc;");
					queryselect_cdr.setString(1, ListUser3.get(i));
					queryselect_cdr.setDate(2, cfm.tanggal1);
					queryselect_cdr.setDate(3, cfm.tanggal2);
					ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)

					while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
					{
						agent[i][0] = Cursor1.getString(1);
						if (agent[i][1] == null) {
							agent[i][1] = String.valueOf(Cursor1.getTimestamp(3));
						}
						agent[i][2] = String.valueOf(Cursor1.getTimestamp(4));
						duration[i] += Cursor1.getInt(5);
					}

					queryselect_cdr.close();
					Cursor1.close();
					Connection1.close();
				}

				Connection Connection3 = dataSource.getConnection();
				PreparedStatement queryselect_cdr3 = Connection3.prepareStatement(
						"SELECT users.extension_user as extensions, users.nama, " + "count(*) AS total_call, "
								+ "    sum(case when disposition = 'ANSWERED' then 1 else 0 end) AS tol_answer, "
								+ "    sum(case when disposition = 'NO ANSWER' then 1 else 0 end) AS tot_noanswer "
								+ "FROM cdr left join users on cdr.dstchannel like concat('%/',users.extension_user,'-%') "
								+ "left join after_call_work on cdr.uniqueid = after_call_work.uniqueid and cdr.dstchannel like concat('%/',after_call_work.dst,'-%') "
								+ "left join case_type on case_type.typecode = after_call_work.\"case\" "
								+ "where users.extension_user = ? and  cdr.\"start\" between ? and ?   "
								+ "GROUP BY users.extension_user, users.nama;");
				queryselect_cdr3.setString(1, cfm.extensions_user);
				queryselect_cdr3.setDate(2, cfm.tanggal1);
				queryselect_cdr3.setDate(3, cfm.tanggal2);
				ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
				System.out.print("aaaaa");
				List<ACWJasper> ListUser1 = new ArrayList<ACWJasper>();

				while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
				{

					ACWJasper ModelCdr = new ACWJasper();
					ModelCdr.extension = Cursor3.getString(1);
					ModelCdr.agent_name = Cursor3.getString(2);
					ModelCdr.start = agent[data][1];
					ModelCdr.end = agent[data][2];
					ModelCdr.duration = String.valueOf(duration[data]);
					ModelCdr.disposition = String.valueOf(Cursor3.getInt(3));
					ModelCdr.case1 = String.valueOf(Cursor3.getInt(4));
					ModelCdr.detail = String.valueOf(Cursor3.getInt(5));

					data++;
					ListUser1.add(ModelCdr);
				}

				queryselect_cdr3.close();
				Cursor3.close();
				Connection3.close();

				return ListUser1;
			}
		}
	}

	@PostMapping("/getCallLog")
	public ResponseEntity<List<InOutBoundModel>> getCdrBetween(@RequestBody CdrModel cm)
	{
		try {
			List<InOutBoundModel> result = doGetCdrBetweenStart(cm);

			if (result.size() > 0)
				return new ResponseEntity<List<InOutBoundModel>>(result, HttpStatus.OK);
			else
				return new ResponseEntity<List<InOutBoundModel>>(result, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<List<InOutBoundModel>>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/jwt")
	public String jwtw() throws NoSuchAlgorithmException, InvalidKeyException
	{
		String id = "4fac7oe940fc46cbb80c1d7e4651b58b";
		String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
		String claims = "{\"timestampInSeconds\":\"1592462301\", \"transactionId\":\"12345678\"}";
		String BasicBase64format = Base64.getEncoder().encodeToString(header.getBytes());
		String BasicBase64format2 = Base64.getEncoder().encodeToString(claims.getBytes());
		System.out.println(id + BasicBase64format + "." + BasicBase64format2);

		String secret = header + "." + claims;
		String message = "HWcQ1UYkOSYgBh10xWLMBCLEPw6R3549rAxV7LgfRk81";

		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);

		String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
		System.out.println(hash);

		String BasicBase64format3 = Base64.getEncoder().encodeToString(hash.getBytes());

		System.out.println(id + BasicBase64format + "." + BasicBase64format2 + "." + hash);

		System.out.println(id + BasicBase64format + "." + BasicBase64format2 + "." + BasicBase64format3);

		return id + BasicBase64format + "." + BasicBase64format2 + "." + BasicBase64format3;
	}

	public List<InOutBoundModel> doGetCdrBetweenStart(@RequestBody CdrModel cm) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement statement = con.prepareStatement(
				"select case when dst=? then ? when src=? then ? end as \"Type\", case when dst=? then src when src=? then dst end as \"Extension\" from cdr where (dst = ? or src = ?) and (\"start\" between ? and ?) order by \"start\" asc ;");
		// PreparedStatement statement = con.prepareStatement("select * from cdr where
		// (src = ?) and (\"start\" between ? and ?) order by \"start\" asc ");

		statement.setString(1, cm.extensions_user);
		statement.setString(2, "Inbound");
		statement.setString(3, cm.extensions_user);
		statement.setString(4, "Outbound");
		statement.setString(5, cm.extensions_user);
		statement.setString(6, cm.extensions_user);
		statement.setString(7, cm.extensions_user);
		statement.setString(8, cm.extensions_user);
		statement.setTimestamp(9, cm.start);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		try {
			if (cm.end.equals(null))
				statement.setTimestamp(10, timestamp);
			else
				statement.setTimestamp(10, cm.end);
		} catch (NullPointerException ex) {
			statement.setTimestamp(10, timestamp);
			System.out.println(timestamp);
		}

		ResultSet Cursor1 = statement.executeQuery();
		ArrayList<InOutBoundModel> ListUser1 = new ArrayList<InOutBoundModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			InOutBoundModel ModelCdr = new InOutBoundModel();
			ModelCdr.Type = Cursor1.getString(1);
			ModelCdr.Extension = Cursor1.getString(2);
			ListUser1.add(ModelCdr);
		}

		statement.close();
		Cursor1.close();
		con.close();

		return ListUser1;
	}

	private String parseToStringJSON(ArrayList<String> field, ArrayList<String> values)
	{
		String JSONHeader = "{\n\t";
		String JSONFooter = "\n}";
		String parsedJSON = "";
		String endLineJSON = ",\n\t";
		String quote = "\"";
		String equal = " : ";

		for (int i = 0; i < field.size(); i++) {
			if (i < field.size() - 1)
				parsedJSON += quote + field.get(i) + quote + equal + quote + values.get(i) + quote + endLineJSON;
			else
				parsedJSON += quote + field.get(i) + quote + equal + quote + values.get(i) + quote;
		}

		parsedJSON = JSONHeader + parsedJSON + JSONFooter;
		return parsedJSON;
	}

}

class InOutBoundModel
{
	public String Type;
	public String Extension;
}
