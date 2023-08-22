package com.prescription.prescriptioncreator.controller;
import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.service.*;
import com.prescription.prescriptioncreator.service.impl.*;
import com.prescription.prescriptioncreator.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.prescription.prescriptioncreator.appenum.Message.MOBILE_OR_PATIENT_ID_BLANK;
import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PrescriptionController {
    @FXML
    Label lblPrintStatus;
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

    }


    @FXML
    public void initialize() throws Exception {
        lblPrintStatus.setVisible(false);
        PrescriptionRenderUtil.removePrescriptionRow(tblPrescription);
        PrescriptionRenderUtil.displayVisitHistoryInPrescriptionTable(tblPreviousVisit, tblPrescription, clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote);
        PrescriptionRenderUtil.displayDataInVisitHistoryTable(tblPatient,tblPreviousVisit,clmnPreviousVisit);
        PrescriptionRenderUtil.setMedicineSearchAutoComplete( medicineService,  txtMedicineName, txtD1,  txtD2,  txtD3, txtD4, txtD5, txtD6, txtNote);

        /* @method  PreviousHistoryRenderUtil.setPreviousHistoryDetailsSearchAutoComplete
         * @param previousHistoryService,txtPHistory
         * @throws Exception
         * @description store and display auto complete Previous History
         * @developer Sukhendu
         */
        PreviousHistoryRenderUtil.setPreviousHistoryDetailsSearchAutoComplete(previousHistoryService,txtPHistory);
        txtPHistory.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try {
                        addPreviousHistory(txtPHistory.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

        FindingsRenderUtil.setFindingsSearchAutoComplete(findingsService,txtFindings);
        txtFindings.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try{
                        addFindings(txtFindings.getText());
                    }catch(Exception ex){
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

        SuggestionsRenderUtil.setSuggestionsSearchAutoComplete(suggestionsService,txtSuggestions);
        txtSuggestions.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try{
                        addSuggestions(txtSuggestions.getText());
                    }catch(Exception ex){
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
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
        lblPrintStatus.setVisible(true);
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();
        PrescriptionService prescriptionService= new PrescriptionServiceImpl();
        prescriptionService.saveNPrintPrescription(lstMedicineDetails,patientDetails.getId());
        PrintUtil printUtil =new PrintUtil();
        if(printUtil.createPrescription()){
            PrintUtil.print();
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("Done");
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("");
        }
    }

    ////
    /* @method addPreviousHistory
     * @param text
     * @throws Exception
     * @description store and display auto complete Previous History
     * @developer Sukhendu
     */
    @FXML
    TextField txtPHistory;
    List<PreviousHistoryDetails> lstPreviousHistoryDetails= new ArrayList<>();
    @FXML
    TableView<PreviousHistoryDetails> tblPreviousHistory;
    @FXML
    TableColumn<PreviousHistoryDetails, String> clmnPreviousHistory;
    PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();
    @FXML
    public void addPreviousHistory(String text) throws Exception{
        PreviousHistoryRenderUtil.addToPreviousHistory(lstPreviousHistoryDetails,tblPreviousHistory,clmnPreviousHistory);
        PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();
        PreviousHistoryDetails previousHistoryDetails = new PreviousHistoryDetails();
        previousHistoryDetails.setPrevious_history(txtPHistory.getText());
        previousHistoryService.addPreviousHistory(previousHistoryDetails);
        String historyDetails = txtPHistory.getText();
        List<PreviousHistoryDetails> lstPreviousHistoryDetails = previousHistoryService.addPreviousHistory(historyDetails);
        PreviousHistoryRenderUtil.addToPreviousHistory(lstPreviousHistoryDetails,tblPreviousHistory,clmnPreviousHistory);
    }

    @FXML
    TableColumn<FindingsDetails, String> clmnFindings;
    @FXML
    TableView<FindingsDetails> tblFindings;
    @FXML
   TextField txtFindings;
    List<FindingsDetails> lstFindingsDetails = new ArrayList<>();
    FindingsService findingsService = new FindingsServiceImpl();
    @FXML
    public void addFindings(String text) throws Exception{
        FindingsRenderUtil.addToFindings(lstFindingsDetails,tblFindings,clmnFindings);
        FindingsService findingsService = new FindingsServiceImpl();
        FindingsDetails findingsDetails = new FindingsDetails();
        findingsDetails.setFindings(txtFindings.getText());
        findingsService.addFindings(findingsDetails);
        String findings = txtFindings.getText();
        List<FindingsDetails> lstFindingsDetails = findingsService.addFindings(findings);
        FindingsRenderUtil.addToFindings(lstFindingsDetails,tblFindings,clmnFindings);
    }

    @FXML
    private TableColumn<SuggestionsDetails, String> clmnSuggestions;
    @FXML
    private TableView<SuggestionsDetails> tblSuggestions;
    @FXML
    private TextField txtSuggestions;
    List<SuggestionsDetails> lstSuggestionsDetails = new ArrayList<>();
    SuggestionsService suggestionsService = new SuggestionsServiceImpl();

    @FXML
    public void addSuggestions(String text) throws Exception{
        SuggestionsService suggestionsService = new SuggestionsServiceImpl();
        SuggestionsDetails suggestionsDetails = new SuggestionsDetails();
        suggestionsDetails.setSuggestions(txtSuggestions.getText());
        suggestionsService.addSuggestions(suggestionsDetails);
        String suggestions = txtSuggestions.getText();
        List<SuggestionsDetails> lstSuggestionsDetails = suggestionsService.addSuggestions(suggestions);
        SuggestionsRenderUtil.addToSuggestions(lstSuggestionsDetails,tblSuggestions,clmnSuggestions);
    }
}