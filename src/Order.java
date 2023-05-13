package src;

public class order {
    private int order_id;
    private int User_id;
    private String status;

    public order(int order_id, int user_id, String status) {
        this.order_id = order_id;
        this.User_id = user_id;
        this.status = status;
    }

    public int getOrderID() {
        return order_id;
    }

    public void setOrderID(int orderID) {
        this.order_id = orderID;
    }

    public int getUserID() {
        return User_id;
    }

    public void setCustomerID(int user_id) {
        User_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
};

