package com.example.demo.query;

public class SettingProfilQuery
{
	public String query_setting_profil = "select users.nama, users.username, users.password, users.phone_number, status.status_nama, users.avatar  "
			+ "from users join status on users.status = status.status_id"
			+ "where users.id = ?";
	
}