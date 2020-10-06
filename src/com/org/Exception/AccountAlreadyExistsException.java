/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Exception;

/**
 *
 * @author surya
 */
public class AccountAlreadyExistsException extends Exception{
    public AccountAlreadyExistsException(String message){
        super(message);
    }
}
