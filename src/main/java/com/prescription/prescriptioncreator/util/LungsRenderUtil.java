package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.LungsDetails;
import javafx.scene.control.TextField;

public class LungsRenderUtil {
    public static void addToLungs(LungsDetails lungsDetails,
                                  TextField txtLungsPoint1,
                                  TextField txtLungsPoint2,
                                  TextField txtLungsPoint3,
                                  TextField txtLungsPoint4,
                                  TextField txtLungsPoint5,
                                  TextField txtLungsPoint6)
    {
        txtLungsPoint1.setText(lungsDetails.getLungs_point1());
        txtLungsPoint2.setText(lungsDetails.getLungs_point2());
        txtLungsPoint3.setText(lungsDetails.getLungs_point3());
        txtLungsPoint4.setText(lungsDetails.getLungs_point4());
        txtLungsPoint5.setText(lungsDetails.getLungs_point5());
        txtLungsPoint6.setText(lungsDetails.getLungs_point6());
    }
}

