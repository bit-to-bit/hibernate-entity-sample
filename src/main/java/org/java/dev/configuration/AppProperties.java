package org.java.dev.configuration;

import java.util.Properties;

import static org.java.dev.util.Constant.DEFAULT_APP_FILE_NAME;

public class AppProperties {
    private final Properties properties;

    public AppProperties(Properties properties) {
        this.properties = properties;
    }

    public static AppProperties load() {
        try {
            Properties properties = new Properties();
            properties.load(AppProperties.class.getClassLoader().getResourceAsStream(DEFAULT_APP_FILE_NAME));
            return new AppProperties(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getObject(String key) {
        return properties.get(key);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
