package main;

import Main_GUI.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;



public class Executor {
    private static String JavaLocation = System.getProperty("java.home") + "/bin/java";

    public static void main(String[] args) throws IOException, InterruptedException {
        //Calls module Graphics, to open graphics.


        Thread ThreadedUI = new Thread(){
            public void run(){
                Main.main("Start");
            }
        };

        Thread ThreadedServer = new Thread(){
            public void run(){
                ProcessBuilder pb = new ProcessBuilder(JavaLocation, "-jar", "server.jar");
                try {
                    Process p = pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadedServer.start();
        ThreadedUI.start();

        //System.out.println(System.getProperty("java.home"));

    }
}
