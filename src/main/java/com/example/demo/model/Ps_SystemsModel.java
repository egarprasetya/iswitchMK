package com.example.demo.model;

import com.example.demo.Enum.YesNo_Values;

public class Ps_SystemsModel
{
	public String id;
	public int timer_t1;
	public int timer_b;
	public YesNo_Values compact_headers; // YesNo value;
	public int threadpool_initial_size;
	public int threadpool_auto_increment;
	public int threadpool_idle_timeout;
	public int threadpool_max_size;
	public String disable_tcp_switch;
}
