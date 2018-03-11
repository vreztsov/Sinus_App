package vreztsov.training.sinus_app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vreztsov.training.sinus_app.controller.Controller;
import vreztsov.training.sinus_app.model.Chart;

import java.net.URL;

public class Main extends Application {

    private ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
    private Chart chart;
//    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sinus App");
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("view/window.fxml");
        loader.setLocation(url);
        AnchorPane rootLayout = loader.load();
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setWidth(405);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setMainApp(this);

    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<XYChart.Data> getData() {
        return data;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {

        this.chart = chart;
    }
}
