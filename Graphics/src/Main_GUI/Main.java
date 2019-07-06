package Main_GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {
public static long numberOfServers;
public static File CurrentWorkingDirectory = new File(String.valueOf(Paths.get("").toAbsolutePath()));
public static Server server;
    @Override
    public void start(Stage primaryStage) throws IOException {

        Scene scene = propertiesScene();
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setScene(serverSelectionScene());
        primaryStage.show();

    }


    public Scene propertiesScene() throws IOException {
        Object[] properties = server.displayProperties();
        VBox box = new VBox();
        for (int i=0;i<properties.length;i++) {
            Label tmp = new Label(properties[i].toString());
            box.getChildren().add(tmp);
        }
        Scene scene = new Scene(box,400,400);
        return scene;

    }

    public Scene serverSelectionScene() throws IOException{

        File file = CurrentWorkingDirectory;
        String[] directories = file.list();
        int numberOfDirectories = directories.length;


        //Count starts at 1 to avoid the .DS folder at 0.
        int count = 0;
        VBox box = new VBox();
        box.setPadding(new Insets(50));
        for (int i=0;i<numberOfDirectories;i++) {
            Button tmp = new Button();
            tmp.setPadding(new Insets(15));
            tmp.setMinSize(180, 70);
            tmp.setMaxSize(180, 70);
            tmp.setText(directories[count]);
            box.getChildren().addAll(tmp);
            if (++count == numberOfDirectories)
                break;

        }
        System.out.println(numberOfDirectories);
        if (numberOfDirectories<9){
            Button tmp = new Button("Create Server");
            tmp.setPadding(new Insets(15));
            tmp.setMinSize(180, 70);
            tmp.setMaxSize(180, 70);
            box.getChildren().add(tmp);
        }
        Scene scene = new Scene(box,400,600);
        return scene;
    }
    public static void main(String args,Server serv) throws IOException {
        server = serv;
        Application.launch(args);
    }
}