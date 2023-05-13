package src;

import src.Guest;

public class User extends Guest {
    private int UserID;
    private String name;
    private int age;
    private String email;
    private String password;
    User(int ID, String Name, int Age, String Email, String password){
        UserID = ID;
        this.name = Name;
        this.age = Age;
        this.email = Email;
        this.password = password;
    }
    int getID(){
        return this.UserID;
    }
    void setName(String Name){
        this.name = Name;
    }
    String getName(){
        return this.name;
    }
    void setAge(int Age){
        this.age = Age;
    }
    int getAge(){
        return this.age;
    }
    void updateEmail(String Email){
        this.email = Email;
    }
    String getEmail(){
        return this.email;
    }
    void setPassword(String password){
        this.password = password;
    }
    String getPassword(){
        return this.password;
    }

}
