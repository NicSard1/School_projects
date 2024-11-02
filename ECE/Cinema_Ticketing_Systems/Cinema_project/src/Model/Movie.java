/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dckt2
 */
public class Movie {
    private int movieId;
    private int duration;
    private int movieShowId;
    private String title;
    private String productor;
    private String type;
    private String summary;
    private String affiche;
    
    public int getMovieId(){
        return movieId;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getMovieShowId(){
        return movieShowId;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getProductor(){
        return productor;
    }
    
    public String getType(){
        return type;
    }
    
    public String getSummary(){
        return summary;
    }
    
    public String getAffiche(){
        return affiche;
    }
    
    public void setAffiche(String afficheMovie){
        affiche = afficheMovie;
    }

    public void setMovieId(int id){
        movieId = id;
    }
    
    public void setDuration(int d){
        duration = d;
    }
    
    public void setMovieShowId(int id){
        movieShowId = id;
    }
    
    public void setTitle(String t){
        title = t;
    }
    
    public void setType(String t){
        type = t;
    }
    
    public void setProductor(String p){
        productor = p;
    }
    
    public void setSummary(String s){
        summary = s;
    }
}
