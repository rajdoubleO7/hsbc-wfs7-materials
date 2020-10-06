package com.org.model.dao;

import com.org.Exception.AccountAlreadyExistsException;
import com.org.Exception.AccountDoesnotExistsException;
import com.org.Exception.InsufficentFundException;
import java.util.ArrayList;
import java.util.List;

import com.org.model.beans.Account;
import java.util.NoSuchElementException;

public class CollectionBackedAccountDaoImpl implements AccountDao {

	private static List<Account> database = new ArrayList<>();
	@Override
	public Account createAccount(Account account) throws AccountAlreadyExistsException{
		
            try {
                Account a = getAccount(account.getAccountNumber());
            } catch (AccountDoesnotExistsException ex) {
                database.add(account);
                return account;
            }
            throw new AccountAlreadyExistsException("An Account with this provided Account number Already Exists ");
	}


	@Override
	public Account getAccount(int accountNumber) throws AccountDoesnotExistsException{
		// TODO Auto-generated method stub
                Account a = null;
                try{
                     a = database.stream().filter(item -> item.getAccountNumber() == accountNumber).findFirst().get();
                }catch(NoSuchElementException e){
                    throw new AccountDoesnotExistsException("not account present with account no: "+accountNumber);
                }
		return a;
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return database;
	}

    @Override
    public Account debit(int accountNumber, double amount) throws InsufficentFundException,AccountDoesnotExistsException {
        Account a = getAccount(accountNumber);
        double x= a.getBalance()-amount;
        if(x<0){
            throw new InsufficentFundException("Insufficent Fund in "+a.getAccountNumber()+" account");
        }else{ 
            a.setBalance(x);
        }
        
        return a;
    }

    @Override
    public Account credit(int accountNumber, double amount) throws AccountDoesnotExistsException{
        Account a = getAccount(accountNumber);
        a.setBalance(a.getBalance()+amount);
        return a;
    }

    @Override
    public void deleteAccount(int accNo) throws AccountDoesnotExistsException {
        for(Account acc:database){
            if(acc.getAccountNumber()==accNo){
                database.remove(acc);
                return;
            }
        }
        throw new AccountDoesnotExistsException("account does not exists in the database");
    }

}
