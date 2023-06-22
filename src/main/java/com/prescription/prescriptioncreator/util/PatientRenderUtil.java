package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.PatientDetails;
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


         ObservableList<PatientDetails> data = FXCollections.observableArrayList();

        for(PatientDetails patientDetails:lstPatient)
        {
            PatientDetails p1= new PatientDetails();
            p1.setFirst_name(patientDetails.getFirst_name() +" "+patientDetails.getLast_name());
            p1.setAge(patientDetails.getAge());
            p1.setSex(patientDetails.getSex());
            p1.setAddress(patientDetails.getAddress());
            p1.setPatientId(patientDetails.getPatientId());
            p1.setMobile_no(patientDetails.getMobile_no());
            data.add(p1);
        }
        tblPatientName.setCellValueFactory(new PropertyValueFactory("first_name"));
        tblPatientAge.setCellValueFactory(new PropertyValueFactory("age"));
        tblPatientSex.setCellValueFactory(new PropertyValueFactory("sex"));
        tblPatientAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblPatientMobileNo.setCellValueFactory(new PropertyValueFactory("mobile_no"));
        tblPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        tblPatient.setItems(data);
    }
}
