package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;

/**
 * Class for items store and management.
 * @author MShonorov (shonorov@gmail.com).
 * @version $Id$.
 * @since 0.1.
 */
public class Tracker implements AutoCloseable {
    /**
     * Connection properties.
     * Database connection.
     */
    private Properties properties = new Properties();

    private Connection connection = null;

    public Tracker(String filename) {
        try {
            InputStream input = Tracker.class.getResourceAsStream("/" + filename);
            properties.load(input);
            setConnection();
            createTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect the to target database.
     */
    private void setConnection() {
        String hostname = properties.getProperty("hostname");
        String port = properties.getProperty("port");
        String database = properties.getProperty("database");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String dbUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
        createDatabase(hostname, port, database, username, password);
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
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
    private void createDatabase(String hostname, String port, String database, String username, String password) {
        String url = "jdbc:postgresql://" + hostname + ":" + port;
        try (Connection srvConnection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ps = srvConnection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
            ResultSet resultSet = ps.executeQuery();
            List<String> databases = new LinkedList<>();
            while (resultSet.next()) {
                databases.add(resultSet.getString(1));
            }
            if (!databases.contains(database)) {
                Statement statement = srvConnection.createStatement();
                statement.executeUpdate("CREATE DATABASE " + database);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create items table.
     */
    private void createTable() {
        try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS items(id text NOT NULL,name text NOT NULL, \"desc\" text, created bigint, PRIMARY KEY (id))")) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clean items table.
     */
    public void cleanTable() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM items")) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     * @return added item.
     */
    public Item add(Item item) {
        String id = generateId();
        item.setId(id);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO items(id, name, \"desc\", created) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, id);
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesc());
            statement.setLong(4, item.getCreated());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    /**
     * Replace one item by another by ID.
     * @param id to replace.
     * @param item replace by.
     */
    public void replace(String id, Item item) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE items SET id=?, name=?, \"desc\"=?, created=? WHERE id = ?")) {
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesc());
            statement.setLong(4, item.getCreated());
            statement.setString(5, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes item from array by id.
     * @param id to delete.
     */
    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns all current items.
     * @return items array.
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item current = new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
                result.add(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Finds items by name.
     * @param key search name.
     * @return result array.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE name LIKE ?")) {
            statement.setString(1, "%" + key + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item current = new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
                result.add(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Finds item by id string.
     * @param id string.
     * @return item if found, null if not found.
     */
    public Item findByID(String id) {
        Item result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Метод генерирует уникальный ключ для заявки на основании времени и произвольного числа.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return LocalTime.now().toString() + (short) (Math.random() * 10000);
    }
}