package src;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class UserDB {
    private ArrayList<User> user;
    private Connection c;
    UserDB(int id, String name, int age, String email, String password){
        user = new ArrayList<User>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./UserDB.db");
        }
        catch (Exception e){
            File f = new File("./UserDB.db");
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
                                                            // User(, , , , , , , ){
            st.execute("CREATE TABLE IF NOT EXISTS USERS(ID integer primary key, name string,Password String, age INTEGER,email String)");

        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        try{
            String SQL = "SELECT ID, name, Password, age, email from USERS";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                //User(int ID, String Name, int Age, String G, String , String Email, int loyalityPoints, String password){
                User u = new User(rs.getInt("id"), rs.getString("Name"), rs.getInt("Age"), rs.getString("email"), rs.getString("password"));
                user.add(u);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void refreshUsers(){
        user.clear();
        try{
            String SQL = "SELECT ID, name, Password, age, email, from USERS";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                User u = new User(rs.getInt("id"), rs.getString("Name"), rs.getInt("Age"), rs.getString("email"), rs.getString("password"));
                user.add(u);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addNewUser(int ID, String Name, int Age, String Email, String password){
        User u = new User(ID, Name, Age,Email, password);
        boolean flag = true;
        if(this.user != null){
            for(int i = 0;i<user.size();i++){
                if(u.getID() == user.get(i).getID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                user.add(u);
//                int ID, String Name, int Age, String G, String , String Email, int loyalityPoints, String password){
//                SELECT ID, name, Password, age, , , email, loyaltyPoints from UsersDB";
                String SQL = "INSERT INTO USERS (ID, name, Password, age, email) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, ID);
                stt.setString(2, Name);
                stt.setString(3, password);
                stt.setString(4, Email);

                stt.executeUpdate();
//                System.out.println("User added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a User since you have a User with the same ID");
        }
    }
    public void Displayuser(){
        if(user.size() == 0){
            System.out.println("There is no user");
            return;
        }
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Age", "", "", "Email", "Loyalty points");
        for(int i = 0;i<user.size();i++){
            System.out.printf("%-20d%-20s%-20d%-20s%-20s%-20s%-20s\n", user.get(i).getID(), user.get(i).getName(), user.get(i).getAge(), user.get(i).getEmail());
        }
    }
    public boolean checkUserByID(int AdminID){
        boolean flag = false;
        if(this.user != null){
            for(int i = 0;i<user.size();i++){
                if(AdminID == user.get(i).getID()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean checkUserByName(String name){
        boolean flag = false;
        if(this.user != null){
            for(int i = 0;i<user.size();i++){
                if(name.equalsIgnoreCase(user.get(i).getName())){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public User getUserByName(String name){
        if(!checkUserByName(name)){
            System.out.println("Admin of Name "+ name + " does not exist");
        }
        for(int i = 0;i<user.size();i++){
            if(name.equalsIgnoreCase(user.get(i).getName())){
                return user.get(i);
            }
        }
        return null;
    }

    public void DeleteUser(int adminId){
        if(!checkUserByID(adminId)){
            System.out.println("Invalid Admin ID");
            return;
        }
        try{
            String SQL = "DELETE FROM USERS WHERE ID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, adminId);
            st.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        refreshUsers();
    }
}
