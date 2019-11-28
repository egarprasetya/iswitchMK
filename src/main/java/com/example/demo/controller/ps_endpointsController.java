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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psendpoint")
public class ps_endpointsController {
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_psendpoint = null;
	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_ps_endpoints = null;

	@PutMapping("/PutPsEndpointse")
	public String putalembicversionconfig(@RequestBody ps_endpointsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_ps_endpoints = Connection1.prepareStatement(query_string_insert.query_insert_ps_endpoints);

		query_insert_ps_endpoints.setString(1, cfm.id);
		query_insert_ps_endpoints.setString(2, cfm.transport);
		query_insert_ps_endpoints.setString(3, cfm.aors);
		query_insert_ps_endpoints.setString(4, cfm.auth);
		query_insert_ps_endpoints.setString(5, cfm.context);
		query_insert_ps_endpoints.setString(6, cfm.disallow);
		query_insert_ps_endpoints.setString(7, cfm.allow);
		query_insert_ps_endpoints.setBoolean(8, cfm.direct_media);
		query_insert_ps_endpoints.setString(9, cfm.connected_line_method);
		query_insert_ps_endpoints.setString(10, cfm.direct_media_method);
		query_insert_ps_endpoints.setString(11, cfm.direct_media_glare_mitigation);
		query_insert_ps_endpoints.setBoolean(12, cfm.disable_direct_media_on_nat);
		query_insert_ps_endpoints.setString(13, cfm.dtmf_mode);
		query_insert_ps_endpoints.setString(14, cfm.external_media_address);
		query_insert_ps_endpoints.setBoolean(15, cfm.force_rport);
		query_insert_ps_endpoints.setBoolean(16, cfm.ice_support);
		query_insert_ps_endpoints.setString(17, cfm.identify_by);
		query_insert_ps_endpoints.setString(18, cfm.mailboxes);
		query_insert_ps_endpoints.setString(19, cfm.moh_suggest);
		query_insert_ps_endpoints.setString(20, cfm.outbound_auth);
		query_insert_ps_endpoints.setString(21, cfm.outbounf_proxy);
		query_insert_ps_endpoints.setBoolean(22, cfm.rewrite_contact);
		query_insert_ps_endpoints.setBoolean(23, cfm.rtp_ipv6);
		query_insert_ps_endpoints.setBoolean(24, cfm.rtp_symmetric);
		query_insert_ps_endpoints.setBoolean(25, cfm.send_diversion);
		query_insert_ps_endpoints.setBoolean(26, cfm.send_pai);
		query_insert_ps_endpoints.setBoolean(27, cfm.send_rpid);
		query_insert_ps_endpoints.setInt(28, cfm.timers_min_se);
		query_insert_ps_endpoints.setString(29, cfm.timers);
		query_insert_ps_endpoints.setInt(30, cfm.timers_sess_expires);
		query_insert_ps_endpoints.setString(31, cfm.callerid);
		query_insert_ps_endpoints.setString(32, cfm.callerid_privacy);
		query_insert_ps_endpoints.setString(33, cfm.callerid_tag);
		query_insert_ps_endpoints.setString(34, cfm._100rel);
		query_insert_ps_endpoints.setBoolean(35, cfm.aggregate_mwi);
		query_insert_ps_endpoints.setBoolean(36, cfm.trust_id_inbound);
		query_insert_ps_endpoints.setBoolean(37, cfm.trust_id_outbound);
		query_insert_ps_endpoints.setBoolean(38, cfm.use_ptime);
		query_insert_ps_endpoints.setBoolean(39, cfm.use_avpf);
		query_insert_ps_endpoints.setString(40, cfm.media_encryption);
		query_insert_ps_endpoints.setBoolean(41, cfm.inband_progress);
		query_insert_ps_endpoints.setString(42, cfm.call_group);
		query_insert_ps_endpoints.setString(43, cfm.pickup_group);
		query_insert_ps_endpoints.setString(44, cfm.named_call_group);
		query_insert_ps_endpoints.setString(45, cfm.named_pickup_group);
		query_insert_ps_endpoints.setInt(46, cfm.device_state_busy_at);
		query_insert_ps_endpoints.setBoolean(47, cfm.fax_detect);
		query_insert_ps_endpoints.setBoolean(48, cfm.t38_udptl);
		query_insert_ps_endpoints.setString(49, cfm.t38_udptl_ec);
		query_insert_ps_endpoints.setInt(50, cfm.t38_udptl_maxdatagram);
		query_insert_ps_endpoints.setBoolean(51, cfm.t38_udptl_nat);
		query_insert_ps_endpoints.setBoolean(52, cfm.t38_udptl_ipv6);
		query_insert_ps_endpoints.setString(53, cfm.tone_zone);
		query_insert_ps_endpoints.setString(54, cfm.language);
		query_insert_ps_endpoints.setBoolean(55, cfm.one_touch_recording);
		query_insert_ps_endpoints.setString(56, cfm.record_on_feature);
		query_insert_ps_endpoints.setString(57, cfm.record_off_feature);
		query_insert_ps_endpoints.setString(58, cfm.rtp_engine);
		query_insert_ps_endpoints.setBoolean(59, cfm.allow_transfer);
		query_insert_ps_endpoints.setBoolean(60, cfm.allow_subscribe);
		query_insert_ps_endpoints.setString(61, cfm.sdp_owner);
		query_insert_ps_endpoints.setString(62, cfm.sdp_session);
		query_insert_ps_endpoints.setString(63, cfm.tos_audio);
		query_insert_ps_endpoints.setString(64, cfm.tos_video);
		query_insert_ps_endpoints.setInt(65, cfm.sub_min_expiry);
		query_insert_ps_endpoints.setString(66, cfm.from_domain);
		query_insert_ps_endpoints.setString(67, cfm.from_user);
		query_insert_ps_endpoints.setString(68, cfm.mwi_from_user);
		query_insert_ps_endpoints.setString(69, cfm.dtls_verify);
		query_insert_ps_endpoints.setString(70, cfm.dtls_rekey);
		query_insert_ps_endpoints.setString(71, cfm.dtls_cert_file);
		query_insert_ps_endpoints.setString(72, cfm.dtls_private_key);
		query_insert_ps_endpoints.setString(73, cfm.dtls_cipher);
		query_insert_ps_endpoints.setString(74, cfm.dtls_ca_file);
		query_insert_ps_endpoints.setString(75, cfm.dtls_ca_path);
		query_insert_ps_endpoints.setString(76, cfm.dtls_setup);
		query_insert_ps_endpoints.setBoolean(77, cfm.srtp_tag_32);
		query_insert_ps_endpoints.setString(78, cfm.media_address);
		query_insert_ps_endpoints.setString(79, cfm.redirect_method);
		query_insert_ps_endpoints.setString(80, cfm.set_var);
		query_insert_ps_endpoints.setInt(81, cfm.cos_audio);
		query_insert_ps_endpoints.setInt(82, cfm.cos_video);
		query_insert_ps_endpoints.setString(83, cfm.message_context);
		query_insert_ps_endpoints.setBoolean(84, cfm.force_avp);
		query_insert_ps_endpoints.setBoolean(85, cfm.media_use_received_transport);
		query_insert_ps_endpoints.setString(86, cfm.accountcode);
		query_insert_ps_endpoints.setBoolean(87, cfm.user_eq_phone);
		query_insert_ps_endpoints.setBoolean(88, cfm.moh_passthrough);
		query_insert_ps_endpoints.setBoolean(89, cfm.media_encryption_optimistic);
		query_insert_ps_endpoints.setBoolean(90, cfm.rpid_immediate);
		query_insert_ps_endpoints.setBoolean(91, cfm.g726_non_standard);
		query_insert_ps_endpoints.setInt(92, cfm.rtp_keepalive);
		query_insert_ps_endpoints.setInt(93, cfm.rtp_timeout);
		query_insert_ps_endpoints.setInt(94, cfm.rtp_timeout_hold);
		query_insert_ps_endpoints.setBoolean(95, cfm.bind_rtp_to_media_address);
		query_insert_ps_endpoints.setString(96, cfm.voicemail_extension);
		query_insert_ps_endpoints.setInt(97, cfm.mwi_subscribe_replaces_unsolicited);
		query_insert_ps_endpoints.setString(98, cfm.deny);
		query_insert_ps_endpoints.setString(99, cfm.permit);
		query_insert_ps_endpoints.setString(100, cfm.acl);
		query_insert_ps_endpoints.setString(101, cfm.contact_deny);
		query_insert_ps_endpoints.setString(102, cfm.contact_permit);
		query_insert_ps_endpoints.setString(103, cfm.contact_acl);
		query_insert_ps_endpoints.setString(104, cfm.subscribe_context);
		query_insert_ps_endpoints.setInt(105, cfm.fax_detect_timeout);
		query_insert_ps_endpoints.setString(106, cfm.contact_user);
		query_insert_ps_endpoints.setBoolean(107, cfm.preferred_codec_only);
		query_insert_ps_endpoints.setBoolean(108, cfm.asymmetric_rtp_codec);
		query_insert_ps_endpoints.setBoolean(109, cfm.rtcp_mux);
		query_insert_ps_endpoints.setBoolean(110, cfm.allow_overlap);
		query_insert_ps_endpoints.setBoolean(111, cfm.refer_blind_progress);
		query_insert_ps_endpoints.setBoolean(112, cfm.notify_early_inuse_ringing);
		query_insert_ps_endpoints.setInt(113, cfm.max_audio_streams);
		query_insert_ps_endpoints.setInt(114, cfm.max_video_streams);
		query_insert_ps_endpoints.setBoolean(115, cfm.webrtc);
		query_insert_ps_endpoints.setString(116, cfm.dtls_fingerprint);
		query_insert_ps_endpoints.setString(117, cfm.incomming_mwi_mailbox);
		query_insert_ps_endpoints.setBoolean(118, cfm.bundle);
		query_insert_ps_endpoints.setBoolean(119, cfm.dtls_auto_generate_cert);

		int Cursor1 = query_insert_ps_endpoints.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		Connection1.close();
		return a;
	}

	@GetMapping("/Getpsendpoint")
	public ArrayList<ps_endpointsModel> TampilAlembicVersionConfig() throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_psendpoint = Connection1.prepareStatement(query_string.query_select_ps_endpoints);
		ResultSet Cursor1 = queryselect_psendpoint.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<ps_endpointsModel> ListUser1 = new ArrayList<ps_endpointsModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			ps_endpointsModel ModelPs_enpoints = new ps_endpointsModel();
			ModelPs_enpoints.id = Cursor1.getString(1);
			ModelPs_enpoints.transport = Cursor1.getString(2);
			ModelPs_enpoints.aors = Cursor1.getString(3);
			ModelPs_enpoints.auth = Cursor1.getString(4);
			ModelPs_enpoints.context = Cursor1.getString(5);
			ModelPs_enpoints.disallow = Cursor1.getString(6);
			ModelPs_enpoints.allow = Cursor1.getString(7);
			ModelPs_enpoints.direct_media = Cursor1.getBoolean(8); // YesNo value / Type.
			ModelPs_enpoints.connected_line_method = Cursor1.getString(9); // pjsip_connected_line_method value/type.
			ModelPs_enpoints.direct_media_method = Cursor1.getString(10); // pjsip_connected_line_method value/type.
			ModelPs_enpoints.direct_media_glare_mitigation = Cursor1.getString(11); // pjsip_direct_media_glare_mitigation
																					// // value/Type.
			ModelPs_enpoints.disable_direct_media_on_nat = Cursor1.getBoolean(12);
			ModelPs_enpoints.dtmf_mode = Cursor1.getString(13); // pjsip_dtmf_mode value/Type.
			ModelPs_enpoints.external_media_address = Cursor1.getString(14);
			ModelPs_enpoints.force_rport = Cursor1.getBoolean(15);
			ModelPs_enpoints.ice_support = Cursor1.getBoolean(16);
			ModelPs_enpoints.identify_by = Cursor1.getString(17);
			ModelPs_enpoints.mailboxes = Cursor1.getString(18);
			ModelPs_enpoints.moh_suggest = Cursor1.getString(19);
			ModelPs_enpoints.outbound_auth = Cursor1.getString(20);
			ModelPs_enpoints.outbounf_proxy = Cursor1.getString(21);
			ModelPs_enpoints.rewrite_contact = Cursor1.getBoolean(22);
			ModelPs_enpoints.rtp_ipv6 = Cursor1.getBoolean(23);
			ModelPs_enpoints.rtp_symmetric = Cursor1.getBoolean(24);
			ModelPs_enpoints.send_diversion = Cursor1.getBoolean(25);
			ModelPs_enpoints.send_pai = Cursor1.getBoolean(26);
			ModelPs_enpoints.send_rpid = Cursor1.getBoolean(27);
			ModelPs_enpoints.timers_min_se = Cursor1.getInt(28);
			ModelPs_enpoints.timers = Cursor1.getString(29); // pjsip_timer value/Type.
			ModelPs_enpoints.timers_sess_expires = Cursor1.getInt(30);
			ModelPs_enpoints.callerid = Cursor1.getString(31);
			ModelPs_enpoints.callerid_privacy = Cursor1.getString(32); // pjsip_cid_privacy value/Type.
			ModelPs_enpoints.callerid_tag = Cursor1.getString(33);
			ModelPs_enpoints._100rel = Cursor1.getString(34); // pjsip_100rel value/Type.
			ModelPs_enpoints.aggregate_mwi = Cursor1.getBoolean(35); // YesNo Value/Type
			ModelPs_enpoints.trust_id_inbound = Cursor1.getBoolean(36); // YesNo Value/Type
			ModelPs_enpoints.trust_id_outbound = Cursor1.getBoolean(37); // YesNo Value/Type
			ModelPs_enpoints.use_ptime = Cursor1.getBoolean(38); // YesNo Value/Type
			ModelPs_enpoints.use_avpf = Cursor1.getBoolean(39); // YesNo Value/Type
			ModelPs_enpoints.media_encryption = Cursor1.getString(40);
			ModelPs_enpoints.inband_progress = Cursor1.getBoolean(41); // YesNo Value/Type
			ModelPs_enpoints.call_group = Cursor1.getString(42);
			ModelPs_enpoints.pickup_group = Cursor1.getString(43);
			ModelPs_enpoints.named_call_group = Cursor1.getString(44);
			ModelPs_enpoints.named_pickup_group = Cursor1.getString(45);
			ModelPs_enpoints.device_state_busy_at = Cursor1.getInt(46);
			ModelPs_enpoints.fax_detect = Cursor1.getBoolean(47); // YesNo Value/Type
			ModelPs_enpoints.t38_udptl = Cursor1.getBoolean(48); // YesNo Value/Type
			ModelPs_enpoints.t38_udptl_ec = Cursor1.getString(49); // pjsip_t38_udptl value/type.
			ModelPs_enpoints.t38_udptl_maxdatagram = Cursor1.getInt(50);
			ModelPs_enpoints.t38_udptl_nat = Cursor1.getBoolean(51); // YesNo value/Type.
			ModelPs_enpoints.t38_udptl_ipv6 = Cursor1.getBoolean(52); // YesNo value/Type.
			ModelPs_enpoints.tone_zone = Cursor1.getString(53);
			ModelPs_enpoints.language = Cursor1.getString(54);
			ModelPs_enpoints.one_touch_recording = Cursor1.getBoolean(55); // YesNo Value/Type
			ModelPs_enpoints.record_on_feature = Cursor1.getString(56);
			ModelPs_enpoints.record_off_feature = Cursor1.getString(57);
			ModelPs_enpoints.rtp_engine = Cursor1.getString(58);
			ModelPs_enpoints.allow_transfer = Cursor1.getBoolean(59); // YesNo Value/Type
			ModelPs_enpoints.allow_subscribe = Cursor1.getBoolean(60); // YesNo value/Type.
			ModelPs_enpoints.sdp_owner = Cursor1.getString(61);
			ModelPs_enpoints.sdp_session = Cursor1.getString(62);
			ModelPs_enpoints.tos_audio = Cursor1.getString(63);
			ModelPs_enpoints.tos_video = Cursor1.getString(64);
			ModelPs_enpoints.sub_min_expiry = Cursor1.getInt(65);
			ModelPs_enpoints.from_domain = Cursor1.getString(66);
			ModelPs_enpoints.from_user = Cursor1.getString(67);
			ModelPs_enpoints.mwi_from_user = Cursor1.getString(68);
			ModelPs_enpoints.dtls_verify = Cursor1.getString(69);
			ModelPs_enpoints.dtls_rekey = Cursor1.getString(70);
			ModelPs_enpoints.dtls_cert_file = Cursor1.getString(71);
			ModelPs_enpoints.dtls_private_key = Cursor1.getString(72);
			ModelPs_enpoints.dtls_cipher = Cursor1.getString(73);
			ModelPs_enpoints.dtls_ca_file = Cursor1.getString(74);
			ModelPs_enpoints.dtls_ca_path = Cursor1.getString(75);
			ModelPs_enpoints.dtls_setup = Cursor1.getString(76); // pjsip_dtls_setup value/Type.
			ModelPs_enpoints.srtp_tag_32 = Cursor1.getBoolean(77); // YesNo value/Type
			ModelPs_enpoints.media_address = Cursor1.getString(78);
			ModelPs_enpoints.redirect_method = Cursor1.getString(79); // pjsip_redirect_method value/Type.
			ModelPs_enpoints.set_var = Cursor1.getString(80); // Text value/Type.
			ModelPs_enpoints.cos_audio = Cursor1.getInt(81);
			ModelPs_enpoints.cos_video = Cursor1.getInt(82);
			ModelPs_enpoints.message_context = Cursor1.getString(83);
			ModelPs_enpoints.force_avp = Cursor1.getBoolean(84); // YesNo value/Type.
			ModelPs_enpoints.media_use_received_transport = Cursor1.getBoolean(85);
			ModelPs_enpoints.accountcode = Cursor1.getString(86);
			ModelPs_enpoints.user_eq_phone = Cursor1.getBoolean(87); // YesNo value/Type.
			ModelPs_enpoints.moh_passthrough = Cursor1.getBoolean(88); // YesNo value/Type.
			ModelPs_enpoints.media_encryption_optimistic = Cursor1.getBoolean(89); // YesNo value/Type.
			ModelPs_enpoints.rpid_immediate = Cursor1.getBoolean(90); // YesNo value/Type.
			ModelPs_enpoints.g726_non_standard = Cursor1.getBoolean(91); // YesNo value/Type.
			ModelPs_enpoints.rtp_keepalive = Cursor1.getInt(92);
			ModelPs_enpoints.rtp_timeout = Cursor1.getInt(93);
			ModelPs_enpoints.rtp_timeout_hold = Cursor1.getInt(94);
			ModelPs_enpoints.bind_rtp_to_media_address = Cursor1.getBoolean(95); // YesNo value/Type.
			ModelPs_enpoints.voicemail_extension = Cursor1.getString(96);
			ModelPs_enpoints.mwi_subscribe_replaces_unsolicited = Cursor1.getInt(97);
			ModelPs_enpoints.deny = Cursor1.getString(98);
			ModelPs_enpoints.permit = Cursor1.getString(99);
			ModelPs_enpoints.acl = Cursor1.getString(100);
			ModelPs_enpoints.contact_deny = Cursor1.getString(101);
			ModelPs_enpoints.contact_permit = Cursor1.getString(102);
			ModelPs_enpoints.contact_acl = Cursor1.getString(103);
			ModelPs_enpoints.subscribe_context = Cursor1.getString(104);
			ModelPs_enpoints.fax_detect_timeout = Cursor1.getInt(105);
			ModelPs_enpoints.contact_user = Cursor1.getString(106);
			ModelPs_enpoints.preferred_codec_only = Cursor1.getBoolean(107); // YesNo value/Type.
			ModelPs_enpoints.asymmetric_rtp_codec = Cursor1.getBoolean(108); // YesNo value/Type.
			ModelPs_enpoints.rtcp_mux = Cursor1.getBoolean(109); // YesNo value/Type.
			ModelPs_enpoints.allow_overlap = Cursor1.getBoolean(110); // YesNo value/Type.
			ModelPs_enpoints.refer_blind_progress = Cursor1.getBoolean(111);
			ModelPs_enpoints.notify_early_inuse_ringing = Cursor1.getBoolean(112);
			ModelPs_enpoints.max_audio_streams = Cursor1.getInt(113);
			ModelPs_enpoints.max_video_streams = Cursor1.getInt(114);
			ModelPs_enpoints.webrtc = Cursor1.getBoolean(115); // YesNo value/Type.
			ModelPs_enpoints.dtls_fingerprint = Cursor1.getString(116); // sha_hash value/Type.
			ModelPs_enpoints.incomming_mwi_mailbox = Cursor1.getString(117);
			ModelPs_enpoints.bundle = Cursor1.getBoolean(118); // YesNo value/Type.
			ModelPs_enpoints.dtls_auto_generate_cert = Cursor1.getBoolean(119);
			ListUser1.add(ModelPs_enpoints);

		}
		Connection1.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/DeletePostPsEndpoint", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostAlembicVersionConfig(@RequestBody ps_endpointsModel cfm) throws SQLException {
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1
				.prepareStatement(query_string_delete.query_delete_ps_endpoints);
		querydelete_alembic_version_config.setString(1, cfm.id);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		return a;
	}

}
