package org.java.dev.configuration;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.java.dev.util.Constant;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class FlywayConfiguration {
    private Flyway flyway;
    public FlywayConfiguration setup() throws IOException {

        AppProperties app = AppProperties.load();

        String url = app.getProperty(Constant.CONNECTION_URL);
        String username = app.getProperty(Constant.CONNECTION_USERNAME);
        String password = app.getProperty(Constant.CONNECTION_PASSWORD);

        Location migrations = new Location(app.getProperty(Constant.FLYWAY_MIGRATION_SQL_DIR));

        flyway = Flyway.configure()
                .encoding(StandardCharsets.UTF_8)
                .locations(migrations)
                .dataSource(url, username, password)
                .loggers(app.getProperty(Constant.FLYWAY_LOGGER))
                .sqlMigrationPrefix("V")
                .createSchemas(false)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .placeholderReplacement(false)
                .failOnMissingLocations(true)
                .load();
        return this;
    }
    public void migrate() {
        flyway.migrate();
    }
}
