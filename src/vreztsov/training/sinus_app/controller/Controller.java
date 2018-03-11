package vreztsov.training.sinus_app.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vreztsov.training.sinus_app.Main;
import vreztsov.training.sinus_app.model.Chart;

import java.io.File;



/**
 * Created by Viktor on 28.02.2018.
 */
public class Controller {

    @FXML
    private TableView<XYChart.Data> dataTableView;
    @FXML
    private TableColumn<XYChart.Data, Double> coordinateX;
    @FXML
    private TableColumn<XYChart.Data, Double> coordinateY;
    @FXML
    private TextField xInputField;
    @FXML
    private TextField yInputField;
    @FXML
    private TextField xAxisLabel;
    @FXML
    private TextField yAxisLabel;
    @FXML
    private TextField xLimits;
    @FXML
    private TextField yLimits;
    @FXML
    private Label errorBar1;
    @FXML
    private Label errorBar2;
    @FXML
    private Label statusBar;
    @FXML
    private TableColumn<XYChart.Data, Integer> pointNumber;

    private static final int X = 1;

    private static final int Y = 2;

    private double x1, x2, y1, y2;

    private Main mainApp;

    public Controller() {
        int i = 0;
    }

    @FXML
    private void initialize() {
        dataTableView.setEditable(true);

        coordinateX.setCellValueFactory(param -> param.getValue().XValueProperty());
        coordinateY.setCellValueFactory(param -> param.getValue().YValueProperty());
//       TODO: подумать, как сделать нумерацию в ячейке (потом прикрутишь)
//        pointNumber.setCellValueFactory();
        coordinateX.setEditable(true);
        coordinateY.setEditable(true);
    }

    @FXML
    private void addPair() {
        try {
            double x = Double.parseDouble(xInputField.getText());
            double y = Double.parseDouble(yInputField.getText());
            dataTableView.getItems().add(new XYChart.Data<>(x, y));

            xInputField.clear();
            yInputField.clear();
            statusBar.setText("");
        } catch (NumberFormatException e) {
            statusBar.setText("Неправильно введены координаты");
        }
    }

    @FXML
    private void deletePair() {

        int n = dataTableView.getSelectionModel().getSelectedIndex();
        if (n!=-1) dataTableView.getItems().remove(n);

    }

    @FXML
    private void plot() {
        try {
            statusBar.setText("");
            Chart chart = new Chart(dataTableView.getItems());
            chart.setAxisLabels(xAxisLabel.getText(), yAxisLabel.getText());
            chart.setXAxisBounds(x1, x2);
            chart.setYAxisBounds(y1, y2);
            chart.showGraphicWindow();
            mainApp.setChart(chart);

        } catch (Exception e) {
            e.printStackTrace();
            statusBar.setText("Ошибка при выводе графика");
        }
    }

    @FXML
    private void startAfresh(){
        dataTableView.getItems().clear();
        xInputField.setText("");
        yInputField.setText("");
        xAxisLabel.setText("");
        yAxisLabel.setText("");
        xLimits.setText("");
        yLimits.setText("");
        errorBar1.setText("");
        errorBar2.setText("");
        statusBar.setText("");
        mainApp.setChart(null);
    }

    @FXML
    private void saveToWordFile(){
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Microsoft Word files (*.docx)", "*.docx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            // TODO: дописать дальнейшие действия
        }
    }

    @FXML
    private void parseXLimits() {
        if (!xLimits.getText().equals("")) parseAxisLimits(X);
        else {
            x1 = 0;
            x2 = 0;
        }
    }

    @FXML
    private void parseYLimits() {

        if (!yLimits.getText().equals("")) parseAxisLimits(Y);
        else {
            y1 = 0;
            y2 = 0;
        }
    }



    private void parseAxisLimits(int axis) {
        char c = ' ';
        try {
            errorBar1.setText("");
            switch (axis) {
                case 1: {
                    String[] s = xLimits.getText().split(",");
                    if (s.length != 2) throw new IllegalArgumentException();
                    else {
                        c = 'X';
                        x1 = Double.parseDouble(s[0]);
                        x2 = Double.parseDouble(s[1]);
                        if (x2 <= x1) throw new Exception();
                    }
                    break;
                }
                case 2: {
                    String[] s = yLimits.getText().split(",");
                    if (s.length != 2) throw new IllegalArgumentException();
                    else {
                        c = 'Y';
                        y1 = Double.parseDouble(s[0]);
                        y2 = Double.parseDouble(s[1]);
                        if (y2 <= y1) throw new Exception();
                    }
                    break;
                }
            }
        } catch (NumberFormatException e) {
            errorBar1.setText(String.format("Неправильно введено число в поле \"Ось %c\"", c));
        } catch (IllegalArgumentException e) {
            errorBar1.setText("Введите 2 числа через запятую");
        } catch (Exception e) {
            errorBar1.setText("Правая граница диапазона должна быть больше левой");
        }
    }


    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
