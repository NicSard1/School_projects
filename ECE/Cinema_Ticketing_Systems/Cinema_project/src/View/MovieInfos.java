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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dckt2
 */
public class MovieInfos extends CinemaInterface implements EventHandler<ActionEvent> {

    int movieIdSelect;

    public void start(int movieId) {
        getField();
        showPass();
        movieIdSelect = movieId;

        Image img = new Image(movieDao.getMovie(movieId).getAffiche());
        ImageView affiche = new ImageView(img);
        affiche.setFitHeight(300);
        affiche.setPreserveRatio(true);

        Label title = new Label(String.valueOf(movieDao.getMovie(movieId).getTitle()));
        Label productor = new Label(movieDao.getMovie(movieId).getProductor());
        Label duration = new Label(Integer.toString(movieDao.getMovie(movieId).getDuration()));
        Label type = new Label(movieDao.getMovie(movieId).getType());
        TextArea summary = new TextArea(movieDao.getMovie(movieId).getSummary());
        Label title1 = new Label("Title : ");
        Label productor1 = new Label("Productor : ");
        Label duration1 = new Label("Duration : ");
        Label type1 = new Label("Type : ");
        Label summary1 = new Label("Summary : ");

        getGridPane(box1, 0, 30, 0, 50, 10, 10);
        box1.add(title1, 0, 0);
        box1.add(title, 1, 0);
        box1.add(productor1, 0, 1);
        box1.add(productor, 1, 1);
        box1.add(duration1, 0, 2);
        box1.add(duration, 1, 2);
        box1.add(type1, 0, 3);
        box1.add(type, 1, 3);
        box1.add(summary1, 0, 5);
        box1.add(summary, 1, 5);
        summary.setPrefWidth(200);
        summary.setEditable(false);
        summary.setWrapText(true);
        box1.setAlignment(Pos.CENTER);

        HBox choice = new HBox(book, cancel);
        choice.setSpacing(30);
        choice.setAlignment(Pos.CENTER);

        HBox data = new HBox(affiche, box1);
        data.setSpacing(50);
        data.setAlignment(Pos.CENTER);

        VBox movieInfosView = new VBox(data, choice);
        movieInfosView.setSpacing(30);
        movieInfosView.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(movieInfosView);
        bord.setBackground(Background.EMPTY);

        //activate button
        cancel.setId("bouton");
        cancel.setOnAction(this);
        book.setId("bouton");
        book.setOnAction(this);

        //bord.setId("menubar");
        
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().addAll(bord);
        secondaryLayout.setId("moreAboutMovie");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 700, 600);
        getNewWindow(addScene, "Movie infos");
    }

    @Override
    public void handle(ActionEvent e) {

        if (book == e.getSource()) {
            TicketRegister nTicketRegister = new TicketRegister();
            nTicketRegister.start(movieDao.getMovie(movieIdSelect).getTitle());
            getCloseWindow(e, book);
        }

        if (cancel == e.getSource()) {
            getCloseWindow(e, cancel);
        }
    }

}
