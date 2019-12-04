package com.example.demo.model;

import com.example.demo.Enum.pjsip_auth_type_values;

public class Ps_AuthsModel
{
	public String id;
	public pjsip_auth_type_values auth_type; // pjsip_auth Type
	public int nonce_lifetime;
	public String md5_cred;
	public String password;
	public String realm;
	public String username;
}
