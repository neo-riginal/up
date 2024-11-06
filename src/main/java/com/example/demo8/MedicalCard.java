package com.example.demo8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class MedicalCard {
    @FXML
    private Button toCabinet;

    @FXML
    private ListView<String> diagnoses;

    @FXML
    private TableView<Patients> patients;

    @FXML
    private TableColumn<Patients, String> fio;

    @FXML
    private TableColumn<Patients, Date> birthdate;

    @FXML
    private TableColumn<Patients, String> phone;

    @FXML
    private TableColumn<Patients, String> address;

    @FXML
    private TableView<Appointment> appointments;

    @FXML
    private TableColumn<Appointment, String> doctor;

    @FXML
    private TableColumn<Appointment, String> day_of_week;

    @FXML
    private TableColumn<Appointment, Time> time;

    @FXML
    private TableColumn<Appointment, String> service;

    @FXML
    private TableColumn<Appointment, Double> cost;
    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        ArrayList<String> fioPatient = db.getFIOPatients();
        ArrayList<Date> birthdayPatient = db.getBirthdatePatients();
        ArrayList<String> phonePatient = db.getPhonePatients();
        ArrayList<String> addressPatient = db.getAddressPatients();
        ObservableList<Patients> data = FXCollections.observableArrayList();


        for (int i = 0; i < fioPatient.size(); i++) {
            data.add(new Patients(fioPatient.get(i), birthdayPatient.get(i), phonePatient.get(i), addressPatient.get(i)));
        }

        fio.setCellValueFactory(new PropertyValueFactory<Patients, String>("fio"));

        birthdate.setCellValueFactory(new PropertyValueFactory<Patients, Date>("birthdate"));

        phone.setCellValueFactory(new PropertyValueFactory<Patients, String>("phone"));

        address.setCellValueFactory(new PropertyValueFactory<Patients, String>("address"));

        patients.setItems(data);
        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList();

        patients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int p = Math.toIntExact(patients.getSelectionModel().getSelectedItems().stream().count());
                diagnoses.getItems().clear();
                for (int i = 0; i < p; i++) {
                    TableView.TableViewSelectionModel<Patients> selectionModel = patients.getSelectionModel();
                    Patients selectedItem = selectionModel.getSelectedItems().get(i);

                    if (selectedItem != null) {

                        String column1Value = (String) patients.getColumns().get(0).getCellData(selectedItem);
                        Date column2Value = (Date) patients.getColumns().get(1).getCellData(selectedItem);
                        String column3Value = (String) patients.getColumns().get(2).getCellData(selectedItem);
                        String column4Value = (String) patients.getColumns().get(3).getCellData(selectedItem);

                        int idPat = db.getIdPatient(column1Value, column2Value, column3Value, column4Value);
                        try {
                            langs.addAll(db.getDiagnoseForPatient(idPat));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        diagnoses.setItems(langs);

                    }

                }

                appointments.getItems().clear();
                Patients selectedItem = (Patients) patients.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String column1Value = (String) patients.getColumns().get(0).getCellData(selectedItem);
                    Date column2Value = (Date) patients.getColumns().get(1).getCellData(selectedItem);
                    String column3Value = (String) patients.getColumns().get(2).getCellData(selectedItem);
                    String column4Value = (String) patients.getColumns().get(3).getCellData(selectedItem);

                    int idPatient = db.getIdPatient(column1Value, column2Value, column3Value, column4Value);
                    ArrayList<Appointment> appointmentsList = db.getAppointmentsForPatient(idPatient);

                    ObservableList<Appointment> appointmentsData = FXCollections.observableArrayList(appointmentsList);
                    appointments.setItems(appointmentsData);

                    doctor.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctor"));
                    //speciality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("speciality"));
                    day_of_week.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Day_of_weekAppointment"));
                    time.setCellValueFactory(new PropertyValueFactory<Appointment, Time>("Time_appointment"));
                    service.setCellValueFactory(new PropertyValueFactory<Appointment, String>("nameService"));
                    cost.setCellValueFactory(new PropertyValueFactory<Appointment, Double>("costAppointment"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
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
