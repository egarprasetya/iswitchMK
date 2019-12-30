package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserModel
{
	public String user_id;
	public String nama;
	public String username;
	public String password;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp created;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp modified;
	public String email;
	public String password_email;
	public String phone_number;
	public String extensions_user;
	public String skill;
	public String status;
	public String avatar;
	public String websocket;
	public String url_websocket;
	
	// Dummy
	public String old_password;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp date_begin;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp date_end;
}
