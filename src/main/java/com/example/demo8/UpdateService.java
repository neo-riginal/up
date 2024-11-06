package com.example.demo8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateService {
    @FXML
    private TextField updateServiceField;

    @FXML
    private TextField updateServiceCost;

    @FXML
    private Button toOldDate;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private TableView<OurServices> services;

    @FXML
    private TableColumn<OurServices, String> servicesName;

    @FXML
    private TableColumn<OurServices, Double> servicesCost;

    DB db = null;

    int idServ;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        db = new DB();


        ArrayList<String> allService = db.getServices();
        ArrayList<Double> allCost = db.getCostServices();
        ObservableList<OurServices> data = FXCollections.observableArrayList();

        for (int i = 0; i < allService.size(); i++) {
            data.add(new OurServices(allService.get(i), allCost.get(i)));
        }

        servicesName.setCellValueFactory(new PropertyValueFactory<OurServices, String>("Service"));

        servicesCost.setCellValueFactory(new PropertyValueFactory<OurServices, Double>("CostS"));

        services.setItems(data);
        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(); // Initialize as empty ObservableList


        services.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateServiceField.clear();
            updateServiceCost.clear();
            int p = Math.toIntExact(services.getSelectionModel().getSelectedItems().stream().count());
            for (int i = 0; i < p; i++) {
                TableView.TableViewSelectionModel<OurServices> selectionModel = services.getSelectionModel();
                OurServices selectedItem = selectionModel.getSelectedItems().get(i);

                if (selectedItem != null) {

                    String column1Value = (String) services.getColumns().get(0).getCellData(selectedItem);
                    Double column2Value = (Double) services.getColumns().get(1).getCellData(selectedItem);

                    try {
                        idServ = db.getIdServicesCost(column1Value, column2Value);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        updateServiceField.setText(db.getNameService(idServ));
                        updateServiceCost.setText((String.valueOf(db.getCostService(idServ))));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                String costServiceText = updateServiceCost.getText();
                if (!costServiceText.matches("\\d*")) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Неверные данные");
                    errorAlert.setContentText("Ошибка данных. Убедитесь, что вы ввели правильную цену и попробуйте снова.");
                    errorAlert.show();
                }else{
                    try {
                        db.updateService(updateServiceField.getText(), Double.parseDouble(updateServiceCost.getText()), idServ);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    db.deleteService(idServ);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        toOldDate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // сохраняем текущее окно как ссылку в переменной
                Stage currentStage = (Stage) toOldDate.getScene().getWindow();

                // Получаем ссылку на предыдущее окно
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("oldDate.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage previousStage = new Stage();
                previousStage.setScene(new Scene(root));
//                Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                previousStage.getIcons().add(icon);
                // Закрываем текущее окно и открываем предыдущее
                currentStage.close();
                previousStage.show();
            }
        });
    }
}




