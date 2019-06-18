package com.scalingfiltering.java.controllers;

import com.scalingfiltering.java.engine.Filtrage;
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
        series1.setName("Moyenne d'erreur");

        // Populating the series with data
        for (int i = 0; i < Filtrage.moy_err.length; i++) {
            series1.getData().add(new XYChart.Data(i + 1 + "", Filtrage.moy_err[i]));
            System.out.println(Filtrage.moy_err[i]);
        }

        // Add the series (lines) to the chart
        lineChartError.getData().addAll(series1);
    }

    private void loadBarChartHard() {
        XYChart.Series[] series = new XYChart.Series[6];
        for(int i = 0; i < series.length; i++)
            series[i] = new XYChart.Series();

        series[0].setName("CPU:i3 RAM:4GB");
        series[1].setName("CPU:i3 RAM:8GB");
        series[2].setName("CPU:i5 RAM:4GB");
        series[3].setName("CPU:i5 RAM:8GB");
        series[4].setName("CPU:i7 RAM:8GB");
        series[5].setName("CPU:i7 RAM:16GB");

        // insert the data to the series (bars)

        series[0].getData().addAll(new XYChart.Data<>("Time Speed", 70));
        series[1].getData().addAll(new XYChart.Data<>("Time Speed", 60));
        series[2].getData().addAll(new XYChart.Data<>("Time Speed", 50));
        series[3].getData().addAll(new XYChart.Data<>("Time Speed", 48));
        series[4].getData().addAll(new XYChart.Data<>("Time Speed", 30));
        series[5].getData().addAll(new XYChart.Data<>("Time Speed", 20));

        // add the series to the chart
        barChartHardComp.getData().addAll(series);
    }

    @FXML
    private void onClose() {
        MainController.dialogStatistic.close();
    }
}
