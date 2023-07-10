package com.prescription.prescriptioncreator.controller;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.PrescriptionService;
import com.prescription.prescriptioncreator.service.impl.MedicineServiceImpl;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.service.impl.PrescriptionServiceImpl;
import com.prescription.prescriptioncreator.util.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
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
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionController {
    List<MedicineDetails> lstMedicineDetails= new ArrayList<>();
    @FXML
    TextField  txtD1,txtD2,txtD3,txtD4,txtD5,txtD6,txtNote;
    @FXML
    private ComboBox<String> cmbWhen,cmbNoOFDays;
    @FXML
    TableView tblPrescription;
    @FXML
    TableView<PreviousVisit> tblPreviousVisit;
    @FXML TableView<PatientDetails> tblPatient;

    @FXML
    TextField  txtMobileNo,txtPatientId,txtMedicineName;
    @FXML
    TableColumn <PreviousVisit, String> clmnPreviousVisit;


    @FXML
    TableColumn <MedicineDetails, String> clmnMedicineName,clmnD1,clmnD2,clmnD3,clmnD4,clmnD5,clmnD6,clmnWhen,clmnDays,clmnNote;
    @FXML
    TableColumn <PatientDetails, String> tblPatientName,tblPatientAge,tblPatientSex,tblPatientAddress,tblPatientMobileNo,tblPatientId;
    MedicineService medicineService= new MedicineServiceImpl();
    @FXML
    private Label welcomeText;
    @FXML
    ButtonBar btnBar;
    @FXML
    VBox mainVBox;

    @FXML
    DatePicker txtCurrentDate;

    private ObservableList<MedicineDetails> autoCompleteData;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

   static  AutoCompletionBinding acb =null;
    @FXML
    private void addToPrescription( ActionEvent event){

        MedicineDetails medicineDetails= new MedicineDetails();
        medicineDetails.setMedicineName(txtMedicineName.getText());
        medicineDetails.setDose1(txtD1.getText());
        medicineDetails.setDose2(txtD2.getText());
        medicineDetails.setDose3(txtD3.getText());
        medicineDetails.setDose4(txtD4.getText());
        medicineDetails.setDose5(txtD5.getText());
        medicineDetails.setDose6(txtD6.getText());
        medicineDetails.setNote(txtNote.getText());
        medicineDetails.setWhen(cmbWhen.getValue());
        medicineDetails.setNoOfDays(Integer.parseInt(cmbNoOFDays.getValue()));
        lstMedicineDetails.add(0,medicineDetails);
        PrescriptionRenderUtil.addToPrescription(lstMedicineDetails, tblPrescription, clmnMedicineName,clmnD1,clmnD2,clmnD3,clmnD4,clmnD5,clmnD6,clmnWhen,clmnDays,clmnNote);

    }

    public  List<PreviousVisit>  getVisitDetails(int patientID) throws Exception {
        PrescriptionService ps= new PrescriptionServiceImpl();
       return  ps.getVisitDetails(patientID);

    }

    @FXML
    public void initialize() throws Exception {
        autoCompleteData= FXCollections.observableArrayList(medicineService.getAutoSuggestMedicine());
        acb = TextFields.bindAutoCompletion(txtMedicineName ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<MedicineDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<MedicineDetails> event)
            {

                MedicineDetails value = event.getCompletion();
                txtD1.setText(value.getDose1());
                txtD2.setText(value.getDose2());
                txtD3.setText(value.getDose3());
                txtD4.setText(value.getDose4());
                txtD5.setText(value.getDose5());
                txtD6.setText(value.getDose6());
                txtNote.setText(value.getNote());
               // acb.dispose();

            }
        });


        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbNoOFDays.setItems(listDays);

      //  prescriptionData = FXCollections.observableArrayList();

        txtCurrentDate.setValue(DateUtil.NOW_LOCAL_DATE());
         // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.

        tblPatient.setRowFactory(tv -> {
            TableRow <PatientDetails>row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    PatientDetails clickedRow = row.getItem();
                    try {
                        PatientRenderUtil.displayPreviousVisitDetails( getVisitDetails(clickedRow.getId()),tblPreviousVisit,clmnPreviousVisit);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row ;
        });

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

    @FXML
    public void saveNPrintPrescription( ActionEvent event) throws Exception {
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();
        PrescriptionService prescriptionService= new PrescriptionServiceImpl();
        prescriptionService.saveNPrintPrescription(lstMedicineDetails,patientDetails.getId());
        if(PrintUtil.createPrescription()){
            PrintUtil.print();

        }
    }
}