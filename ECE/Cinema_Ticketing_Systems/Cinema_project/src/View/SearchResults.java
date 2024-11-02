/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MovieDaoImpl;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dckt2
 */
public class SearchResults extends CinemaInterface implements EventHandler<ActionEvent> {

    MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
    ArrayList<Integer> mySearch;
    public void start(ArrayList<Integer> search_film) {
        mySearch=search_film;
        getField();
        showPass();

        Label help = new Label();

        //activate button
        cancel.setId("bouton");
        cancel.setOnAction(this);
        cancel.setAlignment(Pos.BOTTOM_CENTER);

        Label any_film_found;

        HBox booking = new HBox();
        booking.setAlignment(Pos.CENTER);
        booking.setSpacing(10);

        if (search_film.isEmpty()) {
            any_film_found = new Label("Any film found with your criteria...");
            booking.getChildren().add(any_film_found);
            help = new Label("");
        } else {
            for (int i = 0; i < search_film.size(); ++i) {
                Image img = new Image(nMovieDaoImpl.getMovie(search_film.get(i)).getAffiche());
                ImageView affiche = new ImageView(img);
                affiche.setFitHeight(180);
                affiche.setId("affiche");
                affiche.setPreserveRatio(true);
                Tooltip newButton = new Tooltip(nMovieDaoImpl.getMovie(search_film.get(i)).getTitle());
                book_film_found.add(new Button());
                book_film_found.get(i).setId("menu");
                book_film_found.get(i).setTooltip(newButton);
                book_film_found.get(i).setGraphic(affiche);
                book_film_found.get(i).setOnAction(this);
                //A revoir maisn'affiche plus le filmsélectionner prbook
                //book_film_found.get(i).setText(nMovieDaoImpl.getMovie(search_film.get(i)).getTitle());
                
                booking.getChildren().addAll(book_film_found.get(i));
                help = new Label("Click on a movie to book it !");
            }
        }
        help.setAlignment(Pos.CENTER);

        search_movie.setText("New search");
        search_movie.setId("bouton");
        search_movie.setOnAction(this);

        HBox nouveau = new HBox(cancel, search_movie);
        nouveau.setAlignment(Pos.CENTER);
        nouveau.setSpacing(20);

        VBox bottom = new VBox(booking, help, nouveau);
        bottom.setSpacing(20);
        bottom.setTranslateY(10);
        bottom.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(bottom);
        bord.setBackground(Background.EMPTY);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("search");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Film found");
    }

    @Override
    public void handle(ActionEvent e) {

        int nb_button = book_film_found.size();

        for (int i = 0; i < nb_button; ++i) {
            if (book_film_found.get(i) == e.getSource()) {

                System.out.println("appui " + i);
                TicketRegister nTicketRegister = new TicketRegister();
                System.out.println("book_film_found = " + book_film_found.get(i).getText());
                nTicketRegister.start(nMovieDaoImpl.getMovie(mySearch.get(i)).getTitle());
            }
            getCloseWindow(e, book_film_found.get(i));
        }

        if (search_movie == e.getSource()) {
            MovieSearch nMovieSearch = new MovieSearch();
            nMovieSearch.start();
            getCloseWindow(e, search_movie);
        }

        if (cancel == e.getSource()) {
            getCloseWindow(e, cancel);
        }

    }

}
