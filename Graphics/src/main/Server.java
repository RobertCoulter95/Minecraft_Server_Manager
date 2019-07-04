package main;

import java.io.*;
import java.util.Properties;

public class Server {
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


    public Server() throws IOException {
        FileInputStream in = new FileInputStream("server.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("server.properties");
        props.setProperty("level-seed", "america");
        props.store(out, null);
        out.close();
    }
}
