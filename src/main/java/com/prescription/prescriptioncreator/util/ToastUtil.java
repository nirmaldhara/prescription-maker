package com.prescription.prescriptioncreator.util;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ToastUtil
{
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay,int msgType)
    {
        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Verdana", 12));
        StackPane root = new StackPane(text);
        if(msgType==200) {
            text.setFill(Color.GREEN);

            root.setStyle("-fx-background-radius: 5; -fx-background-color: #d8ffcc; -fx-padding: 5px; -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: #165c01;");

        }
        else if(msgType==500) {

            root.setStyle("-fx-background-radius: 5; -fx-background-color: #f78888; -fx-padding: 5px;  -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: #f54a3b;");

            text.setFill(Color.RED);
        }

        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) ->
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        fadeOutTimeline.play(); // do your GUI stuff here
                    }
                });
            }).start();
        });
        fadeInTimeline.play();
    }
}