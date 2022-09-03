package server.logging;

public class Log {

    public void basicConf(String filename){
        filename = "Example";
    }

    public static void info(Object message){
        System.out.println(message);
    }

    public static void warning(Object message){
        System.err.println(message);
    }

    public static void error(Object message){
        System.err.println(message);
    }

    public static void critical(Object message){
        System.err.println(message);
    }
}
