package com.example.demo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CdrModel;
import com.example.demo.model.MessageModel;
import com.example.demo.model.Queue_MemberModel;
import com.example.demo.query.AllDeleteQuery;
import com.example.demo.query.AllInsertQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;

public class MessageController
{
	@RestController
	@CrossOrigin(origins = "*")
	@RequestMapping(produces = "application/json", path = "/message")
	public class Queue_MemberController
	{
		@Autowired
		private DataSource dataSource;
		
		public Queue_MemberController (DataSource dataSource)
		{
			this.dataSource = dataSource;
		}
		
		AllInsertQuery query_string_insert = new AllInsertQuery ();
		
		PreparedStatement querydelete_alembic_version_config = null;
		PreparedStatement queryselect_queuemember = null;
		AllSelectParameterQuery query_string2 = new AllSelectParameterQuery ();
		
		PreparedStatement query_insert_queue_members = null;
		
		@PostMapping("/getMessageBySrcDst")
		public ArrayList<MessageModel> getMessageBySrcDst (MessageModel cfm) throws SQLException
		{
			// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection ();
			queryselect_queuemember = Connection1.prepareStatement (query_string2.query_select_message_src);
			queryselect_queuemember.setString (1, cfm.src);
			queryselect_queuemember.setString (2, cfm.dst);
			ResultSet Cursor1 = queryselect_queuemember.executeQuery ();// Evaluate (Connected_Expression1)
			ArrayList<MessageModel> ListUser1 = new ArrayList<MessageModel> ();
			while (Cursor1.next ()) // while there_is_next_record_in (Cursor1)
			{
				MessageModel ModelQueue_member = new MessageModel ();
				ModelQueue_member.id = Cursor1.getInt (1);
				ModelQueue_member.status = Cursor1.getString (2);
				ModelQueue_member.pesan = Cursor1.getString (3);
				ModelQueue_member.src = Cursor1.getString (4);
				ModelQueue_member.dst = Cursor1.getString (5);
				ModelQueue_member.datetime = Cursor1.getTimestamp (6);
				
				ListUser1.add (ModelQueue_member);
				
			}
			Connection1.close ();
			queryselect_queuemember.close ();
			return ListUser1;
		}
		
		@PostMapping("/insertMessageBySrcDst")
		public String insertMessage (MessageModel cfm) throws SQLException
		{
			// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
			// sk.service_user, sk.service_password);
			Connection Connection1 = dataSource.getConnection ();
			queryselect_queuemember = Connection1.prepareStatement (query_string_insert.query_insert_message);
			queryselect_queuemember.setString (1, cfm.pesan);
			queryselect_queuemember.setString (2, cfm.status);
			queryselect_queuemember.setString (3, cfm.src);
			queryselect_queuemember.setString (4, cfm.dst);
			queryselect_queuemember.setTimestamp (5, cfm.datetime);
			queryselect_queuemember.executeQuery ();// Evaluate (Connected_Expression1)
			
			Connection1.close ();
			queryselect_queuemember.close ();
			return "1";
		}
	}
}
