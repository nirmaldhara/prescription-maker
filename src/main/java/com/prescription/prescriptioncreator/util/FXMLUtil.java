package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.PrescriptionMaker;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLUtil {

    public static void openAddpatientWindow(String Fxml,int width, int height,String title)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation( FXMLUtil.class.getResource(Fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image(FXMLUtil.class.getResourceAsStream("/img/registration.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FXMLUtil.class.getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    public static void openAddTestReportWindow(String Fxml,int width, int height,String title)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation( FXMLUtil.class.getResource(Fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image(FXMLUtil.class.getResourceAsStream("/img/test-tube.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FXMLUtil.class.getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    public static void openAddMedicineWindow(String Fxml,int width, int height,String title)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation( FXMLUtil.class.getResource(Fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image(FXMLUtil.class.getResourceAsStream("/img/medicine.png")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FXMLUtil.class.getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    public static void clearTextBox(TextField t1){
        t1.clear();
    }
    public static void clearTextBox(TextField t1,String value){
        t1.setText(value);
    }
    public static void clearTableColumn(TableColumn c1,String value){
        c1.setText(value);
    }
    public static void clearComboBox(ComboBox<String> comboBox,String value) {
        comboBox.getSelectionModel().clearSelection();

        comboBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty) ;
                if (empty || item == null) {
                    setText(value);
                } else {
                    setText(item);
                }
            }
        });
        comboBox.setPromptText(value);
    }


    public static TextField toUpperCase(TextField t){

        t.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));
        return t;
    }


    public static void removeTableRow(TableView tbl) {
        tbl.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView tableView) {
                final TableRow row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Remove");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tbl.getItems().remove(row.getItem());
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
