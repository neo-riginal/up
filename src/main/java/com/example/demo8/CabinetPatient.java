package com.example.demo8;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ArrayList;

public class CabinetPatient {

    @FXML
    public Button Exit;

    @FXML
    public Button ToService;

    @FXML
    public Button ToDoctors;

    @FXML
    public Button ToAppointment;

    @FXML
    private ListView<String> diagnoses;

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
    void initialize() throws SQLException, ClassNotFoundException, IOException {

        db = new DB();

        int n = Author.idUser;


        ArrayList<String> selectedItems = new ArrayList<>(db.getDiagnoseForPatient(db.getIdPatientOfUser(n)));
        ObservableList<String> langs = FXCollections.observableArrayList(selectedItems);
        diagnoses.setItems(langs);

        ArrayList<Appointment> appointmentsList = db.getAppointmentsForPatient(db.getIdPatientOfUser(n));

        ObservableList<Appointment> appointmentsData = FXCollections.observableArrayList(appointmentsList);
        appointments.setItems(appointmentsData);

        doctor.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctor"));
        //speciality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("speciality"));
        day_of_week.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Day_of_weekAppointment"));
        time.setCellValueFactory(new PropertyValueFactory<Appointment, Time>("Time_appointment"));
        service.setCellValueFactory(new PropertyValueFactory<Appointment, String>("nameService"));
        cost.setCellValueFactory(new PropertyValueFactory<Appointment, Double>("costAppointment"));


        Exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Author.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 463, 274);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Внести новые данные");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) Exit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        ToService.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listServices.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 540, 371);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Услуги");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) Exit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ToDoctors.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listDoctors.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 1045, 403);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Доктора");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) Exit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ToAppointment.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Загрузка нового FXML-файла "author.fxml"
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("apointmentsPatient.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 1072, 672);
//                    Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
//                    stage.getIcons().add(icon);
                    stage.setTitle("Запись на услугу");
                    stage.setResizable(false); // Не позволяем изменять размер окна
                    stage.setScene(scene);
                    stage.show();

                    // Закрытие текущего окна
                    Stage currentStage = (Stage) Exit.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
