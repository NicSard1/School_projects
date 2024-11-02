/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maithili
 */
public interface EmployeeDao {

    public List<Employee> getAllEmployees();

    public void addEmployee(Employee employee);

    public Employee getEmployee(int employeeId);

    public void updateEmployee(Employee employee);
    
    public boolean connecEmployee(Employee employee);

    public void deleteEmployee(Employee employee);
    
    public ArrayList<Integer> getId();
}
