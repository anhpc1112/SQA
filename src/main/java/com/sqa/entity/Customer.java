package com.sqa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	
	
	@Column
	private String hoTen;
	
	@Column
	private String gioiTinh;
	
	@Column
	@Pattern(regexp = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}",message = "Ngày sinh không đúng định dạng")
	private String dob;
	
	
	
	@Column
	private String quocTich;
	
	@Column
	private String danToc;
	
	@Column
	private String cmnd;
	
	@Column
	private String sdt;
	
	@Column
	private String diaChiKhaiSinh;
	
	@Column
	private String diaChiNhanKetQua;
	
	@Column
	private String ngheNghiep;
	
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private Payment payment;

	
	@Column 
	private String email;
	
	@Column
	private String password;
	
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	




	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}





	public String getQuocTich() {
		return quocTich;
	}


	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}


	public String getDanToc() {
		return danToc;
	}


	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}


	public String getCmnd() {
		return cmnd;
	}


	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getDiaChiKhaiSinh() {
		return diaChiKhaiSinh;
	}


	public void setDiaChiKhaiSinh(String diaChiKhaiSinh) {
		this.diaChiKhaiSinh = diaChiKhaiSinh;
	}


	public String getDiaChiNhanKetQua() {
		return diaChiNhanKetQua;
	}


	public void setDiaChiNhanKetQua(String diaChiNhanKetQua) {
		this.diaChiNhanKetQua = diaChiNhanKetQua;
	}





	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	
	
	public Customer() {
		
	}





	public String getNgheNghiep() {
		return ngheNghiep;
	}


	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}













	public void setDob(String dob) {
		this.dob = dob;
	}

	

	public String getDob() {
		return dob;
	}


	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Customer(String hoTen, String gioiTinh, String dob, String quocTich, String danToc, String cmnd, String sdt,
			String diaChiKhaiSinh, String diaChiNhanKetQua, String ngheNghiep, Payment payment, String email,
			String password) {
		super();
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.dob = dob;
		this.quocTich = quocTich;
		this.danToc = danToc;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diaChiKhaiSinh = diaChiKhaiSinh;
		this.diaChiNhanKetQua = diaChiNhanKetQua;
		this.ngheNghiep = ngheNghiep;
		this.payment = payment;
		this.email = email;
		this.password = password;
	}



	




	






	
	
	
	
	
	

	


	
	
	
	
	
	
}
