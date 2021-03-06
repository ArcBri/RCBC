/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import Entity.EmployeeEntity;
import java.util.List;


public interface EmployeeCreatorBeanRemote {

    public List<EmployeeEntity> retrieveAllEmployeeEntity();

    public EmployeeEntity retrieveStaffByUsername(String username) ;

    public EmployeeEntity staffLogin(String username, String password);

    public Long createEmployee(EmployeeEntity newEmployeeEntity);
    
}
