/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;
import javafx.geometry.Pos;
/**
 *
 * @author bretechersandric
 */
public class MemberProfile extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Button homeMana = new Button("CineMovie - Your profile");
    private final Button basket = new Button("Basket");
    private final Button exit = new Button("Quit");
    private final Button movie = new Button("Movies");

    public void start() throws IOException {
        movie.setId("menu");
        exit.setId("menu");
        homeMana.setId("menu");
        basket.setId("menu");
        delete.setId("menu");

        movie.setOnAction(this);
        exit.setOnAction(this);
        homeMana.setOnAction(this);
        basket.setOnAction(this);
        delete.setOnAction(this);

        //Menu Bar
        getMenu();

        HBox left = new HBox(homeMana);
        left.setAlignment(Pos.CENTER_LEFT);

        HBox right = new HBox(movie, basket, delete);
        right.setAlignment(Pos.CENTER_RIGHT);

        HBox centerBot = new HBox(homeBot);
        centerBot.setAlignment(Pos.CENTER);

        HBox rightBot = new HBox(exit);
        rightBot.setAlignment(Pos.CENTER_RIGHT);

        StackPane menuBot = new StackPane();
        menuBot.getChildren().addAll(centerBot, rightBot);
        menuBot.setId("menubar");

        StackPane menuTop = new StackPane();
        menuTop.getChildren().addAll(left, right);
        menuTop.setId("menubar");

        //border pane
        BorderPane window = new BorderPane();
        window.setTop(menuTop);
        window.setBottom(menuBot);

        StackPane root = new StackPane();
        root.getChildren().addAll(window);
        root.setBackground(Background.EMPTY);
        root.setId("back");

        //new scene
        Scene managmentScene = new Scene(root, 968, 368);
        getNewWindow(managmentScene, "");
    }

    @Override
    public void handle(ActionEvent e) {
        if (exit == e.getSource()) {
            getCloseWindow(e, exit);
        }
        if (basket == e.getSource()) {
            EmployeeRegister register = new EmployeeRegister();
            register.start();
        }

        if (delete == e.getSource()) {
            EmployeeDelete del = new EmployeeDelete();
            del.start();
        }

    }

    @Override
    public void stop() {// il faut quitter l'application lorsque la fen�tre est ferm�e
        System.exit(0);
    }

}
