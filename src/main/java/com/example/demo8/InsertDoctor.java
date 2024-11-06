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
import java.util.concurrent.ScheduledExecutorService;

public class InsertDoctor {
    @FXML
    private TextField fio;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private Button toCabinet;

    @FXML
    private Button save;

    @FXML
    private ListView<String> specialities;

    @FXML
    private ListView<String> services;

    @FXML
    private TableView<Schedule> schedule;

    @FXML
    private TableColumn<Schedule, String> dayWeek;

    @FXML
    private TableColumn<Schedule, Time> timeOfDay;


    DB db = null;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        //создаем список объектов
        ArrayList<String> selectedItems = new ArrayList<>();
        ObservableList<String> langs = FXCollections.observableArrayList(db.getSpecialities());
        specialities.setItems(langs);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = specialities.getSelectionModel();
        langsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected = langsSelectionModel.getSelectedItems();
                selectedItems.add(selected.toString());
            }
        });

        //создаем список объектов
        ArrayList<String> selectedItems2 = new ArrayList<>();
        ObservableList<String> langs2 = FXCollections.observableArrayList(db.getServices());
        services.setItems(langs2);

        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel2 = services.getSelectionModel();
        langsSelectionModel2.setSelectionMode(SelectionMode.MULTIPLE);

        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel2.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                ObservableList<String> selected2 = langsSelectionModel2.getSelectedItems();
                selectedItems2.add(selected2.toString());
            }
        });


        //устанавливаем возможность выбирать несколько записей из tableview по времени
        schedule.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ArrayList<String> dayweek = db.getDayOfWeek();
        ArrayList<Time> time = db.getTimeBeginning();
        ObservableList<Schedule> data = FXCollections.observableArrayList();

        schedule.getItems().clear(); // очищаем TableView от предыдущих данных
        data.clear(); // очищаем массив данных

        for (int i = 0; i < dayweek.size(); i++) {
            data.add(new Schedule(dayweek.get(i), time.get(i)));
        }

        dayWeek.setCellValueFactory(new PropertyValueFactory<Schedule, String>("day_of_week"));
        timeOfDay.setCellValueFactory(new PropertyValueFactory<Schedule, Time>("time_beginning"));
        schedule.setItems(data);


        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int n = Math.toIntExact(services.getSelectionModel().getSelectedItems().stream().count());
                    int m = Math.toIntExact(specialities.getSelectionModel().getSelectedItems().stream().count());
                    int p = Math.toIntExact(schedule.getSelectionModel().getSelectedItems().stream().count());

                    db.insertUserDoctor(login.getText(), password.getText());
                    db.insertDoctor(fio.getText(), Date.valueOf(birthdate.getValue()), phone.getText(), address.getText());

                    for (int i = 0; i < n; i++){
                        String selected = String.valueOf(services.getSelectionModel().getSelectedItems().get(i));
                        db.insertServicesHasDoctors(db.getIDService(selected), db.getMaxIdDoctor());
                    }

                    for (int i = 0; i < m; i++){
                        String selected = String.valueOf(specialities.getSelectionModel().getSelectedItems().get(i));
                        db.insertSpecialityHasDoctors(db.getIDSpeciality(selected), db.getMaxIdDoctor());
                    }

                    for (int i = 0; i < p; i++){
                        TableView.TableViewSelectionModel<Schedule> selectionModel = schedule.getSelectionModel();
                        Schedule selectedItem = selectionModel.getSelectedItems().get(i);

                        if (selectedItem != null) {
                            String column1Value = (String) schedule.getColumns().get(0).getCellData(selectedItem);
                            Time column2Value = (Time) schedule.getColumns().get(1).getCellData(selectedItem);
                            db.insertDoctorsHasTime(db.getMaxIdDoctor(), db.getIDTime(column1Value, column2Value));
                            // Получите значения из других столбцов по аналогии
                        }
                        //String selected = String.valueOf(schedule.getSelectionModel().getSelectedItems().get(i));
                        //Time selected2 = Time.valueOf(schedule.getSelectionModel().getSelectedItems().get(i));
                        //System.out.println(selected);
                        //db.insertDoctorsHasTime(db.getMaxIdDoctor(), db.getIDTime(selected, selected2));

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
