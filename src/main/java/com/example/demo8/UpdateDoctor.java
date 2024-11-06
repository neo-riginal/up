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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateDoctor {

    @FXML
    private TextField updateDoctorName;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button toOldDate;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private ListView doctors;


    DB db = null;

    int idDoctor;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        db = new DB();

        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(db.getFIODoctors());
        doctors.setItems(langs);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = doctors.getSelectionModel();

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected = langsSelectionModel.getSelectedItems();
                selectedItems.add(selected.toString());
            }
        });

        doctors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateDoctorName.clear();
            birthdate.setValue(null);
            phone.clear();
            address.clear();
            login.clear();
            password.clear();
            String selected = String.valueOf(doctors.getSelectionModel().getSelectedItem());
            try {
                idDoctor = db.getIdDoctorOfFIO(selected);
                updateDoctorName.setText(selected);
                birthdate.setValue(db.getBirthdateIdDoctor(idDoctor));
                phone.setText(String.valueOf(db.getPhoneIdDoctor(idDoctor)));
                address.setText(db.getAddressIdDoctor(idDoctor));
                login.setText(db.getLoginIdDoctor(idDoctor));
                password.setText(db.getPasswordIdDoctor(idDoctor));
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
                    int idUser = db.getIdUsersForIdDoctor(idDoctor);
                    db.updateUser(login.getText(), password.getText(), idUser);
                    db.updateDoctor(updateDoctorName.getText(), Date.valueOf(birthdate.getValue()), phone.getText(), address.getText(), idDoctor);
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
                    int idUser = db.getIdUsersForIdPatient(idDoctor);
                    db.deleteDoctor(idDoctor);
                    db.deleteUser(idUser);
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
