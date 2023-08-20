package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.PreviousHistoryService;
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

public class PreviousHistoryRenderUtil {
    public static void addToPreviousHistory(List<PreviousHistoryDetails> lstPreviousHistoryDetails,
                                            TableView tblPreviousHistory,
                                            TableColumn<PreviousHistoryDetails, String> clmnPreviousHistory
                                            ){
        ObservableList<PreviousHistoryDetails> data = FXCollections.observableArrayList(lstPreviousHistoryDetails);

        clmnPreviousHistory.setCellValueFactory(new PropertyValueFactory("previous_history"));

        tblPreviousHistory.setItems(data);

    }

    public static void setPreviousHistoryDetailsSearchAutoComplete(PreviousHistoryService previousHistoryService, TextField txtPHistory) throws Exception {
        ObservableList<PreviousHistoryDetails> autoCompleteData;
        autoCompleteData= FXCollections.observableArrayList(previousHistoryService.getAutoSuggestPreviousHistory());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtPHistory ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<PreviousHistoryDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<PreviousHistoryDetails> event)
            {

                PreviousHistoryDetails value = event.getCompletion();
                txtPHistory.setText(value.getPrevious_history());
                // acb.dispose();

            }
        });

    }
}
