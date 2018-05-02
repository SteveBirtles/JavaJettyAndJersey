package server.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MessageService {

    public static void selectAll(List<Message> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT Id, Text, PostDate, Author FROM Messages");
        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Message(results.getInt("Id"), results.getString("Text"), results.getString("PostDate"), results.getString("Author")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database error - can't select all from 'Messages' table: " + resultsException.getMessage());

        }
    }

    public static Message selectById(int id, DatabaseConnection database) {

        Message result = null;

        PreparedStatement statement = database.newStatement("SELECT Text, PostDate, Author FROM Messages WHERE Id = ?");

        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Message(id, results.getString("Text"), results.getString("PostDate"), results.getString("Author"));
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(Message messageToSave, DatabaseConnection database) {

        Message existingItem = null;
        if (messageToSave.getId() != 0) existingItem = selectById(messageToSave.getId(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Messages (Id, Text, PostDate, Author) VALUES (?, ?, ?, ?)");
                statement.setInt(1, messageToSave.getId());
                statement.setString(2, messageToSave.getText());
                statement.setString(3, messageToSave.getPostDate());
                statement.setString(4, messageToSave.getAuthor());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Messages SET Text = ?, PostDate = ?, Author = ? WHERE Id = ?");
                statement.setString(1, messageToSave.getText());
                statement.setString(2, messageToSave.getPostDate());
                statement.setString(3, messageToSave.getAuthor());
                statement.setInt(4, messageToSave.getId());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void deleteById(int id, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("DELETE FROM Messages WHERE Id = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }



}
