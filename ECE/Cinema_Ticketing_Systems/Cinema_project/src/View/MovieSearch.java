/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import Model.*;
import java.util.ArrayList;
import javafx.geometry.Pos;

/**
 *
 * @author dckt2
 */
public class MovieSearch extends CinemaInterface implements EventHandler<ActionEvent> {
    
    
    public void start() {
        getField();
        showPass();
                
        getGridPane(box1, 10, 20, 0, 50, 5, 5);
        box1.add(movie_type, 0, 0);
        box1.add(movie_productor, 0, 1); 
        box1.add(movie_duration, 0, 2); 
        
        MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
        ArrayList<String> all_movie_types = nMovieDaoImpl.get_all_movies_types();
        
        for(int i=0; i<all_movie_types.size(); ++i){
            movie_types.getItems().addAll(all_movie_types.get(i));
        }
        ArrayList<String> all_movie_productors = nMovieDaoImpl.get_all_movies_productors();
        for(int i=0; i<all_movie_productors.size(); ++i){
            movie_productors.getItems().addAll(all_movie_productors.get(i));
        }
        ArrayList<String> all_movie_durations = nMovieDaoImpl.get_all_movies_durations();
        for(int i=0; i<all_movie_durations.size(); ++i){
            movie_durations.getItems().addAll(all_movie_durations.get(i));
        }
        
        movie_types.setOnAction(this);
        movie_types.setId("choice-box");
        movie_productors.setOnAction(this);
        movie_productors.setId("choice-box");
        movie_durations.setOnAction(this);
        movie_durations.setId("choice-box");
        box1.add(movie_types, 1, 0);
        box1.add(movie_productors, 1, 1);
        box1.add(movie_durations, 1, 2);
        
        search.setOnAction(this);
        search.setId("bouton");
        box1.add(search, 0, 6);
   
        box1.setAlignment(Pos.CENTER);
        
        //border pane
        BorderPane bord = new BorderPane();
        bord.setCenter(box1);
        bord.setBackground(Background.EMPTY);


        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(bord);
        secondaryLayout.setId("searchmovie");

        // New window (Stage)
        Scene addScene = new Scene(secondaryLayout, 550, 450);
        getNewWindow(addScene, "Search a movie");
    }
    
    
    
    @Override
    public void handle(ActionEvent e) {
        if (search == e.getSource()) {
            
            String type, productor, duration;
            if(movie_types.getValue()==null){
                type = "";
            }else{
                type = (String)movie_types.getValue();
            }
            if(movie_productors.getValue()==null){
                productor = "";
            }else{
                productor = (String)movie_productors.getValue();
            }
            if(movie_durations.getValue()==null){
                duration = "";
            }else{
                duration = (String)movie_durations.getValue();
            }
            
            //display movies which correspond to the filters
            MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
            ArrayList<Integer> search_film = nMovieDaoImpl.search_film(type, productor, duration);
            
            SearchResults nSearchResults = new SearchResults();
            nSearchResults.start(search_film);
            
            //clear text field
            getClearField();
            getCloseWindow(e, search);
            
            
        }
    }    
}
