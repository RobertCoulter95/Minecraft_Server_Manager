package Main_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Server;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Server newServer = new Server();
        Scene scene = new Scene(CreateServerUIBox(newServer),400,400);


        primaryStage.setScene(scene);
        primaryStage.setTitle("My window!");
        primaryStage.show();

    }

    public static GridPane CreateServerUIBox(Server server){
        GridPane pane = new GridPane();
        Button btn1 = new Button(server.World);
        pane.add(btn1,0,0);
        return pane;
    }


    public static void main(String args) {
        Application.launch(args);
    }
}