package com.example.demo.query;

public class AllUpdateQuery {
	public String query_update_alembic_version="UPDATE alembic_version " + 
			"SET version_num=? " +
			"WHERE version_num=?;"; 
	public String query_update_alembic_version_config="UPDATE alembic_version_config " + 
			"SET version_num=? " + 
			"WHERE version_num=?;";
	public String query_update_cdr="UPDATE cdr " + 
			"SET src=?, dst=?, dcontext=?, clid=?, channel=?, dstchannel=?, lastapp=?, lastdata=?, start=?, answer=?, end=?, duration=?, billsec=?, disposition=?, amaflags=?, userfield=?, uniqueid=?, linkedid=?, peeraccount=?, sequence=? " +
			"WHERE accountcode=?;";
	public String query_update_extensions="UPDATE extensions " + 
			"SET context=?, exten=?, priority=?, app=?, appdata=? " + 
			"WHERE id=?";
	public String query_update_iaxfriends="UPDATE iaxfriends " + 
			"SET name=?, type=?::type_value, username=?, mailbox=?, secret=?,"
			+ "dbsecret=?, context=?, regcontext=?, host=?, ipaddr=?,"
			+ "port=?, defaultip=?, sourceaddress=?, mask=?, regexten=?,"
			+ "regseconds=?, accountcode=?, mohinterpret=?, mohsuggest=?, inkeys=?,"
			+ "outkeys=?, language=?, callerid=?, cid_number=?, sendani=?::yesno_values,"
			+ "fullname=?::yesno_values, trunk=?, auth=?, maxauthreq=?, requirecalltoken=?::iax_requirecalltoken,"
			+ "encryption=?::iax_encryption, transfer=?::iax_transfer, jitterbuffer=?::yesno_values, forcejitterbuffer=?::yesno_values, disallow=?,"
			+ "allow=?, codecpriority=?, qualify=?, qualifysmoothing=?, qualifyfreqok=?::yesno_values,"
			+ "qualifyfreqnotok=?, timezone=?, adsi=?::yesno_values, amaflags=?, setvar=? " + 
			"WHERE id=?;";
	public String query_update_meetme="UPDATE meetme " + 
			"SET confno=?, starttime=?, endtime=?, pin=?, adminpin=?, opts=?, adminopts=?, recordingfilename=?, recordingformat=?, maxusers=?, members=? " + 
			"WHERE bookid=?;";
	public String query_update_musiconhold="UPDATE musiconhold " + 
			"SET mode=?::Moh_mode, directory=?, application=?, digit=?, sort=?, format=?, stamp=? " + 
			"WHERE name=?;";
	public String query_update_ps_aors="UPDATE ps_aors " + 
			"SET contact=?, default_expiration=?, mailboxes=?, max_contacts=?, minimum_expiration=?, remove_existing=?::yesno_values, qualify_frequency=?, authenticate_qualify=?::yesno_values, maximum_expiration=?, outbound_proxy=?, support_path=?::yesno_values, qualify_timeout=?, voicemail_extension=? " + 
			"WHERE id=?;";
	public String query_update_ps_asterisk_publications="UPDATE ps_asterisk_publications " + 
			"SET devicestate_publish=?, mailboxstate_publish=?, device_state=?::device_state, device_state_filter=?, mailbox_state=?, mailbox_state_filter=? " + 
			"WHERE id=?;";
	public String query_update_ps_auths="UPDATE ps_auths " + 
			"SET auth_type=?::pjsip_auth, nonce_lifetime=?, md5_cred=?, password=?, realm=?, username=? " + 
			"WHERE id=?;";
	public String query_update_ps_contacts="UPDATE ps_contacts " + 
			"SET uri=?, expiration_time=?, qualify_frequency=?, outbound_proxy=?, path=?, user_agent=?, qualify_timeout=?, reg_server=?, authenticate_qualify=?::yesno_values, via_addr=?, via_port=?, call_id=?, endpoint=?, prune_on_boot=? " + 
			"WHERE id=?;";
	public String query_update_ps_domain_aliases="UPDATE ps_domain_aliases " + 
			"SET domain=? " + 
			"WHERE id=?;";
	public String query_update_ps_endpoints_id="UPDATE ps_endpoint_id_ips " + 
			"SET endpoint=?, match=?, srv_lookups=?::yesno_values, match_header=? " + 
			"WHERE id=?;";
	public String query_update_ps_endpoints="UPDATE ps_endpoints " + 
			"SET transport=?, aors=?, auth=?, context=?, disallow=?,"
			+ "allow=?, direct_media=?::yesno_values, connected_line_method=?::pjsip_connected_line_method, direct_media_method=?::pjsip_connected_line_method, direct_media_glare_mitigation=?::pjsip_direct_media_glare_mitigation, "
			+ "disable_direct_media_on_nat=?, dtmf_mode=?::pjsip_dtmf_mode, external_media_address=?, force_rport=?, ice_support=?,"
			+ "identify_by=?, mailboxes=?, moh_suggest=?, outbound_auth=?, outbound_proxy=?,"
			+ "rewrite_contact=?, rtp_ipv6=?, rtp_symmetric=?, send_diversion=?, send_pai=?,"
			+ "send_rpid=?, timers_min_se=?, timers=?::pjsip_timer, timers_sess_expires=?, callerid=?,"
			+ "callerid_privacy=?::pjsip_cid_privacy, callerid_tag=?, 100rel=?::pjsip_100rel, aggregate_mwi=?::yesno_values, trust_id_inbound=?::yesno_values,"
			+ "trust_id_outbound=?::yesno_values, use_ptime=?::yesno_values, use_avpf=?::yesno_values, media_encryption=?, inband_progress=?::yesno_values,"
			+ "call_group=?, pickup_group=?, named_call_group=?, named_pickup_group=?, device_state_busy_at=?,"
			+ "fax_detect=?::yesno_values, t38_udptl=?::yesno_values, t38_udptl_ec=?::pjsip_t38_udptl, t38_udptl_maxdatagram=?, t38_udptl_nat=?::yesno_values,"
			+ "t38_udptl_ipv6=?::yesno_values, tone_zone=?, language=?, one_touch_recording=?::yesno_values, record_on_feature=?,"
			+ "record_off_feature=?, rtp_engine=?, allow_transfer=?::yesno_values, allow_subscribe=?::yesno_values, sdp_owner=?,"
			+ "sdp_session=?, tos_audio=?, tos_video=?, sub_min_expiry=?, from_domain=?,"
			+ "from_user=?, mwi_from_user=?, dtls_verify=?, dtls_rekey=?, dtls_cert_file=?,"
			+ "dtls_private_key=?, dtls_cipher=?, dtls_ca_file=?, dtls_ca_path=?, dtls_setup=?::pjsip_dtls_setup,"
			+ "srtp_tag_32=?::yesno_values, media_address=?, redirect_method=?::pjsip_redirect_method, set_var=?::Text, cos_audio=?,"
			+ "cos_video=?, message_context=?, force_avp=?::yesno_values, media_use_received_transport=?, accountcode=?,"
			+ "user_eq_phone=?::yesno_values, moh_passthrough=?::yesno_values, media_encryption_optimistic=?::yesno_values, rpid_immediate=?::yesno_values, g726_non_standard=?::yesno_values,"
			+ "rtp_keepalive=?, rtp_timeout=?, rtp_timeout_hold=?, bind_rtp_to_media_address=?::yesno_values, voicemail_extension=?,"
			+ "mwi_subscribe_replaces_unsolicited=?, deny=?, permit=?, acl=?, contact_deny=?,"
			+ "contact_permit=?, contact_acl=?, subscribe_context=?, fax_detect_timeout=?, contact_user=?,"
			+ "preferred_codec_only=?::yesno_values, asymmetric_rtp_codec=?::yesno_values, rtcp_mux=?::yesno_values, allow_overlap=?::yesno_values, refer_blind_progress=?,"
			+ "notify_early_inuse_ringing=?, max_audio_streams=?, max_video_streams=?, webrtc=?::yesno_values, dtls_fingerprint=?::sha_hash,"
			+ "incoming_mwi_mailbox=?, bundle=?::yesno_values, dtls_auto_generate_cert=?::yesno_values " + 
			"WHERE id=?;";
	public String query_update_ps_globals="UPDATE ps_globals " + 
			"SET max_forwards=?, user_agent=?, default_outbound_endpoint=?, debug=?, endpoint_identifier_order=?, max_initial_qualify_time=?, default_from_user=?, keep_alive_interval=?, regcontext=?, contact_expiration_check_interval=?, default_voicemail_extension=?, disable_multi_domain=?::yesno_values, unidentified_request_count=?, unidentified_request_period=?, unidentified_request_prune_interval=?, default_realm=?, mwi_tps_queue_high=?, mwi_tps_queue_low=?, mwi_disable_initial_unsolicited=?::yesno_values, ignore_uri_user_options=?::yesno_values " + 
			"WHERE id=?;";
	public String query_update_ps_inbound_publications="UPDATE ps_inbound_publications " + 
			"SET endpoint=?, event_asterisk-devicestate=?, event_asterisk-mwi=? " + 
			"WHERE id=?; ";
	public String query_update_ps_outbound_publishes="UPDATE ps_outbound_publishes " + 
			"SET expiration=?, outbound_auth=?, outbound_proxy=?, server_uri=?, from_uri=?, to_uri=?, event=?, max_auth_attempts=?, transport=?, multi_user=?::yesno_values, @body=?, @context=?, @exten=? " + 
			"WHERE id=?;";
	public String query_update_ps_registrations="UPDATE ps_registrations " + 
			"SET auth_rejection_permanent=::?yesno_values, client_uri=?, contact_user=?, expiration=?, max_retries=?, outbound_auth=?, outbound_proxy=?, retry_interval=?, forbidden_retry_interval=?, server_uri=?, transport=?, support_path=?, fatal_retry_interval=?, line=?::yesno_values, endpoint=? " + 
			"WHERE id=?; ";
	public String query_update_ps_resource_list="UPDATE ps_resource_list " + 
			"SET list_item=?, event=?, full_state=?, notification_batch_interval=? " + 
			"WHERE id=?;";
	public String query_update_ps_subscription_persistence="UPDATE ps_subscription_persistence " + 
			"SET packet=?, src_name=?, src_port=?, transport_key=?, local_name=?, local_port=?, cseq=?, tag=?, endpoint=?, expires=?, contact_uri=?, prune_on_boot=?::yesno_values " + 
			"WHERE id=?;";
	public String query_update_ps_systems="UPDATE ps_systems " + 
			"SET timer_t1=?, timer_b=?, compact_headers=?::yesno_values, threadpool_initial_size=?, threadpool_auto_increment=?, threadpool_idle_timeout=?, threadpool_max_size=?, disable_tcp_switch=? " + 
			"WHERE id=?;";
	public String query_update_ps_transports="UPDATE ps_transports " + 
			"SET async_operations=?, bind=?, ca_list_file=?, cert_file=?, cipher=?, domain=?, external_media_address=?, external_signaling_address=?, external_signaling_port=?, method=?::pjsip_transport_method, local_net=?, password=?, priv_key_file=?, protocol=?pjsip_transport_protocol, require_client_cert=?::yesno_values, verify_client=?::yesno_values, verify_server=?::yesno_values, tos=?, cos=?, allow_reload=?::yesno_values, symmetric_transport=?::yesno_values " + 
			"WHERE id=?;";
	public String query_update_queue_log="UPDATE queue_log " + 
			"SET calldatetime=?, time=?, callid=?, queuename=?, agent=?, event=?, data=?, data1=?, data2=?, data3=?, data4=?, data5=? " + 
			"WHERE id=?";
	public String query_update_queue_members="UPDATE queue_members " + 
			"SET queue_name=?, interface=?, membername=?, state_interface=?, penalty=?, paused=? " + 
			"WHERE uniqueid=?;";
	public String query_update_queue_rules="UPDATE queue_rules " + 
			"SET time=?, min_penalty=?, max_penalty=? " +
			"WHERE rule_name=?;";
	public String query_update_queues="UPDATE queues " + 
			"SET musiconhold=?, announce=?, context=?, timeout=?, ringinuse=?::yesno_values,"
			+ "setinterfacevar=?::yesno_values, setqueuevar=?::yesno_values, setqueueentryvar=?::yesno_values, monitor_format=?, membermacro=?,"
			+ "membergosub=?, queue_youarenext=?, queue_thereare=?, queue_callswaiting=?, queue_quantity1=?,"
			+ "queue_quantity2=?, queue_holdtime=?, queue_minutes=?, queue_minute=?, queue_seconds=?,"
			+ "queue_thankyou=?, queue_callerannounce=?, queue_reporthold=?, announce_frequency=?, announce_to_first_user=?::yesno_values,"
			+ "min_announce_frequency=?, announce_round_seconds=?, announce_holdtime=?, announce_position=?, announce_position_limit=?,"
			+ "periodic_announce=?, periodic_announce_frequency=?, relative_periodic_announce=?, random_periodic_announce=?, retry=?,"
			+ "wrapuptime=?, penaltymemberslimit=?, autofill=?, monitor_type=?, autopause=?::Queue_autopause,"
			+ "autopausedelay=?, autopausebusy=?::yesno_values, autopauseunavail=?::yesno_values, maxlen=?, servicelevel=?,"
			+ "strategy=?::queue_strategy, joinempty=?, leavewhenempty=?, reportholdtime=?::yesno_values, memberdelay=?,"
			+ "weight=?, timeoutrestart=?::yesno_values, defaultrule=?, timeoutpriority=? " + 
			"WHERE name=?;";
	public String query_update_sippeers ="UPDATE sippeers " + 
			"SET name=?, ipaddr=?, port=?, regseconds=?, defaultuser=?, fullcontact=?, regserver=?, useragent=?, lastms=?, host=?, type=?::type, context=?, permit=?, deny=?, secret=?, md5secret=?, remotesecret=?, transport=?, dtmfmode=?::sip_dtmf_mode, directmedia=?::sip_direct_media, nat=?, callgroup=?, pickupgroup=?, language=?, disallow=?, allow=?, insecure=?, trustrpid=?, progressinband=?::sip_progressinband, promiscredir=?::yesno_values, useclientcode=?::yesno_values, accountcode=?, setvar=?, callerid=?, amaflags=?, callcounter=?::yesno_values, busylevel=?, allowoverlap=?::yesno_values, allowsubscribe=?::yesno_values, videosupport=?::yesno_values, maxcallbitrate=?, rfc2833compensate=?::yesno_values, mailbox=?, session-timers=?::sip_session_timers, session-expires=?, session-minse=?, session-refresher=?::sip_session_refresh, t38pt_usertpsource=?, regexten=?, fromdomain=?, fromuser=?, qualify=?, defaultip=?, rtptimeout=?, rtpholdtimeout=?, sendrpid=?, outboundproxy=?, callbackextension=?, timert1=?, timerb=?, qualifyfreq=?, constantssrc=?::yesno_values, contactpermit=?, contactdeny=?, usereqphone=?::yesno_values, textsupport=?::yesno_values, faxdetect=?::yesno_values, buggymwi=?::yesno_values, auth=?, fullname=?, trunkname=?, cid_number=?, callingpres=?::sip_callingpres, mohinterpret=?, mohsuggest=?, parkinglot=?, hasvoicemail=?::yesno_values, subscribemwi=?::yesno_values, vmexten=?, autoframing=?::yesno_values, rtpkeepalive=?, call-limit=?, g726nonstandard=?::yesno_values, ignoresdpversion=?::yesno_values, allowtransfer=?::yesno_values, dynamic=?::yesno_values, path=?, supportpath=?::yesno_values, email=?::bp_char " + 
			"WHERE id=?;";
	public String query_update_voicemail="UPDATE voicemail " + 
			"SET context=?, mailbox=?, password=?, fullname=?, alias=?, email=?, pager=?, attach=?::yesno_values, attachfmt=?, serveremail=?, language=?, tz=?, deletevoicemail=?::yesno_values, saycid=?::yesno_values, sendvoicemail=?::yesno_values, review=?::yesno_values, tempgreetwarn=?::yesno_values, operator=?::yesno_values, envelope=?::yesno_values, sayduration=?, forcename=?::yesno_values, forcegreetings=?::yesno_values, callback=?, dialout=?, exitcontext=?, maxmsg=?, volgain=?, imapuser=?, imappassword=?, imapserver=?, imapport=?, imapflags=?, stamp=? " + 
			"WHERE uniqueid=?;";

	public String query_login2 = "UPDATE users SET status = ? WHERE user_id=?";
	
	public String query_login3 = "UPDATE users SET status = ? WHERE username=? and password=?";
	
	public String query_updateUserById = "UPDATE users "
	+ "SET nama=?, username=?, \"password\"=?, modified=?, email=?, password_email=?, extensions_user=?, skill=?, status=?, avatar=? "
	+ "WHERE user_id=?;";
	
	public String query_changeStatus = "UPDATE users "
			+ "SET status=?"
			+ " WHERE user_id=?";
	String a;
}
