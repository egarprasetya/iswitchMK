package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

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
	public String type; // type value / Type.
	public String context;
	public String permit;
	public String deny;
	public String secret;
	public String md5secret;
	public String remotesecret;
	public String transport;

	public String dtmfmode; // sip_dtmf_mode value / Type.
	public String directmedia; // sip_direct_media value v2 / Type.
	public String nat;
	public String callgroup;
	public String pickupgroup;
	public String language;
	public String disallow;
	public String allow;
	public String insecure;
	public String trustrpid;
	public String progressinband; // sip_progressinband value / Type.
	public YesNo_Values promiscredir; // YesNo value / Type.
	public YesNo_Values useclientcode; // YesNo value / Type.
	public String accountcode;
	public String setvar;
	public String callerid;
	public String amaflags;
	public YesNo_Values callcounter; // YesNo value / Type.
	public int busylevel;
	public YesNo_Values allowoverlap; // YesNo value / Type.
	public YesNo_Values allowsubscribe; // YesNo value / Type.
	public YesNo_Values videosupport; // YesNo value / Type.
	public int maxcallbitrate;
	public YesNo_Values rfc2833compensate; // YesNo value / Type.
	public String mailbox;
	public String session_timers; // sip_session_timers value / Type.
	public int session_expires;
	public int session_minse;
	public String session_refresher; // sip_session_refresh value / Type.
	public String t38pt_usertpsource;
	public String regexten;
	public String fromdomain;
	public String fromuser;
	public String qualify;
	public String defaultip;
	public int rtptimeout;
	public int rtpholdtimeout;
	public String sendrpid;
	public String outboundproxy;
	public String callbackextension;
	public int timer1;
	public int timerb;
	public int qualifyfreq;
	public YesNo_Values constantssrc; // YesNo vale / Type.
	public String contact_permit;
	public String contact_deny;
	public YesNo_Values usereqphone; // YesNo vale / Type.
	public YesNo_Values textsupport; // YesNo vale / Type.
	public YesNo_Values faxdetect; // YesNo vale / Type.
	public YesNo_Values buggymwi; // YesNo vale / Type.
	public String auth;
	public String fullname;
	public String trunkname;
	public String cid_number;
	public String callingpres; // sip_callingpres value / Type.
	public String mohinterpret;
	public String mohsuggest;
	public String parkinglot;
	public YesNo_Values hasvoicemail; // YesNo vale / Type.
	public YesNo_Values subscribemwi; // YesNo vale / Type.
	public String vmexten;
	public YesNo_Values autoframing; // YesNo vale / Type.
	public int rtpkeepalive;
	public int call_limit;
	public YesNo_Values g726nonstandard; // YesNo vale / Type.
	public YesNo_Values ignoresdpversion; // YesNo vale / Type.
	public YesNo_Values allowtransfer; // YesNo vale / Type.
	public YesNo_Values dynamic; // YesNo vale / Type.
	public String path;
	public YesNo_Values supportpath; // YesNo vale / Type.
	public String email; // bp_char value / Type.
}
