package src;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class OrderDB {
    public ArrayList<order> orders;
    private Connection c;
    void RefreshOrders(){
        orders.clear();
        try{
            // ORDER(orderid int, cartid int, customerID int, status String)
            String SQL = "SELECT orderID, UserID, Status from ORDERDB";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                order p = new order(rs.getInt("orderID"), rs.getInt("CustomerID"), rs.getString("Status"));
                orders.add(p);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    OrderDB(){
        orders = new ArrayList<order>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./OrderDB.db");
        }
        catch (Exception e){
            File f = new File("./OrderDB.db");
            if(!(f.exists())){
                System.out.println("Couldn't create dataBase");
                System.out.println(e.getMessage());
            }
            else{
                System.out.println(e.getMessage());
            }
        }
        try{
            Statement st = c.createStatement();
            //          orderID, CartID, CustomerID, Status
            st.execute("CREATE TABLE IF NOT EXISTS ORDERDB(orderID integer primary key, CustomerID integer,CartID String, Status string)");
        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        RefreshOrders();
    }

    public void AddNewOrders(int orderID,int customerID, String status){
        order pd = new order(orderID, customerID, status);
        boolean flag = true;
        if(this.orders != null){
            for(int i = 0;i<orders.size();i++){
                if(pd.getOrderID() == orders.get(i).getOrderID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                orders.add(pd);
//String SQL = "SELECT orderID, CartID, CustomerID, Status from ORDER";
                String SQL = "INSERT INTO ORDERDB (orderID, CustomerID, Status) VALUES(?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, orderID);
                stt.setInt(2, customerID );
                stt.setString(3, status);
                stt.executeUpdate();
                System.out.println("Order added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a new order since you have an order with the same ID");
        }

    }
    public void DisplayProducts(){
        if(orders.size() == 0){
            System.out.println("Order data base is empty");
            return;
        }
        System.out.printf("%-20s%-20s%-20s%-20s\n", "CustomerID", "OrderID", "CartID", "Status");
        for(int i = 0;i<orders.size();i++){
            System.out.printf("%-20d%-20d%-20s%-20s\n",orders.get(i).getUserID(), orders.get(i).getOrderID(), orders.get(i).getStatus());
        }
    }

    public boolean CheckOrderByID(int orderID){
        boolean flag = false;
        if(this.orders != null){
            for(int i = 0;i<orders.size();i++){
                if(orderID == orders.get(i).getOrderID()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public void deleteOrder(int orderID){
        if(!CheckOrderByID(orderID)){
            System.out.println("Invalid order ID");
            return;
        }
        try{
            // orderID, CartID, CustomerID, Status
            String SQL = "DELETE FROM ORDERDB WHERE orderID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, orderID);
            st.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        RefreshOrders();
    }

public order GetOrder(int orderID){
    order ord;
    if(!CheckOrderByID(orderID)){
        System.out.println("Invalid order ID");
        return null;
    }
    try{
        // orderID, CartID, CustomerID, Status
        String SQL = "SELECT CustomerID, Status FROM ORDERDB WHERE orderID = ?";
        PreparedStatement st = c.prepareStatement(SQL);
        st.setInt(1, orderID);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            int CustID = rs.getInt("CustomerID");
//            int cartID = rs.getInt("CartID");
            String status = rs.getString("Status");
            ord = new order(orderID, CustID, status);
            return ord;
        } else {
            System.out.println("No order found with ID: " + orderID);
            return null;
        }
    }
    catch (Exception e){
        System.out.println(e.getMessage());
        return null;
    }
}

    void CancelOrder(int orderID){
        String SQL = "UPDATE  SET STATUS = CANCELED WHERE ID = ?";
        try{
            PreparedStatement st = c.prepareStatement(SQL);
            st.executeUpdate();
            RefreshOrders();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}


