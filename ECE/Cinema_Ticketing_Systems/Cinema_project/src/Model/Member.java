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
public class Member extends Customer{
    private String password,email;
    private double discount; // in percent
    private int payment_infos;
    private int loyalty_points;
    private Purchase basket_purchase = new Purchase(); //tickets in the basket that are not paid yet
    
    public void set_basket_purchase(Purchase purchase){
        basket_purchase = purchase;
    }

    public Purchase getbasket_purchase(){
        return basket_purchase;
    }
    
    public void clear_basket(){
        basket_purchase = new Purchase();
        
    }
    
    public void setPassword(String new_password){
        password = new_password;
    }
    
    public String get_password(){
        return password;
    }
    
    public void set_discount(double new_discount){
        discount = new_discount;
    }
    
    public double get_discount(){
        return discount;
    }
    
    public void set_payment_infos(int new_payment_infos){
        payment_infos = new_payment_infos;
    }
    
    public int get_payment_infos(){
        return payment_infos;
    }
    
    public void set_loyalty_points(int pts){
        loyalty_points = pts;
    }
    
    public int get_loyalty_points(){
        return loyalty_points;
    }
    
    public void setEmail(String newEmail) {
        email=newEmail;
    }
    public String getEmail(){
        return email;
    }
    
    public void addMember() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString(){
        String member_str = "";
        member_str += "Customer ID : " + this.getId();
        member_str += "\nName : " + this.getName();
        member_str += "\nSurname : " + this.getSurname();
        member_str += "\nAge : " + this.getAge();
        member_str += "\nEmail : " + this.getEmail();
        //member_str += "\nPassword : " + this.get_password();
        member_str += "\nPayment information : " + this.get_payment_infos();
        member_str += "\nLoyalty points : " + this.get_loyalty_points();
        member_str += "\nDiscount : " + this.get_discount();

        return member_str;
    }
}
