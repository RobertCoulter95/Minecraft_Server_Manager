package main;

import java.io.File;

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
}
