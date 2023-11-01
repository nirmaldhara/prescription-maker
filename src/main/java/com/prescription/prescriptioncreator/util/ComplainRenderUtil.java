package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.ComplainService;
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

public class ComplainRenderUtil {
    public static void addToComplain(List<ComplainDetails> lstComplainDetails,
                                     TableView tblComplain,
                                     TableColumn<ComplainDetails, String> clmnComplain
    ) {
        ObservableList<ComplainDetails> data = FXCollections.observableArrayList(lstComplainDetails);

        clmnComplain.setCellValueFactory(new PropertyValueFactory("complain"));

        tblComplain.setItems(data);
    }

    public static void setComplainDetailsSearchAutoComplete(ComplainService complainService, TextField txtComplain) throws Exception {
        ObservableList<ComplainDetails> autoCompleteData;
        autoCompleteData = FXCollections.observableArrayList(complainService.getAutoSuggestComplain());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtComplain, autoCompleteData);
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<ComplainDetails>>() {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<ComplainDetails> event) {

                ComplainDetails value = event.getCompletion();
                txtComplain.setText(value.getComplain());
                // acb.dispose();

            }
        });

    }
}
