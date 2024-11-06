package com.example.demo8;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

    public class DB {
        private final String HOST = "127.0.0.1";
        private final String PORT = "13306";
        private final String DB_NAME = "kurs2";
        private final String LOGIN = "root";
        private final String PASS = "changeme";

        private Connection dbConn = null;
        private static String login;
        private static String password;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?characterEncoding=UTF8";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public int getUsers(String log, String pass) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Role_idRole FROM User where login=? and password=? group by Role_idRole";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setString(1, log);
        statement.setString(2, pass);

        login = log;
        password = pass;

        ResultSet res = statement.executeQuery();
        Integer User_role = 0;
        while (res.next()) {
            User_role = res.getInt("Role_idRole");
        }
        return User_role;
    }

    public int getUsersRole(Integer idUser) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Role_idRole FROM User where idUser = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setInt(1, idUser);

        ResultSet res = statement.executeQuery();
        Integer User_role = 0;
        while (res.next()) {
            User_role = res.getInt("Role_idRole");
        }
        return User_role;
    }

    public int getIdPatientOfUser(Integer idUser) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT idPatients FROM patients WHERE User_idUser = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setInt(1, idUser);

        ResultSet res = statement.executeQuery();
        Integer idPatient = 0;
        while (res.next()) {
            idPatient = res.getInt("idPatients");
        }
        return idPatient;
    }
    public int getIdUsers(String log, String pass) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idUser FROM User where login=? and password=?";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setString(1, log);
        statement.setString(2, pass);

        ResultSet res = statement.executeQuery();
        Integer User_id = 0;
        while (res.next()) {
            User_id = res.getInt("idUser");
        }
        return User_id;
    }

    public int getIdUsersForIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idUser FROM User inner join patients on user.idUser = patients.User_idUser where idPatients = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setInt(1, idPatient);

        ResultSet res = statement.executeQuery();
        Integer User_id = 0;
        while (res.next()) {
            User_id = res.getInt("idUser");
        }
        return User_id;
    }

    public int getIdUsersForIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idUser FROM User inner join doctors on user.idUser = doctors.User_idUser where idDoctors = ?";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setInt(1, idDoctor);

        ResultSet res = statement.executeQuery();
        Integer User_id = 0;
        while (res.next()) {
            User_id = res.getInt("idUser");
        }
        return User_id;
    }

    public ArrayList<String> getDiagnoseForPatient(Integer idPatient) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT name FROM diagnosis inner join patients_has_diagnosis on diagnosis.IdDiagnose = patients_has_diagnosis.Diagnosis_Role WHERE patients_has_diagnosis.Patients_idPatients = '" + idPatient + "' ";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> nameDiag = new ArrayList<>();
        while (res.next()) {
            nameDiag.add(res.getString("name"));
        }
        return nameDiag;
    }

    public int getIDDiagnose(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT IdDiagnose FROM diagnosis where name = '" + name + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("IdDiagnose");
        }
        return n;
    }

    public int getIDService(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idservices FROM services where name = '" + name + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idservices");
        }
        return n;
    }

    public int getIDOfDoctor(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idDoctors FROM doctors where fio = '" + name + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idDoctors");
        }
        return n;
    }

    public int getIDSpeciality(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idSpeciality FROM speciality where name = '" + name + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idSpeciality");
        }
        return n;
    }

    public int getIdPatient(String FIO, Date birthdate, String phone, String address) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idPatients FROM patients where fio = '" + FIO + "' and birthdate = '" + birthdate + "' and phone = '" + phone + "' and address = '" + address + "' ";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idPatients");
        }
        return n;
    }

    public int getIdPatientOfFIO(String FIO) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idPatients FROM patients where fio = '" + FIO + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idPatients");
        }
        return n;
    }

    public int getIdDoctorOfFIO(String FIO) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idDoctors FROM doctors where fio = '" + FIO + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idDoctors");
        }
        return n;

    }

    public int getIdServicesCost(String name, Double cost) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idservices FROM services where name = '" + name + "' and cost = '" + cost + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idservices");
        }
        return n;
    }

    public int getIDTime(String Day_of_week, Time Time_beginning) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idTime FROM time_appointments where Day_of_week = '" + Day_of_week + "' and Time_beginning = '" + Time_beginning + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("idTime");
        }
        return n;
    }

    public String getNameSpeciality(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM speciality where idSpeciality = '" + idSpeciality + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String speciality = "";
        while (res.next()) {
            speciality = res.getString("name");
        }
        return speciality;
    }

    public String getNameDiagnose(Integer idDiagnose) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM diagnosis where IdDiagnose = '" + idDiagnose + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String diagnose = "";
        while (res.next()) {
            diagnose = res.getString("name");
        }
        return diagnose;
    }

    public String getNameService(Integer idService) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM services where idservices = '" + idService + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String service = "";
        while (res.next()) {
            service = res.getString("name");
        }
        return service;
    }

    public Double getCostService(Integer idService) throws SQLException, ClassNotFoundException {
        String sql = "SELECT cost FROM services where idservices = '" + idService + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Double serviceCost = null;
        while (res.next()) {
            serviceCost = res.getDouble("cost");
        }
        return serviceCost;
    }

    public LocalDate getBirthdateIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT birthdate FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        LocalDate birthdate = null;
        while (res.next()) {
            birthdate = res.getDate("birthdate").toLocalDate();
        }
        return birthdate;
    }

    public int getPassIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT passport FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Integer pass = null;
        while (res.next()) {
            pass = res.getInt("passport");
        }
        return pass;
    }

    public int getPhoneIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT phone FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Integer phone = null;
        while (res.next()) {
            phone = res.getInt("phone");
        }
        return phone;
    }

    public String getAddressIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT address FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String address = null;
        while (res.next()) {
            address = res.getString("address");
        }
        return address;
    }

    public int getSnilsIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT snils FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Integer snils = null;
        while (res.next()) {
            snils = res.getInt("snils");
        }
        return snils;
    }

    public int getPolisIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT polis_OMS FROM patients where idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Integer polis = null;
        while (res.next()) {
            polis = res.getInt("polis_OMS");
        }
        return polis;
    }

    public String getLoginIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT login FROM user inner join patients on user.idUser = patients.User_idUser where patients.idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String login = null;
        while (res.next()) {
            login = res.getString("login");
        }
        return login;
    }

    public String getPasswordIdPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM user inner join patients on user.idUser = patients.User_idUser where patients.idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String pass = null;
        while (res.next()) {
            pass = res.getString("password");
        }
        return pass;
    }

    public LocalDate getBirthdateIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT birthdate FROM doctors where idDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        LocalDate birthdate = null;
        while (res.next()) {
            birthdate = res.getDate("birthdate").toLocalDate();
        }
        return birthdate;
    }

    public int getPhoneIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT phone FROM doctors where idDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        Integer phone = null;
        while (res.next()) {
            phone = res.getInt("phone");
        }
        return phone;
    }

    public String getAddressIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT address FROM doctors where idDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String address = null;
        while (res.next()) {
            address = res.getString("address");
        }
        return address;
    }

    public String getLoginIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT login FROM user inner join doctors on user.idUser = doctors.User_idUser where doctors.idDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String login = null;
        while (res.next()) {
            login = res.getString("login");
        }
        return login;
    }

    public String getPasswordIdDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT password FROM user inner join doctors on user.idUser = doctors.User_idUser where doctors.idDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        String pass = null;
        while (res.next()) {
            pass = res.getString("password");
        }
        return pass;
    }

    public int getMaxIdUser() throws SQLException, ClassNotFoundException {
        String sql = "SELECT max(idUser) as max FROM user";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        ResultSet res = prSt.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("max");
        }
        return n;
    }
    public int getMaxIdPatient() throws SQLException, ClassNotFoundException {
        String sql = "SELECT max(idPatients) as max FROM patients";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        ResultSet res = prSt.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("max");
        }
        return n;
    }

    public int getMaxIdDoctor() throws SQLException, ClassNotFoundException {
        String sql = "SELECT max(idDoctors) as max FROM doctors";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        ResultSet res = prSt.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("max");
        }
        return n;
    }

    public int getMaxIdAppointment() throws SQLException, ClassNotFoundException {
        String sql = "SELECT max(idApointments) as max FROM apointments";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);

        ResultSet res = prSt.executeQuery();
        int n = 0;
        while (res.next()) {
            n = res.getInt("max");
        }
        return n;
    }

    public ArrayList<String> getDiagnoses() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM diagnosis";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> diagnose = new ArrayList<>();
        while (res.next()) {
            diagnose.add(res.getString("name"));
        }
        return diagnose;
    }

    public ArrayList<String> getSpecialities() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM speciality";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> speciality = new ArrayList<>();
        while (res.next()) {
            speciality.add(res.getString("name"));
        }
        return speciality;
    }


    public ArrayList<Appointment> getAppointmentsForPatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "SELECT doctors.fio, time_appointments.Day_of_week, time_appointments.Time_beginning, services.name, services.cost FROM apointments inner join time_appointments on apointments.Time_appointments_idTime = time_appointments.idTime inner join services_has_apointments on services_has_apointments.apointments_idApointments = apointments.idApointments inner join services on services_has_apointments.services_idservices = services.idservices inner join doctors on doctors.idDoctors = apointments.Doctors_idDoctors where apointments.Patients_idPatients = '" + idPatient + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Appointment> appointments = new ArrayList<>();

        while (res.next()) {
            appointments.add(new Appointment(res.getString("doctors.fio"), res.getString("time_appointments.Day_of_week"), res.getTime("time_appointments.Time_beginning"), res.getString("services.name"), res.getDouble("services.cost")));
        }
        return appointments;
    }


    public ArrayList<String> getServices() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM services";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> service = new ArrayList<>();
        while (res.next()) {
            service.add(res.getString("name"));
        }
        return service;
    }

    public ArrayList<OurServices> getOurServicesOfDoctor(int idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT services.name, services.cost FROM services inner join services_has_doctors on services.idservices = services_has_doctors.Services_idservices inner join doctors on services_has_doctors.Doctors_idDoctors = doctors.IdDoctors where doctors.IdDoctors = '" + idDoctor + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<OurServices> service = new ArrayList<>();
        while (res.next()) {
            service.add(new OurServices(res.getString("name"), res.getDouble("cost")));
        }
        return service;
    }

    public ArrayList<String> getDayOfWeek() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Day_of_week FROM time_appointments";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> day = new ArrayList<>();
        while (res.next()) {
            day.add(res.getString("Day_of_week"));
        }
        return day;
    }

    public ArrayList<String> getDayOfWeekForDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT distinct Day_of_week FROM time_appointments inner join doctors_has_time on time_appointments.idTime = doctors_has_time.Time_appointments_idTime where doctors_has_time.Doctors_idDoctors = '"+ idDoctor +"'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> day = new ArrayList<>();
        while (res.next()) {
            day.add(res.getString("Day_of_week"));
        }
        return day;
    }

    public ArrayList<Time> getTimeBeginning() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Time_beginning FROM time_appointments";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Time> time = new ArrayList<>();
        while (res.next()) {
            time.add(res.getTime("Time_beginning"));
        }
        return time;
    }

    public ArrayList<Time> getTimeBeginningForDoctor(String nameDay, Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Time_beginning FROM time_appointments inner join  doctors_has_time on time_appointments.idTime = doctors_has_time.Time_appointments_idTime where doctors_has_time.Doctors_idDoctors = '"+ idDoctor +"' and time_appointments.Day_of_week = '" + nameDay + "'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Time> time = new ArrayList<>();
        while (res.next()) {
            time.add(res.getTime("Time_beginning"));
        }
        return time;
    }

    public ArrayList<Double> getCostServices() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cost FROM services";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Double> cost = new ArrayList<>();
        while (res.next()) {
            cost.add(res.getDouble("cost"));
        }
        return cost;
    }


    public ArrayList<String> getFIODoctors() throws SQLException, ClassNotFoundException {
        String sql = "SELECT fio FROM doctors";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> fio = new ArrayList<>();
        while (res.next()) {
            fio.add(res.getString("fio"));
        }
        return fio;
    }

    public ArrayList<String> getFIOPatients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT fio FROM patients";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> fio = new ArrayList<>();
        while (res.next()) {
            fio.add(res.getString("fio"));
        }
        return fio;
    }

    public ArrayList<String> getFIOForDoctor(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "SELECT fio FROM doctors inner join speciality_has_doctors on speciality_has_doctors.Doctors_idDoctors = doctors.idDoctors where speciality_has_doctors.Speciality_idSpeciality = '"+ idSpeciality +"'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> fio = new ArrayList<>();
        while (res.next()) {
            fio.add(res.getString("fio"));
        }
        return fio;
    }

    public ArrayList<Date> getBirthdatePatients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT birthdate FROM patients";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Date> birth = new ArrayList<>();
        while (res.next()) {
            birth.add(res.getDate("birthdate"));
        }
        return birth;
    }

    public ArrayList<Date> getBirthdateForDoctor(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "SELECT birthdate FROM doctors inner join speciality_has_doctors on speciality_has_doctors.Doctors_idDoctors = doctors.idDoctors where speciality_has_doctors.Speciality_idSpeciality = '"+ idSpeciality +"'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Date> birth = new ArrayList<>();
        while (res.next()) {
            birth.add(res.getDate("birthdate"));
        }
        return birth;
    }

    public ArrayList<String> getPhonePatients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT phone FROM patients";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> phone = new ArrayList<>();
        while (res.next()) {
            phone.add(res.getString("phone"));
        }
        return phone;
    }

    public ArrayList<String> getPhoneForDoctor(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "SELECT phone FROM doctors inner join speciality_has_doctors on speciality_has_doctors.Doctors_idDoctors = doctors.idDoctors where speciality_has_doctors.Speciality_idSpeciality = '"+ idSpeciality +"'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> phone = new ArrayList<>();
        while (res.next()) {
            phone.add(res.getString("phone"));
        }
        return phone;
    }

    public ArrayList<String> getAddressPatients() throws SQLException, ClassNotFoundException {
        String sql = "SELECT address FROM patients";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> address = new ArrayList<>();
        while (res.next()) {
            address.add(res.getString("address"));
        }
        return address;
    }

    public ArrayList<String> getAddressForDoctor(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "SELECT address FROM doctors  inner join speciality_has_doctors on speciality_has_doctors.Doctors_idDoctors = doctors.idDoctors where speciality_has_doctors.Speciality_idSpeciality = '"+ idSpeciality +"'";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<String> address = new ArrayList<>();
        while (res.next()) {
            address.add(res.getString("address"));
        }
        return address;
    }

    public void insertUserPatient(String login, String password) throws SQLException, ClassNotFoundException {
        String sql = "insert into user values (null, ?, ?, 2)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, login);
        prSt.setString(2, password);
        prSt.executeUpdate();
    }

    public void insertUserDoctor(String login, String password) throws SQLException, ClassNotFoundException {
        String sql = "insert into user values (null, ?, ?, 1)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, login);
        prSt.setString(2, password);
        prSt.executeUpdate();
    }

    public void insertPatient(String FIO, Date birthdate, String pass, String phone, String address, String snils, String polis) throws SQLException, ClassNotFoundException {
        String sql = "insert into patients values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, FIO);
        prSt.setString(2, String.valueOf(birthdate));
        prSt.setString(3, pass);
        prSt.setString(4, String.valueOf(phone));
        prSt.setString(5, address);
        prSt.setString(6, String.valueOf(snils));
        prSt.setString(7, String.valueOf(polis));
        prSt.setInt(8, getMaxIdUser());
        prSt.executeUpdate();
    }

    public void insertDoctor(String FIO, Date birthdate, String phone, String address) throws SQLException, ClassNotFoundException {
        String sql = "insert into doctors values (null, ?, ?, ?, ?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, FIO);
        prSt.setString(2, String.valueOf(birthdate));
        prSt.setString(3, phone);
        prSt.setString(4, address);
        prSt.setInt(5, getMaxIdUser());
        prSt.executeUpdate();
    }

    public void insertSpeciality(String name) throws SQLException, ClassNotFoundException {
        String sql = "insert into speciality values (null, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.executeUpdate();
    }

    public void insertDiagnose(String name) throws SQLException, ClassNotFoundException {
        String sql = "insert into diagnosis values (null, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.executeUpdate();
    }

    public void insertService(String name, double cost) throws SQLException, ClassNotFoundException {
        String sql = "insert into services values (null, ?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.setDouble(2, cost);
        prSt.executeUpdate();
    }

    public void insertDoctorsHasTime(Integer idDoctor, Integer idTime) throws SQLException, ClassNotFoundException {
        String sql = "insert into doctors_has_time values (?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idDoctor);
        prSt.setInt(2, idTime);
        prSt.executeUpdate();
    }

    public void insertPatientsHasDiagnosis(int Patients_idPatients, int Diagnosis_Role) throws SQLException, ClassNotFoundException  {
        String sql = "insert into patients_has_diagnosis values (?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, Patients_idPatients);
        prSt.setInt(2, Diagnosis_Role);
        prSt.executeUpdate();
    }

    public void insertServicesHasDoctors(int Services_idservices, int Doctors_idDoctors) throws SQLException, ClassNotFoundException  {
        String sql = "insert into services_has_doctors values (?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, Services_idservices);
        prSt.setInt(2, Doctors_idDoctors);
        prSt.executeUpdate();
    }

    public void insertSpecialityHasDoctors(int Speciality_idSpeciality, int Doctors_idDoctors) throws SQLException, ClassNotFoundException  {
        String sql = "insert into speciality_has_doctors values (?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, Speciality_idSpeciality);
        prSt.setInt(2, Doctors_idDoctors);
        prSt.executeUpdate();
    }

    public void insertAppointment(int idPatient, int idDoctor, int idTime, double cost) throws SQLException, ClassNotFoundException {
        String sql = "insert into apointments values (null, ?, ?, ?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idPatient);
        prSt.setInt(2, idDoctor);
        prSt.setInt(3, idTime);
        prSt.setDouble(4, cost);
        prSt.executeUpdate();
    }

    public void insertAppointmentService(int idService, int idAppointment) throws SQLException, ClassNotFoundException {
        String sql = "insert into services_has_apointments values (?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idService);
        prSt.setInt(2, idAppointment);
        prSt.executeUpdate();
    }

    public void updateSpeciality(String name, int idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "update speciality set name = ? where idSpeciality = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.setInt(2, idSpeciality);

        prSt.executeUpdate();
    }

    public void updateDiagnose(String name, int idDiagnose) throws SQLException, ClassNotFoundException {
        String sql = "update diagnosis set name = ? where IdDiagnose= ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, name);
        prSt.setInt(2, idDiagnose);

        prSt.executeUpdate();
    }

    public void updatePatient(String FIO, Date birthdate, String pass, String phone, String address, String snils, String polis, Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "update patients set fio = ?, birthdate = ?, passport = ?, phone = ? address = ?, snils = ?, polis = ? where idPatients =  ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, FIO);
        prSt.setString(2, String.valueOf(birthdate));
        prSt.setString(3, pass);
        prSt.setString(4, String.valueOf(phone));
        prSt.setString(5, address);
        prSt.setString(6, String.valueOf(snils));
        prSt.setString(7, String.valueOf(polis));
        prSt.setInt(8, idPatient);
        prSt.executeUpdate();
    }

    public void updateUser(String login, String password, Integer idUser) throws SQLException, ClassNotFoundException {
        String sql = "update user set login = ?, password = ? where idUser =  ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, login);
        prSt.setString(2, password);
        prSt.setInt(3, idUser);
        prSt.executeUpdate();
    }

    public void updateDoctor(String FIO, Date birthdate, String phone, String address, Integer idDoctors) throws SQLException, ClassNotFoundException {
        String sql = "update doctors set fio = ?, birthdate = ?, phone = ?, address = ? where idDoctors = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, FIO);
        prSt.setString(2, String.valueOf(birthdate));
        prSt.setString(3, phone);
        prSt.setString(4, address);
        prSt.setInt(5, idDoctors);
        prSt.executeUpdate();
    }

    public String updateService(String name, double cost, int idService)throws SQLException, ClassNotFoundException{
        String v = "Данные были обновлены";
        CallableStatement proc = getDbConnection().prepareCall("CALL update_service(?, ?, ?)");
        proc.setString(1, name);
        proc.setDouble(2, cost);
        proc.setInt(3, idService);
        ResultSet res = proc.executeQuery();
        return v;
    }



    public void deleteSpeciality(Integer idSpeciality) throws SQLException, ClassNotFoundException {
        String sql = "delete from speciality where idSpeciality = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idSpeciality);

        prSt.executeUpdate();
    }

    public void deleteDiagnose(Integer idDiagnose) throws SQLException, ClassNotFoundException {
        String sql = "delete from diagnosis where IdDiagnose = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idDiagnose);

        prSt.executeUpdate();
    }

    public void deleteService(Integer idService) throws SQLException, ClassNotFoundException {
        String sql = "delete from services where idservices = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idService);

        prSt.executeUpdate();
    }

    public void deletePatient(Integer idPatient) throws SQLException, ClassNotFoundException {
        String sql = "delete from patients where idPatients = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idPatient);

        prSt.executeUpdate();
    }

    public void deleteUser(Integer idUser) throws SQLException, ClassNotFoundException {
        String sql = "delete from user where idUser = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idUser);

        prSt.executeUpdate();
    }

    public void deleteDoctor(Integer idDoctor) throws SQLException, ClassNotFoundException {
        String sql = "delete from doctors where idDoctors = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idDoctor);

        prSt.executeUpdate();
    }


}
