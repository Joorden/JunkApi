package nl.hsleiden;

import java.sql.*;

public class Database {

    private static Database instance;
    private Connection connection;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private boolean hasConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void connect(String connectionString) {
        if (hasConnection()) {
            System.out.println("already connected");
        }

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String parameter, String selectString) {
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = instance.getConnection().prepareStatement(selectString);
            preparedStatement.setString(1, parameter);

            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void delete(String parameter, String deleteString) {
        try {
            PreparedStatement preparedStatement = instance.getConnection().prepareStatement(deleteString);
            preparedStatement.setString(1, parameter);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResult(ResultSet result) {
        Statement statement = null;
        try {
            statement = result.getStatement();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

