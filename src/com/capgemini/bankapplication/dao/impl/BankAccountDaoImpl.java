package com.capgemini.bankapplication.dao.impl;

import com.capgemini.bankapplication.dao.BankAccountDao;
import com.capgemini.bankapplication.database.Database;
import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;

public class BankAccountDaoImpl implements BankAccountDao {
	
	
	
	@Override
	public double getBalance(long accountId) {
		
		for(Customer c1 : Database.customerSet)
		{
			BankAccount b=c1.getAccount() ;
			if(b.getAccountId()==accountId)
			{
				return b.getBalance() ;
			}
		}
		return -1 ;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		for(Customer c1 : Database.customerSet)
		{
			BankAccount b=c1.getAccount() ;
			if(b.getAccountId()==accountId)
			{
				b.setBalance(newBalance);
				return true ;
			}
		}
		return false ;
	}

}
