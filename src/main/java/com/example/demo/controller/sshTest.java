package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/ssh")
public class sshTest
{
	public String sshExec ()
	{
		String host = "10.30.1.17";
		String user = "root";
		String password = "c1d3ngb4r4t";

		String command1 = "mkdir baru";
//		String command1 = "asterisk -rvvvvvv";
		String command2 = "reload";
		
		try
		{
			
			java.util.Properties config = new java.util.Properties ();
			config.put ("StrictHostKeyChecking", "no");
			JSch jsch = new JSch ();
			Session session = jsch.getSession (user, host);
			session.setPassword (password);
			session.setConfig (config);
			session.connect ();
			System.out.println ("Connected");
			
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand("rasterisk -x reload");
      channelExec.connect();
//        
			channelExec.disconnect ();
			session.disconnect ();
			System.out.println ("DONE");
		} catch (Exception e)
		{
			e.printStackTrace ();
		}
		return "0";
	}
}
