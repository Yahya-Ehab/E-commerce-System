/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_assignment3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class AppDB {
    static Connection conn = null;
    static Statement stmt = null;
    static String url = "jdbc:sqlite:AppDB.db";
    
    public static void createItemTable() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            
            stmt = conn.createStatement();
            String sql = "CREATE TABLE Item " +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " NAME TEXT NOT NULL, " + 
                        " QUANTITY INT NOT NULL, " + 
                        " DESCRIPTION TEXT, " + 
                        " CATEGORY TEXT, " +
                        " PRICE REAL);"; 
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void insertItem(Item item) {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO Item(NAME, QUANTITY, DESCRIPTION, PRICE)" + 
                    " VALUES(" + item.getName() + ", " + item.getQuantity() + ", " +
                    item.getDescription() + ", " + item.getPrice() + ");"; 
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static List<Item> getItemsByNameAndCategory(String name, String category){
        try {
            List<Item> items = new ArrayList<Item>();
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            
            stmt = conn.createStatement();
            String sql = "SELECT NAME, DESCRIPTION, PRICE" +
                    " FROM Item WHERE(NAME = " + name + " and CATEGORY = " + category + ");"; 
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                Item i = new Item();
                
                i.setName(rs.getString(0));
                i.setDescription(rs.getString(1));
                i.setPrice(rs.getInt(2));
                
                items.add(i);
            }
            rs.close();
            stmt.close();
            conn.close();
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static List<Item> getItemsByCategory(String category){
        try {
            List<Item> items = new ArrayList<Item>();
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            
            stmt = conn.createStatement();
            String sql = "SELECT NAME, DESCRIPTION, CATEGORY, PRICE" +
                    " FROM Item WHERE(CATEGORY = " + category + ");"; 
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                Item i = new Item();
                
                i.setName(rs.getString(0));
                i.setDescription(rs.getString(1));
                i.setCategory(rs.getString(2));
                i.setPrice(rs.getInt(3));
                
                items.add(i);
            }
            rs.close();
            stmt.close();
            conn.close();
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void deleteItem(Item item) {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO Item(NAME, QUANTITY, DESCRIPTION, PRICE)" + 
                    " VALUES(" + item.getName() + ", " + item.getQuantity() + ", " +
                    item.getDescription() + ", " + item.getPrice() + ");"; 
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
