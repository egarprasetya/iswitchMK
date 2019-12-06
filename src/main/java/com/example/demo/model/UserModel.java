package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserModel
{
	public String user_id;
	public String nama;
	public String username;
	public String password;
	public Date created;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date modified;
	public String email;
	public String password_email;
	public String phone_number;
	public String extensions_user;
	public String skill;
	public String status;
	public String avatar;
}
