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
    private ComboBox<String> cmbDays;

    @FXML
    private ComboBox<String> cmbFood;

    @FXML
    private TextField txtAddMedicine;

    @FXML
    private TextField txtAddMedicine1;

    @FXML
    private TextField txtAddMedicine11;

    @FXML
    private TextField txtAddMedicine12;

    @FXML
    private TextField txtAddMedicine121;

    @FXML
    private TextField txtAddMedicine122;

    @FXML
    private TextField txtAddMedicine123;

    @FXML
    private TextField txtAddMedicine2;
    @FXML
    public void initialize() {

        ArrayList<String> cities = new ArrayList<>();
        cities.add("BF");cities.add("AF");
        ObservableList<String> list = FXCollections.observableArrayList(cities);
        cmbFood.setItems(list);


        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        cmbDays.setItems(listDays);

    }
    @FXML
    public  void addMedicineDetails(ActionEvent event) throws Exception{
        MedicineService medicineService = new MedicineServiceImpl();
        MedicineDetails medicineDetails = new MedicineDetails();
        medicineDetails.setMedicine_name(txtAddMedicine.getText());
        medicineDetails.setT1(txtAddMedicine1.getText());
        medicineDetails.setT2(txtAddMedicine11.getText());
        medicineDetails.setT3(txtAddMedicine12.getText());
        medicineDetails.setT4(txtAddMedicine121.getText());
        medicineDetails.setT5(txtAddMedicine122.getText());
        medicineDetails.setT6(txtAddMedicine123.getText());
        medicineDetails.setFr(cmbFood.getEditor().getText());
        medicineDetails.setDays(cmbDays.getEditor().getText());
        medicineDetails.setNote(txtAddMedicine2.getText());
        medicineService.addMedicine(medicineDetails);
    }
}
