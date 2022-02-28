package com.mycompany.csc311hw1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for the JavaFx program to set and launch the JavaFx application
 * 
 * @author paulk
 */
public class App extends Application {

    // Member variable of App
    private static Scene scene;

    /**
     * Overridden version of the start from the Application abstract class
     * 
     * @param stage The stage the visual platform of the application
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Sets the size of the stage
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Connects the scene to the fxml
     * 
     * @param fxml
     * @throws IOException 
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Gets the resource from the fxml file and loads it into the application
     * 
     * @param fxml
     * @return 
     * @throws IOException 
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main class to start the JavaFx application
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }
}
