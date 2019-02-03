package nl.hsleiden.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import javax.jws.soap.SOAPBinding;

import nl.hsleiden.Database;
import nl.hsleiden.model.Item;
import nl.hsleiden.model.User;

import static java.lang.Float.parseFloat;


public class UserDAO
{
    private static UserDAO instance;
    private Database database = Database.getInstance();

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    public void addUser(User user) {
        String insertString = "INSERT INTO UserTable " +
                "(Email, Password, Admin) VALUES " +
                "(?,?,?);";

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(insertString);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isAdmin());
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUser(String Email) {

        String selectString = "SELECT * FROM UserTable WHERE Email = ?;";

        ResultSet result;

        result = database.getResult(Email, selectString);

        User user = null;
        try {
            if (result.next()) {
                user = new User(
                        result.getString("Email"),
                        result.getString("Password"),
                        result.getBoolean("Admin")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void deleteUser(String name) {
        String deleteString = "DELETE FROM UserTable " +
                "WHERE Email = ?;";

        database.delete(name, deleteString);
    }
    public void updateUser(User user, User authenticator){

        String updateString = "UPDATE UserTable " +
                "SET Email = ?, Password = ?, Admin = ? " +
                "WHERE Email = ?;";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(updateString);
            preparedStatement.setString(1, authenticator.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, authenticator.isAdmin());
            preparedStatement.setString(4, authenticator.getEmail());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
