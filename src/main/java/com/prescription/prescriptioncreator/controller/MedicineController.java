package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.appenum.Message;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.service.MedicineService;
import com.prescription.prescriptioncreator.service.impl.MedicineServiceImpl;
import com.prescription.prescriptioncreator.util.ToastUtil;
import com.prescription.prescriptioncreator.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.prescription.prescriptioncreator.appenum.Message.*;
import static com.prescription.prescriptioncreator.appenum.IntegerValue.*;
import static com.prescription.prescriptioncreator.appenum.IntegerValue.SUCCESS;

public class MedicineController {
    @FXML
    private ComboBox<String> cmbWhen,cmbNoOFDays;
    @FXML
    private TextField txtMedicineName,txtDose1,txtDose2,txtDose3,txtDose4,txtDose5,txtDose6,txtNote;
    Stage stage = new Stage();
    @FXML
    public void initialize() {
        Scene scne= new Scene(new VBox());
        stage.setScene(scne);
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
        if(!(ValidationUtil.isTextFieldBlank(txtMedicineName, Message.MEDICINE_NAME_BLANK.val()))) {
            medicineDetails.setMedicineName(txtMedicineName.getText());
            medicineDetails.setDose1(txtDose1.getText());
            medicineDetails.setDose2(txtDose2.getText());
            medicineDetails.setDose3(txtDose3.getText());
            medicineDetails.setDose4(txtDose4.getText());
            medicineDetails.setDose5(txtDose5.getText());
            medicineDetails.setDose6(txtDose6.getText());
            medicineDetails.setWhen(cmbWhen.getValue());
            medicineDetails.setNoOfDays(Integer.parseInt(cmbNoOFDays.getValue()==null?"0":cmbNoOFDays.getValue()));
            medicineDetails.setNote(txtNote.getText());

            if(medicineService.addMedicine(medicineDetails)==true){
                ToastUtil.makeText(stage, ADD_MEDICINE_SUCCESS.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), SUCCESS.val());
            }
            else {
                ToastUtil.makeText(stage, ADD_MEDICINE_ERROR.val(), LONG_DELAY.val(), SHORT_FADE_IN_DELAY.val(), SHORT_FADE_OUT_DELAY.val(), ERROR.val());

            }
        }
    }
}
