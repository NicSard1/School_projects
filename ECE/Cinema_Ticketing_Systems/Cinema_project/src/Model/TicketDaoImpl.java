/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dckt2
 */
public class TicketDaoImpl implements TicketDao {

    private final String url = "jdbc:mysql://localhost:3306/Cinema";
    private final String user = "root";
    private final String password = "root";
    private final Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addTicket(Ticket ticket) {

        String sql = "INSERT INTO `ticket`(idTicket, idCustomer, idMovie, "
                + "purchaseId, price) VALUES (" + ticket.getTicketId() + ","
                + ticket.getCustomerId() + "," + ticket.getMovieId() + ","
                + ticket.getPurchaseId() + "," + ticket.getPrice() + ")";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into Ticket table for  ticket : " + ticket.getMovieId());

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
    public List<Ticket> getAllTickets() {
        ArrayList<Ticket> ticket_list = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            ResultSet result = statement.executeQuery(sql);
            System.out.println("The tickets has been loaded");

            while (result.next()) {
                Ticket newTicket = new Ticket();
                newTicket.setMovieID(Integer.parseInt(result.getString("idMovie")));
                newTicket.setPrice(Double.parseDouble(result.getString("price")));
                newTicket.setPurchaseID(Integer.parseInt(result.getString("purchaseId")));
                newTicket.setCustomerID(Integer.parseInt(result.getString("idCustomer")));
                newTicket.setTicketID(Integer.parseInt(result.getString("idTicket")));
                ticket_list.add(newTicket);
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

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return ticket_list;

    }

    @Override
    public Ticket getTicket(int ticketId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTicket(Ticket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTicket(int ticketId) {

        String sql = "DELETE FROM `Ticket` WHERE `Id`= " + ticketId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The ticket with the Id " + ticketId + " has been deleted");

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

    //load all tickets bought buy a known customer
    public ArrayList<Ticket> load_tickets_knowing_customerId(int customerId) {
        ArrayList<Ticket> ticket_list = new ArrayList<>();
        String sql = "SELECT DISTINCT * FROM ticket WHERE idCustomer = " + customerId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            ResultSet result = statement.executeQuery(sql);
            System.out.println("The tickets has been loaded");

            while (result.next()) {
                Ticket newTicket = new Ticket();
                newTicket.setMovieID(Integer.parseInt(result.getString(3)));
                newTicket.setPrice(Double.parseDouble(result.getString(5)));
                newTicket.setPurchaseID(Integer.parseInt(result.getString(4)));
                newTicket.setCustomerID(Integer.parseInt(result.getString(2)));
                newTicket.setTicketID(Integer.parseInt(result.getString(1)));
                ticket_list.add(newTicket);
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

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return ticket_list;
    }

}
