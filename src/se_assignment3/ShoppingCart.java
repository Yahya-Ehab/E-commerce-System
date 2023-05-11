/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_assignment3;

/**
 *
 * @author Home
 */
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart{
    List<Item> itemList;
    float totalPrice;

    public ShoppingCart(){
        itemList = new ArrayList<Item>();
        totalPrice = 0;
    }

    public void addToCart(Item item){
        itemList.add(item);
        totalPrice += item.getPrice();
    }

    public List<Item> getIList(){
        return this.itemList;
    }

    public float getTotalPrice(){
        return totalPrice;
    }

}
