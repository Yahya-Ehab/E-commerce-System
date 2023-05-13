package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class StockDB {

    private static Connection connect;
    public StockDB(){
        connect = connect();
    }
     /**
     * Connect to a sample database
     */
     public static void createNewTable(){
        String url = "jdbc:sqlite:./StockDB.db";
        String sql = "CREATE TABLE IF NOT EXISTS STOCK (id integer PRIMARY KEY, name text NOT NULL, capacity real, price double) ";
         try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:./StockDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void selectAll(){
         String sql = "SELECT id, name, capacity, price FROM STOCK";

        try (Statement stmt  = connect.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity") + "\t" +
                                   rs.getDouble("price"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert(String name, double capacity, double price) {
        String sql = "INSERT INTO STOCK(name,capacity,price) VALUES(?,?,?)";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
             pstmt.setDouble(3, price);
            pstmt.executeUpdate();
            System.out.println("Inserting " + name + " " + capacity + " " + price);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     public void delete(int id) {
        String sql = "DELETE FROM STOCK WHERE id = ?";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StockDB app = new StockDB();
        app.createNewTable();
//         insert three new rows
        app.insert("Raw Materials", 3000, 10) ;
        app.insert("Semifinished Goods", 4000, 20);
        app.insert("Finished Goods", 5000, 30);
//        app.delete(3);
        app.selectAll();
    }
}