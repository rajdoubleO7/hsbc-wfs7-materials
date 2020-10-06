package com.org.model.service;

import com.org.Exception.AccountAlreadyExistsException;
import com.org.Exception.AccountDoesnotExistsException;
import com.org.Exception.InsufficentFundException;
import java.util.List;
import java.util.stream.Collectors;

import com.org.model.beans.Account;
import com.org.model.dao.AccountDao;
import com.org.model.util.ObjectFactory;

public class AccountServiceImpl implements AccountService {

	
	private AccountDao accountDao = null;
	public AccountServiceImpl() {
		accountDao = ObjectFactory.getAccountDaoInstance();
	}
	
	
	@Override
	public Account createAccount(Account account) throws AccountAlreadyExistsException{
		// TODO Auto-generated method stub
		return accountDao.createAccount(account);
	}

	@Override
	public double getBalance(int accountNumber) throws AccountDoesnotExistsException{
		// TODO Auto-generated method stub
            Account a = accountDao.getAccount(accountNumber);
            return a.getBalance();
	}

	@Override
	public void transfer(int sourceAccount, int destincationAccount, double amount) throws InsufficentFundException,AccountDoesnotExistsException{
            accountDao.credit(destincationAccount, amount);
            accountDao.debit(sourceAccount, amount);
	}

	@Override
	public List<Account> getAccountsSortedByName() {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAccount = accounts.stream()
		.sorted((account1, account2) -> account1.getCustomerName().compareTo(account2.getCustomerName()))
		.collect(Collectors.toList());
		return sortedAccount;
	}

	@Override
	public List<Account> getAccountsSortedByAccountNumber() {
		// TODO Auto-generated method stub
                List<Account> list = accountDao.getAccounts().stream().sorted((l1,l2) -> Double.compare(l1.getBalance(), l2.getBalance())).collect(Collectors.toList());
		return list;
	}

    @Override
    public void deleteAccount(int accno) throws AccountDoesnotExistsException {
        accountDao.deleteAccount(accno);
    }

}
