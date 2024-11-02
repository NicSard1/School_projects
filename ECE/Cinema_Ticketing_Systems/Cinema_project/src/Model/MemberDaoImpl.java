/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.*;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author dckt2
 */
public class MemberDaoImpl implements MemberDao {

    private String url = "jdbc:mysql://localhost:3306/Cinema";
    private String user = "root";
    private String password = "root";
    private final Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addMember(Member member) {

        String sql = "INSERT INTO `customer`(`Name`, `Surname`, `Age` ,`email`"
                + ",`password`,`payment_infos`) VALUES ('" + member.getName()
                + "','" + member.getSurname() + "'," + member.getAge() + ",'"
                + member.getEmail() + "','" + member.get_password() + "',"
                + member.get_payment_infos() + ")";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into Customer table for  Customer : " + member.getSurname());

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
    public ArrayList<Member> getAllMembers() {

        ArrayList<Member> listMembers = new ArrayList<>();
        String sql = "SELECT * FROM `customer`";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            try ( //statement.executeUpdate(sql);
                    ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Member infoMembers = new Member();
                    infoMembers.setId(rs.getInt("idCustomer"));
                    infoMembers.setAge(rs.getInt("Age"));
                    infoMembers.setName(rs.getString("Name"));
                    infoMembers.setSurname(rs.getString("Surname"));
                    infoMembers.setEmail(rs.getString("email"));
                    infoMembers.setPassword(rs.getString("password"));
                    infoMembers.set_discount(rs.getDouble("discount"));
                    listMembers.add(infoMembers);
                }
            }

            listMembers.forEach((test1) -> {
                System.out.println("Name : " + test1.getName() + ", Surname : "
                        + test1.getSurname() + ", Age : " + test1.getAge() + ", Email : " + test1.getEmail() + ", password : " + test1.get_password() + ", Discounts : " + test1.get_discount() + "%"
                        + ", Id : " + test1.getId());
            });
            return listMembers;

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
        return listMembers;
    }

    @Override
    public Member getMember(int memberId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMember(Member member) {

        String sql = "DELETE FROM `customer` WHERE (`name`='" + member.getName() + "' AND `password`='" + member.get_password() + "') OR (`email`='" + member.getEmail() + "' AND `password`='" + member.get_password() + "')";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The account of " + member.getName() + " " + member.getSurname() + " has been deleted");

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

    public int get_loyalty_points(Member member) {

        int loyalty_points = 0;

        String sql = "SELECT loyalty_points from `customer` where idCustomer = " + member.getId();

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The request for loyalty points of customer " + member.getId() + " has been made");

            ResultSet result = statement.executeQuery(sql);
            loyalty_points = result.getInt(1);

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

        return loyalty_points;
    }

    public void update_loyalty_points(int loyalty_points, int customerId) {
        String sql = "UPDATE customer SET loyalty_points = " + loyalty_points
                + " WHERE idCustomer = " + customerId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The request for change loyalty points of customer " + customerId + " has been made");

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

    public void update_discount(int loyalty_points, int customerId) {

        double discount = loyalty_points * 0.001;
        CineProjectDemo.member_connected.set_discount(discount);
        if (discount > 0.5) {
            discount = 0.5;
        }

        String sql = "UPDATE customer SET discount = " + discount
                + " WHERE idCustomer = " + customerId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The request for change discount of customer " + customerId + " has been made");

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

    public String get_history_purchase(int customerId) {
        String history = "";

        //recup tous les tickets
        TicketDaoImpl nTicketDoaImpl = new TicketDaoImpl();
        ArrayList<Ticket> ticket_list = nTicketDoaImpl.load_tickets_knowing_customerId(customerId);

        //display tickets
        for (int i = 0; i < ticket_list.size(); ++i) {
            history += ticket_list.get(i).toString();
        }

        return history;
    }

    //update all member's features
    @Override
    public void updateMember(Member member) {

        String sql = "UPDATE customer "
                + "SET Surname = '" + member.getSurname()
                + "', Name = '" + member.getName()
                + "', Age = " + member.getAge()
                + " , email = '" + member.getEmail()
                + "', password = '" + member.get_password()
                + "', payment_infos = " + member.get_payment_infos()
                + " WHERE idCustomer = " + member.getId();

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Record to update Customer : " + member.getSurname() + " has been made");

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

    public boolean sign_in(String name, String passwd) {

        boolean connection_succes = true;

        String sql = "SELECT * FROM `customer` where Name = '" + name + "' "
                + "AND password = '" + passwd + "'";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request verify if a connection is possible has been made");

            try (ResultSet result = statement.executeQuery(sql)) {
                if (result.next()) {
                    //System.out.println("Connection with name = " + result.getString(1) + " and password = " + result.getString(7)) ;
                    CineProjectDemo.memberConnected = true;
                    CineProjectDemo.member_connected = new Member();
                    CineProjectDemo.member_connected.setName(result.getString(1));
                    CineProjectDemo.member_connected.setSurname(result.getString(2));
                    CineProjectDemo.member_connected.setAge(Integer.parseInt(result.getString(3)));
                    CineProjectDemo.member_connected.setEmail(result.getString(4));
                    CineProjectDemo.member_connected.setId(Integer.parseInt(result.getString(5)));
                    CineProjectDemo.member_connected.set_discount(Double.parseDouble(result.getString(6)));
                    CineProjectDemo.member_connected.setPassword(result.getString(7));
                    CineProjectDemo.member_connected.set_payment_infos(Integer.parseInt(result.getString(8)));
                    CineProjectDemo.member_connected.set_loyalty_points(Integer.parseInt(result.getString(9)));
                } else {
                    connection_succes = false;
                }
            }

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

        return connection_succes;
    }

    public boolean payment(int value) {
        boolean payment_succes = false;

        if (!CineProjectDemo.memberConnected) {
            payment_succes = true;
        } else {
            System.out.println("member_connected.get_payment_infos() = " + CineProjectDemo.member_connected.get_payment_infos());
            if (value != CineProjectDemo.member_connected.get_payment_infos()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message...");
                alert.setHeaderText("payment failed");
                alert.setContentText("This information don't correspond to your payment information.");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            } else {
                payment_succes = true;
            }
        }

        return payment_succes;
    }
}
