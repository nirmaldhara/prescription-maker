package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import javafx.print.*;
import javafx.scene.control.DatePicker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
    //7 parameters
    public static boolean createPrescription(PatientDetails patientDetails, List<MedicineDetails> lstMedicineDetails, List<ComplainDetails> lstComplainDetails, List<PreviousHistoryDetails> lstPreviousHistoryDetails, List<FindingsDetails> lstFindingsDetails, List<SuggestionsDetails> lstSuggestionsDetails, DatePicker txtCurrentDate) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        boolean flag = false;
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100%>");

        htmlBuilder.append("<table style='width:350px;'>");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td nowrap style='text-align: left;font-size:12;'>"+formattedDateTime+"</td>");
        htmlBuilder.append("<td style='text-align: center;font-size:12;'>Prescription</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");

        htmlBuilder.append("<div style='padding-top:20px;'>");
        htmlBuilder.append("<table width=100%>");

        htmlBuilder.append("<tr><td nowrap> <b>Patient's Id :</b></td><td width>"+"100"+patientDetails.getId()+"</td> <td nowrap><b>Patient's Name :</b></td><td nowrap>"+patientDetails.getFirst_name()+"</td><td nowrap><b>Date :</b></td><td nowrap>"+formattedDateTime+"</td><td></td></tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr><td nowrap align='right'><b>Mobile Number :</b></td><td nowrap>"+patientDetails.getMobile_no()+"</td><td nowrap align='right'><b>Address :</b></td><td nowrap>"+patientDetails.getAddress()+"</td><td nowrap><b>Age :</b></td><td>"+patientDetails.getAge()+"</td><td nowrap><b>Gender :</b></td><td>"+patientDetails.getSex()+"</td></tr>");
        htmlBuilder.append("</table>");

        htmlBuilder.append("<table width=100%>");
        htmlBuilder.append("<tr><td nowrap><b>Next Visit :</b></td><td nowrap>"+txtCurrentDate.getValue()+"</td></tr>");
        htmlBuilder.append("<tr><td><img src='rx.png' width=30 height = 40 style='padding-top:50px;'></td></tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");


        htmlBuilder.append("<div style='padding-left:30px;'>");
        htmlBuilder.append("<table style='border-collapse: collapse;width: 600px;border:none;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'>");
        htmlBuilder.append("<th nowrap>Medicine Name</th>");
        htmlBuilder.append("<th>Dose</th>");
        htmlBuilder.append("<th>Duration</th>");
        htmlBuilder.append("<th>Note</th>");
        htmlBuilder.append("</tr>");

        for (MedicineDetails pd : lstMedicineDetails) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td nowrap>" + (pd.getMedicineName() != null ? pd.getMedicineName() : "")  + "</td>");

            htmlBuilder.append("<td>");
            htmlBuilder.append("<div style='display:flex; align-items:center'>");

            // Check and display Dose 1 if it has a value
            if (pd.getDose1() != null && !pd.getDose1().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose1() + "</div>");
                htmlBuilder.append("-");
            }

            // Check and display Dose 2 if it has a value
            if (pd.getDose2() != null && !pd.getDose2().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose2() + "</div>");
                htmlBuilder.append("-");
            }

            // Check and display Dose 3 if it has a value
            if (pd.getDose3() != null && !pd.getDose3().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose3() + "</div>");
                htmlBuilder.append("-");
            }

            // Check and display Dose 4 if it has a value
            if (pd.getDose4() != null && !pd.getDose4().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose4() + "</div>");
                htmlBuilder.append("-");
            }

            // Check and display Dose 5 if it has a value
            if (pd.getDose5() != null && !pd.getDose5().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose5() + "</div>");
                htmlBuilder.append("-");
            }

            // Check and display Dose 6 if it has a value
            if (pd.getDose6() != null && !pd.getDose6().isEmpty()) {
                htmlBuilder.append("<div style='width:30px; height:30px; text-align:center; border-radius:50%; border:1px solid black; color:black; line-height:30px; margin-right: 5px; font-size:10;'>" + pd.getDose6() + "</div>");
            }

            htmlBuilder.append("</div>");
            htmlBuilder.append("</td>");
            htmlBuilder.append("<td nowrap>" + pd.getNoOfDays() + "</td>");
            htmlBuilder.append("<td nowrap>" + pd.getNote() + "</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");

        /////////////////////////////////////////////
        htmlBuilder.append("<div style='padding-left:30px;padding-top:50px;display:flex;'>");

        htmlBuilder.append("<div style='width:150px'>");////
        htmlBuilder.append("<table style='border-collapse: collapse;width: 150px;border:none;'>");

        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap>Complain Of</th></tr>");
        for(ComplainDetails cd:lstComplainDetails) {
            htmlBuilder.append("<tr><td nowrap>"+cd.getComplain()+"</td></tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");////

        htmlBuilder.append("<div style='width:150px'>");////
        htmlBuilder.append("<table style='border-collapse: collapse;width: 150px;border:none;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap>Previous History</th></tr>");
        for(PreviousHistoryDetails phd:lstPreviousHistoryDetails) {
            htmlBuilder.append("<tr><td nowrap>"+phd.getPrevious_history()+"</td></tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");////

        htmlBuilder.append("<div style='width:150px'>");////
        htmlBuilder.append("<table style='border-collapse: collapse;width: 150px;border:none;'>");
            htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap>Findings</th></tr>");
        for(FindingsDetails fd:lstFindingsDetails) {
            htmlBuilder.append("<tr><td nowrap>"+fd.getFindings()+"</td></tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");////

        htmlBuilder.append("<div style='width:150px'>");////
        htmlBuilder.append("<table style='border-collapse: collapse;width: 150px;border:none;'>");
            htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap>Suggestions</th></tr>");
        for(SuggestionsDetails sd:lstSuggestionsDetails) {
            htmlBuilder.append("<tr><td nowrap>"+sd.getSuggestions()+"</td></tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");////

        htmlBuilder.append("</div>");

        htmlBuilder.append("<div style='padding-left:30px;padding-top:50px'>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td><img src='lungs.png' width=140 height = 153></td>");
        htmlBuilder.append("<td><img src='heart.png' width=140 height = 153></td>");
        htmlBuilder.append("<td><img src='abdomen.png' width=140 height = 153></td>");
        htmlBuilder.append("<td><img src='kidney.png' width=140 height=153></td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");

        /////////////////////////////////////////////
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
