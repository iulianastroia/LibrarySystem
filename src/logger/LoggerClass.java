package logger;

import java.util.logging.*;

/**
 * @author Iuliana
 */
public class LoggerClass {
    Logger logger = Logger.getLogger("MyLog");

    FileHandler fileHandler;

    public void writeToFile(Exception ex) {
        try {
            //logger configured with handler and formatter
            fileHandler = new FileHandler("logging.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            writeLog(ex, logger);

        } catch (Exception e) {
            writeLog(e, logger);
        }
    }

    //    pass exception to log
    public void writeLog(Exception e, Logger logger) {
        logger.info(e.toString());

//        logger.info(logger.getName());
    }
}
