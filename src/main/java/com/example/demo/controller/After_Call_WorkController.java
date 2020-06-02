package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
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
		queryinsert_cdr.setString(4, cfm.detail);
		

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
		queryselect_cdr = Connection1.prepareStatement("select uniqueid from cdr where channel like concat('%',?,'%') and dstchannel like concat('%',?,'%') and disposition = 'ANSWERED' order by \"end\" desc limit 1;");
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
		queryselect_cdr2.setString(2, cfm.src);
		queryselect_cdr2.setString(3, cfm.dst);
		queryselect_cdr2.executeUpdate();// Evaluate (Connected_Expression1)
		

		queryselect_cdr.close();
		Cursor1.close();
		Connection1.close();

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

	//
	@Autowired
	private ACWJasperRepository repository;
	
	
	public String detailReport( CdrModel cfm) throws FileNotFoundException, JRException, SQLException
	{
		CdrController cdr = new CdrController(dataSource);
		List<ACWJasper> acw = new ArrayList<>();
		acw = cdr.doLaporanCdr1(cfm);
		System.out.print(acw.get(0).case1);
		File file = ResourceUtils.getFile("classpath:report1.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource1 = new JRBeanCollectionDataSource(acw);
		Map parameters= new HashMap();
		parameters.put("dateStart","PERIOD : "+String.valueOf(cfm.tanggal1)+" - " +String.valueOf(cfm.tanggal2));
		parameters.put("datasource1",datasource1);
		JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
		
		if(cfm.format.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"/opt/tomcat/webapps/iswitch/detail"+cfm.extensions_user+"-"+cfm.tanggal1+"-"+cfm.tanggal2+".html");
		}
		if(cfm.format.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,"/opt/tomcat/webapps/iswitch/detail"+cfm.extensions_user+"-"+cfm.tanggal1+"-"+cfm.tanggal2+".pdf");
		}
		return"";
	}
	
	public String rekapReport( CdrModel cfm) throws FileNotFoundException, JRException, SQLException
	{
		CdrController cdr = new CdrController(dataSource);
		List<ACWJasper> acw = new ArrayList<>();
		acw = cdr.doRekapCdr1(cfm);
		System.out.print(acw.get(0).case1);
		File file = ResourceUtils.getFile("classpath:report5.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource1 = new JRBeanCollectionDataSource(acw);
		Map parameters= new HashMap();
		parameters.put("dateStart","PERIOD : "+String.valueOf(cfm.tanggal1)+" - " +String.valueOf(cfm.tanggal2));
		parameters.put("datasource1",datasource1);
		JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
		if(cfm.format.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"/opt/tomcat/webapps/iswitch/rekap"+cfm.tanggal1+"-"+cfm.tanggal2+".html");
		}
		if(cfm.format.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,"/opt/tomcat/webapps/iswitch/rekap"+cfm.tanggal1+"-"+cfm.tanggal2+".pdf");
		}
		return"";
	}

	@GetMapping("/getACW")
	public List<ACWJasper> getACW()
	{
		return repository.findAll();
	}
	
	@PostMapping(path="/detailReport")
	public String generateReport( @RequestBody CdrModel cfm) throws FileNotFoundException,JRException, JRException, SQLException
	{
		return detailReport(cfm);
	}
	
	@PostMapping(path="/rekapReport")
	public String generateRekapReport( @RequestBody CdrModel cfm) throws FileNotFoundException,JRException, JRException, SQLException
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
