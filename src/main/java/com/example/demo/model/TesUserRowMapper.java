package com.example.demo.model;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
public class TesUserRowMapper implements RowMapper<TesUserModel>
{
	public TesUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		TesUserModel dh = new TesUserModel();
		dh.setNama(rs.getString("nama"));
		dh.setUmur(rs.getInt("umur"));
		return dh;
	}
}
