package com.scalingfiltering.java.controllers;

import com.scalingfiltering.java.App;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox root;

    private StackPane mainView;

    @FXML
    private Label dateLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainView = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Main.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        initClock();
    }

    @FXML
    private void initClock() {
        // initialize Clock Showing in home
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
            Date date = new Date();
            dateLabel.setText(dateFormat.format(date));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void onStart() {
        ((Stage) root.getScene().getWindow()).setScene(new Scene(mainView));
        App.centerOnScreen(); // make interface in the center of screen
    }
}
