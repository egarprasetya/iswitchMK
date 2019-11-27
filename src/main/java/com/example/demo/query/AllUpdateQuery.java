package com.example.demo.query;

public class AllUpdateQuery {
	public String query_replace_alembic_version="UPDATE alembic_version " + 
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
			"SET name=?, type=?, username=?, mailbox=?, secret=?, dbsecret=?, context=?, regcontext=?, host=?, ipaddr=?, port=?, defaultip=?, sourceaddress=?, mask=?, regexten=?, regseconds=?, accountcode=?, mohinterpret=?, mohsuggest=?, inkeys=?, outkeys=?, language=?, callerid=?, cid_number=?, sendani=?, fullname=?, trunk=?, auth=?, maxauthreq=?, requirecalltoken=?, encryption=?, transfer=?, jitterbuffer=?, forcejitterbuffer=?, disallow=?, allow=?, codecpriority=?, qualify=?, qualifysmoothing=?, qualifyfreqok=?, qualifyfreqnotok=?, timezone=?, adsi=?, amaflags=?, setvar=? " + 
			"WHERE id=?;";
	public String query_update_meetme="UPDATE meetme " + 
			"SET confno=?, starttime=?, endtime=?, pin=?, adminpin=?, opts=?, adminopts=?, recordingfilename=?, recordingformat=?, maxusers=?, members=? " + 
			"WHERE bookid=?;";
	public String query_update_musiconhold="UPDATE musiconhold " + 
			"SET mode=?, directory=?, application=?, digit=?, sort=?, format=?, stamp=? " + 
			"WHERE name=?;";
	public String query_update_ps_aors="UPDATE ps_aors " + 
			"SET contact=?, default_expiration=?, mailboxes=?, max_contacts=?, minimum_expiration=?, remove_existing=?, qualify_frequency=?, authenticate_qualify=?, maximum_expiration=?, outbound_proxy=?, support_path=?, qualify_timeout=?, voicemail_extension=? " + 
			"WHERE id=?;";
	public String query_update_ps_asterisk_publications="UPDATE ps_asterisk_publications " + 
			"SET devicestate_publish=?, mailboxstate_publish=?, device_state=?, device_state_filter=?, mailbox_state=?, mailbox_state_filter=? " + 
			"WHERE id=?;";
	public String query_update_ps_auths="UPDATE ps_auths " + 
			"SET auth_type=?, nonce_lifetime=?, md5_cred=?, password=?, realm=?, username=? " + 
			"WHERE id=?;";
	public String query_update_ps_contacts="UPDATE ps_contacts " + 
			"SET uri=?, expiration_time=?, qualify_frequency=?, outbound_proxy=?, path=?, user_agent=?, qualify_timeout=?, reg_server=?, authenticate_qualify=?, via_addr=?, via_port=?, call_id=?, endpoint=?, prune_on_boot=? " + 
			"WHERE id=?;";
	public String query_update_ps_domain_aliases="UPDATE ps_domain_aliases " + 
			"SET domain=? " + 
			"WHERE id=?;";
	public String query_update_ps_endpoints_id="UPDATE ps_endpoint_id_ips " + 
			"SET endpoint=?, match=?, srv_lookups=?, match_header=? " + 
			"WHERE id=?;";
	public String query_update_ps_endpoints="UPDATE ps_endpoints " + 
			"SET transport=?, aors=?, auth=?, context=?, disallow=?, allow=?, direct_media=?, connected_line_method=?, direct_media_method=?, direct_media_glare_mitigation=?, disable_direct_media_on_nat=?, dtmf_mode=?, external_media_address=?, force_rport=?, ice_support=?, identify_by=?, mailboxes=?, moh_suggest=?, outbound_auth=?, outbound_proxy=?, rewrite_contact=?, rtp_ipv6=?, rtp_symmetric=?, send_diversion=?, send_pai=?, send_rpid=?, timers_min_se=?, timers=?, timers_sess_expires=?, callerid=?, callerid_privacy=?, callerid_tag=?, 1??rel=?, aggregate_mwi=?, trust_id_inbound=?, trust_id_outbound=?, use_ptime=?, use_avpf=?, media_encryption=?, inband_progress=?, call_group=?, pickup_group=?, named_call_group=?, named_pickup_group=?, device_state_busy_at=?, fax_detect=?, t38_udptl=?, t38_udptl_ec=?, t38_udptl_maxdatagram=?, t38_udptl_nat=?, t38_udptl_ipv6=?, tone_zone=?, language=?, one_touch_recording=?, record_on_feature=?, record_off_feature=?, rtp_engine=?, allow_transfer=?, allow_subscribe=?, sdp_owner=?, sdp_session=?, tos_audio=?, tos_video=?, sub_min_expiry=?, from_domain=?, from_user=?, mwi_from_user=?, dtls_verify=?, dtls_rekey=?, dtls_cert_file=?, dtls_private_key=?, dtls_cipher=?, dtls_ca_file=?, dtls_ca_path=?, dtls_setup=?, srtp_tag_32=?, media_address=?, redirect_method=?, set_var=?, cos_audio=?, cos_video=?, message_context=?, force_avp=?, media_use_received_transport=?, accountcode=?, user_eq_phone=?, moh_passthrough=?, media_encryption_optimistic=?, rpid_immediate=?, g726_non_standard=?, rtp_keepalive=?, rtp_timeout=?, rtp_timeout_hold=?, bind_rtp_to_media_address=?, voicemail_extension=?, mwi_subscribe_replaces_unsolicited=?, deny=?, permit=?, acl=?, contact_deny=?, contact_permit=?, contact_acl=?, subscribe_context=?, fax_detect_timeout=?, contact_user=?, preferred_codec_only=?, asymmetric_rtp_codec=?, rtcp_mux=?, allow_overlap=?, refer_blind_progress=?, notify_early_inuse_ringing=?, max_audio_streams=?, max_video_streams=?, webrtc=?, dtls_fingerprint=?, incoming_mwi_mailbox=?, bundle=?, dtls_auto_generate_cert=? " + 
			"WHERE id=?;";
	public String query_update_ps_globals="UPDATE ps_globals " + 
			"SET max_forwards=?, user_agent=?, default_outbound_endpoint=?, debug=?, endpoint_identifier_order=?, max_initial_qualify_time=?, default_from_user=?, keep_alive_interval=?, regcontext=?, contact_expiration_check_interval=?, default_voicemail_extension=?, disable_multi_domain=?, unidentified_request_count=?, unidentified_request_period=?, unidentified_request_prune_interval=?, default_realm=?, mwi_tps_queue_high=?, mwi_tps_queue_low=?, mwi_disable_initial_unsolicited=?, ignore_uri_user_options=? " + 
			"WHERE id=?;";
	public String query_update_ps_inbound_publications="UPDATE ps_inbound_publications " + 
			"SET endpoint=?, event_asterisk-devicestate=?, event_asterisk-mwi=? " + 
			"WHERE id=?; ";
	public String query_update_ps_outbound_publishes="UPDATE ps_outbound_publishes " + 
			"SET expiration=?, outbound_auth=?, outbound_proxy=?, server_uri=?, from_uri=?, to_uri=?, event=?, max_auth_attempts=?, transport=?, multi_user=?, @body=?, @context=?, @exten=? " + 
			"WHERE id=?;";
	public String query_update_ps_registrations="UPDATE ps_registrations " + 
			"SET auth_rejection_permanent=?, client_uri=?, contact_user=?, expiration=?, max_retries=?, outbound_auth=?, outbound_proxy=?, retry_interval=?, forbidden_retry_interval=?, server_uri=?, transport=?, support_path=?, fatal_retry_interval=?, line=?, endpoint=? " + 
			"WHERE id=?; ";
	public String query_update_ps_resource_list="UPDATE ps_resource_list " + 
			"SET list_item=?, event=?, full_state=?, notification_batch_interval=? " + 
			"WHERE id=?;";
	public String query_update_ps_subscription_persistence="UPDATE ps_subscription_persistence " + 
			"SET packet=?, src_name=?, src_port=?, transport_key=?, local_name=?, local_port=?, cseq=?, tag=?, endpoint=?, expires=?, contact_uri=?, prune_on_boot=? " + 
			"WHERE id=?;";
	public String query_update_ps_systems="UPDATE ps_systems " + 
			"SET timer_t1=?, timer_b=?, compact_headers=?, threadpool_initial_size=?, threadpool_auto_increment=?, threadpool_idle_timeout=?, threadpool_max_size=?, disable_tcp_switch=? " + 
			"WHERE id=?;";
	public String query_update_ps_transports="UPDATE ps_transports " + 
			"SET async_operations=?, bind=?, ca_list_file=?, cert_file=?, cipher=?, domain=?, external_media_address=?, external_signaling_address=?, external_signaling_port=?, method=?, local_net=?, password=?, priv_key_file=?, protocol=?, require_client_cert=?, verify_client=?, verify_server=?, tos=?, cos=?, allow_reload=?, symmetric_transport=? " + 
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
			"SET musiconhold=?, announce=?, context=?, timeout=?, ringinuse=?, setinterfacevar=?, setqueuevar=?, setqueueentryvar=?, monitor_format=?, membermacro=?, membergosub=?, queue_youarenext=?, queue_thereare=?, queue_callswaiting=?, queue_quantity1=?, queue_quantity2=?, queue_holdtime=?, queue_minutes=?, queue_minute=?, queue_seconds=?, queue_thankyou=?, queue_callerannounce=?, queue_reporthold=?, announce_frequency=?, announce_to_first_user=?, min_announce_frequency=?, announce_round_seconds=?, announce_holdtime=?, announce_position=?, announce_position_limit=?, periodic_announce=?, periodic_announce_frequency=?, relative_periodic_announce=?, random_periodic_announce=?, retry=?, wrapuptime=?, penaltymemberslimit=?, autofill=?, monitor_type=?, autopause=?, autopausedelay=?, autopausebusy=?, autopauseunavail=?, maxlen=?, servicelevel=?, strategy=?, joinempty=?, leavewhenempty=?, reportholdtime=?, memberdelay=?, weight=?, timeoutrestart=?, defaultrule=?, timeoutpriority=? " + 
			"WHERE name=?;";
	public String query_update_sippeers ="UPDATE sippeers " + 
			"SET name=?, ipaddr=?, port=?, regseconds=?, defaultuser=?, fullcontact=?, regserver=?, useragent=?, lastms=?, host=?, type=?, context=?, permit=?, deny=?, secret=?, md5secret=?, remotesecret=?, transport=?, dtmfmode=?, directmedia=?, nat=?, callgroup=?, pickupgroup=?, language=?, disallow=?, allow=?, insecure=?, trustrpid=?, progressinband=?, promiscredir=?, useclientcode=?, accountcode=?, setvar=?, callerid=?, amaflags=?, callcounter=?, busylevel=?, allowoverlap=?, allowsubscribe=?, videosupport=?, maxcallbitrate=?, rfc2833compensate=?, mailbox=?, session-timers=?, session-expires=?, session-minse=?, session-refresher=?, t38pt_usertpsource=?, regexten=?, fromdomain=?, fromuser=?, qualify=?, defaultip=?, rtptimeout=?, rtpholdtimeout=?, sendrpid=?, outboundproxy=?, callbackextension=?, timert1=?, timerb=?, qualifyfreq=?, constantssrc=?, contactpermit=?, contactdeny=?, usereqphone=?, textsupport=?, faxdetect=?, buggymwi=?, auth=?, fullname=?, trunkname=?, cid_number=?, callingpres=?, mohinterpret=?, mohsuggest=?, parkinglot=?, hasvoicemail=?, subscribemwi=?, vmexten=?, autoframing=?, rtpkeepalive=?, call-limit=?, g726nonstandard=?, ignoresdpversion=?, allowtransfer=?, dynamic=?, path=?, supportpath=?, email=? " + 
			"WHERE id=?;";
	public String query_update_voicemail="UPDATE voicemail " + 
			"SET context=?, mailbox=?, password=?, fullname=?, alias=?, email=?, pager=?, attach=?, attachfmt=?, serveremail=?, language=?, tz=?, deletevoicemail=?, saycid=?, sendvoicemail=?, review=?, tempgreetwarn=?, operator=?, envelope=?, sayduration=?, forcename=?, forcegreetings=?, callback=?, dialout=?, exitcontext=?, maxmsg=?, volgain=?, imapuser=?, imappassword=?, imapserver=?, imapport=?, imapflags=?, stamp=? " + 
			"WHERE uniqueid=?;";

}
