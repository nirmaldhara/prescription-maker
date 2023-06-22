package com.prescription.prescriptioncreator.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLUtil {

    public static void openChildWindow(String Fxml,int width, int height,String title)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation( FXMLUtil.class.getResource(Fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FXMLUtil.class.getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}
