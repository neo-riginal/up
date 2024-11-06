package com.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class OldDate {
    @FXML
    private Button toUpdateDiagnose;

    @FXML
    private Button toUpdateDoctor;

    @FXML
    private Button toUpdatePatient;

    @FXML
    private Button toUpdateService;

    @FXML
    private Button toUpdateSpeciality;

    @FXML
    private Button toExit;

    DB db = new DB();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {
        // Инициируем объект
        db = new DB();

        toUpdateDiagnose.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateDiagnose.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 585, 332);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toUpdateDoctor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateDoctor.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 768, 449);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toUpdatePatient.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updatePatient.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 710, 475);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toUpdateService.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateService.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 669, 332);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toUpdateSpeciality.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateSpeciality.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 585, 332);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        toExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CabinetDoctor.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 753, 292);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) toExit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
