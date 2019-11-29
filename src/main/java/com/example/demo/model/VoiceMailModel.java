package com.example.demo.model;

import java.sql.Date;

public class VoiceMailModel {
	public int uniqueid;
	public String context;
	public String mailbox;
	public String password;
	public String fullname;
	public String alias;
	public String email;
	public String pager;
	public String attach;					// YesNo value/Type.
	public String attachfmt;
	public String servermail;
	public String language;
	public String tz;
	public String deletevoicemail;			// YesNo value/Type.
	public String saycid;					// YesNo value/Type.
	public String sendvoicemail;			// YesNo value/Type.
	public String review;					// YesNo value/Type.
	public String tempgreetwarn;			// YesNo value/Type.
	public String operator;				// YesNo value/Type.
	public String envelope;				// YesNo value/Type.
	public int sayduration;
	public String forcename;				// YesNo value/Type.
	public String forcegreetings;			// YesNo value/Type.
	public String callback;
	public String dialout;
	public String exitcontext;
	public int maxmsg;
	public int volgain;
	public String imapuser;
	public String imappassword;
	public String imapserver;
	public String imapport;
	public String imapflags;
	public Date stamp;						// Timestamp value / Type.
}
