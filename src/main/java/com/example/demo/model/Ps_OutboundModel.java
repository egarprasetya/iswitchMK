package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class Ps_OutboundModel
{
	public String id;
	public int expiration;
	public String outbound_auth;
	public String outbound_proxy;
	public String server_uri;
	public String from_uri;
	public String to_uri;
	public String event;
	public int max_auth_attemps;
	public String transport;
	public YesNo_Values multi_user; // YesNo value/Type.
	public String _body;
	public String _context;
	public String _exten;
}
