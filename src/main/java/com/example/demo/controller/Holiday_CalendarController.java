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

import com.example.demo.connection.stringkoneksi;
import com.example.demo.model.After_Call_WorkModel;
import com.example.demo.model.Holiday_CalendarModel;
import com.example.demo.model.UserModel;
import com.example.demo.query.AllDeleteQuery;
import com.example.demo.query.AllQuery;
import com.example.demo.query.AllSelectParameterQuery;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/holiday_calendar")
public class Holiday_CalendarController
{

	@Autowired
	private DataSource dataSource;

	@PostMapping(produces = "application/json", path = "/insert_holiday_calendar")
	public String doInsertHolidayCalendar(@RequestBody Holiday_CalendarModel cfm) throws SQLException
	{
		// Connection Connection1 = DriverManager.getConnection(sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection Connection1 = dataSource.getConnection();
		PreparedStatement queryinsert_cdr = Connection1.prepareStatement("INSERT INTO public.holiday_calendar "
				+ "(date_holiday, description, createdon, createdby) " + "VALUES(?,?,?,?);");
		queryinsert_cdr.setDate(1, cfm.date_holiday);
		queryinsert_cdr.setString(2, cfm.description);
		queryinsert_cdr.setTimestamp(3, cfm.createdon);
		queryinsert_cdr.setString(4, cfm.createdby);

		int flag = queryinsert_cdr.executeUpdate();// Evaluate (Connected_Expression1)

		queryinsert_cdr.close();
		Connection1.close();

		return "{ " + "\"response\":" + "\"" + 1 + "\" }";

	}
	
	@PostMapping(produces = "application/json", path = "/get_holiday_calendar")
	public ArrayList<Holiday_CalendarModel> getHolidayCalendar(@RequestBody Holiday_CalendarModel cfm) throws SQLException
	{
		

		// Connection connection = DriverManager.getConnection (sk.Path_expr,
		// sk.service_user, sk.service_password);
		Connection connection = dataSource.getConnection();
		PreparedStatement a = connection.prepareStatement(
				"SELECT id, date_holiday, description, createdon, createdby, modifiedon, modifiedby, state_code\r\n"
						+ "FROM public.holiday_calendar;");

		ResultSet Cursor1 = a.executeQuery();// Evaluate (Connected_Expression1)
		ArrayList<Holiday_CalendarModel> ListUser1 = new ArrayList<Holiday_CalendarModel>();
		while (Cursor1.next()) {
			// System.out.println (Cursor1.getString (1));
			Holiday_CalendarModel Modeluser = new Holiday_CalendarModel();
			Modeluser.id = Cursor1.getInt(1);
			Modeluser.date_holiday = Cursor1.getDate(2);
			Modeluser.description = Cursor1.getString(3);
			Modeluser.createdon = Cursor1.getTimestamp(4);
			Modeluser.createdby = Cursor1.getString(5);
			Modeluser.modifiedon = Cursor1.getTimestamp(6);
			Modeluser.modifiedby = Cursor1.getString(7);
			Modeluser.state_code = Cursor1.getString(8);
			ListUser1.add(Modeluser);
		}
		return ListUser1;

	}
}
