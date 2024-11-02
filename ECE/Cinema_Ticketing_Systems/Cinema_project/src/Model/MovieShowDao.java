/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dckt2
 */
public interface MovieShowDao {
    public ArrayList<MovieShow> getAllMovieShows();

    public void addMovieShow(MovieShow movieShow);

    public Movie getMovieShow(int movieShowId);

    public void updateMovieShow(MovieShow movieShow);

    public void deleteMovieShow(int movieShowId); 
    
}
