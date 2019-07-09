package main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class Server {
    private static int servercount =0;
    private static String JavaLocation = System.getProperty("java.home") + "/bin/java";
    String serverName ="";
    private File ServersDirectory;
    public String serverIP;
    public String serverPort;
    int memoryUsageMB;
    String[] propKeys;
    FileOutputStream out;
    Properties props;
    Properties temp;
    Process p;
    boolean hasSaved;
    public boolean isRunning;


    public Server(String name) throws IOException {
        props = new Properties();
        serverName = name;
        ServersDirectory = new File(String.valueOf(Paths.get("").toAbsolutePath())+"/"+serverName);
        servercount++;
        setProperties("server-port",Integer.toString(25565+servercount));

        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");
        props.store(out, null);
        out.close();
        setKeySet();
    }

    //Says duplicate code, but should cause no errors, as long as you dont have setproperties
    //and get property open at the same time.


    public void setProperties(String property, String values) throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");
        props.setProperty(property, values);
        props.store(out, null);
        out.close();
    }

//    public String getProperty(String object) throws IOException {
//        FileInputStream in = new FileInputStream(serverName + "/server.properties");
//        props = new Properties();
//        props.load(in);
//        in.close();
//
//        FileOutputStream out = new FileOutputStream(serverName+"/server.properties");
//        String key = props.getProperty("level-seed");
//        props.store(out,null);
//        out.close();
//        return key;
//
//    }

    public String[][] displayProperties() throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        props.load(in);
        in.close();

        out = new FileOutputStream(serverName+"/server.properties");
        int length = props.stringPropertyNames().toArray().length;
        String[][] key = new String[2][length];

        for(int i=0;i<length;i++){
            key[0][i]=props.stringPropertyNames().toArray()[i].toString();
            key[1][i]=props.get(key[0][i]).toString();
        }
        //props.store(out,null);
        //out.close();
        hasSaved = false;
        return key;


    }

    public void startServer(){
        Thread ThreadedServer = new Thread(){
            public void run(){
                isRunning=true;
                ProcessBuilder pb = new ProcessBuilder(JavaLocation, "-jar", "server.jar", "Xmx512", "Xms512");
                pb.directory(ServersDirectory);
                System.out.println("Server " + serverName + " is starting");
                try {
                    p = pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadedServer.start();

    }

    public void stopServer(){
        isRunning = false;
        p.destroy();
    }


    public static int indexOf(Server[] server,String name){
        for(int i=0;i<server.length;i++){
            if (server[i].serverName.equals(name)){
                return i;
            }
        }
        return -1;
    }

    public void initializeTempProperties() throws IOException {
        FileInputStream in = new FileInputStream(serverName + "/server.properties");
        temp = new Properties();
        temp.load(in);
        in.close();

        out = new FileOutputStream(serverName+"/server.properties");
        //props.store(out,null);
    }



    public void setTempProperties(String newValue,int z) throws IOException {
        temp.setProperty(propKeys[z],newValue);
    }

    public void saveProperties() throws IOException {
        temp.store(out,null);
        hasSaved = true;
        out.close();
    }

    public void saveOldProperties() throws IOException {
        if (!hasSaved){
            props.store(out,null);
            out.close();
        }
    }

    private Set<Object> getAllKeys(){
        Set<Object> keys = props.keySet();
        return keys;
    }

    public void setKeySet(){

        Set<Object> keys = getAllKeys();
        propKeys = new String[keys.size()];
        int i =0;
        for(Object k:keys){
            String key = (String)k;
            propKeys[i++]=key;
        }
    }
}
