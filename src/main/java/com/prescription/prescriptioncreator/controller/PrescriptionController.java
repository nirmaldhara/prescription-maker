package com.prescription.prescriptioncreator.controller;

import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.service.PatientService;
import com.prescription.prescriptioncreator.service.impl.PatientServiceImpl;
import com.prescription.prescriptioncreator.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionController {

    @FXML
    TableView tblPrescription;

    @FXML
    TextField  txtMobileNo;
    @FXML
    TextField  txtPatientId;

    @FXML
    TableColumn <PatientDetails, String> clmnMedicine;
    @FXML
    TableColumn <PatientDetails, String> clmnT1;
    @FXML
    TableColumn <PatientDetails, String> clmnT2;
    @FXML
    TableColumn <PatientDetails, String> clmnT3;

    @FXML
    TableColumn <PatientDetails, String> clmnT4;
    @FXML
    TableColumn <PatientDetails, String> clmnT5;
    @FXML
    TableColumn <PatientDetails, String> clmnT6;
    @FXML
    TableColumn <PatientDetails, String> clmnNote;


    @FXML
    TableView tblPatient;
    @FXML
    TableColumn <PatientDetails, String> tblPatientName;
    @FXML
    TableColumn <PatientDetails, Integer> tblPatientAge;
    @FXML
    TableColumn <PatientDetails, String> tblPatientSex;
    @FXML
    TableColumn <PatientDetails, String> tblPatientAddress;

    @FXML
    TableColumn <PatientDetails, String> tblPatientMobileNo;
    @FXML
    TableColumn <PatientDetails, String> tblPatientId;
    @FXML
    private Label welcomeText;
    @FXML
    private  ChoiceBox chBoxWhen;
    @FXML
    private  ChoiceBox chBoxDays;
    @FXML
    ButtonBar btnBar;
    @FXML
    TextField txtAddMedicine;
    @FXML
    VBox mainVBox;

    @FXML
    DatePicker txtCurrentDate;

    private ObservableList<PatientDetails> data;
    private ObservableList<PrescriptionDetails> prescriptionData;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    private void displayPatientDetails(List<PatientDetails> lstPatient){


        data = FXCollections.observableArrayList();

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
    @FXML
    public void initialize() {


        ArrayList<String> cities = new ArrayList<>();
        cities.add("BF");cities.add("AF");
        ObservableList<String> list = FXCollections.observableArrayList(cities);
        chBoxWhen.setItems(list);


        ArrayList<String> days = new ArrayList<>();
        for(int i=1;i<=365;i++)
            days.add(""+i);
        ObservableList<String> listDays = FXCollections.observableArrayList(days);
        chBoxDays.setItems(listDays);

        prescriptionData = FXCollections.observableArrayList();
        PrescriptionDetails pd = new PrescriptionDetails();
        pd.setMedicineName("Calpol 650");
        pd.setTime1("1/2");
        pd.setTime2("1/2");
        pd.setTime3("1/2");
        pd.setTime4("1/2");
        pd.setNote("For 5 days");
        prescriptionData.add(pd);
       // tblPatientName.setCellValueFactory(new PropertyValueFactory("Medicine"));

//        clmnT1.setCellValueFactory(new PropertyValueFactory("T1"));
//        clmnT2.setCellValueFactory(new PropertyValueFactory("T2"));
//        clmnT3.setCellValueFactory(new PropertyValueFactory("T3"));
//        clmnT4.setCellValueFactory(new PropertyValueFactory("T4"));
//        clmnNote.setCellValueFactory(new PropertyValueFactory("Note"));
//        tblPrescription.setItems(data);
      //  txtAddMedicine.bindAutoCompletion(textfield,"text to suggest", "another text to suggest");
        txtCurrentDate.setValue(NOW_LOCAL_DATE());
         // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.
    }
    public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }
@FXML
    public void openPrescription( ActionEvent event){
        try {

            System.out.println("Opening");

            VBox newLoadedPane =  FXMLLoader.load(getClass().getResource("addpatient-view.fxml"));
            mainVBox.getChildren().add(newLoadedPane);
        } catch (IOException e) {

        }
    }

    @FXML
    public void openAddPatient( ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/addpatient-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 540, 220);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void searchPatientDetails ( ActionEvent event) throws Exception {
        PatientService patientService= new PatientServiceImpl();
        String mobileNo=txtMobileNo.getText();
        String patientId=txtPatientId.getText();
        List<PatientDetails> lstPatient=patientService.searchPatientDetails(mobileNo,patientId);
        displayPatientDetails(lstPatient);
        System.out.println("Search"+mobileNo);
    }
}