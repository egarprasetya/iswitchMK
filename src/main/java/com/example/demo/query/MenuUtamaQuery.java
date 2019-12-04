package com.example.demo.query;

public class MenuUtamaQuery
{
	public String query_profil = "select users.nama, status.status_nama, users.avatar "
			+ "from users join status on users.status = status.status_id "
			+ "where users.id = ?";
	public String query_status = "select * from status";
}
