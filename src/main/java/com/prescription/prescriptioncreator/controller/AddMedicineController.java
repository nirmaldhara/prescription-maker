package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.impl.MedicineServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class AddMedicineController {
    @FXML
    private ComboBox<String> cmbWhen,cmbNoOFDays;
    @FXML
    private TextField txtMedicineName,txtDose1,txtDose2,txtDose3,txtDose4,txtDose5,txtDose6,txtNote;

    @FXML
    public void initialize() {


        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbNoOFDays.setItems(listDays);

    }
    @FXML
    public  void addMedicineDetails(ActionEvent event) throws Exception{
        MedicineService medicineService = new MedicineServiceImpl();
        MedicineDetails medicineDetails = new MedicineDetails();
        medicineDetails.setMedicineName(txtMedicineName.getText());
        medicineDetails.setDose1(txtDose1.getText());
        medicineDetails.setDose2(txtDose2.getText());
        medicineDetails.setDose3(txtDose3.getText());
        medicineDetails.setDose4(txtDose4.getText());
        medicineDetails.setDose5(txtDose5.getText());
        medicineDetails.setDose6(txtDose6.getText());
        medicineDetails.setWhen(cmbWhen.getValue());
        medicineDetails.setNoOfDays(cmbNoOFDays.getValue());
        medicineDetails.setNote(txtNote.getText());
        medicineService.addMedicine(medicineDetails);
    }
}
