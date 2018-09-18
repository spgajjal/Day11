package com.capgemini.bankapplication.dao.impl;

import com.capgemini.bankapplication.dao.CustomerDao;
import com.capgemini.bankapplication.database.Database;
import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	
	
	@Override
	public Customer authenticate(Customer customer) throws AccountNotFoundException{
		for(Customer cust:Database.customerSet)
		{
			if(cust.getCustomerId()==customer.getCustomerId()) {
				if(cust.getPassword().equals(customer.getPassword()))
				{
					return cust ;
				}
				else
				{
					return null ;
				}
			}
			
		}
		throw new AccountNotFoundException("Account Id not found!!") ;
		
		
	}

	@Override
	public Customer updateProfile(Customer customer) {
		for(Customer cust:Database.customerSet)
		{
			if(cust.getCustomerId()==customer.getCustomerId()) {
				cust.setAddress(customer.getAddress());
				cust.setCustomerName(customer.getCustomerName());
				cust.setDateOfBirth(customer.getDateOfBirth());
				cust.setEmail(customer.getEmail());
				return cust ;
			}
		}
		return null ;
	}

	@Override
	public boolean updatePassword(Customer customer, String newPassword,String oldPassword) {
		
		for(Customer cust:Database.customerSet)
		{
			System.out.println(cust.getCustomerId()+"----"+customer.getCustomerId());
			if(cust.getCustomerId()==customer.getCustomerId()) {
				System.out.println("Id matches");
				System.out.println(cust.getPassword()+"----"+customer.getPassword());
				if(cust.getPassword().equals(customer.getPassword()))
				{
					cust.setPassword(newPassword);
					return true ;
				}
					
			}
		}
		return false ;
		}

}
