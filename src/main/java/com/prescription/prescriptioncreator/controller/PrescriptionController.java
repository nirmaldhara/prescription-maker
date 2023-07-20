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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.appenum.Message.MOBILE_OR_PATIENT_ID_BLANK;

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


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


public void addDataToPrescriptionTable(){
    PrescriptionRenderUtil.addToPrescription(lstMedicineDetails, tblPrescription, clmnMedicineName,clmnD1,clmnD2,clmnD3,clmnD4,clmnD5,clmnD6,clmnWhen,clmnDays,clmnNote);

}
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
        medicineDetails.setNoOfDays(Integer.parseInt(cmbNoOFDays.getValue()==null? "0":cmbNoOFDays.getValue()));
        lstMedicineDetails=tblPrescription.getItems();
        lstMedicineDetails.add(0,medicineDetails);
        addDataToPrescriptionTable();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added!");
        alert.showAndWait();

    }


    @FXML
    public void initialize() throws Exception {

        PrescriptionRenderUtil.removePatientRow(tblPatient);
        PrescriptionRenderUtil.removePrescriptionRow(tblPrescription);
        PrescriptionRenderUtil.displayVisitHistoryInPrescriptionTable(tblPreviousVisit, tblPrescription, clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote);
        PrescriptionRenderUtil.displayDataInVisitHistoryTable(tblPatient,tblPreviousVisit,clmnPreviousVisit);
        PrescriptionRenderUtil.setMedicineSearchAutoComplete( medicineService,  txtMedicineName, txtD1,  txtD2,  txtD3, txtD4, txtD5, txtD6, txtNote);

        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbNoOFDays.setItems(listDays);

      //  prescriptionData = FXCollections.observableArrayList();

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
        if((!ValidationUtil.isTextFieldBlank(txtMobileNo, MOBILE_OR_PATIENT_ID_BLANK.val())) || (!ValidationUtil.isTextFieldBlank(txtPatientId,MOBILE_OR_PATIENT_ID_BLANK.val()))) {
            List<PatientDetails> lstPatient = patientService.searchPatientDetails(mobileNo, patientId);
            PatientRenderUtil.displayPatientDetails(lstPatient, tblPatient, tblPatientName, tblPatientAge, tblPatientSex, tblPatientAddress, tblPatientMobileNo, tblPatientId);
            System.out.println("Search" + mobileNo);
        }
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