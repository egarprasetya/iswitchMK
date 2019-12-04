package com.example.demo.model;

import java.sql.Date;
import com.example.demo.Enum.*;

public class VoiceMailModel
{
	public int uniqueid;
	public String context;
	public String mailbox;
	public String password;
	public String fullname;
	public String alias;
	public String email;
	public String pager;
	public YesNo_Values attach; // YesNo value/Type.
	public String attachfmt;
	public String servermail;
	public String language;
	public String tz;
	public YesNo_Values deletevoicemail; // YesNo value/Type.
	public YesNo_Values saycid; // YesNo value/Type.
	public YesNo_Values sendvoicemail; // YesNo value/Type.
	public YesNo_Values review; // YesNo value/Type.
	public YesNo_Values tempgreetwarn; // YesNo value/Type.
	public YesNo_Values operator; // YesNo value/Type.
	public YesNo_Values envelope; // YesNo value/Type.
	public int sayduration;
	public YesNo_Values forcename; // YesNo value/Type.
	public YesNo_Values forcegreetings; // YesNo value/Type.
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
	public Date stamp; // Timestamp value / Type.
}
