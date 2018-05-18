package ru.job4j.users;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Persistent layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store, Runnable {
    /**
     * Memory store singleton.
     */
    private static final MemoryStore INSTANCE = new MemoryStore("users.properties");
    private BasicDataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(MemoryStore.class);
    private Properties properties = new Properties();

    public MemoryStore(String filename) {
        InputStream input = MemoryStore.class.getResourceAsStream("/" + filename);
        try {
            properties.load(input);
            dataSource = getDataSource();
            createDatabase(properties.getProperty("hostname"), properties.getProperty("port"), properties.getProperty("database"), properties.getProperty("username"), properties.getProperty("password"));
//            createTable();
//            Connection connection = dataSource.getConnection();
//            DriverManager.registerDriver(new org.postgresql.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:postgresql//localhost:5432", "postgres", "postgres");
//            PreparedStatement statement = connection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
//            ResultSet set = statement.executeQuery();
//            while (set.next()) {
//                System.out.println(set.getString(1));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        if (!dataSource.isClosed()) {
            try {
                dataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:postgresql://" + properties.getProperty("hostname") + ":" + properties.getProperty("port"));
            ds.setDriverClassName("org.postgresql.driver");
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
     * Connect to the server and create database if such no present.
     * @param hostname server.
     * @param port TCP port.
     * @param database database name.
     * @param username server user.
     * @param password user password.
     */
    private void createDatabase(String hostname, String port, String database, String username, String password) throws SQLException {
        String url = "jdbc:postgresql://" + hostname + ":" + port;
        try (Connection srvConnection = dataSource.getConnection()) {
            PreparedStatement ps = srvConnection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
            ResultSet resultSet = ps.executeQuery();
            List<String> databases = new LinkedList<>();
            while (resultSet.next()) {
                databases.add(resultSet.getString(1));
            }
            if (!databases.contains(database)) {
                Statement statement = srvConnection.createStatement();
                statement.executeUpdate("CREATE DATABASE " + database);
                LOGGER.info("Database created.");
            }
        } catch (Exception e) {
//            LOGGER.error("Can not connect to the postgres server.", e);
            e.printStackTrace();
        }
    }
    /**
     * Create items table.
     */
    private void createTable() {
        try (Connection localConnection = dataSource.getConnection();
             PreparedStatement statement = localConnection.prepareStatement("CREATE TABLE IF NOT EXISTS users(id character varying NOT NULL, name character varying NOT NULL, login character varying NOT NULL, email character varying NOT NULL, createDate character varying NOT NULL, PRIMARY KEY (id));")) {
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Failed to create table.", e);
            e.printStackTrace();
        }
    }

    /**
     * List of users.
     */
    private List<User> users = new CopyOnWriteArrayList<User>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, User update) {
        users.set(users.indexOf(user), update);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> result =  Optional.empty();
        for (User user : users) {
            if (id.equals(user.getId())) {
                result = Optional.of(user);
            }
        }
        return result;
    }
}
