package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.PatientDetails;
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
    ComboBox txtSex;
    @FXML
    public void addPatientDetails ( ActionEvent event) throws SQLException {
        String first_name = txtFName.getText();
        String last_name = txtLName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String mobile_no=txtMobileNo.getText();
        String address = txtAddress.getText();

        System.out.println("AddPatient "+first_name);
        System.out.println("AddPatient "+last_name);
        System.out.println("AddAge "+age);
        System.out.println("AddMobileNo "+mobile_no);
        System.out.println("AddAddress "+address);
        //System.out.println("AddSex "+sex);
    }
}
