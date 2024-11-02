/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.CineProjectDemo;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author bretechersandric
 */
public class EmployeeDelete extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Button deleteEmp = new Button("OK");

    ComboBox selectId = new ComboBox();
    //private final Label title = new Label("Delete employee");

    public void start() {
        ArrayList<Integer> a = employeeDao.getId();

        for (int i = 0; i < a.size(); i++) {
            selectId.getItems().add(a.get(i));
        }

        getField();
        showPass();

        deleteEmp.setId("bouton");
        cancel.setId("bouton");
        selectId.setPromptText("Choose employee Id");

        //setBox
        getGridPane(box1, 20, 100, 0, 100, 10, 10);

        //getBox
        box1.add(id, 0, 0);
        box1.add(selectId, 1, 0);
        box1.add(name, 0, 1);
        box1.add(name2, 1, 1);
        box1.add(password, 0, 2);
        box1.add(password2, 1, 2);
        box1.add(showpass, 2, 2);
        box1.add(deleteEmp, 1, 3);
        box1.setAlignment(Pos.CENTER);

        //cr�ation d'un cadre, de bords
        BorderPane bord = new BorderPane();
        bord.setCenter(box1); //on poistionne le conteneur "box1" en haut du cadre

        //activate button
        deleteEmp.setOnAction(this);

        //background a revoir car marche pas avec box1
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("employee");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Delete employee");
    }

    @Override
    public void handle(ActionEvent e) {
        if (deleteEmp == e.getSource()) {

            String idSelect = (String) selectId.getSelectionModel().getSelectedItem().toString();
            nEmployee.setEmployeeID(Integer.parseInt(idSelect));
            nEmployee.setName(name2.getText());
            nEmployee.setSurname((password2.getText()));

            employeeDao.deleteEmployee(nEmployee);

            int employee_id = CineProjectDemo.employee_connected.getEmployeeId();
            if (Integer.parseInt(idSelect) == employee_id) {
                //CineProjectDemo.employeeConnected = false;
                stop();
            } else {
                getCloseWindow(e, deleteEmp);
            }

        }
    }

}
