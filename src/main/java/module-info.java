module com.prescription.prescriptioncreator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.prescription.prescriptioncreator to javafx.fxml;
    exports com.prescription.prescriptioncreator;
}