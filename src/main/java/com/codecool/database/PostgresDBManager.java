package com.codecool.database;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PostgresDBManager {
    public DataSource connect() {
        try {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();

            ApplicationProperties properties = new ApplicationProperties();
            dataSource.setDatabaseName(properties.readProperty("database"));
            dataSource.setUser(properties.readProperty("user"));
            dataSource.setPassword(properties.readProperty("password"));

            System.out.println("Trying to connect...");
            dataSource.getConnection().close();
            System.out.println("Connection OK");

            return dataSource;
        } catch (SQLException e) {
            System.err.println("Could not connect to the database.");
        }
        return null;
    }
}
