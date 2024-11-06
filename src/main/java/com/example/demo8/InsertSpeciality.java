package com.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InsertSpeciality {

    @FXML
    private TextField insertSpeciality;

    @FXML
    private Button toCabinet;

    @FXML
    private Button save;

    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        db = new DB();

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    db.insertSpeciality(insertSpeciality.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toCabinet.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // сохраняем текущее окно как ссылку в переменной
                Stage currentStage = (Stage) toCabinet.getScene().getWindow();

                // Получаем ссылку на предыдущее окно
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("cabinetDoctor.fxml"));
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
