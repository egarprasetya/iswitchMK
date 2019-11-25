package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.query.*;
import com.example.demo.model.UserModel;
import com.example.demo.connection.*;
@RestController
@RequestMapping("/callservice")
public class TestController {
	
	stringkoneksi sk = new stringkoneksi();
AllQuery query_string= new AllQuery();
	
	@GetMapping("/LoginUser2/{nama}/{email}")
	public int cekuser2(@PathVariable("nama") String nama, @PathVariable("email") String email)
	{
		if(nama=="tossa" || email=="tes@gmail.com")
		{
			return 0;
		}
		else
			
		{
			return 1;
		}
		
		
	}
	
	PreparedStatement queryselect=null;
	PreparedStatement queryselectnamaEmail=null;
	
	@GetMapping("/sqlparam/{nama}")
	
	public String ceksql(@PathVariable("nama") String nama) throws SQLException
	{	        String namaw="Tidak Ditemukan";

		try
		{
		
	
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        queryselect= Connection1.prepareStatement(query_string.query_select_user);
	        queryselect.setString(1, nama);
	        ResultSet rs = queryselect.executeQuery();
	        while(rs.next())
	        {
	       
	        	String nama2;
	        	nama2="tossa";
	        	return nama2;
	        
	        }
	       Connection1.close();

		}
	        catch (SQLException e ) {
	            String error;
	            error=e.getMessage();
	            return error;
	            }
	        return namaw;
	}
	
	
	
	@GetMapping("/LoginUser/{nama}/{email}")
	public int cekuser(@PathVariable("nama") String nama, @PathVariable("email") String email)
	{
		int rows=-1;
			try
		     {
		        Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		        queryselectnamaEmail=Connection1.prepareStatement(query_string.query_select_nama_email);
		        queryselect.setString(1, nama);
		        queryselect.setString(2, email);
		        ResultSet Cursor1= queryselect.executeQuery();

		        {
		          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		          {   
		        	  if(Cursor1.wasNull())
		        	  {
		        		   
		        		  rows=0;
		        		  return rows;
		        	  }
		        	  else
		        		  
		        	  {
		        		  
		        		  rows=1;
		        		  return rows;
		        	  }

		          }
		          Connection1.close(); // close (Connected_Expression1) -> close Cursor1
		        }
		      }
		      catch (SQLException e)
		      {
		    	 return e.getErrorCode();
		      }
			return rows;		
	}
	
	
	@GetMapping("/TambahUser/{id_register}/{nama_user}/{umur_user}/{alamat_user}/{provinsi}/{kota}/{zipcode}/{email}/{phone}")
	public String TambahUser(@PathVariable("id_register") int id_register, @PathVariable("nama_user") String nama_user,@PathVariable("umur_user") int umur,@PathVariable("alamat_user") String alamat,
			@PathVariable("provinsi") String provinsi,@PathVariable("kota") String kota,@PathVariable("zipcode") int zipcode,@PathVariable("email") String email,@PathVariable("phone") String phone) throws SQLException, ClassNotFoundException
	{
	      Class.forName(sk.myDriver);
	      Connection conn = DriverManager.getConnection(sk.Path_expr,sk.service_user, sk.service_password);
	      
	      Statement st = conn.createStatement();

	      // note that i'm leaving "date_created" out of this insert statement
	      st.executeUpdate("INSERT INTO user_collection(id_register,nama_user,umur_user,alamat_user,provinsi,kota,zipcode,email,phone)"
	      		+ " VALUES ('"+id_register+"','"+nama_user+"','"+umur+"','"+alamat+"','"+provinsi+"','"+kota+"','"+zipcode+"','"+email+"','"+phone+"')");
String hasil="Sukses";
	      conn.close();
	      return hasil;

}
	
	
	@GetMapping("/TampilkanUser")
	public ArrayList<UserModel> TampilUser() throws SQLException
	{
		
	        Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        Statement Connected_Expression1 = Connection1.createStatement(); // Create_Expression (Connection1);
	        Connected_Expression1.setFetchSize(100);
	        ResultSet Cursor1 = Connected_Expression1.executeQuery // Evaluate (Connected_Expression1)
	          ("select * from user_collection");
	        
	      	  ArrayList<UserModel> ListUser1 = new ArrayList<UserModel>();

      	  int count=0;
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {        UserModel UserModel1=new UserModel();

	        		count++;
	        	  UserModel1.nama=Cursor1.getString(2);
	        	  UserModel1.umur=Cursor1.getInt(3);
	        	  UserModel1.alamat=Cursor1.getString(4);
	        	  UserModel1.email=Cursor1.getString(8);
	        	  UserModel1.phone=Cursor1.getString(9);
	        	  UserModel1.zipcode=Cursor1.getInt(7);
	        	  UserModel1.kota=Cursor1.getString(6);
	        	  UserModel1.id_register=Cursor1.getInt(1);
	        	  UserModel1.provinsi=Cursor1.getString(5);
	        	  
	        	  ListUser1.add(UserModel1);
	        	  
	        	
	        	  
	          }
	          
	          Connected_Expression1.close();
	          return ListUser1;
        	  
	           // close (Connected_Expression1) -> close Cursor1
	         
		

}
	
	@GetMapping("/cariumur/{umur}")
	public int getcountavgtime(@PathVariable("umur") int umur)
	{
		int totalcount, start, end,  selisih, rumus;		
		try
	     {
	        Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	        Statement Connected_Expression1 = Connection1.createStatement(); // Create_Expression (Connection1);
	        ResultSet Cursor1 = Connected_Expression1.executeQuery // Evaluate (Connected_Expression1)
	          ("select SUM(id_register) as totstart from user_collection where id_register='"+umur+"'");
	        {
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {      
	        	  start=Cursor1.getInt("totstart");
	           return start;
	          }
	          Connected_Expression1.close(); // close (Connected_Expression1) -> close Cursor1
	        }
	      }
	      catch (SQLException e)
	      {
	    	  int hasil;
	    	  hasil = e.getErrorCode();
	    	  return hasil;
	      }
		int hasil =0;
		return hasil;

}
}
