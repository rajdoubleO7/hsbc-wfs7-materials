package com.org.model.dao;

import com.org.Exception.AccountAlreadyExistsException;
import com.org.Exception.AccountDoesnotExistsException;
import com.org.Exception.InsufficentFundException;
import java.util.List;

import com.org.model.beans.Account;

public interface AccountDao {
	public Account createAccount(Account account) throws AccountAlreadyExistsException;
	public Account debit(int accountNumber, double amount) throws InsufficentFundException,AccountDoesnotExistsException;
        public Account credit(int accountNumber, double amount) throws AccountDoesnotExistsException;
	public Account getAccount(int accountNumber)  throws AccountDoesnotExistsException;
        public void deleteAccount(int accNo) throws AccountDoesnotExistsException;
	public List<Account> getAccounts();
}
