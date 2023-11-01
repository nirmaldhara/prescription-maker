package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;
import com.prescription.prescriptioncreator.service.FindingsService;
import com.prescription.prescriptioncreator.service.SuggestionsService;
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

public class SuggestionsRenderUtil {
    public static void addToSuggestions(List<SuggestionsDetails> lstSuggestionsDetails,
                                     TableView tblSuggestions,
                                     TableColumn<SuggestionsDetails, String> clmnSuggestions
    ){
        ObservableList<SuggestionsDetails> data = FXCollections.observableArrayList(lstSuggestionsDetails);

        clmnSuggestions.setCellValueFactory(new PropertyValueFactory("suggestions"));

        tblSuggestions.setItems(data);

    }

    public static void setSuggestionsSearchAutoComplete(SuggestionsService suggestionsService, TextField txtSuggestions) throws Exception {
        ObservableList<SuggestionsDetails> autoCompleteData;
        autoCompleteData= FXCollections.observableArrayList(suggestionsService.getAutoSuggestSuggestions());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtSuggestions ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<SuggestionsDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<SuggestionsDetails> event)
            {

                SuggestionsDetails value = event.getCompletion();
                txtSuggestions.setText(value.getSuggestions());
                // acb.dispose();

            }
        });

    }
}
