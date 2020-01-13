package com.example.demo.controller;

import com.example.demo.config.RestTempleteConfig;
import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.*;
import com.example.demo.query.*;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(produces = "application/json", path = "/queue_member")
public class Queue_MemberController
{
	PreparedStatement querydelete_alembic_version_config = null;
	AllDeleteQuery query_string_delete = new AllDeleteQuery();
	stringkoneksi sk = new stringkoneksi();
	AllQuery query_string = new AllQuery();
	PreparedStatement queryselect_queuemember = null;

	AllInsertQuery query_string_insert = new AllInsertQuery();
	PreparedStatement query_insert_queue_members = null;

	@PostMapping("/addQueueMember")
	public String addQueueMember(@RequestBody Queue_MemberModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		query_insert_queue_members = Connection1.prepareStatement(query_string_insert.query_insert_queue_members);

		query_insert_queue_members.setString(1, cfm.queue_name);
		query_insert_queue_members.setString(2, cfm._interface);
		query_insert_queue_members.setString(3, cfm.membername);
		query_insert_queue_members.setString(4, cfm.state_interface);
		query_insert_queue_members.setInt(5, cfm.penalty);
		query_insert_queue_members.setInt(6, cfm.paused);
		query_insert_queue_members.setInt(7, cfm.wrapuptime);

		int Cursor1 = query_insert_queue_members.executeUpdate();// Evaluate (Connected_Expression1)
		String a = "1";
		query_insert_queue_members.close();
		Connection1.close();
		return a;
	}

	@PostMapping("/updateQueueName")
	public int updateQueueName(@RequestBody Queue_MemberModel cfm) throws SQLException
	{
		Connection connection = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement("UPDATE queue_members SET queue_name = ? WHERE interface = concat('PJSIP/',?) ; ");

		query.setString(1, cfm.queue_name);
		query.setString(2, cfm.extension);

		int Cursor1 = query.executeUpdate();
		query.close();
		connection.close();

		return Cursor1;
	}

	@PostMapping("/updatePaused")
	public int updatePaused(@RequestBody Queue_MemberModel cfm) throws SQLException
	{
		Connection connection = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		PreparedStatement query = connection
				.prepareStatement("UPDATE queue_members SET paused = ? WHERE interface = concat('PJSIP/',?) ; ");

		query.setInt(1, cfm.paused);
		query.setString(2, cfm.extension);

		int Cursor1 = query.executeUpdate();
		query.close();
		connection.close();

		return Cursor1;
	}

	@GetMapping("/getAllQueueMember")
	public ArrayList<Queue_MemberModel> getQueueMember() throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		queryselect_queuemember = Connection1.prepareStatement(query_string.query_select_queue_members);
		ResultSet Cursor1 = queryselect_queuemember.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Queue_MemberModel> ListUser1 = new ArrayList<Queue_MemberModel>();
		while (Cursor1.next()) // while there_is_next_record_in (Cursor1)
		{
			Queue_MemberModel ModelQueue_member = new Queue_MemberModel();
			ModelQueue_member.queue_name = Cursor1.getString(1);
			ModelQueue_member._interface = Cursor1.getString(2);
			ModelQueue_member.membername = Cursor1.getString(3);
			ModelQueue_member.state_interface = Cursor1.getString(4);
			ModelQueue_member.penalty = Cursor1.getInt(5);
			ModelQueue_member.paused = Cursor1.getInt(6);
			ModelQueue_member.uniqueid = Cursor1.getInt(7);

			ListUser1.add(ModelQueue_member);

		}
		Connection1.close();
		queryselect_queuemember.close();
		return ListUser1;
	}

	@DeleteMapping(path = "/deleteQueueMember", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteQueueMember(@RequestBody Queue_MemberModel cfm) throws SQLException
	{
		Connection Connection1 = DriverManager.getConnection(sk.Path_expr, sk.service_user, sk.service_password);
		querydelete_alembic_version_config = Connection1.prepareStatement(query_string_delete.query_delete_queue_members);
		querydelete_alembic_version_config.setString(1, cfm.queue_name);
		int Cursor1 = querydelete_alembic_version_config.executeUpdate();// Evaluate (Connected_Expression1)
		int a = 0;
		Connection1.close();
		querydelete_alembic_version_config.close();
		return a;
	}

	@GetMapping("/getQueueMemberBy")
	public ResponseEntity<ArrayList<Queue_MemberModel2>> getData(@RequestParam String bla)
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// RestTempleteConfig.disableSslVerification();
		RestTemplate restTemplate = new RestTempleteConfig().getRestTemplate();
		String uri = "https://10.30.1.17:8089/amxml?action=queuestatus";
		ResponseEntity<String> entity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
//		System.out.println(entity.getBody());

		String[] str_array = entity.getBody().split("\n");
		ArrayList<Queue_MemberModel2> result = getQueueByBla(str_array, bla);

//		for (Queue_MemberModel2 i : result)
//		{
//			System.out.println(i.name);
//		}
		
		if (result.size() > 0)
			return new ResponseEntity<ArrayList<Queue_MemberModel2>>(result, HttpStatus.OK);
		else
			return new ResponseEntity<ArrayList<Queue_MemberModel2>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getAllQueueMembers")
	public ArrayList<Queue_MemberModel2> getAllData()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// RestTempleteConfig.disableSslVerification();
		RestTemplate restTemplate = new RestTempleteConfig().getRestTemplate();
		String uri = "https://10.30.1.17:8089/amxml?action=queuestatus";
		ResponseEntity<String> entity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
//		System.out.println(entity.getBody());

		String[] str_array = entity.getBody().split("\n");
		ArrayList<Queue_MemberModel2> result = getAllQueue(str_array);

//		for (Queue_MemberModel2 i : result) {
//			System.out.println(i.name);
//		}

		return result;
	}

	public static Queue_MemberModel2 queueMember = new Queue_MemberModel2();

	static ArrayList<Queue_MemberModel2> getQueueByBla(String[] lines, String bla)
	{
		// kalimat
		ArrayList<Queue_MemberModel2> listQueue = new ArrayList<Queue_MemberModel2>();
		boolean[] isFound = new boolean[lines.length];
		boolean[] isFound2 = new boolean[lines.length];
		String[] sentence = lines;
		for (int i = 0; i < sentence.length; i++)
		{
			isFound[i] = lines[i].indexOf("QueueMember") != -1 ? true : false;
			isFound2[i] = lines[i].indexOf(bla) == -1 ? false : true;
			if (isFound[i])
			{
				if (isFound2[i])
				{
					queueMember = new Queue_MemberModel2();
					String[] words = lines[i].split("='");
					queueMember.event = words[3].replaceAll("' queue", "");
					queueMember.queue = words[4].replaceAll("' name", "");
					queueMember.name = words[5].replaceAll("' location", "");
					queueMember.location = words[6].replaceAll("' stateinterface", "");
					queueMember.stateinterface = words[7].replaceAll("' membership", "");
					queueMember.membership = words[8].replaceAll("' penalty", "");
					queueMember.penalty = words[9].replaceAll("' callstaken", "");
					queueMember.callstaken = words[10].replaceAll("' lastcall", "");
					queueMember.lastcall = words[11].replaceAll("' lastpause", "");
					queueMember.lastpause = words[12].replaceAll("' incall", "");
					queueMember.incall = words[13].replaceAll("' status", "");
					queueMember.status = words[14].replaceAll("' paused", "");
					queueMember.paused = words[15].replaceAll("' pausedreason", "");
					queueMember.pausedreason = words[16].replaceAll("' wraptime", "");
					queueMember.wrapuptime = words[17].replaceAll("' /></response>", "");

					listQueue.add(queueMember);
				}
			}
//			if (i > sentence.length)
//			{
//				break;
//			}
		}

		return listQueue;

	}

	static ArrayList<Queue_MemberModel2> getAllQueue(String[] lines)
	{
		// kalimat
		ArrayList<Queue_MemberModel2> listQueue = new ArrayList<Queue_MemberModel2>();
		boolean[] isFound = new boolean[lines.length];
		String[] sentence = lines;
		for (int i = 0; i < sentence.length; i++)
		{
			isFound[i] = lines[i].indexOf("QueueMember") != -1 ? true : false;
			if (isFound[i])
			{
				queueMember = new Queue_MemberModel2();
				String[] words = lines[i].split("='");
				queueMember.event = words[3].replaceAll("' queue", "");
				queueMember.queue = words[4].replaceAll("' name", "");
				queueMember.name = words[5].replaceAll("' location", "");
				queueMember.location = words[6].replaceAll("' stateinterface", "");
				queueMember.stateinterface = words[7].replaceAll("' membership", "");
				queueMember.membership = words[8].replaceAll("' penalty", "");
				queueMember.penalty = words[9].replaceAll("' callstaken", "");
				queueMember.callstaken = words[10].replaceAll("' lastcall", "");
				queueMember.lastcall = words[11].replaceAll("' lastpause", "");
				queueMember.lastpause = words[12].replaceAll("' incall", "");
				queueMember.incall = words[13].replaceAll("' status", "");
				queueMember.status = words[14].replaceAll("' paused", "");
				queueMember.paused = words[15].replaceAll("' pausedreason", "");
				queueMember.pausedreason = words[16].replaceAll("' wraptime", "");
				queueMember.wrapuptime = words[17].replaceAll("' /></response>", "");

				listQueue.add(queueMember);
			}
//			if (i > sentence.length)
//			{
//				break;
//			}
		}

		return listQueue;

	}
}
