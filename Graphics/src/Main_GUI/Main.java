package Main_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {
public static long numberOfServers;
    @Override
    public void start(Stage primaryStage) throws IOException {

        Server newServer = new Server();
        Scene serverSelect = new Scene(CreateServerSelection(),600,600);

        primaryStage.setScene(serverSelect);
        primaryStage.setTitle("My window!");
        primaryStage.show();

    }

    public static GridPane CreateServerUIBox(Server server){
        GridPane pane = new GridPane();
        Button btn1 = new Button(server.World);
        pane.add(btn1,0,0);
        return pane;
    }


    public static GridPane CreateServerSelection(){
        File[] directories = new File(String.valueOf(Paths.get(".").toAbsolutePath())).listFiles(File::isDirectory);
        int numberOfDirectories = directories.length;
        int count = 0;
        GridPane pane = new GridPane();
        for (int i=0;i<2;i++){
            for (int x=0;x<4;x++){
                pane.add(new Button(directories[count].getName()),i,x);
                if (++count==numberOfDirectories)
                    break;
            }
            if (count==numberOfDirectories)
                break;
        }
        return pane;
    }
    public static void main(String args) {
        numberOfServers = Long.parseLong(args);
        Application.launch(args);
    }
}