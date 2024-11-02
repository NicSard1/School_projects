/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*; //a modifier peut etre

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/**
 *
 * @author bretechersandric
 */
public class MemberLogin extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start() {
        getField();
        showPass();

        //setBox
        getGridPane(box1, 20, 100, 0, 100, 10, 10);

        //getBox
        box1.add(name, 0, 1);
        box1.add(name2, 1, 1);
        box1.add(password, 0, 2);
        box1.add(password2, 1, 2);
        box1.add(showpass, 2, 2);
        box1.add(signIn, 1, 3);
        box1.setAlignment(Pos.CENTER);

        //cr�ation d'un cadre, de bords
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);

        //activate button
        signIn.setId("bouton");
        signIn.setOnAction(this);

        //background a revoir car marche pas avec box1
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("log");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Member login");

    }

    @Override
    public void handle(ActionEvent e) {
        if (signIn == e.getSource()) {

            if (!memberDao.sign_in(name2.getText(), password2.getText())) {
                System.out.println("Connection failed");
                Label dataMiss = new Label("Missing datas ! ");
                dataMiss.setId("data");
                dataMiss.setAlignment(Pos.CENTER);
                box1.add(dataMiss, 0, 5);

            } else {
                nMember.setName(name2.getText());
                nMember.setPassword(password2.getText());
                System.out.println("Connection succeded");
                getCloseWindow(e, signIn);

            }
        }
    }
}
