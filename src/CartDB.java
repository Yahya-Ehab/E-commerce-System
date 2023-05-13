package src;

import java.io.File;
import java.sql.*;

public class CartDB {
    private Connection c;
    CartDB(){
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./CartDB.db");


        }
        catch (Exception e){
            File f = new File("./CartDB.db");
            if(!(f.exists())){
                System.out.println("Couldn't create dataBase");
                System.out.println(e.getMessage());
            }
            else{
                System.out.println(e.getMessage());
            }
        }

    }
    public void AddNewCart(ShoppingCart ct, int userID, int cartID){
        String Tablee = "C" +Integer.toString(userID)+"_"+Integer.toString(cartID);
        try{
            Statement st = c.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS "+Tablee+"(ID integer primary key, name string, price Double, discount Double, category String, amount String )");

        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
            try {
                for(int i = 0;i<ct.cart.size();i++){
                    String SQL = "INSERT INTO " + Tablee + "(ID, name, price, discount, category, amount) VALUES(?, ?, ?, ?, ?, ?)";
                    PreparedStatement stt = c.prepareStatement(SQL);
                    stt.setInt(1, ct.cart.get(i).getID());
                    stt.setString(2, ct.cart.get(i).getName());
                    stt.setDouble(3, ct.cart.get(i).getPrice());
                    stt.setDouble(4, ct.cart.get(i).getDiscount());
                    stt.setString(5, ct.cart.get(i).getCategory());
                    stt.setInt(6, ct.cart.get(i).getSize());
                    stt.executeUpdate();
//                    System.out.println("product added successfully");
                }
//                String SQL = "INSERT INTO STOCK (ID, name, price, discount, category) VALUES("+id+","+Name+","+productPrice+","+Discount+","+Categ+")";
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }

    }
    public void DisplayuserCart(int userID, int cartID){
        System.out.printf("Name            amount\n");
        String V = "C"+Integer.toString(userID)+"_"+Integer.toString(cartID);
        String SQL = "SELECT name, amount from "+V;
        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                System.out.printf("%-20s%-20d\n", rs.getString("name"), rs.getInt("amount"));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    ShoppingCart reOrder(int userID, int OrderID){
        ShoppingCart sc = new ShoppingCart();
        String V = "C"+Integer.toString(userID)+"_"+Integer.toString(OrderID);
        String SQL = "SELECT name, amount from "+V;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                sc.AddToCart(rs.getString("name"), rs.getInt("amount"));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sc;
    }
}