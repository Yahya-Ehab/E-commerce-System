package src;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
//trr
public class AdminDB {
    private ArrayList<Admin> admins;
    private Connection c;
    AdminDB(){
        admins = new ArrayList<Admin>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./AdminDB.db");


        }
        catch (Exception e){
            File f = new File("./Admin.db");
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
            st.execute("CREATE TABLE IF NOT EXISTS Admin(ID integer primary key, name string,Password String, age INTEGER, gender String, address String, email String )");

        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        try{
            String SQL = "SELECT ID, name, Password, age, gender, address, email from Admin";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Admin a = new Admin(rs.getInt("ID"), rs.getString("name"),rs.getString("Password"), rs.getInt("age"), rs.getString("email"));
                admins.add(a);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void refreshAdmins(){
        admins.clear();
        try{
            String SQL = "SELECT ID, name, Password, age, gender, address, email from Admin";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Admin a = new Admin(rs.getInt("ID"), rs.getString("name"),rs.getString("Password"), rs.getInt("age"), rs.getString("email"));
                admins.add(a);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addNewAdmin(int id, String Name,String password, int age, String email){
        Admin a = new Admin(id, Name, password,age, email);
        boolean flag = true;
        if(this.admins != null){
            for(int i = 0;i<admins.size();i++){
                if(a.getId() == admins.get(i).getId()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                admins.add(a);

                String SQL = "INSERT INTO Admin (ID, name,password, age, email) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, id);
                stt.setString(2, Name);
                stt.setString(3, password);
                stt.setInt(4, age);
                stt.setString(5, email);
                stt.executeUpdate();
                System.out.println("Admin added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a admin since you have an admin with the same ID");
        }
    }
    public void DisplayAdmins(){
        if(admins.size() == 0){
            System.out.println("There is no Admins");
            return;
        }
        System.out.printf("ID                  Name                  Email\n");
        for(int i = 0;i<admins.size();i++){
            System.out.printf("%-20d%-20s%-20d%-20s%-20s%-20s\n", admins.get(i).getId(), admins.get(i).getName(), admins.get(i).getAge(), admins.get(i).getEmail());
        }
    }
    public boolean CheckAdminByID(int AdminID){
        boolean flag = false;
        if(this.admins != null){
            for(int i = 0;i<admins.size();i++){
                if(AdminID == admins.get(i).getId()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean CheckAdminByName(String name){
        boolean flag = false;
        if(this.admins != null){
            for(int i = 0;i<admins.size();i++){
                if(name.equalsIgnoreCase(admins.get(i).getName())){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public Admin getAdmin(String name){
        if(!CheckAdminByName(name)){
            System.out.println("Admin of Name "+ name + " does not exist");
        }
        for(int i = 0;i<admins.size();i++){
            if(name.equalsIgnoreCase(admins.get(i).getName())){
                return admins.get(i);
            }
        }
        return null;
    }

    public void deleteAdmin(int adminId){
        if(!CheckAdminByID(adminId)){
            System.out.println("Invalid Admin ID");
            return;
        }
        try{
            String SQL = "DELETE FROM Admin WHERE ID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, adminId);
            st.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        refreshAdmins();
    }
}
