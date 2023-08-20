package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.PreviousHistoryService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.service.impl.PreviousHistoryServiceImpl;
import com.prescription.prescriptioncreator.util.PreviousHistoryRenderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PreviousHistoryController implements Initializable {
    @FXML
     TextField txtPHistory;
    Set<String> posibleWordSet = new HashSet<>();
    private AutoCompletionBinding<String>autoCompletionBinding;
    @FXML
    public void addPreviousHistory(KeyEvent event) throws Exception{
            PreviousHistoryService previousHistoryService = new PreviousHistoryServiceImpl();
            PreviousHistoryDetails previousHistoryDetails = new PreviousHistoryDetails();
            previousHistoryDetails.setPrevious_history(txtPHistory.getText());
            previousHistoryService.addPreviousHistory(previousHistoryDetails);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String dbsql = "select * from previous_history;";
        PreparedStatement preparedStmt =null;
        String previousHistory =null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            previousHistory=rs.getString("previous_history");

        } catch(Exception e){
            e.printStackTrace();
        }
        //TextFields.bindAutoCompletion(txtPHistory,previousHistory);
        autoCompletionBinding = TextFields.bindAutoCompletion(txtPHistory,posibleWordSet);
        txtPHistory.setOnKeyPressed((KeyEvent e)->{
            switch (e.getCode()){
                case ENTER:
                    learnWord(txtPHistory.getText());
                    break;
                default:
                    break;
            }
        });
    }

    private void learnWord(String text) {
        posibleWordSet.add(text);
        if(autoCompletionBinding !=null){
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(txtPHistory,posibleWordSet);
    }

}
