package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.TestDetails;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.TestService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public static  void removeTestRow(  TableView tblTest){
        tblTest.setRowFactory(new Callback<TableView<TestDetails>, TableRow<TestDetails>>() {
            @Override
            public TableRow<TestDetails> call(TableView<TestDetails> tableView) {
                final TableRow<TestDetails> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tblTest.getItems().remove(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );
                return row ;
            }
        });
    }
    public static void setTestSearchAutoComplete(TestService testService, TextField txtTestName, TextField txtTestValue) throws Exception {
        ObservableList<TestDetails> autoCompleteData;
        autoCompleteData= FXCollections.observableArrayList(testService.getAutoSuggestTest());
        AutoCompletionBinding acb = TextFields.bindAutoCompletion(txtTestName ,autoCompleteData );
        acb.setVisibleRowCount(5);
        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<TestDetails>>()
        {

            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<TestDetails> event)
            {

                TestDetails value = event.getCompletion();
                txtTestValue.setText(value.getTest_value());
                // acb.dispose();
            }
        });

    }
}
