package src;

public class User {

    private String name;
    private int id;
    private String username;
    private String password;
    private String email;
    private StockDB stockDB;
    private Order order;
    private UserDB userDB;

    public User() {
       this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.stockDB = new StockDB();
        this.order = new Order();
        this.userDB = new UserDB();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void registerUser(User user) {
        userDB.insertUser(id, name, username, password, email);
        System.out.println("User registration logic");
    }

    public void loginUser(User user) {
        System.out.println("User login logic");
    }

    public void logoutUser(User user) {
        System.out.println("User logout logic");
    }

    public void checkoutUser(User user) {
        System.out.println("User checkout logic");
    }

    public void addToCart(User user) {
        StockDB stockDB = new StockDB();
        VoucherDB voucherDB = new VoucherDB();
        System.out.println("Add to cart logic");
    }

    public void viewCart(User user) {
        StockDB stockDB = new StockDB();
        VoucherDB voucherDB = new VoucherDB();
        System.out.println("View cart logic");
    }

    public void removeFromCart(User user) {
        StockDB stockDB = new StockDB();
        VoucherDB voucherDB = new VoucherDB();
        System.out.println("Remove from cart logic");
    }

    public void viewOrders(User user) {
        StockDB stockDB = new StockDB();
        VoucherDB voucherDB = new VoucherDB();
        System.out.println("View orders logic");
    }
}
