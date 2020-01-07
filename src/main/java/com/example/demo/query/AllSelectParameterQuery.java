package com.example.demo.query;

public class AllSelectParameterQuery {
	public String query_select_parameter_meetme="select * from meetme where bookid = ?";
	public String query_select_parameter_ps_globals="select * from ps_globals where id = ?";
	public String query_select_parameter_sippeers ="select*from sippeers where id = ?";
	public String query_select_parameter_alembic_version="select*from alembic_version where version_num = ?";
	public String query_select_parameter_alembic_version_config="select*from alembic_version_config where version_num = ?";
	public String query_select_parameter_cdr="select*from cdr where accountcode = ?";
	public String query_select_parameter_extension="select*from extensions where id = ?";
	public String query_select_parameter_iaxfriends="select*from iaxfriends where id = ?";
	public String query_select_parameter_musiconhold="select*from musiconhold where name = ?";
	public String query_select_parameter_ps_aors="select*from ps_aors where id = ?";
	public String query_select_parameter_ps_asterisk_publications="select*from ps_asterisk_publications where id = ?";
	public String query_select_parameter_ps_auths="select*from ps_auths where id = ?";
	public String query_select_parameter_ps_contacts="select*from ps_contacts where id = ?";
	public String query_select_parameter_ps_domain_aliases="select*from ps_domain_aliases where id = ?";
	public String query_select_parameter_ps_endpoints="select*from ps_endpoints where id = ?";
	public String query_select_parameter_ps_inbound_publications="select*from ps_inbound_publications where id = ?";
	public String query_select_parameter_ps_outbound_publishes="select*from ps_outbound_publishes where id = ?";
	public String query_select_parameter_ps_registrations="select*from ps_registrations where id = ?";
	public String query_select_parameter_ps_resource_list="select*from ps_resource_list where id = ?";
	public String query_select_parameter_ps_subscription_persistence="select*from ps_subscription_persistence where id = ?";
	public String query_select_parameter_ps_systems="select*from ps_systems where id = ?";
	public String query_select_parameter_ps_transports="select*from ps_transports where id = ?";
	public String query_select_parameter_queue_log="select*from queue_log where id = ?";
	public String query_select_parameter_queue_members="select*from queue_members where queue_name = ?";
	public String query_select_parameter_queue_rules="select*from queue_rules where rule_name = ?";
	public String query_select_parameter_queues="select*from queues where name = ?";
	public String query_select_parameter_voicemail="select*from voicemail where uniqueid = ?";
	public String query_select_parameter_ps_endpoints_id="select*from ps_endpoint_id_ips where id = ?";
	public String query_select_parameter_domain_aliases="select*from ps_domain_aliases where id = ?";
//	public String query_select_parameter_user="select*from users where nama=? where id = ?";

	public String query_login = "select users.* "
			+ "from users  "
			+ "where users.username = ? "; 
	
	public String query_password = "select users.password "
			+ "from users  "
			+ "where user_id = ? "; 			// where user_id
	
	public String query_login0 = "select * "
			+ "from users "
			+ "where users.username = ? and users.password = ? "; 
	 
//	public String query_login1 = "select id_user, extension_user" + 
//			"from users"
//			+ "where username = ? and password = ?";
	
	public String query_logout = "SELECT * FROM users WHERE user_id = ?"; // where user_id;
	
	public String query_profil = "select nama, status, avatar "
			+ "from users "
			+ "where user_id = ?";				// where user_id;
	

	public String query_get_user_cdr ="select customers.nomor_telepon, cdr.duration, cdr.\"start\", cdr.disposition "
			+	"from users join cdr on users.extension_user = cdr.dst join customers on cdr.src = customers.extension "
			+ "where user_id = ? order by cdr.\"start\" desc limit 10";		// where user_id
	
	public String query_setting_profil = "select users.nama, users.username, users.password, users.phone_number, users.extension_user, status.status_nama, users.avatar  "
			+ "from users join status on users.status = status.status_id"
			+ "where user_id = ?";		// where user_id;
	
	public String query_setting_update_profil = "UPDATE users " + 
			"SET nama=?, username = ?, password = ?, number = ?, status = ?, extension_user = ? " + 
			"WHERE user_id=?;";			// where user_id;
	
	
	public String query_customer = "SELECT * FROM customers WHERE extension = ?";
	
	public String query_user_by_status_skill = "select users.* " + 
			"from users join queue_members on users.skill = queue_members.interface " + 
			"where " + 
			"queue_members.queue_name = '?' and users.status = '1' and queue_members.paused = 0;";
	
}
