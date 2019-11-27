package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.query.*;
import com.example.demo.model.UserModel;
import com.example.demo.model.*;
import com.example.demo.connection.*;
@RestController
@RequestMapping("/Sippeers")
public class SippeersController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_sippeers=null;
	@GetMapping("/GetSippeers")
	public ArrayList<SippeersModel> TampilSippeers() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_sippeers=Connection1.prepareStatement(query_string.query_select_sippeers);
        ResultSet Cursor1 = queryselect_sippeers.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<SippeersModel> ListUser1 = new ArrayList<SippeersModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  SippeersModel ModelSippeers=new SippeersModel();
        	  ModelSippeers.id = Cursor1.getInt(1);
        	  ModelSippeers.name = Cursor1.getString(2);
        		ModelSippeers.ipaddr = Cursor1.getString(3);
        		ModelSippeers.port = Cursor1.getInt(4);
        		ModelSippeers.regseconds = Cursor1.getInt(5);
        		ModelSippeers.defaultuser = Cursor1.getString(6);
        		ModelSippeers.fullcontact = Cursor1.getString(7);
        		ModelSippeers.regserver = Cursor1.getString(8);
        		ModelSippeers.useragent = Cursor1.getString(9);
        		ModelSippeers.lastms = Cursor1.getInt(10);
        		ModelSippeers.host = Cursor1.getString(11);
        		ModelSippeers.type = Cursor1.getString(12);					// type value / Type.
        		ModelSippeers.context = Cursor1.getString(13);
        		ModelSippeers.permit = Cursor1.getString(14);
        		ModelSippeers.deny = Cursor1.getString(15);
        		ModelSippeers.secret = Cursor1.getString(16);
        		ModelSippeers.md5secret = Cursor1.getString(17);
        		ModelSippeers.remotesecret = Cursor1.getString(18);
        		ModelSippeers.transport = Cursor1.getString(19);			// sip_transport value / Type.
        		ModelSippeers.dtmfmode = Cursor1.getString(20);				// sip_dtmf_mode value / Type.
        		ModelSippeers.directmedia = Cursor1.getString(21);			// sip_direct_media value v2 / Type.
        		ModelSippeers.nat = Cursor1.getString(22);
        		ModelSippeers.callgroup = Cursor1.getString(23);
        		ModelSippeers.pickupgroup = Cursor1.getString(24);
        		ModelSippeers.language = Cursor1.getString(25);
        		ModelSippeers.disallow = Cursor1.getString(26);
        		ModelSippeers.allow = Cursor1.getString(27);
        		ModelSippeers.insecure = Cursor1.getString(28);
        		ModelSippeers.trustrpid = Cursor1.getBoolean(29);
        		ModelSippeers.progressinband = Cursor1.getString(30);		// sip_progressinband value / Type.
        		ModelSippeers.promiscredir = Cursor1.getBoolean(31);		// YesNo value / Type.
        		ModelSippeers.useclientcode = Cursor1.getBoolean(32);		// YesNo value / Type.
        		ModelSippeers.accountcode = Cursor1.getString(33);
        		ModelSippeers.setvar = Cursor1.getString(34);
        		ModelSippeers.callerid = Cursor1.getString(35);
        		ModelSippeers.amaflags = Cursor1.getString(36);
        		ModelSippeers.callcounter = Cursor1.getBoolean(37);			// YesNo value / Type.
        		ModelSippeers.busylevel = Cursor1.getInt(38);
        		ModelSippeers.allowoverlap = Cursor1.getBoolean(39);		// YesNo value / Type.
        		ModelSippeers.allowsubscribe = Cursor1.getBoolean(40);		// YesNo value / Type.
        		ModelSippeers.videosupport = Cursor1.getBoolean(41);		// YesNo value / Type.
        		ModelSippeers.maxcallbitrate = Cursor1.getInt(42);
        		ModelSippeers.rfc2833compensate = Cursor1.getBoolean(43);	// YesNo value / Type.
        		ModelSippeers.mailbox = Cursor1.getString(44);
        		ModelSippeers.session_timers = Cursor1.getString(45);		// sip_session_timers value / Type.
        		ModelSippeers.session_expires = Cursor1.getInt(46);
        		ModelSippeers.session_minse = Cursor1.getInt(47);
        		ModelSippeers.session_refresher = Cursor1.getString(48);	// sip_session_refresh value / Type.
        		ModelSippeers.t38pt_usertpsource = Cursor1.getString(49);
        		ModelSippeers.regexten = Cursor1.getString(50);
        		ModelSippeers.fromdomain = Cursor1.getString(51);
        		ModelSippeers.fromuser = Cursor1.getString(52);
        		ModelSippeers.qualify = Cursor1.getString(53);
        		ModelSippeers.defaultip = Cursor1.getString(54);
        		ModelSippeers.rtptimeout = Cursor1.getInt(55);
        		ModelSippeers.rtpholdtimeout = Cursor1.getInt(56);
        		ModelSippeers.sendrpid = Cursor1.getBoolean(57);
        		ModelSippeers.outboundproxy = Cursor1.getString(58);
        		ModelSippeers.callbackextension = Cursor1.getString(59);
        		ModelSippeers.timer1 = Cursor1.getInt(60);
        		ModelSippeers.timerb = Cursor1.getInt(61);
        		ModelSippeers.qualifyfreq = Cursor1.getInt(62);
        		ModelSippeers.constantssrc = Cursor1.getBoolean(63);			// YesNo vale / Type.
        		ModelSippeers.contact_permit = Cursor1.getString(64);
        		ModelSippeers.contact_deny = Cursor1.getString(65);	
        		ModelSippeers.usereqphone = Cursor1.getBoolean(66);				// YesNo vale / Type.
        		ModelSippeers.textsupport = Cursor1.getBoolean(67);				// YesNo vale / Type.
        		ModelSippeers.faxdetect = Cursor1.getBoolean(68);				// YesNo vale / Type.
        		ModelSippeers.buggymwi = Cursor1.getBoolean(69);				// YesNo vale / Type.
        		ModelSippeers.auth = Cursor1.getString(70);
        		ModelSippeers.fullname = Cursor1.getString(71);
        		ModelSippeers.trunkname = Cursor1.getString(72);
        		ModelSippeers.cid_number = Cursor1.getString(73);
        		ModelSippeers.callingpres = Cursor1.getString(74);				// sip_callingpres value / Type.
        		ModelSippeers.mohinterpret = Cursor1.getString(75);
        		ModelSippeers.mohsuggest = Cursor1.getString(76);
        		ModelSippeers.parkinglot = Cursor1.getString(77);
        		ModelSippeers.hasvoicemail = Cursor1.getBoolean(78);			// YesNo vale / Type.
        		ModelSippeers.subscribemwi = Cursor1.getBoolean(79);			// YesNo vale / Type.
        		ModelSippeers.vmexten = Cursor1.getString(80);
        		ModelSippeers.autoframing = Cursor1.getBoolean(81);				// YesNo vale / Type.
        		ModelSippeers.rtpkeepalive = Cursor1.getInt(82);
        		ModelSippeers.call_limit = Cursor1.getInt(83);
        		ModelSippeers.g726nonstandard = Cursor1.getBoolean(84);			// YesNo vale / Type.
        		ModelSippeers.ignoresdpversion = Cursor1.getBoolean(85);		// YesNo vale / Type.
        		ModelSippeers.allowtransfer = Cursor1.getBoolean(86);			// YesNo vale / Type.
        		ModelSippeers.dynamic = Cursor1.getBoolean(87);					// YesNo vale / Type.
        		ModelSippeers.path = Cursor1.getString(88);
        		ModelSippeers.supportpath = Cursor1.getBoolean(89);				// YesNo vale / Type.
        		ModelSippeers.email = Cursor1.getString(90);
         ListUser1.add(ModelSippeers);  
          return ListUser1;
          }          
          Connection1.close();
          SippeersModel ModelSippeers=new SippeersModel();	          
          ListUser1.add(ModelSippeers);  
          return ListUser1;
	}
	
	@PostMapping("/DeletePostSippeers")
	public int DeletePostAlembicVersionConfig(@RequestBody int id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_sippeers);
		 querydelete_alembic_version_config.setInt(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	
	@GetMapping("/DeleteGetSippeers")
	public int DeleteGetAlembicVersionConfig(@RequestBody int id) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_sippeers);
		 querydelete_alembic_version_config.setInt(1, id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
}
