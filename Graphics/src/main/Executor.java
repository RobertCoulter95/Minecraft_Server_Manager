package main;

import Main_GUI.Main;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class Executor extends Application{
    private static Server[] serverList = new Server[16];
    public static File CurrentWorkingDirectory = new File(String.valueOf(Paths.get("").toAbsolutePath()));


    public static void main(String[] args) throws IOException, InterruptedException {
        //Creates list of servers on startup
        File file = CurrentWorkingDirectory;
        String[] directories = file.list();
        for (int i=0;(i<directories.length) && (directories.length<=16);i++){
            serverList[i]=new Server(directories[i]);
        }


        System.out.println(serverList[0].serverName);
        Thread ThreadedUI = new Thread(){
            public void run(){
                try {
                    Main.main(args,serverList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadedUI.start();

    }
    @Override
    public void start(Stage stage){

    }


}
