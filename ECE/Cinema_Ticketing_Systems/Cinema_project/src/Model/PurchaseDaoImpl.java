/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.PaymentView;
import controller.*;
import java.sql.*;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author dckt2
 */
public class PurchaseDaoImpl implements PurchaseDao {

    private String url = "jdbc:mysql://localhost:3306/Cinema";
    private String user = "root";
    private String password = "root";
    private Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addPurchase(Purchase purchase) {

        String sql = "INSERT INTO `Purchase`(purchaseId, customerId, total_price"
                + ") VALUES (" + purchase.getPurchaseId() + "," + purchase.getCustomerId()
                + "," + purchase.getTotalPrice() + ")";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into Purchase table for  purchase : " + purchase.getPurchaseId());

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public List<Purchase> getAllPurchases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Purchase getPurchase(int purchaseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePurchase(int purchaseId) {

        String sql = "DELETE FROM `Purchase` WHERE `purchaseId`=" + purchaseId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The movie with the Id " + purchaseId + " has been deleted");

        } catch (SQLException e) {

            System.out.println(sql);

            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public int get_purchase_id() {

        int purchase_id = 0;

        String sql = "SELECT MAX(purchaseId) FROM `purchase`";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain a purchaseId has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getInt(1)); 
                purchase_id = result.getInt(1);
            }
            result.close();

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return purchase_id;
    }

    public void update_total_price(Purchase purchase) {
        System.out.println("purchase.getTotalPrice() = " + purchase.getTotalPrice());
        String sql = "UPDATE `purchase` SET total_price = " + purchase.getTotalPrice()
                + " WHERE purchaseId = " + purchase.getPurchaseId();
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("The request to update the total_price has been made");

        } catch (SQLException e) {

            System.out.println(sql);
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (login != null) {
                try {
                    login.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    
    //create a purchase for a guest (paid immediately)
    public void createPurchase2(int movie_id, int junior_places_choice, int adult_places_choice, int senior_places_choice) {
        
        
        int customer_id = 0; //default customer_id if the customer is not a member
        int loyalty_points_parchase = 0;

        // create purchase 
        Purchase newPurchase = new Purchase();
        newPurchase.setCustomerId(customer_id);
        newPurchase.setTotalPrice(0);
        PurchaseDaoImpl nPurchaseDaoImpl = new PurchaseDaoImpl();
        nPurchaseDaoImpl.addPurchase(newPurchase);
        int purchase_id = nPurchaseDaoImpl.get_purchase_id();
        newPurchase.setPurchaseId(purchase_id);

        //add in table ticket the tickets bought
        for (int i = 0; i < junior_places_choice; ++i) {
            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.JUNIOR_PRICE);
            newPurchase.setTotalPrice(newPurchase.getTotalPrice() + Ticket.JUNIOR_PRICE);
            //System.out.println("total_price = " + newPurchase.getTotalPrice());
            newticket.setPurchaseID(purchase_id);
            TicketDaoImpl nTicketDoaImpl = new TicketDaoImpl();
            nTicketDoaImpl.addTicket(newticket);
            loyalty_points_parchase += (int) Ticket.JUNIOR_PRICE;
        }
        for (int i = 0; i < adult_places_choice; ++i) {

            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.ADULT_PRICE);
            newPurchase.setTotalPrice(newPurchase.getTotalPrice() + Ticket.ADULT_PRICE);
            //System.out.println("total_price = " + newPurchase.getTotalPrice());
            newticket.setPurchaseID(purchase_id);
            TicketDaoImpl nTicketDoaImpl = new TicketDaoImpl();
            nTicketDoaImpl.addTicket(newticket);
            loyalty_points_parchase += (int) Ticket.ADULT_PRICE;
        }
        for (int i = 0; i < senior_places_choice; ++i) {

            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.SENIOR_PRICE);
            newPurchase.setTotalPrice(newPurchase.getTotalPrice() + Ticket.SENIOR_PRICE);
            //System.out.println("total_price = " + newPurchase.getTotalPrice());
            newticket.setPurchaseID(purchase_id);
            TicketDaoImpl nTicketDoaImpl = new TicketDaoImpl();
            nTicketDoaImpl.addTicket(newticket);
            loyalty_points_parchase += (int) Ticket.SENIOR_PRICE;
        }

        //update loyalty points of the customer if he's a member
        if (CineProjectDemo.memberConnected) {
            CineProjectDemo.member_connected.set_loyalty_points(loyalty_points_parchase);
            MemberDaoImpl nMemberDaoImpl = new MemberDaoImpl();
            nMemberDaoImpl.update_loyalty_points(loyalty_points_parchase, customer_id);
            nMemberDaoImpl.update_discount(loyalty_points_parchase, customer_id);
        }

        //update total price 
        nPurchaseDaoImpl.update_total_price(newPurchase);

        //display total price
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information message...");
        alert.setHeaderText("Purchase succeded");
        if (CineProjectDemo.memberConnected) {
            alert.setContentText("Original price of the purchase : " + newPurchase.getTotalPrice() + "£."
                    + "\nYour member discount : " + CineProjectDemo.member_connected.get_discount()
                    + "\nNow price = " + (newPurchase.getTotalPrice() - newPurchase.getTotalPrice() * CineProjectDemo.member_connected.get_discount()) + "£.");
        } else {
            alert.setContentText("Total price of the purchase : " + newPurchase.getTotalPrice() + "£.");
        }
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
        
    }
    
    
    //create a purchase (not paid yet) and add it in the basket
    public void createPurchase(int movie_id, int junior_places_choice, int adult_places_choice, int senior_places_choice) {
        
        int customer_id = 0; //default customer_id if the customer is not a member
        if (CineProjectDemo.memberConnected) {
            customer_id = CineProjectDemo.member_connected.getId();
        }
        
        // create basket purchase 
        CineProjectDemo.member_connected.getbasket_purchase().setCustomerId(customer_id);
        CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(0);
        

        //add the tickets in a new future purchase (basket) 
        for (int i = 0; i < junior_places_choice; ++i) { 
            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.JUNIOR_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() + Ticket.JUNIOR_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().add_ticket_to_purchase(newticket);
            
        }
        for (int i = 0; i < adult_places_choice; ++i) {
            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.ADULT_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() + Ticket.ADULT_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().add_ticket_to_purchase(newticket);
            
        }
        for (int i = 0; i < senior_places_choice; ++i) {
            Ticket newticket = new Ticket();
            newticket.setCustomerID(customer_id);
            newticket.setMovieID(movie_id); //get idmovie of the selected movie
            newticket.setPrice(Ticket.SENIOR_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() + Ticket.SENIOR_PRICE);
            CineProjectDemo.member_connected.getbasket_purchase().add_ticket_to_purchase(newticket);
        }
    }
    

        //buy the tickets that are in the basket
        public void buyPurchase(){
            
        int customer_id = 0; //default customer_id if the customer is not a member
        int loyalty_points_parchase = 0;
        if (CineProjectDemo.memberConnected) {
            customer_id = CineProjectDemo.member_connected.getId();

            // update features of the purchase and add it in the database
            CineProjectDemo.member_connected.getbasket_purchase().setCustomerId(customer_id);
            CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(0);
            PurchaseDaoImpl nPurchaseDaoImpl = new PurchaseDaoImpl();
            nPurchaseDaoImpl.addPurchase(CineProjectDemo.member_connected.getbasket_purchase()); //pas creer mais sauver dans basket
            int purchase_id = nPurchaseDaoImpl.get_purchase_id();
            CineProjectDemo.member_connected.getbasket_purchase().setPurchaseId(purchase_id);

            //add in database tickets bought 
            TicketDaoImpl nTicketDoaImpl = new TicketDaoImpl();
            double total_price = 0;    
            for(int i=0;i<CineProjectDemo.member_connected.getbasket_purchase().getTicketList().size();++i){
                loyalty_points_parchase += (int) CineProjectDemo.member_connected.getbasket_purchase().getTicketList().get(i).getPrice();
                CineProjectDemo.member_connected.getbasket_purchase().getTicketList().get(i).setPurchaseID(purchase_id);
                total_price += CineProjectDemo.member_connected.getbasket_purchase().getTicketList().get(i).getPrice();
                nTicketDoaImpl.addTicket(CineProjectDemo.member_connected.getbasket_purchase().getTicketList().get(i));
            }


            //update loyalty points of the customer if he's a member
            if (CineProjectDemo.memberConnected) {
                CineProjectDemo.member_connected.set_loyalty_points(loyalty_points_parchase);
                MemberDaoImpl nMemberDaoImpl = new MemberDaoImpl();
                nMemberDaoImpl.update_loyalty_points(loyalty_points_parchase, customer_id);
                nMemberDaoImpl.update_discount(loyalty_points_parchase, customer_id);
            }


            //update total price 
            CineProjectDemo.member_connected.getbasket_purchase().setTotalPrice(total_price);
            nPurchaseDaoImpl.update_total_price(CineProjectDemo.member_connected.getbasket_purchase());

            //display total price
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message...");
            alert.setHeaderText("Purchase succeded");
            if (CineProjectDemo.memberConnected) {
                alert.setContentText("Original price of the purchase : " + CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() + "£."
                        + "\nYour member discount : " + CineProjectDemo.member_connected.get_discount()
                        + "\nNow price = " + (CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() - CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() * CineProjectDemo.member_connected.get_discount()) + "£.");
            } else {
                alert.setContentText("Total price of the purchase : " + CineProjectDemo.member_connected.getbasket_purchase().getTotalPrice() + "£.");
            }
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

    }

}
