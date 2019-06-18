package com.scalingfiltering.java.controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.scalingfiltering.java.engine.Filtrage;
import com.scalingfiltering.java.model.Rating;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private JFXTreeTableView tableSim, tablePreduction;

    private JFXTreeTableColumn<Rating, String>[] colsSim, colsPreduction;

    public static int NUMBER_OF_COLUMNS;
    public static int NUMBER_OF_ROWS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init
        NUMBER_OF_COLUMNS = 50;
        NUMBER_OF_ROWS = 50;

        initTableSim();
        loadTableSim();

        initTablePreduction();
        loadTablePreduction();
    }

    private void initTableSim() {
        colsSim = new JFXTreeTableColumn[NUMBER_OF_COLUMNS];

        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            colsSim[i] = new JFXTreeTableColumn<>("Item" + (i + 1));
            colsSim[i].setPrefWidth(80);
            final int num = i;
            colsSim[i].setCellValueFactory((TreeTableColumn.CellDataFeatures<Rating, String> param) -> param.getValue().getValue().p[num]);
        }

        tableSim.getColumns().addAll(colsSim);
        tableSim.setShowRoot(false);

    }

    private void loadTableSim() { // load data to table
        ObservableList<Rating> ratings = FXCollections.observableArrayList();

        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            Rating rating = new Rating();
            for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                rating.p[j] = new SimpleStringProperty(String.valueOf(Filtrage.tab_s[i][j]));
            }

            ratings.add(rating);
        }

        final TreeItem<Rating> treeItem = new RecursiveTreeItem<>(ratings, RecursiveTreeObject::getChildren);
        try {
            tableSim.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    private void initTablePreduction() {
        colsPreduction = new JFXTreeTableColumn[NUMBER_OF_COLUMNS];

        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            colsPreduction[i] = new JFXTreeTableColumn<>("Item" + (i + 1));
            colsPreduction[i].setPrefWidth(80);
            final int num = i;
            colsPreduction[i].setCellValueFactory((TreeTableColumn.CellDataFeatures<Rating, String> param) -> param.getValue().getValue().p[num]);
        }

        tablePreduction.getColumns().addAll(colsPreduction);
        tablePreduction.setShowRoot(false);

    }

    private void loadTablePreduction() { // load data to table
        ObservableList<Rating> ratings = FXCollections.observableArrayList();

        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            Rating rating = new Rating();
            for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                rating.p[j] = new SimpleStringProperty(String.valueOf(Filtrage.tab_p[i][j]));
            }

            ratings.add(rating);
        }

        final TreeItem<Rating> treeItem = new RecursiveTreeItem<>(ratings, RecursiveTreeObject::getChildren);
        try {
            tablePreduction.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    private void onClose() {
        MainController.dialogResult.close();
    }
}
