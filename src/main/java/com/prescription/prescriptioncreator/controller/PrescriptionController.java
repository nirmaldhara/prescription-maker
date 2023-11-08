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
        //////
        if (!(
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
            lstMedicineDetails.add(0, medicineDetails);


            String medname=txtMedicineName.getText().trim();
            if(!medname.equals("")) {
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
                lstMedicineDetails.add(1, medicineDetails);
                addDataToPrescriptionTable();
            }
            clearAddMedicine( txtId, txtMedicineName, txtD1, txtD2, txtD3,  txtD4,  txtD5,  txtD6,  txtNote,  cmbWhen,  cmbNoOFDays);
            clearAddMedicine( txtId, txtMedicineName, txtD11, txtD21, txtD31,  txtD41,  txtD51,  txtD61,  txtNote,  cmbWhen1,  cmbNoOFDays1);
        }

    }

    @FXML
    public void initialize() throws Exception {
        lblPrintStatus.setVisible(false);
        txtId.setText("0");
        PrescriptionRenderUtil.removePrescriptionRow(tblPrescription);
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
                lstSuggestionsDetails,
                tblSuggestions,
                clmnSuggestions);
        PrescriptionRenderUtil.displayDataInVisitHistoryTable(tblPatient, tblPreviousVisit, clmnPreviousVisit);
        PrescriptionRenderUtil.setMedicineSearchAutoComplete(medicineService, txtMedicineName, txtId, txtD1, txtD2, txtD3, txtD4, txtD5, txtD6, txtNote);

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

        txtCurrentDate.setValue(DateUtil.NEXT_MONTH_DATE());
        // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.

    }


    @FXML
    public void openAddPatient(ActionEvent event) {

        FXMLUtil.openAddpatientWindow("/fxml/addpatient-view.fxml", 540, 220, "Add Patient");
    }

    @FXML
    public void searchPatientDetails(ActionEvent event) throws Exception {
        PatientService patientService = new PatientServiceImpl();
        String mobileNo = txtMobileNo.getText();
        String patientId = txtPatientId.getText();
        if ((!ValidationUtil.isTextFieldBlank(txtMobileNo, MOBILE_OR_PATIENT_ID_BLANK.val())) || (!ValidationUtil.isTextFieldBlank(txtPatientId, MOBILE_OR_PATIENT_ID_BLANK.val()))) {
            List<PatientDetails> lstPatient = patientService.searchPatientDetails(mobileNo, patientId);
            PatientRenderUtil.displayPatientDetails(lstPatient, tblPatient, tblPatientName, tblPatientAge, tblPatientSex, tblPatientAddress, tblPatientMobileNo, tblPatientId);
            System.out.println("Search" + mobileNo);
        }
    }

    @FXML
    public void openAddMedicine(ActionEvent event) throws IOException {

        FXMLUtil.openAddMedicineWindow("/fxml/addmedicine-view.fxml", 540, 220, "Add Medicine");
    }

    @FXML
    public void saveNPrintPrescription(ActionEvent event) throws Exception {
        lblPrintStatus.setVisible(true);
        lblPrintStatus.setText("Printing....");
        PatientDetails patientDetails = tblPatient.getSelectionModel().getSelectedItem();
        PrescriptionService prescriptionService = new PrescriptionServiceImpl();

        ComplainService cs = new ComplainServiceImpl();
        PreviousHistoryService phs = new PreviousHistoryServiceImpl();
        FindingsService fd = new FindingsServiceImpl();
        SuggestionsService sd = new SuggestionsServiceImpl();

        if (patientDetails == null) {
            ToastUtil.makeText(stage, PRINT_ERROR.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());
        }
        lstMedicineDetails = tblPrescription.getItems();
        long visit_id = prescriptionService.saveNPrintPrescription(lstMedicineDetails, patientDetails.getId());

        lstComplainDetails = tblComplain.getItems();
        cs.saveComplainToPrescription(lstComplainDetails, visit_id);

        lstPreviousHistoryDetails = tblPreviousHistory.getItems();
        phs.savePreviousHistoryToPrescription(lstPreviousHistoryDetails, visit_id);

        lstFindingsDetails = tblFindings.getItems();
        fd.saveFindingsToPrescription(lstFindingsDetails, visit_id);

        lstSuggestionsDetails = tblSuggestions.getItems();
        sd.saveSuggestionsToPrescription(lstSuggestionsDetails, visit_id);

        PrintUtil printUtil = new PrintUtil();
        if (printUtil.createPrescription(patientDetails, lstMedicineDetails, lstComplainDetails, lstPreviousHistoryDetails, lstFindingsDetails, lstSuggestionsDetails,txtCurrentDate)) {
            PrintUtil.print();
            TimeUnit.SECONDS.sleep(2);
            lblPrintStatus.setText("Done");
            TimeUnit.SECONDS.sleep(2);
            lblPrintStatus.setText("");
        }
    }

}