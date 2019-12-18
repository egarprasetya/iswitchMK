package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.CustomersModel;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/customers")
public class CustomersController
{
	AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
	stringkoneksi sk = new stringkoneksi ();
	
	@PostMapping ("/getCustomersByPhone")
	public List<CustomersModel> getAll (@RequestBody CustomersModel cm) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement (select_query.query_customer);

		query.setString (1, cm.nomor_telepon);
		List<CustomersModel> listCustomers = new ArrayList<CustomersModel> ();
		ResultSet Cursor1 = query.executeQuery ();
		while (Cursor1.next ())
		{
			CustomersModel customerModel = new CustomersModel ();
			customerModel.id = Cursor1.getString (1);
			customerModel.nama = Cursor1.getString (2);
			customerModel.nomor_telepon = Cursor1.getString (3);
			customerModel.extension = Cursor1.getString (4);
			listCustomers.add (customerModel);
			
		}
		return listCustomers;
	}
}
