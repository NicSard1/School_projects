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
public class Ticket {
    final static public double JUNIOR_PRICE = 5.99;
    final static public double ADULT_PRICE = 7.99;
    final static public double SENIOR_PRICE = 6.99;
    

    private int ticketId;
    private int customerId;
    private int movieId;
    private int purchaseId;
    private double price;
    
    
    public Ticket(){
        
    }
    
    public Ticket(int cusId, int movId, int purchId, double p ){
        customerId = cusId;
        movieId = movId;
        purchaseId = purchId;
        price = p;
    }

    public void setTicketID(int tID) {
        ticketId = tID;
    }
    
    public void setCustomerID(int cID) {
        customerId = cID;
    }
    
    public void setMovieID(int mID) {
        movieId = mID;
    }
    
    public void setPurchaseID(int pID) {
        purchaseId = pID;
    }
    
    public void setPrice(double p){
        price = p;
    }

    public int getTicketId() {
        return ticketId;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public int getMovieId() {
        return movieId;
    }
    
    public int getPurchaseId() {
        return purchaseId;
    }
    
    public double getPrice() {
        return price;
    }

    
    void addTicket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public String toString(){
        String ticket_display = "";
        
        MovieDaoImpl nMovieDaoImpl = new MovieDaoImpl();
        
        if(price == JUNIOR_PRICE){
            ticket_display+="\nJunior";
        }else if(price == ADULT_PRICE){
            ticket_display+="\nAdult";
        }else{
            ticket_display+="\nSenior";
        }
        ticket_display+=" place for ' "+ nMovieDaoImpl.get_a_movieTitle(movieId) + "'.";
    
        return ticket_display;
    }
}
