package com.scalingfiltering.java.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.scalingfiltering.java.engine.Filtrage;
import com.scalingfiltering.java.model.Rating;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
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

    private FileChooser fileChooser;

    public static JFXDialog dialogResult, dialogStatistic;

    private static final int NUMBER_OF_COLUMNS = 50;
    private static final int NUMBER_OF_ROWS = 100;

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
        colP = new JFXTreeTableColumn[NUMBER_OF_COLUMNS];

        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            colP[i] = new JFXTreeTableColumn<>("Item " + i);
            colP[i].setPrefWidth(80);
            final int num = i;
            colP[i].setCellValueFactory((TreeTableColumn.CellDataFeatures<Rating, String> param) -> param.getValue().getValue().p[num]);
        }

        tableView.getColumns().addAll(colP);
        tableView.setShowRoot(false);

    }

    private void loadTable() { // load data to table
        ObservableList<Rating> ratings = FXCollections.observableArrayList();

        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            Rating rating = new Rating();
            for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
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
        if(radio50.isSelected()) {
            ResultController.NUMBER_OF_COLUMNS = 50;
            ResultController.NUMBER_OF_ROWS = 50;
        } else if(radio100.isSelected()) {
            ResultController.NUMBER_OF_COLUMNS = 100;
            ResultController.NUMBER_OF_ROWS = 100;
        } else if(radio200.isSelected()) {
            ResultController.NUMBER_OF_COLUMNS = 200;
            ResultController.NUMBER_OF_ROWS = 200;
        } else {
            ResultController.NUMBER_OF_COLUMNS = 700;
            ResultController.NUMBER_OF_ROWS = 200000;
        }

        Filtrage.calculate(ResultController.NUMBER_OF_COLUMNS, ResultController.NUMBER_OF_ROWS);

        try {
            StackPane resultView = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Result.fxml"));
            dialogResult = new JFXDialog(root, resultView, JFXDialog.DialogTransition.CENTER);
            dialogResult.show();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        btnViewStatistic.setDisable(false);
    }

    @FXML
    void onViewStatistic() {
        try {
            StackPane statisticView = FXMLLoader.load(getClass().getResource("/com/scalingfiltering/resources/views/Statistic.fxml"));
            dialogStatistic = new JFXDialog(root, statisticView, JFXDialog.DialogTransition.CENTER);
            dialogStatistic.show();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
