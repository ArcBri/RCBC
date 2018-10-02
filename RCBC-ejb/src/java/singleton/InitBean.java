/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import Entity.EmployeeEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import stateless.EmployeeCreatorBeanLocal;

/**
 *
 * @author Joshua
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

    @EJB
    private EmployeeCreatorBeanLocal employeeCreatorBeanLocal;

    @PostConstruct
    public void postConstruct(){
        employeeCreatorBeanLocal.createEmployeeEntity(new EmployeeEntity("Brian", "Aquilas", "manager", "password", 1));
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
