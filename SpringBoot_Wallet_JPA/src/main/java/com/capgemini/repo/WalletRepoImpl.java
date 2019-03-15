package com.capgemini.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.bean.Customer;
import com.capgemini.entitymanager.Util;
import com.capgemini.exception.AlreadyRegisteredMobileException;
import com.capgemini.exception.MobileNotFoundException;


@Repository
@Transactional
public class WalletRepoImpl implements WalletRepo{

	
	@PersistenceContext
	EntityManager entitymanager;

	@Override
	public boolean save(Customer customer){
		
				
		if(entitymanager.find(Customer.class, customer.getMobileno()) == null) {
		
			entitymanager.persist(customer);		   
		
			return true;
		}
		return false;
		   
	}

	@Override
	public Customer findMobile(String mobileno) {

		
		Customer c = entitymanager.find(Customer.class, mobileno);
		 
		  
		
		return c;
		
		
		
		// TODO Auto-generated method stub
		
		/*if(map.containsKey(mobileno))
		{
			return map.get(mobileno);
		}
		throw new MobileNotFoundException();
		*/
		
	}
	
	public void update(Customer customer)
	{
		Customer c=new Customer();
	
		
	      c=entitymanager.find(Customer.class, customer.getMobileno());
		
	     
		c.setWallet(customer.getWallet());

	}

	public List getSameName(String name) {
		
		Query q=entitymanager.createQuery("Select c FROM Customer c WHERE c.name=:name").setParameter("name", name);
		List<Customer> list=new ArrayList<>(q.getResultList());
		return list;
	}

}