import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.collections.ObservableList;
import javafx.scene.input.*;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage){

        //Create the view
        ElectronicStoreView view = new ElectronicStoreView();

        primaryStage.setTitle("Electronic Store Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view,800,400 ));
        primaryStage.show();

        view.update(model);

        //EVENT HANDLER
        //ADD BUTTON
        view.getAddButton().setOnAction(actionEvent -> {
            int selectedItem =  view.getStoreStockList().getSelectionModel().getSelectedIndex();
            model.addToCart(selectedItem);
            view.update(model);
            view.getAddButton().setDisable(true);

        });

        //REMOVE BUTTON
        view.getRemoveButton().setOnAction(actionEvent -> {
            int selectedItem = view.getCartList().getSelectionModel().getSelectedIndex();
            model.removeFromCart(selectedItem);
            view.update(model);
        });

        //COMPLETE BUTTON
        view.getCompleteButton().setOnAction(actionEvent -> {
            model.sellProducts();
            view.update(model);
        });


        //STOCK LIST
        view.getStoreStockList().setOnMouseClicked(mouseEvent -> {
            view.getAddButton().setDisable(false);
            view.getRemoveButton().setDisable(true);
        });


        //CART LIST
        view.getCartList().setOnMouseClicked(mouseEvent -> {
            view.getRemoveButton().setDisable(false);
            view.getAddButton().setDisable(true);
        });

        //RESET BUTTON
        view.getResetButton().setOnAction(actionEvent -> {
            model = model.createStore();
            view.update(model);
        });
    }

    public static void main(String[] args) { launch(args); }
}

