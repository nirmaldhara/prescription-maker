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
        String preMedName="";
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100%>");

        htmlBuilder.append("<div style='padding-top:20px;'>");
        htmlBuilder.append("<table border=0 style='width:595px; font-size: 12px;'>");

        htmlBuilder.append("<tr><td nowrap style='width: 100px;'> <b>Patient's Id :</b></td><td  nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+(1000+patientDetails.getId())+"</td> <td nowrap style='width: 100px;'><b>Patient's Name :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 295px;'>"+patientDetails.getFirst_name()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap><b>Age :</b></td><td style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getAge()+"</td><td nowrap><b>Mobile Number :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getMobile_no()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Weight:</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getWeight()+" KG </td> <td nowrap align='left' ><b>Height :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getHeight()+" CM</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>BP :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getBp()+" mmHg</td><td nowrap align='left' ><b>Pulse :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getPulse()+" per min</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+visitDate+"</td> <td nowrap align='left' ><b>Next Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+nextVisit+"</td></tr>");

        htmlBuilder.append("<tr><td nowrap align='left' ><b>Address :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 400px;' colspan='3'>"+patientDetails.getAddress()+"</td></tr>");


        htmlBuilder.append("</table>");
        htmlBuilder.append("</div>");


        htmlBuilder.append("<img src='rx.png' width=20 height = 20 style='padding-top:50px; padding-bottom:10px'>");





        /////////////////////////////////////////////
        htmlBuilder.append("<table><tr>");

        htmlBuilder.append("<td><table style='border-collapse: collapse;width: 150px;border:none;font-size: 10px;'>");

        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Complain Of</th></tr>");
        for(ComplainDetails cd:lstComplainDetails) {
            htmlBuilder.append("<tr><td nowrap>"+cd.getComplain()+"</td></tr>");
        }
        htmlBuilder.append("</table></td>");

        htmlBuilder.append("<td><table style='border-collapse: collapse;width: 150px;border:none; font-size: 10px;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Previous History</th></tr>");
        for(PreviousHistoryDetails phd:lstPreviousHistoryDetails) {
            htmlBuilder.append("<tr><td nowrap>"+phd.getPrevious_history()+"</td></tr>");
        }
        htmlBuilder.append("</table></td>");


        htmlBuilder.append("<td><table style='border-collapse: collapse;width: 150px;border:none; font-size: 10px;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Findings</th></tr>");
        for(FindingsDetails fd:lstFindingsDetails) {
            htmlBuilder.append("<tr><td nowrap>"+fd.getFindings()+"</td></tr>");
        }
        htmlBuilder.append("</table></td>");

        htmlBuilder.append("<td><table style='border-collapse: collapse;width: 150px;border:none; font-size: 10px;'>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Suggestion</th></tr>");
        for(SuggestionsDetails sd:lstSuggestionsDetails) {
            htmlBuilder.append("<tr><td nowrap>"+sd.getSuggestions()+"</td></tr>");
        }
        htmlBuilder.append("</table></td>");



        htmlBuilder.append("</tr></table>");


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
