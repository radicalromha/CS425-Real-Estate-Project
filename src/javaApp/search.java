package src.javaApp;


import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.javaApp.models.*;


public class search {
    public static void searchResult(Stage primaryStage, ArrayList<property> rs,String prop_type,String userType, String email){
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        GridPane view = new GridPane();
        Scene scene = new Scene(root,1000,400);
        GridPane propData = new GridPane();
        Button exitButton = new Button("X");
        TableView<property> table = new TableView<property>();
        table.setEditable(true);
        ObservableList<property> propList = FXCollections.observableList(rs);
        table.setItems(propList);
        TableColumn<property,String> address = new TableColumn<property,String>("Address");
        address.setCellValueFactory(new PropertyValueFactory("address"));
        TableColumn<property,String> city = new TableColumn<property,String>("City");
        city.setCellValueFactory(new PropertyValueFactory("city"));
        TableColumn<property,String> state = new TableColumn<property,String>("State");
        state.setCellValueFactory(new PropertyValueFactory("state"));
        TableColumn<property,String> area = new TableColumn<property,String>("Square \n Footage");
        area.setCellValueFactory(new PropertyValueFactory("area"));
        area.setMinWidth(100);
        TableColumn<property,String> rooms = new TableColumn<property,String>("Number \n of Rooms");
        rooms.setCellValueFactory(new PropertyValueFactory("rooms"));
        rooms.setMinWidth(100);
        table.getColumns().addAll(address,city,state,area,rooms);
        switch(prop_type){
            case "apartment":
            TableColumn<property,String> apart = new TableColumn<property,String>("Apartment \n Style");
            apart.setCellValueFactory(new PropertyValueFactory("apartType"));
            apart.setMinWidth(100);
            table.getColumns().add(apart);
            break;
            case "business":
            TableColumn<property,String> bus = new TableColumn<property,String>("Business \n Style");
            bus.setCellValueFactory(new PropertyValueFactory("busType"));
            bus.setMinWidth(100);
            table.getColumns().add(bus);
            break;
        }
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPropertyDetails(newValue,propData, userType, email));
        table.setMinWidth(600);
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        view.add(table,0,0);
        view.add(propData,2,0);
        view.setPadding(new Insets(10,10,10,10));
        root.getChildren().add(view);
        stage.setScene(scene);
        stage.show();
    }

    public static void showPropertyDetails(property prop, GridPane grid, String userType, String email){
        grid.getChildren().clear();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(3);
        grid.setHgap(3);
        Label address = new Label("Address: " + prop.getAddress() + "\n City: " + prop.getCity() + "\n State: " + prop.getState());
        Label price = new Label("Renting Price: $" + prop.getRentPrice());
        Label sale = new Label("Sale Price: $" + prop.getSalePrice());
        Label pets = new Label("Pets Allowed? " + ((prop.getPets()) ? "Yes" : "No"));
        Label furnished = new Label("Prefurnished? " + ((prop.getFurnish()) ? "Yes" : "No"));
        Label wheelchair = new Label("Wheelchair Accessible? " + ((prop.getWheelchair()) ? "Yes" : "No"));
        Label rooms = new Label("Number of Rooms: " + prop.getRooms());
        Label area = new Label("Area: " + prop.getArea()+ " sq ft");
        Label menuLabel = new Label("");
        grid.add(address,0,0);
        grid.add(price,0,1);
        grid.add(sale,1,1);
        grid.add(pets,0,2);
        grid.add(furnished,0,3);
        grid.add(wheelchair,0,4);
        grid.add(rooms,1,0);
        grid.add(area,1,2);
        switch(prop.getPropType()){
            case "a":
            Label apart = new Label("Apartment Type: " + prop.getApartType());
            grid.add(apart,1,3);
            break;
            case "b":
            Label bus = new Label("Business Type: " + prop.getBusType());
            grid.add(bus,1,3);
        }
        Button bookButton = new Button("Book Property");
        bookButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    viewBookings.bookProperty(prop,email);
                }catch(Exception e){

                }
            }
        });
        Button modifyButton = new Button("Modify Property");
        modifyButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    modifyAttri.modifyProperty(prop);
                }catch(Exception e){

                }
           }
        });
        Button deleteButton = new Button("Delete Property");
        deleteButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int update = jdbcAccessor.deleteProperty(prop.getAddress());
                if(update != -1){
                    menuLabel.setText("Property Deleted");
                }
           }
        });
        if(userType.equalsIgnoreCase("agent")){
            grid.add(modifyButton,1,4);
            grid.add(deleteButton,1,5);
        } else if(userType.equalsIgnoreCase("renter")){
            grid.add(bookButton,1,4);
        }

    }
}
