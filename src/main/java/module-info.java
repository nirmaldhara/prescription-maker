module com.prescription.prescriptioncreator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.prescription.prescriptioncreator to javafx.fxml;
    exports com.prescription.prescriptioncreator;
    exports com.prescription.prescriptioncreator.model;
    opens com.prescription.prescriptioncreator.model to javafx.fxml;
    exports com.prescription.prescriptioncreator.controller;
    opens com.prescription.prescriptioncreator.controller to javafx.fxml;
}