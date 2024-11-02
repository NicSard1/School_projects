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
public class MovieShow {
    private int movieShowId;
    private int movieId;
    private int availablePlaces;
    private int seatsSold;
    private String date;
    private String hour;
    private int roomNumber;
    
    
    public void setMovieShowId(int id){
        movieShowId = id;
    }
    
    public void setMovieId(int id){
        movieId = id;
    }
    
    public void setAvailablePlaces(int p){
        availablePlaces = p;
    }
    
    public void setSeatsSold(int sold){
        seatsSold = sold;
    }
    
    public void setDate(String d){
        date = d;
    }
    
    public void setHour(String h){
        hour = h;
    }
    
    public void setRoomNumber(int nb){
        roomNumber = nb;
    }
    
  
    
    
    public int getMovieShowId(){
        return movieShowId;
    }
    
    public int getMovieId(){
        return movieId;
    }
    
    public int getAvailablePlaces(){
        return availablePlaces;
    }
    
    public int getSeatsSold(){
        return seatsSold;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getHour(){
        return hour;
    }
    
    public int getRoomNumber(){
        return roomNumber;
    }    
}
