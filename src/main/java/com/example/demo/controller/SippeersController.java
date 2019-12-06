package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.query.*;
import com.example.demo.model.*;
import com.example.demo.Enum.YesNo_Values;
import com.example.demo.Enum.sip_callingpres_values;
import com.example.demo.Enum.sip_directmedia_values_v2;
import com.example.demo.Enum.sip_dtmfmode_values;
import com.example.demo.Enum.sip_progressinband_values;
import com.example.demo.Enum.sip_session_refresher_values;
import com.example.demo.Enum.sip_session_timers_values;
import com.example.demo.Enum.type_values;
import com.example.demo.connection.*;

@RestController
@RequestMapping(produces = "application/json", path = "/Sippeers")
public class SippeersController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_sippeers = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_sippeers = null;

	@PutMapping("/putSippeers")
	public String putSippeers(@RequestBody SippeersModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_sippeers = Connection1.prepareStatement(query_string_insert.query_insert_sippeers);

		query_insert_sippeers.setInt(1, cfm.id);
		query_insert_sippeers.setString(2, cfm.name);
		query_insert_sippeers.setString(3, cfm.ipaddr);
		query_insert_sippeers.setInt(4, cfm.port);
		query_insert_sippeers.setInt(5, cfm.regseconds);
		query_insert_sippeers.setString(6, cfm.defaultuser);
		query_insert_sippeers.setString(7, cfm.fullcontact);
		query_insert_sippeers.setString(8, cfm.regserver);
		query_insert_sippeers.setString(9, cfm.useragent);
		query_insert_sippeers.setInt(10, cfm.lastms);
		query_insert_sippeers.setString(11, cfm.host);
		query_insert_sippeers.setString(12, cfm.type.toString());
		query_insert_sippeers.setString(13, cfm.context);
		query_insert_sippeers.setString(14, cfm.permit);
		query_insert_sippeers.setString(15, cfm.deny);
		query_insert_sippeers.setString(16, cfm.secret);
		query_insert_sippeers.setString(17, cfm.md5secret);
		query_insert_sippeers.setString(18, cfm.remotesecret);
		query_insert_sippeers.setString(19, cfm.transport);
		query_insert_sippeers.setString(20, cfm.dtmfmode.toString());
		query_insert_sippeers.setString(21, cfm.directmedia.toString());
		query_insert_sippeers.setString(22, cfm.nat);
		query_insert_sippeers.setString(23, cfm.callgroup);
		query_insert_sippeers.setString(24, cfm.pickupgroup);
		query_insert_sippeers.setString(25, cfm.language);
		query_insert_sippeers.setString(26, cfm.disallow);
		query_insert_sippeers.setString(27, cfm.allow);
		query_insert_sippeers.setString(28, cfm.insecure);
		query_insert_sippeers.setString(29, cfm.trustrpid);
		query_insert_sippeers.setString(30, cfm.progressinband.toString());
		query_insert_sippeers.setString(31, cfm.promiscredir.toString());
		query_insert_sippeers.setString(32, cfm.useclientcode.toString());
		query_insert_sippeers.setString(33, cfm.accountcode);
		query_insert_sippeers.setString(34, cfm.setvar);
		query_insert_sippeers.setString(35, cfm.callerid);
		query_insert_sippeers.setString(36, cfm.amaflags);
		query_insert_sippeers.setString(37, cfm.callcounter.toString());
		query_insert_sippeers.setInt(38, cfm.busylevel);
		query_insert_sippeers.setString(39, cfm.allowoverlap.toString());
		query_insert_sippeers.setString(40, cfm.allowsubscribe.toString());
		query_insert_sippeers.setString(41, cfm.videosupport.toString());
		query_insert_sippeers.setInt(42, cfm.maxcallbitrate);
		query_insert_sippeers.setString(43, cfm.rfc2833compensate.toString());
		query_insert_sippeers.setString(44, cfm.mailbox);
		query_insert_sippeers.setString(45, cfm.session_timers.toString());
		query_insert_sippeers.setInt(46, cfm.session_expires);
		query_insert_sippeers.setInt(47, cfm.session_minse);
		query_insert_sippeers.setString(48, cfm.session_refresher.toString());
		query_insert_sippeers.setString(49, cfm.t38pt_usertpsource);
		query_insert_sippeers.setString(50, cfm.regexten);
		query_insert_sippeers.setString(51, cfm.fromdomain);
		query_insert_sippeers.setString(52, cfm.fromuser);
		query_insert_sippeers.setString(53, cfm.qualify);
		query_insert_sippeers.setString(54, cfm.defaultip);
		query_insert_sippeers.setInt(55, cfm.rtptimeout);
		query_insert_sippeers.setInt(56, cfm.rtpholdtimeout);
		query_insert_sippeers.setString(57, cfm.sendrpid);
		query_insert_sippeers.setString(58, cfm.outboundproxy);
		query_insert_sippeers.setString(59, cfm.callbackextension);
		query_insert_sippeers.setInt(60, cfm.timer1);
		query_insert_sippeers.setInt(61, cfm.timerb);
		query_insert_sippeers.setInt(62, cfm.qualifyfreq);
		query_insert_sippeers.setString(63, cfm.constantssrc.toString());
		query_insert_sippeers.setString(64, cfm.contact_permit);
		query_insert_sippeers.setString(65, cfm.contact_deny);
		query_insert_sippeers.setString(66, cfm.usereqphone.toString());
		query_insert_sippeers.setString(67, cfm.textsupport.toString());
		query_insert_sippeers.setString(68, cfm.faxdetect.toString());
		query_insert_sippeers.setString(69, cfm.buggymwi.toString());
		query_insert_sippeers.setString(70, cfm.auth);
		query_insert_sippeers.setString(71, cfm.fullname);
		query_insert_sippeers.setString(72, cfm.trunkname);
		query_insert_sippeers.setString(73, cfm.cid_number);
		query_insert_sippeers.setString(74, cfm.callingpres.toString());
		query_insert_sippeers.setString(75, cfm.mohinterpret);
		query_insert_sippeers.setString(76, cfm.mohsuggest);
		query_insert_sippeers.setString(77, cfm.parkinglot);
		query_insert_sippeers.setString(78, cfm.hasvoicemail.toString());
		query_insert_sippeers.setString(79, cfm.subscribemwi.toString());
		query_insert_sippeers.setString(80, cfm.vmexten);
		query_insert_sippeers.setString(81, cfm.autoframing.toString());
		query_insert_sippeers.setInt(82, cfm.rtpkeepalive);
		query_insert_sippeers.setInt(83, cfm.call_limit);
		query_insert_sippeers.setString(84, cfm.g726nonstandard.toString());
		query_insert_sippeers.setString(85, cfm.ignoresdpversion.toString());
		query_insert_sippeers.setString(86, cfm.allowtransfer.toString());
		query_insert_sippeers.setString(87, cfm.dynamic.toString());
		query_insert_sippeers.setString(88, cfm.path);
		query_insert_sippeers.setString(89, cfm.supportpath.toString());
		// query_insert_sippeers.setString(90, cfm.email.toString());

		int Cursor1 = query_insert_sippeers.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/getSippeers")
	public ArrayList<SippeersModel> getSippeers() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_sippeers = Connection1.prepareStatement(query_string.query_select_sippeers);
		ResultSet Cursor1 = queryselect_sippeers.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<SippeersModel> ListUser1 = new ArrayList<SippeersModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			SippeersModel ModelSippeers = new SippeersModel();
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
			ModelSippeers.type = type_values.valueOf(Cursor1.getString(12)); // type value / Type.
			ModelSippeers.context = Cursor1.getString(13);
			ModelSippeers.permit = Cursor1.getString(14);
			ModelSippeers.deny = Cursor1.getString(15);
			ModelSippeers.secret = Cursor1.getString(16);
			ModelSippeers.md5secret = Cursor1.getString(17);
			ModelSippeers.remotesecret = Cursor1.getString(18);
			ModelSippeers.transport = Cursor1.getString(19); // sip_transport value / Type.
			ModelSippeers.dtmfmode = sip_dtmfmode_values.valueOf(Cursor1.getString(20)); // sip_dtmf_mode value / Type.
			ModelSippeers.directmedia = sip_directmedia_values_v2.valueOf(Cursor1.getString(21)); // sip_direct_media value v2
																																														// / Type.
			ModelSippeers.nat = Cursor1.getString(22);
			ModelSippeers.callgroup = Cursor1.getString(23);
			ModelSippeers.pickupgroup = Cursor1.getString(24);
			ModelSippeers.language = Cursor1.getString(25);
			ModelSippeers.disallow = Cursor1.getString(26);
			ModelSippeers.allow = Cursor1.getString(27);
			ModelSippeers.insecure = Cursor1.getString(28);
			ModelSippeers.trustrpid = Cursor1.getString(29);
			ModelSippeers.progressinband = sip_progressinband_values.valueOf(Cursor1.getString(30)); // sip_progressinband
																																																// value / Type.
			ModelSippeers.promiscredir = YesNo_Values.valueOf(Cursor1.getString(31)); // YesNo value / Type.
			ModelSippeers.useclientcode = YesNo_Values.valueOf(Cursor1.getString(32)); // YesNo value / Type.
			ModelSippeers.accountcode = Cursor1.getString(33);
			ModelSippeers.setvar = Cursor1.getString(34);
			ModelSippeers.callerid = Cursor1.getString(35);
			ModelSippeers.amaflags = Cursor1.getString(36);
			ModelSippeers.callcounter = YesNo_Values.valueOf(Cursor1.getString(37)); // YesNo value / Type.
			ModelSippeers.busylevel = Cursor1.getInt(38);
			ModelSippeers.allowoverlap = YesNo_Values.valueOf(Cursor1.getString(39)); // YesNo value / Type.
			ModelSippeers.allowsubscribe = YesNo_Values.valueOf(Cursor1.getString(40)); // YesNo value / Type.
			ModelSippeers.videosupport = YesNo_Values.valueOf(Cursor1.getString(41)); // YesNo value / Type.
			ModelSippeers.maxcallbitrate = Cursor1.getInt(42);
			ModelSippeers.rfc2833compensate = YesNo_Values.valueOf(Cursor1.getString(43)); // YesNo value / Type.
			ModelSippeers.mailbox = Cursor1.getString(44);
			ModelSippeers.session_timers = sip_session_timers_values.valueOf(Cursor1.getString(45)); // sip_session_timers
																																																// value / Type.
			ModelSippeers.session_expires = Cursor1.getInt(46);
			ModelSippeers.session_minse = Cursor1.getInt(47);
			ModelSippeers.session_refresher = sip_session_refresher_values.valueOf(Cursor1.getString(48)); // sip_session_refresh
																																																			// value / Type.
			ModelSippeers.t38pt_usertpsource = Cursor1.getString(49);
			ModelSippeers.regexten = Cursor1.getString(50);
			ModelSippeers.fromdomain = Cursor1.getString(51);
			ModelSippeers.fromuser = Cursor1.getString(52);
			ModelSippeers.qualify = Cursor1.getString(53);
			ModelSippeers.defaultip = Cursor1.getString(54);
			ModelSippeers.rtptimeout = Cursor1.getInt(55);
			ModelSippeers.rtpholdtimeout = Cursor1.getInt(56);
			ModelSippeers.sendrpid = Cursor1.getString(57);
			ModelSippeers.outboundproxy = Cursor1.getString(58);
			ModelSippeers.callbackextension = Cursor1.getString(59);
			ModelSippeers.timer1 = Cursor1.getInt(60);
			ModelSippeers.timerb = Cursor1.getInt(61);
			ModelSippeers.qualifyfreq = Cursor1.getInt(62);
			ModelSippeers.constantssrc = YesNo_Values.valueOf(Cursor1.getString(63)); // YesNo vale / Type.
			ModelSippeers.contact_permit = Cursor1.getString(64);
			ModelSippeers.contact_deny = Cursor1.getString(65);
			ModelSippeers.usereqphone = YesNo_Values.valueOf(Cursor1.getString(66)); // YesNo vale / Type.
			ModelSippeers.textsupport = YesNo_Values.valueOf(Cursor1.getString(67)); // YesNo vale / Type.
			ModelSippeers.faxdetect = YesNo_Values.valueOf(Cursor1.getString(68)); // YesNo vale / Type.
			ModelSippeers.buggymwi = YesNo_Values.valueOf(Cursor1.getString(69)); // YesNo vale / Type.
			ModelSippeers.auth = Cursor1.getString(70);
			ModelSippeers.fullname = Cursor1.getString(71);
			ModelSippeers.trunkname = Cursor1.getString(72);
			ModelSippeers.cid_number = Cursor1.getString(73);
			ModelSippeers.callingpres = sip_callingpres_values.valueOf(Cursor1.getString(74)); // sip_callingpres value /
																																													// Type.
			ModelSippeers.mohinterpret = Cursor1.getString(75);
			ModelSippeers.mohsuggest = Cursor1.getString(76);
			ModelSippeers.parkinglot = Cursor1.getString(77);
			ModelSippeers.hasvoicemail = YesNo_Values.valueOf(Cursor1.getString(78)); // YesNo vale / Type.
			ModelSippeers.subscribemwi = YesNo_Values.valueOf(Cursor1.getString(79)); // YesNo vale / Type.
			ModelSippeers.vmexten = Cursor1.getString(80);
			ModelSippeers.autoframing = YesNo_Values.valueOf(Cursor1.getString(81)); // YesNo vale / Type.
			ModelSippeers.rtpkeepalive = Cursor1.getInt(82);
			ModelSippeers.call_limit = Cursor1.getInt(83);
			ModelSippeers.g726nonstandard = YesNo_Values.valueOf(Cursor1.getString(84)); // YesNo vale / Type.
			ModelSippeers.ignoresdpversion = YesNo_Values.valueOf(Cursor1.getString(85)); // YesNo vale / Type.
			ModelSippeers.allowtransfer = YesNo_Values.valueOf(Cursor1.getString(86)); // YesNo vale / Type.
			ModelSippeers.dynamic = YesNo_Values.valueOf(Cursor1.getString(87)); // YesNo vale / Type.
			ModelSippeers.path = Cursor1.getString(88);
			ModelSippeers.supportpath = YesNo_Values.valueOf(Cursor1.getString(89)); // YesNo vale / Type.
			ModelSippeers.email = Cursor1.getString(90);
			ListUser1.add(ModelSippeers);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteSippeers", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteSippeers(@RequestBody SippeersModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_sippeers);
		querydelete_alembic_version_config.setInt(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 1;
		Connection1.close();
		return a;
	}

}
