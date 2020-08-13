package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Holiday_CalendarModel
{
	public int id;
	@JsonFormat(pattern="yyyy-MM-dd")
    public Date date_holiday;
    public String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Timestamp createdon;
    public String createdby;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Timestamp modifiedon;
    public String modifiedby;
    public String state_code;
}
