package com.prescription.prescriptioncreator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class HelloController {

    @FXML
    TableView tblPrescription;

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
      //  txtAddMedicine= new AutoCompleteTextField().getEntries().addAll(Arrays.asList("AA", "AB", "AC","BCA"));
        data = FXCollections.observableArrayList();

        PatientDetails p1= new PatientDetails();
        p1.setName("Patient1");
        p1.setAge(30);
        p1.setSex("Male");
        p1.setAddress("Kolkata");
        p1.setPatientId("12345");
        p1.setMobileNo("9611207000");
        data.add(p1);

        p1= new PatientDetails();
        p1.setName("Patient2");
        p1.setAge(6);
        p1.setSex("Male");
        p1.setAddress("Kolkata");
        p1.setPatientId("123456");
        p1.setMobileNo("9611207000");
        data.add(p1);

        p1= new PatientDetails();
        p1.setName("Patient3");
        p1.setAge(25);
        p1.setSex("Female");
        p1.setAddress("Kolkata");
        p1.setPatientId("123457");
        p1.setMobileNo("9611207000");
        data.add(p1);

        tblPatientName.setCellValueFactory(new PropertyValueFactory("name"));
        tblPatientAge.setCellValueFactory(new PropertyValueFactory("age"));
        tblPatientSex.setCellValueFactory(new PropertyValueFactory("sex"));
        tblPatientAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblPatientMobileNo.setCellValueFactory(new PropertyValueFactory("mobileNo"));
        tblPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        tblPatient.setItems(data);

        ////////////////////////////////////////

        prescriptionData = FXCollections.observableArrayList();
        PrescriptionDetails pd = new PrescriptionDetails();
        pd.setMedicineName("Calpol 650");
        pd.setTime1("1/2");
        pd.setTime2("1/2");
        pd.setTime3("1/2");
        pd.setTime4("1/2");
        pd.setNote("For 5 days");
        prescriptionData.add(pd);
        tblPatientName.setCellValueFactory(new PropertyValueFactory("Medicine"));

        clmnT1.setCellValueFactory(new PropertyValueFactory("T1"));
        clmnT2.setCellValueFactory(new PropertyValueFactory("T2"));
        clmnT3.setCellValueFactory(new PropertyValueFactory("T3"));
        clmnT4.setCellValueFactory(new PropertyValueFactory("T4"));
        clmnNote.setCellValueFactory(new PropertyValueFactory("Note"));
        tblPrescription.setItems(data);
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

    public void openPrescription( ActionEvent event){
        try {

            System.out.println("Opening");

            VBox newLoadedPane =  FXMLLoader.load(getClass().getResource("Prescription.fxml"));
            mainVBox.getChildren().add(newLoadedPane);
        } catch (IOException e) {

        }
    }


}