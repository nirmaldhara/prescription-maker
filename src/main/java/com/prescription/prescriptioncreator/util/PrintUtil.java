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

    public static boolean createPrescription() throws IOException {
        boolean flag = false;
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100%>");

        htmlBuilder.append("<table style='width:100%;'>");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td nowrap style='text-align: left;font-size:12;'>09/28/23, 3:26 AM</td>");
        htmlBuilder.append("<td style='text-align: center;font-size:12;'>Prescription</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");

        htmlBuilder.append("<div style='padding-top:20px;'>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr><td nowrap> <b>Patient's Id :</b></td><td width>123456</td> <td nowrap><b>Patient's Name :</b></td><td nowrap>Sukhendu Bhowmik</td><td nowrap><b>Date :</b></td><td>10/08/2023</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='right'><b>Height :</b></td><td nowrap>167CM</td><td nowrap align='right'><b>Weight :</b></td><td nowrap>72KG</td><td nowrap><b>Age :</b></td><td>41</td><td nowrap><b>Gender :</b></td><td>Male</td></tr>");
        htmlBuilder.append("<tr><td><img src='rx.png' width=30 height = 40 style='padding-top:50px;'></td></tr>");//


        //htmlBuilder.append("<tr><td nowrap><center><img src='paid.png' width=70/></center></td></tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");

        htmlBuilder.append("<div style='padding-left:30px;'>");
        htmlBuilder.append("<table style='border-collapse: collapse;width: 100%;border:none;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'>");
        htmlBuilder.append("<th nowrap>Medicine Name</th>");
        htmlBuilder.append("<th>Dose</th>");
        htmlBuilder.append("<th>Duration</th>");
        htmlBuilder.append("<th>Note</th>");
        htmlBuilder.append("</tr>");

        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td nowrap>1.TAB DEMO MEDICINE1</td>");

        htmlBuilder.append("<td>");
        htmlBuilder.append("<div style='display:flex;align-items:center'>");
        htmlBuilder.append("<div style='width:30px;height:30px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:30px;margin-right: 5px;font-size:10;'>7.5ml</div>");
        htmlBuilder.append("-");
        htmlBuilder.append("<div style='width:30px;height:30px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:30px;margin-right: 5px;font-size:10;'>0</div>");
        htmlBuilder.append("-");
        htmlBuilder.append("<div style='width:30px;height:30px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:30px;margin-right: 5px;font-size:10;'>7.5ml</div>");

        htmlBuilder.append("</div>");
        htmlBuilder.append("</td>");
        htmlBuilder.append("<td nowrap>10 days</td>");
        htmlBuilder.append("<td nowrap>Demo notes here</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");

        htmlBuilder.append("<div style='padding-left:30px;padding-top:50px;'>");
        htmlBuilder.append("<table style='border-collapse: collapse;width: 100%;border:none;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black''>");
        htmlBuilder.append("<th nowrap>Complain Of</th>");
        htmlBuilder.append("<th nowrap>Previous History</th>");
        htmlBuilder.append("<th nowrap>Findings</th>");
        htmlBuilder.append("<th nowrap>Suggestions</th>");
        htmlBuilder.append("</tr>")
        ;
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td nowrap>Demo Complain1</td>");
        htmlBuilder.append("<td nowrap>Demo history1</td>");
        htmlBuilder.append("<td nowrap>Demo Findings1</td>");
        htmlBuilder.append("<td nowrap>Demo Suggestions1</td>");
        htmlBuilder.append("</tr>");

        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td><img src='lungs.png' width=140 height = 153 style='padding-top:50px;'></td>");
        htmlBuilder.append("<td><img src='heart.png' width=140 height = 153 style='padding-top:50px;'></td>");
        htmlBuilder.append("<td><img src='abdomen.png' width=140 height = 153 style='padding-top:50px;'></td>");
        htmlBuilder.append("<td><img src='kidney.png' width=140 height=153 style='padding-top:50px;'/></td>");
        htmlBuilder.append("</tr>");

        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");


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
