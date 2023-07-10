package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PatientRenderUtil {

    public static  void displayPatientDetails(List<PatientDetails> lstPatient,
                                       TableView tblPatient,
                                       TableColumn<PatientDetails, String> tblPatientName,
                                       TableColumn<PatientDetails, String> tblPatientAge,
                                       TableColumn<PatientDetails, String> tblPatientSex,
                                       TableColumn<PatientDetails, String> tblPatientAddress,
                                       TableColumn<PatientDetails, String> tblPatientMobileNo,
                                       TableColumn<PatientDetails, String> tblPatientId){


         ObservableList<PatientDetails> data = FXCollections.observableArrayList(lstPatient);
        tblPatientName.setCellValueFactory(new PropertyValueFactory("first_name"));
        tblPatientAge.setCellValueFactory(new PropertyValueFactory("age"));
        tblPatientSex.setCellValueFactory(new PropertyValueFactory("sex"));
        tblPatientAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblPatientMobileNo.setCellValueFactory(new PropertyValueFactory("mobile_no"));
        tblPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        tblPatient.setItems(data);
    }


    public static  void displayPreviousVisitDetails(List<PreviousVisit> previousVisit,
                                              TableView <PreviousVisit> tblPreviousVisit,
                                              TableColumn<PreviousVisit, String> clmnPreviousVisit){

        ObservableList<PreviousVisit> data = FXCollections.observableArrayList(previousVisit);
        clmnPreviousVisit.setCellValueFactory(new PropertyValueFactory("previousVisit"));

        tblPreviousVisit.setItems(data);
    }
}
