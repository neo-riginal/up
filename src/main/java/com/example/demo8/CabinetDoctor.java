package com.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class CabinetDoctor {
        @FXML
        private Button toNewDate;

        @FXML
        private Button toOldDate;

        @FXML
        private Button toMedicalCard;

        @FXML
        private Button toListDoctors;

        @FXML
        private Button toListServices;

        @FXML
        private Button toExit;


        DB db = new DB();

        @FXML
        void initialize() throws SQLException, ClassNotFoundException, ParseException {
                // Инициируем объект
                db = new DB();

                toNewDate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        // Метод, что будет срабатывать
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                try {
                                        // Загрузка нового FXML-файла "author.fxml"
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewDate.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 577, 262);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
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

                toOldDate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        // Метод, что будет срабатывать
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                try {
                                        // Загрузка нового FXML-файла "author.fxml"
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OldDate.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 577, 262);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
                                        stage.setTitle("Изменить данные");
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

                toMedicalCard.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        // Метод, что будет срабатывать
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                try {
                                        // Загрузка нового FXML-файла "author.fxml"
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MedicalCard.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 1286, 630);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
                                        stage.setTitle("Мед. карты");
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


                toListDoctors.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        // Метод, что будет срабатывать
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                try {
                                        // Загрузка нового FXML-файла "author.fxml"
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListDoctors.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 1045, 404);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
                                        stage.setTitle("Доктора");
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

                toListServices.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        // Метод, что будет срабатывать
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                                try {
                                        // Загрузка нового FXML-файла "author.fxml"
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListServices.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 540, 371);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
                                        stage.setTitle("Услуги");
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
                                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Author.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(fxmlLoader.load(), 463, 274);
//                                        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                                        stage.getIcons().add(icon);
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
