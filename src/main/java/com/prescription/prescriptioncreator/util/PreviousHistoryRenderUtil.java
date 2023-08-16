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
}
