package com.example.demo.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.After_Call_WorkModel;
import com.example.demo.model.CaseTypeModel;
import com.example.demo.model.CdrModel;
import com.example.demo.query.AllDeleteQuery;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/after_call_work")
public class After_Call_WorkController
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

	@PostMapping(produces = "application/json", path = "/insertACW")
	public ResponseEntity<String> insertACW(@RequestBody After_Call_WorkModel cfm)
	{
		try {
			String result = doInsertACW(cfm);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (SQLException error_sql) {
			error_sql.printStackTrace();
			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String doInsertACW(After_Call_WorkModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryinsert_cdr = Connection1.prepareStatement("INSERT INTO public.after_call_work " + 
				"(src, dst, \"case\", detail) " + 
				"VALUES(?, ?, ?, ?);");
		queryinsert_cdr.setString(1, cfm.src);
		queryinsert_cdr.setString(2, cfm.dst);
		queryinsert_cdr.setString(3, cfm.case1);
		queryinsert_cdr.setString(3, cfm.detail);
		

		int flag = queryinsert_cdr.executeUpdate();// Evaluate (Connected_Expression1)

		queryinsert_cdr.close();
		Connection1.close();

		return "{ " + "\"response\":" + "\"" + flag + "\" }";

	}

	@PostMapping(produces = "application/json", path = "/updateUniqueid")
	public String updateUniqueid(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryselect_cdr = Connection1.prepareStatement("select uniqueid from cdr where src = ? and dst = ? order by \"end\" desc limit 1;");
		queryselect_cdr.setString(1, cfm.src);
		queryselect_cdr.setString(2, cfm.dst);
		ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<CdrModel> ListUser1 = new ArrayList<CdrModel>();
		String result="";
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			
			result = Cursor1.getString(1);
		}

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();
		
		Connection Connection2 = dataSource.getConnection();
		PreparedStatement queryselect_cdr2 = Connection2.prepareStatement("UPDATE public.after_call_work " + 
				"SET uniqueid = ? " + 
				"where src = ? and dst = ? and uniqueid is null;");
		queryselect_cdr2.setString(1, result);
		queryselect_cdr2.setString(1, cfm.src);
		queryselect_cdr2.setString(2, cfm.dst);
		ResultSet Cursor2 = queryselect_cdr2.executeQuery();// Evaluate (Connected_Expression1)
		

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();

		return "{\"response\":\"sukses\"";
	}
	
	@GetMapping(produces = "application/json", path = "/getCaseType")
	public ArrayList<CaseTypeModel> getCdr() throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryselect_cdr = Connection1.prepareStatement("select * from case_type;");
		
		ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<CaseTypeModel> ListUser1 = new ArrayList<CaseTypeModel>();
		String result="";
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			
			CaseTypeModel ModelCdr = new CaseTypeModel();
			ModelCdr.typecode = Cursor1.getString(1);
			ModelCdr.case1 = Cursor1.getString(2);
			
			ListUser1.add(ModelCdr);
		}

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();
		
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
