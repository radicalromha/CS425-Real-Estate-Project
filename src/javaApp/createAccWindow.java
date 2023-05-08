package src.javaApp;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;

public class createAccWindow {

    static String accEmail;
    static String lastName;
    static String firstName;
    static String location;
    static String title;
    static String agencyName;
    static BigDecimal phoneNumber = new BigDecimal(0);;
    static BigDecimal budget = new BigDecimal(0);
    static ArrayList<String> addresses = new ArrayList<String>();
    static ArrayList<String> cards = new ArrayList<String>();
    static ArrayList<String> cardAddress = new ArrayList<String>();
    static java.sql.Date date;

    public static void resetData(){
        accEmail ="";
        lastName = "";
        firstName = "";
        location = "";
        title = "";
        agencyName = "";
        phoneNumber = new BigDecimal(0);
        budget = new BigDecimal(0);
        addresses = new ArrayList<String>();
        cards = new ArrayList<String>();
        cardAddress = new ArrayList<String>();
        date = java.sql.Date.valueOf(LocalDate.now());
    }

    public static UnaryOperator<Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        int newLength = change.getControlNewText().length();
        if (newText.matches("-?([1-9][0-9]*)?")) { 
            if(newLength <= 16){
                return change;
            }
        }
        return null;
    };

    public static void display(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        GridPane acc = new GridPane();
        Scene scene = new Scene(root ,800,600);
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        Button exit = exit(primaryStage, 50,30);
        Label fnLabel = new Label("First Name:");
        TextField fn = new TextField (firstName);
        Label lnLabel = new Label("Last Name: ");
        TextField ln = new TextField(lastName);
        Label emailLabel = new Label("Email: ");
        TextField email = new TextField(accEmail);
        String options[] = {"Renter","Agent"};
        Label selection = new Label("Choose your account type: ");
        Button backButton = new Button("Back");
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });

        Button addressButton = new Button("Add Addresses Here");
        addressButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    askAddress(primaryStage, scene,backButton);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });

        ComboBox<String> dropdown = new ComboBox<String>(FXCollections.observableArrayList(options));
        VBox select = new VBox();
        select.getChildren().addAll(selection,dropdown);
        for (Node i : select.getChildren()){
            VBox.setMargin(i, new Insets(5,5,5,5));
        }
        HBox fnBox = makeHbox(fnLabel,fn);
        HBox lnBox = makeHbox(lnLabel,ln);
        HBox eBox = makeHbox(emailLabel, email);
        GridPane menu = new GridPane();
        Label menuNotif = new Label(" ");
        menuNotif.setTextFill(Color.web("#B73131"));
        menu.add(select, 0,0);
        menu.add(menuNotif,1,0);
        menu.add(fnBox,0,1);
        menu.add(lnBox,0,2);
        menu.add(eBox,0,3);
        menu.add(addressButton, 0,4);
        menu.setPadding(new Insets(10,10,10,10));
        menu.setVgap(5);
        Label mvDate = new Label("Preferred Move In Date: ");
        DatePicker datePicker = new DatePicker();
        datePicker.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
            }
        });
        HBox dateBox = makeHbox(mvDate, datePicker);

        Label desiredLoc = new Label("Enter A Preferred Location: ");
        TextField desiredField = new TextField(location);
        HBox desiredLocBox = makeHbox(desiredLoc, desiredField);

        Label budgetLabel = new Label("Enter your budget: ");
        TextField budgetField = new TextField(budget.toString());
        budgetField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, integerFilter));
        HBox budgetBox = makeHbox(budgetLabel, budgetField);
        
        Label titleLabel = new Label("Enter your agency title: ");
        TextField titleField = new TextField(title);
        HBox titleBox = makeHbox(titleLabel, titleField);

        Label agencyLabel = new Label("Enter your agency's name: ");
        TextField agencyField = new TextField(agencyName);
        HBox agencyBox = makeHbox(agencyLabel, agencyField);

        Label phoneLabel = new Label("Enter your phone number: ");
        TextField phoneField = new TextField(phoneNumber.toString());
        HBox phoneBox = makeHbox(phoneLabel, phoneField);

        Button nextButton = new Button("Next");
        Button returnButton = new Button("Back");
        returnButton.setPadding(new Insets(5,5,5,5));
        Button askCards = new Button("Add Credit Card");
        
        Label rewardLabel = new Label("Check to join the rewards program: ");
        CheckBox getReward = new CheckBox();
        HBox rewardBox = makeHbox(rewardLabel, getReward);

        Button createAgentButton = new Button("Create Account");
        createAgentButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    title = titleField.getText();
                    agencyName = agencyField.getText();
                    phoneNumber = new BigDecimal(phoneField.getText());
                    jdbcAccessor.createAgent(accEmail, lastName, firstName, addresses, title, agencyName, phoneNumber);
                    resetData();
                    Main.preLogIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button createRentButton = new Button("Create Account");
        createRentButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    location = desiredField.getText();
                    budget = new BigDecimal(budgetField.getText());
                    boolean rewards = getReward.isSelected();
                    jdbcAccessor.createRenter(accEmail, lastName, firstName, addresses, date, location, budget, cards,rewards);
                    jdbcAccessor.addRenterCards(accEmail, cards , cardAddress);
                    resetData();
                    Main.preLogIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        askCards.setPadding(new Insets(5,5,5,5));
        askCards.setOnMouseClicked(new EventHandler<MouseEvent>() {
             public void handle(MouseEvent event) {
                 try {
                     askCards(primaryStage, scene,returnButton);
                 } catch (Exception e) {
                     System.out.println("Error: "+e.toString());
                 }
             }
         });
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent e){
            switch(getLogin(email.getText())){
                case 1:
                    menuNotif.setText("This email is associated with another account, \n please enter a different email");
                    break;
                case -1:
                    menuNotif.setText("");
                    accEmail = email.getText();
                    firstName = fn.getText();
                    lastName = ln.getText();
                    switch(dropdown.getValue().toLowerCase()){
                            case "renter":
                                date = java.sql.Date.valueOf(((datePicker.getValue() == null)? LocalDate.now() : datePicker.getValue()));
                                menu.getChildren().removeIf(item -> !item.equals(select));
                                menu.add(desiredLocBox,0,1);
                                menu.add(budgetBox,0,2);
                                menu.add(askCards,0,3);
                                menu.add(rewardBox, 0,4);
                                menu.add(returnButton,0,5);
                                menu.add(createRentButton,1,5);
                        break;
                            case "agent":
                                menu.getChildren().removeIf(item -> !item.equals(select));
                                menu.add(titleBox,0,1);
                                menu.add(agencyBox,0,2);
                                menu.add(phoneBox,0,3);
                                menu.add(returnButton,0,5);
                                menu.add(createAgentButton,1,5);
                        break;
                    }
                break;
            }
        }
        });
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e){
                primaryStage.setScene(scene);
                menu.getChildren().removeIf(item -> !item.equals(select) && !item.equals(menuNotif));
                menu.add(fnBox,0,1);
                menu.add(lnBox,0,2);
                menu.add(eBox,0,3);
                menu.add(addressButton, 0,4);
                switch(dropdown.getValue().toLowerCase()){
                    case "renter":
                        menu.add(dateBox,0,5);
                        menu.add(nextButton,1,5);
                    break;
                    case "agent":
                        menu.getChildren().removeIf(item -> item.equals(dateBox));
                        break;
                }
            }
        }
        );
        dropdown.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                //switches display for renters and agents
                switch(dropdown.getValue().toLowerCase()){
                    case "renter":
                        menu.getChildren().removeIf(item -> !item.equals(select) && !item.equals(menuNotif));
                        menu.add(fnBox,0,1);
                        menu.add(lnBox,0,2);
                        menu.add(eBox,0,3);
                        menu.add(addressButton, 0,4);
                        menu.add(dateBox,0,5);
                        menu.add(nextButton,1,5);
                    break;
                    case "agent":
                        menu.getChildren().removeIf(item -> !item.equals(select)&& !item.equals(menuNotif));
                        menu.add(fnBox,0,1);
                        menu.add(lnBox,0,2);
                        menu.add(eBox,0,3);
                        menu.add(addressButton, 0,4);
                        menu.add(nextButton,1,5);
                    break;
                }
            }
        });
        menu.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        GridPane.setMargin(exit,new Insets(20,20,20,20));
        acc.add(exit,0,0);
        acc.add(menu, 1,1);
        menu.translateXProperty().bind((scene.widthProperty().subtract(menu.widthProperty())).divide(2).subtract(exit.getMaxWidth()).subtract(scene.widthProperty().divide(10)));
        root.getChildren().add(acc);
        primaryStage.setScene(scene);
    }
    
    public static HBox makeHbox(Label label, Node text){
        HBox hb = new HBox();
        hb.getChildren().addAll(label,text);
        hb.setSpacing(10);
        hb.setPadding(new Insets(15, 12, 15, 12));
        return hb;
    }

    //creates window for adding addresses
    public static void askAddress(Stage stage, Scene scene, Button backButton){
        Stage address = new Stage();
        GridPane addressPane = new GridPane();
        Label addressLabel = new Label("Address " + ((addresses.isEmpty())? 1 : addresses.size()+1) +": ");
        Label notif = new Label("");
        TextField addressField = new TextField();
        HBox aBox = makeHbox(addressLabel, addressField);
        Button doneButton = new Button("Done");
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try{
                    if(!addressField.getText().isEmpty()){
                        boolean contained = false;
                        for (String i : addresses){
                        if(i.equalsIgnoreCase(addressField.getText().toLowerCase())){
                            contained = true;
                        }
                    }
                        if(!contained){
                            addresses.add(addressField.getText().toLowerCase());
                            notif.setText("Address Added!");
                            address.close();
                        } else{
                            notif.setText("Address Already Added!");
                        }
                    }
                    else{
                        notif.setText("Please Enter An Address");
                    }
                }catch (Exception e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        addressPane.add(notif,1,1);
        addressPane.add(aBox,1,2);
        addressPane.add(doneButton,1,3);
        addressPane.add(backButton,0,0);
        addressPane.setPadding(new Insets(10, 10, 10, 10));
        Scene aScene = new Scene(addressPane);
        address.setScene(aScene);
        address.show();
    }

    //creates window for adding credit cards + card-address relations
    public static void askCards(Stage stage, Scene scene, Button backButton){
        Stage cardStage = new Stage();
        GridPane cardPane = new GridPane();
        Label cardLabel = new Label("Credit Card " + ((cards.isEmpty())? 1 : cards.size()+1) +": ");
        Label notif = new Label("");
        TextField cardField = new TextField();
        cardField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, integerFilter));
        HBox cBox = makeHbox(cardLabel, cardField);
        Button doneButton = new Button("Done");
        Label addressLabel = new Label("Payment Address (Please Use One Already Added In Address Window): ");
        TextField addressField = new TextField();
        HBox aBox = makeHbox(addressLabel, addressField);
        doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try{
                    boolean contained = false;
                    for (String i : addresses){
                        if(i.equalsIgnoreCase(addressField.getText().toLowerCase())){
                            contained = true;
                        }
                    }
                    if(!cardField.getText().isEmpty()){
                        if(!addressField.getText().isEmpty()){
                            if(!cards.contains(cardField.getText())){
                                if(contained){
                                    cards.add(cardField.getText());
                                    cardAddress.add(addressField.getText());
                                    notif.setText("Card Added!");
                                    cardStage.close();
                                }
                                else{
                                    notif.setText("The Address Provided Was Not Recorded Previously Please Use An Address Previously Recorded");
                                }
                            } else{
                                notif.setText("Card Already Added!");
                            }
                        }
                        else{
                            notif.setText("Please Enter An Address!");
                        }
                    }
                    else{
                        notif.setText("Please Enter A Card!");
                    }
                }catch (Exception e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        cardPane.add(notif,1,1);
        cardPane.add(cBox,1,2);
        cardPane.add(aBox,1,3);
        cardPane.add(doneButton,1,4);
        cardPane.add(backButton,0,0);
        cardPane.setPadding(new Insets(10, 10, 10, 10));
        Scene cScene = new Scene(cardPane);
        cardStage.setScene(cScene);
        cardStage.show();
    }

    //needed a special exit button for this class
    // resets cards + addresses
    public static Button exit(Stage primaryStage, double width, double height){
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Main.preLogIn(primaryStage);
                    addresses = new ArrayList<String>();
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        exit.setPrefSize(width, height);
        exit.setFont(Font.font(width/height*10));
        return exit;
    }

    public static int getLogin(String email){
        if(!(email.isEmpty())){
            String query = "SELECT email FROM renter";
        try (Statement stmt = Main.conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String rsEmail = rs.getString("email");
                if(email.equalsIgnoreCase(rsEmail)){
                    return 1;
                }
            }
            query = "SELECT email FROM agent";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                String rsEmail = rs.getString("email");
                if(email.equalsIgnoreCase(rsEmail)){
                    return 1;
                }
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.toString());
        };
        return -1;
    } else{
        return 0;
    }
        }
}
