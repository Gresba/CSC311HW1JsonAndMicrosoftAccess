package com.mycompany.csc311hw1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private MenuItem close, loadDbMenuItem;

    @FXML
    private Button loadListViewBtn;
    
    @FXML
    private ListView listView;
    
    @FXML
    private TextField recordCount;
    
    /**
     * Close the application
     * 
     * @param e Action Event
     */
    @FXML
    protected void closeApplication(ActionEvent e) 
    {
        System.exit(0);
    }

    /**
     * Loads data from a json file and loads it into a database in the root directory of the project
     * 
     * @param e
     * @throws FileNotFoundException
     * @throws SQLException 
     */
    @FXML
    protected void loadDBFromJsonFile(ActionEvent e) throws FileNotFoundException, SQLException
    {
        // Creating GSON variable to parse JSON
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        // Establishing connection with the input JSON file
        FileReader fr = new FileReader("VideoGames.json");
        
        // Loading data from the JSON file into the videoGamesList ArrayList 
        ArrayList<VideoGames> videoGamesList = gson.fromJson(fr, new TypeToken<ArrayList<VideoGames>>() {}.getType());

        // Creation connection with the Microsoft Access database
        String databaseURL = "";
        Connection conn = null;
        try 
        {
            databaseURL = "jdbc:ucanaccess://.//Games.accdb";
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create and executing a query to delete the current data in the database
        String deleteQuery = "DELETE FROM VideoGames";
        PreparedStatement delete = conn.prepareStatement(deleteQuery);
        delete.executeUpdate();
        
        // Looping through the videoGamesList arraylist
        for (VideoGames games : videoGamesList) 
        {
            // Create and executing a query to increase the game data from the JSON file into the database
            String sql = "INSERT INTO VideoGames (Title, Price, Esrb) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, games.getTitle());
            preparedStatement.setDouble(2, games.getPrice());
            preparedStatement.setString(3, games.getEsrb());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Display the data from the database on the listView and update counter
     * 
     * @param e ActionEvent the event that will trigger this method to run
     */
    @FXML
    protected void displayData(ActionEvent e) 
    {
        int counter = 0;
        
        // Getting all data that is current in the listView
        ObservableList<String> items = listView.getItems();

        // Clearing the data in the list View
        items.clear();
        String databaseURL = "";
        
        // Establishing connecting with the Microsoft Access database
        Connection conn = null;
        try 
        {
            databaseURL = "jdbc:ucanaccess://.//Games.accdb";
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try 
        {
            // Pulling data from the VideoGames table and storing it in result
            String tableName = "VideoGames";
            Statement stmt = conn.createStatement();
       
            ResultSet result = stmt.executeQuery("SELECT * FROM " + tableName);
            
            // Looping through the data pulled and displaying the data in the listView
            while (result.next()) 
            {
                // Updating counter
                counter++;
                String title = result.getString("Title");
                double price = result.getInt("Price");
                String esrb = result.getString("Esrb");
                items.add(title + ", " +  price + ", " + esrb);
            }

            // Updating counter display
            recordCount.setText("" + counter);
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }
}
