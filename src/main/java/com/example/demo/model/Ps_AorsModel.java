package com.example.demo.model;

import com.example.demo.Enum.*;
import com.example.demo.Enum.yesenum.yesno_values;

public class Ps_AorsModel
{
	YesNo_Values po;
	yesno_values ys;
	public String id;
	public String contact;
	public int default_expiration;
	public String mailboxes;
	public int max_contacts;
	public int minimum_expiration;

	public YesNo_Values remove_existing;
	// public String remove_existings;// YesNo value/Type.
	// public String remove_existing; // YesNo value/Type.
	public int qualify_frequency;
	public YesNo_Values authenticate_qualify; // YesNo value/Type.
	// public String authenticate_qualify; // YesNo value/Type.
	public int maximum_expiration;
	public String outbound_proxy;
	public YesNo_Values support_path;
	public double qualify_timeout;
	public String voicemail_extension;
	public String a;
	public String b;
	public String c;

}
