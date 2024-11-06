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
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;

public class ListDoctors {
    @FXML
    private Button toCabinet;

    @FXML
    private ListView<String> specialities;

    @FXML
    private TableView<Doctors> doctors;

    @FXML
    private TableColumn<Doctors, String> fio;

    @FXML
    private TableColumn<Doctors, Date> birthdate;

    @FXML
    private TableColumn<Doctors, String> phone;

    @FXML
    private TableColumn<Doctors, String> address;


    DB db = null;

    int specId;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        final int a = db.getUsersRole(Author.idUser);

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
            doctors.getItems().clear();
            int n = Math.toIntExact(specialities.getSelectionModel().getSelectedItems().stream().count());
            for (int j = 0; j < n; j++) {
                String selected = String.valueOf(specialities.getSelectionModel().getSelectedItems().get(j));
                try {
                    specId = db.getIDSpeciality(selected);

                    ArrayList<String> fioDoctor = db.getFIOForDoctor(specId);
                    ArrayList<Date> birthdayDoctor = db.getBirthdateForDoctor(specId);
                    ArrayList<String> phoneDoctor = db.getPhoneForDoctor(specId);
                    ArrayList<String> addressDoctor = db.getAddressForDoctor(specId);
                    ObservableList<Doctors> data = FXCollections.observableArrayList();

                    doctors.getItems().clear(); // очищаем TableView от предыдущих данных
                    data.clear(); // очищаем массив данных

                    for (int i = 0; i < fioDoctor.size(); i++) {
                        data.add(new Doctors(fioDoctor.get(i), birthdayDoctor.get(i), phoneDoctor.get(i), addressDoctor.get(i)));
                    }

                    fio.setCellValueFactory(new PropertyValueFactory<Doctors, String>("fio"));

                    birthdate.setCellValueFactory(new PropertyValueFactory<Doctors, Date>("birthdate"));

                    phone.setCellValueFactory(new PropertyValueFactory<Doctors, String>("phone"));

                    address.setCellValueFactory(new PropertyValueFactory<Doctors, String>("address"));

                    doctors.setItems(data);


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
                FXMLLoader loader = null;
                if (a == 1) {
                    // Получаем ссылку на предыдущее окно
                    loader = new FXMLLoader(getClass().getResource("cabinetDoctor.fxml"));
                } else {
                    // Получаем ссылку на предыдущее окно
                    loader = new FXMLLoader(getClass().getResource("cabinetPatient.fxml"));
                }

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
