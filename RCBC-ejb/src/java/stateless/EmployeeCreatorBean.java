/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import Entity.EmployeeEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Joshua
 */
@Stateless
@Local(EmployeeCreatorBeanLocal.class)
@Remote(EmployeeCreatorBeanRemote.class)
public class EmployeeCreatorBean implements EmployeeCreatorBeanRemote, EmployeeCreatorBeanLocal { // i lazy make another one for retrieve so together same

    @PersistenceContext(unitName = "RCBC-ejbPU")
    private EntityManager em;

    
    @Override
    public Long createEmployeeEntity(EmployeeEntity newEmployeeEntity){
        em.persist(newEmployeeEntity);
        em.flush(); //for identity primary key must flush before exit method
        
        return newEmployeeEntity.getEmployeeId(); //ctrl space auto complete
    }
    
    @Override
    public List<EmployeeEntity> retrieveAllEmployeeEntity(){
        Query query = em.createQuery("SELECT ee FROM EmployeeEntity ee");
        return query.getResultList();
    }
    @Override
    public EmployeeEntity retrieveStaffByUsername(String username)
    {
        Query query = em.createQuery("SELECT e FROM EmployeeEntity e WHERE e.username = :inUsername");
        query.setParameter("inUsername", username);
        
        {
            return (EmployeeEntity)query.getSingleResult();
        }


    }
    @Override
    public EmployeeEntity staffLogin(String username, String password)
    {
       
        {
            EmployeeEntity staffEntity = retrieveStaffByUsername(username);
            
            if(staffEntity.getPassword().equals(password))
            {                
                return staffEntity;
            }

        }
    }
        @Override
    public Long createEmployee(EmployeeEntity newEmployeeEntity){
        em.persist(newEmployeeEntity);
        em.flush(); //for identity primary key must flush before exit method
        
        return newEmployeeEntity.getEmployeeId(); //ctrl space auto complete
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
