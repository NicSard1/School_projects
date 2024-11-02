/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import controller.CineProjectDemo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.geometry.Pos;

/**
 *
 * @author dckt2
 */
public class EmployeeUpdateInfos extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start() {
        getField();
        showPass();
        name2.setPromptText(CineProjectDemo.employee_connected.getName());
        surname2.setPromptText(CineProjectDemo.employee_connected.getSurname());
        //born.setPromptText(Integer.parseInt(CineProjectDemo.employee_connected.getAge()));
        password2.setPromptText(CineProjectDemo.employee_connected.getPassword());

        getGridPane(box1, 10, 20, 0, 50, 5, 5);
        box1.add(name, 0, 0);
        box1.add(name2, 1, 0);
        box1.add(surname, 0, 1);
        box1.add(surname2, 1, 1);
        box1.add(age, 0, 2);
        box1.add(born, 1, 2);
        box1.add(password, 0, 3);
        box1.add(password2, 1, 3);
        box1.add(showpass, 2, 3);
        box1.add(update_infos, 1, 5);
        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        update_infos.setId("bouton");
        update_infos.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(bord);
        secondaryLayout.setId("update");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Update informations");
    }

    @Override
    public void handle(ActionEvent e) {

        if (update_infos == e.getSource()) {
            int AGE = 0;
            if ((name2.getText().compareTo("") == 0)) {
                name2.setText(CineProjectDemo.employee_connected.getName());
                nEmployee.setName(name2.getText());
            } else {
                nEmployee.setPassword((name2.getText()));
            }
            if ((surname2.getText().compareTo("") == 0)) {
                surname2.setText(CineProjectDemo.employee_connected.getSurname());
                nEmployee.setSurname((surname2.getText()));
            } else {
                nEmployee.setPassword((surname2.getText()));
            }
            if ((password2.getText().compareTo("") == 0)) {
                password2.setText(CineProjectDemo.employee_connected.getPassword());
                nEmployee.setPassword((password2.getText()));
            } else {
                nEmployee.setPassword((password2.getText()));
            }
            if ((AGE == 0)) {
                AGE = CineProjectDemo.employee_connected.getAge();
            } else {
                LocalDate naissance = born.getValue();
                LocalDate aujourdhui = LocalDate.now();
                long birth = ChronoUnit.YEARS.between(naissance, aujourdhui);
                AGE = (int) birth;
            }

            nEmployee.setAge(AGE);
            nEmployee.setEmployeeID(CineProjectDemo.employee_connected.getEmployeeId());
            //update infos
            employeeDao.updateEmployee(nEmployee);

            //clear text field
            getClearField();
            getCloseWindow(e, update_infos);

        }

    }

}
