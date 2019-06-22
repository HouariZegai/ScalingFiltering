package com.scalingfiltering.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Home.fxml"));
            stage.setScene(new Scene(root));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        stage.setTitle("Scaling Filtring System");
        App.stage = stage;
        stage.show();
    }

    public static void centerOnScreen() { // make interface in the center of screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        App.stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        App.stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
