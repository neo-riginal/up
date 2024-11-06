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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsertPatient {
    @FXML
    private TextField fio;

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
    private ListView<String> diagnoses;

    @FXML
    private Button toCabinet;

    @FXML
    private Button save;

    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(db.getDiagnoses());
        diagnoses.setItems(langs);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = diagnoses.getSelectionModel();
        langsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected = langsSelectionModel.getSelectedItems();
                selectedItems.add(selected.toString());
            }
        });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int n = Math.toIntExact(diagnoses.getSelectionModel().getSelectedItems().stream().count());
                    db.insertUserPatient(login.getText(), password.getText());
                    db.insertPatient(fio.getText(), Date.valueOf(birthdate.getValue()), pass.getText(), phone.getText(), address.getText(), snils.getText(), polis.getText());

                    for (int i = 0; i < n; i++){
                        String selected = String.valueOf(diagnoses.getSelectionModel().getSelectedItems().get(i));
                        db.insertPatientsHasDiagnosis(db.getIDDiagnose(selected), db.getMaxIdPatient());
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
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
