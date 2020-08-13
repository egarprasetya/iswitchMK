package com.example.demo.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String apiId;
	private String apiKey;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String apiId, String apiKey) {
		this.setApiId(apiId);
		this.setApiKey(apiKey);
	}

	public String getApiId()
	{
		return apiId;
	}

	public void setApiId(String apiId)
	{
		this.apiId = apiId;
	}

	public String getApiKey()
	{
		return apiKey;
	}

	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}


}