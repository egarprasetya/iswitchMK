package com.example.demo.model;

public class SippeersModel {
	public int id;
	public String name;
	public String ipaddr;
	public int port;
	public int regseconds;
	public String defaultuser;
	public String fullcontact;
	public String regserver;
	public String useragent;
	public int lastms;
	public String host;
	public String type;					// type value / Type.
	public String context;
	public String permit;
	public String deny;
	public String secret;
	public String md5secret;
	public String remotesecret;
	public String transport;	
	
	public String dtmfmode;				// sip_dtmf_mode value / Type.
	public String directmedia;			// sip_direct_media value v2 / Type.
	public String nat;
	public String callgroup;
	public String pickupgroup;
	public String language;
	public String disallow;
	public String allow;
	public String insecure;
	public boolean trustrpid;
	public String progressinband;		// sip_progressinband value / Type.
	public boolean promiscredir;		// YesNo value / Type.
	public boolean useclientcode;		// YesNo value / Type.
	public String accountcode;
	public String setvar;
	public String callerid;
	public String amaflags;
	public boolean callcounter;			// YesNo value / Type.
	public int busylevel;
	public boolean allowoverlap;		// YesNo value / Type.
	public boolean allowsubscribe;		// YesNo value / Type.
	public boolean videosupport;		// YesNo value / Type.
	public int maxcallbitrate;
	public boolean rfc2833compensate;	// YesNo value / Type.
	public String mailbox;
	public String session_timers;		// sip_session_timers value / Type.
	public int session_expires;
	public int session_minse;
	public String session_refresher;	// sip_session_refresh value / Type.
	public String t38pt_usertpsource;
	public String regexten;
	public String fromdomain;
	public String fromuser;
	public String qualify;
	public String defaultip;
	public int rtptimeout;
	public int rtpholdtimeout;
	public boolean sendrpid;
	public String outboundproxy;
	public String callbackextension;
	public int timer1;
	public int timerb;
	public int qualifyfreq;
	public boolean constantssrc;			// YesNo vale / Type.
	public String contact_permit;
	public String contact_deny;	
	public boolean usereqphone;				// YesNo vale / Type.
	public boolean textsupport;				// YesNo vale / Type.
	public boolean faxdetect;				// YesNo vale / Type.
	public boolean buggymwi;				// YesNo vale / Type.
	public String auth;
	public String fullname;
	public String trunkname;
	public String cid_number;
	public String callingpres;				// sip_callingpres value / Type.
	public String mohinterpret;
	public String mohsuggest;
	public String parkinglot;
	public boolean hasvoicemail;			// YesNo vale / Type.
	public boolean subscribemwi;			// YesNo vale / Type.
	public String vmexten;
	public boolean autoframing;				// YesNo vale / Type.
	public int rtpkeepalive;
	public int call_limit;
	public boolean g726nonstandard;			// YesNo vale / Type.
	public boolean ignoresdpversion;		// YesNo vale / Type.
	public boolean allowtransfer;			// YesNo vale / Type.
	public boolean dynamic;					// YesNo vale / Type.
	public String path;
	public boolean supportpath;				// YesNo vale / Type.
	public String email;					// bp_char value / Type.
}
