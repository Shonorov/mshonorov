package ru.job4j.spring;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JdbcDao implements Closeable {

    private static final JdbcDao INSTANCE = new JdbcDao();
    private BasicDataSource dataSource;
    private Properties properties = new Properties();

    private JdbcDao() {
        InputStream input = JdbcStorage.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(input);
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            createDatabase(properties.getProperty("hostname"), properties.getProperty("port"), properties.getProperty("database"), properties.getProperty("username"), properties.getProperty("password"));
            dataSource = getDataSource();
            createUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcDao getInstance() {
        return INSTANCE;
    }

    /**
     * Connect to the server and create database if such no present.
     * @param hostname server.
     * @param port TCP port.
     * @param database database name.
     * @param username server user.
     * @param password user password.
     */
    private void createDatabase(String hostname, String port, String database, String username, String password) throws SQLException {
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/postgres";
        try (Connection srvConnection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = srvConnection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
             ResultSet resultSet = ps.executeQuery()) {
            List<String> databases = new LinkedList<>();
            while (resultSet.next()) {
                databases.add(resultSet.getString(1));
            }
            if (!databases.contains(database)) {
                try (Statement statement = srvConnection.createStatement()) {
                    statement.executeUpdate("CREATE DATABASE " + database);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create database connection pool.
     * @return datasource connection pool.
     */
    private BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:postgresql://" + properties.getProperty("hostname") + ":" + properties.getProperty("port") + "/" + properties.getProperty("database"));
            ds.setUsername(properties.getProperty("username"));
            ds.setPassword(properties.getProperty("password"));
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
            dataSource = ds;
        }
        return dataSource;
    }

    /**
     //     * Create table for user roles at startup.
     //     */
    private void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users("
            + "name character varying NOT NULL,"
            + "PRIMARY KEY (name));";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(User user) {
        System.out.println("Store " + user.getName() + " to database!");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name) VALUES (?) ON CONFLICT (name) DO NOTHING");
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                result.add(new User(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if (!dataSource.isClosed()) {
            try {
                dataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

