package com.capgemini.repo;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.bean.Customer;
import com.capgemini.exception.AlreadyRegisteredMobileException;
import com.capgemini.exception.MobileNotFoundException;

import java.sql.SQLException;
import java.util.List;



public interface WalletRepo {
	
	public boolean save(Customer customer) ;
	public Customer findMobile(String mobileno) ;

	public void update(Customer enroll);
	public List getSameName(String name);

	

}