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
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author dckt2
 */
public class EmployeeInfos extends CinemaInterface implements EventHandler<ActionEvent> {
    
    public void start() {
        getField();
        showPass();
        
        Label id_infos = new Label(String.valueOf(CineProjectDemo.employee_connected.getEmployeeId()));
        Label name_infos = new Label(CineProjectDemo.employee_connected.getName());
        Label surname_infos = new Label(CineProjectDemo.employee_connected.getSurname());
        Label age_infos = new Label(String.valueOf(CineProjectDemo.employee_connected.getAge()));
        Label password_infos = new Label(CineProjectDemo.employee_connected.getPassword());
        
        getGridPane(box1, 10, 20, 0, 50, 5, 5);
        box1.add(id, 0, 0);
        box1.add(id_infos, 1, 0);
        box1.add(name, 0, 1);
        box1.add(name_infos, 1, 1);
        box1.add(surname, 0, 2);
        box1.add(surname_infos, 1, 2);
        box1.add(age, 0, 3);
        box1.add(age_infos, 1, 3);
        box1.add(password, 0, 5);
        box1.add(password_infos, 1, 5);
        box1.add(update_infos, 1, 9);
        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        quit.setId("bouton");
        quit.setOnAction(this);
        update_infos.setId("bouton");
        update_infos.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(bord);
        secondaryLayout.setId("info");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "My information");
    }

    @Override
    public void handle(ActionEvent e) {

        if (update_infos == e.getSource()) {
            EmployeeUpdateInfos employeeUpdate = new EmployeeUpdateInfos();
            employeeUpdate.start();
            
            //clear text field
            getClearField();
            getCloseWindow(e, update_infos);
        }
        
        if(quit == e.getSource()){
            getCloseWindow(e, quit);
        }
    }

}
