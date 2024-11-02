/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Employee;
import Model.Member;
import Model.MemberDaoImpl;
import controller.CineProjectDemo;
import java.util.ArrayList;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 *
 * @author dckt2
 */
public class viewBasket extends CinemaInterface implements EventHandler<ActionEvent> {

    private final Text book_title = new Text("Book");
    int cmovie_id;
    int cjunior_choice;
    int cadult_choice;
    int csenior_choice;

    public void start(String purchase_basket, int movie_id, int junior_choice, int adult_choice, int senior_choice) {

        cmovie_id = movie_id;
        cjunior_choice = junior_choice;
        cadult_choice = adult_choice;
        csenior_choice = senior_choice;

        getField();
        showPass();

        book_title.setId("#titlelabel");
        HBox title = new HBox(book_title);
        title.setAlignment(Pos.CENTER);

        getGridPane(box1, 10, 20, 0, 50, 5, 5);

        Label basket_purchase = new Label();
        if (purchase_basket == "") {
            basket_purchase = new Label("Nothing in your basket");
            box1.add(cancel, 0, 6);
        } else {
            /*MemberDaoImpl hisorique = new MemberDaoImpl();
            
           //ArrayList<Member> a = hisorique.get_history_purchase(cmovie_id);

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
            }*/

            //window.setCenter(tableView);
            basket_purchase = new Label(purchase_basket);
            box1.add(newBooking, 0, 6);
            box1.add(empty_basket, 1, 6);
        }

        box1.add(basket_purchase, 0, 0);

        //activate button
        cancel.setId("bouton");
        cancel.setOnAction(this);
        cancel.setAlignment(Pos.BOTTOM_CENTER);

        box1.setAlignment(Pos.CENTER);

        //border pane
        BorderPane bord = new BorderPane();
        bord.setTop(title);
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);

        //activate button
        newBooking.setId("bouton");
        newBooking.setOnAction(this);
        empty_basket.setId("bouton");
        empty_basket.setOnAction(this);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("panier");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "View basket");
    }

    @Override
    public void handle(ActionEvent e) {
        if (newBooking == e.getSource()) {

            //payment infos window
            PaymentView nPaymentView = new PaymentView();
            nPaymentView.start(cmovie_id, cjunior_choice, cadult_choice, csenior_choice);

            //clear text field
            getClearField();
            getCloseWindow(e, newBooking);
        }

        if (empty_basket == e.getSource()) {
            CineProjectDemo.member_connected.clear_basket();

            //clear text field
            getClearField();
            getCloseWindow(e, empty_basket);
        }

        if (cancel == e.getSource()) {
            getCloseWindow(e, cancel);
        }
    }

}
