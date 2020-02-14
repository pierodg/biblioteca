package it.piero.repository;

import it.piero.configuration.DatabaseConfig;

import java.sql.Connection;

public class GenericDAO {

    protected Connection connection;

    public GenericDAO() {
        DatabaseConfig databaseConnection = new DatabaseConfig();
        connection = databaseConnection.getConnection();
    }

}
