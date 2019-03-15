package com.capgemini.exception;

import java.math.BigDecimal;

public class NegativeWithdrawalException extends Exception {

	BigDecimal balance;
	public NegativeWithdrawalException(BigDecimal balance) {
			
		this.balance=balance;
			
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Your Account has "+balance+"\n Please withdraw respectively\n";
	}
	
	

}