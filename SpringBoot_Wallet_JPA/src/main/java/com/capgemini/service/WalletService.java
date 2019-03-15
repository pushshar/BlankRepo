package com.capgemini.service;

import java.math.BigDecimal;
//import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.capgemini.bean.Customer;
import com.capgemini.exception.AlreadyRegisteredMobileException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NegativeWithdrawalException;


public interface WalletService {

	public Customer createAccount(String name,String mobileNo,BigDecimal amount) throws AlreadyRegisteredMobileException ;
	public Object showBalance(String mobileNo) throws MobileNotFoundException ;
	public Customer fundTransfer(String sourceMobileNo,String targetMobileNo,BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException;
	public Customer depositAmount(String mobileNo,BigDecimal amount) throws MobileNotFoundException ;
	public Customer withdrawAmount(String mobileNo,BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException ;
	public List getSameName(String name);
	
	
	
}
