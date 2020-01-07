package com.example.demo.query;

public class AllDeleteQuery {

	public String query_delete_meetme = "delete from meetme where bookid = ?";
	public String query_delete_ps_globals = "delete from ps_globals where id = ?";
	public String query_delete_sippeers = "delete from sippeers where id = ?";
	public String query_delete_alembic_version = "delete from alembic_version where version_num = ?";
	public String query_delete_alembic_version_config = "delete from alembic_version_config where version_num = ?";
	public String query_delete_cdr = "delete from cdr where accountcode = ?";
	public String query_delete_extension = "delete from extensions where id = ?";
	public String query_delete_iaxfriends = "delete from iaxfriends where id = ?";
	public String query_delete_musiconhold = "delete from musiconhold where name = ?";
	public String query_delete_ps_aors = "delete from ps_aors where id = ?";
	public String query_delete_ps_asterisk_publications = "delete from ps_asterisk_publications where id = ?";
	public String query_delete_ps_auths = "delete from ps_auths where id = ?";
	public String query_delete_ps_contacts = "delete from ps_contacts where id = ?";
	public String query_delete_ps_domain_aliases = "delete from ps_domain_aliases where id = ?";
	public String query_delete_ps_endpoints = "delete from ps_endpoints where id = ?";
	public String query_delete_ps_inbound_publications = "delete from ps_inbound_publications where id = ?";
	public String query_delete_ps_outbound_publishes = "delete from ps_outbound_publishes where id = ?";
	public String query_delete_ps_registrations = "delete from ps_registrations where id = ?";
	public String query_delete_ps_resource_list = "delete from ps_resource_list where id = ?";
	public String query_delete_ps_subscription_persistence = "delete from ps_subscription_persistence where id = ?";
	public String query_delete_ps_systems = "delete from ps_systems where id = ?";
	public String query_delete_ps_transports = "delete from ps_transports where id = ?";
	public String query_delete_queue_log = "delete from queue_log where id = ?";
	public String query_delete_queue_members = "delete from queue_members where queue_name = ?";
	public String query_delete_queue_rules = "delete from queue_rules where rule_name = ?";
	public String query_delete_queues = "delete from queues where name = ?";
	public String query_delete_voicemail = "delete from voicemail where uniqueid = ?";
	public String query_delete_ps_endpoints_id = "delete from ps_endpoint_id_ips where id = ?";
	public String query_delete_domain_aliases = "delete from ps_domain_aliases where id = ?";

	public String query_delete_user = "delete from users where user_id=?";
}
