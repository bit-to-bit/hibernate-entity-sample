package org.java.dev.util;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {
    //Common
    public static final String DEFAULT_APP_FILE_NAME = "application.properties";
    public static final String CONNECTION_URL = "com.h2.url";
    public static final String CONNECTION_USERNAME = "com.h2.username";
    public static final String CONNECTION_PASSWORD = "com.h2.password";

    // Flyway
    public static final String FLYWAY_MIGRATION_SQL_DIR = "org.flywaydb.migration.sql.dir";
    public static final String FLYWAY_LOGGER = "org.flywaydb.logger";
    public static final String LOG_PATTERN = "log4j.conversion.pattern";
    public static final String LOG_FILE = "log4j.appender.file";
    public static final String LOG_LEVEL = "log4j.logger.level";
    public static final String LOG_ENCODING = "log4j.logger.encoding";

    // Hibernate
    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_ENTITY_PACKAGE = "hibernate.entity.package";
    public static final String HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_JAKARTA_VALIDATION_MODE = "hibernate.jakarta_validation_mode";
}
