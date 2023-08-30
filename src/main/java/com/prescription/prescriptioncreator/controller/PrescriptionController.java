package com.prescription.prescriptioncreator.controller;
import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.service.*;
import com.prescription.prescriptioncreator.service.impl.*;
import com.prescription.prescriptioncreator.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
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

    ////
    @FXML
    TextField txtPHistory;
    List<PreviousHistoryDetails> lstPreviousHistoryDetails= new ArrayList<>();
    @FXML
    TableView<PreviousHistoryDetails> tblPreviousHistory;
    @FXML
    TableColumn<PreviousHistoryDetails, String> clmnPreviousHistory;
    PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();

    @FXML
    TableColumn<FindingsDetails, String> clmnFindings;
    @FXML
    TableView<FindingsDetails> tblFindings;
    @FXML
    TextField txtFindings;
    List<FindingsDetails> lstFindingsDetails = new ArrayList<>();
    FindingsService findingsService = new FindingsServiceImpl();

    @FXML
    private TableColumn<SuggestionsDetails, String> clmnSuggestions;
    @FXML
    private TableView<SuggestionsDetails> tblSuggestions;
    @FXML
    private TextField txtSuggestions;
    List<SuggestionsDetails> lstSuggestionsDetails = new ArrayList<>();
    SuggestionsService suggestionsService = new SuggestionsServiceImpl();


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

        /*
         * @description store and display auto complete Previous History
         * @developer Sukhendu
         */
        PreviousHistoryRenderUtil.setPreviousHistoryDetailsSearchAutoComplete(previousHistoryService,txtPHistory);
        txtPHistory.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try {
                        PreviousHistoryDetails previousHistoryDetails= new PreviousHistoryDetails();
                        PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();
                        previousHistoryDetails.setPrevious_history(txtPHistory.getText());
                        lstPreviousHistoryDetails=tblPreviousHistory.getItems();
                        lstPreviousHistoryDetails.add(0,previousHistoryDetails);
                        PreviousHistoryRenderUtil.addToPreviousHistory(lstPreviousHistoryDetails,tblPreviousHistory,clmnPreviousHistory);

                        previousHistoryDetails.setPrevious_history(txtPHistory.getText());
                        previousHistoryService.addPreviousHistory(previousHistoryDetails);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });
        /*
         * @description store and display auto complete findings
         * @developer Sukhendu
         */

        FindingsRenderUtil.setFindingsSearchAutoComplete(findingsService,txtFindings);
        txtFindings.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try{
                        FindingsService findingsService = new FindingsServiceImpl();
                        FindingsDetails findingsDetails = new FindingsDetails();
                        findingsDetails.setFindings(txtFindings.getText());
                        lstFindingsDetails = tblFindings.getItems();
                        lstFindingsDetails.add(0,findingsDetails);
                        FindingsRenderUtil.addToFindings(lstFindingsDetails,tblFindings,clmnFindings);
                        findingsService.addFindings(findingsDetails);
                        FindingsRenderUtil.addToFindings(lstFindingsDetails,tblFindings,clmnFindings);
                    }catch(Exception ex){
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });
        /*
         * @description store and display auto suggestions
         * @developer Sukhendu
         */

        SuggestionsRenderUtil.setSuggestionsSearchAutoComplete(suggestionsService,txtSuggestions);
        txtSuggestions.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try{
                        SuggestionsService suggestionsService = new SuggestionsServiceImpl();
                        SuggestionsDetails suggestionsDetails = new SuggestionsDetails();
                        suggestionsDetails.setSuggestions(txtSuggestions.getText());
                        lstSuggestionsDetails = tblSuggestions.getItems();
                        lstSuggestionsDetails.add(0,suggestionsDetails);
                        SuggestionsRenderUtil.addToSuggestions(lstSuggestionsDetails,tblSuggestions,clmnSuggestions);
                        suggestionsService.addSuggestions(suggestionsDetails);
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

        txtCurrentDate.setValue(DateUtil.NEXT_MONTH_DATE());
         // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.

    }


    @FXML
    public void openAddPatient( ActionEvent event){

        FXMLUtil.openAddpatientWindow("/fxml/addpatient-view.fxml",540,220,"Add Patient");
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
    public void openAddMedicine( ActionEvent event) throws IOException {

        FXMLUtil.openAddMedicineWindow("/fxml/addmedicine-view.fxml",540,220,"Add Medicine");
    }

    @FXML
    public void saveNPrintPrescription( ActionEvent event) throws Exception {
        lblPrintStatus.setVisible(true);
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();
        PrescriptionService prescriptionService= new PrescriptionServiceImpl();
        prescriptionService.saveNPrintPrescription(lstMedicineDetails,patientDetails.getId());
        P_Previous_HistoryService pPreviousHistoryService = new P_Previous_HistoryServiceImpl();//
        int previousHistoryId = 0;//
        int visitId = 0;//
        pPreviousHistoryService.saveP_Previous_HistoryDao(previousHistoryId,visitId);//

        P_FindingsService pFindingsService = new P_FindingsServiceImpl();//
        int findingsId = 0;
        int visitidP = 0;
        pFindingsService.saveP_Findings(findingsId,visitidP);

        P_SuggestionsService pSuggestionsService = new P_SuggestionsServiceImpl();
        int suggestionsId = 0;
        int visitIdPs = 0;
        pSuggestionsService.saveP_Suggestions(suggestionsId,visitIdPs);

        PrintUtil printUtil =new PrintUtil();
        if(printUtil.createPrescription()){
            PrintUtil.print();
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("Done");
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("");
        }
    }

}