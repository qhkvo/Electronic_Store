import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class ElectronicStoreView extends Pane {
    private Button              resetButton, addButton, removeButton, completeButton;
    private ListView<String>    cartList;
    private ListView<Product>   storeStockList, popularItemsList;
    private TextField           salesField, revenueField, saleDollarField;
    final Label currentCart;

    public ElectronicStoreView(){
        //Create labels
        Label label1 = new Label(("Store Summary:"));
        label1.relocate(40,20);
        Label label2 = new Label("# Sales:");
        label2.relocate(35,50);
        Label label3 = new Label("Revenue:");
        label3.relocate(24,90);
        Label label4 = new Label("$ / Sale:");
        label4.relocate(35,130);
        Label label5 = new Label("Most Popular Items:");
        label5.relocate(35, 160);
        currentCart = new Label("Current Cart ($0.00):");
        currentCart.relocate(580,20 );
        Label label7 = new Label("Store Stock:");
        label7.relocate(280,20 );

        //Create list
        popularItemsList = new ListView<Product>();
        popularItemsList.relocate(10,180);
        popularItemsList.setPrefSize(170,170);

        storeStockList = new ListView<Product>();
        storeStockList.relocate(200,45);
        storeStockList.setPrefSize(290,305);

        cartList = new ListView<String>();
        cartList.relocate(500,45);
        cartList.setPrefSize(290,305);

        //Create buttons
        resetButton = new Button("Reset Store");
        resetButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        resetButton.relocate(25, 355);
        resetButton.setPrefSize(130,40);

        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        addButton.relocate(250,355);
        addButton.setPrefSize(130,40);
        addButton.setDisable(true);

        removeButton = new Button("Remove from Cart");
        removeButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        removeButton.relocate(515,355);
        removeButton.setPrefSize(130,40);
        removeButton.setDisable(true);

        completeButton = new Button("Complete Sale");
        completeButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        completeButton.relocate(645,355);
        completeButton.setPrefSize(130,40);
        completeButton.setDisable(true);

        //Create TextFields
        salesField = new TextField();
        salesField.relocate(87,45);
        salesField.setPrefSize(100,20);
        salesField.setText("0");

        revenueField = new TextField();
        revenueField.relocate(87,87);
        revenueField.setPrefSize(100,20);
        revenueField.setText("0.00");

        saleDollarField = new TextField();
        saleDollarField.relocate(87,125);
        saleDollarField.setPrefSize(100,20);
        saleDollarField.setText("N/A");

        getChildren().addAll(label1, label2, label3, label4,label5, currentCart, label7,
                            salesField, revenueField, saleDollarField,popularItemsList,
                            resetButton, storeStockList, cartList,removeButton, addButton, completeButton );

    }

//    //GET METHODS
    public Button getResetButton(){ return resetButton;}
    public Button getAddButton(){ return addButton;}
    public Button getRemoveButton(){ return removeButton;}
    public Button getCompleteButton(){ return completeButton;}
    public ListView<String> getCartList(){ return cartList;}
    public ListView<Product> getStoreStockList(){ return storeStockList;}
    public ListView<Product> getPopularItemsList(){ return popularItemsList;}



    //UPDATE
    public void update(ElectronicStore model) {
        ObservableList<Product> stock = FXCollections.observableArrayList(model.getStock());
        storeStockList.setItems(stock);

        ObservableList<String> cart = FXCollections.observableArrayList(model.getCart());
        cartList.setItems(cart);

        ObservableList<Product> popular = FXCollections.observableArrayList(model.getPopularity());
        popularItemsList.setItems(popular);

        currentCart.setText("Current cart ($" + model.getTotalValue() + "):");

        salesField.setText(model.getNumSales() + "");

        revenueField.setText(model.getRevenue() + "");

        saleDollarField.setText(model.getSaleDollar() + "");

        if (model.getCurCart() > 0){
            completeButton.setDisable(false);
        }else{
            completeButton.setDisable(true);
        }





    }

}
