package com.scalingfiltering.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Main.fxml"));
            stage.setScene(new Scene(root));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        stage.setTitle("Scaling Filtring System");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
