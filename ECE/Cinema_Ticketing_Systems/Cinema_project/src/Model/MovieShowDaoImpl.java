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
public class MovieShowDaoImpl implements MovieShowDao{

    private String url = "jdbc:mysql://localhost:3306/Cinema";
    private String user = "root";
    private String password = "root";
    private Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addMovieShow(MovieShow movieShow) {

        String sql = "INSERT INTO `Movieshow`(idShow, availablePlaces,"
                + "seatsSold, date, hour, roomNumber, movieId) VALUES (" + 
                movieShow.getMovieShowId() +","+movieShow.getAvailablePlaces()+"," +
                movieShow.getSeatsSold()+",'"+movieShow.getDate()+"','"+
                movieShow.getHour()+"'," +movieShow.getRoomNumber()+ ","+
                movieShow.getMovieId() + ")";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into MovieShow table for  movieShow : " + movieShow.getMovieShowId());

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
    public ArrayList<MovieShow> getAllMovieShows() {
        ArrayList<MovieShow> listSession = new ArrayList<>();
        String sql = "SELECT * FROM `movieshow`";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            //statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                MovieShow infoMovie = new MovieShow();
                infoMovie.setAvailablePlaces(rs.getInt("availablePlaces"));
                infoMovie.setDate(rs.getString("date"));
                infoMovie.setMovieId(rs.getInt("movieId"));
                infoMovie.setHour(rs.getString("hour"));
                infoMovie.setMovieShowId(rs.getInt("idShow"));
                infoMovie.setRoomNumber(rs.getInt("roomNumber"));
                listSession.add(infoMovie);
            }
            return listSession;

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
        return listSession;
    }

    @Override
    public Movie getMovieShow(int movieShowId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMovieShow(MovieShow movieShow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMovieShow(int movieShowId) {
        
        String sql = "DELETE FROM `MovieShow` WHERE `Id`=" + movieShowId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The movieShow with the Id "+movieShowId+" has been deleted");
            

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
    
    
    //find in the database all the movie show (date+hour) corresponding to the movie name chosen
    public ArrayList<String> get_all_movie_shows_for_a_movie(String name){
        
        ArrayList<String> movies_show = new ArrayList<String>();
        
        String sql = "SELECT DISTINCT date, hour FROM `movieshow` WHERE movieId"
                + " = (SELECT DISTINCT id from `movie` WHERE title = '"+name+"')";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate date and hour has been made");

            ResultSet result = statement.executeQuery(sql);
            while(result.next()){ 
                //System.out.println(result.getString(1)); 
                movies_show.add(result.getString(1) + " " + result.getString(2));
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

        return movies_show ;
    }    
}




