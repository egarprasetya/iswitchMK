package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class After_Call_WorkModel
{
	public String src;
	public String dst;
	public String case1;
	public String detail;
	public String uniqueid;

	public String direction;
	public String recorded_video_filename;
	public String recorded_filename;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp datetime;
}
