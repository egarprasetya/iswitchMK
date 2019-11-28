package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/psendpoint")
public class ps_endpointsController {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_psendpoint=null;
	@GetMapping("/Getpsendpoint")
	public ArrayList<ps_endpointsModel> TampilAlembicVersionConfig() throws SQLException
	{
		 Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		 queryselect_psendpoint=Connection1.prepareStatement(query_string.query_select_ps_endpoints);
	        ResultSet Cursor1 = queryselect_psendpoint.executeQuery();// Evaluate (Connected_Expression1)
	      	  ArrayList<ps_endpointsModel> ListUser1 = new ArrayList<ps_endpointsModel>();
	          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
	          {       
	        	  ps_endpointsModel ModelPs_enpoints=new ps_endpointsModel();
	        	  ModelPs_enpoints.id=Cursor1.getString(1);
	        		ModelPs_enpoints.transport=Cursor1.getString(2);
	        		ModelPs_enpoints.aors=Cursor1.getString(3);
	        		ModelPs_enpoints.auth=Cursor1.getString(4);
	        		ModelPs_enpoints.context=Cursor1.getString(5);
	        		ModelPs_enpoints.disallow=Cursor1.getString(6);
	        		ModelPs_enpoints.allow=Cursor1.getString(7);
	        		ModelPs_enpoints.direct_media=Cursor1.getBoolean(8);			// YesNo value / Type.
	        		ModelPs_enpoints.connected_line_method=Cursor1.getString(9);	// pjsip_connected_line_method value/type.
	        		ModelPs_enpoints.direct_media_method=Cursor1.getString(10);		// pjsip_connected_line_method value/type.
	        		ModelPs_enpoints.direct_media_glare_mitigation=Cursor1.getString(11);		// pjsip_direct_media_glare_mitigation value/Type.
	        		ModelPs_enpoints.disable_direct_media_on_nat=Cursor1.getBoolean(12);
	        		ModelPs_enpoints.dtmf_mode=Cursor1.getString(13);				// pjsip_dtmf_mode value/Type.
	        		ModelPs_enpoints.external_media_address=Cursor1.getString(14);
	        		ModelPs_enpoints.force_rport=Cursor1.getBoolean(15);
	        		ModelPs_enpoints.ice_support=Cursor1.getBoolean(16);
	        		ModelPs_enpoints.identify_by=Cursor1.getString(17);
	        		ModelPs_enpoints.mailboxes=Cursor1.getString(18);
	        		ModelPs_enpoints.moh_suggest=Cursor1.getString(19);
	        		ModelPs_enpoints.outbound_auth=Cursor1.getString(20);
	        		ModelPs_enpoints.outbounf_proxy=Cursor1.getString(21);
	        		ModelPs_enpoints.rewrite_contact=Cursor1.getBoolean(22);
	        		ModelPs_enpoints.rtp_ipv6=Cursor1.getBoolean(23);
	        		ModelPs_enpoints.rtp_symmetric=Cursor1.getBoolean(24);
	        		ModelPs_enpoints.send_diversion=Cursor1.getBoolean(25);
	        		ModelPs_enpoints.send_pai=Cursor1.getBoolean(26);
	        		ModelPs_enpoints.send_rpid=Cursor1.getBoolean(27);
	        		ModelPs_enpoints.timers_min_se=Cursor1.getInt(28);
	        		ModelPs_enpoints.timers=Cursor1.getString(29);				// pjsip_timer value/Type.
	        		ModelPs_enpoints.timers_sess_expires=Cursor1.getInt(30);
	        		ModelPs_enpoints.callerid=Cursor1.getString(31);
	        		ModelPs_enpoints.callerid_privacy=Cursor1.getString(32);		// pjsip_cid_privacy value/Type.
	        		ModelPs_enpoints.callerid_tag=Cursor1.getString(33);
	        		ModelPs_enpoints._100rel=Cursor1.getString(34);				// pjsip_100rel value/Type.
	        		ModelPs_enpoints.aggregate_mwi=Cursor1.getBoolean(35);		// YesNo Value/Type
	        		ModelPs_enpoints.trust_id_inbound=Cursor1.getBoolean(36);	// YesNo Value/Type
	        		ModelPs_enpoints.trust_id_outbound=Cursor1.getBoolean(37);	// YesNo Value/Type
	        		ModelPs_enpoints.use_ptime=Cursor1.getBoolean(38);			// YesNo Value/Type
	        		ModelPs_enpoints.use_avpf=Cursor1.getBoolean(39);			// YesNo Value/Type
	        		ModelPs_enpoints.media_encryption=Cursor1.getString(40);
	        		ModelPs_enpoints.inband_progress=Cursor1.getBoolean(41);		// YesNo Value/Type
	        		ModelPs_enpoints.call_group=Cursor1.getString(42);
	        		ModelPs_enpoints.pickup_group=Cursor1.getString(43);
	        		ModelPs_enpoints.named_call_group=Cursor1.getString(44);
	        		ModelPs_enpoints.named_pickup_group=Cursor1.getString(45);
	        		ModelPs_enpoints.device_state_busy_at=Cursor1.getInt(46);
	        		ModelPs_enpoints.fax_detect=Cursor1.getBoolean(47);			// YesNo Value/Type
	        		ModelPs_enpoints.t38_udptl=Cursor1.getBoolean(48);			// YesNo Value/Type
	        		ModelPs_enpoints.t38_udptl_ec=Cursor1.getString(49);			// pjsip_t38_udptl value/type.
	        		ModelPs_enpoints.t38_udptl_maxdatagram=Cursor1.getInt(50);
	        		ModelPs_enpoints.t38_udptl_nat=Cursor1.getBoolean(51);		// YesNo value/Type.
	        		ModelPs_enpoints.t38_udptl_ipv6=Cursor1.getBoolean(52);		// YesNo value/Type.
	        		ModelPs_enpoints.tone_zone=Cursor1.getString(53);
	        		ModelPs_enpoints.language=Cursor1.getString(54);
	        		ModelPs_enpoints.one_touch_recording=Cursor1.getBoolean(55); // YesNo Value/Type
	        		ModelPs_enpoints.record_on_feature=Cursor1.getString(56);
	        		ModelPs_enpoints.record_off_feature=Cursor1.getString(57);
	        		ModelPs_enpoints.rtp_engine=Cursor1.getString(58);
	        		ModelPs_enpoints.allow_transfer=Cursor1.getBoolean(59);		// YesNo Value/Type
	        		ModelPs_enpoints.allow_subscribe=Cursor1.getBoolean(60);		// YesNo value/Type.
	        		ModelPs_enpoints. sdp_owner=Cursor1.getString(61);
	        		ModelPs_enpoints.sdp_session=Cursor1.getString(62);
	        		ModelPs_enpoints.tos_audio=Cursor1.getString(63);
	        		ModelPs_enpoints.tos_video=Cursor1.getString(64);
	        		ModelPs_enpoints.sub_min_expiry=Cursor1.getInt(65);
	        		ModelPs_enpoints.from_domain=Cursor1.getString(66);
	        		ModelPs_enpoints.from_user=Cursor1.getString(67);
	        		ModelPs_enpoints.mwi_from_user=Cursor1.getString(68);
	        		ModelPs_enpoints.dtls_verify=Cursor1.getString(69);
	        		ModelPs_enpoints.dtls_rekey=Cursor1.getString(70);
	        		ModelPs_enpoints.dtls_cert_file=Cursor1.getString(71);
	        		ModelPs_enpoints.dtls_private_key=Cursor1.getString(72);
	        		ModelPs_enpoints.dtls_cipher=Cursor1.getString(73);
	        		ModelPs_enpoints.dtls_ca_file=Cursor1.getString(74);
	        		ModelPs_enpoints.dtls_ca_path=Cursor1.getString(75);
	        		ModelPs_enpoints.dtls_setup=Cursor1.getString(76);			// pjsip_dtls_setup value/Type.
	        		ModelPs_enpoints.srtp_tag_32=Cursor1.getBoolean(77);			// YesNo value/Type
	        		ModelPs_enpoints.media_address=Cursor1.getString(78);
	        		ModelPs_enpoints.redirect_method=Cursor1.getString(79);		// pjsip_redirect_method value/Type.
	        		ModelPs_enpoints.set_var=Cursor1.getString(80);				// Text value/Type.
	        		ModelPs_enpoints.cos_audio=Cursor1.getInt(81);
	        		ModelPs_enpoints.cos_video=Cursor1.getInt(82);
	        		ModelPs_enpoints.message_context=Cursor1.getString(83);
	        		ModelPs_enpoints.force_avp=Cursor1.getBoolean(84);			// YesNo value/Type.
	        		ModelPs_enpoints.media_use_received_transport=Cursor1.getBoolean(85);
	        		ModelPs_enpoints.accountcode=Cursor1.getString(86);
	        		ModelPs_enpoints.user_eq_phone=Cursor1.getBoolean(87);		// YesNo value/Type.
	        		ModelPs_enpoints.moh_passthrough=Cursor1.getBoolean(88);		// YesNo value/Type.
	        		ModelPs_enpoints.media_encryption_optimistic=Cursor1.getBoolean(89);		// YesNo value/Type.
	        		ModelPs_enpoints.rpid_immediate=Cursor1.getBoolean(90);		// YesNo value/Type.
	        		ModelPs_enpoints.g726_non_standard=Cursor1.getBoolean(91);	// YesNo value/Type.
	        		ModelPs_enpoints.rtp_keepalive=Cursor1.getInt(92);
	        		ModelPs_enpoints.rtp_timeout=Cursor1.getInt(93);
	        		ModelPs_enpoints.rtp_timeout_hold=Cursor1.getInt(94);
	        		ModelPs_enpoints.bind_rtp_to_media_address=Cursor1.getBoolean(95);		// YesNo value/Type.
	        		ModelPs_enpoints.voicemail_extension=Cursor1.getString(96);
	        		ModelPs_enpoints.mwi_subscribe_replaces_unsolicited=Cursor1.getInt(97);
	        		ModelPs_enpoints.deny=Cursor1.getString(98);
	        		ModelPs_enpoints.permit=Cursor1.getString(99);
	        		ModelPs_enpoints.acl=Cursor1.getString(100);
	        		ModelPs_enpoints.contact_deny=Cursor1.getString(101);
	        		ModelPs_enpoints.contact_permit=Cursor1.getString(102);
	        		ModelPs_enpoints.contact_acl=Cursor1.getString(103);
	        		ModelPs_enpoints.subscribe_context=Cursor1.getString(104);
	        		ModelPs_enpoints.fax_detect_timeout=Cursor1.getInt(105);
	        		ModelPs_enpoints.contact_user=Cursor1.getString(106);
	        		ModelPs_enpoints.preferred_codec_only=Cursor1.getBoolean(107);			// YesNo value/Type.
	        		ModelPs_enpoints.asymmetric_rtp_codec=Cursor1.getBoolean(108);			// YesNo value/Type.
	        		ModelPs_enpoints.rtcp_mux=Cursor1.getBoolean(109);						// YesNo value/Type.
	        		ModelPs_enpoints.allow_overlap=Cursor1.getBoolean(110);					// YesNo value/Type.
	        		ModelPs_enpoints.refer_blind_progress=Cursor1.getBoolean(111);
	        		ModelPs_enpoints.notify_early_inuse_ringing=Cursor1.getBoolean(112);
	        		ModelPs_enpoints.max_audio_streams=Cursor1.getInt(113);
	        		ModelPs_enpoints.max_video_streams=Cursor1.getInt(114);
	        		ModelPs_enpoints.webrtc=Cursor1.getBoolean(115);							// YesNo value/Type.
	        		ModelPs_enpoints.dtls_fingerprint=Cursor1.getString(116);					// sha_hash value/Type.
	        		ModelPs_enpoints.incomming_mwi_mailbox=Cursor1.getString(117);
	        		ModelPs_enpoints.bundle=Cursor1.getBoolean(118);							// YesNo value/Type.
	        		ModelPs_enpoints.dtls_auto_generate_cert=Cursor1.getBoolean(119);
	         ListUser1.add(ModelPs_enpoints);  
	          
	          }	          
	          Connection1.close();
	          return ListUser1;
	}
	
	@DeleteMapping(path="/DeletePostPsEndpoint",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostAlembicVersionConfig(@RequestBody ps_endpointsModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_ps_endpoints);
		 querydelete_alembic_version_config.setString(1, cfm.id);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =0; 
		Connection1.close();
		return a;    	         
}
	

}
