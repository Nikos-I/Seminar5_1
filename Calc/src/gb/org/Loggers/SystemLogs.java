package gb.org.Loggers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SystemLogs {
    private Logger logger;
    public SystemLogs() {
        try {
            LogManager.getLogManager().readConfiguration(SystemLogs.class.getResourceAsStream("/logging.properties"));
            logger = Logger.getLogger(SystemLogs.class.getName());
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
