package ru.job4j.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.model.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * DAO layer PostgresSQL implementation for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MusicStore implements Store, Closeable {

    private static final Store INSTANCE = new MusicStore("music.properties");
    private Properties properties = new Properties();
    private BasicDataSource dataSource;

    public MusicStore(String filename) {
        InputStream input = MusicStore.class.getResourceAsStream("/" + filename);
        try {
            properties.load(input);
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            createDatabase();
            dataSource = getDataSource();
            pgcrypto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create postgresSQL database for application from properties file.
     */
    private void createDatabase() {
        String url = "jdbc:postgresql://" + properties.getProperty("hostname") + ":" + properties.getProperty("port") + "/postgres";
        try (Connection srvConnection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));
             PreparedStatement ps = srvConnection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
             ResultSet resultSet = ps.executeQuery()) {
            List<String> databases = new LinkedList<>();
            while (resultSet.next()) {
                databases.add(resultSet.getString(1));
            }
            if (!databases.contains(properties.getProperty("database"))) {
                try (Statement statement = srvConnection.createStatement()) {
                    statement.executeUpdate("CREATE DATABASE " + properties.getProperty("database"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create database connection pool.
     * @return datasource.
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
     * Create pasword encryption extension.
     */
    private void pgcrypto() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE EXTENSION IF NOT EXISTS pgcrypto;");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public void createUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users(id, name, login, password, \"address_ID\", role) VALUES (?, ?, ?, crypt(?, gen_salt('bf')), ?, ?) ON CONFLICT (id) DO NOTHING;")) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, login=?, password=crypt(?, gen_salt('bf')), \"address_ID\"=?, role=? WHERE id=?;")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            statement.setString(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findUserByID(String id) {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                result = Optional.of(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM users;")) {
            while (rs.next()) {
                User current = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createAddress(Address address) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO address(id, address) VALUES (?, ?) ON CONFLICT (id) DO NOTHING;")) {
            statement.setString(1, address.getId());
            statement.setString(2, address.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAddress(Address address) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE address SET address=? WHERE id=?;")) {
            statement.setString(1, address.getAddress());
            statement.setString(2, address.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAddress(Address address) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM address WHERE id=?;")) {
            statement.setString(1, address.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM address;")) {
            while (rs.next()) {
                Address current = new Address(rs.getString(1), rs.getString(2));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Address> findAddressByID(String id) {
        Optional<Address> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address current = new Address(rs.getString(1), rs.getString(2));
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
                result = findUserByID(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Style> getAllStyles() {
        List<Style> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM music_types;")) {
            while (rs.next()) {
                Style current = new Style(rs.getString(1), rs.getString(2));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createStyleEntry(StyleEntry entry) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO music_type_list(id, \"user_ID\", \"type_ID\") VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING;")) {
            statement.setString(1, entry.getId());
            statement.setString(2, entry.getUserID());
            statement.setString(3, entry.getStyleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStyleEntry(StyleEntry entry) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM music_type_list WHERE id=?;")) {
            statement.setString(1, entry.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStyleEntry(StyleEntry entry) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE music_type_list SET \"user_ID\"=?, \"type_ID\"=? WHERE id=?;")) {
            statement.setString(1, entry.getUserID());
            statement.setString(2, entry.getStyleID());
            statement.setString(3, entry.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Style> findStyleByID(String id) {
        Optional<Style> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM music_types WHERE id=?;")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Style current = new Style(rs.getString(1), rs.getString(2));
                result = Optional.of(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Style> getUserStyles(User user) {
        List<Style> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT t.id, t.name FROM music_types AS t JOIN music_type_list AS l ON t.id = l.\"type_ID\" WHERE l.\"user_ID\"=?;")) {
            statement.setString(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Style current = new Style(rs.getString(1), rs.getString(2));
                result.add(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<StyleEntry> getStyleEntries() {
        List<StyleEntry> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM music_type_list;")) {
            while (rs.next()) {
                StyleEntry current = new StyleEntry(rs.getString(1), rs.getString(2), rs.getString(3));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> result = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM roles;")) {
            while (rs.next()) {
                Role current = new Role(rs.getString(1), rs.getBoolean(2));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
