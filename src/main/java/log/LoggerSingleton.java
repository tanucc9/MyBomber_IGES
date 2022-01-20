package log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerSingleton {
    private final String logFilePath = "MyBomber_log/log_file.txt";
    private PrintWriter writer;

    // Singleton
    private static LoggerSingleton logger = null;

    private LoggerSingleton() {
        try {
            File file = new File(logFilePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file, true);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {}
    }

    public static synchronized LoggerSingleton getInstance(){
        if(logger == null)
            logger = new LoggerSingleton();
        return logger;
    }

    public void info(String message) {
        writer.println("### INFO ### : " + message);
    }

    public void debug(String message) {
        writer.println("### DEBUG ### : " + message);
    }

    public void warning(String message) {
        writer.println("### WARNING ### : " + message);
    }

    public void error(String message) {
        writer.println("### ERROR ### : " + message);
    }
}
