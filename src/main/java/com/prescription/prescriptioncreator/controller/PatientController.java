package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.Message;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.ValidationUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PatientController {
    @FXML
    TextField txtFName,txtLName,txtAge,txtMobileNo;
    @FXML
    TextArea txtAddress;
    @FXML
    ComboBox<String> cmbSex;
    @FXML
    public void addPatientDetails ( ActionEvent event) throws Exception {
        if(!
                ValidationUtil.isTextFieldBlank(txtFName, Message.PATIENT_FNAME_BlANK.val()) &&
                ValidationUtil.isTextFieldBlank(txtLName, Message.PATIENT_LNAME_BlANK.val()) &&
                ValidationUtil.isTextFieldBlank(txtAge, Message.PATIENT_AGE_BlANK.val()) &&
                ValidationUtil.isComboBoxBlank(cmbSex, Message.PATIENT_SEX_BLANK.val())
        ){
            PatientService patientService = new PatientServiceImpl();
            PatientDetails patientDetails = new PatientDetails();
            patientDetails.setFirst_name(txtFName.getText());
            patientDetails.setLast_name(txtLName.getText());
            patientDetails.setSex(cmbSex.getValue());
            patientDetails.setMobile_no(txtMobileNo.getText());
            patientDetails.setAddress(txtAddress.getText());
            patientDetails.setAge(Integer.parseInt(txtAge.getText()));
            patientService.addPatient(patientDetails);
        }

        //System.out.println("AddSex "+sex);

    }
    @FXML
    public void initialize() throws Exception {

        txtAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*")) {
                    int value = Integer.parseInt(newValue);
                } else {
                    txtAge.setText(oldValue);
                }
            }
        });
    }
}