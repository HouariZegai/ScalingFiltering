package com.scalingfiltering.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChartError;

    @FXML
    private BarChart<?, ?> barChartHardComp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLineChartError();
        loadBarChartHard();
    }

    private void loadLineChartError() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Series1");

        // Populating the series with data
        for (int i = 1; i <= 12; i++) {
            series1.getData().add(new XYChart.Data("" + i, i / 2));
        }

        // Add the series (lines) to the chart
        lineChartError.getData().addAll(series1);
    }

    private void loadBarChartHard() {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series1.setName("CPU:i3 RAM:4GB");
        series2.setName("CPU:i5 RAM:8GB");
        series3.setName("CPU:i7 RAM:16GB");

        // insert the data to the series (bars)

        series1.getData().addAll(
                new XYChart.Data<>("Time Speed", 20));

        series2.getData().addAll(
                new XYChart.Data<>("Time Speed", 50));

        series3.getData().addAll(
                new XYChart.Data<>("Time Speed", 30));

        // add the series to the chart
        barChartHardComp.getData().addAll(series1, series2, series3);
    }

    @FXML
    private void onClose() {
        MainController.dialogStatistic.close();
    }
}
