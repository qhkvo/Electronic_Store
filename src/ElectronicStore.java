//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ElectronicStore implements Serializable {
    private String name;
    private HashSet<Product> stock; //Array to hold all products
    private HashSet<Customer> customers;


    public ElectronicStore(String initName) {
        name = initName;
        stock = new HashSet<>();
        customers = new HashSet<>();
    }
    
    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        return stock.add(newProduct);
    }

    public boolean registerCustomer(Customer c){        //2
        return customers.add(c);
    }

    //Add the amount of unit before each item in cart
    public List<Customer> getCustomers(){
        return new ArrayList<>(customers);
    }

    public List<Product> searchProducts(String x){
        ArrayList<Product> searchResult = new ArrayList<>();

        x = x.toLowerCase();
        for (Product p : stock)
            if (p.toString().toLowerCase().contains(x))
                searchResult.add(p);

        return searchResult;
    }

    public List<Product> searchProducts(String x, double minPrice, double maxPrice){
        if (minPrice < 0 && maxPrice < 0)
            return searchProducts(x);

        ArrayList<Product> searchResult = new ArrayList<>();

        x = x.toLowerCase();
        for (Product p : stock)
            if (p.toString().toLowerCase().contains(x)
                    && minPrice <= p.getPrice() && (p.getPrice() <= maxPrice || maxPrice < 0))
                        searchResult.add(p);

        return searchResult;
    }

    public boolean addStock(Product p, int amount){
        if (stock.contains(p)) {
            p.increaseQuantity(amount);
            return true;
        } else
            return false;
    }

    public boolean sellProduct(Product p, Customer c, int amount){
        if (stock.contains(p) && customers.contains(c) && p.getStockQuantity() >= amount) {
            c.sellProduct(p, amount);
            return true;
        } else
            return false;
    }

    public List<Customer> getTopXCustomers(int x) {
        ArrayList<Customer> customersSorted = new ArrayList<>(customers);

        //Sort from smallest to largest of the customers' purchase amounts by comparing them
        customersSorted.sort(Comparator.comparing(Customer::getPurchaseAmount));
        //Reverse to get from largest to smallest
        Collections.reverse(customersSorted);

        if (x >= customersSorted.size())
            return customersSorted;
        else
            return customersSorted.subList(0, x);       //Get from 0 to the given x customers
    }

    public boolean saveToFile(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
            out.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static ElectronicStore loadFromFile(String fileName) {
        try {
            ElectronicStore electronicStore;

            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
            electronicStore = (ElectronicStore) inp.readObject();
            inp.close();

            return electronicStore;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
} 