package com.example.demo.controller;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/queues")
public class queuesContoller {
	PreparedStatement querydelete_alembic_version_config=null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string= new AllQuery();
	PreparedStatement queryselect_queues=null;
	@GetMapping("/Getqueues")
	public ArrayList<queuesModel> Tampilqueues() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_queues=Connection1.prepareStatement(query_string.query_select_queues);
        ResultSet Cursor1 = queryselect_queues.executeQuery();// Evaluate (Connected_Expression1)
      	  ArrayList<queuesModel> ListUser1 = new ArrayList<queuesModel>();
          while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
          {       
        	  queuesModel ModelQueues=new queuesModel();
        	  
        	  ModelQueues.name = Cursor1.getString(1);
        		ModelQueues.musiconhold = Cursor1.getString(2);
        		ModelQueues.announce = Cursor1.getString(3);
        		ModelQueues.context = Cursor1.getString(4);
        		ModelQueues.timeout = Cursor1.getInt(5);
        		ModelQueues.ringinuse = Cursor1.getBoolean(6);				// YesNo value / Type.
        		ModelQueues.setinterfacevar = Cursor1.getBoolean(7);			// YesNo value / Type.
        		ModelQueues.setqueuevar = Cursor1.getBoolean(8);				// YesNo value / Type.
        		ModelQueues.setqueueentryvar = Cursor1.getBoolean(9);		// YesNo value / Type.
        		ModelQueues.monitor_format = Cursor1.getString(10);
        		ModelQueues.membermacro = Cursor1.getString(11);
        		ModelQueues.membergosub = Cursor1.getString(12);
        		ModelQueues.queue_youare_next = Cursor1.getString(13);
        		ModelQueues.queue_thereare = Cursor1.getString(14);
        		ModelQueues.queue_callswaiting = Cursor1.getString(15);
        		ModelQueues.queue_quantity1 = Cursor1.getString(16);
        		ModelQueues.queue_quantity2 = Cursor1.getString(17);
        		ModelQueues.queue_holdtime = Cursor1.getString(18);
        		ModelQueues.queue_minutes = Cursor1.getString(19);
        		ModelQueues.queue_minute = Cursor1.getString(20);
        		ModelQueues.queue_seconds = Cursor1.getString(21);
        		ModelQueues.queue_thankyou = Cursor1.getString(22);
        		ModelQueues.queue_callerannouce = Cursor1.getString(23);
        		ModelQueues.queue_reporthold = Cursor1.getString(24);
        		ModelQueues.announce_frequency = Cursor1.getInt(25);
        		ModelQueues.announce_to_first_user = Cursor1.getBoolean(26);	// YesNo value / Type.
        		ModelQueues.min_announce_frequency = Cursor1.getInt(27);
        		ModelQueues.announce_round_seconds = Cursor1.getInt(28);
        		ModelQueues.announce_holdtime = Cursor1.getString(29);
        		ModelQueues.announce_position = Cursor1.getString(30);
        		ModelQueues.announce_position_limit = Cursor1.getInt(31);
        		ModelQueues.periodic_announce = Cursor1.getString(32);
        		ModelQueues.periodic_announce_frequency = Cursor1.getInt(33);
        		ModelQueues.relative_periodic_announce = Cursor1.getBoolean(34);
        		ModelQueues.random_periodic_announce = Cursor1.getBoolean(35);
        		ModelQueues.retry = Cursor1.getInt(36);
        		ModelQueues.wrapuptime = Cursor1.getInt(37);
        		ModelQueues.penaltymemberslimit = Cursor1.getInt(38);
        		ModelQueues.autofill = Cursor1.getBoolean(39);
        		ModelQueues.monitor_type = Cursor1.getString(40);
        		ModelQueues.autopause = Cursor1.getString(41);				// Queue_autopause value / Type.
        		ModelQueues.autopausedelay = Cursor1.getInt(42);
        		ModelQueues.autopausebusy = Cursor1.getBoolean(43);			// YesNo value / Type.
        		ModelQueues.autopauseunavail = Cursor1.getBoolean(44);		// YesNo value / Type.
        		ModelQueues.maxlen = Cursor1.getInt(45);
        		ModelQueues.servicelevel = Cursor1.getInt(46);
        		ModelQueues.strategy = Cursor1.getString(47);					// queue_strategy value / Type.
        		ModelQueues.joinempty = Cursor1.getString(48);
        		ModelQueues.leavewhenempty = Cursor1.getString(49);
        		ModelQueues.reportholdtime = Cursor1.getBoolean(50);			// YesNo value / Type.
        		ModelQueues.memberdelay = Cursor1.getInt(51);
        		ModelQueues.weight = Cursor1.getInt(52);
        		ModelQueues.timeoutrestart = Cursor1.getBoolean(53);			// YesNo value / Type.
        		ModelQueues.defaultrule = Cursor1.getString(54);
        		ModelQueues.timeoutpriority = Cursor1.getString(55);
         ListUser1.add(ModelQueues);  
        
          }          
          Connection1.close();
          return ListUser1;
	}
	
	@PostMapping(path="/DeletePostQueues",produces="application/json",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int DeletePostQueues(@RequestBody queuesModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
	      querydelete_alembic_version_config=Connection1.prepareStatement(query_string_delete.query_delete_queues);
		 querydelete_alembic_version_config.setString(1, cfm.name);   
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a =1; 
		Connection1.close();
		return a;    	         
}
	
	
}
