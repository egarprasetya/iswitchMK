package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class Ps_Subscription_PersistenceModel
{
	public String id;
	public String packet;
	public String src_name;
	public int src_port;
	public String transport_key;
	public String local_name;
	public int local_port;
	public int cseq;
	public String tag;
	public String endpoint;
	public int expires;
	public String contact_uri;
	public YesNo_Values prune_on_boot; // YesNo value/Type.
}
