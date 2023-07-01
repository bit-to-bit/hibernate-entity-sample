package org.java.dev.configuration;
import org.apache.log4j.*;
import org.java.dev.util.Constant;
public class LoggingConfiguration {
    private static final AppProperties app = AppProperties.load();

    static {

        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(app.getProperty(Constant.LOG_PATTERN));

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.setEncoding(app.getProperty(Constant.LOG_ENCODING));
        consoleAppender.activateOptions();

        DailyRollingFileAppender rollingFileAppender = new DailyRollingFileAppender();
        rollingFileAppender.setEncoding(app.getProperty(Constant.LOG_ENCODING));
        rollingFileAppender.setFile(app.getProperty(Constant.LOG_FILE));
        rollingFileAppender.setLayout(layout);
        rollingFileAppender.setDatePattern("'.'yyyy-MM-dd");
        rollingFileAppender.activateOptions();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.toLevel(app.getProperty(Constant.LOG_LEVEL)));
        rootLogger.removeAllAppenders();
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(rollingFileAppender);
    }
}

