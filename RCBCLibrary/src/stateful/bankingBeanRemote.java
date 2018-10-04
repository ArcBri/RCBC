/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import Entity.Account;


/**
 *
 * @author Joshua
 */
public interface bankingBeanRemote {

    public Long createAccount(Account newAccount);

    public void withdraw(Long id, int amount);

    public void deposit(Long id, int amount);

    public Account findAccount(Long Id);
    
}
