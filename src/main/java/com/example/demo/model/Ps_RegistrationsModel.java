package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class Ps_RegistrationsModel
{
	public String id;
	public YesNo_Values auth_rejection_permanent; // YesNo value/Type.
	public String client_uri;
	public String contact_user;
	public int expiration;
	public int max_retries;
	public String outbound_auth;
	public String outbound_proxy;
	public int retry_interval;
	public int forbidden_retry_interval;
	public String server_uri;
	public String transport;
	public String support_path;
	public int fatal_retry_interval;
	public YesNo_Values line; // YesNo value/Type.
	public String endpoint;
}