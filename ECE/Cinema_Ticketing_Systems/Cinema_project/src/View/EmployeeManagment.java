/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author bretechersandric
 */
public class EmployeeManagment extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Label homeMana = new Label("CineMovie - Managment");
    private final MenuItem newEmployee = new MenuItem("New employee");
    private final MenuItem movie = new MenuItem("Movies");
    private final MenuItem sessions = new MenuItem("Movies sessions");
    private final MenuItem infosEmployee = new MenuItem("My informations");
    private final MenuItem customerManagement = new MenuItem("Customers accounts");
    private final MenuItem employeeMana = new MenuItem("Employees accounts");
    private final MenuItem deleteEmployeeAccount = new MenuItem("Delete employee");
    private final MenuItem accueil = new MenuItem("Home");
    private final MenuItem signout = new MenuItem("Quit");

    private final BorderPane window = new BorderPane();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart barChart = new BarChart(xAxis, yAxis);

    public void start() throws IOException {
        getMenu();

        //movie.setId("menu");
        quit.setId("menu");
        homeMana.setId("menu");
        //newEmployee.setId("menu");
        //delete.setId("menu");

        movie.setOnAction(this);
        sessions.setOnAction(this);
        quit.setOnAction(this);
        infosEmployee.setOnAction(this);
        newEmployee.setOnAction(this);
        deleteEmployeeAccount.setOnAction(this);
        customerManagement.setOnAction(this);
        employeeMana.setOnAction(this);
        accueil.setOnAction(this);
        signout.setOnAction(this);

        MenuButton profile = new MenuButton("Options", null, infosEmployee, customerManagement, employeeMana, movie, sessions, newEmployee, deleteEmployeeAccount, accueil, signout);
        profile.setId("menu");

        HBox right = new HBox(profile);
        right.setAlignment(Pos.CENTER_RIGHT);

        HBox left = new HBox(homeMana);
        left.setAlignment(Pos.CENTER_LEFT);

        HBox centerBot = new HBox(homeBot);
        centerBot.setAlignment(Pos.CENTER);

        HBox rightBot = new HBox(quit);
        rightBot.setAlignment(Pos.CENTER_RIGHT);

        
        /*---------- Graph for number of ticket sold ----------*/
        //Thread.sleep(2000);
        barChart.setTitle("Tickets sold");
        xAxis.setLabel("Ticket category");
        yAxis.setLabel("Tickets sold");
        
        XYChart.Series ticketSeries = new XYChart.Series();
        ticketSeries.setName("Sold tickets");

        TicketDaoImpl ticketDao = new TicketDaoImpl();
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) ticketDao.getAllTickets();
        int junior = 0;
        int adult = 0;
        int senior = 0;
        for (int i = 0; i < tickets.size(); ++i) {
            if (ticketDao.getAllTickets().get(i).getPrice() == 7.99) {
                adult = adult;
                adult++;
            }
            if (ticketDao.getAllTickets().get(i).getPrice() == 5.99) {
                junior = junior;
                junior++;
            }
            if (ticketDao.getAllTickets().get(i).getPrice() == 6.99) {
                senior = senior;
                senior++;
            }
        }
        ticketSeries.getData().add(new XYChart.Data("Junior, 5.99£", junior));
        ticketSeries.getData().add(new XYChart.Data("Adult, 7.99£", adult));
        ticketSeries.getData().add(new XYChart.Data("Senior, 6.99£", senior));

        barChart.getData().add(ticketSeries);
        
        /*---------- Scene ----------*/
        StackPane menuBot = new StackPane();
        menuBot.getChildren().addAll(centerBot, rightBot);
        menuBot.setId("menubar");

        StackPane menuTop = new StackPane();
        menuTop.getChildren().addAll(left, right);
        menuTop.setId("menubar");

        //border pane
        window.setTop(menuTop);
        window.setCenter(barChart);
        window.setBottom(menuBot);
        window.setId("menubar");

        StackPane root = new StackPane();
        root.getChildren().addAll(window);
        root.setBackground(Background.EMPTY);

        //new scene
        Scene managmentScene = new Scene(root, 968, 368);
        getNewWindow(managmentScene, "");
    }

    @Override
    public void handle(ActionEvent e) {
        if (quit == e.getSource()) {
            //CineProjectDemo.employeeConnected = false;
            getCloseWindow(e, quit);
        }
        if (newEmployee == e.getSource()) {
            EmployeeRegister register = new EmployeeRegister();
            register.start();
        }
        if (deleteEmployeeAccount == e.getSource()) {
            EmployeeDelete del = new EmployeeDelete();
            del.start();
        }
        if (infosEmployee == e.getSource()) {
            EmployeeInfos employeeViewInfos = new EmployeeInfos();
            employeeViewInfos.start();
        }
        if (signout == e.getSource()) {
            stop();
        }
        if (movie == e.getSource()) {
            MovieDaoImpl movieDao = new MovieDaoImpl();
            ArrayList<Movie> a = movieDao.getAllMovies();

            TableView tableView = new TableView();

            TableColumn<Movie, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Movie, String> productCol = new TableColumn<>("Productor");
            productCol.setCellValueFactory(new PropertyValueFactory<>("productor"));

            TableColumn<Movie, String> typeCol = new TableColumn<>("Type");
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

            TableColumn<Movie, Integer> durationCol = new TableColumn<>("Duration");
            durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

            TableColumn<Movie, String> summaryCol = new TableColumn<>("Summary");
            summaryCol.setCellValueFactory(new PropertyValueFactory<>("summary"));

            TableColumn<Movie, String> afficheCol = new TableColumn<>("Affiche");
            afficheCol.setCellValueFactory(new PropertyValueFactory<>("affiche"));

            TableColumn<Movie, Integer> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn manaMovie = new TableColumn("All movies");
            manaMovie.getColumns().addAll(titleCol, durationCol, productCol, typeCol, summaryCol, afficheCol);

            tableView.getColumns().addAll(manaMovie);
            for (int i = 0; i < a.size(); i++) {
                tableView.getItems().add(a.get(i));
            }

            final TextField titleMovie = new TextField();
            titleMovie.setPromptText("Title");
            titleMovie.setMaxWidth(titleCol.getPrefWidth());

            final TextField productor = new TextField();
            productor.setMaxWidth(productCol.getPrefWidth());
            productor.setPromptText("Productor");

            final TextField duration = new TextField();
            duration.setMaxWidth(durationCol.getPrefWidth());
            duration.setPromptText("Duration");

            final TextField type = new TextField();
            type.setMaxWidth(typeCol.getPrefWidth());
            type.setPromptText("Type");

            final TextField summary = new TextField();
            summary.setMaxWidth(summaryCol.getPrefWidth());
            summary.setPromptText("Summary");

            ComboBox idMovie = new ComboBox();
            idMovie.setMaxWidth(idCol.getPrefWidth());
            idMovie.setPromptText("Id");
            idMovie.setEditable(true);
            ArrayList<Movie> b = movieDao.getAllMovies();

            for (int j = 0; j < b.size(); j++) {
                idMovie.getItems().add(b.get(j).getMovieId());
            }

            final TextField afficheNew = new TextField();
            afficheNew.setMaxWidth(afficheCol.getPrefWidth());
            afficheNew.setPromptText("Affiche  (/sources/affiche.format)");

            final Button addMovie = new Button("Add movie");
            addMovie.setOnAction((ActionEvent event) -> {
                Movie nMovie = new Movie();
                nMovie.setTitle(titleMovie.getText());
                nMovie.setDuration(Integer.parseInt(duration.getText()));
                nMovie.setProductor(productor.getText());
                nMovie.setType((type.getText()));
                nMovie.setAffiche(afficheNew.getText());
                nMovie.setSummary(summary.getText());

                movieDao.addMovie(nMovie);

                titleMovie.clear();
                productor.clear();
                type.clear();
                summary.clear();
                afficheNew.clear();
            });

            final Button updateMovie = new Button("Update movie");
            updateMovie.setOnAction((ActionEvent event) -> {
                Movie nMovie = new Movie();

                String idSelect = (String) idMovie.getSelectionModel().getSelectedItem().toString();
                int idselected = Integer.parseInt(idSelect);

                nMovie.setMovieId(idselected);

                if ((titleMovie.getText().compareTo("") == 0)) {
                    titleMovie.setText(movieDao.getMovie(idselected).getTitle());
                    nMovie.setTitle(titleMovie.getText());
                } else {
                    nMovie.setTitle(titleMovie.getText());
                }
                if ((productor.getText().compareTo("") == 0)) {
                    productor.setText(movieDao.getMovie(idselected).getProductor());
                    nMovie.setProductor((productor.getText()));
                } else {
                    nMovie.setProductor((productor.getText()));
                }
                if ((duration.getText().compareTo("") == 0)) {
                    duration.setText(Integer.toString(movieDao.getMovie(idselected).getDuration()));
                    nMovie.setDuration(Integer.parseInt(duration.getText()));
                } else {
                    nMovie.setDuration(Integer.parseInt(duration.getText()));
                }
                if ((type.getText().compareTo("") == 0)) {
                    type.setText(movieDao.getMovie(idselected).getType());
                    nMovie.setType((type.getText()));
                } else {
                }
                if ((summary.getText().compareTo("") == 0)) {
                    summary.setText(movieDao.getMovie(idselected).getSummary());
                    nMovie.setSummary((summary.getText()));
                } else {
                    nMovie.setSummary((summary.getText()));
                }
                if ((afficheNew.getText().compareTo("") == 0)) {
                    afficheNew.setText(movieDao.getMovie(idselected).getAffiche());
                    nMovie.setAffiche((afficheNew.getText()));
                } else {
                    nMovie.setAffiche((afficheNew.getText()));
                }
                movieDao.updateMovie(nMovie);

                titleMovie.clear();
                productor.clear();
                duration.clear();
                type.clear();
                summary.clear();
                afficheNew.clear();
                idMovie.getSelectionModel().clearSelection();
            });

            final Button deleteMovie = new Button("Delete movie");
            deleteMovie.setOnAction((ActionEvent event) -> {
                Movie nMovie = new Movie();

                String idSelect = (String) idMovie.getSelectionModel().getSelectedItem().toString();
                int idselected = Integer.parseInt(idSelect);

                nMovie.setMovieId(idselected);

                movieDao.deleteMovie(nMovie.getMovieId());

                idMovie.getSelectionModel().clearSelection();

            });

            final HBox hb = new HBox();
            hb.getChildren().addAll(idMovie, titleMovie, duration, productor, type, summary, afficheNew, addMovie, updateMovie, deleteMovie);
            hb.setSpacing(3);

            final VBox movieView = new VBox();
            movieView.setSpacing(5);
            movieView.setPadding(new Insets(10, 0, 0, 10));
            movieView.getChildren().addAll(tableView, hb);

            window.setCenter(null);
            window.setCenter(movieView);
        }
        if (sessions == e.getSource()) {
            MovieShowDaoImpl movieShowDao = new MovieShowDaoImpl();
            ArrayList<MovieShow> a = movieShowDao.getAllMovieShows();

            TableView tableView = new TableView();

            TableColumn<Movie, String> idmovie = new TableColumn<>("idMovie");
            idmovie.setCellValueFactory(new PropertyValueFactory<>("movieId"));

            TableColumn<Movie, String> idseance = new TableColumn<>("idShow");
            idseance.setCellValueFactory(new PropertyValueFactory<>("idShow"));

            TableColumn<Movie, String> avaiblePlace = new TableColumn<>("Places available");
            avaiblePlace.setCellValueFactory(new PropertyValueFactory<>("availablePlaces"));

            TableColumn<Movie, String> placesSold = new TableColumn<>("Places sold");
            placesSold.setCellValueFactory(new PropertyValueFactory<>("availablePlaces"));

            TableColumn<Movie, Integer> room = new TableColumn<>("Room");
            room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

            TableColumn<Movie, String> date = new TableColumn<>("Date");
            date.setCellValueFactory(new PropertyValueFactory<>("hour"));

            TableColumn<Movie, String> hour = new TableColumn<>("Hour");
            hour.setCellValueFactory(new PropertyValueFactory<>("date"));

            TableColumn manaMovie = new TableColumn("All movies");
            manaMovie.getColumns().addAll(idseance, idmovie, avaiblePlace, placesSold, date, hour, room);

            tableView.getColumns().addAll(manaMovie);
            for (int i = 0; i < a.size(); i++) {
                tableView.getItems().add(a.get(i));
            }

            final TextField idMovie = new TextField();
            idMovie.setPromptText("idMovie");
            idMovie.setMaxWidth(idmovie.getPrefWidth());

            final TextField places = new TextField();
            places.setMaxWidth(avaiblePlace.getPrefWidth());
            places.setPromptText("Places available");

            final DatePicker dateShow = new DatePicker();
            dateShow.setMaxWidth(date.getPrefWidth());
            dateShow.setPromptText("Date");

            final TextField hourShow = new TextField();
            hourShow.setMaxWidth(hour.getPrefWidth());
            hourShow.setPromptText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));

            final TextField roomSession = new TextField();
            roomSession.setMaxWidth(room.getPrefWidth());
            roomSession.setPromptText("Room");

            final Button addSession = new Button("Add session");
            addSession.setOnAction((ActionEvent event) -> {
                MovieShow nMovieShow = new MovieShow();
                nMovieShow.setMovieId(Integer.parseInt(idMovie.getText()));
                nMovieShow.setRoomNumber(Integer.parseInt(roomSession.getText()));
                nMovieShow.setAvailablePlaces(Integer.parseInt(places.getText()));
                LocalDate session = dateShow.getValue();
                nMovieShow.setDate(session.toString());
                nMovieShow.setHour((hourShow.getText()));

                movieShowDao.addMovieShow(nMovieShow);

                hourShow.clear();
            });

            final Button delSession = new Button("Delete session");
            delSession.setOnAction((ActionEvent event) -> {
                MovieShow nMovieShow = new MovieShow();
                nMovieShow.setMovieShowId(Integer.parseInt(idMovie.getText()));

                movieShowDao.deleteMovieShow(nMovieShow.getMovieId());

                idMovie.clear();
            });

            final HBox hb = new HBox();
            hb.getChildren().addAll(idMovie, places, dateShow, hourShow, roomSession, addSession, delSession);
            hb.setSpacing(3);

            final VBox movieView = new VBox();
            movieView.setSpacing(5);
            movieView.setPadding(new Insets(10, 0, 0, 10));
            movieView.getChildren().addAll(tableView, hb);
            
            window.setCenter(null);
            window.setCenter(movieView);
        }

        if (employeeMana == e.getSource()) {
            ArrayList<Employee> a = employeeDao.getAllEmployees();

            TableView tableView = new TableView();

            TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn<Employee, String> surnameCol = new TableColumn<>("Surname");
            surnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));

            TableColumn<Employee, Integer> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

            TableColumn<Employee, Integer> idCol = new TableColumn<>("Id");
            idCol.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));

            TableColumn manaEmployee = new TableColumn("Employees accounts");
            manaEmployee.getColumns().addAll(nameCol, surnameCol, ageCol, idCol);

            tableView.getColumns().addAll(manaEmployee);
            for (int i = 0; i < a.size(); i++) {
                tableView.getItems().add(a.get(i));
            }
            window.setCenter(null);
            window.setCenter(tableView);
        }

        if (customerManagement == e.getSource()) {

            ArrayList<Member> a = memberDao.getAllMembers();

            TableView tableView = new TableView();

            TableColumn<Member, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn<Member, String> surnameCol = new TableColumn<>("Surname");
            surnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));

            TableColumn<Member, Integer> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

            TableColumn<Member, String> emailCol = new TableColumn<>("email");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Member, String> passCol = new TableColumn<>("password");
            passCol.setCellValueFactory(new PropertyValueFactory<>("password"));

            TableColumn<Member, Double> discountCol = new TableColumn<>("discount");
            discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));

            TableColumn memberMana = new TableColumn("Members accounts");
            memberMana.getColumns().addAll(nameCol, surnameCol, ageCol, emailCol, passCol, discountCol);

            tableView.getColumns().addAll(memberMana);
            for (int i = 0; i < a.size(); i++) {
                tableView.getItems().add(a.get(i));
            }

            ComboBox nameMember = new ComboBox();
            //nameMember.setMaxWidth(passCol.getPrefWidth());
            nameMember.setPromptText("Member's name");
            nameMember.setEditable(true);
            ArrayList<Member> b = memberDao.getAllMembers();

            for (int j = 0; j < b.size(); j++) {
                nameMember.getItems().add(b.get(j).getName());
            }

            final TextField password3 = new TextField();
            //password3.setMaxWidth(passCol.getPrefWidth());
            password3.setPromptText("Member's password");

            final Button deleteCustom = new Button("Delete member");
            deleteCustom.setOnAction((ActionEvent event) -> {
                String name3 = (String) nameMember.getSelectionModel().getSelectedItem().toString();
                nMember.setName(name3);
                nMember.setPassword(password3.getText());
                memberDao.deleteMember(nMember);
                nameMember.getSelectionModel().clearSelection();
                password3.clear();
            });
            final HBox hb = new HBox();
            hb.getChildren().addAll(nameMember, password3, deleteCustom);
            hb.setSpacing(3);

            final VBox movieView = new VBox();
            movieView.setSpacing(5);
            movieView.setPadding(new Insets(10, 0, 0, 10));
            movieView.getChildren().addAll(tableView, hb);
            window.setCenter(null);
            window.setCenter(movieView);
        }
        if (accueil == e.getSource()) {
            window.setCenter(barChart);
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

}
