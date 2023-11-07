package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.service.*;
import com.prescription.prescriptioncreator.service.impl.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.List;

public class PrescriptionRenderUtil {
    public static void addToPrescription(List<MedicineDetails> lstMedicineDetails,
                                         TableView tblPrescription,
                                         TableColumn<MedicineDetails, String> clmnMedicineName,
                                         TableColumn<MedicineDetails, String> clmnD1,
                                         TableColumn<MedicineDetails, String> clmnD2,
                                         TableColumn<MedicineDetails, String> clmnD3,
                                         TableColumn<MedicineDetails, String> clmnD4,
                                         TableColumn<MedicineDetails, String> clmnD5,
                                         TableColumn<MedicineDetails, String> clmnD6,
                                         TableColumn<MedicineDetails, String> clmnWhen,
                                         TableColumn<MedicineDetails, String> clmnDays,
                                         TableColumn<MedicineDetails, String> clmnNote) {


        ObservableList<MedicineDetails> data = FXCollections.observableArrayList(lstMedicineDetails);


        clmnMedicineName.setCellValueFactory(new PropertyValueFactory("medicineName"));
        clmnD1.setCellValueFactory(new PropertyValueFactory("Dose1"));
        clmnD2.setCellValueFactory(new PropertyValueFactory("Dose2"));
        clmnD3.setCellValueFactory(new PropertyValueFactory("Dose3"));
        clmnD4.setCellValueFactory(new PropertyValueFactory("Dose4"));
        clmnD5.setCellValueFactory(new PropertyValueFactory("Dose5"));
        clmnD6.setCellValueFactory(new PropertyValueFactory("Dose6"));
        clmnWhen.setCellValueFactory(new PropertyValueFactory("when"));
        clmnDays.setCellValueFactory(new PropertyValueFactory("noOfDays"));
        clmnNote.setCellValueFactory(new PropertyValueFactory("note"));
        System.out.println("addToPrescription :---------called");
        tblPrescription.setItems(data);


    }

    public static List<PreviousVisit> getVisitDetails(int patientID) throws Exception {
        PrescriptionService ps = new PrescriptionServiceImpl();
        return ps.getVisitDetails(patientID);

    }


    public static List<ComplainDetails> getComplainOFDetails(int visitId) throws Exception {
        ComplainService cs = new ComplainServiceImpl();
        return cs.getComplainOFDetails(visitId);

    }

    public static List<PreviousHistoryDetails> getPreviousHistoryOFDetails(int visitId) throws Exception {
        PreviousHistoryService phs = new PreviousHistoryServiceImpl();
        return phs.getPreviousHistoryOFDetails(visitId);

    }

    public static List<FindingsDetails> getFindingsOFDetails(long visitId) throws Exception {
        FindingsService fd = new FindingsServiceImpl();
        return fd.getFindingsOFDetails(visitId);
    }

    public static List<SuggestionsDetails> getSuggestionsOFDetails(long visitId) throws Exception {
        SuggestionsService sd = new SuggestionsServiceImpl();
        return sd.getSuggestionsOFDetails(visitId);
    }

    public static List<MedicineDetails> getPrescriptionDetailsByVisitId(int visitId) throws Exception {
        PrescriptionService ps = new PrescriptionServiceImpl();
        return ps.getPrescriptionDetailsByVisitId(visitId);

    }

    public static void setMedicineSearchAutoComplete(MedicineService medicineService, TextField txtMedicineName, TextField txtId, TextField txtD1, TextField txtD2, TextField txtD3, TextField txtD4, TextField txtD5, TextField txtD6, TextField txtNote) throws Exception {
        ObservableList<MedicineDetails> autoCompleteData;
        autoCompleteData = FXCollections.observableArrayList(medicineService.getAutoSuggestMedicine());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtMedicineName, autoCompleteData);
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<MedicineDetails>>() {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<MedicineDetails> event) {
                MedicineDetails value = event.getCompletion();
                txtId.setText("" + value.getId());
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

    }

    public static void displayVisitHistoryInPrescriptionTable(
            TableView<PreviousVisit> tblPreviousVisit,
            TableView tblPrescription,
            TableColumn<MedicineDetails, String> clmnMedicineName,
            TableColumn<MedicineDetails, String> clmnD1,
            TableColumn<MedicineDetails, String> clmnD2,
            TableColumn<MedicineDetails, String> clmnD3,
            TableColumn<MedicineDetails, String> clmnD4,
            TableColumn<MedicineDetails, String> clmnD5,
            TableColumn<MedicineDetails, String> clmnD6,
            TableColumn<MedicineDetails, String> clmnWhen,
            TableColumn<MedicineDetails, String> clmnDays,
            TableColumn<MedicineDetails, String> clmnNote,
            List<ComplainDetails> lstComplainDetails,
            TableView tblComplain,
            TableColumn<ComplainDetails, String> clmnComplain,
            List<PreviousHistoryDetails> lstPreviousHistoryDetails,
            TableView tblPreviousHistory,
            TableColumn<PreviousHistoryDetails, String> clmnPreviousHistory,
            List<FindingsDetails> lstFindingsDetails,
            TableView tblFindings,
            TableColumn<FindingsDetails, String> clmnFindings,
            List<SuggestionsDetails> lstSuggestionsDetails,
            TableView tblSuggestions,
            TableColumn<SuggestionsDetails, String> clmnSuggestions

    ) {
        System.out.println("load history data");
        tblPreviousVisit.setRowFactory(tv -> {
            TableRow<PreviousVisit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    PreviousVisit clickedRow = row.getItem();
                    try {
                        addToPrescription(getPrescriptionDetailsByVisitId(clickedRow.getVisitId()), tblPrescription, clmnMedicineName, clmnD1, clmnD2, clmnD3, clmnD4, clmnD5, clmnD6, clmnWhen, clmnDays, clmnNote);
                        ComplainRenderUtil.addToComplain(getComplainOFDetails(clickedRow.getVisitId()), tblComplain, clmnComplain);
                        PreviousHistoryRenderUtil.addToPreviousHistory(getPreviousHistoryOFDetails(clickedRow.getVisitId()), tblPreviousHistory, clmnPreviousHistory);
                        FindingsRenderUtil.addToFindings(getFindingsOFDetails(clickedRow.getVisitId()), tblFindings, clmnFindings);
                        SuggestionsRenderUtil.addToSuggestions(getSuggestionsOFDetails(clickedRow.getVisitId()), tblSuggestions, clmnSuggestions);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    public static void displayDataInVisitHistoryTable(TableView<PatientDetails> tblPatient, TableView<PreviousVisit> tblPreviousVisit, TableColumn<PreviousVisit, String> clmnPreviousVisit) {
        tblPatient.setRowFactory(tv -> {
            TableRow<PatientDetails> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    PatientDetails clickedRow = row.getItem();
                    try {
                        System.out.println("displayDataInVisitHistoryTable :: --");
                        PatientRenderUtil.displayPreviousVisitDetails(getVisitDetails(clickedRow.getId()), tblPreviousVisit, clmnPreviousVisit);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    public static void removePrescriptionRow(TableView tblPrescription) {
        tblPrescription.setRowFactory(new Callback<TableView<MedicineDetails>, TableRow<MedicineDetails>>() {
            @Override
            public TableRow<MedicineDetails> call(TableView<MedicineDetails> tableView) {
                final TableRow<MedicineDetails> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tblPrescription.getItems().remove(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );
                return row;
            }
        });
    }

    public static void removePatientRow(TableView tblPatient) {
        tblPatient.setRowFactory(new Callback<TableView<PatientDetails>, TableRow<PatientDetails>>() {
            @Override
            public TableRow<PatientDetails> call(TableView<PatientDetails> tableView) {
                final TableRow<PatientDetails> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tblPatient.getItems().remove(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );
                return row;
            }
        });
    }
}
