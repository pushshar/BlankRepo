package com.capgemini.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.bean.Customer;
import com.capgemini.exception.AlreadyRegisteredMobileException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NegativeWithdrawalException;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

@RestController
public class CustomerController {

	@Autowired
	WalletService walletService;
	
	Customer customer;
	
	@RequestMapping(method=RequestMethod.POST, value="/addCustomer")
	public Customer createAccount(@Valid @RequestBody Customer customer ) throws AlreadyRegisteredMobileException {
		
		
		return walletService.createAccount(customer.getName(), customer.getMobileno(),customer.getWallet().getBalance());
		
		
		
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getBalance/{mobileNo}")
	
	public Customer showBalance(@PathVariable String mobileNo) throws MobileNotFoundException
	{
		return (Customer) walletService.showBalance(mobileNo);
		
		
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/depositBalance/{mobileNo}/{amount}")
		
		public Customer depositBalance(@PathVariable String mobileNo ,@PathVariable BigDecimal amount) throws MobileNotFoundException {
			
		return walletService.depositAmount(mobileNo, amount);
		
		
		
		}
		
	
	@RequestMapping(method=RequestMethod.POST, value="/withdrawBalance/{mobileNo}/{amount}")

		public Customer withdrawBalance(@PathVariable String mobileNo ,@PathVariable BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException 
	{
		return walletService.withdrawAmount(mobileNo, amount);
		
		
			 
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")

	public Customer fundTransfer(@PathVariable String sourceMobileNo ,@PathVariable String targetMobileNo ,@PathVariable BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException 
{
		
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
		
	
		
}
	
	@RequestMapping(method=RequestMethod.GET, value="/getSameName/{name}")

	public List getSameName(@PathVariable String name) 
{
		
		return walletService.getSameName(name);
		
}
	

}




