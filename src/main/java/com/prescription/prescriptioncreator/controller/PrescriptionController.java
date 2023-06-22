package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.DateUtil;
import com.prescription.prescriptioncreator.util.FXMLUtil;
import com.prescription.prescriptioncreator.util.PatientRenderUtil;
import javafx.event.EventHandler;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionController {
    @FXML
    private ComboBox<String> cmbWhen,cmbNoOFDays;
    @FXML
    TableView tblPrescription;

    @FXML
    TextField  txtMobileNo;
    @FXML
    TextField  txtPatientId;

    @FXML
    TableColumn <PatientDetails, String> clmnMedicine;
    @FXML
    TableColumn <PatientDetails, String> clmnT1,clmnT2,clmnT3,clmnT4,clmnT5,clmnT6,clmnNote;



    @FXML
    TableView tblPatient;
    @FXML
    TableColumn <PatientDetails, String> tblPatientName,tblPatientAge,tblPatientSex,tblPatientAddress,tblPatientMobileNo,tblPatientId;

    @FXML
    private Label welcomeText;
    @FXML
    ButtonBar btnBar;
    @FXML
    TextField txtMedicineName;
    @FXML
    VBox mainVBox;

    @FXML
    DatePicker txtCurrentDate;


    private ObservableList<PrescriptionDetails> prescriptionData;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    public void initialize() {
        List<MedicineDetails> lstMedicine= new ArrayList<>();
        MedicineDetails md= new MedicineDetails();
        md.setMedicineName("cal pol");
        md.setNote("twice a day");
        lstMedicine.add(md);
        md= new MedicineDetails();
        md.setMedicineName("calpol 250");
        md.setNote("twice a day 1");
        lstMedicine.add(md);
        md= new MedicineDetails();
        md.setMedicineName("calpol 500");
        md.setNote("twice a day 2");
        lstMedicine.add(md);

        AutoCompletionBinding<MedicineDetails> acb = TextFields.bindAutoCompletion(txtMedicineName , lstMedicine);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<MedicineDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<MedicineDetails> event)
            {
                MedicineDetails valueFromAutoCompletion = event.getCompletion();

            }
        });

        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbNoOFDays.setItems(listDays);

        prescriptionData = FXCollections.observableArrayList();
        PrescriptionDetails pd = new PrescriptionDetails();
        pd.setMedicineName("Calpol 650");
        pd.setTime1("1/2");
        pd.setTime2("1/2");
        pd.setTime3("1/2");
        pd.setTime4("1/2");
        pd.setNote("For 5 days");
        prescriptionData.add(pd);
       // tblPatientName.setCellValueFactory(new PropertyValueFactory("Medicine"));

//        clmnT1.setCellValueFactory(new PropertyValueFactory("T1"));
//        clmnT2.setCellValueFactory(new PropertyValueFactory("T2"));
//        clmnT3.setCellValueFactory(new PropertyValueFactory("T3"));
//        clmnT4.setCellValueFactory(new PropertyValueFactory("T4"));
//        clmnNote.setCellValueFactory(new PropertyValueFactory("Note"));
//        tblPrescription.setItems(data);
      //  txtAddMedicine.bindAutoCompletion(textfield,"text to suggest", "another text to suggest");
        txtCurrentDate.setValue(DateUtil.NOW_LOCAL_DATE());
         // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.
    }



    @FXML
    public void openAddPatient( ActionEvent event){

        FXMLUtil.openChildWindow("/fxml/addpatient-view.fxml",540,220,"Add Patient");
    }

    @FXML
    public void searchPatientDetails ( ActionEvent event) throws Exception {
        PatientService patientService= new PatientServiceImpl();
        String mobileNo=txtMobileNo.getText();
        String patientId=txtPatientId.getText();
        List<PatientDetails> lstPatient=patientService.searchPatientDetails(mobileNo,patientId);
        PatientRenderUtil.displayPatientDetails( lstPatient, tblPatient, tblPatientName, tblPatientAge,tblPatientSex, tblPatientAddress, tblPatientMobileNo,tblPatientId);
        System.out.println("Search"+mobileNo);
    }

    @FXML
    public void openAddMedicine( ActionEvent event){

        FXMLUtil.openChildWindow("/fxml/addmedicine-view.fxml",540,220,"Add Medicine");

    }
}