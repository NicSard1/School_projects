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
public class MovieDaoImpl implements MovieDao {

    private String url = "jdbc:mysql://localhost:3306/Cinema";
    private String user = "root";
    private String password = "root";
    private final Connection dbConnection = null;
    private Connection login = null;
    private Statement statement = null;

    @Override
    public void addMovie(Movie movie) {

        String sql = "INSERT INTO `Movie`(id, title, duration, productor, type,"
                + "summary, idMovieShow, affiche) VALUES (" + movie.getMovieId()
                + ",'" + movie.getTitle() + "'," + movie.getDuration() + ",'"
                + movie.getProductor() + "','" + movie.getType() + "','"
                + movie.getSummary() + "'," + movie.getMovieShowId() + ",'" + movie.getAffiche() + "')";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);

            System.out.println("Record is inserted into Movie table for  movie : " + movie.getTitle());

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
    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> listMovie = new ArrayList<>();
        String sql = "SELECT * FROM `movie`";

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            //statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Movie infoMovie = new Movie();
                infoMovie.setTitle(rs.getString("title"));
                infoMovie.setType(rs.getString("type"));
                infoMovie.setDuration(rs.getInt("duration"));
                infoMovie.setMovieId(rs.getInt("id"));
                infoMovie.setSummary(rs.getString("summary"));
                infoMovie.setMovieShowId(rs.getInt("idMovieShow"));
                infoMovie.setProductor(rs.getString("productor"));
                infoMovie.setAffiche(rs.getString("affiche"));
                listMovie.add(infoMovie);
            }
            listMovie.forEach((test1) -> {
                System.out.println("Title : " + test1.getTitle() + ", Productor : "
                        + test1.getProductor() + ", Type : " + test1.getType()
                        + ", Id : " + test1.getMovieId() + ", IdShowMovie : "
                        + test1.getMovieShowId() + ", Duration : " + test1.getDuration()
                        + ", Summary : " + test1.getSummary() + ", Affiche : " + test1.getAffiche());
            });
            return listMovie;

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
        return listMovie;
    }

    @Override
    public Movie getMovie(int movieId) {

        String sql = "SELECT * FROM movie WHERE `id`=" + movieId;
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            statement.executeQuery(sql);

            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Movie movie = new Movie();
                movie.setTitle(rs.getString("title"));
                movie.setProductor(rs.getString("productor"));
                movie.setDuration(rs.getInt("duration"));
                movie.setType(rs.getString("type"));
                movie.setMovieId(rs.getInt("id"));
                movie.setSummary(rs.getString("summary"));
                movie.setAffiche(rs.getString("affiche"));

                return movie;
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
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {
        String sql = "UPDATE movie "
                + "SET title = '" + movie.getTitle()
                + "', summary = '" + movie.getSummary()
                + "', duration = " + movie.getDuration()
                + ", affiche = '" + movie.getAffiche()
                + "' WHERE `id` = " + movie.getMovieId();

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);
            System.out.println("Record to update Movie : " + movie.getTitle() + " has been made");

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
    public void deleteMovie(int movieId) {

        String sql = "DELETE FROM `movie` WHERE `id`=" + movieId;

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            statement.executeUpdate(sql);
            System.out.println("The movie with the Id " + movieId + " has been deleted");

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

    public ArrayList<String> get_all_movies_name() {

        ArrayList<String> movies_name = new ArrayList<String>();

        String sql = "SELECT DISTINCT title FROM `movie`";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate titles has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getString(1)); 
                movies_name.add(result.getString(1));
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

        return movies_name;
    }

    public int get_a_movieId(String title) {

        int movieId = 0;

        String sql = "SELECT DISTINCT id FROM movie WHERE title = '" + title + "'";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain a movieId has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getInt(1)); 
                movieId = result.getInt(1);
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

        return movieId;
    }

    public String get_a_movieTitle(int movieId) {

        String title = "";

        String sql = "SELECT DISTINCT title FROM movie WHERE id = " + movieId;
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain a movieTitle has been made");

            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                title = result.getString(1);
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

        return title;
    }

    public ArrayList<String> get_all_movies_types() {

        ArrayList<String> movies_types = new ArrayList<String>();

        String sql = "SELECT DISTINCT type FROM `movie`";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate types has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getString(1)); 
                movies_types.add(result.getString(1));
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

        return movies_types;
    }

    public ArrayList<String> get_all_movies_productors() {

        ArrayList<String> movies_productors = new ArrayList<String>();

        String sql = "SELECT DISTINCT productor FROM `movie`";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate productors has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getString(1)); 
                movies_productors.add(result.getString(1));
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

        return movies_productors;
    }

    public ArrayList<String> get_all_movies_durations() {

        ArrayList<String> movies_durations = new ArrayList<String>();

        String sql = "SELECT DISTINCT duration FROM `movie`";
        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate durations has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getString(1)); 
                movies_durations.add(result.getString(1));
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

        return movies_durations;
    }

    //filter movies with type, productor and duration criteria
    public ArrayList<Integer> search_film(String type, String productor, String duration) {

        ArrayList<Integer> movies_id = new ArrayList<Integer>();

        String sql;
        if (type == "") {
            if (productor == "") {
                if (duration == "") {
                    sql = "SELECT DISTINCT id FROM movie";
                } else {
                    sql = "SELECT DISTINCT id FROM movie WHERE duration = " + Integer.parseInt(duration);
                }
            } else {
                if (duration == "") {
                    sql = "SELECT DISTINCT id FROM movie WHERE productor = '" + productor + "'";
                } else {
                    sql = "SELECT DISTINCT id FROM movie WHERE duration = " + Integer.parseInt(duration) + " AND productor = '" + productor + "'";
                }
            }
        } else {
            if (productor == "") {
                if (duration == "") {
                    sql = "SELECT DISTINCT id FROM movie WHERE type = '" + type + "'";
                } else {
                    sql = "SELECT DISTINCT id FROM movie WHERE duration = " + Integer.parseInt(duration) + " AND type = '" + type + "'";
                }
            } else {
                if (duration == "") {
                    sql = "SELECT DISTINCT id FROM movie WHERE productor = '" + productor + "' AND type = '" + type + "'";
                } else {
                    sql = "SELECT DISTINCT id FROM movie WHERE duration = " + Integer.parseInt(duration) + " AND productor = '" + productor + "' AND type = '" + type + "'";
                }
            }

        }

        try {
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();

            System.out.println("The request to obtain all existing separate duration knowing a type and productor has been made");

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //System.out.println(result.getString(1)); 
                movies_id.add(Integer.parseInt(result.getString(1)));
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

        return movies_id;
    }
}
