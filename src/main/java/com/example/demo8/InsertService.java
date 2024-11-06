package com.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertService {

    @FXML
    private TextField insertService;

    @FXML
    private TextField costService;

    @FXML
    private Button toCabinet;

    @FXML
    private Button save;

    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException{

        db = new DB();

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                String costServiceText = costService.getText();
                if (!costServiceText.matches("\\d*")) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Неверные данные");
                    errorAlert.setContentText("Ошибка данных. Убедитесь, что вы ввели правильную цену и попробуйте снова.");
                    errorAlert.show();
                } else {
                    try {
                        db.insertService(insertService.getText(), Double.parseDouble(costService.getText()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        toCabinet.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Получаем ссылку на текущее окно
                Stage currentStage = (Stage) toCabinet.getScene().getWindow();

                // Получаем ссылку на предыдущее окно
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CabinetDoctor.fxml"));
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
