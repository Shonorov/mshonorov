package ru.job4j.parser;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
/**
 * Vacancy database management class..
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class VacancyStore implements Closeable {
    /**
     * Properties file name.
     * Properties.
     * Database connection.
     * Application loger.
     */
    private String filename;
    private Properties properties = new Properties();
    private Connection connection = null;
    private final static Logger LOGGER = Logger.getLogger(VacancyStore.class);

    public VacancyStore(String filename) {
        try {
            this.filename = filename;
            InputStream input = VacancyStore.class.getResourceAsStream("/" + filename);
            properties.load(input);
            setConnection();
            createTable();
        } catch (IOException e) {
            LOGGER.error("Can not read properties.", e);
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
            LOGGER.info("Database connected successfully.");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            LOGGER.error("Database connection failed!", e);
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
        try (Connection srvConnection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = srvConnection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
             ResultSet resultSet = ps.executeQuery()) {
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
            LOGGER.error("Can not connect to the postgres server.", e);
        }
    }

    /**
     * Create items table.
     */
    private void createTable() {
        try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS vacancies(id serial NOT NULL, name character varying NOT NULL, url character varying NOT NULL, PRIMARY KEY (id));")) {
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Failed to create table!", e);
        }
    }

    /**
     * Clean items table.
     */
    public void cleanTable() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM vacancies")) {
            statement.executeUpdate();
            LOGGER.info("Database purged.");
        } catch (Exception e) {
            LOGGER.error("Database purge failed!", e);
        }
    }

    /**
     * Add vacancy to database.
     * @param title new vacancy title.
     * @param url new vacancy url.
     */
    private void add(String title, String url) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO public.vacancies(id, name, url) VALUES (DEFAULT, ?, ?);")) {
            statement.setString(1, title);
            statement.setString(2, url);
            statement.executeUpdate();
            LOGGER.info("Vacancy inserted: " + title + " " + url);
        } catch (Exception e) {
            LOGGER.error("Insert failed!", e);
        }
    }

    /**
     * Add all found vacancies.
     * @param vacancies list of vacancies.
     */
    public void addAll(ArrayList<Vacancy> vacancies) throws SQLException {
        vacancies.removeAll(findAll());
        try {
            for (Vacancy vacancy : vacancies) {
                add(vacancy.getTitle(), vacancy.getUrl());
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Mass INSERT failed. Rolling back!", e);
            connection.rollback();
        }
        writeDate();
    }

    /**
     * Write last run date to the properties file.
     */
    private void writeDate() {
        try {
            properties.setProperty("lastrun", new Date().toString());
            OutputStream output = new FileOutputStream(new File("src/main/resources/" + filename));
            properties.store(output, "Last run date:");
        } catch (IOException e) {
            LOGGER.error("Can not wright date!", e);
        }
    }

    /**
     * Find all vacancies.
     * @return all vacancies from database.
     */
    public ArrayList<Vacancy> findAll() {
        ArrayList<Vacancy> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM vacancies");
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Vacancy current = new Vacancy(rs.getString(2), rs.getString(3));
                result.add(current);
            }
        } catch (Exception e) {
            LOGGER.error("SELECT operation failed!", e);
        }
        return result;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.info("Database connection closed.");
            } catch (SQLException e) {
                LOGGER.info("Database connection failed!", e);
            }
        }
    }
}
