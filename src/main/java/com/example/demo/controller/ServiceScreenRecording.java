package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.connection.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/ServiceScreenRecorder")
public class ServiceScreenRecording {

	PreparedStatement querydelete_alembic_version_config = null;
	stringkoneksi sk = new stringkoneksi();
	PreparedStatement queryselect_ps_domain_id = null;
	PreparedStatement queryinsert_ps_domain_aliases = null;
	
	  @CrossOrigin(origins = "*")

	  @GetMapping("/GetIP") 
	  private  String getClientIp(HttpServletRequest request) {

	        String remoteAddr = "";

	        if (request != null) {
	            remoteAddr = request.getHeader("X-FORWARDED-FOR");
	            if (remoteAddr == null || "".equals(remoteAddr)) {
	                remoteAddr = request.getRemoteAddr();
	            }
	        }

	        
	        return remoteAddr;
	    }
	  
	  public void insertip(String ip) throws SQLException
	  {
		  Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			queryinsert_ps_domain_aliases = Connection1
					.prepareStatement("insert into tb_IPCollected(IPClient) values('"+ip+"')");

			int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
			String a = "APPLICATION RECORDING IS STARTING";
			Connection1.close();
	  }
	  
	  @GetMapping(produces = "application/json", path = "/BeginRecording2/{value}") 
//		value=0
		public String BeginRecording2(@PathVariable("value") int value,HttpServletRequest request) throws SQLException 
		{ 
		  String remoteAddr = "";

			try
			{
				 if (request != null) 
				 {
			            remoteAddr = request.getHeader("X-FORWARDED-FOR");
			            if (remoteAddr == null || "".equals(remoteAddr))
						{
			            	
			                remoteAddr = request.getRemoteAddr();
			                //insertip(remoteAddr);
			                Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			    			queryinsert_ps_domain_aliases = Connection1
			    					.prepareStatement("update tb_registerapp set code='"+value+"', status='call',status_apps='recording' where IPClient='"+remoteAddr+"'");
			    			
			    			int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
			    			
			    			Connection1.close();
			    			
			            }
			            
				 }
				 
				 String a = "APPLICATION RECORDING IS STARTING";
	    			
	    			System.out.println("Proses : Operation Recording is Starting");
	    			return "{ " + "\"response\":" + "\"" + a + "\" }";
			}
				 
			
			
			catch(SQLException EX)
			{ 
				
				System.out.print("Proses :"+EX.getMessage());

				return EX.getMessage();
	
			}
			
		}
	  
	  
	  @GetMapping(produces = "application/json", path = "/StopRecording2/{value}/{extentionagent}/{extentionuser}") 
//		value=0
		public String StopRecording2(@PathVariable("value") int value,@PathVariable("extentionagent") int extentionagent,@PathVariable("extentionuser") int extentionuser,HttpServletRequest request) throws SQLException 
		{ 
		  String remoteAddr = "";

			try
			{
				 if (request != null) 
				 {
			            remoteAddr = request.getHeader("X-FORWARDED-FOR");
			            if (remoteAddr == null || "".equals(remoteAddr))
						{
			            	
			                remoteAddr = request.getRemoteAddr();
			                //insertip(remoteAddr);
			                Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			    			queryinsert_ps_domain_aliases = Connection1
			    					.prepareStatement("update tb_registerapp set code='"+value+"', status='AfterCall',status_apps='combine_video' where IPClient='"+remoteAddr+"'");
			    			
			    			int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
			    			
			    			Connection1.close();
			    			simpanvideoname(extentionagent,extentionuser, remoteAddr);
			            }
			            
				 }
				 
				 String a = "Silahkan Tunggu Sampai Combine Video + Suara Selesai";
	    			
	    			System.out.println("Proses :Silahkan Tunggu Sampai Combine Video + Suara Selesai");
	    			return "{ " + "\"response\":" + "\"" + a + "\" }";
			}
				 
			
			
			catch(SQLException EX)
			{ 
				
				System.out.print("Proses :"+EX.getMessage());

				return EX.getMessage();
			
				
			}
			
		}
	  
	  @GetMapping("/simpan") 


	  public void simpanvideoname2() throws SQLException
	  {
		  Date date = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");

		  String request="a";
		  int extention=12;
		  int extentionuser=11;
				
			                Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			            	queryinsert_ps_domain_aliases = Connection1
			    					.prepareStatement("insert into tb_videoarchive(IPClient, tanggal, extention,extentionuser) values('"+request+"','"+formatter.format(date)+"','"+extention+"','"+extentionuser+"')");

			    			int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
			    			
			    			Connection1.close();
						
				 
				 
		
	  }
	  
	  
	  public void simpanvideoname(int extentionagent,int extentionuser,String request) throws SQLException
	  {

		  Date date = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
				
			                Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
			            	queryinsert_ps_domain_aliases = Connection1
			    					.prepareStatement("insert into tb_videoarchive(IPClient, tanggal, extention,extentionuser) values('"+request+"','"+formatter.format(date)+"','"+extentionagent+"','"+extentionuser+"')");

			    			int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
			    			
			    			Connection1.close();
						
				 
				 
		
	  }
	  
	@GetMapping("/BeginRecording/{value}") 
//	value=0
	public String BeginRecording(@PathVariable("value") int value) throws SQLException 
	{
		try
		{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_domain_aliases = Connection1
				.prepareStatement("update tb_code set code='"+value+"' where id=1");
		
		int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "APPLICATION RECORDING IS STARTING";
		Connection1.close();
		System.out.println("Proses : Operation Recording is Starting");
		return a;
		}
		catch(SQLException EX)
		{ 
			
			System.out.print("Proses :"+EX.getMessage());

			return EX.getMessage();
		
			
		}
	}
	  @CrossOrigin(origins = "*")

	@GetMapping("/StopRecording/{value}")
//	value=1
	public String StopRecording(@PathVariable("value") int value) throws SQLException 
	{
		try
		{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryinsert_ps_domain_aliases = Connection1
				.prepareStatement("update tb_code set code='"+value+"' where id=1");
		
		int Cursor1 = queryinsert_ps_domain_aliases.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "Screen Recording is Stopping";
		Connection1.close();
		System.out.println("Proses : Operation Recording is Stopping");
		return a;
		}
		catch(SQLException EX)
		{ 
			
			System.out.print("Proses :"+EX.getMessage());

			return EX.getMessage();
		
			
		}
	}
	
	
}