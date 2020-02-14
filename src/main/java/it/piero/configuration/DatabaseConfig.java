package it.piero.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private Properties properties = new Properties();
    private Connection connection = null;

    public Connection getConnection() {
        InputStream is = null;

        is = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties");

        try {
            properties.load(is);

            Class.forName(properties.getProperty("datasource.driverClassName"));
            connection = DriverManager.getConnection(
                    properties.getProperty("datasource.url"),
                    properties.getProperty("datasource.username"),
                    properties.getProperty("datasource.password"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
