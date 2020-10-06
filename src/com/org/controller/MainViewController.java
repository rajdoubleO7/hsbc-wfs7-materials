package com.org.controller;

import com.org.Exception.AccountAlreadyExistsException;
import com.org.Exception.AccountDoesnotExistsException;
import com.org.Exception.InsufficentFundException;
import java.util.List;
import java.util.Scanner;

import com.org.model.beans.Account;
import com.org.model.service.AccountService;
import com.org.model.util.ObjectFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainViewController {

	public static void main(String[] args) {
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		AccountService service = ObjectFactory.getAccountServiceInstance();
		do {
			System.out.println("1: Create Account 2: Check Balance");
			System.out.println("3: Transfer Amount 4: Sort Accounts by name 5: Sort Accounts by account number 6: To delete an account 0: Exit");
			option = scanner.nextInt();
			List<Account> list = null;
			switch(option) {
			case 1: 
				System.out.println("Enter name");
				Account account = new Account(scanner.next());
				Account createdAccount = null;
                        try {
                            createdAccount = service.createAccount(account);
                        } catch (AccountAlreadyExistsException ex) {
                            System.out.println(ex.getMessage());
                        }
				System.out.println(createdAccount);
				break;
			case 2: 
                            System.out.println("Enter the Source Account number: ");
                            int source = scanner.nextInt();
                            try {
                                System.out.println("Your Balance is: "+service.getBalance(source));
                            } catch (AccountDoesnotExistsException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;  
			case 3:
                            System.out.println("Enter the Source Account number: ");
                            source = scanner.nextInt();
                            System.out.println("Enter the Source Account number: ");
                            int dest = scanner.nextInt();
                            
                            System.out.println("Enter the amount value: ");
                            double amount = scanner.nextInt();
                            try {
                                service.transfer(source, dest, amount);
                            } catch (InsufficentFundException | AccountDoesnotExistsException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;  
			
			case 4:     
				list = service.getAccountsSortedByName(); // HttpSession -> setAttribute("key", list) -> ${ }
				list.forEach(acc -> System.out.println(acc));
				break;
			case 5:
                                service.getAccountsSortedByAccountNumber();
				break;
                        case 6:
                                System.out.println("Enter the Account number: ");
                                int accountNo = scanner.nextInt();
                                try {
                                    service.deleteAccount(accountNo);
                                } catch (AccountDoesnotExistsException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
			}
		} while(option != 0);
		
		scanner.close();
	}

}

