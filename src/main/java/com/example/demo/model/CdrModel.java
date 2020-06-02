package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CdrModel
{
	public String accountcode;
	public String src;
	public String dst;
	public String dcontext;
	public String clid;
	public String channel;
	public String dstchannel;
	public String lastapp;
	public String lastdata;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp start;
	public Date answer;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp end;
	public int duration;
	public int billsec;
	public String disposition;
	public String amaflags;
	public String userfield;
	public String uniqueid;
	public String linkedid;
	public String peeraccount;
	public int sequence;

	public String cdrCase;
	public String detail;
	public Date tanggal1;
	public Date tanggal2;
	public String format;
	
	
	//Dummy
	public String extensions_user;

}
