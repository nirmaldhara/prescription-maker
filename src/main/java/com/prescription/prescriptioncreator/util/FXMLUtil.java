package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.PrescriptionMaker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
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
        comboBox.setValue(value);
    }
}
