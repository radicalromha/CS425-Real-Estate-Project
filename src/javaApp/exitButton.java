package src.javaApp;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class exitButton {
    public static Button exit(Stage primaryStage, double width, double height){
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Main.preLogIn(primaryStage);
                } catch (Exception e) {
                    System.out.println("Error: "+e.toString());
                }
            }
        });
        exit.setPrefSize(width, height);
        exit.setFont(Font.font(width/height*10));
        return exit;
    }
   
}
