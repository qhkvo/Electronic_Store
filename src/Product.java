import java.io.Serializable;
import java.util.Objects;

//Base class for all products the store will sell
public abstract class Product implements Serializable {
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void increaseQuantity(int amount) { stockQuantity += amount; }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Product product = (Product) o;
        return this.toString().equals(product.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}