package src;

public class Guest {
    private Stock st;
    void guestUser(){
        st = new Stock();
    }
    public  void viewProducts(){
        st.DisplayProducts();
    }
    public  void searchItem(String productName){
        st.printProduct(productName);
    }

}
