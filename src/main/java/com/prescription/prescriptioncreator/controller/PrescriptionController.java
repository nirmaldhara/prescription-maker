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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
//import java.util.concurrent.TimeUnit;

import static com.prescription.prescriptioncreator.appenum.IntegerValue.*;
import static com.prescription.prescriptioncreator.appenum.IntegerValue.ERROR;
import static com.prescription.prescriptioncreator.appenum.Message.*;

public class PrescriptionController {
    Stage stage = new Stage();

    @FXML
    private  ScrollPane prescriptionPane;
    @FXML
    Label lblPrintStatus;
    List<MedicineDetails> lstMedicineDetails = new ArrayList<>();
    @FXML
    TextField txtId, txtD1, txtD2, txtD3, txtD4, txtD5, txtD6, txtNote, txtD11, txtD21, txtD31, txtD41, txtD51, txtD61;

    @FXML
    private ComboBox<String> cmbWhen, cmbNoOFDays,cmbWhen1, cmbNoOFDays1;
    @FXML
    TableView tblPrescription;
    @FXML
    TableView<PreviousVisit> tblPreviousVisit;
    @FXML
    TableView<PatientDetails> tblPatient;
    @FXML
    TextField txtWeight,txtHeight,txtBP,txtPulse;
    @FXML
    TextField txtMobileNo, txtPatientId, txtMedicineName;
    @FXML
    TableColumn<PreviousVisit, String> clmnPreviousVisit;


    @FXML
    TableColumn<MedicineDetails, String> clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote;
    @FXML
    TableColumn<PatientDetails, String> tblPatientName, tblPatientAge, tblPatientSex, tblPatientAddress, tblPatientMobileNo, tblPatientId;
    MedicineService medicineService = new MedicineServiceImpl();
    @FXML
    private Label welcomeText;
    @FXML
    ButtonBar btnBar;
    @FXML
    VBox mainVBox;

    @FXML
    DatePicker txtVisitDate,txtNextVisitDate;

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
    List<PreviousHistoryDetails> lstPreviousHistoryDetails = new ArrayList<>();
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
    private TableColumn<DiagnosisDetails, String> clmnDiagnosis;
    @FXML
    private TableView<DiagnosisDetails> tblDiagnosis;
    @FXML
    private TextField txtDiagnosis;
    List<DiagnosisDetails> lstDiagnosisDetails = new ArrayList<>();
    DiagnosisService diagnosisService = new DiagnosisServiceImpl();

    @FXML
    private TableColumn<SuggestionsDetails, String> clmnSuggestions;
    @FXML
    private TextField txtSuggestions;
    @FXML
    private TableView<SuggestionsDetails> tblSuggestions;
    List<SuggestionsDetails> lstSuggestionsDetails = new ArrayList<>();
    SuggestionsService suggestionsService = new SuggestionsServiceImpl();


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void addDataToPrescriptionTable() {
        PrescriptionRenderUtil.addToPrescription(lstMedicineDetails, tblPrescription, clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote);

    }

    private void clearAddMedicine( TextField txtId,TextField txtMedicineName,TextField txtD1,TextField txtD2,TextField txtD3, TextField txtD4, TextField txtD5, TextField txtD6, TextField txtNote, ComboBox<String> cmbWhen, ComboBox<String> cmbNoOFDays) {
        FXMLUtil.clearTextBox(txtId, "0");
        FXMLUtil.clearTextBox(txtMedicineName);
        FXMLUtil.clearTextBox(txtD1);
        FXMLUtil.clearTextBox(txtD2);
        FXMLUtil.clearTextBox(txtD3);
        FXMLUtil.clearTextBox(txtD4);
        FXMLUtil.clearTextBox(txtD5);
        FXMLUtil.clearTextBox(txtD6);
        FXMLUtil.clearTextBox(txtNote);
        FXMLUtil.clearComboBox(cmbWhen, "When");
        //FXMLUtil.clearComboBox(cmbNoOFDays);
        //cmbNoOFDays.getSelectionModel().clearSelection();
        //cmbNoOFDays.getPromptText();
        FXMLUtil.clearComboBox(cmbNoOFDays, "Days");
    }

    @FXML
    private void addToPrescription(ActionEvent event) throws Exception {
        if (!(
                ValidationUtil.isTextFieldBlank(txtMedicineName, Message.MEDICINE_NAME_BlANK.val()) ||
                        ValidationUtil.isTextFieldBlank(txtD1, Message.MEDICINE_DOSE_BLANK.val()) ||
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



            System.out.println("Medicine Id : " + txtId.getText());
            long id = 0;
            //id = Long.parseLong(txtId.getText().equals("")? "0":txtId.getText());///
            id = Long.parseLong(txtId.getText());
            if ((id == 0)) {
                MedicineService medicineService = new MedicineServiceImpl();
                id = medicineService.addMedicine(medicineDetails);
            }
            medicineDetails.setMedicineID(id);
            lstMedicineDetails = tblPrescription.getItems();
            lstMedicineDetails.add(medicineDetails);


          //  String medname=txtMedicineName.getText().trim();
            if(!txtD11.getText().equals("") || !txtD21.getText().equals("") || !txtD31.getText().equals("") || !txtD41.getText().equals("")  || !txtD51.getText().equals("") || !txtD61.getText().equals("") ) {
                addDataToPrescriptionTable();
                medicineDetails = new MedicineDetails();
                medicineDetails.setMedicineID(id);
                medicineDetails.setMedicineName(txtMedicineName.getText());
                medicineDetails.setDose1(txtD11.getText());
                medicineDetails.setDose2(txtD21.getText());
                medicineDetails.setDose3(txtD31.getText());
                medicineDetails.setDose4(txtD41.getText());
                medicineDetails.setDose5(txtD51.getText());
                medicineDetails.setDose6(txtD61.getText());
                medicineDetails.setNote(txtNote.getText());
                medicineDetails.setWhen(cmbWhen1.getValue());
                medicineDetails.setNoOfDays(Integer.parseInt(cmbNoOFDays1.getValue() == null ? "0" : cmbNoOFDays1.getValue()));
                lstMedicineDetails = tblPrescription.getItems();
                lstMedicineDetails.add( medicineDetails);
            }
                addDataToPrescriptionTable();

            clearAddMedicine( txtId, txtMedicineName, txtD1, txtD2, txtD3,  txtD4,  txtD5,  txtD6,  txtNote,  cmbWhen,  cmbNoOFDays);
            clearAddMedicine( txtId, txtMedicineName, txtD11, txtD21, txtD31,  txtD41,  txtD51,  txtD61,  txtNote,  cmbWhen1,  cmbNoOFDays1);
        }

    }
private void makeInputInUpper(){
    /// convert input in upper case
    txtMedicineName=FXMLUtil.toUpperCase(txtMedicineName);
    txtComplain=FXMLUtil.toUpperCase(txtComplain);
    txtPHistory=FXMLUtil.toUpperCase(txtPHistory);
    txtFindings=FXMLUtil.toUpperCase(txtFindings);
    txtDiagnosis=FXMLUtil.toUpperCase(txtDiagnosis);
    txtSuggestions=FXMLUtil.toUpperCase(txtSuggestions);

}
    @FXML
    public void initialize() throws Exception {
        makeInputInUpper();
        lblPrintStatus.setVisible(false);
        txtId.setText("0");
        prescriptionPane.setVvalue(1.0);
        FXMLUtil.removeTableRow(tblPrescription);
        FXMLUtil.removeTableRow(tblComplain);
        FXMLUtil.removeTableRow(tblPreviousHistory);
        FXMLUtil.removeTableRow(tblFindings);
        FXMLUtil.removeTableRow(tblDiagnosis);
        FXMLUtil.removeTableRow(tblSuggestions);
        PrescriptionRenderUtil.displayVisitHistoryInPrescriptionTable(
                tblPreviousVisit,
                tblPrescription,
                clmnMedicineName,
                clmnD1,
                clmnD2,
                clmnD3,
                clmnD4,
                clmnD5,
                clmnD6,
                clmnWhen,
                clmnDays,
                clmnNote,
                lstComplainDetails,
                tblComplain,
                clmnComplain,
                lstPreviousHistoryDetails,
                tblPreviousHistory,
                clmnPreviousHistory,
                lstFindingsDetails,
                tblFindings,
                clmnFindings,
                lstDiagnosisDetails,
                tblDiagnosis,
                clmnDiagnosis,
                lstSuggestionsDetails,
                tblSuggestions,
                clmnSuggestions,
                txtWeight,
                txtHeight,
                txtBP,
                txtPulse);
        PrescriptionRenderUtil.displayDataInVisitHistoryTable(tblPatient, tblPreviousVisit, clmnPreviousVisit, txtWeight,txtHeight,txtBP,txtPulse);
        PrescriptionRenderUtil.setMedicineSearchAutoComplete(medicineService, txtMedicineName, txtId, txtD1, txtD2, txtD3, txtD4, txtD5, txtD6, txtNote,cmbWhen,cmbNoOFDays);

        /*
         * @description store and display auto complete Previous History
         * @developer Sukhendu
         */
        ComplainRenderUtil.setComplainDetailsSearchAutoComplete(complainService, txtComplain);
        txtComplain.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    try {
                        ComplainDetails complainDetails = new ComplainDetails();
                        ComplainService complainService = new ComplainServiceImpl();
                        long id = complainService.addComplain(txtComplain.getText());
                        complainDetails.setComplain(txtComplain.getText());
                        complainDetails.setId(id);
                        lstComplainDetails = tblComplain.getItems();
                        lstComplainDetails.add(complainDetails);

                        System.out.println(complainDetails.getComplain() + " id= " + id);
                        ComplainRenderUtil.addToComplain(lstComplainDetails, tblComplain, clmnComplain);
                        FXMLUtil.clearTextBox(txtComplain);

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

        PreviousHistoryRenderUtil.setPreviousHistoryDetailsSearchAutoComplete(previousHistoryService, txtPHistory);
        txtPHistory.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    try {
                        PreviousHistoryDetails previousHistoryDetails = new PreviousHistoryDetails();
                        PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();
                        long id = previousHistoryService.addPreviousHistory(txtPHistory.getText());
                        previousHistoryDetails.setPrevious_history(txtPHistory.getText());
                        previousHistoryDetails.setId(id);
                        lstPreviousHistoryDetails = tblPreviousHistory.getItems();
                        lstPreviousHistoryDetails.add(0, previousHistoryDetails);

                        System.out.println(previousHistoryDetails.getPrevious_history() + " id = " + id);
                        PreviousHistoryRenderUtil.addToPreviousHistory(lstPreviousHistoryDetails, tblPreviousHistory, clmnPreviousHistory);
                        FXMLUtil.clearTextBox(txtPHistory);
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

        FindingsRenderUtil.setFindingsSearchAutoComplete(findingsService, txtFindings);
        txtFindings.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    try {
                        FindingsService findingsService = new FindingsServiceImpl();
                        FindingsDetails findingsDetails = new FindingsDetails();
                        long id = findingsService.addFindings(txtFindings.getText());
                        findingsDetails.setFindings(txtFindings.getText());
                        findingsDetails.setId(id);
                        lstFindingsDetails = tblFindings.getItems();
                        lstFindingsDetails.add(findingsDetails);

                        System.out.println(findingsDetails.getFindings() + " id = " + id);
                        FindingsRenderUtil.addToFindings(lstFindingsDetails, tblFindings, clmnFindings);
                        FXMLUtil.clearTextBox(txtFindings);
                    } catch (Exception ex) {
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

        DiagnosisRenderUtil.setDiagnosisSearchAutoComplete(diagnosisService, txtDiagnosis);
        txtDiagnosis.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    try {
                        DiagnosisService diagnosisService = new DiagnosisServiceImpl();
                        DiagnosisDetails diagnosisDetails = new DiagnosisDetails();
                        long id = diagnosisService.addDiagnosis(txtDiagnosis.getText());
                        diagnosisDetails.setDiagnosis(txtDiagnosis.getText());
                        diagnosisDetails.setId(id);
                        lstDiagnosisDetails = tblDiagnosis.getItems();
                        lstDiagnosisDetails.add(diagnosisDetails);

                        System.out.println(diagnosisDetails.getDiagnosis() + " id = " + id);
                        DiagnosisRenderUtil.addToDiagnosis(lstDiagnosisDetails, tblDiagnosis, clmnDiagnosis);
                        FXMLUtil.clearTextBox(txtDiagnosis);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

        SuggestionsRenderUtil.setSuggestionsSearchAutoComplete(suggestionsService, txtSuggestions);
        txtSuggestions.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    try {
                        SuggestionsService suggestionsService = new SuggestionsServiceImpl();
                        SuggestionsDetails suggestionsDetails = new SuggestionsDetails();
                        long id = suggestionsService.addSuggestions(txtSuggestions.getText());
                        suggestionsDetails.setSuggestions(txtSuggestions.getText());
                        suggestionsDetails.setId(id);
                        lstSuggestionsDetails = tblSuggestions.getItems();
                        lstSuggestionsDetails.add(suggestionsDetails);

                        System.out.println(suggestionsDetails.getSuggestions() + " id = " + id);
                        SuggestionsRenderUtil.addToSuggestions(lstSuggestionsDetails, tblSuggestions, clmnSuggestions);
                        FXMLUtil.clearTextBox(txtSuggestions);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        });

        ArrayList<String> days = new ArrayList<>();
        for (int i = 1; i <= 365; i++)
            days.add("" + i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbNoOFDays.setItems(listDays);
        cmbNoOFDays1.setItems(listDays);

        //  prescriptionData = FXCollections.observableArrayList();

        txtNextVisitDate.setValue(DateUtil.NEXT_MONTH_DATE());
        txtVisitDate.setValue(DateUtil.NOW_LOCAL_DATE());
        // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.

        //Calling handleSearchKeyPress method
        txtMobileNo.setOnKeyPressed(this::handleSearchKeyPress);
        txtPatientId.setOnKeyPressed(this::handleSearchKeyPress);

    }


    @FXML
    public void openAddPatient(ActionEvent event) {

        FXMLUtil.openAddpatientWindow("/fxml/addpatient-view.fxml", 540, 220, "Add Patient");
    }

    @FXML
    public void searchPatientDetails(ActionEvent event) throws Exception {
        PatientService patientService = new PatientServiceImpl();
        String mobileNo = txtMobileNo.getText();
        int patientId = Integer.parseInt(txtPatientId.getText().equals("")? "0": txtPatientId.getText());
        if (!ValidationUtil.isTextFieldBlank(txtMobileNo, MOBILE_OR_PATIENT_ID_BLANK.val()) || !ValidationUtil.isTextFieldBlank(txtPatientId, MOBILE_OR_PATIENT_ID_BLANK.val())) {
            ValidationUtil.removeValidation(txtMobileNo);
            ValidationUtil.removeValidation(txtPatientId);
            List<PatientDetails> lstPatient = patientService.searchPatientDetails(mobileNo, patientId);

            PatientRenderUtil.displayPatientDetails(lstPatient, tblPatient, tblPatientName, tblPatientAge, tblPatientSex, tblPatientAddress, tblPatientMobileNo, tblPatientId);
           if(lstPatient==null || lstPatient.size()==0)
                ToastUtil.makeText(stage, PATIENT_NOT_FOUND.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());
            System.out.println("Search" + mobileNo);
        }

    }
    // Searching patient details by using enter key
    private void handleSearchKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                searchPatientDetails(new ActionEvent()); // Trigger search on Enter key press
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
            }
        }
    }
    @FXML
    public void openAddMedicine(ActionEvent event) throws IOException {

        FXMLUtil.openAddMedicineWindow("/fxml/addmedicine-view.fxml", 540, 220, "Add Medicine");
    }

    @FXML
    public void openAddTestReport(ActionEvent event) {

        FXMLUtil.openAddTestReportWindow("/fxml/testreport-view.fxml", 540, 220, "Add Test Report");
    }


    private boolean save() throws Exception {
        PrescriptionService prescriptionService = new PrescriptionServiceImpl();
        ComplainService cs = new ComplainServiceImpl();
        PreviousHistoryService phs = new PreviousHistoryServiceImpl();
        FindingsService fd = new FindingsServiceImpl();
        DiagnosisService ds = new DiagnosisServiceImpl();
        SuggestionsService ss = new SuggestionsServiceImpl();
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();
        lstMedicineDetails = tblPrescription.getItems();
        patientDetails.setWeight(Float.parseFloat(txtWeight.getText().equals("")?"0.0":txtWeight.getText()));
        patientDetails.setHeight(Float.parseFloat(txtHeight.getText().equals("")?"0.0":txtHeight.getText()));
        patientDetails.setBp(txtBP.getText().equals("")?"0/0":txtBP.getText());
        patientDetails.setPulse(Float.parseFloat(txtPulse.getText().equals("")?"0.0":txtPulse.getText()));

        long visit_id = prescriptionService.saveNPrintPrescription(lstMedicineDetails, patientDetails.getId());

        lstComplainDetails = tblComplain.getItems();
        cs.saveComplainToPrescription(lstComplainDetails, visit_id);

        lstPreviousHistoryDetails = tblPreviousHistory.getItems();
        phs.savePreviousHistoryToPrescription(lstPreviousHistoryDetails, visit_id);

        lstFindingsDetails = tblFindings.getItems();
        fd.saveFindingsToPrescription(lstFindingsDetails, visit_id);

        lstDiagnosisDetails = tblDiagnosis.getItems();
        ds.saveDiagnosisToPrescription(lstDiagnosisDetails, visit_id);

        lstSuggestionsDetails = tblSuggestions.getItems();
        ss.saveSuggestionsToPrescription(lstSuggestionsDetails, visit_id);

        prescriptionService.saveVisitHistory(patientDetails.getId(),visit_id, Date.valueOf(txtVisitDate.getValue()),Date.valueOf(txtNextVisitDate.getValue()),patientDetails.getWeight(), patientDetails.getHeight(), patientDetails.getBp(),patientDetails.getPulse());
        return true;
    }

    private boolean print() throws IOException, InterruptedException {
        lblPrintStatus.setVisible(true);
        lblPrintStatus.setText("Printing....");
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();

        if (patientDetails == null) {
            ToastUtil.makeText(stage, PRINT_ERROR.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());
        }
        patientDetails.setWeight(Float.parseFloat(txtWeight.getText().equals("")?"0.0":txtWeight.getText()));
        patientDetails.setHeight(Float.parseFloat(txtHeight.getText().equals("")?"0.0":txtHeight.getText()));
        patientDetails.setBp(txtBP.getText().equals("")?"0/0":txtBP.getText());
        patientDetails.setPulse(Float.parseFloat(txtPulse.getText().equals("")?"0.0":txtPulse.getText()));
        lstMedicineDetails = tblPrescription.getItems();
        lstComplainDetails=tblComplain.getItems();
        lstPreviousHistoryDetails=tblPreviousHistory.getItems();
        lstFindingsDetails=tblFindings.getItems();
        lstDiagnosisDetails=tblDiagnosis.getItems();
        lstSuggestionsDetails=tblSuggestions.getItems();

        PrintUtil printUtil = new PrintUtil();
        Collections.sort(lstMedicineDetails, new Comparator<MedicineDetails>() {
            public int compare(MedicineDetails m1, MedicineDetails m2) {
                // notice the cast to (Integer) to invoke compareTo
                return (m1.getMedicineName()).compareTo(m2.getMedicineName());
            }
        });

        if (printUtil.createPrescription(txtVisitDate.getValue().toString(),txtNextVisitDate.getValue().toString(),patientDetails, lstMedicineDetails, lstComplainDetails, lstPreviousHistoryDetails, lstFindingsDetails, lstDiagnosisDetails)) {
            PrintUtil.print();
            lblPrintStatus.setText("Done");
           // TimeUnit.SECONDS.sleep(4);
            lblPrintStatus.setText("");
        }
        return true;
    }
    @FXML
    public void printPrescription(ActionEvent event) throws Exception {
        print();
    }
    @FXML
    public void saveNPrintPrescription(ActionEvent event) throws Exception {
       save();
       print();
    }

}