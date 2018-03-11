package vreztsov.training.sinus_app.model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;

/**
 * Created by Viktor on 05.03.2018.
 */

public class Chart {

    private XYChart.Series series;
    private LineChart lineChart;


    public Chart(ObservableList<XYChart.Data> datas) throws Exception{
        series = new XYChart.Series(datas);
        NumberAxis axisX = new NumberAxis();
        NumberAxis axisY = new NumberAxis();
        lineChart = new LineChart(axisX, axisY, FXCollections.observableArrayList(series));



    }

    public XYChart.Series getSeries() {
        return series;
    }

    public LineChart getLineChart() {
        return lineChart;
    }

    public void showGraphicWindow() throws Exception {
        Stage window = new Stage();
        URL url = getClass().getResource("../view/chartwindow.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        ((Pane) scene.getRoot()).getChildren().add(lineChart);
        window.setTitle("Graphic");
        window.setScene(scene);
        window.show();
    }

    public void setAxisLabels(String labelX, String labelY){
        lineChart.getXAxis().setLabel(labelX);
        lineChart.getYAxis().setLabel(labelY);
    }

    public void setXAxisBounds(double x1, double x2){
        NumberAxis x = (NumberAxis) lineChart.getXAxis();
        setAxisBounds(x,x1,x2);
    }
    public void setYAxisBounds(double y1, double y2){
        NumberAxis y = (NumberAxis) lineChart.getYAxis();
        setAxisBounds(y,y1,y2);
    }

    private void setAxisBounds(NumberAxis axis, double a, double b){
        if (a==b && b==0) axis.setAutoRanging(true);
        else {
            axis.setAutoRanging(false);
            axis.setLowerBound(a);
            axis.setUpperBound(b);
        }
    }


}
