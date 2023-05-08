package src.javaApp;

import java.sql.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Main extends Application {

    public static jdbcAccessor connection = new jdbcAccessor();
    public static Connection conn = jdbcAccessor.connect();

    //starts GUI
    public void start(Stage primaryStage) throws Exception{
        preLogIn(primaryStage);
        primaryStage.show();
    }

    //window on GUI start
    public static void preLogIn(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(
            (Color.web("#2D5873")), null, null
            )));
        Scene scene = new Scene(root, 800,600);
        Button signup = new Button("Sign Up");
        signup.setPrefSize(300, 200);
        signup.setFont(Font.font(20));
        signup.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    createAccWindow.display(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Button login = new Button("Log In");
        login.setPrefSize(300,200);
        login.setFont(Font.font(20));
        login.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    logInWindow.logIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        Label welcome = new Label("Welcome To Our Real Estate Company \n Ran By Eskinder Fitsum and Triphob Prasopchaichan");
        welcome.setPrefHeight(200);
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setFont(Font.font(30));
        welcome.setBackground(new Background(new BackgroundFill(
            (Color.web("#5F7A8A")), null, null
            )));
        GridPane.setHalignment(welcome, HPos.CENTER);
        HBox hb = new HBox();
        
        hb.getChildren().addAll(signup, login);
        hb.setSpacing(80);
        hb.setPadding(new Insets(15, 15, 15, 15));
        hb.setPrefSize(700,200);
        hb.setBackground(new Background(new BackgroundFill(
            (Color.valueOf("#2B8073")),
            new CornerRadii(10),
            new Insets(10)
            )));
        GridPane.setValignment(hb, VPos.CENTER);
        root.setVgap(80);
        GridPane.setHalignment(hb, HPos.CENTER);
        root.add(welcome, 1,0);
        root.add(hb,1,4);
        root.setPadding(new Insets(10));
        welcome.translateXProperty().bind((scene.widthProperty().subtract(welcome.widthProperty())).divide(2));
        hb.translateXProperty().bind((scene.widthProperty().subtract(hb.widthProperty())).divide(2));
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
        primaryStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}