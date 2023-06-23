package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PrescriptionRenderUtil {
    public static  void addToPrescription(List<MedicineDetails> lstMedicineDetails,
                                          TableView tblPrescription,
                                          TableColumn<MedicineDetails, String> clmnMedicineName,
                                          TableColumn<MedicineDetails, String>clmnD1,
                                          TableColumn<MedicineDetails, String> clmnD2,
                                          TableColumn<MedicineDetails, String> clmnD3,
                                          TableColumn<MedicineDetails, String> clmnD4,
                                          TableColumn<MedicineDetails, String> clmnD5,
                                          TableColumn<MedicineDetails, String> clmnD6,
                                          TableColumn<MedicineDetails, String> clmnWhen,
                                          TableColumn<MedicineDetails, String> clmnDays,
                                          TableColumn<MedicineDetails, String> clmnNote){


        ObservableList<MedicineDetails> data = FXCollections.observableArrayList(lstMedicineDetails);




            clmnMedicineName.setCellValueFactory(new PropertyValueFactory("medicineName"));
            clmnD1.setCellValueFactory(new PropertyValueFactory("Dose1"));
            clmnD2.setCellValueFactory(new PropertyValueFactory("Dose2"));
            clmnD3.setCellValueFactory(new PropertyValueFactory("Dose3"));
            clmnD4.setCellValueFactory(new PropertyValueFactory("Dose4"));
            clmnD5.setCellValueFactory(new PropertyValueFactory("Dose5"));
            clmnD6.setCellValueFactory(new PropertyValueFactory("Dose6"));
            clmnWhen.setCellValueFactory(new PropertyValueFactory("when"));
            clmnDays.setCellValueFactory(new PropertyValueFactory("noOfDays"));
            clmnNote.setCellValueFactory(new PropertyValueFactory("note"));

            tblPrescription.setItems(data);


    }
}
