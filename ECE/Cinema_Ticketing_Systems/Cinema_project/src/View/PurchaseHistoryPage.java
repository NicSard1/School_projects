/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 *
 * @author dckt2
 */
public class PurchaseHistoryPage extends CinemaInterface implements EventHandler<ActionEvent> {

    public void start(String purchase_history) {
        getField();
        showPass();
        
        //activate button
        cancel.setId("bouton");
        cancel.setOnAction(this);
        cancel.setAlignment(Pos.CENTER);

        Text str_hidtory = new Text(purchase_history);
        //str_hidtory.setBackground(Background.EMPTY);
        ListView history = new ListView();
        history.getItems().add(str_hidtory);
        history.setBackground(Background.EMPTY);

        HBox hist = new HBox(history);
        hist.setPrefHeight(250);
        str_hidtory.wrappingWidthProperty().bind(hist.widthProperty());
        
        VBox box =new VBox(hist,cancel);
        box.setTranslateY(40);
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        
        HBox histo = new HBox(box);
        histo.setAlignment(Pos.CENTER);
        
        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(histo);
        

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        //secondaryLayout.setBackground(Background.EMPTY);
        secondaryLayout.setId("histo");
        
        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Purchase history");
    }

    @Override
    public void handle(ActionEvent e) {

        if (cancel == e.getSource()) {
            getCloseWindow(e, cancel);
        }
    }

}
