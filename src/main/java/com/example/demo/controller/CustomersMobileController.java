package com.example.demo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomersModel;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/customers_mobile")
public class CustomersMobileController
{
	
	@Autowired
	private DataSource dataSource;
	
	@PostMapping("/getCustomersMobileByExtension")
	public List<CustomersModel> getAllRekening (@RequestBody CustomersModel cm) throws SQLException
	{
		AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
		AllInsertQuery insert_query = new AllInsertQuery ();
		AllUpdateQuery update_query = new AllUpdateQuery ();
		
		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection ();
		PreparedStatement query = connection.prepareStatement ("SELECT * FROM customers_mobile WHERE extension = ?");
		
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
	
	@PostMapping("/addCustomerMobile")
	public String addCustomerRekening (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			// Connection connection = DriverManager.getConnection (sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection connection = dataSource.getConnection ();
			PreparedStatement query = connection.prepareStatement ("INSERT INTO public.customers_mobile " + 
					"	(nik, nama, tempat_lahir, tanggal_lahir, nomor_telepon, nama_ibu, alamat, rt_rw, kelurahan, kecamatan, kota, kode_pos, foto, foto_ktp, foto_ttd, \"extension\", email) " + 
					"	VALUES(?, ?, ?, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
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
}
