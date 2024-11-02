/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author dckt2
 */
public class Purchase {
    private int purchaseId;
    private int customerId;
    private double total_price;
    private ArrayList<Ticket> ticket_list = new ArrayList<Ticket>();
    
    
    public void setTicketList(ArrayList<Ticket> nlist){
        ticket_list = nlist;
    }
    
    public void add_ticket_to_purchase(Ticket newTicket){
        ticket_list.add(newTicket);
    }
    
    public void setPurchaseId(int new_id){
        purchaseId = new_id;
    }
    
    public void setCustomerId(int new_id){
        customerId = new_id;
    }
    
    public void setTotalPrice(double new_price){
        total_price = new_price;
    }
    
    public ArrayList<Ticket> getTicketList(){
        return ticket_list;
    }
    
    public int getPurchaseId(){
        return purchaseId;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public double getTotalPrice(){
        return total_price;
    }
    
    @Override
    public String toString(){
        String str = "";
        for(int i=0;i<ticket_list.size();++i){
            str += ticket_list.get(i).toString();
        }
        return str;
    }
}
