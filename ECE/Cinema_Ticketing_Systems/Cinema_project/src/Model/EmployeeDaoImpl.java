/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.CineProjectDemo;
import java.sql.*;
import java.util.*;

/**
 *
 * @author maithili
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private String url = "jdbc:mysql://localhost:3306/Cinema";
    private String user = "root";
    private String password = "root";
    private final Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO `employee`(`Name`, `Surname`, `Age`, `idEmployee`, `Password`) VALUES ('"
                + employee.getName() + "','" + employee.getSurname() + "'," + employee.getAge() + ","
                + employee.getEmployeeId() + ",'" + employee.getPassword() + "')";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into Employee table for  Employee : " + employee.getSurname() + " " + employee.getName());

        } catch (SQLException e) {

            System.out.println(sql);

            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> listEmployee = new ArrayList<>();
        String sql = "SELECT * FROM `employee`";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            //statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
            Employee infoEmployee = new Employee();
            infoEmployee.setName(rs.getString("Name"));
            infoEmployee.setSurname(rs.getString("Surname"));
            infoEmployee.setAge(rs.getInt("Age"));
            infoEmployee.setEmployeeID(rs.getInt("idEmployee"));
            listEmployee.add(infoEmployee);
            }
            listEmployee.forEach((test1) -> {
                System.out.println("Name : " + test1.getName() + ", Surname : "
                        + test1.getSurname() + ", Age : " + test1.getAge()
                        + ", Id : " + test1.getEmployeeId());
            });
            return listEmployee;

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return listEmployee;
    }

    @Override
    public Employee getEmployee(int employeeId) {
        String name;
        String pass;
        String sql = "SELECT `Name`, `Password` FROM `employee` WHERE `idEmployee`=" + employeeId;
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            name = rs.getString(1);
            pass = rs.getString(2);
            System.out.print(name + " " + pass);

        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee "
                + "SET Surname = '" + employee.getSurname()
                + "', Name = '" + employee.getName()
                + "', Age = " + employee.getAge()
                + ", password = '" + employee.getPassword()
                + "' WHERE idEmployee = " + employee.getEmployeeId();

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);
            System.out.println("Record to update Customer : " + employee.getSurname() + " has been made");

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM `employee` WHERE `Name`='" + employee.getName() + "' AND `Password`='" + employee.getPassword() + "'AND`idEmployee`=" + employee.getEmployeeId();

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The employee " + employee.getName() + " has been deleted");

        } catch (SQLException e) {

            System.out.println(sql);

            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public boolean connecEmployee(Employee employee) {
        boolean connection_succes = true;

        String sql = "SELECT * FROM `employee` where Name = '" + employee.getName() + "' "
                + "AND password = '" + employee.getPassword() + "'";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request verify if a connection is possible has been made");

            try (ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    //System.out.println("Connection with name = " + result.getString(1) + " and password = " + result.getString(7)) ;
                    //CineProjectDemo.employeeConnected = true;
                    CineProjectDemo.employee_connected = new Employee();
                    CineProjectDemo.employee_connected.setName(rs.getString(1));
                    CineProjectDemo.employee_connected.setSurname(rs.getString(2));
                    CineProjectDemo.employee_connected.setAge(Integer.parseInt(rs.getString(3)));
                    CineProjectDemo.employee_connected.setEmployeeID(Integer.parseInt(rs.getString(4)));
                    System.out.println("\nCineProjectDemo.employeeConnect.getId = " + CineProjectDemo.employee_connected.getEmployeeId());
                    CineProjectDemo.employee_connected.setPassword(rs.getString(5));
                } else {
                    connection_succes = false;
                }
                rs.close();
            }

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return connection_succes;
    }

    /* a revoir*/
    @Override
    public ArrayList<Integer> getId() {
        //String sql = ("SELECT `idEmployee` FROM `Employee` WHERE `Name`='" + employee.getName() + "'AND`Password`='" + employee.getPassword() + "'");
        String sql = "SELECT `idEmployee` FROM `employee`";

        ArrayList<Integer> table = new ArrayList<>();
        try {
            //System.out.println(sql);

            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                table.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return table;
    }

}
