package ru.job4j.users;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

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
            pgcrypto();
//            liquibaseUpdate();
//            initDB();
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
     * Update database structure with liquibase plugin.
     */
    private void liquibaseUpdate() {
        try (Connection connection = dataSource.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase("db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
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
//    /**
//     * Create tables and builtin users.
//     * Create password encryption extension.
//     */
//    private void initDB() {
//        createRolesTable();
//        createUsersTable();
//        builtinRoles();
//        pgcrypto();
//        builtinUsers();
//    }
//
//    /**
//     * Create table for user roles at startup.
//     */
//    private void createRolesTable() {
//        String query = "CREATE TABLE IF NOT EXISTS roles("
//                + "role character varying NOT NULL,"
//                + "administrator boolean NOT NULL,"
//                + "PRIMARY KEY (role));";
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Add builtin roles at startup.
//     */
//    private void builtinRoles() {
//        List<Role> builtin = new ArrayList<>();
//        builtin.add(new Role("administrator", true));
//        builtin.add(new Role("user", false));
//        for (Role role : builtin) {
//            addRole(role);
//        }
//    }
//
//    /**
//     * Create users table at startup.
//     */
//    private void createUsersTable() {
//        String query = "CREATE TABLE IF NOT EXISTS users("
//                + "id character varying NOT NULL,"
//                + "name character varying NOT NULL,"
//                + "login character varying NOT NULL,"
//                + "email character varying NOT NULL,"
//                + "createdate character varying NOT NULL,"
//                + "password character varying NOT NULL,"
//                + "role character varying NOT NULL,"
//                + "CONSTRAINT users_pkey PRIMARY KEY (id),"
//                + "CONSTRAINT user_role FOREIGN KEY (role)"
//                + "REFERENCES public.roles (role) MATCH SIMPLE);";
//        try (Connection localConnection = dataSource.getConnection();
//             Statement statement = localConnection.createStatement()) {
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Create password encryption extension.
//     * Add builtin user at startup.
//     */
//    private void builtinUsers() {
//        List<User> builtin = new ArrayList<>();
//        builtin.add(new User("0", "admin", "admin", "admin@contoso.com", LocalDateTime.now(), "admin", "administrator"));
//        builtin.add(new User("1", "guest", "guest", "guest@contoso.com", LocalDateTime.now(), "guest", "user"));
//        for (User user : builtin) {
//            add(user);
//        }
//    }

    private void pgcrypto() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE EXTENSION IF NOT EXISTS pgcrypto;");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users(id, name, login, email, createdate, password, role, country_id, city_id) VALUES (?, ?, ?, ?, ?, crypt(?, gen_salt('bf')), ?, ?, ?) ON CONFLICT (id) DO NOTHING;")) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCreateDate().toString());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRole());
            statement.setString(8, user.getCountry());
            statement.setString(9, user.getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void addRole(Role role) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO roles(role, administrator) VALUES (?, ?) ON CONFLICT (role) DO NOTHING;")) {
//            statement.setString(1, role.getRole());
//            statement.setBoolean(2, role.isAdministrator());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<Role> getRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM roles;")) {
            while (rs.next()) {
                result.add(new Role(rs.getString("role"), rs.getBoolean("administrator")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(User user, User update) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, login=?, email=?, password=crypt(?, gen_salt('bf')), role=?, country_id=?, city_id=? WHERE name=? AND login=? AND email=? AND role=? AND country_id=? AND city_id=?;")) {
            statement.setString(1, update.getName());
            statement.setString(2, update.getLogin());
            statement.setString(3, update.getEmail());
            statement.setString(4, update.getPassword());
            statement.setString(5, update.getRole());
            statement.setString(6, update.getCountry());
            statement.setString(7, update.getCity());
            statement.setString(8, user.getName());
            statement.setString(9, user.getLogin());
            statement.setString(10, user.getEmail());
            statement.setString(11, user.getRole());
            statement.setString(12, user.getCountry());
            statement.setString(13, user.getCity());
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
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.parse(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
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
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.parse(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                result = Optional.of(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?;")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), LocalDateTime.parse(rs.getString(5)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                result = Optional.of(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<User> checkCredentials(String login, String password) {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login=? AND password = crypt(?, password);")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = findById(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM country;")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Country current = new Country(rs.getString(1), rs.getString(2));
                countries.add(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public List<City> getCitiesByCountryName(String name) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE country_id=?;")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                City current = new City(rs.getString(1), rs.getString(2), rs.getString(3));
                cities.add(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM city;")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                City current = new City(rs.getString(1), rs.getString(2), rs.getString(3));
                cities.add(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public String getCountryID(String name) {
        String id = "";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM country WHERE name=?;")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getCityID(String name) {
        String id = "";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM city WHERE name=?;")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getCountryName(String id) {
        String name = "";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM country WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String getCityName(String id) {
        String name = "";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM city WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
