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

public class viewBookings {
    public static void view(Stage primaryStage, ArrayList<booking> rs){
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        GridPane view = new GridPane();
        Scene scene = new Scene(root,400,400);
        TableView<booking> table = new TableView<booking>();
        table.setEditable(true);
        ObservableList<booking> propList = FXCollections.observableList(rs);
        table.setItems(propList);
        TableColumn<booking,String> address = new TableColumn<booking,String>("Address");
        address.setCellValueFactory(new PropertyValueFactory("address"));
        TableColumn<booking,String> card = new TableColumn<booking,String>("Card");
        card.setCellValueFactory(new PropertyValueFactory("card"));
        table.getColumns().addAll(address,card);
        table.setMinWidth(200);
        Button closeButton = new Button("Done");
        closeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        view.add(closeButton,0,0);
        view.add(table,1,1);
        root.getChildren().add(view);
        stage.setScene(scene);
        stage.show();
    }

    public static void bookProperty(property prop, String email){
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 500,400);
        renter user = jdbcAccessor.getRenter(email);
        String[] cards = user.getCards();
        String[] censoredCards = new String[cards.length];
        for(int i = 0; i < cards.length; i++){
            censoredCards[i] = "****" + cards[i].substring(cards[i].length() - 4);
        }
        Label cardLabel = new Label("Which card do you want to use for booking?");
        ComboBox<String> cardDBox = new ComboBox<String>(FXCollections.observableArrayList(censoredCards));
        VBox cardBox = new VBox();
        cardBox.getChildren().addAll(cardLabel,cardDBox);
        Button bookButton = new Button("Book Now");
        Button cancelButton = new Button("X");
        GridPane menu = new GridPane();
        GridPane info = new GridPane();
        search.showPropertyDetails(prop, info, "neither", email);
        Label menunotif = new Label("");
        menu.add(cancelButton,0,0);
        menu.add(info,0,1);
        menu.add(cardBox,0,2);
        menu.add(bookButton,1,2);
        menu.add(menunotif,0,3);
        menu.setPadding(new Insets(20, 20, 20, 20));
        root.add(menu,1,1);
        Stage stage = new Stage();
        bookButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    if(!cardDBox.getValue().isEmpty()){
                        int index = 0;
                        for(int i = 0; i < censoredCards.length;i++){
                            if(censoredCards[i].equals(cardDBox.getValue())){
                                index = i;
                            }
                        }
                        menunotif.setText("");
                        if(user.getRewards()){
                            jdbcAccessor.addPoints(prop.getAddress(), email);
                        }
                        jdbcAccessor.createBooking(jdbcAccessor.getPropID(prop.getAddress()), email, cards[index]);
                        stage.close();
                    }
                    else{
                        menunotif.setText("Please Select a card before proceeding");
                    }
                }catch(Exception e){
                        System.out.println("Error At Booking: " + e);
                }
            }
        });
        cancelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    
}
