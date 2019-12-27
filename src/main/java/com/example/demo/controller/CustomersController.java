package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.model.UserModel;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;
import com.example.demo.query.AllUpdateQuery;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/customers")
public class CustomersController
{
	AllSelectParameterQuery select_query = new AllSelectParameterQuery ();
	AllInsertQuery insert_query = new AllInsertQuery ();
	AllUpdateQuery update_query = new AllUpdateQuery ();
	
	stringkoneksi sk = new stringkoneksi ();
	
	@PostMapping ("/getCustomersByExtension")
	public ResponseEntity<String> doGetall (@RequestBody CustomersModel cfm)
	{
		try
		{
			CustomersModel result = getAll (cfm);

			ArrayList<String> formatedResultField = new ArrayList<String> ();
			formatedResultField.add ("id");
			formatedResultField.add ("nama");
			formatedResultField.add ("nomor_telepon");
			formatedResultField.add ("alamat");
			formatedResultField.add ("extension");

			ArrayList<String> formatedResultValues = new ArrayList<String> ();
			formatedResultValues.add (result.id);
			formatedResultValues.add (result.nama);
			formatedResultValues.add (result.nomor_telepon);
			formatedResultValues.add (result.alamat);
			formatedResultValues.add (result.extension);
			
			String parsedResult = parseToStringJSON (formatedResultField, formatedResultValues);

			if (!result.equals (null) && result.id != null)
			{
				return new ResponseEntity<String> (parsedResult, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException | NullPointerException error_null)
		{
			error_null.printStackTrace ();
			return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.UNAUTHORIZED);
		}
	}
	public CustomersModel getAll (@RequestBody CustomersModel cm) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement (select_query.query_customer);

		query.setString (1, cm.extension);
		ResultSet Cursor1 = query.executeQuery ();
		CustomersModel customerModel = new CustomersModel ();
		while (Cursor1.next ())
		{
			
			customerModel.id = Cursor1.getString (1);
			customerModel.nama = Cursor1.getString (2);
			customerModel.nomor_telepon = Cursor1.getString (3);
			customerModel.alamat = Cursor1.getString (4);
			customerModel.extension = Cursor1.getString (5);
			
			
		}
		 
		query.close ();
		Cursor1.close ();
		connection.close ();
		return customerModel;
	}
	
	@PostMapping ("/addCustomer")
	public String addCustomer (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection
					.prepareStatement (insert_query.query_insert_customer);

			query.setString (1, cm.id);
			query.setString (2, cm.nama);
			query.setString (3, cm.nomor_telepon);
			query.setString (4, cm.alamat);
			query.setString (5, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();

			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		}
		catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	
	@PostMapping ("/updateCustomer")
	public String updateCustomer (@RequestBody CustomersModel cm) throws SQLException
	{
		try
		{
			Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection
					.prepareStatement (update_query.query_update_customer);

			query.setString (1, cm.nama);
			query.setString (2, cm.nomor_telepon);
			query.setString (3, cm.alamat);
			query.setString (4, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return "{ " + "\"response\":" + "\"" + flag + "\" }";
		}
		catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return "{ " + "\"response\":" + "\"" + "0" + "\" }";
		}
		
	}
	
	public int addSingleCustomer (CustomersModel cm) throws SQLException
	{
		try
		{
			Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection
					.prepareStatement (insert_query.query_insert_customer);

			query.setString (1, cm.id);
			query.setString (2, cm.nama);
			query.setString (3, cm.nomor_telepon);
			query.setString (4, cm.alamat);
			query.setString (5, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag;
		}
		catch (SQLException error_sql)
		{
			error_sql.printStackTrace ();
			return 0;
		}
		
	}
	
	public List<CustomersModel> getCustomersByExtension (CustomersModel cm) throws SQLException
	{
		Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement (select_query.query_customer);

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
			Connection connection = DriverManager.getConnection (sk.Path_expr, sk.service_user, sk.service_password);
			PreparedStatement query = connection
					.prepareStatement (update_query.query_update_customer);

			query.setString (1, cm.nama);
			query.setString (2, cm.nomor_telepon);
			query.setString (3, cm.alamat);
			query.setString (4, cm.extension);
			
			int flag = query.executeUpdate ();
			
			query.close ();
			connection.close ();
			
			return flag ;
		}
		catch (SQLException error_sql)
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
			
			if(resultList.size () <= 0)
			{
				if(addSingleCustomer (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			}
			else
			{
				if(updateCustomerByExtension (cm) == 1)
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "1" + "\" }", HttpStatus.OK);
				else
					return new ResponseEntity<String> ("{ " + "\"response\":" + "\"" + "0" + "\" }", HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	private String parseToStringJSON (ArrayList<String> field, ArrayList<String> values)
	{
		String JSONHeader = "{\n\t";
		String JSONFooter = "\n}";
		String parsedJSON = "";
		String endLineJSON = ",\n\t";
		String quote = "\"";
		String equal = " : ";

		for (int i = 0; i < field.size (); i++)
		{
			if (i < field.size () - 1)
				parsedJSON += quote + field.get (i) + quote + equal + quote + values.get (i) + quote + endLineJSON;
			else
				parsedJSON += quote + field.get (i) + quote + equal + quote + values.get (i) + quote;
		}

		parsedJSON = JSONHeader + parsedJSON + JSONFooter;
		//System.out.println(parsedJSON);
		return parsedJSON;
	}
}
