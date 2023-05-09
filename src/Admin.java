package src;
public class Admin {
    private String name;
    private int id;
    private String username;
    private String password;
    private String email;
    public Admin(String name, int id, String username, String password, String email) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void registerUser(){};
    public void registerAdmin(){};
    public void loginUser(){};
    public void loginAdmin(){};
}