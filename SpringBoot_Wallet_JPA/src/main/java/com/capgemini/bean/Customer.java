package com.capgemini.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern; 

@Entity
@Table(name="mycustomer")
public class Customer {
	
	@Id
	@NotNull(message="null not allowed")
	@Pattern(regexp="[6-9][0-9]{9}", message="please enter a valid number")
	private String mobileno;
	
	@NotNull(message="null not allowed")
	@Pattern(regexp="[A-Za-z]{3,15}", message="please enter a valid name")
	private String name;
	
	@Embedded
	private Wallet wallet;
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileno=" + mobileno + ", wallet=" + wallet.getBalance() + "]\n";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}