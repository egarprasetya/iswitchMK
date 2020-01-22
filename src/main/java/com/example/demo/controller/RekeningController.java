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

	@GetMapping("/getAll")
	public ResponseEntity<List<RekeningModel>> getAll()
	{
		try
		{
			List<RekeningModel> listResult = doGetAll();
			return new ResponseEntity<List<RekeningModel>>(listResult, HttpStatus.OK);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<RekeningModel>>(HttpStatus.BAD_REQUEST);
		}
	}

	public List<RekeningModel> doGetAll() throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("select " + "rekening_id, " + "nik, "
				+ "rekening.nama as nama_nasabah, " + "tempat_lahir, " + "tanggal_lahir, " + "nomor_telepon, " + "nama_ibu, "
				+ "alamat, " + "rt_rw, " + "kelurahan, " + "kecamatan, " + "kota, " + "kode_pos, " + "foto, " + "foto_ktp, "
				+ "foto_ttd, " + "insert_at, " + "no_rekening, " + "jenis_tabungan.nama as jenis_tabungan, "
				+ "kantor_cabang_terdaftar, " + "nomor_kartu, " + "application_status.nama, " + "extension "
				+ "from rekening join application_status on rekening.status = application_status.id join jenis_tabungan on rekening.jenis_tabungan = jenis_tabungan.id;");

		ResultSet result = query.executeQuery();
		List<RekeningModel> listRM = new ArrayList<RekeningModel>();
		while (result.next())
		{
			RekeningModel rm = new RekeningModel();
			rm.rekening_id = result.getInt(1);
			rm.nik = result.getString(2);
			rm.nama = result.getString(3);
			rm.tempat_lahir = result.getString(4);
			rm.tanggal_lahir = result.getString(5);
			rm.nomor_telepon = result.getString(6);
			rm.nama_ibu = result.getString(7);
			rm.alamat = result.getString(8);
			rm.rt_rw = result.getString(9);
			rm.kelurahan = result.getString(10);
			rm.kecamatan = result.getString(11);
			rm.kota = result.getString(12);
			rm.kode_pos = result.getString(13);
			rm.foto = result.getString(14);
			rm.foto_ktp = result.getString(15);
			rm.foto_ttd = result.getString(16);
			rm.insert_at = result.getString(17);
			rm.no_rekening = result.getString(18);
			rm.jenis_tabungan = result.getString(19);
			rm.kantor_cabang_terdaftar = result.getString(20);
			rm.nomor_kartu = result.getString(21);
			rm.application_status = result.getString(22);
			rm.extension = result.getString(23);

			listRM.add(rm);
		}
		result.close();
		query.close();
		con.close();

		return listRM;
	}

	@GetMapping("/test")
	public List<RekeningModel> getAllData2(@RequestParam String bla)
	// public void getAllData2 ()
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/rekening/getAll", HttpMethod.GET,
				null, String.class);
		//ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/rekening/getDataTerbaru", HttpMethod.GET,
		//		null, String.class);
		System.out.println(response.getBody());

		return parsingJSONtoModel(response.getBody(), bla);
	}

	private List<RekeningModel> parsingJSONtoModel(String in, String bla)
	{
		in = in.replace("[", "").replace("]", "");

		String[] item = (in.replace("\":", "\":\"").replace(",\"", "\",\"")).split("},");
		List<RekeningModel> listNasabah = new ArrayList<RekeningModel>();

		for (int i = 0; i < item.length; i++)
		{
			if (item[i].contains(bla))
			{
				String[] model = item[i].split("\",\"");

				RekeningModel dn = new RekeningModel();
				dn.rekening_id = Integer.valueOf(model[0].replace("{\"rekening_id\":\"", ""));
				dn.nik = model[1].replace("nik\":\"", "").replace("\"", "");
				dn.nama = model[2].replace("nama\":\"", "").replace("\"", "");
				dn.tempat_lahir = model[3].replace("tempat_lahir\":\"", "").replace("\"", "");
				dn.tanggal_lahir = model[4].replace("tanggal_lahir\":\"", "").replace("\"", "");
				dn.nomor_telepon = model[5].replace("nomor_telepon\":\"", "").replace("\"", "");
				dn.nama_ibu = model[6].replace("nama_ibu\":\"", "").replace("\"", "");
				dn.alamat = model[7].replace("alamat\":\"", "").replace("\"", "");
				dn.rt_rw = model[8].replace("rt_rw\":\"", "").replace("\"", "");
				dn.kelurahan = model[9].replace("kelurahan\":\"", "").replace("\"", "");
				;
				dn.kecamatan = model[10].replace("kecamatan\":\"", "").replace("\"", "");
				dn.kota = model[11].replace("kota\":\"", "").replace("\"", "");
				dn.kode_pos = model[12].replace("kode_pos\":\"", "").replace("\"", "");
				dn.foto = model[13].replace("foto\":\"", "").replace("\"", "");
				dn.foto_ktp = model[14].replace("foto_ktp\":\"", "").replace("\"", "");
				dn.foto_ttd = model[15].replace("foto_ttd\":\"", "").replace("\"", "");
				dn.insert_at = model[16].replace("insert_at\":\"", "").replace("\"", "");
				dn.no_rekening = model[17].replace("no_rekening\":\"", "").replace("\"", "");
				dn.jenis_tabungan = model[18].replace("jenis_tabungan\":\"", "").replace("\"", "");
				dn.kantor_cabang_terdaftar = model[19].replace("kantor_cabang_terdaftar\":\"", "").replace("\"", "");
				dn.nomor_kartu = model[20].replace("nomor_kartu\":\"", "").replace("\"", "");
				dn.application_status = model[21].replace("application_status\":\"", "").replace("\"", "");
				dn.extension = model[22].replace("extension\":\"", "").replace("\"", "").replace("}", "");
				System.out.println(dn.nama);
				listNasabah.add(dn);
			}
		}

		for (String i : item)
		{
			System.out.println(i);
		}

		return listNasabah;
	}

	@PostMapping("/changeAppStatus")
	public ResponseEntity<String> changeAppStatus(@RequestBody RekeningModel rm, @RequestParam int status)
	{
		try
		{
			if (doChangeAppStatus(rm, status) != 0)
				return new ResponseEntity<String>("1", HttpStatus.OK);
			else
				return new ResponseEntity<String>("0", HttpStatus.NOT_FOUND);
		} catch (SQLException error)
		{
			error.printStackTrace();
			return new ResponseEntity<String>("-1", HttpStatus.BAD_REQUEST);
		}

	}

	private int doChangeAppStatus(RekeningModel rm, int status) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("UPDATE rekening SET status = ? WHERE status = 0 AND extension = ?");
		
		query.setInt(1, status);
		query.setString(2, rm.extension);
		
		int result = query.executeUpdate();
		
		query.close();
		con.close();
		
		System.out.println(result);
		//ResultSet result = query.executeQuery();
		
		return result;
	}

	@PostMapping("/getDataTerbaru")
	public ResponseEntity<List<RekeningModel>> getDataTerbaru(@RequestBody RekeningModel rms)
	{
		try
		{
			List<RekeningModel> result = doGetDataTerbaru(rms);
			
			if(result.size() > 0)
				return new ResponseEntity<List<RekeningModel>>(result, HttpStatus.OK);
			else
				return new ResponseEntity<List<RekeningModel>>(result, HttpStatus.NOT_FOUND);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<List<RekeningModel>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<RekeningModel> doGetDataTerbaru(@RequestBody RekeningModel rms) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("select " + "rekening_id, " + "nik, "
				+ "rekening.nama as nama_nasabah, " + "tempat_lahir, " + "tanggal_lahir, " + "nomor_telepon, " + "nama_ibu, "
				+ "alamat, " + "rt_rw, " + "kelurahan, " + "kecamatan, " + "kota, " + "kode_pos, " + "foto, " + "foto_ktp, "
				+ "foto_ttd, " + "insert_at, " + "no_rekening, " + "jenis_tabungan.nama as jenis_tabungan, "
				+ "kantor_cabang_terdaftar, " + "nomor_kartu, " + "application_status.nama, " + "extension "
				+ "from rekening join application_status on rekening.status = application_status.id join jenis_tabungan on rekening.jenis_tabungan = jenis_tabungan.id "
				+ "WHERE status = 0 AND extension = ? ORDER BY insert_at DESC LIMIT 1;");

		query.setString(1, rms.extension);
		
		ResultSet result = query.executeQuery();
		List<RekeningModel> listRM = new ArrayList<RekeningModel>();
		while (result.next())
		{
			RekeningModel rm = new RekeningModel();
			rm.rekening_id = result.getInt(1);
			rm.nik = result.getString(2);
			rm.nama = result.getString(3);
			rm.tempat_lahir = result.getString(4);
			rm.tanggal_lahir = result.getString(5);
			rm.nomor_telepon = result.getString(6);
			rm.nama_ibu = result.getString(7);
			rm.alamat = result.getString(8);
			rm.rt_rw = result.getString(9);
			rm.kelurahan = result.getString(10);
			rm.kecamatan = result.getString(11);
			rm.kota = result.getString(12);
			rm.kode_pos = result.getString(13);
			rm.foto = result.getString(14);
			rm.foto_ktp = result.getString(15);
			rm.foto_ttd = result.getString(16);
			rm.insert_at = result.getString(17);
			rm.no_rekening = result.getString(18);
			rm.jenis_tabungan = result.getString(19);
			rm.kantor_cabang_terdaftar = result.getString(20);
			rm.nomor_kartu = result.getString(21);
			rm.application_status = result.getString(22);
			rm.extension = result.getString(23);

			listRM.add(rm);
		}
		result.close();
		query.close();
		con.close();
		
		return listRM;
	}
	
	
	@PostMapping("/getDataVerified")
	public ResponseEntity<List<RekeningModel>> getDataVerified(@RequestBody RekeningModel rms)
	{
		try
		{
			List<RekeningModel> result = doGetDataVerified(rms);
			
			if(result.size() > 0)
				return new ResponseEntity<List<RekeningModel>>(result, HttpStatus.OK);
			else
				return new ResponseEntity<List<RekeningModel>>(result, HttpStatus.NOT_FOUND);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<List<RekeningModel>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<RekeningModel> doGetDataVerified(@RequestBody RekeningModel rms) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("select " + "rekening_id, " + "nik, "
				+ "rekening.nama as nama_nasabah, " + "tempat_lahir, " + "tanggal_lahir, " + "nomor_telepon, " + "nama_ibu, "
				+ "alamat, " + "rt_rw, " + "kelurahan, " + "kecamatan, " + "kota, " + "kode_pos, " + "foto, " + "foto_ktp, "
				+ "foto_ttd, " + "insert_at, " + "no_rekening, " + "jenis_tabungan.nama as jenis_tabungan, "
				+ "kantor_cabang_terdaftar, " + "nomor_kartu, " + "application_status.nama, " + "extension "
				+ "from rekening join application_status on rekening.status = application_status.id join jenis_tabungan on rekening.jenis_tabungan = jenis_tabungan.id "
				+ "WHERE status = 1 AND extension = ? ORDER BY insert_at DESC LIMIT 1;");

		query.setString(1, rms.extension);
		
		ResultSet result = query.executeQuery();
		List<RekeningModel> listRM = new ArrayList<RekeningModel>();
		while (result.next())
		{
			RekeningModel rm = new RekeningModel();
			rm.rekening_id = result.getInt(1);
			rm.nik = result.getString(2);
			rm.nama = result.getString(3);
			rm.tempat_lahir = result.getString(4);
			rm.tanggal_lahir = result.getString(5);
			rm.nomor_telepon = result.getString(6);
			rm.nama_ibu = result.getString(7);
			rm.alamat = result.getString(8);
			rm.rt_rw = result.getString(9);
			rm.kelurahan = result.getString(10);
			rm.kecamatan = result.getString(11);
			rm.kota = result.getString(12);
			rm.kode_pos = result.getString(13);
			rm.foto = result.getString(14);
			rm.foto_ktp = result.getString(15);
			rm.foto_ttd = result.getString(16);
			rm.insert_at = result.getString(17);
			rm.no_rekening = result.getString(18);
			rm.jenis_tabungan = result.getString(19);
			rm.kantor_cabang_terdaftar = result.getString(20);
			rm.nomor_kartu = result.getString(21);
			rm.application_status = result.getString(22);
			rm.extension = result.getString(23);

			listRM.add(rm);
		}
		result.close();
		query.close();
		con.close();
		
		return listRM;
	}
	
	@GetMapping("/getAppStatus")
	public List<StatusModel> getAppStatus () throws SQLException
	{
		Connection con= dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("SELECT * FROM application_status");
		
		ResultSet result = query.executeQuery();
		List<StatusModel> listAppStatus = new ArrayList<StatusModel>();
		while(result.next())
		{
			StatusModel sm = new StatusModel();
			sm.status_id = result.getString(1);
			sm.nama_status = result.getString(2);
			listAppStatus.add(sm);
		}
		
		result.close();
		query.close();
		con.close();
		
		return listAppStatus;
	}
	
	
	
	@PostMapping("/updateDataMobile")
	public ResponseEntity<String> updateDataMobile(@RequestBody RekeningModel rm)
	{
		try
		{
			if(doUpdateDataMobile(rm) > 0)
				return new ResponseEntity<String>("1", HttpStatus.OK);
			else
				return new ResponseEntity<String>("0", HttpStatus.NOT_FOUND);
			
		} catch (SQLException error)
		{
			error.printStackTrace();
			return new ResponseEntity<String>("-1", HttpStatus.BAD_REQUEST);
		}

	}
	
	private int doUpdateDataMobile(RekeningModel rm) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement update_query = con.prepareStatement("UPDATE public.rekening " + 
				"SET nik=?, nama=?, tempat_lahir=?, tanggal_lahir=?::date, nomor_telepon=?, nama_ibu=?, alamat=?, rt_rw=?, kelurahan=?, kecamatan=?, kota=?, kode_pos=?, no_rekening=? WHERE status=0 AND extension = ?;");
		
		update_query.setString(1, rm.nik);
		update_query.setString(2, rm.nama);
		update_query.setString(3, rm.tempat_lahir);
		update_query.setString(4, rm.tanggal_lahir);
		update_query.setString(5, rm.nomor_telepon);
		update_query.setString(6, rm.nama_ibu);
		update_query.setString(7, rm.alamat);
		update_query.setString(8, rm.rt_rw);
		update_query.setString(9, rm.kelurahan);
		update_query.setString(10, rm.kecamatan);
		update_query.setString(11, rm.kota);
		update_query.setString(12, rm.kode_pos);
		update_query.setString(13, doUpdateNorek());
		update_query.setString(14, rm.extension);
		
		int result = update_query.executeUpdate();
		
		return result;
	}
	
	private String doUpdateNorek() throws SQLException
	{
		// String encodedPassword = bCryptPasswordEncoder.encode(akun.getPassword());
		String noRek = String.valueOf(Math.random() * 500000000.0 + 200000000.0);
		System.out.println(noRek);
		return noRek;
	}
	
	@GetMapping("/getNorek")
	public ResponseEntity<List<RekeningModel>> getNorek(@RequestBody RekeningModel rms)
	{
		try
		{
			List<RekeningModel> listResult = doGetNorek(rms);
			return new ResponseEntity<List<RekeningModel>>(listResult, HttpStatus.OK);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<RekeningModel>>(HttpStatus.BAD_REQUEST);
		}
	}

	public List<RekeningModel> doGetNorek(RekeningModel rms) throws SQLException
	{
		Connection con = dataSource.getConnection();
		PreparedStatement query = con.prepareStatement("select " + "no_rekening, " + "jenis_tabungan.nama as jenis_tabungan, "
				+ "kantor_cabang_terdaftar, " + "nomor_kartu "
				+ "from rekening join application_status on rekening.status = application_status.id join jenis_tabungan on rekening.jenis_tabungan = jenis_tabungan.id "
				+ "where rekening.status = 1 and rekening.extension = ? order by rekening.insert_at desc limit 1");
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
	
	
}
