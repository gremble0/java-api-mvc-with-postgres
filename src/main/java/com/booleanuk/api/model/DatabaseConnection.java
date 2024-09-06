package com.booleanuk.api.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

abstract public class DatabaseConnection {
  public static Connection getConnection() throws SQLException {
    DatabaseConfig config = DatabaseConnection.readConfig();
    return DatabaseConnection.connectToDatabase(config);
  }

  private static DatabaseConfig readConfig() {
    try {
      InputStream inputStream = new FileInputStream("src/main/resources/config.properties");
      Properties properties = new Properties();
      properties.load(inputStream);

      String dbUser = properties.getProperty("db.user");
      String dbUrl = properties.getProperty("db.url");
      String dbPassword = properties.getProperty("db.password");
      String dbDatabase = properties.getProperty("db.database");

      return new DatabaseConfig(dbUser, dbUrl, dbPassword, dbDatabase);
    } catch (IOException e) {
      // TODO: improve this
      return null;
    }
  }

  private static Connection connectToDatabase(DatabaseConfig config) throws SQLException {
    final String url = String.format("jdbc:postgresql://%s:5432/%s?user=%s&password=%s", config.dbUrl(),
        config.dbDatabase(), config.dbUser(), config.dbPassword());

    final PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setURL(url);

    return dataSource.getConnection();
  }

  public List<Employee> get() {
    return null;
  }
}
