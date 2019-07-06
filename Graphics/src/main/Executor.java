package main;

import Main_GUI.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Executor extends Application{
    private static String JavaLocation = System.getProperty("java.home") + "/bin/java";
    private static File ServersDirectory = new File(String.valueOf(Paths.get("").toAbsolutePath())+"/server");

    public static void main(String[] args) throws IOException, InterruptedException {
        //count the number of servers
        long count = Files.find(Paths.get("/"),1,(path, attributes)->attributes.isDirectory()).count()-1;
        Server server = new Server("server");
        server.displayProperties();

        //System.out.println(server.getProperty("world-seed"));
        //Calls module Graphics, to open graphics.

        Thread ThreadedUI = new Thread(){
            public void run(){
                try {
                    Main.main(count+"",server);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread ThreadedServer = new Thread(){
            public void run(){
                ProcessBuilder pb = new ProcessBuilder(JavaLocation, "-jar", "server.jar", "Xmx512", "Xms512");
                pb.directory(ServersDirectory);
                System.out.println(pb.directory().toString());
                try {
                    Process p = pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadedServer.start();
        //ThreadedUI.start();

        //System.out.println(System.getProperty("java.home"));

    }
    @Override
    public void start(Stage stage){

    }
}
