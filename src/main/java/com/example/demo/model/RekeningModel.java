package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RekeningModel
{
	public int rekening_id;
	public String nik;
	public String nama;
	public String nomor_telepon;
	public String alamat;
	public String nama_ortu;
	public String foto_ktp;
	public String foto;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp insert_at;
	public String status;
}
