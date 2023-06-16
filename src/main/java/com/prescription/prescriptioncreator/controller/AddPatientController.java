package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.util.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddPatientController {


    @FXML
    TextField txtFName;
    @FXML

    public void addPatientDetails ( ActionEvent event) throws SQLException {
        String first_name=txtFName.getText();

        System.out.println("AddPatient "+first_name);
    }
}
