package com.example.demo8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Author {
    @FXML
    private TextField idLog; //поле ввода логина

    @FXML
    private PasswordField idPass; //поле ввода пароля

    @FXML
    public Button Login; //кнопка Войти

    @FXML
    private TextField idPassL; //поле ввода видимого пароля

    @FXML
    private Button check; //кнопка переключения между видимым и невидимым паролями

    DB db = null;

    public static int idUser;



    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        try {
            // Загрузка изображения из файла
            Image image = new Image(new FileInputStream("C:/Users/Admin/Downloads/free-icon-eye-159604.png"));

            // Создание представления изображения
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(21);
            imageView.setFitWidth(21);

            // Установка представления изображения на кнопку
            check.setGraphic(imageView);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Инициируем объект
        db = new DB();

        //Задаем textfield, отображающий пароль, невидимым
        idPassL.setVisible(false);

        //обработчик кнопки Войти
        Login.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (idPass.isVisible()) {
                    idPassL.setText(idPass.getText());  }
                if (!idLog.getText().trim().equals("") & !idPass.getText().trim().equals("")) {
                    try {   int n = db.getUsers(idLog.getText(), idPass.getText());
                            idUser = db.getIdUsers(idLog.getText(), idPass.getText());
                        if (n == 1) {
                            Stage currentStage = (Stage) Login.getScene().getWindow();
                            currentStage.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CabinetDoctor.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 753, 292);
                            //Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
                            //stage.getIcons().add(icon);
                            stage.setTitle("Внести новые данные");
                            stage.setResizable(false); // Не позволяем изменять размер окна
                            stage.setScene(scene);
                            stage.show();
                        } else if (n == 2) {
                            Stage currentStage = (Stage) Login.getScene().getWindow();
                            currentStage.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CabinetPatient.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 738, 554);
                            //Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
                            //stage.getIcons().add(icon);
                            stage.setTitle("Личный кабинет клиента");
                            stage.setScene(scene);
                            stage.setResizable(false); // Не позволяем изменять размер окна
                            stage.show();
                        } else {
                            //создаем окно ошибки
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Неверные данные");
                            errorAlert.setContentText("Ошибка в логине или пароле. Убедитесь, что вы ввели верные данные и попробуйте войти снова.");
                            errorAlert.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //обработчик кнопки показать пароль
        check.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (idPassL.getText().isEmpty()) {
                    idPassL.setVisible(true);
                    idPass.setVisible(false);
                    idPassL.setText(idPass.getText());
                } else {
                    idPass.setVisible(true);
                    idPassL.setVisible(false);
                    idPass.setText(idPassL.getText());
                    idPassL.setText("");
                }
            }
        });

    }
}

