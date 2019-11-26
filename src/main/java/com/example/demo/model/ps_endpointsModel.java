package com.example.demo.model;

public class ps_endpointsModel {
	public String id;
	public String transport;
	public String aors;
	public String auth;
	public String context;
	public String disallow;
	public String allow;
	public boolean direct_media;			// YesNo value / Type.
	public String connected_line_method;	// pjsip_connected_line_method value/type.
	public String direct_media_method;		// pjsip_connected_line_method value/type.
	public String direct_media_glare_mitigation;		// pjsip_direct_media_glare_mitigation value/Type.
	public boolean disable_direct_media_on_nat;
	public String dtmf_mode;				// pjsip_dtmf_mode value/Type.
	public String external_media_address;
	public boolean force_rport;
	public boolean ice_support;
	public String identify_by;
	public String mailboxes;
	public String moh_suggest;
	public String outbound_auth;
	public String outbounf_proxy;
	public boolean rewrite_contact;
	public boolean rtp_ipv6;
	public boolean rtp_symmetric;
	public boolean send_diversion;
	public boolean send_pai;
	public boolean send_rpid;
	public int timers_min_se;
	public String timers;				// pjsip_timer value/Type.
	public int timers_sess_expires;
	public String callerid;
	public String callerid_privacy;		// pjsip_cid_privacy value/Type.
	public String callerid_tag;
	public String _100rel;				// pjsip_100rel value/Type.
	public boolean aggregate_mwi;		// YesNo Value/Type
	public boolean trust_id_inbound;	// YesNo Value/Type
	public boolean trust_id_outbound;	// YesNo Value/Type
	public boolean use_ptime;			// YesNo Value/Type
	public boolean use_avpf;			// YesNo Value/Type
	public String media_encryption;
	public boolean inband_progress;		// YesNo Value/Type
	public String call_group;
	public String pickup_group;
	public String named_call_group;
	public String named_pickup_group;
	public int device_state_busy_at;
	public boolean fax_detect;			// YesNo Value/Type
	public boolean t38_udptl;			// YesNo Value/Type
	public String t38_udptl_ec;			// pjsip_t38_udptl value/type.
	public int t38_udptl_maxdatagram;
	public boolean t38_udptl_nat;		// YesNo value/Type.
	public boolean t38_udptl_ipv6;		// YesNo value/Type.
	public String tone_zone;
	public String language;
	public boolean one_touch_recording; // YesNo Value/Type
	public String record_on_feature;
	public String record_off_feature;
	public String rtp_engine;
	public boolean allow_transfer;		// YesNo Value/Type
	public boolean allow_subscribe;		// YesNo value/Type.
	public String  sdp_owner;
	public String sdp_session;
	public String tos_audio;
	public String tos_video;
	public int sub_min_expiry;
	public String from_domain;
	public String from_user;
	public String mwi_from_user;
	public String dtls_verify;
	public String dtls_rekey;
	public String dtls_cert_file;
	public String dtls_private_key;
	public String dtls_cipher;
	public String dtls_ca_file;
	public String dtls_ca_path;
	public String dtls_setup;			// pjsip_dtls_setup value/Type.
	public boolean srtp_tag_32;			// YesNo value/Type
	public String media_address;
	public String redirect_method;		// pjsip_redirect_method value/Type.
	public String set_var;				// Text value/Type.
	public int cos_audio;
	public int cos_video;
	public String message_context;
	public boolean force_avp;			// YesNo value/Type.
	public boolean media_use_received_transport;
	public String accountcode;
	public boolean user_eq_phone;		// YesNo value/Type.
	public boolean moh_passthrough;		// YesNo value/Type.
	public boolean media_encryption_optimistic;		// YesNo value/Type.
	public boolean rpid_immediate;		// YesNo value/Type.
	public boolean g726_non_standard;	// YesNo value/Type.
	public int rtp_keepalive;
	public int rtp_timeout;
	public int rtp_timeout_hold;
	public boolean bind_rtp_to_media_address;		// YesNo value/Type.
	public String voicemail_extension;
	public int mwi_subscribe_replaces_unsolicited;
	public String deny;
	public String permit;
	public String acl;
	public String contact_deny;
	public String contact_permit;
	public String contact_acl;
	public String subscribe_context;
	public int fax_detect_timeout;
	public String contact_user;
	public boolean preferred_codec_only;			// YesNo value/Type.
	public boolean asymmetric_rtp_codec;			// YesNo value/Type.
	public boolean rtcp_mux;						// YesNo value/Type.
	public boolean allow_overlap;					// YesNo value/Type.
	public boolean refer_blind_progress;
	public boolean notify_early_inuse_ringing;
	public int max_audio_streams;
	public int max_video_streams;
	public boolean webrtc;							// YesNo value/Type.
	public String dtls_fingerprint;					// sha_hash value/Type.
	public String incomming_mwi_mailbox;
	public boolean bundle;							// YesNo value/Type.
	public boolean dtls_auto_generate_cert;			// YesNo value/Type.
}
