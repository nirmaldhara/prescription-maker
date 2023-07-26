package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.TestDetails;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class TestRenderUtil {
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
}
