/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author bretechersandric
 */
public class MemberRegister extends CinemaInterface implements EventHandler<ActionEvent> {

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
        box1.add(newMember, 1, 6);
        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        newMember.setId("bouton");
        newMember.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(bord);
        secondaryLayout.setId("new");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "New member");
    }

    @Override
    public void handle(ActionEvent e) {

        if (newMember == e.getSource()) {
            if ((name2.getText().compareTo("") == 0) || (surname2.getText().compareTo("") == 0) || (born.getValue().toString().compareTo("") == 0) || (email2.getText().compareTo("") == 0)
                    || (password2.getText().compareTo("") == 0)) {
                Label dataMiss = new Label("Missing datas ! ");
                dataMiss.setId("data");
                dataMiss.setAlignment(Pos.CENTER);
                box1.add(dataMiss, 0, 5);

            } else {
                //calcul de l'age avec la date entrée par l'utilisateur
                LocalDate naissance = born.getValue();
                LocalDate aujourdhui = LocalDate.now();
                long birth = ChronoUnit.YEARS.between(naissance, aujourdhui);
                int AGE = (int) birth;

                nMember.setName((name2.getText()));
                nMember.setSurname((surname2.getText()));
                nMember.setEmail((email2.getText()));
                nMember.setAge(AGE);
                nMember.setPassword((password2.getText()));
                nMember.set_payment_infos(Integer.parseInt(payment_infos2.getText()));

                //add employee
                memberDao.addMember(nMember);
                memberDao.sign_in(name2.getText(), password2.getText());

                //clear text field
                getClearField();
                getCloseWindow(e, newMember);
            }
        }
    }

}
