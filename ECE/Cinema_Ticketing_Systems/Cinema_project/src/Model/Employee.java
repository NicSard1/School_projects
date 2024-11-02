/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author maithili
 */
public class Employee {

    private int employeeId;
    private String Name;
    private int Age;
    private String Surname;
    private String Password;

    public void setEmployeeID(int eID) {
        employeeId = eID;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setName(String eName) {
        Name = eName;
    }

    public String getName() {
        return Name;
    }

    public void setAge(int eAge) {
        Age = eAge;
    }

    public int getAge() {
        return Age;
    }

    public void setSurname(String surName) {
        Surname = surName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setPassword(String ePass) {
        Password = ePass;
    }

    public String getPassword() {
        return Password;
    }

    void addEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
