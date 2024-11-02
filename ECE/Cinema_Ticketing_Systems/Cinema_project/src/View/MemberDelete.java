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
import controller.CineProjectDemo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author bretechersandric
 */
public class MemberDelete extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start() {
        delete.setText("Delete my account");

        getField();
        showPass();

        delete.setId("bouton");
        cancel.setId("bouton");
        name.setId("label");
        password.setId("label");
        email.setId("label");

        //setBox
        getGridPane(box1, 20, 100, 0, 100, 10, 10);

        //getBox
        box1.add(email, 0, 0);
        box1.add(email2, 1, 0);
        box1.add(name, 0, 1);
        box1.add(name2, 1, 1);
        box1.add(password, 0, 2);
        box1.add(password2, 1, 2);
        box1.add(showpass, 2, 2);
        box1.add(delete, 1, 3);
        box1.add(cancel, 0, 3);
        box1.setAlignment(Pos.CENTER);

        //cr�ation d'un cadre, de bords
        BorderPane bord = new BorderPane();
        bord.setCenter(box1); //on poistionne le conteneur "box1" en haut du cadre

        //activate button
        delete.setOnAction(this);
        cancel.setOnAction(this);

        //background a revoir car marche pas avec box1
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("delt");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Delete employee");

    }

    @Override
    public void handle(ActionEvent e) {

        if (delete == e.getSource()) {
            nMember.setEmail(email2.getText());
            nMember.setName(name2.getText());
            nMember.setPassword(password2.getText());

            if (!employeeDao.connecEmployee(nEmployee)) {
                System.out.println("Connection failed");
                Label dataMiss = new Label("Missing datas ! ");
                dataMiss.setId("data");
                dataMiss.setAlignment(Pos.CENTER);
                box1.add(dataMiss, 0, 5);
            } else {
                CineProjectDemo.memberConnected = false;
                memberDao.deleteMember(CineProjectDemo.member_connected);
                //delete employee
                MemberDaoImpl delEmpl = new MemberDaoImpl();
                delEmpl.deleteMember(nMember);
                
                getCloseWindow(e, delete);
            }

        }
        if (cancel == e.getSource()) {
            //clear text field
            getClearField();
            getCloseWindow(e, delete);
        }
    }

}
