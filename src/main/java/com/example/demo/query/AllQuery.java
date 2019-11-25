package com.example.demo.query;

public class AllQuery {
public String query_select_user="select nama_user from user_collection where nama_user=?";
public String query_select_nama_email="select * from user_collection where nama_user=? OR email=?";

}
