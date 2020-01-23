package com.example.demo.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.RekeningModel;
import com.example.demo.model.StatusModel;
import com.example.demo.model.UserModel;
//import com.example.demo.model.User_ActivityModel;
import com.example.demo.model.User_HistoryModel;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin("*")
@RequestMapping("/rekening")

public class RekeningController
{

	@Autowired
	private DataSource dataSource;

	public RekeningController(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@PostMapping("/getRekeningByExtension")
	public ResponseEntity<List<RekeningModel>> getAll(@RequestBody RekeningModel rm)
	{
		try
		{
			List<RekeningModel> listResult = doGetAll(rm);
			return new ResponseEntity<List<RekeningModel>>(listResult, HttpStatus.OK);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<RekeningModel>>(HttpStatus.BAD_REQUEST);
		}
	}

	public List<RekeningModel> doGetAll(@RequestBody RekeningModel rms) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("select " + "no_rekening, " + "jenis_tabungan.nama as jenis_tabungan, "
				+ " kantor_cabang_terdaftar, " + "nomor_kartu, " + "application_status.nama "
				+ " from rekening join application_status on rekening.status = application_status.id join jenis_tabungan on rekening.jenis_tabungan = jenis_tabungan.id"
				+ " where \"extension\" = ? and status = '1' ORDER BY insert_at DESC LIMIT 1");
		query.setString(1, rms.extension);
		ResultSet result = query.executeQuery();
		List<RekeningModel> listRM = new ArrayList<RekeningModel>();
		while (result.next())
		{
			RekeningModel rm = new RekeningModel();
			
			rm.no_rekening = result.getString(1);
			rm.jenis_tabungan = result.getString(2);
			rm.kantor_cabang_terdaftar = result.getString(3);
			rm.nomor_kartu = result.getString(4);

			listRM.add(rm);
		}
		result.close();
		query.close();
		con.close();

		return listRM;
	}

	@PostMapping("/insertRekening")
	public ResponseEntity<String> insertRekening(@RequestBody RekeningModel rm)
	{
		try
		{
			if(doInsertRekening(rm) > 0)
				return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
			else
				return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.NOT_FOUND);
			
		} catch (SQLException error)
		{
			error.printStackTrace();
			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "-1" + "\" }", HttpStatus.BAD_REQUEST);
		}

	}
	
	private int doInsertRekening(RekeningModel rm) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement update_query = con.prepareStatement("INSERT INTO public.rekening " +
				" (insert_at, status, \"extension\") " +
				" VALUES(?::date, ?::int, ?) ");
		update_query.setString(1, rm.insert_at);
		update_query.setString(2, rm.application_status);
		update_query.setString(3, rm.extension);
		
		int result = update_query.executeUpdate();
		
		return result;
	}
	
	@PostMapping("/updateRekening")
	public ResponseEntity<String> updateRekening(@RequestBody RekeningModel rm)
	{
		try
		{
			if(doUpdateRekening(rm) > 0)
				return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
			else
				return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.NOT_FOUND);
			
		} catch (SQLException error)
		{
			error.printStackTrace();
			return new ResponseEntity<String>("{ " + "\"response\":" + "\"" + "-1" + "\" }", HttpStatus.BAD_REQUEST);
		}

	}
	
	private int doUpdateRekening(RekeningModel rm) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement update_query = con.prepareStatement("UPDATE public.rekening " + 
				"SET no_rekening=?, jenis_tabungan=?::int, kantor_cabang_terdaftar=?, nomor_kartu=? WHERE status=1 AND \"extension\"=?;");
		update_query.setString(1, rm.no_rekening);
		update_query.setString(2, rm.jenis_tabungan);
		update_query.setString(3, rm.kantor_cabang_terdaftar);
		update_query.setString(4, String.valueOf(Math.random() * 640000000 + 65000000));//rm.nomor_kartu);
		update_query.setString(5, rm.extension);
		
		int result = update_query.executeUpdate();
		
		return result;
	}
	@PostMapping("/changeRekeningStatus")
	public ResponseEntity<String> changeRekeningStatus (@RequestBody RekeningModel rm)
	{
		try
		{
			if (doChangeRekeningStatus (rm) != 0)
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
			else
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.NOT_FOUND);
		} catch (SQLException error)
		{
			error.printStackTrace ();
			return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "-1" + "\" }", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	private int doChangeRekeningStatus (RekeningModel rm) throws SQLException
	{
		Connection con = dataSource.getConnection ();
		PreparedStatement query = con
				.prepareStatement ("UPDATE rekening SET status = ?::int WHERE status = 0 AND extension = ?");
		
		query.setString (1, rm.status);
		query.setString (2, rm.extension);
		
		int result = query.executeUpdate ();
		
		query.close ();
		con.close ();
		
		System.out.println (result);
		// ResultSet result = query.executeQuery();
		
		return result;
	}
	@GetMapping("/getAppStatus")
	public List<StatusModel> getAppStatus () throws SQLException
	{
		Connection con = dataSource.getConnection ();
		PreparedStatement query = con.prepareStatement ("SELECT * FROM application_status");
		
		ResultSet result = query.executeQuery ();
		List<StatusModel> listAppStatus = new ArrayList<StatusModel> ();
		while (result.next ())
		{
			StatusModel sm = new StatusModel ();
			sm.status_id = result.getString (1);
			sm.nama_status = result.getString (2);
			listAppStatus.add (sm);
		}
		
		result.close ();
		query.close ();
		con.close ();
		
		return listAppStatus;
	}
}
