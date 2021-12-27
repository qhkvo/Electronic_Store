//Base class for all products the store will sell
public class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        cartQuantity = 0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits() {
        if (cartQuantity > 0 && stockQuantity >= cartQuantity) {
            stockQuantity -= cartQuantity;
            soldQuantity += cartQuantity;

            double total = price * cartQuantity;
            cartQuantity = 0;

            return total;
        }

        return 0.0;
    }
}

