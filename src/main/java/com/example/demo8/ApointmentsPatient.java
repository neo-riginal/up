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

public class ApointmentsPatient {
    @FXML
    private ListView<String> specialities;

    @FXML
    private TableView<OurServices> services;

    @FXML
    private TableColumn<OurServices, String> nameServ;

    @FXML
    private TableColumn<OurServices, Double> costServ;

    @FXML
    private ListView<String> doctors;

    @FXML
    private ListView<String> dayWeek;

    @FXML
    private ListView<Time> timeOfDay;

    @FXML
    private TextField Cost; //поле ввода видимого пароля


    @FXML
    private Button toCabinet;

    @FXML
    private Button save;

    DB db = null;

    int idDoc;

    int idTime;

    int idSerCost;

    double cost = 0.0;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException, ParseException {

        db = new DB();

        Cost.setDisable(true);

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
            services.getItems().clear();
            dayWeek.getItems().clear();
            timeOfDay.getItems().clear();
            Cost.clear();
            cost = 0.0;
            int j = Math.toIntExact(specialities.getSelectionModel().getSelectedItems().stream().count());
            for (int i = 0; i < j; i++) {
                String selected = String.valueOf(specialities.getSelectionModel().getSelectedItems().get(i));
                try {
                    ArrayList<String> selectedItems2 = new ArrayList<>();
                    ObservableList<String> langs2 = null;
                    langs2 = FXCollections.observableArrayList(db.getFIOForDoctor(db.getIDSpeciality(selected)));

                    doctors.setItems(langs2);

                    // получаем модель выбора элементов
                    MultipleSelectionModel<String> langsSelectionModel2 = doctors.getSelectionModel();

                    // устанавливаем слушатель для отслеживания изменений
                    langsSelectionModel2.selectedItemProperty().addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                            ObservableList<String> selected = langsSelectionModel2.getSelectedItems();
                            selectedItems2.add(selected.toString());
                        }
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        doctors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            dayWeek.getItems().clear();
            timeOfDay.getItems().clear();
            services.getItems().clear();
            Cost.clear();
            cost = 0.0;
            //создаем список объектов
//            ArrayList<String> selectedItems2 = new ArrayList<>();
//            ObservableList<String> langs2 = null;
//            try {
//                langs2 = FXCollections.observableArrayList(db.getDayOfWeekForDoctor(idDoc));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
            int j = Math.toIntExact(doctors.getSelectionModel().getSelectedItems().stream().count());
            for (int i = 0; i < j; i++) {
                String selected = String.valueOf(doctors.getSelectionModel().getSelectedItems().get(i));
                try {
                    idDoc = db.getIDOfDoctor(selected);

                    ArrayList<String> selectedItems2 = new ArrayList<>();
                    ObservableList<String> langs2 = null;
                    langs2 = FXCollections.observableArrayList(db.getDayOfWeekForDoctor(idDoc));

                    dayWeek.setItems(langs2);

                    // получаем модель выбора элементов
                    MultipleSelectionModel<String> langsSelectionModel2 = dayWeek.getSelectionModel();

                    // устанавливаем слушатель для отслеживания изменений
                    langsSelectionModel2.selectedItemProperty().addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                            ObservableList<String> selected = langsSelectionModel2.getSelectedItems();
                            selectedItems2.add(selected.toString());
                        }
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        dayWeek.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            timeOfDay.getItems().clear();
            services.getItems().clear();
            Cost.clear();
            cost = 0.0;
            int j = Math.toIntExact(dayWeek.getSelectionModel().getSelectedItems().stream().count());
            for (int i = 0; i < j; i++) {
                String selected = String.valueOf(dayWeek.getSelectionModel().getSelectedItems().get(i));
                try {
                    //создаем список объектов
                    ArrayList<Time> selectedItems3 = new ArrayList<>();
                    ObservableList<Time> langs3 = null;
                    langs3 = FXCollections.observableArrayList(db.getTimeBeginningForDoctor(selected, idDoc));

                    timeOfDay.setItems(langs3);

                    // получаем модель выбора элементов
                    MultipleSelectionModel<Time> langsSelectionModel3 = timeOfDay.getSelectionModel();

                    // устанавливаем слушатель для отслеживания изменений
                    langsSelectionModel3.selectedItemProperty().addListener(new ChangeListener<Time>() {
                        @Override
                        public void changed(ObservableValue<? extends Time> observableValue, Time time, Time t1) {
                            ObservableList<Time> selected = langsSelectionModel3.getSelectedItems();
                            selectedItems3.addAll(selected);
                        }

//                        public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
//
//                            ObservableList<Time> selected = langsSelectionModel3.getSelectedItems();
//                            selectedItems3.add(selected.toString());
//                        }
                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        timeOfDay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            services.getItems().clear();
            Cost.clear();
            cost = 0.0;
            int n = Math.toIntExact(doctors.getSelectionModel().getSelectedItems().stream().count());
            for (int j = 0; j < n; j++) {
                String selected = String.valueOf(doctors.getSelectionModel().getSelectedItems().get(j));
                try {
                    idDoc = (db.getIDOfDoctor(selected));

                    ArrayList<OurServices> costSer = db.getOurServicesOfDoctor(idDoc);


                    ObservableList<OurServices> data = FXCollections.observableArrayList(costSer);

                    nameServ.setCellValueFactory(new PropertyValueFactory<OurServices, String>("Service"));
                    costServ.setCellValueFactory(new PropertyValueFactory<OurServices, Double>("CostS"));

                    services.setItems(data);

                    services.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        services.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        Cost.clear();
        cost = 0.0;
            int n = Math.toIntExact(services.getSelectionModel().getSelectedItems().stream().count());
            for (int j = 0; j < n; j++) {
                TableView.TableViewSelectionModel<OurServices> selectionModel = services.getSelectionModel();
                OurServices selectedItem = selectionModel.getSelectedItems().get(j);
                if (selectedItem != null) {
                    String column1Value = (String) services.getColumns().get(0).getCellData(selectedItem);
                    Double column2Value = (Double) services.getColumns().get(1).getCellData(selectedItem);
                    // Получите значения из других столбцов по аналогии

                    cost += column2Value;
                    Cost.setText(String.valueOf(cost));
                }
            }
        });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int n1 = Math.toIntExact(dayWeek.getSelectionModel().getSelectedItems().stream().count());
                    int n2 = Math.toIntExact(dayWeek.getSelectionModel().getSelectedItems().stream().count());
                    for (int j = 0; j < n1; j++) {
                        String selected2 = String.valueOf(dayWeek.getSelectionModel().getSelectedItems().get(j));
                        for (int i = 0; i < n2; i++) {
                            Time selected3 = timeOfDay.getSelectionModel().getSelectedItems().get(i);
                            idTime = db.getIDTime(selected2, selected3);
                        }
                    }

                    int n = Author.idUser;

                    int p = Math.toIntExact(services.getSelectionModel().getSelectedItems().stream().count());
                    for (int i = 0; i < p; i++) {

                        TableView.TableViewSelectionModel<OurServices> selectionModel = services.getSelectionModel();
                        OurServices selectedItem = selectionModel.getSelectedItems().get(i);
                        if (selectedItem != null) {
                            String column1Value = (String) services.getColumns().get(0).getCellData( selectedItem);
                            Double column2Value = (Double) services.getColumns().get(1).getCellData( selectedItem);
                            // Получите значения из других столбцов по аналогии
                            idSerCost = db.getIdServicesCost(column1Value, column2Value);
                            System.out.println(idSerCost);
                        }
                        db.insertAppointmentService(idSerCost, db.getMaxIdAppointment());
                    }
                    db.insertAppointment(db.getIdPatientOfUser(n), idDoc, idTime, cost);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CabinetPatient.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage previousStage = new Stage();
                previousStage.setScene(new Scene(root));
                //Image icon = new Image("C:/Users/Admin/IdeaProjects/kurs/src/main/resources/red.jpg");// Установка иконки для главного окна приложения
                //previousStage.getIcons().add(icon);
                // Закрываем текущее окно и открываем предыдущее
                currentStage.close();
                previousStage.show();
            }
        });

        }
    }

