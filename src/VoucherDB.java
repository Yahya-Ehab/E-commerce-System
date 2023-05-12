package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class VoucherDB {

    private static Connection connect;
    public VoucherDB(){
         connect = connect();
    }
     /**
     * Connect to a sample database
     */
     public static void createNewTable(){
        String url = "jdbc:sqlite:./VoucherDB.db";
        String sql = "CREATE TABLE IF NOT EXISTS VOUCHER (id integer PRIMARY KEY, name text NOT NULL, discount double) ";
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
        String url = "jdbc:sqlite:./VoucherDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void selectAll(){
         String sql = "SELECT id, name, discount FROM VOUCHER";

        try (Statement stmt  = connect.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("discount"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insert(String name, double discount) {
        String sql = "INSERT INTO VOUCHER(name,discount) VALUES(?,?)";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, discount);
            pstmt.executeUpdate();
            System.out.println("Inserting " + name + " " + discount + " into VoucherDB");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     public void delete(int id) {
        String sql = "DELETE FROM VOUCHER WHERE id = ?";

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
        VoucherDB app = new VoucherDB();
        app.createNewTable();
//         insert three new rows
        app.insert("Raw Materials", 0.2) ;
        app.insert("Semifinished Goods", 0.3);
        app.insert("Finished Goods", 0.4);
//        app.delete(3);
        app.selectAll();
    }
}