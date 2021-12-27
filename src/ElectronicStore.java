//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.ArrayList;
import java.util.Scanner;

public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts, curCart, numSales;
    private double revenue, totalValue;
    private String name;
    private Product[] stock, popular, cart; //Array to hold all products

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        cart = new Product[MAX_PRODUCTS];
        curProducts = 0;        //# of item in stock list
        curCart = 0;            //# of item in cart list
        numSales = 0;
        totalValue = 0.0;
    }

    public String getName() { return name; }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        if (curProducts < MAX_PRODUCTS) {
            stock[curProducts] = newProduct;
            curProducts++;
            return true;
        }
        return false;
    }


    //Sells 'amount' of the product at 'index' in the stock array
    //Updates the revenue of the store
    //If no sale occurs, the revenue is not modified
    //If the index is invalid, the revenue is not modified
    //Used for Complete Sale
    public void sellProducts() {
        for (int i = 0; i < curCart; i++)
            revenue += cart[i].sellUnits();
        numSales++;
        curCart = 0;
        totalValue = 0;

    }

    public double getRevenue() {
        return revenue;
    }

    //Prints the stock of the store
    public void printStock() {
        for (int i = 0; i < curProducts; i++) {
            System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
        }
    }


    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }

    //Stock list that avoid having null
    public Product[] getStock() {
        Product[] newStock = new Product[curProducts];
        for (int i = 0; i < curProducts; i++)
            newStock[i] = stock[i];
        return newStock;
    }

    //Add the amount of unit before each item in cart
    public String[] getCart() {
        String[] newCart = new String[curCart];
        for (int i = 0; i < curCart; i++)
            newCart[i] = cart[i].getCartQuantity() + "x" + cart[i].toString();
        return newCart;
    }

    //Remove the selected item from the stock list by taking its index
    public void removeFromStock(int selectedItem) {
        for (int i = selectedItem; i < curProducts - 1; i++)
            stock[i] = stock[i + 1];
        curProducts--;
    }

    //Add the selected item from the stock list to cart
    public void addToCart(int selectedItem) {
        Product selectedProduct = stock[selectedItem];
        int cartQuantity = selectedProduct.getCartQuantity();

        //if item not in cart yet
        if (cartQuantity == 0)
            cart[curCart++] = selectedProduct;
        else if (cartQuantity == selectedProduct.getStockQuantity() - 1)
            removeFromStock(selectedItem);

        selectedProduct.setCartQuantity(cartQuantity + 1);                      //increase cart quantity

        totalValue += selectedProduct.getPrice();
    }

    //Remove the selected item in cart
    private void removeCartItem(int item) {
        for (int i = item; i < curCart - 1; item++)
            cart[i] = cart[i + 1];
        curCart--;
    }


    public void removeFromCart(int selectedItem) {
        Product selectedProduct = cart[selectedItem];
        int cartQuantity = selectedProduct.getCartQuantity();

        if (cartQuantity == 1)
            removeCartItem(selectedItem);
        else if (cartQuantity == selectedProduct.getStockQuantity())
            stock[curProducts++] = selectedProduct;

        selectedProduct.setCartQuantity(cartQuantity - 1);                  //decrease cart quantity

        totalValue -= selectedProduct.getPrice();
    }

    public int getNumSales() { return numSales; }

    public double getTotalValue() { return totalValue; }

    public double getSaleDollar(){
        return revenue / numSales;
    }

    public Product[] getPopularity(){
        Product[] resPopular = new Product[curProducts];

        for (int i = 0; i < curProducts; i++)
            resPopular[i] = stock[i];

        for(int i = 0; i< curProducts; i++)
            for (int j = i + 1; j < curProducts; j++)
                if (resPopular[i].getSoldQuantity() < resPopular[j].getSoldQuantity()) {
                    Product temp = resPopular[i];
                    resPopular[i] = resPopular[j];
                    resPopular[j] = temp;
                }

        return new Product[]{ resPopular[0], resPopular[1], resPopular[2] };        //return 3 items that sold most units
    }

    public int getCurCart(){ return curCart;}
}



