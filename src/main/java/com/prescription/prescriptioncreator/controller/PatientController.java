package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.appenum.Message;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.PatientRenderUtil;
import com.prescription.prescriptioncreator.util.ToastUtil;
import com.prescription.prescriptioncreator.util.ValidationUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

import static com.prescription.prescriptioncreator.appenum.IntegerValue.*;
import static com.prescription.prescriptioncreator.appenum.Message.*;

public class PatientController {
    @FXML
    TextField txtFName,txtLName,txtMobileNo;
    @FXML
    TextArea txtAddress;
    @FXML
    DatePicker dob;
    @FXML
    TextField txtAgeInYears;
    @FXML
    ComboBox<String> cmbSex;
    Stage stage = new Stage();
    @FXML
    public void addPatientDetails ( ActionEvent event) throws Exception {
        if(!(
                ValidationUtil.isTextFieldBlank(txtFName, Message.PATIENT_FNAME_BlANK.val()) ||
                ValidationUtil.isTextFieldBlank(txtLName, Message.PATIENT_LNAME_BlANK.val()) ||
                ValidationUtil.isComboBoxBlank(cmbSex, Message.PATIENT_SEX_BLANK.val()) ||
                ValidationUtil.isValidMobileNumber(txtMobileNo,Message.PATIENT_MOBILE_NO_MISMATCH.val()) ||
                ValidationUtil.isNumeric(txtAgeInYears,Message.AGE_IN_INTEGER_BLANK.val()) ||
                ValidationUtil.isDatePickerBlank(dob,Message.DATEPICKER_BLANK.val()))

        ){
            PatientService patientService = new PatientServiceImpl();
            PatientDetails patientDetails = new PatientDetails();
            patientDetails.setFirst_name(txtFName.getText());
            patientDetails.setLast_name(txtLName.getText());
            patientDetails.setSex(cmbSex.getValue());
            patientDetails.setMobile_no(txtMobileNo.getText());
            patientDetails.setAddress(txtAddress.getText());


            patientDetails.setDob(java.sql.Date.valueOf(dob.getValue()));
            patientDetails.setAge_in_years(Integer.parseInt(txtAgeInYears.getText().equals("") ? "0" :txtAgeInYears.getText() ));

            if(patientService.addPatient(patientDetails)==true){
                ToastUtil.makeText(stage, ADD_PATIENT_SUCCESS.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), SUCCESS.val());

            }
            else {
                ToastUtil.makeText(stage, ADD_PATIENT_ERROR.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());
            }
        }

        //System.out.println("AddSex "+sex);

    }

    @FXML
    public void initialize() throws Exception {

        Scene scne= new Scene(new VBox());
        stage.setScene(scne);

        setEnterKeyHandler(txtFName, txtLName);
        setEnterKeyHandler(txtLName, cmbSex);
        setEnterKeyHandler(cmbSex, txtMobileNo);
        setEnterKeyHandler(txtMobileNo, dob );
        setEnterKeyHandler(dob, txtAgeInYears);
        setEnterKeyHandler(txtAgeInYears, txtAddress);

        // Add an event filter for Enter key press at the scene level

    }
    public static void setEnterKeyHandler(Node currentField, Node nextField) {
        currentField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                nextField.requestFocus();
            }
        });
    }

}