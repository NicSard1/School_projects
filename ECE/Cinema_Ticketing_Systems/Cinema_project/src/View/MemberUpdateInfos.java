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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.geometry.Pos;

/**
 *
 * @author dckt2
 */
public class MemberUpdateInfos extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start() {
        getField();
        showPass();

        getGridPane(box1, 10, 20, 0, 50, 5, 5);
        box1.add(name, 0, 0);
        box1.add(name2, 1, 0);
        box1.add(surname, 0, 1);
        box1.add(surname2, 1, 1);
        box1.add(age, 0, 2);
        box1.add(born, 1, 2);
        box1.add(email, 0, 3);
        box1.add(email2, 1, 3);
        box1.add(password, 0, 4);
        box1.add(password2, 1, 4);
        box1.add(showpass, 2, 4);
        box1.add(payment, 0, 5);
        box1.add(payment_infos2, 1, 5);
        box1.add(update_infos, 1, 6);
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
        getNewWindow(addScene, "Update information");
    }

    @Override
    public void handle(ActionEvent e) {

        if (update_infos == e.getSource()) {
            int AGE = 0;
            Member newMemb = new Member();
            //calcul de l'age avec la date entrée par l'utilisateur
            if ((name2.getText().compareTo("") == 0)) {
                name2.setText(CineProjectDemo.member_connected.getName());
                newMemb.setName(name2.getText());
            } else {
                newMemb.setPassword((name2.getText()));
            }
            if ((surname2.getText().compareTo("") == 0)) {
                surname2.setText(CineProjectDemo.member_connected.getSurname());
                newMemb.setSurname((surname2.getText()));
            } else {
                newMemb.setPassword((surname2.getText()));
            }
            if ((email2.getText().compareTo("") == 0)) {
                email2.setText(CineProjectDemo.member_connected.getEmail());
                newMemb.setEmail((email2.getText()));
            } else {
                newMemb.setEmail((email2.getText()));
            }
            if ((password2.getText().compareTo("") == 0)) {
                password2.setText(CineProjectDemo.member_connected.get_password());
                newMemb.setPassword((password2.getText()));
            } else {
                newMemb.setPassword((password2.getText()));
            }
            if ((AGE == 0)) {
                AGE = CineProjectDemo.member_connected.getAge();
            } else {
                LocalDate naissance = born.getValue();
                LocalDate aujourdhui = LocalDate.now();
                long birth = ChronoUnit.YEARS.between(naissance, aujourdhui);
                AGE = (int) birth;
            }
            if ((payment_infos2.getText().compareTo("") == 0)) {
                payment_infos2.setText(Integer.toString(CineProjectDemo.member_connected.get_payment_infos()));
                newMemb.set_payment_infos(Integer.parseInt(payment_infos2.getText()));
            } else {
                newMemb.set_payment_infos(Integer.parseInt(payment_infos2.getText()));
            }
            newMemb.setAge(AGE);
            newMemb.setId(CineProjectDemo.member_connected.getId());

            //add customer
            memberDao.updateMember(newMemb);

            //clear text field
            getClearField();
            getCloseWindow(e, update_infos);

        }

    }

}
