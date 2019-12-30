package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User_HistoryModel
{
	public String id_user_history;
	public String extension;
	public String nama_user;
	public String reason;
	public String status;
	public String skill;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp date_begin;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp date_end;
}
