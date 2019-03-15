package com.capgemini.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exception.AlreadyRegisteredMobileException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NegativeWithdrawalException;
import com.capgemini.repo.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	WalletRepo repo;
	
	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}
	
	
	
	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) throws AlreadyRegisteredMobileException {
		
		Customer enroll = new Customer();
		Wallet wallet=new Wallet();
		enroll.setName(name);
		enroll.setMobileno(mobileno);
		
		wallet.setBalance(amount);
		enroll.setWallet(wallet);
		if(repo.save(enroll)!=true)
		{
			throw new AlreadyRegisteredMobileException("Mobile number already registered");
		}
	
		return enroll;
	}

	
	

	@Override
	public Customer showBalance(String mobileNo) throws MobileNotFoundException {
		// TODO Auto-generated method stub
		Customer enroll=new Customer();
		enroll=repo.findMobile(mobileNo);
		if(enroll==null)
		{
				  throw new MobileNotFoundException("Please Input a Registered number");
		}
		return enroll;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException {
		Customer enroll1=new Customer();
		Customer enroll2=new Customer();

		enroll1=withdrawAmount(sourceMobileNo, amount);
		enroll2=depositAmount(targetMobileNo, amount);
		
		
		return enroll1;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNotFoundException {
		
		Customer enroll=new Customer();
		Wallet wallet=new Wallet();
		enroll=repo.findMobile(mobileNo);
		if(enroll==null)
		{
				  throw new MobileNotFoundException("Please Input a Registered number");
		}
		
	
		wallet.setBalance(enroll.getWallet().getBalance().add(amount));
		enroll.setWallet(wallet);
		repo.update(enroll);
		return enroll;
		
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws MobileNotFoundException, NegativeWithdrawalException {
		Customer enroll=new Customer();
		Wallet wallet=new Wallet();
		enroll=repo.findMobile(mobileNo);
		if(enroll==null)
		{
				  throw new MobileNotFoundException("Please Input a Registered number");
		}
		if((enroll.getWallet().getBalance()).compareTo(amount)>0)
		{

			wallet.setBalance(enroll.getWallet().getBalance().subtract(amount));
			enroll.setWallet(wallet);
			repo.update(enroll);
			return enroll;
		}
		throw new NegativeWithdrawalException(enroll.getWallet().getBalance());
		

		
	}



	@Override
	public List getSameName(String name) {

		
		return (List) repo.getSameName(name);
	}
	

}

	

