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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ListServices {

    @FXML
    private Button toCabinet;

    @FXML
    private TableView<OurServices> ourServices;

    @FXML
    private TableColumn<OurServices, String> services;

    @FXML
    private TableColumn<OurServices, Double> costs;

    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        final int a = db.getUsersRole(Author.idUser);

        ArrayList<String> allService = db.getServices();
        ArrayList<Double> allCost = db.getCostServices();
        ObservableList<OurServices> data = FXCollections.observableArrayList();

        for (int i = 0; i < allService.size(); i++) {
            data.add(new OurServices(allService.get(i), allCost.get(i)));
        }

        services.setCellValueFactory(new PropertyValueFactory<OurServices, String>("Service"));

        costs.setCellValueFactory(new PropertyValueFactory<OurServices, Double>("CostS"));

        ourServices.setItems(data);


        toCabinet.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Получаем ссылку на текущее окно
                Stage currentStage = (Stage) toCabinet.getScene().getWindow();

                FXMLLoader loader = null;
                if (a == 1) {
                    // Получаем ссылку на предыдущее окно
                    loader = new FXMLLoader(getClass().getResource("cabinetDoctor.fxml"));
                } else {
                    // Получаем ссылку на предыдущее окно
                    loader = new FXMLLoader(getClass().getResource("cabinetPatient.fxml"));
                }

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
