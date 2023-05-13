package src;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




public class Main {
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
//    public String sendOTP(String mail)throws Exception{
//        String OPT = generateOTP();
//        String from = "yahyaehab1856@gmail.com";
//        String password = "zgkzoyyczdlefwzu";
//        String host = "smtp.gmail.com";
//        Properties props = new Properties();
//
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
////        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
////            protected  PasswordAuthentication getPasswordAuthentication(){
////                return new PasswordAuthentication(from, password);
////            }
////        });
////
////        try{
////            Message message = new MimeMessage(session);
////            message.setFrom(new InternetAddress(from));
////            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
////            message.setText("Your OTP is: "+ OPT);
////            Transport.send(message);
////            System.out.println("Check you email");
////        }
//        catch (Exception e){
//            throw new RuntimeException((e));
//        }
//        return OPT;
//    }

    public static void userInterface(User user){
    System.out.println("Welcome to the Ecommerce System");
    System.out.println("1. Login");
    System.out.println("2. Register");
    System.out.println("3. Add to cart");
    System.out.println("4. View cart");
    System.out.println("5. Remove from cart");
    System.out.println("6. Checkout");
    System.out.println("7. View orders");
    System.out.println("8. Logout");
    System.out.println("9. Exit");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    switch(choice){
        case 1:
            user.loginUser(user);
            break;
        case 2:
            user.registerUser(user);
            break;
        case 3:
            user.addToCart(user);
            break;
        case 4:
            user.viewCart(user);
            break;
        case 5:
            user.removeFromCart(user);
            break;
        case 6:
            user.checkoutUser(user);
            break;
        case 7:
            user.viewOrders(user);
            break;
        case 8:
            user.logoutUser(user);
            break;
        case 9:
            break;
        default:
            System.out.println("Invalid choice");
            break;
    }
}

public static void main(String[] args) {
        Admin admin = new Admin();
        User user = new User();
        userInterface(user);
}

}