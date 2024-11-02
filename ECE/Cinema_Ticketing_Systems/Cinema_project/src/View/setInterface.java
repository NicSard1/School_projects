/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bretechersandric
 */
public class setInterface extends Application implements EventHandler<ActionEvent> {

// variables d'instance
    protected TextField id2 = new TextField();
    protected TextField name2 = new TextField();
    protected TextField surname2 = new TextField();
    protected TextField email2 = new TextField();
    protected TextField payment_infos2 = new TextField();
    protected PasswordField password2 = new PasswordField();

    protected final Label name = new Label("Name  : ");
    protected final Label id = new Label("Employee Id  : ");
    protected final Label age = new Label("Date of Birth : ");
    protected final Label email = new Label("Email : ");
    protected final Label surname = new Label("Surname  : ");
    protected final Label password = new Label("Password : ");
    protected final Label homeBot = new Label("Cinema Movie Night");
    protected final Label home = new Label("Cinema Movie Night");
    protected final Label payment_infos = new Label("Payment infos : ");
    protected final Label connected_as = new Label("");
    protected final Label id_customer = new Label("Customer ID  : ");
    protected final Label payment = new Label("Payment infos : ");
    protected final Label loyalty_points = new Label("Loyalty points : ");
    protected final Label discount = new Label("Discount : ");
    protected final Label movie_name = new Label("Movie's name : ");
    protected final Label movie_show = new Label("Movie show : ");
    protected final Label movie_title = new Label("Movie's titles : ");
    protected final Label movie_duration = new Label("Movie's durations : ");
    protected final Label movie_productor = new Label("Movie's productors : ");
    protected final Label movie_type = new Label("Movie's types : ");
    protected final Label junior_places = new Label("Junior tickets : ");
    protected final Label adult_places = new Label("Adult tickets : ");
    protected final Label senior_places = new Label("Senior tickets : ");

    protected final Button submit = new Button("Submit");
    protected final Button movie1 = new Button("");
    protected final Button movie2 = new Button("");
    protected final Button movie3 = new Button("");
    protected final Button movie4 = new Button("");
    protected final Button movie5 = new Button("");
    protected final Button movie6 = new Button("");
    protected final Button delete = new Button("Delete employee");
    protected final Button signIn = new Button("Sign In");
    protected final Button signUp = new Button("Sign Up");
    protected final Button signOut = new Button("Sign Out");
    protected final Button quit = new Button();
    protected final Button book = new Button("Book now");
    protected final Button signEmployee = new Button();
    protected final Button showpass = new Button();
    protected final Button update_infos = new Button("Update infos");
    protected final Button newMember = new Button("Add a new member");
    protected final Button search_movie = new Button("Search for a movie");
    protected final Button search = new Button("Search");
    protected final Button cancel = new Button("Cancel");
    protected final Button newBooking = new Button("Book now");
    protected final Button basket_button = new Button();
    protected final Button add_to_basket = new Button("Add to basket");
    protected final Button empty_basket = new Button("Empty my basket");
    protected final Button pay_now = new Button("Pay now");

    protected final DatePicker born = new DatePicker();

    protected ChoiceBox movie_names = new ChoiceBox();
    protected ChoiceBox movie_shows = new ChoiceBox();
    protected ChoiceBox junior_places_choice = new ChoiceBox();
    protected ChoiceBox adult_places_choice = new ChoiceBox();
    protected ChoiceBox senior_places_choice = new ChoiceBox();

    protected ChoiceBox movie_titles = new ChoiceBox();
    protected ChoiceBox movie_durations = new ChoiceBox();
    protected ChoiceBox movie_productors = new ChoiceBox();
    protected ChoiceBox movie_types = new ChoiceBox();

    protected String pass;

    protected GridPane box1 = new GridPane();

    public ArrayList<Button> book_film_found = new ArrayList<>();

    Employee nEmployee = new Employee();
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    Member nMember = new Member();
    MemberDaoImpl memberDao = new MemberDaoImpl();

    MovieDaoImpl movieDao = new MovieDaoImpl();

    public void getCloseWindow(ActionEvent event, Button closeButton) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public Stage getNewWindow(Scene secondScene, String titleWindow) {
        // New window (Stage)
        secondScene.getStylesheets().addAll(getClass().getResource("/sources/style.css").toExternalForm());//we use a css style sheet to put an image in the background
        Stage newWindow = new Stage();
        newWindow.setTitle(titleWindow);//windows's title
        newWindow.initStyle(StageStyle.UTILITY);
        newWindow.setResizable(false);//resizing not possible
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.initOwner(newWindow.getOwner());
        newWindow.setScene(secondScene);
        newWindow.show();
        return newWindow;
    }

    public void getGridPane(GridPane nameBox, int x, int y, int z, int p, int v, int h) {
        nameBox.setHgap(h);
        nameBox.setVgap(v);
        nameBox.setPadding(new Insets(x, y, z, p));
        nameBox.setGridLinesVisible(false);
    }

    public void showPass() {

        //Creating a graphic (image)
        Image img = new Image("/sources/eye.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);
        showpass.setPrefSize(10, 10);
        showpass.setGraphic(view);
        showpass.setId("menu");

        //showpass word
        showpass.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            pass = password2.getText();
            password2.clear();
            password2.setPromptText(pass);
        });
        showpass.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            password2.setText(pass);
            password2.setPromptText("Enter your password.");
        });
    }

    public ImageView getImage(String title, int x, int y) {
        Class<?> clazz = this.getClass();
        InputStream input = clazz.getResourceAsStream(title);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setId("affiche");
        imageView.setFitHeight(x);
        imageView.setFitWidth(y);
        return imageView;
    }

    public void getMenu() {
        //buttons style with css style sheet 
        Tooltip signUpTool = new Tooltip("Creates a new member");
        signUp.setTooltip(signUpTool);
        signUp.setId("menu");

        Tooltip searchMovieTool = new Tooltip("Apply filters to search your movie");
        search_movie.setTooltip(searchMovieTool);
        search_movie.setId("bouton");

        Tooltip signInTool = new Tooltip("Connect you as member");
        signIn.setTooltip(signInTool);
        signIn.setId("menu");

        Tooltip signOutTool = new Tooltip("Disconnect you as member");
        signOut.setTooltip(signOutTool);
        signOut.setId("menu");

        Tooltip employeeOnly = new Tooltip("Employee only");
        signEmployee.setTooltip(employeeOnly);
        signEmployee.setId("menu");

        quit.setId("menu");
        quit.setText("Exit");

        //Creating a graphic (image
        Image admin = new Image("/sources/admin.png");
        ImageView adminicon = new ImageView(admin);
        adminicon.setFitHeight(40);
        adminicon.setPreserveRatio(true);
        signEmployee.setPrefSize(10, 10);
        signEmployee.setGraphic(adminicon);
        signEmployee.setId("menu");

        signUp.setId("menu");
        quit.setId("menu");
        home.setId("menu");
        homeBot.setId("menu");
        book.setId("bouton");
        basket_button.setId("menu");

        //setOnAction button menu
        quit.setOnAction(this);
        signIn.setOnAction(this);
        search_movie.setOnAction(this);
        search.setOnAction(this);
        signEmployee.setOnAction(this);
        signUp.setOnAction(this);
        book.setOnAction(this);
        signOut.setOnAction(this);
    }

    public void getField() {
        id2.setPromptText("Enter an employee ID.");
        id2.setPrefColumnCount(10);
        id2.getText();

        email2.setPromptText("Enter your email.");
        email2.setPrefColumnCount(10);
        email2.getText();

        surname2.setPromptText("Enter your surname.");
        surname2.setPrefColumnCount(10);
        surname2.getText();

        name2.setPromptText("Enter your first name.");
        name2.setPrefColumnCount(10);
        name2.getText();

        born.setPromptText("DD/MM/YYYY");

        password2.setPromptText("Enter your password.");
        password2.setPrefColumnCount(10);
        password2.getText();

        payment_infos2.setPromptText("Enter your payment infos.");
        payment_infos2.setPrefColumnCount(10);
        payment_infos2.getText();
    }

    public void getClearField() {
        surname2.clear();
        name2.clear();
        born.setPromptText("Enter your date.");
        password2.clear();
        id2.clear();
        payment_infos2.clear();
        email2.clear();
    }

    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
