/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.*;
import View.*;

/**
 *
 * @author maithili
 */


public class CineProjectDemo extends CinemaInterface {

    static public boolean memberConnected = false; //true : someone is memberConnected
    //static public boolean employeeConnected = false; //true : someone is employeeConnected
    static public Member member_connected; //data of member memberConnected
    static public Employee employee_connected; //data of employee
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CinemaInterface.launch(args);      
    }

}
