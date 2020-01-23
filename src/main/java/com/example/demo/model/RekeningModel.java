package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RekeningModel
{
	public int rekening_id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss[.fffffffff]")
	public String insert_at;
	public String no_rekening;
	public String jenis_tabungan;
	public String kantor_cabang_terdaftar;
	public String nomor_kartu;
	public String application_status;
	public String extension;
	public String status;
}
