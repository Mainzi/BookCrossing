package com.library.database;

import com.library.config.DataConfig;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseLibrary {

    public DatabaseLibrary() {
    }

    public static void main(String[] args) {
        DatabaseLibrary databaseLibrary = new DatabaseLibrary();

        try {
            Class.forName(DataConfig.DRIVER);
            Connection connection = DriverManager
                    .getConnection(DataConfig.URL, DataConfig.USER, DataConfig.PASSWORD);

            // Демонстрация использования транзакций
            try {
                // Шаг первый - выставляем свойство AutoCommit в false
                connection.setAutoCommit(false);
                // В цикле вставлем несколько записей
//                for (int i = 0; i < 5; i++) {
//                    long contactId = databaseLibrary.insert(connection, "Title_" + i);
//                    System.out.println("book_id:" + contactId);
//                }
//                // Завершаем транзакцию - подтверждаем
//                connection.commit();
                // Вызов rollback отменит все внесенные изменения
                //connection.rollback();

                // Возвращаем свойство AutoCommit в true
                connection.setAutoCommit(true);
//                databaseLibrary.insert(connection, "Title_" + 39);
                // Можно проверить результат
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM get_categories");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
                // databaseLibrary.select(connection);
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println(cause.toString());
        }
    }

    private long insert(Connection connection, String name) throws SQLException {
        long contactId = -1;
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO book (title, description,publisher_id) VALUES (?,?,1)", new String[]{"book_id"});
        stmt.setString(1, null);
        stmt.setString(2, "описание");
        stmt.executeUpdate();

        ResultSet gk = stmt.getGeneratedKeys();
        while (gk.next()) {
            contactId = gk.getLong("book_id");
        }
        stmt.close();
        return contactId;
    }


    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager
                    .getConnection(DataConfig.URL, DataConfig.USER, DataConfig.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCategories(Connection connection) {
        ArrayList<String> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_categories");
            while (resultSet.next()) {
                categories.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public ArrayList<String> getLibraries(Connection connection) {
        ArrayList<String> libraries = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_libraries");
            while (resultSet.next()) {
                libraries.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraries;
    }

    public ArrayList<String> getPublishers(Connection connection) {
        ArrayList<String> publishers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_publishers");
            while (resultSet.next()) {
                publishers.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    public ArrayList<String> getAuthors(Connection connection) {
        ArrayList<String> authors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_authors");
            while (resultSet.next()) {
                authors.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

}
