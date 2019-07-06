package main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class Server {
    String serverName;
    //Basic settings
    public String World = "Minecraft world 1";
    public File properties;
    public boolean isRunning;
    public int playerSlots;
    public String gameMode;
    public String levelSeed;

    //Advanced settings
    public String serverIP;
    public String serverPort;
    int memoryUsageMB;


    public Server(String name) throws IOException {
        serverName = name;
    }

    //Says duplicate code, but should cause no errors, as long as you dont have setproperties
    //and get property open at the same time.
    public void setProperties(String[]property, String[] values) throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");

        for (int i=0;i<property.length;i++){
            props.setProperty(property[i], values[i]);
        }
        props.store(out, null);
        out.close();
    }

    public String getProperty(String object) throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");
        String key = props.getProperty("level-seed");
        props.store(out,null);
        out.close();
        return key;

    }

    public Object[] displayProperties() throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");
        Object[] key = props.stringPropertyNames().toArray();
        System.out.println(key[8].toString());
        props.store(out,null);
        out.close();
        return key;


    }


}
