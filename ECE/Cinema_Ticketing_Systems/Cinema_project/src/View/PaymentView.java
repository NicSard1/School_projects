/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.*;
import controller.CineProjectDemo;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author dckt2
 */
public class PaymentView extends CinemaInterface implements EventHandler<ActionEvent> {

    int cmovie_id;
    int cjunior_choice;
    int cadult_choice;
    int csenior_choice;
    

    //case where someone want to book a movie directly after filtered movies
    //with criteria. select the movie chosen automatiqualy
    public void start(int movie_id,int junior_choice,int adult_choice,int senior_choice) {
 
        cmovie_id = movie_id;
        cjunior_choice = junior_choice;
        cadult_choice = adult_choice;
        csenior_choice = senior_choice;
        
        getField();
        showPass();

        getGridPane(box1, 10, 20, 0, 50, 5, 5);


        //activate button
        cancel.setId("bouton");
        cancel.setOnAction(this);
        cancel.setAlignment(Pos.BOTTOM_CENTER);
        box1.add(cancel, 1, 6);

        box1.add(payment, 0, 3);
        box1.add(payment_infos2, 1, 3);
        box1.add(pay_now, 0, 6);
        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        pay_now.setId("bouton");
        pay_now.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("book");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Payment step");
    }

    
    
    @Override
    public void handle(ActionEvent e) {

        if (pay_now == e.getSource()) {
            //verify payment infos
            MemberDaoImpl nMemberDaoImpl = new MemberDaoImpl();
            boolean payment_success = nMemberDaoImpl.payment(Integer.parseInt(payment_infos2.getText()));   
     
            if(payment_success){
                
                PurchaseDaoImpl nPurchaseDaoImpl = new PurchaseDaoImpl();
                if(CineProjectDemo.memberConnected){
                    nPurchaseDaoImpl = new PurchaseDaoImpl();
                    nPurchaseDaoImpl.buyPurchase();
                    CineProjectDemo.member_connected.clear_basket();
                    
                }else{
                    nPurchaseDaoImpl.createPurchase2(cmovie_id, cjunior_choice, cadult_choice, csenior_choice);
                }
                //clear text field
                getClearField();
                getCloseWindow(e, pay_now);
            }
        }
        
        
        if(cancel == e.getSource()){
            
            getCloseWindow(e, cancel);
        }
    }
    
    public void alert_window(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information message...");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
