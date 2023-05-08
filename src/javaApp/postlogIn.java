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
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import src.javaApp.models.*;


public class postlogIn {

    public static void renterLogin(Stage primaryStage, String userEmail){
        renter renterUser = jdbcAccessor.getRenter(userEmail);
        Label welcome = new Label("Welcome Back, " + renterUser.getFirstName());
        Label totReward = new Label("Total Reward: " + renterUser.getPoints());
        welcome.setPrefSize(400, 50);
        welcome.setFont(Font.font(20));
        welcome.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
            totReward.setPrefSize(400, 50);
            totReward.setFont(Font.font(20));
            totReward.setBackground(new Background(new BackgroundFill(
                (Color.web("#5F7A8A")), null, null
                )));
        GridPane root = new GridPane();
        GridPane acc = new GridPane();
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        Button logout = exitButton.exit(primaryStage,50,30);
        logout.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Main.preLogIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button searchButton = new Button("Search Properties");
        searchButton.setFont(Font.font(15));
        searchButton.setPrefSize(200, 80);
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    searchWindow(primaryStage, renterUser, "renter");
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button modifyButton = new Button("Manage Your Account");
        modifyButton.setFont(Font.font(15));
        modifyButton.setPrefSize(200, 80);
        modifyButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    modifyAttri.modifyAccountInfo(renterUser.getEmail());
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button viewButton = new Button("View Bookings");
        viewButton.setFont(Font.font(15));
        viewButton.setPrefSize(200, 80);
        viewButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ArrayList<booking> rs = jdbcAccessor.getBookings(userEmail);
                viewBookings.view(primaryStage,rs);
            }
        });
        root.add(welcome,1,1);
        if(renterUser.getRewards()){
            root.add(totReward,1,2);
        }
        root.add(logout,0,0);
        acc.add(searchButton,0,2);
        acc.add(modifyButton,1,2);
        acc.add(viewButton, 2,2);
        acc.setPadding(new Insets(5,5,5,5));
        acc.setVgap(10);
        root.add(acc,1,3);
        root.setPadding(new Insets(5,5,5,5));
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        
    }

    public static void agentLogin(Stage primaryStage, String userEmail){
        agent agentUser = jdbcAccessor.getAgent(userEmail);
        AnchorPane root = new AnchorPane();
        GridPane acc = new GridPane();
        Label welcome = new Label("Welcome Back, " + agentUser.getTitle() + " " +agentUser.getFirstName());
        welcome.setPrefSize(400, 100);
        welcome.setFont(Font.font(20));
        welcome.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        
        Button logout = exitButton.exit(primaryStage,50,30);
        logout.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Main.preLogIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button addButton = new Button("Add New Properties");
        addButton.setFont(Font.font(15));
        addButton.setPrefSize(200, 80);
        addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    createProp(primaryStage, agentUser);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button searchButton = new Button("Search Properties");
        searchButton.setFont(Font.font(15));
        searchButton.setPrefSize(200, 80);
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    searchWindow(primaryStage, agentUser, "agent");
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        acc.add(logout,0,0);
        acc.add(welcome,0,1);
        acc.add(searchButton,0,2);
        acc.add(addButton,1,2);
        acc.setPadding(new Insets(10,10,10,10));
        acc.setVgap(10);
        root.getChildren().add(acc);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
    }

    public static void searchWindow(Stage primaryStage, user user, String userType){
        AnchorPane root = new AnchorPane();
        GridPane acc = new GridPane();
        Scene scene = new Scene(root ,primaryStage.getWidth() + 100,primaryStage.getHeight() + 50);
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        Button backButton = new Button("Back");
        backButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    if(userType.equals("renter")){
                        renterLogin(primaryStage,user.getEmail());
                    } else{
                        agentLogin(primaryStage, user.getEmail());
                    }
                        
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        
        Label info = new Label("Provide some information for the search: ");
        Label cityLabel = new Label("City: ");
        TextField cityField = new TextField();
        HBox cityBox = createAccWindow.makeHbox(cityLabel,cityField);
        Label stateLabel = new Label("State: ");
        String[] stateOptions = {"CA", "IL"};
        ComboBox<String> stateField= new ComboBox<String>(FXCollections.observableArrayList(stateOptions));
        stateField.getSelectionModel().selectFirst();
        HBox stateBox = createAccWindow.makeHbox(stateLabel,stateField);
        Label petsLabel = new Label("Pets Allowed?: ");
        CheckBox petsField = new CheckBox();
        HBox petBox = createAccWindow.makeHbox(petsLabel,petsField);
        Label furnishLabel = new Label("Prefurnished?: ");
        CheckBox furnishField = new CheckBox();
        HBox furnishBox = createAccWindow.makeHbox(furnishLabel,furnishField);
        Label wheelchairLabel = new Label("Wheelchair \n Accessible?: ");
        CheckBox wheelchairField = new CheckBox();
        HBox wheelBox = createAccWindow.makeHbox(wheelchairLabel,wheelchairField);
        VBox checkBox = new VBox();
        checkBox.getChildren().addAll(petBox,furnishBox,wheelBox);
        Label areaLabel = new Label("Minimum \n Square Footage: ");
        TextField areaField = new TextField();
        HBox areaBox = createAccWindow.makeHbox(areaLabel,areaField);
        areaField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));
        Label propType = new Label("Select property type: ");
        String[] options = {"House","Apartment","Business","Duplex"};
        ComboBox<String> dropdown = new ComboBox<String>(FXCollections.observableArrayList(options));
        dropdown.getSelectionModel().selectFirst();
        HBox propTypeBox = createAccWindow.makeHbox(propType,dropdown);
        GridPane menu = new GridPane();
        menu.add(info,0,0);
        menu.add(cityBox,0,1);
        menu.add(stateBox,0,2);
        menu.add(checkBox,0,3);
        menu.add(propTypeBox,0,4);
        menu.add(areaBox,0,5);

        Label menunotif = new Label("");
        menu.add(menunotif,1,1);
        String[] aptoptions = {"Apartment","Flat","Condo","Townhouse"};
        ComboBox<String> aptdropBox = new ComboBox<String>(FXCollections.observableArrayList(aptoptions));
        aptdropBox.getSelectionModel().selectFirst();
        Label aptLabel = new Label("Apartment Style: ");
        VBox aptBox = new VBox();
        aptBox.getChildren().addAll(aptLabel,aptdropBox);

        String[] busoptions = {"Store", "Warehouse", "Office"};
        ComboBox<String> busdropBox = new ComboBox<String>(FXCollections.observableArrayList(busoptions));
        busdropBox.getSelectionModel().selectFirst();
        Label busLabel = new Label("Business Style: ");
        VBox busBox = new VBox();
        busBox.getChildren().addAll(busLabel,busdropBox);
        dropdown.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                switch(dropdown.getValue().toLowerCase()){
                    case "apartment":
                    menu.getChildren().removeIf(item -> item.equals(busBox));
                    menu.add(aptBox,1,4);
                    break;
                    case "business":
                    menu.getChildren().removeIf(item -> item.equals(aptBox));
                    menu.add(busBox,1,4);
                    break;
                    default:
                    menu.getChildren().removeIf(item -> item.equals(busBox) || item.equals(aptBox));
                }
             }   
    }
        );


        Button searchButton = new Button("Search");
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    String cityInput = (cityField.getText().isBlank() ? "" : cityField.getText().toLowerCase());
                    String stateInput = stateField.getValue().toLowerCase();
                    String propType = dropdown.getValue().toLowerCase();
                    Boolean pets = petsField.isSelected();
                    Boolean furnished = furnishField.isSelected();
                    Boolean wheelchair = wheelchairField.isSelected();
                    Integer area = (areaField.getText().isBlank() ? 0 : Integer.parseInt(areaField.getText()));
                    ArrayList<property> results = new ArrayList<property>();
                    BigDecimal budget = new BigDecimal(1000000000);
                    if(userType.equals("renter")){
                        budget = ((renter) user).getBudget();
                    } 
                    switch(propType){
                        case "apartment":
                            String apartType = aptdropBox.getValue().toLowerCase();
                            results = jdbcAccessor.searchApt(budget,cityInput,stateInput,pets,furnished,wheelchair,area,apartType);
                            break;
                        case "business":
                            String busType = busdropBox.getValue().toLowerCase();
                            results = jdbcAccessor.searchBus(budget,cityInput, stateInput,pets,furnished,wheelchair,area,busType);
                            break;
                        case "house":
                            results = jdbcAccessor.searchHouse(budget,cityInput,stateInput,pets,furnished,wheelchair,area);
                            break;
                        case "duplex":
                            results = jdbcAccessor.searchDuplex(budget,cityInput,stateInput,pets,furnished,wheelchair,area);
                            break;
                        }
                        if (results.isEmpty()) {
                            menunotif.setText("No Results Found \n Please Change Your Options");
                          } else {
                            menunotif.setText("");
                            search.searchResult(primaryStage,results,propType, userType, user.getEmail());
                          }
                    
                    
                    }
                catch (Exception e) {
                    System.out.println("Error At Search: "+e.toString());
                }
            }
        });
        menu.add(searchButton,1,5);
        menu.setPadding(new Insets(5,5,5,5));
        menu.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        menu.translateXProperty().bind((scene.widthProperty().subtract(menu.widthProperty())).divide(2).subtract(backButton.getMaxWidth()).subtract(scene.widthProperty().divide(10)));
        acc.add(backButton,0,0);
        acc.add(menu,0,1);
        root.getChildren().add(acc);
        primaryStage.setScene(scene);
    }

    public static void createProp(Stage primaryStage, agent user){
        AnchorPane root = new AnchorPane();
        GridPane acc = new GridPane();
        Scene scene = new Scene(root ,primaryStage.getWidth(),primaryStage.getHeight());
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        Button backButton = new Button("Back");
        backButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                    agentLogin(primaryStage,user.getEmail());
                }
        });
        
        Label info = new Label("Add Description: ");
        Label addressLabel = new Label("Address: ");
        TextField addressField = new TextField();
        HBox addressBox = createAccWindow.makeHbox(addressLabel,addressField);
        Label cityLabel = new Label("City: ");
        TextField cityField = new TextField();
        HBox cityBox = createAccWindow.makeHbox(cityLabel,cityField);
        Label stateLabel = new Label("State: ");
        String[] stateOptions = {"CA", "IL"};
        ComboBox<String> stateField= new ComboBox<String>(FXCollections.observableArrayList(stateOptions));
        stateField.getSelectionModel().selectFirst();
        HBox stateBox = createAccWindow.makeHbox(stateLabel,stateField);
        Label petsLabel = new Label("Pets Allowed?: ");
        CheckBox petsField = new CheckBox();
        HBox petBox = createAccWindow.makeHbox(petsLabel,petsField);
        Label furnishLabel = new Label("Prefurnished?: ");
        CheckBox furnishField = new CheckBox();
        HBox furnishBox = createAccWindow.makeHbox(furnishLabel,furnishField);
        Label wheelchairLabel = new Label("Wheelchair \n Accessible?: ");
        CheckBox wheelchairField = new CheckBox();
        HBox wheelBox = createAccWindow.makeHbox(wheelchairLabel,wheelchairField);
        VBox checkBox = new VBox();
        checkBox.getChildren().addAll(petBox,furnishBox,wheelBox);
        Label areaLabel = new Label("Square Footage: ");
        TextField areaField = new TextField();
        HBox areaBox = createAccWindow.makeHbox(areaLabel,areaField);
        areaField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));
        Label roomLabel = new Label("Number of Rooms: ");
        TextField roomField = new TextField();
        HBox roomBox = createAccWindow.makeHbox(roomLabel,roomField);
        roomField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));
        Label saleLabel = new Label("Sale Price: ");
        TextField saleField = new TextField();
        HBox saleBox = createAccWindow.makeHbox(saleLabel,saleField);
        saleField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));
        Label rentLabel = new Label("Rent Price: ");
        TextField rentField = new TextField();
        HBox rentBox = createAccWindow.makeHbox(rentLabel,rentField);
        rentField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, createAccWindow.integerFilter));

        Label propType = new Label("Select property type: ");
        String[] options = {"House","Apartment","Business","Duplex"};
        ComboBox<String> dropdown = new ComboBox<String>(FXCollections.observableArrayList(options));
        dropdown.getSelectionModel().selectFirst();
        HBox propTypeBox = createAccWindow.makeHbox(propType,dropdown);
        GridPane menu = new GridPane();
        menu.add(info,0,0);
        menu.add(addressBox,0,1);
        menu.add(cityBox,0,2);
        menu.add(stateBox,0,3);
        menu.add(checkBox,0,4);
        menu.add(propTypeBox,1,5);
        menu.add(areaBox,0,5);
        menu.add(roomBox,1,2);
        menu.add(saleBox,1,3);
        menu.add(rentBox,1,4);

        Label menunotif = new Label("");
        menu.add(menunotif,1,1);
        String[] aptoptions = {"Apartment","Flat","Condo","Townhouse"};
        ComboBox<String> aptdropBox = new ComboBox<String>(FXCollections.observableArrayList(aptoptions));
        aptdropBox.getSelectionModel().selectFirst();
        Label aptLabel = new Label("Apartment Style: ");
        VBox aptBox = new VBox();
        aptBox.getChildren().addAll(aptLabel,aptdropBox);

        String[] busoptions = {"Store", "Warehouse", "Office"};
        ComboBox<String> busdropBox = new ComboBox<String>(FXCollections.observableArrayList(busoptions));
        busdropBox.getSelectionModel().selectFirst();
        Label busLabel = new Label("Business Style: ");
        VBox busBox = new VBox();
        busBox.getChildren().addAll(busLabel,busdropBox);
        dropdown.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                switch(dropdown.getValue().toLowerCase()){
                    case "apartment":
                    menu.getChildren().removeIf(item -> item.equals(busBox));
                    menu.add(aptBox,2,5);
                    break;
                    case "business":
                    menu.getChildren().removeIf(item -> item.equals(aptBox));
                    menu.add(busBox,2,5);
                    break;
                    default:
                    menu.getChildren().removeIf(item -> item.equals(busBox) || item.equals(aptBox));
                }
             }   
    }
        );
        Button searchButton = new Button("Create");
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    String addressInput = addressField.getText().toLowerCase();
                    String cityInput = cityField.getText().toLowerCase();
                    String stateInput = stateField.getValue().toLowerCase();
                    String propType = dropdown.getValue().toLowerCase();
                    Boolean pets = petsField.isSelected();
                    Boolean furnished = furnishField.isSelected();
                    Boolean wheelchair = wheelchairField.isSelected();
                    int area = (areaField.getText().isBlank() ? -1 : Integer.parseInt(areaField.getText()));
                    int rooms = (roomField.getText().isBlank() ? -1 : Integer.parseInt(roomField.getText()));
                    BigDecimal sale = (saleField.getText().isBlank() ? new BigDecimal(-1) : new BigDecimal(saleField.getText()));
                    BigDecimal rent = (rentField.getText().isBlank() ? new BigDecimal(-1) : new BigDecimal(rentField.getText()));
                    String sqlPropType = "h";
                    String apartType = "NULL";
                    String busType = "NULL";
                    switch(propType){
                        case "apartment":
                            sqlPropType = "a";
                            apartType = aptdropBox.getValue().toLowerCase();
                            busType = "NULL";
                            break;
                        case "business":
                            sqlPropType = "b";
                            busType = busdropBox.getValue().toLowerCase();
                            apartType = "NULL";
                            break;
                        case "house":
                            sqlPropType = "h";
                            apartType = "NULL";
                            busType = "NULL";
                        case "duplex":
                            sqlPropType = "d";
                            apartType = "NULL";
                            busType = "NULL";
                        }
                    if(!(addressInput.isBlank() || cityInput.isBlank() || area == -1 || rent.equals(new BigDecimal(-1)) || sale.equals(new BigDecimal(-1)))){
                        jdbcAccessor.createProperty(addressInput,cityInput,stateInput,sqlPropType,pets.toString(),furnished.toString(),wheelchair.toString(),area,rooms,apartType,busType,rent,sale);
                    } else{
                        menunotif.setText("One Or More Fields Are Missing, Please Fill Out All Fields");
                    }
                    
                    }
                catch (Exception e) {
                    System.out.println("Error At Search: "+e.toString());
                }
            }
        });
        menu.add(searchButton,2,6);
        menu.setPadding(new Insets(5,5,5,5));
        menu.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        menu.translateXProperty().bind((scene.widthProperty().subtract(menu.widthProperty())).divide(2).subtract(backButton.getMaxWidth()).subtract(scene.widthProperty().divide(10)));
        acc.add(backButton,0,0);
        acc.add(menu,0,1);
        root.getChildren().add(acc);
        primaryStage.setScene(scene);
    }
    
}