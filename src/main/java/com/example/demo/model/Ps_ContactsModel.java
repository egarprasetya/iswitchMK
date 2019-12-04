package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class Ps_ContactsModel {
	public String id;
	public String uri;
	public int expiration_time;
	public int qualify_frequency;
	public String outbond_proxy;
	public String path;
	public String user_agent;
	public double qualify_timeout;
	public String reg_server;
	public YesNo_Values authenticate_qualify;	// YesNo Type
	public String via_addr;
	public int via_port;
	public String call_id;
	public String endpoint;
	public String prune_on_boot;		
}
