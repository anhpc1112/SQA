package com.sqa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column
	private int kiHan;
	

	@Column
	private double tongTien;
	
	
	@OneToOne(mappedBy = "payment")
	private Customer customer;


	public Payment() {
		
	}


	public Payment(int kiHan, double tongTien, Customer customer) {
		super();
		this.kiHan = kiHan;
		this.tongTien = tongTien;
		this.customer = customer;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public int getKiHan() {
		return kiHan;
	}


	public void setKiHan(int kiHan) {
		this.kiHan = kiHan;
	}


	public double getTongTien() {
		return tongTien;
	}


	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
}
