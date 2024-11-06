package com.example.demo8;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateSpeciality {
    @FXML
    private TextField updateSpeciality;

    @FXML
    private Button toOldDate;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private ListView<String> specialities;

    DB db = null;

    int idSpeciality;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        db = new DB();

        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(db.getSpecialities());
        specialities.setItems(langs);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = specialities.getSelectionModel();

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected = langsSelectionModel.getSelectedItems();
                selectedItems.add(selected.toString());
            }
        });

        specialities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateSpeciality.clear();
            String selected = String.valueOf(specialities.getSelectionModel().getSelectedItem());
            try {
                idSpeciality = db.getIDSpeciality(selected);
                updateSpeciality.setText(db.getNameSpeciality(idSpeciality));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
                });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                    db.updateSpeciality(updateSpeciality.getText(), idSpeciality);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    db.deleteSpeciality(idSpeciality);
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
