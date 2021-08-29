package project;

import java.sql.*;

public class Repository {

    private boolean dbIsAvailable = true;

    public Repository() {
        try {
            Class.forName("org.sqlite.JDBC");
            createTable();
        } catch (ClassNotFoundException e) {
            System.out.println("Queries will not be logged.");
            dbIsAvailable = false;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:weather_requests.db");
    }

    private void createTable() {
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
            "CREATE TABLE IF NOT EXISTS weather_requests (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "city STRING NOT NULL," +
                    "localDate STRING NOT NULL," +
                    "weatherText STRING," +
                    "temperature DOUBLE NOT NULL);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String city, String date, String weatherText, Double temperature) {
        if (!dbIsAvailable) return;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO weather_requests (city, localDate, weatherText, temperature) VALUES (?, ?, ?, ?);"
            );
            statement.setString(1, city);
            statement.setString(2, date);
            statement.setString(3, weatherText);
            statement.setDouble(4, temperature);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAll() {
        if (!dbIsAvailable) return;

        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
            "SELECT * FROM weather_requests ORDER BY id;"
            );

            while (result.next()) {
                System.out.println(
                    result.getInt(1) + "|" +
                    result.getString(2) + "|" +
                    result.getString(3) + "|" +
                    result.getString(4) + "|" +
                    result.getDouble(5)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
