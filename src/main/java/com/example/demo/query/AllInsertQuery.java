package com.example.demo.query;

public class AllInsertQuery {
	public String query_insert_alembic_version="INSERT INTO alembic_version " + 
			"(version_num) " + 
			"VALUES(?)";
	public String query_insert_alembic_version_config="INSERT INTO alembic_version_config " + 
			"(version_num) " + 
			"VALUES(?)";
	public String query_insert_cdr="INSERT INTO cdr " + 
			"(accountcode, src, dst, dcontext, clid, channel, dstchannel, lastapp, lastdata, start, answer, \"end\", duration, billsec, disposition, amaflags, userfield, uniqueid, linkedid, peeraccount, sequence) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public String query_insert_extension="INSERT INTO extensions " +
			"(context, exten, priority, app, appdata) " + 
			"VALUES(?, ?, ?, ?, ?)";
	public String query_insert_iaxfriends="INSERT INTO iaxfriends " + 
			"(name, type, username, mailbox, secret, dbsecret, context, regcontext, host, ipaddr, port, defaultip, sourceaddress, mask, regexten, regseconds, accountcode, mohinterpret, mohsuggest, inkeys, outkeys, language, callerid, cid_number, sendani, fullname, trunk, auth, maxauthreq, requirecalltoken, encryption, transfer, jitterbuffer, forcejitterbuffer, disallow, allow, codecpriority, qualify, qualifysmoothing, qualifyfreqok, qualifyfreqnotok, timezone, adsi, amaflags, setvar) " + 
			"VALUES("
			+ "?, ?::type_value, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?::yesno_values, ?::yesno_values, ?, ?, ?, ?::iax_requirecalltoken, "
			+ "?::iax_encryption, ?::iax_transfer, ?::yesno_values, ?::yesno_values, ?, ?, ?, ?, ?, ?::yesno_values, "
			+ "?, ?, ?::yesno_values, ?, ?)";
	public String query_insert_meetme="INSERT INTO meetme " + 
			"(confno, starttime, endtime, pin, adminpin, opts, adminopts, recordingfilename, recordingformat, maxusers, members) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public String query_insert_musiconhold="INSERT INTO musiconhold " + 
			"(name, mode, directory, application, digit, sort, format, stamp) " + 
			"VALUES(?, ?::Moh_mode, ?, ?, ?, ?, ?, ?)";
	public String query_insert_ps_aors="INSERT INTO ps_aors " + 
			"(id, contact, default_expiration, mailboxes, max_contacts, minimum_expiration, remove_existing, qualify_frequency, authenticate_qualify, maximum_expiration, outbound_proxy, support_path, qualify_timeout, voicemail_extension) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?::yesno_values, ?, ?::yesno_values, ?, ?, ?::yesno_values, ?, ?)";
	public String query_insert_ps_asterisk_publications="INSERT INTO ps_asterisk_publications " + 
			"(id, devicestate_publish, mailboxstate_publish, device_state, device_state_filter, mailbox_state, mailbox_state_filter) " + 
			"VALUES(?, ?, ?, ?::device_state, ?, ?, ?)";
	public String query_insert_ps_auths="INSERT INTO ps_auths " + 
			"(id, auth_type, nonce_lifetime, md5_cred, password, realm, username) " + 
			"VALUES(?, ?::pjsip_auth, ?, ?, ?, ?, ?)";
	public String query_insert_ps_contacts="INSERT INTO ps_contacts " + 
			"(id, uri, expiration_time, qualify_frequency, outbound_proxy, path, user_agent, qualify_timeout, reg_server, authenticate_qualify, via_addr, via_port, call_id, endpoint, prune_on_boot) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?, ?, ?, ?, ?)";
	public String query_insert_ps_domain_aliases="INSERT INTO ps_domain_aliases " + 
			"(id, domain) " + 
			"VALUES(?, ?)";
	public String query_insert_ps_endpoints_id="INSERT INTO ps_endpoint_id_ips " + 
			"(id, endpoint, match, srv_lookups, match_header) " + 
			"VALUES(?, ?, ?, ?::yesno_values, ?)";
	public String query_insert_ps_endpoints="INSERT INTO ps_endpoints " + 
			"(id, transport, aors, auth, context, disallow, allow, direct_media, connected_line_method, direct_media_method, direct_media_glare_mitigation, disable_direct_media_on_nat, dtmf_mode, external_media_address, force_rport, ice_support, identify_by, mailboxes, moh_suggest, outbound_auth, outbound_proxy, rewrite_contact, rtp_ipv6, rtp_symmetric, send_diversion, send_pai, send_rpid, timers_min_se, timers, timers_sess_expires, callerid, callerid_privacy, callerid_tag, 100rel, aggregate_mwi, trust_id_inbound, trust_id_outbound, use_ptime, use_avpf, media_encryption, inband_progress, call_group, pickup_group, named_call_group, named_pickup_group, device_state_busy_at, fax_detect, t38_udptl, t38_udptl_ec, t38_udptl_maxdatagram, t38_udptl_nat, t38_udptl_ipv6, tone_zone, language, one_touch_recording, record_on_feature, record_off_feature, rtp_engine, allow_transfer, allow_subscribe, sdp_owner, sdp_session, tos_audio, tos_video, sub_min_expiry, from_domain, from_user, mwi_from_user, dtls_verify, dtls_rekey, dtls_cert_file, dtls_private_key, dtls_cipher, dtls_ca_file, dtls_ca_path, dtls_setup, srtp_tag_32, media_address, redirect_method, set_var, cos_audio, cos_video, message_context, force_avp, media_use_received_transport, accountcode, user_eq_phone, moh_passthrough, media_encryption_optimistic, rpid_immediate, g726_non_standard, rtp_keepalive, rtp_timeout, rtp_timeout_hold, bind_rtp_to_media_address, voicemail_extension, mwi_subscribe_replaces_unsolicited, deny, permit, acl, contact_deny, contact_permit, contact_acl, subscribe_context, fax_detect_timeout, contact_user, preferred_codec_only, asymmetric_rtp_codec, rtcp_mux, allow_overlap, refer_blind_progress, notify_early_inuse_ringing, max_audio_streams, max_video_streams, webrtc, dtls_fingerprint, incoming_mwi_mailbox, bundle, dtls_auto_generate_cert) " + 
			"VALUES("
			+ "?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?::pjsip_connected_line_method, ?::pjsip_connected_line_method,"
			+ "?::pjsip_direct_media_glare_mitigation, ?, ?::pjsip_dtmf_mode, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?::pjsip_timer, ?,"
			+ "?, ?::pjsip_cid_privacy, ?, ?::pjsip_100rel, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?,"
			+ "?::yesno_values, ?, ?, ?, ?, ?, ?::yesno_values, ?::yesno_values, ?::pjsip_t38_udptl, ?,"
			+ "?::yesno_values, ?::yesno_values, ?, ?, ?::yesno_values, ?, ?, ?, ?::yesno_values, ?::yesno_values,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?::pjsip_dtls_setup, ?::yesno_values, ?, ?::pjsip_redirect_method, ?::Text,"
			+ "?, ?, ?, ?::yesno_values, ?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values,"
			+ "?::yesno_values, ?, ?, ?, ?::yesno_values, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values,"
			+ "?, ?, ?, ?, ?::yesno_values, ?::sha_hash, ?, ?::yesno_values, ?)";
	public String query_insert_ps_globals="INSERT INTO ps_globals " + 
			"(id, max_forwards, user_agent, default_outbound_endpoint, debug, endpoint_identifier_order, max_initial_qualify_time, default_from_user, keep_alive_interval, regcontext, contact_expiration_check_interval, default_voicemail_extension, disable_multi_domain, unidentified_request_count, unidentified_request_period, unidentified_request_prune_interval, default_realm, mwi_tps_queue_high, mwi_tps_queue_low, mwi_disable_initial_unsolicited, ignore_uri_user_options) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?::yesno_values)";
	public String query_insert_ps_inbound_publications="INSERT INTO ps_inbound_publications " + 
			"(id, endpoint, event_asterisk-devicestate, event_asterisk-mwi) " + 
			"VALUES(?, ?, ?, ?)";
	public String query_insert_ps_outbound_publishes="INSERT INTO ps_outbound_publishes " + 
			"(id, expiration, outbound_auth, outbound_proxy, server_uri, from_uri, to_uri, event, max_auth_attempts, transport, multi_user, @body, @context, @exten) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?, ?, ?)";
	public String query_insert_ps_registrations="INSERT INTO ps_registrations " + 
			"(id, auth_rejection_permanent, client_uri, contact_user, expiration, max_retries, outbound_auth, outbound_proxy, retry_interval, forbidden_retry_interval, server_uri, transport, support_path, fatal_retry_interval, line, endpoint) " + 
			"VALUES(?, ?::yesno_values, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?)";
	public String query_insert_ps_resource_list="INSERT INTO ps_resource_list " + 
			"(id, list_item, event, full_state, notification_batch_interval) " + 
			"VALUES(?, ?, ?, ?, ?)";
	public String query_insert_ps_subscription_persistence="INSERT INTO ps_subscription_persistence " + 
			"(id, packet, src_name, src_port, transport_key, local_name, local_port, cseq, tag, endpoint, expires, contact_uri, prune_on_boot) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values)";
	public String query_insert_ps_systems="INSERT INTO ps_systems " + 
			"(id, timer_t1, timer_b, compact_headers, threadpool_initial_size, threadpool_auto_increment, threadpool_idle_timeout, threadpool_max_size, disable_tcp_switch) " + 
			"VALUES(?, ?, ?, ?::yesno_values, ?, ?, ?, ?, ?)";
	public String query_insert_ps_transports="INSERT INTO ps_transports " + 
			"(id, async_operations, bind, ca_list_file, cert_file, cipher, domain, external_media_address, external_signaling_address, external_signaling_port, method, local_net, password, priv_key_file, protocol, require_client_cert, verify_client, verify_server, tos, cos, allow_reload, symmetric_transport) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::pjsip_transport_method, ?, ?, ?, ?::pjsip_transport_protocol, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?, ?, ?::yesno_values, ?::yesno_values)";
	public String query_insert_queue_log="INSERT INTO queue_log " + 
			"(calldatetime, time, callid, queuename, agent, event, data, data1, data2, data3, data4, data5) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public String query_insert_queue_members="INSERT INTO queue_members " + 
			"(queue_name, interface, membername, state_interface, penalty, paused, uniqueid) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?)";
	public String query_insert_queue_rules="INSERT INTO queue_rules " + 
			"(rule_name, time, min_penalty, max_penalty) " + 
			"VALUES(?, ?, ?, ?)";
	public String query_insert_queues="INSERT INTO queues " + 
			"(name, musiconhold, announce, context, timeout, ringinuse, setinterfacevar, setqueuevar, setqueueentryvar, monitor_format, membermacro, membergosub, queue_youarenext, queue_thereare, queue_callswaiting, queue_quantity1, queue_quantity2, queue_holdtime, queue_minutes, queue_minute, queue_seconds, queue_thankyou, queue_callerannounce, queue_reporthold, announce_frequency, announce_to_first_user, min_announce_frequency, announce_round_seconds, announce_holdtime, announce_position, announce_position_limit, periodic_announce, periodic_announce_frequency, relative_periodic_announce, random_periodic_announce, retry, wrapuptime, penaltymemberslimit, autofill, monitor_type, autopause, autopausedelay, autopausebusy, autopauseunavail, maxlen, servicelevel, strategy, joinempty, leavewhenempty, reportholdtime, memberdelay, weight, timeoutrestart, defaultrule, timeoutpriority) " + 
			"VALUES("
			+ "?, ?, ?, ?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?::yesno_values, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?::Queue_autopause, ?, ?::yesno_values, ?::yesno_values, ?, ?, ?::queue_strategy, ?, ?, ?::yesno_values,"
			+ "?, ?, ?::yesno_values, ?, ?)";
	public String query_insert_sippeers ="INSERT INTO sippeers " + 
			"(name, ipaddr, port, regseconds, defaultuser, fullcontact, regserver, useragent, lastms, host, type, context, permit, deny, secret, md5secret, remotesecret, transport, dtmfmode, directmedia, nat, callgroup, pickupgroup, language, disallow, allow, insecure, trustrpid, progressinband, promiscredir, useclientcode, accountcode, setvar, callerid, amaflags, callcounter, busylevel, allowoverlap, allowsubscribe, videosupport, maxcallbitrate, rfc2833compensate, mailbox, session-timers, session-expires, session-minse, session-refresher, t38pt_usertpsource, regexten, fromdomain, fromuser, qualify, defaultip, rtptimeout, rtpholdtimeout, sendrpid, outboundproxy, callbackextension, timert1, timerb, qualifyfreq, constantssrc, contactpermit, contactdeny, usereqphone, textsupport, faxdetect, buggymwi, auth, fullname, trunkname, cid_number, callingpres, mohinterpret, mohsuggest, parkinglot, hasvoicemail, subscribemwi, vmexten, autoframing, rtpkeepalive, call-limit, g726nonstandard, ignoresdpversion, allowtransfer, dynamic, path, supportpath, email) " + 
			"VALUES("
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?::type, ?, ?, ?, ?, ?, ?, ?, ?::sip_dtmf_mode, ?::sip_direct_media"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?::sip_progressinband, ?::yesno_values"
			+ "?::yesno_values, ?, ?, ?, ?, ?::yesno_values, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values"
			+ "?, ?::yesno_values, ?, ?::sip_session_timers, ?, ?, ?::sip_session_refresh, ?, ?, ?"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?::yesno_values, ?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?, ?"
			+ "?, ?, ?::sip_callingpres, ?, ?, ?, ?::yesno_values, ?::yesno_values, ?, ?::yesno_values,"
			+ "?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?, ?::yesno_values, ?)";
	public String query_insert_voicemail="INSERT INTO voicemail " + 
			"(context, mailbox, password, fullname, alias, email, pager, attach, attachfmt, serveremail, language, tz, deletevoicemail, saycid, sendvoicemail, review, tempgreetwarn, operator, envelope, sayduration, forcename, forcegreetings, callback, dialout, exitcontext, maxmsg, volgain, imapuser, imappassword, imapserver, imapport, imapflags, stamp) " + 
			"VALUES("
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?::yesno_values, ?,"
			+ "?, ?, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?::yesno_values, ?,"
			+ "?::yesno_values, ?::yesno_values, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?)";
	
	public String query_insert_customer = "INSERT INTO public.customers " + 
			"(id, nama, nomor_telepon, alamat, \"extension\") " + 
			"VALUES(?, ?, ?, ?, ?);";


}
