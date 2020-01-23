package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.CustomersModel;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/customers")
public class CustomersController
{
	@Autowired
	private DataSource dataSource;
	
	AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
	AllInsertQuery insert_query = new AllInsertQuery ();
	AllUpdateQuery update_query = new AllUpdateQuery ();
	
	stringkoneksi sk = new stringkoneksi ();
	
	@PostMapping("/getCustomersByExtensionRekening")
	public List<CustomersModel> getAllRekening (@RequestBody CustomersModel cm) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (select_query.query_customer);
		
		query.setString (1, cm.extension);
		
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			
			customerModel.id = Cursor1.getString (1);
			customerModel.nik = Cursor1.getString (2);
			customerModel.nama = Cursor1.getString (3);
			customerModel.tempat_lahir = Cursor1.getString (4);
			customerModel.tanggal_lahir = Cursor1.getString (5);
			customerModel.nomor_telepon = Cursor1.getString (6);
			customerModel.nama_ibu = Cursor1.getString (7);
			customerModel.alamat = Cursor1.getString (8);
			customerModel.rt_rw = Cursor1.getString (9);
			customerModel.kelurahan = Cursor1.getString (10);
			customerModel.kecamatan = Cursor1.getString (11);
			customerModel.kota = Cursor1.getString (12);
			customerModel.kode_pos = Cursor1.getString (13);
			customerModel.foto = Cursor1.getString (14);
			customerModel.foto_ktp = Cursor1.getString (15);
			customerModel.foto_ttd = Cursor1.getString (16);
			customerModel.extension = Cursor1.getString (17);
			customerModel.email = Cursor1.getString (18);
			
			listCustomers.add (customerModel);
		}
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return listCustomers;
	}
	
	@PostMapping("/getCustomersByExtensionRekeningVerified")
	public List<CustomersModel> getRekeningByExtensionVerified (@RequestBody CustomersModel cm) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement ("SELECT \r\n" + 
				"customers.id, \r\n" + 
				"customers.nik, \r\n" + 
				"customers.nama, \r\n" + 
				"customers.tempat_lahir, \r\n" + 
				"customers.tanggal_lahir, \r\n" + 
				"customers.nomor_telepon, \r\n" + 
				"customers.nama_ibu, \r\n" + 
				"customers.alamat, \r\n" + 
				"customers.rt_rw, kelurahan, \r\n" + 
				"customers.kecamatan, \r\n" + 
				"customers.kota, \r\n" + 
				"customers.kode_pos, \r\n" + 
				"customers.foto, \r\n" + 
				"customers.foto_ktp, \r\n" + 
				"customers.foto_ttd, \r\n" + 
				"customers.\"extension\", \r\n" + 
				"customers.email \r\n" + 

				"FROM public.customers JOIN public.rekening ON customers.extension = rekening.extension JOIN public.jenis_tabungan ON rekening.jenis_tabungan = jenis_tabungan.id JOIN public.application_status ON application_status.id = rekening.status \r\n" + 
				"where rekening.status = 1 and customers.\"extension\" = ?;");
		
		query.setString (1, cm.extension);
		
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			
			customerModel.id = Cursor1.getString (1);
			customerModel.nik = Cursor1.getString (2);
			customerModel.nama = Cursor1.getString (3);
			customerModel.tempat_lahir = Cursor1.getString (4);
			customerModel.tanggal_lahir = Cursor1.getString (5);
			customerModel.nomor_telepon = Cursor1.getString (6);
			customerModel.nama_ibu = Cursor1.getString (7);
			customerModel.alamat = Cursor1.getString (8);
			customerModel.rt_rw = Cursor1.getString (9);
			customerModel.kelurahan = Cursor1.getString (10);
			customerModel.kecamatan = Cursor1.getString (11);
			customerModel.kota = Cursor1.getString (12);
			customerModel.kode_pos = Cursor1.getString (13);
			customerModel.foto = Cursor1.getString (14);
			customerModel.foto_ktp = Cursor1.getString (15);
			customerModel.foto_ttd = Cursor1.getString (16);
			customerModel.extension = Cursor1.getString (17);
			customerModel.email = Cursor1.getString (18);
			
			listCustomers.add (customerModel);
		}
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return listCustomers;
	}
	
	@PostMapping("/addCustomerRekening")
	public String addCustomerRekening (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (insert_query.query_insert_customer);
			
			query.setString (1, cm.nik);
			query.setString (2, cm.nama);
			query.setString (3, cm.tempat_lahir);
			query.setString (4, cm.tanggal_lahir);
			query.setString (5, cm.nomor_telepon);
			query.setString (6, cm.nama_ibu);
			query.setString (7, cm.alamat);
			query.setString (8, cm.rt_rw);
			query.setString (9, cm.kelurahan);
			query.setString (10, cm.kecamatan);
			query.setString (11, cm.kota);
			query.setString (12, cm.kode_pos);
			query.setString (13, cm.foto);
			query.setString (14, cm.foto_ktp);
			query.setString (15, cm.foto_ttd);
			query.setString (16, cm.extension);
			query.setString (17, cm.email);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	@PostMapping("/updateCustomerRekening")
	public String updateCustomerRekening (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (update_query.query_update_customer);
			
			query.setString (1, cm.nik);
			query.setString (2, cm.nama);
			query.setString (3, cm.tempat_lahir);
			query.setString (4, cm.tanggal_lahir);
			query.setString (5, cm.nomor_telepon);
			query.setString (6, cm.nama_ibu);
			query.setString (7, cm.alamat);
			query.setString (8, cm.rt_rw);
			query.setString (9, cm.kelurahan);
			query.setString (10, cm.kecamatan);
			query.setString (11, cm.kota);
			query.setString (12, cm.kode_pos);
			query.setString (13, cm.foto);
			query.setString (14, cm.foto_ktp);
			query.setString (15, cm.foto_ttd);
			query.setString (16, cm.email);
			
			query.setString (17, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	public int addSingleCustomerRekening (CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (insert_query.query_insert_customer);
			
			query.setString (1, cm.nik);
			query.setString (2, cm.nama);
			query.setString (3, cm.tempat_lahir);
			query.setString (4, cm.tanggal_lahir);
			query.setString (5, cm.nomor_telepon);
			query.setString (6, cm.nama_ibu);
			query.setString (7, cm.alamat);
			query.setString (8, cm.rt_rw);
			query.setString (9, cm.kelurahan);
			query.setString (10, cm.kecamatan);
			query.setString (11, cm.kota);
			query.setString (12, cm.kode_pos);
			query.setString (13, cm.foto);
			query.setString (14, cm.foto_ktp);
			query.setString (15, cm.foto_ttd);
			query.setString (16, cm.extension);
			query.setString (17, cm.email);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag;
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return 0;
		}
		
	}
	
	public List<CustomersModel> getCustomersByExtensionRekening (CustomersModel cm) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (select_query.query_customer);
		
		query.setString (1, cm.extension);
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			customerModel.id = Cursor1.getString (1);
			customerModel.nik = Cursor1.getString (2);
			customerModel.nama = Cursor1.getString (3);
			customerModel.tempat_lahir = Cursor1.getString (4);
			customerModel.tanggal_lahir = Cursor1.getString (5);
			customerModel.nomor_telepon = Cursor1.getString (6);
			customerModel.nama_ibu = Cursor1.getString (7);
			customerModel.alamat = Cursor1.getString (8);
			customerModel.rt_rw = Cursor1.getString (9);
			customerModel.kelurahan = Cursor1.getString (10);
			customerModel.kecamatan = Cursor1.getString (11);
			customerModel.kota = Cursor1.getString (12);
			customerModel.kode_pos = Cursor1.getString (13);
			customerModel.foto = Cursor1.getString (14);
			customerModel.foto_ktp = Cursor1.getString (15);
			customerModel.foto_ttd = Cursor1.getString (16);
			customerModel.extension = Cursor1.getString (17);
			customerModel.email = Cursor1.getString (18);
			listCustomers.add (customerModel);
			
		}
		
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return listCustomers;
	}
	
	public int updateCustomerByExtensionRekening (CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (update_query.query_update_customer);
			
			query.setString (1, cm.nik);
			query.setString (2, cm.nama);
			query.setString (3, cm.tempat_lahir);
			query.setString (4, cm.tanggal_lahir);
			query.setString (5, cm.nomor_telepon);
			query.setString (6, cm.nama_ibu);
			query.setString (7, cm.alamat);
			query.setString (8, cm.rt_rw);
			query.setString (9, cm.kelurahan);
			query.setString (10, cm.kecamatan);
			query.setString (11, cm.kota);
			query.setString (12, cm.kode_pos);
			query.setString (13, cm.foto);
			query.setString (14, cm.foto_ktp);
			query.setString (15, cm.foto_ttd);
			query.setString (16, cm.email);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag;
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return 0;
		}
		
	}
	
	@PostMapping("/saveCustomerRekening")
	public ResponseEntity<String> saveCustomerRekening (@RequestBody CustomersModel cm)
	{
		try
		{
			List<CustomersModel> resultList = getCustomersByExtensionRekening (cm);
			
			if (resultList.size () <= 0)
			{
				if (addSingleCustomerRekening (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			} else
			{
				if (updateCustomerByExtensionRekening (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e)
		{
			e.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/getCustomersByExtension")
	public List<CustomersModel> getAll (@RequestBody CustomersModel cm) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (select_query.query_customer);
		
		query.setString (1, cm.extension);
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			customerModel.id = Cursor1.getString (1);
			customerModel.nama = Cursor1.getString (2);
			customerModel.nomor_telepon = Cursor1.getString (3);
			customerModel.alamat = Cursor1.getString (4);
			customerModel.extension = Cursor1.getString (5);
			listCustomers.add (customerModel);
			
		}
		
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return listCustomers;
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (insert_query.query_insert_customer);
			
			query.setString (1, cm.id);
			query.setString (2, cm.nama);
			query.setString (3, cm.nomor_telepon);
			query.setString (4, cm.alamat);
			query.setString (5, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	@PostMapping("/updateCustomer")
	public String updateCustomer (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (update_query.query_update_customer);
			
			query.setString (1, cm.nama);
			query.setString (2, cm.nomor_telepon);
			query.setString (3, cm.alamat);
			query.setString (4, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	public int addSingleCustomer (CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (insert_query.query_insert_customer);
			
			query.setString (1, cm.id);
			query.setString (2, cm.nama);
			query.setString (3, cm.nomor_telepon);
			query.setString (4, cm.alamat);
			query.setString (5, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag;
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return 0;
		}
		
	}
	
	public List<CustomersModel> getCustomersByExtension (CustomersModel cm) throws SQLException
	{
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement (select_query.query_customer);
		
		query.setString (1, cm.extension);
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			customerModel.id = Cursor1.getString (1);
			customerModel.nama = Cursor1.getString (2);
			customerModel.nomor_telepon = Cursor1.getString (3);
			customerModel.alamat = Cursor1.getString (4);
			customerModel.extension = Cursor1.getString (5);
			listCustomers.add (customerModel);
			
		}
		
		query.close ();
		Cursor1.close ();
		connection.close ();
		
		return listCustomers;
	}
	
	public int updateCustomerByExtension (CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement (update_query.query_update_customer);
			
			query.setString (1, cm.nama);
			query.setString (2, cm.nomor_telepon);
			query.setString (3, cm.alamat);
			query.setString (4, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag;
		} catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return 0;
		}
		
	}
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<String> saveCustomer (@RequestBody CustomersModel cm)
	{
		try
		{
			List<CustomersModel> resultList = getCustomersByExtension (cm);
			
			if (resultList.size () <= 0)
			{
				if (addSingleCustomer (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			} else
			{
				if (updateCustomerByExtension (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e)
		{
			e.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
