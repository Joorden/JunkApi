package nl.hsleiden.persistence;

import nl.hsleiden.Database;
import nl.hsleiden.model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class ItemDAO {

    private static ItemDAO instance;
    private Database database = Database.getInstance();

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        String selectString = "SELECT * FROM Item";

        PreparedStatement preparedStatement;
        ResultSet result = null;
        try {
            preparedStatement = database.getConnection().prepareStatement(selectString);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (result.next()) {
                Item item = new Item(
                        Integer.parseInt(result.getString("ItemID")),
                        result.getString("ItemName"),
                        result.getDouble("ItemPrice"),
                        result.getString("ItemDesc"),
                        result.getString("ItemImg")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeResult(result);
        return items;
    }
    public void addItem(Item item) {
        String insertString = "INSERT INTO Item " +
                "(ItemID, ItemName, ItemDesc, ItemPrice, ItemImg) VALUES " +
                "(?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(insertString);
            preparedStatement.setInt(1, item.getItemCode());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setString(5, item.getImage());
            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteItem(String name) {
        String deleteString = "DELETE FROM Item " +
                "WHERE ItemName = ?;";

        database.delete(name, deleteString);
    }
    public void updateItem(Item item){

        String updateString = "UPDATE Item " +
                "SET ItemID = ?, ItemName = ?, ItemDesc = ?, " +
                "ItemPrice = ?, ItemImg = ?" +
                "WHERE ItemName = ?;";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(updateString);
            preparedStatement.setInt(1, item.getItemCode());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setString(5, item.getImage());
            preparedStatement.setString(6, item.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
