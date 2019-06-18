package com.scalingfiltering.java.controllers;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private JFXTreeTableView<?> tableSim;

    @FXML
    private JFXTreeTableView<?> tablePreduction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onClose() {
        MainController.dialogResult.close();
    }
}
