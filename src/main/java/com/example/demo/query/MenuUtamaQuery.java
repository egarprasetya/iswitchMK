package com.example.demo.query;

public class MenuUtamaQuery
{
	public String query_profil = "select users.nama, status.nama_status, users.avatar "
			+ "from users join status on users.status = status.status_id "
			+ "where users.user_id = ? and users.status = '1'";
	public String query_status = "select * from status";
	public String query_get_user_cdr ="select cdr.* "
			+ "from users join cdr on users.user_id = cdr.dst "
			+ "where cdr.dst = ?";
}
