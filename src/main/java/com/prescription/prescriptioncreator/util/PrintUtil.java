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
    //6 parameters
    public static boolean createPrescription(String visitDate,String nextVisit,PatientDetails patientDetails,List<MedicineDetails> lstMedicineDetails,List<ComplainDetails> lstComplainDetails,List<PreviousHistoryDetails> lstPreviousHistoryDetails,List<FindingsDetails> lstFindingsDetails,List<SuggestionsDetails> lstSuggestionsDetails) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        boolean flag = false;
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100%>");

        htmlBuilder.append("<div style='padding-top:20px;'>");
        htmlBuilder.append("<table border=0 style='width:595px; font-size: 12px;'>");

        htmlBuilder.append("<tr><td nowrap style='width: 100px;'> <b>Patient's Id :</b></td><td  nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+(1000+patientDetails.getId())+"</td> <td nowrap style='width: 100px;'><b>Patient's Name :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 295px;'>"+patientDetails.getFirst_name()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap><b>Age :</b></td><td style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getAge()+"</td><td nowrap><b>Mobile Number :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getMobile_no()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+visitDate+"</td> <td nowrap align='left' ><b>Next Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+nextVisit+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Address :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 400px;' colspan='3'>"+patientDetails.getAddress()+"</td></tr>");


        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");


        htmlBuilder.append("<div style='padding-left:30px;'>");
        htmlBuilder.append("<img src='rx.png' width=20 height = 20 style='padding-top:50px; padding-bottom:10px'>");

        htmlBuilder.append("<table style='border-collapse: collapse;width: 595px;border:none; font-size: 10px;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'>");
        htmlBuilder.append("<th nowrap>Medicine Name</th>");
        htmlBuilder.append("<th>Dose</th>");
        htmlBuilder.append("<th>Duration</th>");
        htmlBuilder.append("<th>Note</th>");
        htmlBuilder.append("</tr>");

        for(MedicineDetails pd:lstMedicineDetails) {
            String d1=pd.getDose1();
            String d2=pd.getDose2();
            String d3=pd.getDose3();
            String d4=pd.getDose4();
            String d5=pd.getDose5();
            String d6=pd.getDose6();
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td nowrap>"+pd.getMedicineName() +"</td>");

            htmlBuilder.append("<td>");
            htmlBuilder.append("<div style='display:flex;align-items:center'>");
            if(d1!=null && !d1.equals("0") && !d1.equals(""))
            htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d1+"</div> <div><img src='line.png' width=10 style='padding-right:4px'></div>");
            if(d2!=null && !d2.equals("0") && !d2.equals(""))
                htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d2+"</div><div><img src='line.png' width=10 style='padding-right:4px'></div>");
            if(d3!=null && !d3.equals("0") && !d3.equals(""))
                htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d3+"</div> <div><img src='line.png' width=10 style='padding-right:4px'></div>");
            if(d4!=null && !d4.equals("0") && !d4.equals(""))
                htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d4+"</div><div><img src='line.png' width=10 style='padding-right:4px'></div>");
            if(d5!=null && !d5.equals("0") && !d5.equals(""))
                htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d6+"</div><div><img src='line.png' width=10 style='padding-right:4px'></div>");
            if(d6!=null && !d6.equals("0") && !d6.equals(""))
                htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>"+d6+"</div><div><img src='line.png' width=10 style='padding-right:4px'></div>");

            htmlBuilder.append("</div>");
            htmlBuilder.append("</td>");
            htmlBuilder.append("<td nowrap>"+pd.getNoOfDays()+"</td>");
            htmlBuilder.append("<td nowrap>"+pd.getNote()+"</td>");
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
        htmlBuilder.append("<table style='border-collapse: collapse;width: 150px;border:none; font-size: 10px;'>");
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
