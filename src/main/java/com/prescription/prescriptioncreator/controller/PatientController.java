package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.appenum.Message;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.FXMLUtil;
import com.prescription.prescriptioncreator.util.PatientRenderUtil;
import com.prescription.prescriptioncreator.util.ToastUtil;
import com.prescription.prescriptioncreator.util.ValidationUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
        if(
                (!(ValidationUtil.isTextFieldBlank(txtFName, Message.PATIENT_FNAME_BlANK.val()) ||
                ValidationUtil.isTextFieldBlank(txtLName, Message.PATIENT_LNAME_BlANK.val()) ||
                ValidationUtil.isComboBoxBlank(cmbSex, Message.PATIENT_SEX_BLANK.val()) ||
                ValidationUtil.isValidMobileNumber(txtMobileNo,Message.PATIENT_MOBILE_NO_MISMATCH.val()))) &&
                        !(ValidationUtil.isNotNumeric(txtAgeInYears,Message.AGE_IN_INTEGER_BLANK.val()) &&
                                ValidationUtil.isDatePickerBlank(dob,Message.AGE_IN_INTEGER_BLANK.val()))

        ){
            dob.setStyle("");
            txtAgeInYears.setStyle("");
            PatientService patientService = new PatientServiceImpl();
            PatientDetails patientDetails = new PatientDetails();
            patientDetails.setFirst_name(txtFName.getText());
            patientDetails.setLast_name(txtLName.getText());
            patientDetails.setSex(cmbSex.getValue());
            patientDetails.setMobile_no(txtMobileNo.getText());
            patientDetails.setAddress(txtAddress.getText());

            if(dob.getValue()!=null)
            patientDetails.setDob(java.sql.Date.valueOf(dob.getValue()));
            patientDetails.setAge_in_years(Integer.parseInt(txtAgeInYears.getText().equals("") ? "0" :txtAgeInYears.getText() ));

            if(patientService.addPatient(patientDetails)==true){
                ToastUtil.makeText(stage, ADD_PATIENT_SUCCESS.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), SUCCESS.val());
                closeRegistrationWindow();
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
        dob.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                if(newValue!=null)
                    txtAgeInYears.setDisable(true);
                else
                    txtAgeInYears.setDisable(false);


                System.out.println(newValue);
            }
        });
        // Add an event filter for Enter key press at the scene level
    }
    public static void setEnterKeyHandler(Node currentField, Node nextField) {
        currentField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                nextField.requestFocus();
            }
        });
    }
    //Closing the current window after successfully adding the details
    private void closeRegistrationWindow() {
        // Get the reference to the current window's Stage and close it
        Stage currentStage = (Stage) txtFName.getScene().getWindow();
        currentStage.close();
    }

}