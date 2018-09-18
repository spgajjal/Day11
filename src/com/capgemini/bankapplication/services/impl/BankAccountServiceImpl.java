package com.capgemini.bankapplication.services.impl;

import com.capgemini.bankapplication.dao.BankAccountDao;
import com.capgemini.bankapplication.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapplication.database.Database;
import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankapplication.exceptions.NegativeAmountException;
import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.services.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {
	BankAccountDao bankDaoObj=new BankAccountDaoImpl() ;

	@Override
	public double getBalance(long accountId) {
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public double withdraw(long accountId, double amount) {
		double balance=bankDaoObj.getBalance(accountId) ;
		bankDaoObj.updateBalance(accountId, balance-amount)  ;
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance=bankDaoObj.getBalance(accountId) ;
		bankDaoObj.updateBalance(accountId, (balance+amount))  ;
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InsufficientAccountBalanceException,AccountNotFoundException,NegativeAmountException{
		boolean found=false ;
		boolean balProblem=false ;
		boolean negAmount=false ;
		for(Customer c1 : Database.customerSet)
		{
			BankAccount b=c1.getAccount() ;
			if(b.getAccountId()==toAcc)
			{
				found=true ;
				if(b.getBalance()<amount)
				{
					balProblem=true ;
				}
			}
		}
		if(balProblem==true)
		{
			throw new InsufficientAccountBalanceException("Your account balance is low!!") ;
		}
		if(found==false)
		{
			throw new AccountNotFoundException("The Account Id is incorrect!!") ;
		}
		if(amount<0)
		{
			throw new NegativeAmountException("You have entered negative amount") ;
		}
		
		withdraw(fromAcc, amount) ;
		deposit(toAcc, amount) ;
		return true ;
	} 
		
		
	
	

}
