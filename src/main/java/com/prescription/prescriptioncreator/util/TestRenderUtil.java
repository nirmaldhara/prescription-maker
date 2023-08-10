package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.PrescriptionService;
import com.prescription.prescriptioncreator.service.TestService;
import com.prescription.prescriptioncreator.service.impl.PrescriptionServiceImpl;
import com.prescription.prescriptioncreator.service.impl.TestServiceImpl;
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

public class TestRenderUtil {
    /* @method addToTest
     * @param lstTestDetails,tblTest,clmnTestName,clmnTestValue
     * @description adding test details in UI
     * @developer Sukhendu
     */
    /* @method removeTestRow
     * @param tblTest
     * @description removing 1 test row
     * @developer Sukhendu
     */
    public static void addToTest(List<TestDetails> lstTestDetails,
                                 TableView tblTest,
                                 TableColumn<TestDetails,String> clmnTestName,
                                 TableColumn<TestDetails,String> clmnTestValue){
        ObservableList<TestDetails> data = FXCollections.observableArrayList(lstTestDetails);
        clmnTestName.setCellValueFactory(new PropertyValueFactory("test_name"));
        clmnTestValue.setCellValueFactory(new PropertyValueFactory("test_value"));
        tblTest.setItems(data);
    }
}
