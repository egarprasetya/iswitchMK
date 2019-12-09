package com.example.demo.query;

public class LoginQuery
{
	public String query_login = "select users.user_id, users.username, ps_auths.realm "
			+ "from users join ps_auths on users.extensions_user = ps_auths.id  "
			+ "where users.username = ? and users.password = ? "; 
	
	public String query_login0 = "select * "
			+ "from users "
			+ "where users.username = ? and users.password = ? "; 
	 
		public String query_login1 = "select id_user, extension_user" + 
				"from users"
				+ "where username = ? and password = ?";
	public String query_login2 = "UPDATE users SET status = ? WHERE user_id=?"; 
}
