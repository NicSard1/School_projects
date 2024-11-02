/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dckt2
 */
public abstract class Customer {
    
    private int id;
    private String name;
    private int age;
    private String surname;

    public void setId(int new_id) {
        id = new_id;
    }

    public int getId() {
        return id;
    }

    public void setName(String cName) {
        name = cName;
    }

    public String getName() {
        return name;
    }

    public void setAge(int cAge) {
        age = cAge;
    }

    public int getAge() {
        return age;
    }

    public void setSurname(String cSurname) {
        surname = cSurname;
    }

    public String getSurname() {
        return surname;
    } 
}
