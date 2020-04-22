package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserModel
{
	public int user_id;
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
	public String extensions_user_baru;
	public String old_password;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp date_begin;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp date_end;
	public String queue;
	public String ipSSH;
	public String userSSH;
	public String passwordSSH;
	public String comandSSH;
	public String count;
	
	// Email
	public String host_imap;
	public String port_host_imap;
	public String ssl_imap;
	public String host_smtp;
	public String port_host_smtp;
	public String ssl_smtp;
}
