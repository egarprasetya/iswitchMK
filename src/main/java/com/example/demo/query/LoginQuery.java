package com.example.demo.query;

public class LoginQuery
{
	public String query_login1 = "select users.id, users.username, users.password, ps_auths.realm "
			+ "from users join ps_auths on users.extension = ps_auths.id join status on users.status = status.status_id "
			+ "where users.username = ? and users.password = ?"; 
	public String query_login2 = "UPDATE users SET status = ? WHERE id=?"; 
}
