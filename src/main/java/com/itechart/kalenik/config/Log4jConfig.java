/*
package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4jConfig {
    @Bean
    public ConsoleAppender consoleAppender() {
        ConsoleAppender appender = new ConsoleAppender();
        appender.setThreshold(Level.ALL);

        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p [%c] %m%n");
        appender.setLayout(patternLayout);

        return appender;
    }

    @Bean
    public FileAppender fileAppender() {
        RollingFileAppender appender = new RollingFileAppender();
        appender.setThreshold(Level.ALL);
        appender.setFile("logger.log");
        appender.setMaxFileSize("5MB");
        appender.setMaxBackupIndex(5);

        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d{ABSOLUTE} %-5p [%c{1}] %m%n");
        appender.setLayout(patternLayout);

        return appender;
    }

    @Bean
    public Logger registerLogger() {
        Logger logger = Logger.getRootLogger();
        logger.addAppender(fileAppender());
        logger.addAppender(consoleAppender());
        return logger;
    }
}

*/
