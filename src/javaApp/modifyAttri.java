package src.javaApp;

import java.math.BigDecimal;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;
import src.javaApp.models.*;

public class modifyAttri {
    public static void modifyProperty(property prop){
        GridPane root = new GridPane();
        root.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(root, 500,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        Button doneButton = new Button("Done");
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        Label intro = new Label("Select the attribute to modify");
        Button saleButton = new Button("Sale Price");
        Button rentButton = new Button("Rent Price");
        Button petsButton = new Button("Allowing Pets");
        Button furnishButton = new Button("Prefurnished");
        Button wheelButton = new Button("Wheelchair Access");
        Button roomsButton = new Button("Number of Rooms");
        Button apartTypeButton = new Button("Apartment Typing");
        Button busTypeButton = new Button("Business Typing");
        GridPane menu = new GridPane();
        menu.add(petsButton,0,0);
        menu.add(furnishButton,0,1);
        menu.add(wheelButton, 0,2);
        menu.add(roomsButton,1,0);
        menu.add(saleButton,0,3);
        menu.add(rentButton,1,3);
        menu.add(doneButton,2,4);
        switch(prop.getPropType()){
            case "a":
            menu.add(apartTypeButton,1,1);
            break;
            case "b":
            menu.add(busTypeButton,1,1);
            break;
        }
        saleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getSalePrice().toString(), "sale_price", "price");
            }
        });
        rentButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getRentPrice().toString(), "rent_price", "price");
            }
        });
        petsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getPets().toString(), "allow_pets", "boolean");
            }
        });
        wheelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getWheelchair().toString(), "wheelchair_access", "boolean");
            }
        });
        furnishButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getFurnish().toString(), "is_furnished", "boolean");
            }
        });
        roomsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getRooms().toString(), "num_rooms", "integer");
            }
        });
        apartTypeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getApartType().toString(), "apart_type", "string");
            }
        });
        busTypeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyPropPopUp(prop, prop.getBusType().toString(), "bus_type", "string");
            }
        });
        menu.setPadding(new Insets(20, 20, 20, 20));
        root.add(intro,0,0);
        root.add(menu,0,1);
        stage.show();
    }

    public static void modifyPropPopUp(property prop,String current, String attri, String dataType){
        GridPane menu = new GridPane();
        Scene scene = new Scene(menu, 500,400);
        Stage stage = new Stage();
        Label info = new Label(attri + ": " + current);
        menu.add(info,1,1);
        Button doneButton = new Button("Done");
        Label stringInfo = new Label("Enter the new data:");
        TextField input = new TextField();
        if(!(attri.equals("apart_type") || attri.equals("bus_type"))){
            switch(dataType){
                case "boolean":
                Button trueButton = new Button("Set True");
                Button falseButton = new Button("Set False");
                HBox buttonsBox = new HBox(trueButton,falseButton);
                menu.add(buttonsBox,1,2);
                trueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, "true", "boolean");
                        stage.close();
                    }});
                falseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, "false","boolean");
                        stage.close();
                    }});
                break;
                case "string":
                input.setTextFormatter(null);
                HBox stringBox = new HBox(stringInfo,input);
                menu.add(stringBox,1,2);
                menu.add(doneButton,1,3);
                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(!input.getText().isBlank())
                        {jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, input.getText().toLowerCase(),"string");
                            stage.close();
                        } else{
                            stage.close();
                        }
                    }
                });
                break;
                case "integer":
                input.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));
                HBox intBox = createAccWindow.makeHbox(stringInfo,input);
                menu.add(intBox,1,2);
                menu.add(doneButton,1,3);
                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(!input.getText().isBlank())
                        {jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, input.getText(),"int");
                            stage.close();
                        } else{
                            stage.close();
                        }
                    }
                });
                break;
                case "price":
                input.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, createAccWindow.integerFilter));
                HBox priceBox = createAccWindow.makeHbox(stringInfo,input);
                menu.add(priceBox,1,2);
                menu.add(doneButton,1,3);
                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(!input.getText().isBlank())
                        {jdbcAccessor.modifyPricing(jdbcAccessor.getPropID(prop.getAddress()),attri, new BigDecimal(input.getText()));
                            stage.close();
                        } else{
                            stage.close();
                        }
                    }
                });
                break;
            }
        } else{
            switch(attri){
                case "apart_type":
                String[] aptType = {"flat", "apartment", "condo", "townhouse"};
                ComboBox<String> aptComboBox = new ComboBox<String>(FXCollections.observableArrayList(aptType));
                aptComboBox.getSelectionModel().select(current);
                HBox aptBox = createAccWindow.makeHbox(stringInfo,aptComboBox);
                menu.add(aptBox,1,2);
                menu.add(doneButton,1,3);
                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(!input.getText().isBlank())
                        {jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, aptComboBox.getValue(),"string");
                            stage.close();
                        } else{
                            stage.close();
                        }
                        
                    }
                });
                break;
            case "bus_type":
                String[] busType = {"store", "warehouse", "office"};
                ComboBox<String> busComboBox = new ComboBox<String>(FXCollections.observableArrayList(busType));
                busComboBox.getSelectionModel().select(current);
                HBox busBox = createAccWindow.makeHbox(stringInfo,busComboBox);
                menu.add(busBox,1,2);
                menu.add(doneButton,1,3);
                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if(!input.getText().isBlank())
                        {jdbcAccessor.modifyHousing(jdbcAccessor.getPropID(prop.getAddress()),attri, busComboBox.getValue(),"string");
                            stage.close();
                        } else{
                            stage.close();
                        }
                    }
                });
                break;
            }
        }
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setHgap(2);
        menu.setVgap(2);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void modifyAccountInfo(String email){
        GridPane root = new GridPane();
        root.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(root, 500,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        Button doneButton = new Button("Done");
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        Label intro = new Label("Select the attribute to modify");
        Button addressButton = new Button("Address");
        addressButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyAddress(email);
            }
        });
        Button cardButton = new Button("Cards");
        cardButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyCards(email);
            }
        });
        Button rewardButton = new Button("Reward Program");
        rewardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                modifyRewards(email);
                stage.close();
            }
        });
        root.add(doneButton,0,0);
        root.add(intro,0,1);
        root.add(addressButton, 0,2);
        root.add(cardButton, 0, 3);
        root.add(rewardButton,0,4);
        stage.show();

}
    public static void modifyAddress(String email){
        renter user = jdbcAccessor.getRenter(email);
        GridPane root = new GridPane();
        GridPane menu = new GridPane();
        root.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(root, 500,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        Button doneButton = new Button("Done");
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        doneButton.setPrefSize(50,40);
        Label notifLabel = new Label("");
        root.add(doneButton,0,0);
        root.add(notifLabel,1,0);
        ComboBox<String> addressDBox = new ComboBox<String>(FXCollections.observableArrayList(user.getAddresses()));
        String[] cards = user.getCards();
        String[] cardAddresses = new String[cards.length];
        for (int i = 0; i < cards.length; i++){
            cardAddresses[i] = jdbcAccessor.getCardAddress(cards[i]);
        }
        Label addressLabel = new Label("Enter The New Address: ");
        TextField addressField = new TextField();
        HBox addressInput = createAccWindow.makeHbox(addressLabel,addressField);
        Button addAddress = new Button("Add");
        Button modButton = new Button("Modify");
        Button delButton = new Button("Delete");
        String[] options = {"Add", "Modify", "Delete"};
        Label addressBoxLabel = new Label("Choose Address");
        addressBoxLabel.setPrefWidth(100);
        ComboBox<String> optionsBox = new ComboBox<String>(FXCollections.observableArrayList(options));
        menu.add(optionsBox,0,1);
        optionsBox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                menu.getChildren().removeIf(item -> !(item.equals(optionsBox)));
                notifLabel.setText(" ");
                switch(optionsBox.getValue().toLowerCase()){
                    case "add":
                    menu.add(addressInput,0,2);
                    menu.add(addAddress,1,2);
                    break;
                    case "modify":
                    menu.add(addressBoxLabel,0,2);
                    menu.add(addressDBox,1,2);
                    menu.add(addressInput,0,3);
                    menu.add(modButton, 1,3);
                    break;
                    case "delete":
                    menu.add(addressBoxLabel,0,2);
                    menu.add(addressDBox,1,2);
                    menu.add(delButton, 1,3);
                }
            }
        });
        modButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean contained = false;
                    for(String c : cardAddresses){
                        if(c.equalsIgnoreCase(addressDBox.getValue())){
                            contained = true;
                        } else if(addressDBox.getValue().equalsIgnoreCase(c)){
                            contained = true;
                        }
                    }
                    if(!contained){
                        if(!addressField.getText().isBlank()){
                            int index = Arrays.asList(user.getAddresses()).indexOf(addressDBox.getValue());
                            String[] addresses = user.getAddresses();
                            addresses[index] = addressField.getText();
                            jdbcAccessor.modifyAddress(user.getEmail(), addresses);
                            stage.close();
                    }
                    else{
                        notifLabel.setText("Please Enter A New Address Or Cancel Your Operations");
                    }
                } else{
                    notifLabel.setText("This Address Cannot Be Edited As It Is Connected To A Card.\n Please Edit Card Before Editing Address");
                }
            }
        });
        addAddress.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String[] addresses = user.getAddresses();
                String[] newAddresses = new String[addresses.length+1];
                for(int i = 0; i < addresses.length;i++){
                    newAddresses[i] = addresses[i];
                }
                newAddresses[addresses.length] = addressField.getText().toLowerCase();
                jdbcAccessor.modifyAddress(user.getEmail(),newAddresses);
                stage.close();
            }
        });

        delButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String[] addresses = user.getAddresses();
                String[] newAddresses = new String[addresses.length-1];
                boolean contained = false;
                    for(String c : cardAddresses){
                        if(c.equalsIgnoreCase(addressDBox.getValue())){
                            contained = true;
                        } else if(addressDBox.getValue().equalsIgnoreCase(c)){
                            contained = true;
                        }
                    }
                    if(!contained){
                        for(int i = 0; i < addresses.length;i++){
                            if(!addresses[i].equalsIgnoreCase(addressDBox.getValue())){
                                newAddresses[i] = addresses[i];
                            }
                        }
                        jdbcAccessor.modifyAddress(user.getEmail(),newAddresses);
                        stage.close();
                    } else{
                        notifLabel.setText("This Address Cannot Be Edited As It Is Connected To A Card.\n Please Edit Card Before Editing Address");
                    }
            }
        });
        root.add(menu,1,1);
        stage.show();
}
        public static void modifyCards(String email){
        renter user = jdbcAccessor.getRenter(email);
        GridPane root = new GridPane();
        GridPane menu = new GridPane();
        root.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(root, 500,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        Button doneButton = new Button("Done");
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        doneButton.setPrefSize(50,40);
        Label notifLabel = new Label("");
        root.add(doneButton,0,0);
        root.add(notifLabel,1,0);
        ComboBox<String> cardDBox = new ComboBox<String>(FXCollections.observableArrayList(user.getCards()));
        String[] cards = user.getCards();
        String[] cardAddresses = new String[cards.length];
        for (int i = 0; i < cards.length; i++){
            cardAddresses[i] = jdbcAccessor.getCardAddress(cards[i]);
        }
        Label cardLabel = new Label("Enter The New Card: ");
        TextField cardField = new TextField();
        cardField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, createAccWindow.integerFilter));
        ComboBox<String> addressDBox = new ComboBox<String>(FXCollections.observableArrayList(user.getAddresses()));
        Label addressLabel = new Label("Select Address Connected To Card: ");
        HBox addressBox = createAccWindow.makeHbox(addressLabel,addressDBox);
        HBox cardBox = createAccWindow.makeHbox(cardLabel,cardField);
        Button addButton = new Button("Add");
        Button modButton = new Button("Modify");
        Button delButton = new Button("Delete");
        String[] options = {"Add", "Modify", "Delete"};
        Label cardBoxLabel = new Label("Choose Card");
        cardBoxLabel.setPrefWidth(100);
        ComboBox<String> optionsBox = new ComboBox<String>(FXCollections.observableArrayList(options));
        menu.add(optionsBox,0,1);
        optionsBox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                menu.getChildren().removeIf(item -> !(item.equals(optionsBox)));
                notifLabel.setText(" ");
                switch(optionsBox.getValue().toLowerCase()){
                    case "add":
                    menu.add(cardBox,0,2);
                    menu.add(addressBox,0,3);
                    menu.add(addButton,1,2);
                    break;
                    case "modify":
                    menu.add(cardBoxLabel,0,2);
                    menu.add(cardDBox,1,2);
                    menu.add(cardBox,0,3);
                    menu.add(addressBox,0,4);
                    menu.add(modButton, 1,3);
                    break;
                    case "delete":
                    menu.add(cardBoxLabel,0,2);
                    menu.add(cardDBox,1,2);
                    menu.add(delButton, 1,3);
                }
            }
        });
        modButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean contained = false;
                    for(String c : cardAddresses){
                        if(c.equalsIgnoreCase(addressDBox.getValue())){
                            contained = true;
                        } else if(addressDBox.getValue().equalsIgnoreCase(c)){
                            contained = true;
                        }
                    }
                    if(!contained){
                        if(!cardField.getText().isBlank()){
                            int index = Arrays.asList(user.getCards()).indexOf(addressDBox.getValue());
                            String oldCard = cards[index];
                            cards[index] = cardField.getText();
                            jdbcAccessor.modifyCard(user.getEmail(), cards);
                            jdbcAccessor.modifyCardAddress(user.getEmail(),oldCard,cardField.getText());
                            stage.close();
                    }
                    else{
                        notifLabel.setText("Please Enter A New Address Or Cancel Your Operations");
                    }
                } else{
                    notifLabel.setText("This Address Cannot Be Edited As It Is Connected To A Card.\n Please Edit Card Before Editing Address");
                }
            }
        });
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String[] cards = user.getCards();
                String[] newCards = new String[cards.length+1];
                for(int i = 0; i < cards.length;i++){
                    newCards[i] = cards[i];
                }
                newCards[cards.length] = cardField.getText();
                jdbcAccessor.modifyCard(user.getEmail(),newCards);
                jdbcAccessor.addCardAddress(user.getEmail(),cardField.getText(),addressDBox.getValue());
                stage.close();
            }
        });

        delButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String[] cards = user.getCards();
                String[] newCards = new String[cards.length-1];
                for(int i = 0; i < cards.length;i++){
                    if(!cards[i].equalsIgnoreCase(cardDBox.getValue())){
                        newCards[i] = cards[i];
                    }
                }
                jdbcAccessor.modifyCard(user.getEmail(),newCards);
                stage.close();
            }
        });
        root.add(menu,1,1);
        stage.show();
}
public static void modifyRewards(String email){
    renter user = jdbcAccessor.getRenter(email);
    GridPane root = new GridPane();
    GridPane menu = new GridPane();
    root.setPadding(new Insets(5, 5, 5, 5));
    Scene scene = new Scene(root, 500,400);
    Stage stage = new Stage();
    stage.setScene(scene);
    Button doneButton = new Button("Done");
    doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            stage.close();
        }
    });
    doneButton.setPrefSize(50,40);
    Label currentVal = new Label("Current Value: " + Boolean.toString(user.getRewards()));
    Button trueButton = new Button("Set True");
    trueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            jdbcAccessor.modifyReward(email,"true");
            stage.close();
        }
    });
    Button falseButton = new Button("Set False");
    falseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            jdbcAccessor.modifyReward(email,"false");
            stage.close();
        }
    });
    menu.add(currentVal,0,0);
    menu.add(trueButton,0,1);
    menu.add(falseButton,1,1);
    root.add(doneButton,0,0);
    root.add(menu,1,1);
    stage.show();
}
}
