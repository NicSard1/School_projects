/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.*;

import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bretechersandric
 */
public class CinemaInterface extends setInterface implements EventHandler<ActionEvent> {

    MenuItem my_information_button = new MenuItem("My information");
    MenuItem purchase_history_button = new MenuItem("Purchase history");
    MenuItem delete_account_button = new MenuItem("Delete account");
    MenuItem sign_out_button = new MenuItem("Sign out");

    Button viewRight = new Button();
    Button viewLeft = new Button();

    GridPane setimage = new GridPane();

    @Override
    public void start(Stage bookingApp) throws IOException {

        BorderPane window = new BorderPane();

        /*-------- MENU BAR --------*/
        //menu bar if nobody is memberConnected
        HBox right_disconnected = new HBox(signIn, signUp);
        right_disconnected.setAlignment(Pos.CENTER_RIGHT);

        /*menu bar if a member is memberConnected*/
        sign_out_button.setOnAction(this);
        delete_account_button.setOnAction(this);
        purchase_history_button.setOnAction(this);
        my_information_button.setOnAction(this);

        //basket button
        Image img_basket = new Image("/sources/basket_icon.png");
        ImageView view_basket = new ImageView(img_basket);
        view_basket.setFitHeight(40);
        view_basket.setPreserveRatio(true);
        basket_button.setOnAction(this);
        basket_button.setGraphic(view_basket);

        //profile menu
        MenuButton profile = new MenuButton("My profile", null, my_information_button, purchase_history_button, delete_account_button, sign_out_button);
       
        Image icon_profile = new Image("/sources/icon.png");
        ImageView icon_profile_view = new ImageView(icon_profile);
        icon_profile_view.setFitHeight(40);
        icon_profile_view.setPreserveRatio(true);
        profile.setGraphic(icon_profile_view);
        profile.setId("menu");
        HBox right_connected = new HBox(basket_button, profile);
        right_connected.setAlignment(Pos.CENTER_RIGHT);

        //Menu Bar
        getMenu();

        HBox left = new HBox(home);
        left.setAlignment(Pos.CENTER_LEFT);

        HBox centerBot = new HBox(homeBot);
        centerBot.setAlignment(Pos.CENTER);

        HBox rightBot = new HBox(signEmployee, quit);
        rightBot.setAlignment(Pos.CENTER_RIGHT);

        StackPane menuBot = new StackPane();
        menuBot.getChildren().addAll(centerBot, rightBot);
        menuBot.setId("menubar");

        StackPane menuTop = new StackPane();

        //main window is different if someone is memberConnected or not
        window.addEventFilter(MouseEvent.MOUSE_MOVED, e
                -> {

            if (CineProjectDemo.memberConnected) {
                menuTop.getChildren().clear();
                menuTop.getChildren().addAll(left, right_connected);

            } else {
                menuTop.getChildren().clear();
                menuTop.getChildren().addAll(left, right_disconnected);
            }

        });
        menuTop.setId("menubar");

        /*-------- HOME PAGE --------*/
        movie1.setGraphic(getImage("/sources/drStrange.jpeg", 330, 230));
        movie1.setId("menu");
        movie1.setOnAction(this);

        movie2.setGraphic(getImage("/sources/topgun.jpeg", 330, 230));
        movie2.setId("menu");
        movie2.setOnAction(this);

        movie3.setGraphic(getImage("/sources/jurassic.jpeg", 330, 230));
        movie3.setId("menu");
        movie3.setOnAction(this);

        movie4.setGraphic(getImage("/sources/avatar2.jpeg", 330, 230));
        movie4.setId("menu");
        movie4.setOnAction(this);

        movie5.setGraphic(getImage("/sources/men.jpeg", 330, 230));
        movie5.setId("menu");
        movie5.setOnAction(this);

        movie6.setGraphic(getImage("/sources/elvis.jpeg", 330, 230));
        movie6.setId("menu");
        movie6.setOnAction(this);

        getGridPane(setimage, 20, 80, 0, 80, 5, 5);

        viewRight.setOnAction(this);
        Image arrowr = new Image("/sources/arrowR.png");
        ImageView arrowR = new ImageView(arrowr);
        arrowR.setFitHeight(40);
        arrowR.setPreserveRatio(true);
        viewRight.setPrefSize(10, 10);
        viewRight.setGraphic(arrowR);
        viewRight.setId("menu");
        viewRight.setAlignment(Pos.CENTER_RIGHT);

        viewLeft.setOnAction(this);

        Image arrowl = new Image("/sources/arrowL.png");
        ImageView arrowL = new ImageView(arrowl);
        arrowL.setFitHeight(40);
        arrowL.setPreserveRatio(true);
        viewLeft.setPrefSize(10, 10);
        viewLeft.setGraphic(arrowL);
        viewLeft.setId("menu");
        viewLeft.setAlignment(Pos.CENTER_LEFT);

        setimage.setAlignment(Pos.CENTER);
        setimage.setTranslateY(35);

        setimage.add(movie1, 0, 0);
        setimage.add(movie2, 2, 0);
        setimage.add(movie3, 4, 0);

        HBox arrow = new HBox(viewLeft, viewRight);
        arrow.setAlignment(Pos.CENTER);
        arrow.setSpacing(90);

        HBox booking = new HBox(book, search_movie);
        booking.setAlignment(Pos.CENTER);
        booking.setSpacing(260);

        VBox bottom = new VBox(arrow, booking, menuBot);
        bottom.setSpacing(45);

        /*-------- APPLICATION --------*/
        window.setTop(menuTop);
        window.setCenter(setimage);
        window.setBottom(bottom);

        StackPane root = new StackPane();
        root.getChildren().addAll(window);
        root.setBackground(Background.EMPTY);
        //root.setId("back");

        Scene scene = new Scene(root, 1368.0, 1026.0);//size of scene with the content to display
        scene.getStylesheets().addAll(this.getClass().getResource("/sources/style.css").toExternalForm());//we use a css style sheet to put an image in the background
        bookingApp = new Stage(StageStyle.DECORATED);
        bookingApp.setTitle("Ticket Booking Project");//windows's title
        bookingApp.setResizable(true);//resizing possible
        bookingApp.setFullScreen(false);
        bookingApp.setScene(scene);
        bookingApp.show();
        //player.play();  //lance la video

    }

    @Override
    public void handle(ActionEvent e) {
        int movieIdButton;
        if (quit == e.getSource()) {
            stop();
        }
        if (signEmployee == e.getSource()) {
            EmployeeLogin loginEmp = new EmployeeLogin();
            loginEmp.start();
        }

        if (signUp == e.getSource()) {
            MemberRegister register = new MemberRegister();
            register.start();
        }

        if (signIn == e.getSource()) {
            MemberLogin login = new MemberLogin();
            login.start();
        }

        if (book == e.getSource()) {
            TicketRegister ticket_register = new TicketRegister();
            ticket_register.start();
        }
        if (viewRight == e.getSource()) {
            setimage.getChildren().clear();
            setimage.add(movie4, 0, 0);
            setimage.add(movie5, 2, 0);
            setimage.add(movie6, 4, 0);
        }
        if (viewLeft == e.getSource()) {
            setimage.getChildren().clear();
            setimage.add(movie1, 0, 0);
            setimage.add(movie2, 2, 0);
            setimage.add(movie3, 4, 0);
        }

        if (search_movie == e.getSource()) {
            MovieSearch nMovieSearch = new MovieSearch();
            nMovieSearch.start();
        }

        if (movie1 == e.getSource()) {
            movieIdButton = 1;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }
        if (movie2 == e.getSource()) {
            movieIdButton = 134;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }
        if (movie3 == e.getSource()) {
            movieIdButton = 135;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }

        if (movie4 == e.getSource()) {
            movieIdButton = 136;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }
        if (movie5 == e.getSource()) {
            movieIdButton = 139;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }

        if (movie6 == e.getSource()) {
            movieIdButton = 140;
            MovieInfos movieSelect = new MovieInfos();
            movieSelect.start(movieIdButton);
        }

        if (basket_button == e.getSource()) {

            viewBasket nviewBasket = new viewBasket();
            nviewBasket.start(CineProjectDemo.member_connected.getbasket_purchase().toString(), 0, 0, 0, 0);
        }
        if (my_information_button == e.getSource()) {
            //System.out.println("clique book");
            MemberViewInfos nMemberViewInfos = new MemberViewInfos();
            nMemberViewInfos.start();
        }
        if (purchase_history_button == e.getSource()) {
            String history_purchase = memberDao.get_history_purchase(CineProjectDemo.member_connected.getId());
            PurchaseHistoryPage nPurchaseHistoryPage = new PurchaseHistoryPage();
            nPurchaseHistoryPage.start(history_purchase);
        }
        if (delete_account_button == e.getSource()) {
            MemberDelete deletedMember = new MemberDelete();
            deletedMember.start();
        }
        if (sign_out_button == e.getSource()) {
            CineProjectDemo.memberConnected = false;
        }

    }

    @Override
    public void stop() {// il faut quitter l'application lorsque la fen�tre est ferm�e
        System.exit(0);
    }

}
