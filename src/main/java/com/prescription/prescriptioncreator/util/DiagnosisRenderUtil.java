package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.DiagnosisDetails;
import com.prescription.prescriptioncreator.service.DiagnosisService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.List;

public class DiagnosisRenderUtil {
    public static void addToDiagnosis(List<DiagnosisDetails> lstDiagnosisDetails,
                                     TableView tblDiagnosis,
                                     TableColumn<DiagnosisDetails, String> clmnDiagnosis
    ){
        ObservableList<DiagnosisDetails> data = FXCollections.observableArrayList(lstDiagnosisDetails);

        clmnDiagnosis.setCellValueFactory(new PropertyValueFactory("diagnosis"));

        tblDiagnosis.setItems(data);

    }

    public static void setDiagnosisSearchAutoComplete(DiagnosisService diagnosisService, TextField txtDiagnosis) throws Exception {
        ObservableList<DiagnosisDetails> autoCompleteData;
        autoCompleteData= FXCollections.observableArrayList(diagnosisService.getAutoSuggestDiagnosis());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtDiagnosis ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<DiagnosisDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<DiagnosisDetails> event)
            {

                DiagnosisDetails value = event.getCompletion();
                txtDiagnosis.setText(value.getDiagnosis());
                // acb.dispose();

            }
        });

    }
}
