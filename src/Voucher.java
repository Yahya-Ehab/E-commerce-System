package src;

public class Voucher {
    int id;
    double discountAmount;
    Voucher(int ID , double discount){
        id = ID;
        discountAmount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
