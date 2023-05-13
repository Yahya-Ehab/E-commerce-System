package src;

import src.Guest;


public class Admin extends Guest {
    private int id;
    private String name;
    private String password;
    private int age;
    private String email;
    private  Stock stock;
    private VoucherDB voucherDB;


    public Admin(int id, String name,String password, int age, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        stock = new Stock();
        voucherDB = new VoucherDB();
    }

    public int getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void editProduct(int id, String attribute, String value){
        stock.UpdateProduct(id,attribute,value);
    }

    public void addProduct(int id, double price, String name, String category, double discount, int size){
        stock.AddNewProduct(id,price,name,category,discount,size);
    }
    public void removeProduct(int id){

        stock.deleteProduct(id);
    }
    public void editVoucher(int id, String attribute, String value){
        voucherDB.UpdateVoucher(id,attribute,value);
    }

    public void addVoucher(int id, double discount){
        voucherDB.addVoucher(id,discount);
    }

    public void removeVoucher(int id){
        voucherDB.removeVoucher(id);
    }
    public void viewVouchers(){
        voucherDB.displayVouchers();
    }

    public void viewStockStatistics(){
        stock.stockStatistics();
    }
}