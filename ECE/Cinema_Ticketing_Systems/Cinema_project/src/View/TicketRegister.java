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
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 *
 * @author dckt2
 */
public class TicketRegister extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Text book_title = new Text("Book");

    //standard booking
    //no movie already selected
    public void start() {
        getField();
        showPass();

        book_title.setId("#titlelabel");
        HBox title = new HBox(book_title);
        title.setAlignment(Pos.CENTER);

        getGridPane(box1, 10, 20, 0, 50, 5, 5);

        box1.add(movie_name, 0, 0);
        box1.add(movie_show, 0, 2);

        MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
        ArrayList<String> all_movie_names = nMovieDaoImpl.get_all_movies_name();
        for (int i = 0; i < all_movie_names.size(); ++i) {
            movie_names.getItems().addAll(all_movie_names.get(i));
        }
        movie_names.setOnAction(this);
        box1.add(movie_names, 1, 0);
        box1.add(movie_shows, 1, 2);

        box1.add(junior_places, 0, 3);
        junior_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        junior_places_choice.setValue("0");
        box1.add(junior_places_choice, 1, 3);

        box1.add(adult_places, 0, 4);
        adult_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        adult_places_choice.setValue("0");
        box1.add(adult_places_choice, 1, 4);

        box1.add(senior_places, 0, 5);
        senior_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        senior_places_choice.setValue("0");
        box1.add(senior_places_choice, 1, 5);

        if (CineProjectDemo.memberConnected) {
            box1.add(add_to_basket, 0, 6);
        } else {
            box1.add(book, 0, 6);
        }

        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setTop(title);
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        add_to_basket.setId("bouton");
        add_to_basket.setOnAction(this);
        book.setId("bouton");
        book.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("book");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Book a ticket");
    }

    //case where someone want to book a movie directly after filtered movies
    //with criteria. select the movie chosen automatiqualy
    public void start(String movieName) {
        //String movieName=movie.getTitle();
        getField();
        showPass();

        book_title.setId("#titlelabel");
        HBox title = new HBox(book_title);
        title.setAlignment(Pos.CENTER);

        getGridPane(box1, 10, 20, 0, 50, 5, 5);

        box1.add(movie_name, 0, 0);
        box1.add(movie_show, 0, 2);

        MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
        ArrayList<String> all_movie_names = nMovieDaoImpl.get_all_movies_name();
        for (int i = 0; i < all_movie_names.size(); ++i) {
            movie_names.getItems().addAll(all_movie_names.get(i));
        }

        movie_names.setValue(movieName);

        movie_shows.getItems().removeAll(movie_shows.getItems());
        MovieShowDaoImpl nMovieShowDaoImpl = new MovieShowDaoImpl();
        ArrayList<String> all_movieshow_infos = nMovieShowDaoImpl.get_all_movie_shows_for_a_movie((String) movie_names.getValue());
        for (int i = 0; i < all_movieshow_infos.size(); ++i) {
            movie_shows.getItems().addAll(all_movieshow_infos.get(i));
        }

        movie_names.setOnAction(this);
        movie_shows.setId("choice-box");
        movie_names.setId("choice-box");
        box1.add(movie_names, 1, 0);
        box1.add(movie_shows, 1, 2);

        box1.add(junior_places, 0, 3);
        junior_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        junior_places_choice.setValue("0");
        junior_places_choice.setId("choice-box");
        box1.add(junior_places_choice, 1, 3);

        box1.add(adult_places, 0, 4);
        adult_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        adult_places_choice.setValue("0");
        adult_places_choice.setId("choice-box");
        box1.add(adult_places_choice, 1, 4);

        box1.add(senior_places, 0, 5);
        senior_places_choice.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        senior_places_choice.setValue("0");
        senior_places_choice.setId("choice-box");
        box1.add(senior_places_choice, 1, 5);

        if (CineProjectDemo.memberConnected) {
            box1.add(add_to_basket, 0, 6);
        } else {
            box1.add(book, 0, 6);
        }

        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setTop(title);
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        add_to_basket.setId("bouton");
        add_to_basket.setOnAction(this);
        book.setId("bouton");
        book.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(bord);
        secondaryLayout.setId("book");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Book a ticket");
    }

    @Override
    public void handle(ActionEvent e) {

        if (add_to_basket == e.getSource()) {
            //add to basket les tickets
            PurchaseDaoImpl nPurchaseDaoImpl = new PurchaseDaoImpl();
            MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
            int movie_id = nMovieDaoImpl.get_a_movieId((String) movie_names.getValue());
            int junior_choice = Integer.parseInt(junior_places_choice.getValue().toString());
            int adult_choice = Integer.parseInt(adult_places_choice.getValue().toString());
            int senior_choice = Integer.parseInt(senior_places_choice.getValue().toString());

            //check if everything is correctly filled
            if ((String) movie_names.getValue() == null) {//if any movie is selected
                alert_window("booking error", "you didn't selected any movie");
            } else if ((String) movie_shows.getValue() == null) { //if any movie show is selected
                alert_window("booking error", "you didn't selected any movie show");
            } else if (junior_choice == 0 && adult_choice == 0 && senior_choice == 0) { //if any ticket is selected
                alert_window("booking error", "you didn't selected any ticket");
            } else { //RAS
                nPurchaseDaoImpl.createPurchase(movie_id, junior_choice, adult_choice, senior_choice);

                //clear text field
                getClearField();
                getCloseWindow(e, add_to_basket);
            }
        }

        if (book == e.getSource()) {
            //add to basket les tickets

            MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
            int movie_id = nMovieDaoImpl.get_a_movieId((String) movie_names.getValue());
            int junior_choice = Integer.parseInt(junior_places_choice.getValue().toString());
            int adult_choice = Integer.parseInt(adult_places_choice.getValue().toString());
            int senior_choice = Integer.parseInt(senior_places_choice.getValue().toString());

            //check if everything is correctly filled
            if ((String) movie_names.getValue() == null) {//if any movie is selected
                alert_window("booking error", "you didn't selected any movie");
            } else if ((String) movie_shows.getValue() == null) { //if any movie show is selected
                alert_window("booking error", "you didn't selected any movie show");
            } else if (junior_choice == 0 && adult_choice == 0 && senior_choice == 0) { //if any ticket is selected
                alert_window("booking error", "you didn't selected any ticket");
            } else { //RAS

                //payment infos window
                PaymentView nPaymentView = new PaymentView();
                nPaymentView.start(movie_id, junior_choice, adult_choice, senior_choice);

                //clear text field
                getClearField();
                getCloseWindow(e, book);

            }
        }

        if (movie_names == e.getSource()) {

            movie_shows.getItems().removeAll(movie_shows.getItems());
            MovieShowDaoImpl nMovieShowDaoImpl = new MovieShowDaoImpl();
            ArrayList<String> all_movieshow_infos = nMovieShowDaoImpl.get_all_movie_shows_for_a_movie((String) movie_names.getValue());
            for (int i = 0; i < all_movieshow_infos.size(); ++i) {
                movie_shows.getItems().addAll(all_movieshow_infos.get(i));
            }
        }
    }

    public void alert_window(String header, String content) {
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
