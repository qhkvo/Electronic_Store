import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable {
    private String name;
    private double purchaseAmount;
    private HashMap<String, Integer> purchasedProducts;

    public Customer(String name){
        this.name = name;
        purchaseAmount = 0.0;
        purchasedProducts = new HashMap<>();
    }

    public String toString(){
        return name + " who has spent " + purchaseAmount;
    }

    public String getName(){return name;}

    public double getPurchaseAmount(){return purchaseAmount;}

    public void sellProduct(Product p, int amount){
        purchaseAmount += p.sellUnits(amount);

        String key = p.toString();
        if (purchasedProducts.containsKey(key)) {
            int curAmount = purchasedProducts.get(key);
            purchasedProducts.put(key, curAmount + amount);
        } else {
            purchasedProducts.put(key, amount);
        }
    }

    public void printPurchaseHistory(){
        System.out.println("All purchases of " + name + ":");
        for (String key : purchasedProducts.keySet())
            System.out.println(purchasedProducts.get(key) + " x " + key);
    }

    //Compare Customer names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
