package com.scalingfiltering.java.controllers;

import com.scalingfiltering.java.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private HBox root;

    private StackPane mainView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainView = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Main.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void onStart() {
        ((Stage) root.getScene().getWindow()).setScene(new Scene(mainView));
        App.centerOnScreen(); // make interface in the center of screen
    }
}
