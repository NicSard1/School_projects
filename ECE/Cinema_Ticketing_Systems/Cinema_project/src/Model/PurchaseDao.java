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
public interface PurchaseDao {
    public List<Purchase> getAllPurchases();

    public void addPurchase(Purchase purchase);

    public Purchase getPurchase(int purchaseId);

    public void updatePurchase(Purchase purchase);

    public void deletePurchase(int purchaseId);
    
}

