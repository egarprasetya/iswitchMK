package com.example.demo.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RekeningModel
{
	public int rekening_id;
	public String nik;
	public String nama;
	public String tempat_lahir;
	public String tanggal_lahir;
	public String nomor_telepon;
	public String nama_ibu;
	public String alamat;
	public String rt_rw;
	public String kelurahan;
	public String kecamatan;
	public String kota;
	public String kode_pos;
	public String foto;
	public String foto_ktp;
	public String foto_ttd;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss[.fffffffff]")
	public String insert_at;
	public String no_rekening;
	public String jenis_tabungan;
	public String kantor_cabang_terdaftar;
	public String nomor_kartu;
	public String application_status;
	public String extension;
}
