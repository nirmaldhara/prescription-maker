package com.prescription.prescriptioncreator.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValidationUtil {

    public static boolean isTextFieldBlank(TextField textField,String message){
        if(textField!=null)
       if(textField.getText().trim().equals(""))
       {
           Tooltip tooltip1 = new Tooltip(message);
           tooltip1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-background-color: #f74848; -fx-font-size: 16px;-fx-font-weight: bold;");
           textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
           textField.setTooltip(tooltip1);
           return true;
       }
        else{
           textField.setStyle("");
       }

        return false;
    }
    private boolean isNumeric(String text)
    {
        return text.matches("[0-9]*");
    }
    public static boolean isComboBoxBlank(ComboBox<String> cmbBox, String message){
        if(cmbBox!=null)
            if(cmbBox.getValue()==null)
            {
                Tooltip tooltip1 = new Tooltip(message);
                tooltip1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-background-color: #f74848; -fx-font-size: 16px;-fx-font-weight: bold;");
                cmbBox.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                cmbBox.setTooltip(tooltip1);
                return true;
            }
        else {
                cmbBox.setStyle("");
            }

        return false;
    }
    private boolean isValidMobileNumber(String text)
    {
        return text.matches("[0-9]*");
    }


}
