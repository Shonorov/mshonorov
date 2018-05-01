package ru.job4j.xmlxslt;

import java.sql.*;
/**
 * SQLIte JDBC connection class.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SQLiteStorage {
    /**
     * Database root path.
     * Database connection uri.
     * Element count.
     */

    private static final String PATH = "C:\\Windows\\temp\\";
    private String uri;
    private int count;

    private Connection connection = null;

    public SQLiteStorage(String database, int count) {
        this.uri = "jdbc:sqlite:" + PATH + database;
        this.count = count;
    }

    public void setDatabase(String database) {
        this.uri = "jdbc:sqlite:" + PATH + database;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Connect, clean and write/read table.
     * @return written elements from table.
     */
    public int[] process() {
        int[] result = new int[count];
        try {
            connection = DriverManager.getConnection(uri);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE if EXISTS test");
            statement.executeUpdate("CREATE TABLE test (field INTEGER PRIMARY KEY AUTOINCREMENT)");
            insert(statement);
            result = select(statement);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Insert N values to the test table.
     * @param statement to use.
     */
    private void insert(Statement statement) throws SQLException {
        try {
            for (int i = 0; i < count; i++) {
                statement.executeUpdate("INSERT INTO test (field) VALUES (NULL)");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Selects all values from field.
     * @param statement to use.
     * @return field values.
     */
    private int[] select(Statement statement) {
        int[] result = new int[count];
        try {
            ResultSet resultSet = statement.executeQuery("SELECT field FROM test");
            for (int i = 0; i < count; i++) {
                resultSet.next();
                result[i] = resultSet.getInt("field");
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
