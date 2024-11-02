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
public interface TicketDao {
    
    public List<Ticket> getAllTickets();

    public void addTicket(Ticket ticket);

    public Ticket getTicket(int ticketId);

    public void updateTicket(Ticket ticket);

    public void deleteTicket(int ticketId);
}
