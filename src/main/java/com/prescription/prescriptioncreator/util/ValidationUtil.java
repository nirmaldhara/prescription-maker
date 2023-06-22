package com.prescription.prescriptioncreator.util;

import javafx.scene.control.TextField;

public class ValidationUtil {

    public static boolean isBlank(TextField textField){
        if(textField!=null)
       if(textField.getText().trim().equals(""))
       {
           textField.setStyle("-fx-text-inner-color: red;");
           return true;
       }

        return false;
    }

}
