-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema kurs2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kurs2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kurs2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `kurs2` ;

-- -----------------------------------------------------
-- Table `kurs2`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `Role_idRole` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_idRole`)
    REFERENCES `kurs2`.`role` (`idRole`))
ENGINE = InnoDB
AUTO_INCREMENT = 87
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`doctors` (
  `idDoctors` INT NOT NULL AUTO_INCREMENT,
  `fio` VARCHAR(100) NOT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idDoctors`),
  CONSTRAINT `fk_Doctors_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `kurs2`.`user` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`patients` (
  `idPatients` INT NOT NULL AUTO_INCREMENT,
  `fio` VARCHAR(100) NULL DEFAULT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  `passport` INT NULL DEFAULT NULL,
  `phone` INT NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `snils` INT NULL DEFAULT NULL,
  `polis_OMS` INT NULL DEFAULT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idPatients`),
  CONSTRAINT `fk_Patients_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `kurs2`.`user` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`time_appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`time_appointments` (
  `idTime` INT NOT NULL AUTO_INCREMENT,
  `Day_of_week` VARCHAR(45) NULL DEFAULT NULL,
  `Time_beginning` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`idTime`))
ENGINE = InnoDB
AUTO_INCREMENT = 56
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`apointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`apointments` (
  `idApointments` INT NOT NULL AUTO_INCREMENT,
  `Patients_idPatients` INT NOT NULL,
  `Doctors_idDoctors` INT NOT NULL,
  `Time_appointments_idTime` INT NOT NULL,
  `cost` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idApointments`),
  CONSTRAINT `fk_Apointments_Doctors1`
    FOREIGN KEY (`Doctors_idDoctors`)
    REFERENCES `kurs2`.`doctors` (`idDoctors`),
  CONSTRAINT `fk_Apointments_Patients1`
    FOREIGN KEY (`Patients_idPatients`)
    REFERENCES `kurs2`.`patients` (`idPatients`),
  CONSTRAINT `fk_Apointments_Time_appointments1`
    FOREIGN KEY (`Time_appointments_idTime`)
    REFERENCES `kurs2`.`time_appointments` (`idTime`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`diagnosis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`diagnosis` (
  `IdDiagnose` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`IdDiagnose`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`doctors_has_time`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`doctors_has_time` (
  `Doctors_idDoctors` INT NOT NULL,
  `Time_appointments_idTime` INT NOT NULL,
  PRIMARY KEY (`Doctors_idDoctors`, `Time_appointments_idTime`),
  CONSTRAINT `fk_Doctors_has_Time_appointments_Doctors1`
    FOREIGN KEY (`Doctors_idDoctors`)
    REFERENCES `kurs2`.`doctors` (`idDoctors`),
  CONSTRAINT `fk_Doctors_has_Time_appointments_Time_appointments1`
    FOREIGN KEY (`Time_appointments_idTime`)
    REFERENCES `kurs2`.`time_appointments` (`idTime`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`patients_has_diagnosis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`patients_has_diagnosis` (
  `Patients_idPatients` INT NOT NULL,
  `Diagnosis_Role` INT NOT NULL,
  PRIMARY KEY (`Patients_idPatients`, `Diagnosis_Role`),
  CONSTRAINT `fk_Patients_has_Diagnosis_Diagnosis1`
    FOREIGN KEY (`Diagnosis_Role`)
    REFERENCES `kurs2`.`diagnosis` (`IdDiagnose`),
  CONSTRAINT `fk_Patients_has_Diagnosis_Patients1`
    FOREIGN KEY (`Patients_idPatients`)
    REFERENCES `kurs2`.`patients` (`idPatients`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`services` (
  `idservices` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `cost` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idservices`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`services_has_apointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`services_has_apointments` (
  `services_idservices` INT NOT NULL,
  `apointments_idApointments` INT NOT NULL,
  PRIMARY KEY (`services_idservices`, `apointments_idApointments`),
  CONSTRAINT `fk_services_has_apointments_apointments1`
    FOREIGN KEY (`apointments_idApointments`)
    REFERENCES `kurs2`.`apointments` (`idApointments`),
  CONSTRAINT `fk_services_has_apointments_services1`
    FOREIGN KEY (`services_idservices`)
    REFERENCES `kurs2`.`services` (`idservices`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`services_has_doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`services_has_doctors` (
  `Services_idservices` INT NOT NULL,
  `Doctors_idDoctors` INT NOT NULL,
  PRIMARY KEY (`Services_idservices`, `Doctors_idDoctors`),
  CONSTRAINT `fk_Services_has_Doctors_Doctors1`
    FOREIGN KEY (`Doctors_idDoctors`)
    REFERENCES `kurs2`.`doctors` (`idDoctors`),
  CONSTRAINT `fk_Services_has_Doctors_Services1`
    FOREIGN KEY (`Services_idservices`)
    REFERENCES `kurs2`.`services` (`idservices`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`speciality`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`speciality` (
  `idSpeciality` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idSpeciality`))
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kurs2`.`speciality_has_doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kurs2`.`speciality_has_doctors` (
  `Speciality_idSpeciality` INT NOT NULL,
  `Doctors_idDoctors` INT NOT NULL,
  PRIMARY KEY (`Speciality_idSpeciality`, `Doctors_idDoctors`),
  CONSTRAINT `fk_Speciality_has_Doctors_Doctors1`
    FOREIGN KEY (`Doctors_idDoctors`)
    REFERENCES `kurs2`.`doctors` (`idDoctors`),
  CONSTRAINT `fk_Speciality_has_Doctors_Speciality1`
    FOREIGN KEY (`Speciality_idSpeciality`)
    REFERENCES `kurs2`.`speciality` (`idSpeciality`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;