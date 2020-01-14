package com.example.demo.controller;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

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
public class CdrController {
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

	@PutMapping(produces = "application/json", path = "/putCdr")
	public ResponseEntity<String> putCdr(@RequestBody CdrModel cfm) {
		try {
			String result = doPutCdr(cfm);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String doPutCdr(CdrModel cfm) throws SQLException {
		//Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
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
		queryinsert_cdr.setDate(12, cfm.end);
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
	public ArrayList<CdrModel> getCdr() throws SQLException {
		//Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
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
			ModelCdr.end = Cursor1.getDate(12);
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
	public int deleteCdr(@RequestBody CdrModel cfm) throws SQLException {
		//Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		querydelete_cdr = Connection1.prepareStatement(query_string_delete.query_delete_cdr);
		querydelete_cdr.setString(1, cfm.accountcode);
		int Cursor1 = querydelete_cdr.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;

		querydelete_cdr.close();
		Connection1.close();

		return a;
	}

	@PostMapping(produces = "application/json", path = "/postUserCdr")
	public ResponseEntity<String> postUserCdr(@RequestBody UserModel cfm) {
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

	public List<CdrModel> doPostUserCdr(UserModel cfm) throws SQLException {
		//Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		PreparedStatement queryselect_cdr = Connection1.prepareStatement(query_string2.query_get_user_cdr);
		queryselect_cdr.setInt(1, cfm.user_id);

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

	private String parseToStringJSON(ArrayList<String> field, ArrayList<String> values) {
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
