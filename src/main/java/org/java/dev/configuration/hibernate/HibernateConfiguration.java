package org.java.dev.configuration.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.java.dev.configuration.AppProperties;
import org.java.dev.entity.ClientEntity;
import org.java.dev.entity.PlanetEntity;
import org.java.dev.entity.TicketEntity;
import org.java.dev.util.Constant;

@Slf4j
public class HibernateConfiguration {
    private HibernateConfiguration() {
    }

    public static Configuration get() {
        AppProperties app = AppProperties.load();
        Configuration configuration = new Configuration();
        configuration.setProperty(AvailableSettings.DRIVER, app.getProperty(Constant.HIBERNATE_CONNECTION_DRIVER_CLASS));
        configuration.setProperty(AvailableSettings.URL, app.getProperty(Constant.HIBERNATE_CONNECTION_URL));
        configuration.setProperty(AvailableSettings.SHOW_SQL, app.getProperty(Constant.HIBERNATE_SHOW_SQL));
        configuration.setProperty(AvailableSettings.DIALECT, app.getProperty(Constant.HIBERNATE_DIALECT));
        configuration.setProperty(AvailableSettings.USER, app.getProperty(Constant.HIBERNATE_CONNECTION_USERNAME));
        configuration.setProperty(AvailableSettings.PASS, app.getProperty(Constant.HIBERNATE_CONNECTION_PASSWORD));
        configuration.setProperty(AvailableSettings.DEFAULT_SCHEMA, app.getProperty(Constant.HIBERNATE_DEFAULT_SCHEMA));
        configuration.addAnnotatedClass(ClientEntity.class);
        configuration.addAnnotatedClass(PlanetEntity.class);
        configuration.addAnnotatedClass(TicketEntity.class);
        return configuration;
    }
}