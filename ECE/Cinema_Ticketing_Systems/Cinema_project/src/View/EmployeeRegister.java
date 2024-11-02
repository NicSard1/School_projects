/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import Model.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.geometry.Pos;

/**
 *
 * @author bretechersandric
 */
public class EmployeeRegister extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start() {
        getField();
        showPass();

        getGridPane(box1, 10, 20, 0, 50, 10, 10);
        box1.add(name, 0, 0);
        box1.add(name2, 1, 0);
        box1.add(surname, 0, 1);
        box1.add(surname2, 1, 1);
        box1.add(age, 0, 2);
        box1.add(born, 1, 2);
        box1.add(password, 0, 3);
        box1.add(password2, 1, 3);
        box1.add(showpass, 2, 3);
        box1.add(submit, 1, 4);
        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        submit.setId("bouton");
        submit.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("new");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "New employee");
    }

    @Override
    public void handle(ActionEvent e) {

        if (submit == e.getSource()) {

            //calcul de l'age avec la date entrée par l'utilisateur
            LocalDate naissance = born.getValue();
            LocalDate aujourdhui = LocalDate.now();
            long birth = ChronoUnit.YEARS.between(naissance, aujourdhui);
            int AGE = (int) birth;

            //getText pour pour la classe employee
            nEmployee.setName(name2.getText());
            nEmployee.setSurname((surname2.getText()));
            nEmployee.setAge(AGE);//calculer l'age avec la date
            nEmployee.setPassword((password2.getText()));

            //add employee
            EmployeeDaoImpl addNewEmployee = new EmployeeDaoImpl();
            addNewEmployee.addEmployee(nEmployee);

            //clear text field
            getClearField();
            getCloseWindow(e, submit);
        }
    }

}
