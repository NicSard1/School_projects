/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author dckt2
 */
public interface MovieDao {

    public List<Movie> getAllMovies();

    public void addMovie(Movie movie);

    public Movie getMovie(int movieId);

    public void updateMovie(Movie movie);

    public void deleteMovie(int movieId);

}
