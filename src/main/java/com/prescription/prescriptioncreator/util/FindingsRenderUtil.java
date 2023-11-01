package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.FindingsService;
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

public class FindingsRenderUtil {
    public static void addToFindings(List<FindingsDetails> lstFindingsDetails,
                                            TableView tblFindings,
                                            TableColumn<FindingsDetails, String> clmnFindings
    ){
        ObservableList<FindingsDetails> data = FXCollections.observableArrayList(lstFindingsDetails);

        clmnFindings.setCellValueFactory(new PropertyValueFactory("findings"));

        tblFindings.setItems(data);

    }

    public static void setFindingsSearchAutoComplete(FindingsService findingsService, TextField txtFindings) throws Exception {
        ObservableList<FindingsDetails> autoCompleteData;
        autoCompleteData= FXCollections.observableArrayList(findingsService.getAutoSuggestFindings());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtFindings ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<FindingsDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<FindingsDetails> event)
            {

                FindingsDetails value = event.getCompletion();
                txtFindings.setText(value.getFindings());
                // acb.dispose();

            }
        });

    }
}
