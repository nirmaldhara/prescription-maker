package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.AbdomenDetails;
import com.prescription.prescriptioncreator.model.LungsDetails;
import javafx.scene.control.TextField;

public class AbdomenRenderUtil {
    public static void addToAbdomen(AbdomenDetails abdomenDetails,
                                    TextField txtAbdomenPoint1,
                                    TextField txtAbdomenPoint2,
                                    TextField txtAbdomenPoint3,
                                    TextField txtAbdomenPoint4,
                                    TextField txtAbdomenPoint5,
                                    TextField txtAbdomenPoint6,
                                    TextField txtAbdomenPoint7,
                                    TextField txtAbdomenPoint8,
                                    TextField txtAbdomenPoint9)
    {
        txtAbdomenPoint1.setText(abdomenDetails.getAbdomen_point1());
        txtAbdomenPoint2.setText(abdomenDetails.getAbdomen_point2());
        txtAbdomenPoint3.setText(abdomenDetails.getAbdomen_point3());
        txtAbdomenPoint4.setText(abdomenDetails.getAbdomen_point4());
        txtAbdomenPoint5.setText(abdomenDetails.getAbdomen_point5());
        txtAbdomenPoint6.setText(abdomenDetails.getAbdomen_point6());
        txtAbdomenPoint7.setText(abdomenDetails.getAbdomen_point7());
        txtAbdomenPoint8.setText(abdomenDetails.getAbdomen_point8());
        txtAbdomenPoint9.setText(abdomenDetails.getAbdomen_point9());

    }
}
