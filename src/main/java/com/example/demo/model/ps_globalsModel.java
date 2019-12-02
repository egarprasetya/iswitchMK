package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class ps_globalsModel {
	public String id;
	public int max_forwards;
	public String user_agent;
	public String default_outbound_endpoint;
	public String debug;
	public String endpoint_identifier_order;
	public int max_initial_qualify_time;
	public String default_from_user;
	public int keep_alive_interval;
	public String regcontext;
	public int contact_expiration_check_interval;
	public String default_voicemail_extension;
	public YesNo_Values disable_multi_domain;				// YesNo value/Type.
	public int unidentified_request_count;
	public int unidentified_request_period;
	public int unidentified_request_prune_interval;
	public String default_realm;
	public int mwi_tps_queue_high;
	public int mwi_tps_queue_low;
	public YesNo_Values mwi_disable_initial_unsolicited;		// YesNo value/Type.
	public YesNo_Values ignore_uri_user_options;				// YesNo v
}
