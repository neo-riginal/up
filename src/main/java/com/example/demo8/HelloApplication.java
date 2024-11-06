package com.example.demo8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("author.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 463, 274);
//        Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//        stage.getIcons().add(icon);
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.setResizable(false); // Не позволяем изменять размер окна
        stage.show();
    }


    public Stage getPrimaryStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}