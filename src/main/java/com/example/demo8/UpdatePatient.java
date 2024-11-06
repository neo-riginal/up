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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdatePatient {

    @FXML
    private TextField updatePatientName;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField pass;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField snils;

    @FXML
    private TextField polis;

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
    private ListView patients;


    DB db = null;

    int idPatient;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        db = new DB();

        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(db.getFIOPatients());
        patients.setItems(langs);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = patients.getSelectionModel();

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected = langsSelectionModel.getSelectedItems();
                selectedItems.add(selected.toString());
            }
        });

        patients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePatientName.clear();
            birthdate.setValue(null);
            pass.clear();
            phone.clear();
            address.clear();
            snils.clear();
            polis.clear();
            login.clear();
            password.clear();
            String selected = String.valueOf(patients.getSelectionModel().getSelectedItem());
            try {
                idPatient = db.getIdPatientOfFIO(selected);
                updatePatientName.setText(selected);
                birthdate.setValue(db.getBirthdateIdPatient(idPatient));
                pass.setText(String.valueOf(db.getPassIdPatient(idPatient)));
                phone.setText(String.valueOf(db.getPhoneIdPatient(idPatient)));
                address.setText(db.getAddressIdPatient(idPatient));
                snils.setText(String.valueOf(db.getSnilsIdPatient(idPatient)));
                polis.setText(String.valueOf(db.getPolisIdPatient(idPatient)));
                login.setText(db.getLoginIdPatient(idPatient));
                password.setText(db.getPasswordIdPatient(idPatient));
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
                    int idUser = db.getIdUsersForIdPatient(idPatient);
                    db.updateUser(login.getText(), password.getText(), idUser);
                    db.updatePatient(updatePatientName.getText(), Date.valueOf(birthdate.getValue()), pass.getText(), phone.getText(), address.getText(), snils.getText(), polis.getText(), idPatient);
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
                    int idUser = db.getIdUsersForIdPatient(idPatient);
                    db.deletePatient(idPatient);
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
