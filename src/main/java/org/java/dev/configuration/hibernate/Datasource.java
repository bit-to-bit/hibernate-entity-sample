package org.java.dev.configuration.hibernate;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.Configuration;
import org.java.dev.configuration.FlywayConfiguration;
import org.java.dev.configuration.LoggingConfiguration;

import java.io.IOException;
@Log4j
public class Datasource {

    private final Configuration configuration;
    private final SessionFactory sessionFactory;

    public Datasource() {
        this.configuration = HibernateConfiguration.get();
        this.sessionFactory = createSessionFactory();
        try {
            new LoggingConfiguration();
            new FlywayConfiguration().setup().migrate();
        } catch (IOException e) {
            log.info("Datasource init fail", e);
        }

    }
    public Session openSession() {
        return this.sessionFactory.openSession();
    }

    private SessionFactory createSessionFactory() {
        final ClassLoaderService classLoaderService = new ClassLoaderServiceImpl(getClass().getClassLoader());
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .addService(ClassLoaderService.class, classLoaderService)
                        .build()
        );
    }
}