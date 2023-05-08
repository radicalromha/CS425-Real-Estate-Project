package src.javaApp;

import java.sql.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class logInWindow {

    public static void logIn(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        GridPane acc = new GridPane();
        Scene scene = new Scene(root ,800,600);
        Button exit = exitButton.exit(primaryStage,50,30);
        Label emailText = new Label("Email:");
        emailText.setFont(exit.getFont());
        TextField emailField = new TextField ();
        emailField.setFont(Font.font(15));
        HBox eBox = new HBox();
        eBox.getChildren().addAll(emailText, emailField);
        eBox.setSpacing(10);
        eBox.setPadding(new Insets(0,0, 20, 0));
        VBox menu = new VBox();
        Label result = new Label("");
        result.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,15));
        result.setAlignment(Pos.CENTER);
        Button button = new Button("Log In");
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    switch(getLogin(emailField.getText())){
                        case 0:
                            result.setTextFill(Color.web("#B73131"));
                            result.setText("Please Enter An Email");
                            break;
                        case 1:
                            postlogIn.renterLogin(primaryStage,emailField.getText());
                            break;
                        case 2:
                            postlogIn.agentLogin(primaryStage,emailField.getText());
                            break;
                        default:
                            result.setTextFill(Color.web("#B73131"));
                            result.setText("That Email Is Not In Our Systems \n Please Create A New Account");
                            break;
                    }
                    result.setTextFill(Color.web("#3C4D57"));
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        button.setPrefSize(70,40);
        button.setFont(exit.getFont());
        VBox.setMargin(result, new Insets(20,20,20,20));
        VBox.setMargin(eBox,new Insets(20, 20, 20, 20));
        VBox.setMargin(button,new Insets(20, 20, 20, 20));
        menu.getChildren().addAll(eBox,result,button);
        menu.setPadding(new Insets(20, 50, 00, 20));
        menu.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        menu.setAlignment(Pos.CENTER);
        acc.add(exit,0,0);
        acc.add(menu,0,1);
        acc.setPadding(new Insets(20, 00 , 50, 20));
        root.getChildren().add(acc);
        menu.translateXProperty().bind((scene.widthProperty().subtract(menu.widthProperty())).divide(2).subtract(acc.getPadding().getLeft()));
        primaryStage.setScene(scene);
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
                    return 2;
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
