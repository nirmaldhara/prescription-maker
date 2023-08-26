package com.prescription.prescriptioncreator.util;

import com.sun.javafx.print.Units;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.print.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import static com.sun.javafx.print.Units.MM;

public class PrintUtil {
    public static void print() throws MalformedURLException {
        //printstatus.setText("print : printing...");
        System.out.println("printing");
        Paper photo = null;
        try {
            WebView webview = new WebView();
            System.out.println("printing 1");
            final WebEngine webengine = webview.getEngine();
            System.out.println("printing 2");
            Printer printer = Printer.getDefaultPrinter();

            System.out.println("done");



            PrinterJob job = PrinterJob.createPrinterJob();

            job.getJobSettings().getPageLayout();

            JobSettings js = job.getJobSettings();

            Double w = js.getPageLayout().getLeftMargin();
            Double h = js.getPageLayout().getRightMargin();

            System.out.println("=------------" + w + "   " + h);
            if (job != null) {
                //System.out.println(job.getJobSettings().getPageLayout());

                File myFile = new File("prescription.html");
                URL myUrl = myFile.toURI().toURL();
                webengine.load(myUrl.toString());

                webengine.print(job);


                job.endJob();

                // printstatus.setText("print : Done");
            }
        } catch (SecurityException ex) {
            //Logger.getLogger(ElectricBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public    boolean createPrescription() throws IOException {
        boolean flag = false;
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100%>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr><td nowrap> Patient's Id</td><td width>123456</td> <td>Date</td><td>10/08/2023</td></tr>");
        htmlBuilder.append("<tr><td nowrap>Patient's Name :</td><td nowrap>Nirmal Dhara</td><td>Age <td><td>41</td><td>Gender</td><td>Male</td></tr>");
        htmlBuilder.append("<tr><td><img src='rx.png'></td></tr>");

        htmlBuilder.append("<tr><td></tr>");

        htmlBuilder.append("<tr><td nowrap><center><img src='paid.png' width=70/></center></td></tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        try (FileOutputStream oS = new FileOutputStream(new File("prescription.html"))) {
            oS.write(htmlBuilder.toString().getBytes());
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
