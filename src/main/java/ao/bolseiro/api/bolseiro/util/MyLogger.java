package ao.bolseiro.api.bolseiro.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

@SuppressWarnings("rawtypes")
public class MyLogger {
    private Class myClass;
    private Logger logger;

    public MyLogger(Class myClass) {
        this.myClass = myClass;
    }

    public static MyLogger getInstance(Class myClass) {
        return new MyLogger(myClass);
    }

    public void severe(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        getLogger().severe(sw.toString());
    }

    public void severe(String message, Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        getLogger().severe(message + ": " + sw.toString());
    }

    public void severe(String logMessage) {
        getLogger().severe(logMessage);
    }

    public void fine(String logMessage) {
        getLogger().fine(logMessage);
    }

    public void warning(String logMessage) {
        getLogger().warning(logMessage);
    }

    public void info(String logMessage) {
        getLogger().info(logMessage);
    }

    public void config(String logMessage) {
        getLogger().config(logMessage);
    }

    public Class getMyClass() {
        return myClass;
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(myClass.getName());
        }
        return logger;
    }

}
