package src;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;


public class Main {

    VoucherDB vouchers;
    UserDB users;
    OrderDB orders;
    AdminDB admins;
    CartDB carts;

    public static String generateOTP(){
        int otpl = 6;
        String numbers = "0123456789";
        Random random = new Random();
        char[] otp = new char[otpl];
        for(int i = 0;i<otpl;i++){
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));

        }
        return new String(otp);
    }
    public String sendOTP(String mail) throws Exception{
        String OPT = generateOTP();
        String from = "yahyaehab1856@gmail.com";
        String password = "zgkzoyyczdlefwzu";
        String host = "smtp.gmail.com";
        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setText("Your OTP is: "+ OPT);
            Transport.send(message);
            System.out.println("Check you email");
        }
        catch (Exception e){
            throw new RuntimeException((e));
        }
        return OPT;
    }

    private Scanner scan = new Scanner(System.in);



public Main() {
      vouchers = new VoucherDB();
        orders = new OrderDB();
        carts = new CartDB();
    UIManager rs = new UIManager();
    users = new UserDB(rs.getInt("id"), rs.getString("Name"), rs.getInt("Age"), rs.getString("email"), rs.getString("password"));
        admins = new AdminDB();
        boolean loop = true;

    while(loop)  {
         Guest guest = new Guest();
         System.out.println("Welcome to our online store");
         System.out.println("1 - View products");
         System.out.println("2 - Search for an item");
         System.out.println("3 - Register");
         System.out.println("4 - Login");
         System.out.println("5 - Exit");
         System.out.println("Enter your choice: ");

         int choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
                 case 1: {
                    guest.viewProducts();
                    break;
                }
                case 2: {
                    System.out.print("Please Enter the name of the item you want to search for: ");
                    String p = scan.nextLine();
                    guest.searchItem(p);
                    break;
                }
                case 3: {
                    register();
                    break;
                }
                case 4: {
                    login();
                    break;
                }
                case 5:{
                    System.out.println("Goodbye, come again!");
                    loop = false;
                    break;
                }
                default:{
                    System.out.println("Invalid option please enter a valid option");
                    break;
                }
        }

    }
}


        void register(){
        System.out.println("Choose which account do you want to create:");
        System.out.println("1 - User");
        System.out.println("2 - Admin");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice){
            case 1:{
                System.out.println("Please Enter Customer Data");
                System.out.print("ID: ");
                int id = Integer.parseInt(scan.nextLine());
                System.out.print("Name: ");
                String name = scan.nextLine();
                System.out.print("Password: ");
                String pass = scan.nextLine();
                System.out.print("Age: ");
                int age = Integer.parseInt(scan.nextLine());
                String gen = scan.nextLine();
                System.out.print("Email: ");
                String Mail = scan.nextLine();

                try{
                    String OTP = sendOTP(Mail);
                    System.out.printf("Welcome %s, an OTP message was sent to your account\n", name);
                    System.out.printf("Please write the OTP number: ");
                    String otp = scan.nextLine();
                    if(otp.equalsIgnoreCase(OTP)){
                        System.out.println("Email was confirmed Successfully");
                        users.addNewUser(id, name, age, Mail, pass);
                    }
                    else{
                        System.out.println("Wrong OTP");
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2:{


                System.out.println("To create New Admin please Enter owner password:");
                String Adminpassword = scan.nextLine();
                if(Adminpassword.equalsIgnoreCase("12345")){
                    System.out.println("The password is correct");
                    System.out.println("Please Enter Admin Data");
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.print("Name: ");
                    String name = scan.nextLine();
                    System.out.print("Password: ");
                    String pass = scan.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scan.nextLine());
                    String gen = scan.nextLine();
                    System.out.print("Email: ");
                    String Mail = scan.nextLine();

                    try{
                        String OTP = sendOTP(Mail);
                        System.out.printf("Welcome %s, an OTP message was sent to your account\n", name);
                        System.out.printf("Please write the OTP number: ");
                        String otp = scan.nextLine();
                        if(otp.equalsIgnoreCase(OTP)){
                            System.out.println("Email was confirmed Successfully");
                            admins.addNewAdmin(id, name, pass, age, Mail);
                        }
                        else{
                            System.out.println("Wrong OTP");
                            TimeUnit.SECONDS.sleep(2);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }
                else{
                    try {
                        System.out.println("Wrong Owner Password");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }
                break;
            }
        }

    }

    void login (){
        System.out.println("Log-IN\n\n\n");
        System.out.println("Choose which Interface do you want to login into as:");
        System.out.println("1 - User");
        System.out.println("2 - Admin");
        System.out.print("-->");
        int choice = Integer.parseInt(scan.nextLine());
        if(choice == 1){
            System.out.println("Please Enter User username and password");
            System.out.print("UserName: ");
            String userName = scan.nextLine();
            if(users.getUserByName(userName) == null){
                System.out.println("No users with such name exists");
            }
            else{
                System.out.print("Password: ");
                String pass = scan.nextLine();
                if(users.getUserByName(userName).getPassword().equals(pass)){
                    userInterface(users.getUserByName(userName));
                }
                else{
                    System.out.println("Wrong password");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        else{
            System.out.println("Please Enter Admin username and password");
            System.out.print("UserName: ");
            String userName = scan.nextLine();
            if(admins.getAdmin(userName) == null){
                System.out.println("No Admins with such name exists");
            }
            else{
                System.out.print("Password: ");
                String pass = scan.nextLine();
                if(admins.getAdmin(userName).getPassword().equals(pass)){
                    adminInterface(admins.getAdmin(userName));
                }
                else{
                    System.out.println("Wrong password");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

     public void adminInterface(Admin admin){
        while (true) {
            System.out.println("Admin\n\n");
            System.out.println("Choose One Of The Following Options\n");
            System.out.println("1 - View Products");
            System.out.println("2 - Search For Product");
            System.out.println("3 - Edit Product");
            System.out.println("4 - Add Product");
            System.out.println("5 - Remove Product");
            System.out.println("6 - dit Voucher");
            System.out.println("7 - Add Voucher");
            System.out.println("8 - Remove Voucher");
            System.out.println("9 - View Vouchers");
            System.out.println("10 - View Statistics");
            System.out.println("11 - Log Out");
            System.out.println("12 - Exit");

            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("View Products\n");
                    admin.viewProducts();
                    break;
                }
                case 2: {
                    System.out.println("Search For Product\n");
                    System.out.print("\nEnter Product Name: ");
                    String name = scan.nextLine();
                    admin.searchItem(name);
                    break;
                }
                case 3: {
                    System.out.println("Edit Product\n");

                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Attribute to change (Name, Price, Size, Category, Discount, Size): ");
                    String attribute = scan.nextLine();

                    System.out.print("\nEnter The New Value: ");
                    String value = scan.nextLine();

                    admin.editProduct(id, attribute, value);
                    break;
                }
                case 4: {
                    System.out.println("Add Product\n");
                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Product Name: ");
                    String name = scan.nextLine();

                    System.out.print("\nEnter Product Price: ");
                    double price = Double.parseDouble(scan.nextLine());

                    System.out.print("\nEnter Product Discount: ");
                    double discount = Double.parseDouble(scan.nextLine());

                    System.out.print("\nEnter Product Category: ");
                    String category = scan.nextLine();

                    System.out.print("\nEnter Product Size: ");
                    int size = Integer.parseInt(scan.nextLine());

                    admin.addProduct(id, price, name, category, discount, size);
                    break;
                }
                case 5: {
                    System.out.println("Remove Product\n");
                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());
                    admin.removeProduct(id);
                    break;
                }
                case 6: {
                    System.out.println("------Edit Voucher------\n");
                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.print("\nEnter Attribute to change (Discount)");
                    String attribute = scan.nextLine();
                    System.out.print("\nEnter The New Value: ");
                    String value = scan.nextLine();
                    admin.editVoucher(id, attribute, value);
                    break;
                }
                case 7: {
                    System.out.println("------Add Voucher------\n");
                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Discount Amount: ");
                    double discount = Double.parseDouble(scan.nextLine());

                    admin.addVoucher(id, discount);
                    break;
                }
                case 8: {
                    System.out.println("Remove Voucher\n");

                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    admin.removeVoucher(id);
                    break;
                }
                case 9:{
                    System.out.println("View Vouchers\n");

                    admin.viewVouchers();
                    break;
                }
                case 10: {
                    System.out.println("View Statistics\n");

                    System.out.println("1 - Display users");
                    System.out.println("2 - Stock Statistics");
                    System.out.print("Enter Your Choice:");
                    int n = Integer.parseInt(scan.nextLine());
                    switch (n) {
                        case 1:
                            users.Displayuser();
                            break;
                        case 2:
                            admin.viewStockStatistics();
                            break;
                    }
                    break;
                }
                case 11: {
                    return;
                }
                case 12: {
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Error: Invalid Input Please Try Again");
                    break;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

     public void userInterface(User user){
        ShoppingCart Cart = new ShoppingCart();
        boolean lo = true;
        while (lo){
            System.out.println("User Interface\n\n");
            System.out.println("Please Choose one of our available options:");
            System.out.println("1 - View Stock");
            System.out.println("2 - Search for a product");
            System.out.println("3 - Add to cart");
            System.out.println("4 - Remove from cart");
            System.out.println("5 - View cart items");
            System.out.println("6 - Reorder");
            System.out.println("7 - Cancel order");
            System.out.println("8 - View orders");
            System.out.println("9 - View order products");
            System.out.println("10 - Checkout");
            System.out.println("11 - Logout");
            System.out.println("12 - Exit");
            System.out.print("Enter Your Choice:");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice){
                case 1:{
                    user.viewProducts();
                    break;
                }
                case 2:{
                    System.out.println("Please Enter the name of the product you are looking for");
                    String Pname = scan.nextLine();
                    user.searchItem(Pname);
                    break;
                }
                case 3:{
//                public void AddToCart(String productName, int amount){
                    System.out.println("Please Enter the name and amount of the product you are searching for");
                    System.out.print("Enter product Name: ");
                    String Pname = scan.nextLine();
                    System.out.print("Enter product amount: ");
                    int amount = Integer.parseInt(scan.nextLine());
                    Cart.AddToCart(Pname, amount);
                    break;
                }
                case 4:{
//                    (String productName, int amount){
                    System.out.println("Please enter the name and amount of the product");
                    System.out.print("Enter product name:");
                    String Pname = scan.nextLine();
                    System.out.print("Enter product amount: ");
                    int amount = Integer.parseInt(scan.nextLine());
                    Cart.RemoveFromCart(Pname, amount);
                    break;
                }
                case 5:{
                    Cart.viewCart();
                    break;
                }
                case 6:{
                    //ReOrder
                    System.out.println("Please Choose order ID");
                    int Order = Integer.parseInt(scan.nextLine());
                    if(orders.CheckOrderByID(Order)){
                        order Ord = orders.GetOrder(Order);
                        Cart = carts.reOrder(user.getID(), Ord.getOrderID());
                    }
                    break;
                }
                case 7:{
                    System.out.println("Enter ID of the order you want to cancel");
                    System.out.print("OrderID: ");
                    int orderID = Integer.parseInt(scan.nextLine());
                    orders.CancelOrder(orderID);
                    break;
                }
                case 8:{
//                    order(int orderID,int customerID, String status){
                    orders.DisplayProducts();
                    break;
                }
                case 9:{
                    System.out.println("Please specify the order you want to see: ");
                    System.out.println("Order ID: ");
                    int OrderID = Integer.parseInt(scan.nextLine());
                    carts.DisplayuserCart(user.getID(), OrderID);
                    break;
                }
                case 10:{
                    System.out.println("Do you want to add a voucher");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    System.out.println("-->");

                    int ch = Integer.parseInt(scan.nextLine());
                    double TP = Cart.getTotalPrice();
                    switch (ch){
                        case 1:{
                            System.out.print("Enter the voucher ID: ");
                            int VID = Integer.parseInt(scan.nextLine());
                            double DISCOUNT = vouchers.applyVoucher(VID);
                            if(DISCOUNT == 0.0){
                                System.out.println("Incorrect voucher lol");
                            }
                            else{
                                TP = TP - TP*(DISCOUNT/100);
                            }
                            break;
                        }
                        case 2:{
                            System.out.println("ok");
                            break;
                        }
                    }
                    order newOrder = new order(orders.orders.size(), user.getID(), "Pending");
                    orders.AddNewOrders(newOrder.getOrderID(), newOrder.getUserID(), newOrder.getStatus());
                    carts.AddNewCart(Cart, user.getID(), newOrder.getOrderID());
                    System.out.println("Order has been made");
                    int ItemSize = 0;
                    for (int i = 0;i<Cart.cart.size();i++){
                        System.out.printf("%s\n", Cart.cart.get(i).getName());
                        ItemSize += Cart.cart.get(i).getSize();
                        Stock ss = new Stock();
                        ss.DecrementProductSize(Cart.cart.get(i).getID(), Cart.cart.get(i).getSize());
                    }
                    System.out.printf("Total price = %f\n", TP);
                    users.DeleteUser(user.getID());
                    users.addNewUser(user.getID(), user.getName(), user.getAge(), user.getEmail(),user.getPassword());
                    break;
                }
                case 11:{
                    return;
                }
                case  12:{
                    System.exit(0);
                }
            }

        }

    }








public static void main(String[] args)  {
        Main main = new Main();
    }
}