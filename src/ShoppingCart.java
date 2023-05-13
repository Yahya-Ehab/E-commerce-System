package src;

import java.util.ArrayList;
public class ShoppingCart {
    public ArrayList<product> cart;
    private Stock st;

    ShoppingCart(){

        cart = new ArrayList<product>();
        st = new Stock();
    }
    public boolean isEmpty(){
        return cart.isEmpty();
    }
    public void AddToCart(String productName, int amount){
        if(CheckItem(productName)){
            for(int i = 0;i<cart.size();i++){
                if(cart.get(i).getName().equalsIgnoreCase(productName)) {
                    product p = st.getProduct(productName);
                    if(cart.get(i).getSize()+amount>p.getSize()){
                        System.out.println("Sorry, no enough amount; please specify a smaller amount for this product.");
                    }
                    else{
                        cart.get(i).SetSize(cart.get(i).getSize()+amount);
                        System.out.print("Item amount increased to become " + cart.get(i).getSize() + " successfully");
                    }
                }
            }
            return;
        }
        if(st.CheckProductByName(productName)){
            product p = st.getProduct(productName);
            if(amount > p.getSize()){
                System.out.println("Sorry, no enough amount; please specify a smaller amount for this product.");
                return;
            }
            else{
                System.out.println(amount+" " + productName+" got added to your cart successfully");
                product item = new product(p.getID(), p.getPrice()/(1-p.getDiscount()/100), p.getName(), p.getCategory(), p.getDiscount(), amount);
                cart.add(item);
            }

        }
    }
    public boolean CheckItem(String productName){
        boolean flag = false;
        for(int i = 0;i<cart.size();i++){
            if(cart.get(i).getName().equalsIgnoreCase(productName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public void RemoveFromCart(String productName, int amount){
        for(int i = 0;i<cart.size();i++){
            if(cart.get(i).getName().equalsIgnoreCase(productName)){
                if(amount > cart.get(i).getSize()){
                    System.out.println("Error.... be bo peep you tried to remove too many items please try to delete smaller amount or an equivalent amount to what you have in your cart");
                    return;
                }
                else if(amount == cart.get(i).getSize()){
                    cart.remove(i);
                    System.out.println("Product got removed out of your product");
                }
                else{
                    cart.get(i).SetSize(cart.get(i).getSize()- amount);
                    System.out.println("The amount got removed from your product now you have " + cart.get(i).getSize() + " from this product in your cart");
                }
            }
        }
    }
    public double getTotalPrice(){
        double sum = 0;
        for(int i = 0;i<cart.size();i++){
             sum += cart.get(i).getPrice()*(cart.get(i).getSize());
        }
        return sum;
    }
    public void viewCart(){
        if(cart.size() == 0){
            System.out.println("Stock is empty");
            return;
        }
        System.out.printf("ID                  Name                Price               Discount            Category            Size\n");
        for(int i = 0;i<cart.size();i++){
            System.out.printf("%-20d%-20s%-20.2f%-20.2f%-20s%-20d\n", cart.get(i).getID(), cart.get(i).getName(), cart.get(i).getPrice(), cart.get(i).getDiscount(), cart.get(i).getCategory(), cart.get(i).getSize());
        }
    }
}

