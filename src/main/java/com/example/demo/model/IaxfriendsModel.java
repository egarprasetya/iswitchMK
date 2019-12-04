package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;
import com.example.demo.Enum.iax_encryption_values;
import com.example.demo.Enum.iax_requirecalltoken_values;
import com.example.demo.Enum.iax_transfer_values;
import com.example.demo.Enum.type_values;

public class IaxfriendsModel
{
	public int id;
	public String name;
	public type_values type; // type_value Type.
	public String username;
	public String mailbox;
	public String secret;
	public String dbsecret;
	public String context;
	public String regcontext;
	public String host;
	public String ipaddr;
	public int port;
	public String defaultip;
	public String sourceaddress;
	public String mask;
	public String regexten;
	public int regseconds;
	public String accountcode;
	public String mohinterpret;
	public String mohsuggest;
	public String inkeys;
	public String outkeys;
	public String language;
	public String callerid;
	public String cid_number;
	public YesNo_Values sendani; // YesNo Value/Type.
	public YesNo_Values fullname; // YesNo Value/Type
	public String trunk;
	public String auth;
	public int maxauthreq;
	public iax_requirecalltoken_values requirecalltoken; // iax_requirecalltoken value/Type.
	public iax_encryption_values encryption; // iax_encryption value/Type.
	public iax_transfer_values transfer; // iax_transfer value/Type.
	public YesNo_Values jitterbuffer; // YesNo Value/Type.
	public YesNo_Values forcejitterbuffer; // YesNo Value/Type.
	public String disallow;
	public String allow;
	public String codecpriority;
	public String qualify;
	public YesNo_Values qualifysmoothing; // YesNo Value/Type
	public String qualifyfreqok;
	public String qualifyfreqnotok;
	public String timezone;
	public YesNo_Values adsi; // YesNo Value/Type.
	public String amaflags;
	public String setvar;
}
