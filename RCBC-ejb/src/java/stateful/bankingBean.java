/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateful;

import Entity.Account;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Joshua
 */
@Stateful
@Remote(bankingBeanRemote.class)
public class bankingBean implements bankingBeanRemote {

    @PersistenceContext(unitName = "RCBC-ejbPU")
    private EntityManager em;

    @Override
    public Long createAccount(Account newAccount){
        em.persist(newAccount);
        em.flush(); //for identity primary key must flush before exit method
        
        return newAccount.getAccountId(); //ctrl space auto complete
    }
    @Override
    public Account findAccount(Long Id){
       Account account= em.find(Account.class, Id);
        return account;
    }
    public void updateAccount(Account account){
        em.merge(account);
       // em.flush();
    }
    @Override
    public void withdraw(Long id, int amount){
        Account account=em.find(Account.class, id);
        int balance =account.getBalance();
        balance=balance-amount;
        account.setBalance(balance);
        updateAccount(account);
    }
    @Override
        public Account retrieveAccountbyName(String name)
    {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.name = :inName");
        query.setParameter("inName", name);
        
        {
            return (Account)query.getSingleResult();
        }


    }
    @Override
    public void deposit(Long id, int amount){
        Account account=em.find(Account.class, id);
        int balance =account.getBalance();
        balance=balance+amount;
        account.setBalance(balance);
        updateAccount(account);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
