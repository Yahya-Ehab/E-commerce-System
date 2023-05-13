package src;

public class product {
    private int size;
    private int id;
    private double price;
    private String name;
    private double Discount;
    private String category;
    product(int id, double productPrice, String Name, String Categ, double discount, int Size){
        this.id = id;
        this.name = Name;
        this.category = Categ;
        this.Discount = discount;
        this.price = productPrice - productPrice*(discount/100);
        this.size = Size;
    }
    public void SetSize(int newSize){
        this.size = newSize;
    }
    public int getSize(){
        return size;
    }
    int getID(){
        return id;
    }
    void setPrice(double price){
        this.price = price - price*(Discount/100);

    }
    double getPrice(){
        return this.price;
    }
    void setName(String Name){
        name = Name;
    }
    String getName(){
        return this.name;
    }
    void setDiscount(double discount){
        this.Discount = discount;
        setPrice(getPrice()/(1-getDiscount()/100));
    }
    double getDiscount(){
        return Discount;
    }
    String getCategory(){
        return this.category;
    }
}