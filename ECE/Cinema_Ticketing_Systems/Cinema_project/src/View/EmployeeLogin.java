/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;

/**
 *
 * @author bretechersandric
 */
public class EmployeeLogin extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Button login = new Button("Sign in");

    public void start() {
        getField();
        showPass();
        login.setId("bouton");

        //setBox
        getGridPane(box1, 20, 100, 0, 100, 10, 10);

        //getBox
        box1.add(name, 0, 1);
        box1.add(name2, 1, 1);
        box1.add(password, 0, 2);
        box1.add(password2, 1, 2);
        box1.add(showpass, 2, 2);
        box1.add(login, 1, 3);
        box1.setAlignment(Pos.CENTER);

        //cr�ation d'un cadre, de bords
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);

        //activate button
        login.setOnAction(this);

        //background a revoir car marche pas avec box1
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("employee");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Employee login");

    }

    @Override
    public void handle(ActionEvent e) {
        if (login == e.getSource()) {

            nEmployee.setName(name2.getText());
            nEmployee.setPassword(password2.getText());

            if (!employeeDao.connecEmployee(nEmployee)) {
                System.out.println("Connection failed");
                Label dataMiss = new Label("Missing datas ! ");
                dataMiss.setId("data");
                dataMiss.setAlignment(Pos.CENTER);
                box1.add(dataMiss, 0, 5);
            } else {
                System.out.println("Connection succeded");
                EmployeeManagment manageCine = new EmployeeManagment();
                try {
                    manageCine.start();
                    getCloseWindow(e, login);

                } catch (IOException ex) {
                    System.err.print("Id or name or password is incorect ! ");
                    Logger.getLogger(EmployeeLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
