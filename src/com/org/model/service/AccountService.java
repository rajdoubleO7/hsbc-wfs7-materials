package com.org.model.service;

import com.org.Exception.AccountAlreadyExistsException;
import com.org.Exception.AccountDoesnotExistsException;
import com.org.Exception.InsufficentFundException;
import java.util.List;

import com.org.model.beans.Account;

public interface AccountService {
	public Account createAccount(Account account) throws AccountAlreadyExistsException;
	public double getBalance(int accountNumber) throws AccountDoesnotExistsException;
	// call updateBalance() on source & destination account
	public void transfer(int sourceAccount, int destincationAccount, double amount) throws InsufficentFundException,AccountDoesnotExistsException;
	public List<Account> getAccountsSortedByName();
	public List<Account> getAccountsSortedByAccountNumber();
        public void  deleteAccount(int accno) throws AccountDoesnotExistsException;
}
