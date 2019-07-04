package main;

import Main_GUI.Main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Executor {
    private static String JavaLocation = System.getProperty("java.home") + "/bin/java";

    public static void main(String[] args) throws IOException, InterruptedException {
        //count the number of servers
        long count = Files.find(Paths.get("/"),1,(path, attributes)->attributes.isDirectory()).count()-1;



        //Calls module Graphics, to open graphics.

        Thread ThreadedUI = new Thread(){
            public void run(){
                Main.main(count+"");
            }
        };





        Thread ThreadedServer = new Thread(){
            public void run(){
                ProcessBuilder pb = new ProcessBuilder(JavaLocation, "-jar", "server.jar", "Xmx512", "Xms512");
                try {
                    Process p = pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        //ThreadedServer.start();
        ThreadedUI.start();

        //System.out.println(System.getProperty("java.home"));

    }
}
