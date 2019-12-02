package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class ps_transportModel {
	public String id;
	public int async_operations;
	public String bind;
	public String ca_list_file;
	public String cert_file;
	public String cipher;
	public String domain;
	public String external_media_address;
	public String external_signaling_address;
	public int external_signaling_port;
	public String method;				// pjsip_transport_method value/Type.
	public String local_net;
	public String password;
	public String priv_key_file;
	public String protocol;				// pjsip_transport_protocol value/Type.
	public YesNo_Values require_client_cert;	// YesNo value/Type.
	public YesNo_Values verify_client;		// YesNo value/Type.
	public YesNo_Values verify_server;		// YesNo value/Type.
	public String tos;
	public int cos;
	public YesNo_Values allow_reload;				// YesNo value/Type.
	public YesNo_Values symmetric_transport;			// YesNo value/Type.
}
