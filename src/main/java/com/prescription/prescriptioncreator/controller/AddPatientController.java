package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
    TextField txtLName;
    @FXML
    TextField txtAge;
    @FXML
    TextField txtMobileNo;
    @FXML
    TextArea txtAddress;
    @FXML
    ComboBox cmbSex;
    @FXML
    public void addPatientDetails ( ActionEvent event) throws Exception {

        PatientService patientService = new PatientServiceImpl();
        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setFirst_name(txtFName.getText());
        patientDetails.setLast_name(txtLName.getText());
        patientDetails.setSex(cmbSex.getEditor().getText());
        patientDetails.setMobile_no(txtMobileNo.getText());
        patientDetails.setAddress(txtAddress.getText());
        patientDetails.setAge(Integer.parseInt(txtAge.getText()));
        patientService.addPatient(patientDetails);
        //System.out.println("AddSex "+sex);

    }
}
