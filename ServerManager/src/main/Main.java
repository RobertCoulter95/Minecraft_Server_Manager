package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    Button btn;
    @Override
    public void start(Stage primaryStage) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of your server");
        //String servername = scanner.nextLine();

        btn = new Button("Button 1");
        Button btn2 = new Button("Button2");
        Button btn3 = new Button("Button3");
        Button btn4 = new Button("Button4");
        Button btn5 = new Button("Button5");

        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btn,btn2,btn3);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(btn4,btn5);
        pane.setTop(hbox);
        pane.setCenter(hbox2);



        Scene scene = new Scene(pane,400,400);




        primaryStage.setScene(scene);
        primaryStage.setTitle("My window!");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}