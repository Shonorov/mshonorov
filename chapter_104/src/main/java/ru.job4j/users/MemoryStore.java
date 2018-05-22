package ru.job4j.users;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Persistent layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store, Closeable {
    /**
     * Memory store singleton.
     * Database connection pool.
     * Application properties.
     */
    private static final MemoryStore INSTANCE = new MemoryStore("users.properties");
    private BasicDataSource dataSource;
    private Properties properties = new Properties();

    public MemoryStore(String filename) {
        InputStream input = MemoryStore.class.getResourceAsStream("/" + filename);
        try {
            properties.load(input);
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            createDatabase(properties.getProperty("hostname"), properties.getProperty("port"), properties.getProperty("database"), properties.getProperty("username"), properties.getProperty("password"));
            dataSource = getDataSource();
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void close() {
        if (!dataSource.isClosed()) {
            try {
                dataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
     * Create items table if not exists.
     */
    private void createTable() {
        createRolesTable();
        try (Connection localConnection = dataSource.getConnection();
             PreparedStatement statement = localConnection.prepareStatement("CREATE TABLE IF NOT EXISTS users(id character varying NOT NULL, name character varying NOT NULL, login character varying NOT NULL, email character varying NOT NULL, createdate character varying NOT NULL, PRIMARY KEY (id))")) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createRolesTable() {
        String query = "CREATE TABLE IF NOT EXISTS roles("
                + "role character varying NOT NULL,"
                + "administrator boolean NOT NULL,"
                + "PRIMARY KEY (role))";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertRoles();
    }

    private void insertRoles() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO roles(role, administrator) VALUES (?, ?) ON CONFLICT (role) DO NOTHING;")) {
            for (Role role : new Roles().getRoles()) {
                statement.setString(1, role.getRole());
                statement.setBoolean(2, role.isAdministrator());
                statement.executeUpdate();
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUsersTable() {
        //TODO
        String query = "";
    }

    private void insertUsers() {
        //TODO
        String query = "";
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users(id, name, login, email, createdate) VALUES (?, ?, ?, ?, ?);")) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCreateDate().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, User update) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE name=? AND login=? AND email=?;")) {
            statement.setString(1, update.getName());
            statement.setString(2, update.getLogin());
            statement.setString(3, update.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            statement.setString(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM users;")) {
            while (rs.next()) {
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.parse(rs.getString(5)));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.parse(rs.getString(5)));
                result = Optional.of(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
