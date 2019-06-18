package com.scalingfiltering.java.controllers;

import com.jfoenix.controls.*;
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
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private JFXTreeTableView tableView;

    private JFXTreeTableColumn<Rating, String>[] colP;

    @FXML
    private JFXSpinner spinnerWait;

    @FXML
    private JFXRadioButton radio50, radio100, radio200, radioAll;

    @FXML
    private JFXButton btnCalculate, btnViewStatistic;

    FileChooser fileChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init file chooser
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV file");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Excel File", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        initTable();
    }

    private void initTable() {
        colP = new JFXTreeTableColumn[30];

        for(int i = 0; i < 30; i++) {
            colP[i] = new JFXTreeTableColumn<>("Item" + i);
            colP[i].setPrefWidth(80);
            final int num = i;
            colP[i].setCellValueFactory((TreeTableColumn.CellDataFeatures<Rating, String> param) -> param.getValue().getValue().p[num]);
        }

        tableView.getColumns().addAll(colP);
        tableView.setShowRoot(false);

    }

    private void loadTable() { // load data to table
        ObservableList<Rating> ratings = FXCollections.observableArrayList();

        for(int i = 0; i < 100; i++) {
            Rating rating = new Rating();
            for(int j = 0; j < 30; j++) {
                rating.p[j] = new SimpleStringProperty(String.valueOf(Filtrage.tab[i][j]));
            }

            ratings.add(rating);
        }

        final TreeItem<Rating> treeItem = new RecursiveTreeItem<>(ratings, RecursiveTreeObject::getChildren);
        try {
            tableView.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    @FXML
    void onLoadCSV() {
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if(file != null) {
            spinnerWait.setVisible(true);

            Filtrage.loadTable(file);
            loadTable();

            spinnerWait.setVisible(false);
            btnCalculate.setDisable(false);
        }

    }

    @FXML
    void onCalculate() {
        Filtrage.calcPreduction();

        btnViewStatistic.setDisable(false);
    }

    @FXML
    void onViewStatistic() {

    }
}
