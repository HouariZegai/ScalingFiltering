package com.scalingfiltering.java.controllers;

import com.jfoenix.controls.JFXTextField;
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
import java.util.function.Predicate;

public class RecommendationController implements Initializable {

    @FXML
    private JFXTextField fieldUser;

    @FXML
    private JFXTreeTableView tableRecom;

    private JFXTreeTableColumn<Rating, String>[] colsRecom;

    public static int NUMBER_OF_COLUMNS;
    public static int NUMBER_OF_ROWS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init
        NUMBER_OF_COLUMNS = 50;
        NUMBER_OF_ROWS = 50;

        initTableRecom();
        loadTableRecom();
        fieldUser.textProperty().addListener(e -> {
            filterSearchTable();
        });
    }

    private void initTableRecom() {
        colsRecom = new JFXTreeTableColumn[NUMBER_OF_COLUMNS];

        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            colsRecom[i] = new JFXTreeTableColumn<>();
            colsRecom[i].setPrefWidth(80);
            final int num = i;
            colsRecom[i].setCellValueFactory((TreeTableColumn.CellDataFeatures<Rating, String> param) -> param.getValue().getValue().p[num]);
        }

        tableRecom.getColumns().addAll(colsRecom);
        tableRecom.setShowRoot(false);

    }

    private void loadTableRecom() { // load data to table
        ObservableList<Rating> ratings = FXCollections.observableArrayList();

        if(Filtrage.tab_s.length <= NUMBER_OF_ROWS) {
            NUMBER_OF_ROWS = Filtrage.tab_recom.length - 1;
        }

        if(Filtrage.tab_s[0].length <= NUMBER_OF_COLUMNS) {
            NUMBER_OF_COLUMNS = Filtrage.tab_recom[0].length - 1;
        }

        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            Rating rating = new Rating();

            rating.p[0] = new SimpleStringProperty("User " + (i + 1));

            for(int j = 1; j < NUMBER_OF_COLUMNS; j++) {
                rating.p[j] = new SimpleStringProperty(String.valueOf(Filtrage.tab_recom[i + 1][j + 1]));
            }

            ratings.add(rating);
        }

        final TreeItem<Rating> treeItem = new RecursiveTreeItem<>(ratings, RecursiveTreeObject::getChildren);
        try {
            tableRecom.setRoot(treeItem);
        } catch (Exception ex) {
            System.out.println("Error catched !");
        }
    }

    public void filterSearchTable() {
        tableRecom.setPredicate(new Predicate<TreeItem<Rating>>() {
            @Override
            public boolean test(TreeItem<Rating> rating) {
                return rating.getValue().p[0].getValue().toLowerCase().contains(fieldUser.getText().toLowerCase());
            }
        });
    }

    @FXML
    private void onClose() {
        MainController.dialogRecommendation.close();
    }
}