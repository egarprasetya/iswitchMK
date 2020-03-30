package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Message_OdbcModel
{
	public String serial_id;
	public String uniqueid;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	public Timestamp calldate;
	public String msg_context;
	public String flag_status;
	
	public String src;
	public String dst;
	
	public String duration;
}
