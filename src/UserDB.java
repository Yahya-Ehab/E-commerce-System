package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserDB {

    private static Connection connect;
    public UserDB(){
        connect = connect();
    }
     /**
     * Connect to a sample database
     */
     public static void createNewTable(){
        String url = "jdbc:sqlite:./UserDB.db";
        String sql = "CREATE TABLE IF NOT EXISTS USER (id integer PRIMARY KEY, name text NOT NULL, username text NOT NULL , email text NOT NULL ) ";
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
        String url = "jdbc:sqlite:./UserDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void selectAll(){
         String sql = "SELECT id, name, username, email FROM USER";

        try (Statement stmt  = connect.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("username") + "\t" +
                                   rs.getDouble("email"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       public void insertUser(int id, String name, String username, String password, String email) {
           String sql = "INSERT INTO USER(id, name, username, password, email) VALUES(?, ?, ?, ?, ?)";

           try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
               pstmt.setInt(1, id);
               pstmt.setString(2, name);
               pstmt.setString(3, username);
               pstmt.setString(4, email);
               pstmt.setString(5, password);
               pstmt.executeUpdate();
               System.out.println("User inserted into the database");
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }


     public void delete(int id) {
        String sql = "DELETE FROM USER WHERE id = ?";

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
        UserDB app = new UserDB();
        app.createNewTable();
//         insert three new rows
//        app.delete(3);
        app.selectAll();
    }
}