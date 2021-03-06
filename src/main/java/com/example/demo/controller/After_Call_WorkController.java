package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Enum.ACWJasperRepository;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.ACWJasper;
import com.example.demo.model.After_Call_WorkModel;
import com.example.demo.model.CaseTypeModel;
import com.example.demo.model.CdrModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.AllDeleteQuery;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/after_call_work")
public class After_Call_WorkController
{
	
	PreparedStatement queryinsert_cdr = null;
	PreparedStatement querydelete_cdr = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	AllSelectParameterQuery query_string2 = new AllSelectParameterQuery();
	PreparedStatement queryselect_cdr = null;

	@Autowired
	private DataSource dataSource;

//	@PostMapping(produces = "application/json", path = "/insertACW")
//	public ResponseEntity<String> insertACW(@RequestBody After_Call_WorkModel cfm)
//	{
//		try {
//			String result = doInsertACW(cfm);
//			return new ResponseEntity<String>(result, HttpStatus.OK);
//		} catch (SQLException error_sql) {
//			error_sql.printStackTrace();
//			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }",
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
	
	
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
	@PostMapping("/insertACW")
	public String doInsertACW(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryinsert_cdr = Connection1.prepareStatement("INSERT INTO public.after_call_work "
				+ "(src, dst, \"case\", detail, datetime, recorded_video_filename,direction) "
				+ "VALUES(?, ?, ?, ?, (now() + interval '7 hours'), ?,?);");
		queryinsert_cdr.setString(1, cfm.src);
		queryinsert_cdr.setString(2, cfm.dst);
		queryinsert_cdr.setString(3, cfm.case1);
		queryinsert_cdr.setString(4, cfm.detail);
//		queryinsert_cdr.setTimestamp(5, cfm.datetime);
		queryinsert_cdr.setString(5, cfm.recorded_filename);
		queryinsert_cdr.setString(6, cfm.direction);

		int flag = queryinsert_cdr.executeUpdate();// Evaluate (Connected_Expression1)

		queryinsert_cdr.close();
		Connection1.close();

		return "{ " + "\"response\":" + "\"" + flag + "\" }";

	}

	@PostMapping(produces = "application/json", path = "/updateUniqueid")
	public String updateUniqueid(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		Connection Connection5 = dataSource.getConnection();
		PreparedStatement queryselect_cdr5 = Connection5
				.prepareStatement("select idacw from after_call_work where src = ? and dst = ? and uniqueid is null order by datetime desc limit 1");
		queryselect_cdr5.setString(1, cfm.src);
		queryselect_cdr5.setString(2, cfm.dst);
		ResultSet Cursor5 = queryselect_cdr5.executeQuery();// Evaluate (Connected_Expression1)
		String idacw = "";

		while (Cursor5.next()) // while there_is_next_record_in (Cursor1)
		{
			idacw = Cursor5.getString(1);
		}

		queryselect_cdr5.close();
		Cursor5.close();
		Connection5.close();

		Connection Connection4 = dataSource.getConnection();
		PreparedStatement queryselect_cdr4 = Connection4
				.prepareStatement("select src, dst from after_call_work where uniqueid is null");
		ResultSet Cursor4 = queryselect_cdr4.executeQuery();// Evaluate (Connected_Expression1)
		int jumlah = 0;
		while (Cursor4.next()) // while there_is_next_record_in (Cursor1)
		{
			jumlah++;
		}

		queryselect_cdr4.close();
		Cursor4.close();
		Connection4.close();

		Connection Connection3 = dataSource.getConnection();
		PreparedStatement queryselect_cdr3 = Connection3
				.prepareStatement("select src, dst from after_call_work where uniqueid is null");
		ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
		String target[][] = new String[jumlah][2];
		int test = 0;
		while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
		{

			target[test][0] = Cursor3.getString(1);

			target[test][1] = Cursor3.getString(2);
			test++;
		}

		queryselect_cdr3.close();
		Cursor3.close();
		Connection3.close();

		for (int i = 0; i < jumlah; i++) {
			// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection();
			queryselect_cdr = Connection1.prepareStatement(
					"select uniqueid from cdr where channel like concat('%/',?,'-%') and dstchannel like concat('%/',?,'-%') and disposition = 'ANSWERED' order by \"end\" desc limit 1;");
			queryselect_cdr.setString(1, target[i][0]);
			queryselect_cdr.setString(2, target[i][1]);
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			ArrayList<CdrModel> ListUser1 = new ArrayList<CdrModel>();
			String result = "";
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{

				result = Cursor1.getString(1);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();

			Connection Connection2 = dataSource.getConnection();
			PreparedStatement queryselect_cdr2 = Connection2.prepareStatement("UPDATE public.after_call_work "
					+ "SET uniqueid = ? " + "where src = ? and dst = ? and uniqueid is null;");
			queryselect_cdr2.setString(1, result);
			queryselect_cdr2.setString(2, target[i][0]);
			queryselect_cdr2.setString(3, target[i][1]);
			queryselect_cdr2.executeUpdate();// Evaluate (Connected_Expression1)

			queryselect_cdr2.close();
			Connection2.close();

		}
		
		

		return "{\"idacw\" : \"" + idacw + "\"}";
	}
	//lama
	@PostMapping(produces = "application/json", path = "/insertACW2")
	public String doInsertACW2(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		queryinsert_cdr = Connection1.prepareStatement(
				"INSERT INTO public.after_call_work " + "(src, dst, \"case\", detail, datetime, recorded_video_filename,direction) " + "VALUES(?, ?, ?, ?, ?, ?,?);");
		queryinsert_cdr.setString(1, cfm.src);
		queryinsert_cdr.setString(2, cfm.dst);
		queryinsert_cdr.setString(3, cfm.case1);
		queryinsert_cdr.setString(4, cfm.detail);
		queryinsert_cdr.setTimestamp(5, cfm.datetime);
		queryinsert_cdr.setString(6, cfm.recorded_video_filename);
		queryinsert_cdr.setString(7, cfm.direction);
		

		int flag = queryinsert_cdr.executeUpdate();// Evaluate (Connected_Expression1)

		queryinsert_cdr.close();
		Connection1.close();

		return "{ " + "\"response\":" + "\"" + 1 + "\" }";

	}
	
	@PostMapping(produces = "application/json", path = "/getACW2")
	public After_Call_WorkModel getACW2(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		After_Call_WorkModel ListUser1 = new After_Call_WorkModel();
		Connection Connection1 = dataSource.getConnection();
		queryinsert_cdr = Connection1.prepareStatement(
				"select b.case, a.detail, a.recorded_video_filename from after_call_work a join case_type b on a.case = b.typecode where a.src = ? and a.dst = ? order by datetime desc limit 1");
		queryinsert_cdr.setString(1, cfm.src);
		queryinsert_cdr.setString(2, cfm.dst);
		
		ResultSet Cursor = queryinsert_cdr.executeQuery();// Evaluate (Connected_Expression1)
		while (Cursor.next()) // while there_is_next_record_in (Cursor1)
		{

			ListUser1.case1 = Cursor.getString(1);

			ListUser1.detail = Cursor.getString(2);
			ListUser1.recorded_video_filename = Cursor.getString(3);
			ListUser1.src = cfm.src;
			ListUser1.dst = cfm.dst;
		}
		queryinsert_cdr.close();
		Connection1.close();

		return ListUser1;

	}

	@PostMapping(produces = "application/json", path = "/updateUniqueid2")
	public String updateUniqueid2(@RequestBody After_Call_WorkModel cfm) throws SQLException
	{
		Connection Connection4 = dataSource.getConnection();
		PreparedStatement queryselect_cdr4 = Connection4
				.prepareStatement("select src, dst from after_call_work where uniqueid is null");
		ResultSet Cursor4 = queryselect_cdr4.executeQuery();// Evaluate (Connected_Expression1)
		int jumlah = 0;
		while (Cursor4.next()) // while there_is_next_record_in (Cursor1)
		{
			jumlah++;
		}

		queryselect_cdr4.close();
		Cursor4.close();
		Connection4.close();

		Connection Connection3 = dataSource.getConnection();
		PreparedStatement queryselect_cdr3 = Connection3
				.prepareStatement("select src, dst from after_call_work where uniqueid is null");
		ResultSet Cursor3 = queryselect_cdr3.executeQuery();// Evaluate (Connected_Expression1)
		String target[][] = new String[jumlah][2];
		int test = 0;
		while (Cursor3.next()) // while there_is_next_record_in (Cursor1)
		{

			target[test][0] = Cursor3.getString(1);

			target[test][1] = Cursor3.getString(2);
			test++;
		}

		queryselect_cdr3.close();
		Cursor3.close();
		Connection3.close();

		for (int i = 0; i < jumlah; i++) {
			// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection();
			queryselect_cdr = Connection1.prepareStatement(
					"select uniqueid from cdr where channel like concat('%',?,'%') and dstchannel like concat('%',?,'%') and disposition = 'ANSWERED' order by \"end\" desc limit 1;");
			queryselect_cdr.setString(1, target[i][0]);
			queryselect_cdr.setString(2, target[i][1]);
			ResultSet Cursor1 = queryselect_cdr.executeQuery();// Evaluate (Connected_Expression1)
			ArrayList<CdrModel> ListUser1 = new ArrayList<CdrModel>();
			String result = "";
			while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
			{

				result = Cursor1.getString(1);
			}

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();

			Connection Connection2 = dataSource.getConnection();
			PreparedStatement queryselect_cdr2 = Connection2.prepareStatement("UPDATE public.after_call_work "
					+ "SET uniqueid = ? " + "where src = ? and dst = ? and uniqueid is null;");
			queryselect_cdr2.setString(1, result);
			queryselect_cdr2.setString(2, target[i][0]);
			queryselect_cdr2.setString(3, target[i][1]);
			queryselect_cdr2.executeUpdate();// Evaluate (Connected_Expression1)

			queryselect_cdr.close();
			Cursor1.close();
			Connection1.close();

		}

		return "{\"response\":\"sukses\"}";
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
		String result = "";
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

	//
	@Autowired
	private ACWJasperRepository repository;

	public String detailReport(CdrModel cfm) throws FileNotFoundException, JRException, SQLException
	{
		Date tanggal2 = cfm.tanggal2;
		CdrController cdr = new CdrController(dataSource);
		List<ACWJasper> acw = new ArrayList<>();
		acw = cdr.doLaporanCdr1(cfm);
		System.out.print(acw.get(0).case1);
		File file = ResourceUtils.getFile("classpath:report1.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource1 = new JRBeanCollectionDataSource(acw);
		Map parameters = new HashMap();
		parameters.put("dateStart", "PERIOD : " + String.valueOf(cfm.tanggal1) + " - " + String.valueOf(cfm.tanggal2));
		parameters.put("datasource1", datasource1);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		if (cfm.format.equalsIgnoreCase("html")) {
//			JasperExportManager.exportReportToHtmlFile(jasperPrint, "/opt/tomcat/latest/webapps/examples/detail"
					JasperExportManager.exportReportToHtmlFile(jasperPrint, "/opt/tomcat/webapps/examples/detail"
					+ cfm.extensions_user + "-" + cfm.tanggal1 + "-" + tanggal2 + ".html");
		}
		if (cfm.format.equalsIgnoreCase("pdf")) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, "/opt/tomcat/latest/webapps/examples/detail"
					JasperExportManager.exportReportToPdfFile(jasperPrint, "/opt/tomcat/webapps/examples/detail"
					+ cfm.extensions_user + "-" + cfm.tanggal1 + "-" + tanggal2 + ".pdf");
		}
		return "";
	}

	public String rekapReport(CdrModel cfm) throws FileNotFoundException, JRException, SQLException
	{
		Date tanggal2 = cfm.tanggal2;
		CdrController cdr = new CdrController(dataSource);
		List<ACWJasper> acw = new ArrayList<>();
		acw = cdr.doRekapCdr1(cfm);
		System.out.print(acw.get(0).case1);
		File file = ResourceUtils.getFile("classpath:report5.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource1 = new JRBeanCollectionDataSource(acw);
		Map parameters = new HashMap();
		parameters.put("dateStart", "PERIOD : " + String.valueOf(cfm.tanggal1) + " - " + String.valueOf(cfm.tanggal2));
		parameters.put("datasource1", datasource1);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		if (cfm.format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,
//					"/opt/tomcat/latest/webapps/examples/rekap" + cfm.tanggal1 + "-" + tanggal2 + ".html");
					"/opt/tomcat/webapps/examples/rekap" + cfm.tanggal1 + "-" + tanggal2 + ".html");
		}
		if (cfm.format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint,
//					"/opt/tomcat/latest/webapps/examples/rekap" + cfm.tanggal1 + "-" + tanggal2 + ".pdf");
					"/opt/tomcat/webapps/examples/rekap" + cfm.tanggal1 + "-" + tanggal2 + ".pdf");
		}
		return "";
	}

	@GetMapping("/getACW")
	public List<ACWJasper> getACW()
	{
		return repository.findAll();
	}

	@PostMapping(path = "/detailReport")
	public String generateReport(@RequestBody CdrModel cfm)
			throws FileNotFoundException, JRException, JRException, SQLException
	{
		return detailReport(cfm);
	}

	@PostMapping(path = "/rekapReport")
	public String generateRekapReport(@RequestBody CdrModel cfm)
			throws FileNotFoundException, JRException, JRException, SQLException
	{
		return rekapReport(cfm);
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
