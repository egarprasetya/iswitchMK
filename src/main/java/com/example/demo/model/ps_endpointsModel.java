package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;
import com.example.demo.Enum.pjsip_100rel_values;
import com.example.demo.Enum.pjsip_cid_privacy_values;
import com.example.demo.Enum.pjsip_connected_line_method_values;
import com.example.demo.Enum.pjsip_direct_media_glare_mitigation_values;
import com.example.demo.Enum.pjsip_dtls_setup_values;
import com.example.demo.Enum.pjsip_dtmf_mode_values_v3;
import com.example.demo.Enum.pjsip_redirect_method_values;
import com.example.demo.Enum.pjsip_t38udptl_ec_values;
import com.example.demo.Enum.pjsip_timer_values;
import com.example.demo.Enum.sha_hash_values;

public class ps_endpointsModel {
	public String id;
	public String transport;
	public String aors;
	public String auth;
	public String context;
	public String disallow;
	public String allow;
	public YesNo_Values direct_media;			// YesNo value / Type.
	public pjsip_connected_line_method_values connected_line_method;	// pjsip_connected_line_method value/type.
	public pjsip_connected_line_method_values direct_media_method;		// pjsip_connected_line_method value/type.
	public pjsip_direct_media_glare_mitigation_values direct_media_glare_mitigation;		// pjsip_direct_media_glare_mitigation value/Type.
	public String disable_direct_media_on_nat;
	public pjsip_dtmf_mode_values_v3 dtmf_mode;				// pjsip_dtmf_mode value/Type.
	public String external_media_address;
	public String force_rport;
	public String ice_support;
	public String identify_by;
	public String mailboxes;
	public String moh_suggest;
	public String outbound_auth;
	public String outbounf_proxy;
	public String rewrite_contact;
	public String rtp_ipv6;
	public String rtp_symmetric;
	public String send_diversion;
	public String send_pai;
	public String send_rpid;
	public int timers_min_se;
	public pjsip_timer_values timers;				// pjsip_timer value/Type.
	public int timers_sess_expires;
	public String callerid;
	public pjsip_cid_privacy_values callerid_privacy;		// pjsip_cid_privacy value/Type.
	public String callerid_tag;
	public pjsip_100rel_values _100rel;				// pjsip_100rel value/Type.
	public YesNo_Values aggregate_mwi;		// YesNo Value/Type
	public YesNo_Values trust_id_inbound;	// YesNo Value/Type
	public YesNo_Values trust_id_outbound;	// YesNo Value/Type
	public YesNo_Values use_ptime;			// YesNo Value/Type
	public YesNo_Values use_avpf;			// YesNo Value/Type
	public String media_encryption;
	public YesNo_Values inband_progress;		// YesNo Value/Type
	public String call_group;
	public String pickup_group;
	public String named_call_group;
	public String named_pickup_group;
	public int device_state_busy_at;
	public YesNo_Values fax_detect;			// YesNo Value/Type
	public YesNo_Values t38_udptl;			// YesNo Value/Type
	public pjsip_t38udptl_ec_values t38_udptl_ec;			// pjsip_t38_udptl value/type.
	public int t38_udptl_maxdatagram;
	public YesNo_Values t38_udptl_nat;		// YesNo value/Type.
	public YesNo_Values t38_udptl_ipv6;		// YesNo value/Type.
	public String tone_zone;
	public String language;
	public YesNo_Values one_touch_recording; // YesNo Value/Type
	public String record_on_feature;
	public String record_off_feature;
	public String rtp_engine;
	public YesNo_Values allow_transfer;		// YesNo Value/Type
	public YesNo_Values allow_subscribe;		// YesNo value/Type.
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
	public pjsip_dtls_setup_values dtls_setup;			// pjsip_dtls_setup value/Type.
	public YesNo_Values srtp_tag_32;			// YesNo value/Type
	public String media_address;
	public pjsip_redirect_method_values redirect_method;		// pjsip_redirect_method value/Type.
	public String set_var;				// Text value/Type.
	public int cos_audio;
	public int cos_video;
	public String message_context;
	public YesNo_Values force_avp;			// YesNo value/Type.
	public String media_use_received_transport;
	public String accountcode;
	public YesNo_Values user_eq_phone;		// YesNo value/Type.
	public YesNo_Values moh_passthrough;		// YesNo value/Type.
	public YesNo_Values media_encryption_optimistic;		// YesNo value/Type.
	public YesNo_Values rpid_immediate;		// YesNo value/Type.
	public YesNo_Values g726_non_standard;	// YesNo value/Type.
	public int rtp_keepalive;
	public int rtp_timeout;
	public int rtp_timeout_hold;
	public YesNo_Values bind_rtp_to_media_address;		// YesNo value/Type.
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
	public YesNo_Values preferred_codec_only;			// YesNo value/Type.
	public YesNo_Values asymmetric_rtp_codec;			// YesNo value/Type.
	public YesNo_Values rtcp_mux;						// YesNo value/Type.
	public YesNo_Values allow_overlap;					// YesNo value/Type.
	public String refer_blind_progress;
	public String notify_early_inuse_ringing;
	public int max_audio_streams;
	public int max_video_streams;
	public YesNo_Values webrtc;							// YesNo value/Type.
	public sha_hash_values dtls_fingerprint;					// sha_hash value/Type.
	public String incomming_mwi_mailbox;
	public YesNo_Values bundle;							// YesNo value/Type.
	public YesNo_Values dtls_auto_generate_cert;			// YesNo value/Type.
}
