package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "access_api")
public class DAOApi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String apiId;
	@Column
	@JsonIgnore
	private String apiKey;
	
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