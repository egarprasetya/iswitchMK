package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BMIModel
{
	public String conversationId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp requestTimeStamp;
	public String acctNo;
}
