/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerclient;

import Entity.Account;
import Entity.EmployeeEntity;
import static java.lang.Long.reverse;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import stateful.bankingBeanRemote;
import stateless.EmployeeCreatorBeanRemote;


/**
 *
 * @author Joshua
 */
class mainApp {
    @EJB
    private final EmployeeCreatorBeanRemote employeebean = lookupEmployeeCreatorBeanRemote();
    
    @EJB
    private bankingBeanRemote bank = lookupbankingBeanRemote();
    
    Long accountId = new Long(99999);
    Account account;
    Long prevacctId= new Long(997);


    public mainApp() {
    }
    
    public void run() throws Exception{
        Scanner sc = new Scanner(System.in);       
        List<EmployeeEntity> employeeEntities = employeebean.retrieveAllEmployeeEntity();  
        
       /* for(EmployeeEntity employeeEntity:employeeEntities){
            System.out.println(employeeEntity.getFirstName()+" "+ employeeEntity.getLastName());
        }*/
        
        System.out.println("Welcome to RCBC");

        
        System.out.println("Enter Username:");
        String user=sc.nextLine();
        System.out.println("Enter password");
        String pass=sc.nextLine();
        EmployeeEntity currentUser=employeebean.staffLogin(user, pass);
        employeeEntities.add(currentUser);

        System.err.println("Welcome" + user+ ".");
        
        boolean finished=false;
        while(finished==false){
            System.err.println("Choose Option");
            System.err.println("1) New Customer");
            System.err.println("2) View Available Account Balance");
            System.err.println("3) Withdraw");
            System.err.println("4) Deposit");
            if(currentUser.getAccess()==1){
                System.err.println("5) Create new Employee");
            }
            System.err.println("0) End");
            
            int choice =sc.nextInt();
            switch(choice){
                case 0:
                    finished=true;
                    break;
                case 1:
                    System.out.println("Enter Name");
                    String name=sc.next();
                    sc.nextLine();
                    System.out.println("Loading");
                    System.out.println("Enter initial deposit");
                    int balance=sc.nextInt();
                    System.out.println("Enter account PIN");
                    int pin=sc.nextInt();
                    account = new Account(name,balance,pin);
                    accountId=bank.createAccount(account);
                    System.out.println("success");
                    break;
                case 2:
                    if(accountId.equals(prevacctId)){
                       System.out.println("Change account? Enter 0 for yes, 1 for no ");                    
                        if(sc.nextInt()==0){
                        prevacctId=reverse(prevacctId);
                        System.out.print("Enter holder's name:");
                    //String namae=sc.nextLine();
                    account=bank.retrieveAccountbyName(sc.next());
                     }else{
                            account=bank.findAccount(accountId);
                        }
                    }else{
                    System.out.print("Enter holder's name:");
                    //String namae=sc.nextLine();
                    account=bank.retrieveAccountbyName(sc.next());
                    }
                    accountId=account.getAccountId();
                    prevacctId=accountId;
                    System.out.println("Remaining balance is: $"+account.getBalance()) ;
                    break;
                case 3:
                    System.out.println("Enter Withdraw Amount");
                    int amount=sc.nextInt();
                    bank.withdraw(accountId, amount);
                    System.out.println("Success");
                    break;
                case 4:
                    System.out.println("Enter Deposit Amount");
                    int amt=sc.nextInt();
                    bank.deposit(accountId, amt);
                    System.out.println("Success");
                    break;
                case 5:
                    System.out.println("Enter first name");
                    String firstName=sc.next();
                    sc.nextLine();
                    System.out.println("Enter last name");
                    String lastName=sc.nextLine();
                    System.out.println("Enter username");
                    String userName=sc.nextLine();
                    System.out.println("Enter password");
                    String password=sc.nextLine();
                    System.out.println("Enter 1 for manager access, 0 otherwise");
                    int access=sc.nextInt();
                    EmployeeEntity newUser= new EmployeeEntity(firstName,lastName,userName,password,access);
                    employeebean.createEmployee(newUser);
                    break;
                    //enter details
            }
                    
        }
    }

    private EmployeeCreatorBeanRemote lookupEmployeeCreatorBeanRemote() {
        try {
            Context c = new InitialContext();
            return (EmployeeCreatorBeanRemote) c.lookup("java:comp/env/EmployeeCreatorBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private bankingBeanRemote lookupbankingBeanRemote() {
        try {
            Context c = new InitialContext();
            return (bankingBeanRemote) c.lookup("java:comp/env/bankingBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
