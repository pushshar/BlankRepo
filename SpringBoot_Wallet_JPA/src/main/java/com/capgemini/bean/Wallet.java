package com.capgemini.bean;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
public class Wallet {

	@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}

	@NotNull(message="null not allowed")
	@Pattern(regexp="[0-9]+", message="please enter a valid balance")
	private BigDecimal balance;

	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	

}