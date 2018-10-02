/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerclient;

import Entity.EmployeeEntity;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import stateless.EmployeeCreatorBeanRemote;


/**
 *
 * @author Joshua
 */
class mainApp {
    @EJB
    private EmployeeCreatorBeanRemote employeebean = lookupEmployeeCreatorBeanRemote();


    public mainApp() {
    }
    
    public void run(){
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
        /*while(employee.logIn(user, pass)==false){
            System.err.println("Please try again");
            System.err.println("Enter Username:");
            user=sc.nextLine();
            System.err.println("Enter password");
            pass=sc.nextLine();
        }
        System.err.println("Welcome" + user+ ".");
        
        boolean finished=false;
        while(finished==false){
            System.err.println("Choose Option");
            System.err.println("1) New Customer");
            System.err.println("2) View Account");
            System.err.println("3) Withdraw");
            System.err.println("4) Deposit");
            if(employee.getAccess()==2){
                System.err.println("5) New Employee");
            }
            System.err.println("0) End");
            
            int choice =sc.nextInt();
            switch(choice){
                case 0:
                    finished=true;
                    break;
                case 1:
                    //enter details
            }
                    
        }*/
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
    
}
