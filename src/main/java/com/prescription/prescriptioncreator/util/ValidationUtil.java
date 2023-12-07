package com.prescription.prescriptioncreator.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean removeValidation(TextField textField){
         textField .setStyle("");

         return true;
    }
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
    public static boolean isNotNumeric(TextField txtAgeInYears, String text)
    {
        //return text.matches("[0-9]*");
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher match = pattern.matcher(txtAgeInYears.getText());

        if (match.matches()) {
            // Reset
            txtAgeInYears.setStyle("");
            txtAgeInYears.setTooltip(null);
            return false; // Valid input
        } else {
            Tooltip tooltip1 = new Tooltip(text);
            tooltip1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-background-color: #f74848; -fx-font-size: 16px;-fx-font-weight: bold;");
            txtAgeInYears.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            txtAgeInYears.setTooltip(tooltip1);
            return true; // Invalid input
        }
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
    public static boolean isValidMobileNumber(TextField text, String message) {
        Pattern pattern = Pattern.compile("^[6-9][0-9]{9}$");
        Matcher match = pattern.matcher(text.getText());

        if (match.matches()) {
            // Reset
            text.setStyle("");
            text.setTooltip(null);
            return false; // Valid input
        } else {
            Tooltip tooltip1 = new Tooltip(message);
            tooltip1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-background-color: #f74848; -fx-font-size: 16px;-fx-font-weight: bold;");
            text.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            text.setTooltip(tooltip1);
            return true; // Invalid input
        }
    }

    public static boolean isDatePickerBlank(DatePicker datepicker , String message){
        if(datepicker!=null)
            if(datepicker.getValue()==null){
                Tooltip tooltip1 = new Tooltip(message);
                tooltip1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-background-color: #f74848; -fx-font-size: 16px;-fx-font-weight: bold;");
                datepicker.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                datepicker.setTooltip(tooltip1);
                return true;
            }
        else{
                datepicker.setStyle("");
            }
        return false;
    }

}
