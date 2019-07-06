package Main_GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
Scene scene2;
public static Server[] serverList;
public static File CurrentWorkingDirectory = new File(String.valueOf(Paths.get("").toAbsolutePath()));

    public static void main(String[] args,Server[] serverPass) throws IOException {
        serverList = serverPass;
        Application.launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(serverSelectionScene(primaryStage));
        primaryStage.show();
    }


    private Scene propertiesScene(int indexOfServer) throws IOException {
        Object[] properties = serverList[indexOfServer].displayProperties();
        VBox box = new VBox();
        for (int i=0;i<properties.length;i++) {
            Label tmp = new Label(properties[i].toString());
            box.getChildren().add(tmp);
        }
        Scene scene3 = new Scene(box,400,400);
        return scene3;

    }

    public Scene serverSelectionScene(Stage primaryStage) throws IOException{

        File file = CurrentWorkingDirectory;
        String[] directories = file.list();
        int numberOfDirectories = directories.length;

        int count = 0;
        VBox box = new VBox();
        box.setPadding(new Insets(50));
        for (int i=0;i<numberOfDirectories;i++) {
            Button tmp = new Button();
            tmp.setPadding(new Insets(15));
            tmp.setMinSize(180, 70);
            tmp.setMaxSize(180, 70);
            tmp.setText(directories[count]);
            tmp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        primaryStage.setScene(propertiesScene(Server.indexOf(serverList,tmp.getText())));
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            box.getChildren().addAll(tmp);
            if (++count == numberOfDirectories)
                break;

        }
        Scene scene = new Scene(box,400,600);
        return scene;
    }
}