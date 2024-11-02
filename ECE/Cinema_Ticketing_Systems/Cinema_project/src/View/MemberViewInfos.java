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
public class MemberViewInfos extends CinemaInterface implements EventHandler<ActionEvent> {
    
    
    public void start() {
        getField();
        showPass();
        
        Label id_infos = new Label(String.valueOf(CineProjectDemo.member_connected.getId()));
        Label name_infos = new Label(CineProjectDemo.member_connected.getName());
        Label surname_infos = new Label(CineProjectDemo.member_connected.getSurname());
        Label age_infos = new Label(String.valueOf(CineProjectDemo.member_connected.getAge()));
        Label email_infos = new Label(CineProjectDemo.member_connected.getEmail());
        Label password_infos = new Label(CineProjectDemo.member_connected.get_password());
        Label payementInfos = new Label(String.valueOf(CineProjectDemo.member_connected.get_payment_infos()));
        Label loyalty_points_infos = new Label(String.valueOf(CineProjectDemo.member_connected.get_loyalty_points()));
        Label discount_infos = new Label(String.valueOf(CineProjectDemo.member_connected.get_discount()));

        getGridPane(box1, 10, 20, 0, 50, 5, 5);
        box1.add(id_customer, 0, 0);
        box1.add(id_infos, 1, 0);
        box1.add(name, 0, 1);
        box1.add(name_infos, 1, 1);
        box1.add(surname, 0, 2);
        box1.add(surname_infos, 1, 2);
        box1.add(age, 0, 3);
        box1.add(age_infos, 1, 3);
        box1.add(email, 0, 4);
        box1.add(email_infos, 1, 4);
        box1.add(password, 0, 5);
        box1.add(password_infos, 1, 5);
        box1.add(payment, 0, 6);
        box1.add(payementInfos, 1, 6);
        box1.add(loyalty_points, 0, 7);
        box1.add(loyalty_points_infos, 1, 7);
        box1.add(discount, 0, 8);
        box1.add(discount_infos, 1, 8); 
        //box1.add(quit, 0, 9);
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
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("info");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "My information");
    }

    @Override
    public void handle(ActionEvent e) {

        if (update_infos == e.getSource()) {
            MemberUpdateInfos nMemberUpdateInfos = new MemberUpdateInfos();
            nMemberUpdateInfos.start();
            
            //clear text field
            getClearField();
            getCloseWindow(e, update_infos);
        }
        
        if(quit == e.getSource()){
            getCloseWindow(e, quit);
        }
    }

}
