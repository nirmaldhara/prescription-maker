package com.prescription.prescriptioncreator.controller;
import com.prescription.prescriptioncreator.appenum.Message;
import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.service.*;
import com.prescription.prescriptioncreator.service.impl.*;
import com.prescription.prescriptioncreator.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.prescription.prescriptioncreator.appenum.IntegerValue.*;
import static com.prescription.prescriptioncreator.appenum.IntegerValue.ERROR;
import static com.prescription.prescriptioncreator.appenum.Message.*;
import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;
public class PrescriptionController {
    Stage stage = new Stage();
    @FXML
    Label lblPrintStatus;
    List<MedicineDetails> lstMedicineDetails= new ArrayList<>();
    @FXML
    TextField  txtId,txtD1,txtD2,txtD3,txtD4,txtD5,txtD6,txtNote;
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
    TableColumn<ComplainDetails, String> clmnComplain;
    @FXML
    TableView<ComplainDetails> tblComplain;
    @FXML
    TextField txtComplain;
    ComplainService complainService = new ComplainServiceImpl();
    List<ComplainDetails> lstComplainDetails = new ArrayList<>();
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
private void clearAddMedicine(){
    FXMLUtil.clearTextBox(txtId,"0");
    FXMLUtil.clearTextBox(txtMedicineName);
    FXMLUtil.clearTextBox(txtD1);
    FXMLUtil.clearTextBox(txtD2);
    FXMLUtil.clearTextBox(txtD3);
    FXMLUtil.clearTextBox(txtD4);
    FXMLUtil.clearTextBox(txtD5);
    FXMLUtil.clearTextBox(txtD6);
    FXMLUtil.clearTextBox(txtNote);
    FXMLUtil.clearComboBox(cmbWhen,"");
    //FXMLUtil.clearComboBox(cmbNoOFDays);
    //cmbNoOFDays.getSelectionModel().clearSelection();
    //cmbNoOFDays.getPromptText();
    FXMLUtil.clearComboBox(cmbNoOFDays,"");
}
    @FXML
    private void addToPrescription( ActionEvent event) throws Exception {
        //////
        if(!(
                ValidationUtil.isTextFieldBlank(txtMedicineName, Message.MEDICINE_NAME_BlANK.val()) ||
                        ValidationUtil.isComboBoxBlank(cmbWhen, Message.MEDICINE_TIME_BlANK.val()) ||
                        ValidationUtil.isComboBoxBlank(cmbNoOFDays, Message.MEDICINE_DAYS_BLANK.val()))
        ) {
            MedicineDetails medicineDetails = new MedicineDetails();
            medicineDetails.setMedicineName(txtMedicineName.getText());
            medicineDetails.setDose1(txtD1.getText());
            medicineDetails.setDose2(txtD2.getText());
            medicineDetails.setDose3(txtD3.getText());
            medicineDetails.setDose4(txtD4.getText());
            medicineDetails.setDose5(txtD5.getText());
            medicineDetails.setDose6(txtD6.getText());
            medicineDetails.setNote(txtNote.getText());
            medicineDetails.setWhen(cmbWhen.getValue());
            medicineDetails.setNoOfDays(Integer.parseInt(cmbNoOFDays.getValue() == null ? "0" : cmbNoOFDays.getValue()));
            lstMedicineDetails = tblPrescription.getItems();
            lstMedicineDetails.add(0, medicineDetails);
            addDataToPrescriptionTable();

            System.out.println("Medicine Id : " + txtId.getText());
            long id = 0;
            //id = Long.parseLong(txtId.getText().equals("")? "0":txtId.getText());///
            id=Long.parseLong(txtId.getText());
            if ((id == 0)) {
                MedicineService medicineService = new MedicineServiceImpl();
                medicineService.addMedicine(medicineDetails);
            }
            clearAddMedicine();
        }

    }
    @FXML
    public void initialize() throws Exception {
        lblPrintStatus.setVisible(false);
        txtId.setText("0");
        PrescriptionRenderUtil.removePrescriptionRow(tblPrescription);
        PrescriptionRenderUtil.displayVisitHistoryInPrescriptionTable(tblPreviousVisit, tblPrescription, clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote);
        PrescriptionRenderUtil.displayDataInVisitHistoryTable(tblPatient,tblPreviousVisit,clmnPreviousVisit);
        PrescriptionRenderUtil.setMedicineSearchAutoComplete( medicineService,  txtMedicineName,txtId, txtD1,  txtD2,  txtD3, txtD4, txtD5, txtD6, txtNote);

        /*
         * @description store and display auto complete Previous History
         * @developer Sukhendu
         */
        ComplainRenderUtil.setComplainDetailsSearchAutoComplete(complainService,txtComplain);
        txtComplain.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    try{
                        ComplainDetails complainDetails = new ComplainDetails();
                        ComplainService complainService = new ComplainServiceImpl();
                        complainDetails.setComplain(txtComplain.getText());
                        lstComplainDetails = tblComplain.getItems();
                        lstComplainDetails.add(0,complainDetails);
                        ComplainRenderUtil.addToComplain(lstComplainDetails,tblComplain,clmnComplain);
                        FXMLUtil.clearTextBox(txtComplain);
                        complainService.addComplain(complainDetails);
                    }catch(Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

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

                        FXMLUtil.clearTextBox(txtPHistory);
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
                        FXMLUtil.clearTextBox(txtFindings);
                        findingsService.addFindings(findingsDetails);
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
                        FXMLUtil.clearTextBox(txtSuggestions);
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
        if(patientDetails == null){
            ToastUtil.makeText(stage, PRINT_ERROR.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());
        }
        prescriptionService.saveNPrintPrescription(lstMedicineDetails,patientDetails.getId());

        P_Previous_HistoryService pPreviousHistoryService = new P_Previous_HistoryServiceImpl();//
        int previousHistoryId = 0;
        int visitId = 0;//
        pPreviousHistoryService.saveP_Previous_HistoryDao(lstPreviousHistoryDetails,previousHistoryId);//

        P_FindingsService pFindingsService = new P_FindingsServiceImpl();//
        int findingsId = 0;
        int visitidP = 0;
        pFindingsService.saveP_Findings(lstFindingsDetails,findingsId);

        P_SuggestionsService pSuggestionsService = new P_SuggestionsServiceImpl();
        int suggestionsId = 0;
        int visitIdPs = 0;
        pSuggestionsService.saveP_Suggestions(lstSuggestionsDetails,suggestionsId);

        P_ComplainService pComplainService = new P_ComplainServiceImpl();
        int complainId = 0;
        int visitIdC = 0;
        pComplainService.saveP_ComplainDao(lstComplainDetails,complainId);

        PrintUtil printUtil =new PrintUtil();
        if(printUtil.createPrescription(patientDetails,lstMedicineDetails,lstComplainDetails,lstPreviousHistoryDetails,lstFindingsDetails,lstSuggestionsDetails)){
            PrintUtil.print();
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("Done");
            TimeUnit.SECONDS.sleep(5);
            lblPrintStatus.setText("");
        }
    }

}