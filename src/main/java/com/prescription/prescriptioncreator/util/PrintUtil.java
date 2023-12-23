package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    private static void printLung(StringBuilder htmlBuilder){
        htmlBuilder.append("<table style='border-collapse: collapse;border:none; font-size: 10px;' width=100%> ");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td>");
        htmlBuilder.append("<img src='/resources/img/abdomen.png'");
        htmlBuilder.append("</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");
    }

    private  static  void printMedicineDetails(StringBuilder htmlBuilder,List<MedicineDetails> lstMedicineDetails){
        String preMedName="";
        htmlBuilder.append("<table style='border-collapse: collapse;border:1px; font-size: 10px;' width=100%>");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'>");
        htmlBuilder.append("<th nowrap style='text-align:left'>Medicine Name</th>");
        htmlBuilder.append("<th style='text-align:left'>Dose</th>");
        htmlBuilder.append("<th style='text-align:left'>Duration(Days)</th>");
        htmlBuilder.append("<th style='text-align:left'>Note</th>");


        for(MedicineDetails pd:lstMedicineDetails) {
            String d1=pd.getDose1();
            String d2=pd.getDose2();
            String d3=pd.getDose3();
            String d4=pd.getDose4();
            String d5=pd.getDose5();
            String d6=pd.getDose6();

            String medName=pd.getMedicineName();

            if(!preMedName.equals(medName)) {
                htmlBuilder.append("<tr>");
                htmlBuilder.append("<td nowrap>" + medName + "</td>");
                preMedName = medName;


                htmlBuilder.append("<td>");
                htmlBuilder.append("<div style='display:flex;align-items:center'>");
                if (d1 != null &&  !d1.equals(""))
                    htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d1 + "</div> ");
                if (d2 != null  && !d2.equals(""))

                    htmlBuilder.append(" <div><img src='line.png' width=10 style='padding-right:4px'></div> <div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d2 + "</div>");

                if (d3 != null &&  !d3.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d3 + "</div> ");
                if (d4 != null &&  !d4.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d4 + "</div>");
                if (d5 != null && !d5.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d6 + "</div>");
                if (d6 != null &&  !d6.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d6 + "</div>");

                htmlBuilder.append("</div>");
                htmlBuilder.append("</td>");
                htmlBuilder.append("<td nowrap>" + pd.getNoOfDays() + "</td>");
                htmlBuilder.append("<td nowrap>" + pd.getNote() + "</td>");
                htmlBuilder.append("</tr>");
            }
            else{
                htmlBuilder.append("<tr>");
                htmlBuilder.append("<td nowrap> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                htmlBuilder.append("<td nowrap align=left style='width:100px'> <img src='arrow-down.png' width=10/></td>");
                htmlBuilder.append("</tr>");

                htmlBuilder.append("<tr>");
                htmlBuilder.append("<td nowrap> &nbsp;</td>");
                htmlBuilder.append("<td>");
                htmlBuilder.append("<div style='display:flex;align-items:center'>");
                if (d1 != null &&  !d1.equals(""))
                    htmlBuilder.append("<div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d1 + "</div> ");
                if (d2 != null &&  !d2.equals(""))

                    htmlBuilder.append(" <div><img src='line.png' width=10 style='padding-right:4px'></div> <div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d2 + "</div>");

                if (d3 != null  && !d3.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d3 + "</div> ");
                if (d4 != null &&  !d4.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d4 + "</div>");
                if (d5 != null &&  !d5.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d6 + "</div>");
                if (d6 != null &&  !d6.equals(""))
                    htmlBuilder.append("<div><img src='line.png' width=10 style='padding-right:4px'></div><div style='width:15px;height:15px;text-align:center;border-radius:50%;border:1px solid black;color:black;line-height:15px;margin-right: 5px;font-size:10;'>" + d6 + "</div>");

                htmlBuilder.append("</div>");
                htmlBuilder.append("</td>");
                htmlBuilder.append("<td nowrap>" + pd.getNoOfDays() + "</td>");
                htmlBuilder.append("<td nowrap>" + pd.getNote() + "</td>");
                htmlBuilder.append("</tr>");
            }

        }

        htmlBuilder.append("</td></tr></table>");
    }
    private static void printFindings(StringBuilder htmlBuilder,List<FindingsDetails> lstFindingsDetails){
        htmlBuilder.append("<table style='border-collapse: collapse;border:none; font-size: 10px;' width=100%> ");
        htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Findings</th></tr>");
        for(FindingsDetails fd:lstFindingsDetails) {
            htmlBuilder.append("<tr><td nowrap>"+fd.getFindings()+"</td></tr>");
        }
        htmlBuilder.append("</table>");
    }
private static void printComplainOf(StringBuilder htmlBuilder,List<ComplainDetails> lstComplainDetails){


    htmlBuilder.append("<table style='border-collapse: collapse;border:none;font-size: 10px;' width=100%>");
    htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Complain Of</th></tr>");
    for(ComplainDetails cd:lstComplainDetails) {
        htmlBuilder.append("<tr><td nowrap>"+cd.getComplain()+"</td></tr>");
    }
    htmlBuilder.append("</table>");

}

private  static void printDiagnosis(StringBuilder htmlBuilder,List<DiagnosisDetails> lstDiagnosisDetails){
    htmlBuilder.append("<table style='border-collapse: collapse;border:none; font-size: 10px;' width=100%>");
    htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Diagnosis</th></tr>");
    for(DiagnosisDetails dd:lstDiagnosisDetails) {
        htmlBuilder.append("<tr><td nowrap>"+dd.getDiagnosis()+"</td></tr>");
    }
    htmlBuilder.append("</table>");

}
private static void printPreviousHistory(StringBuilder htmlBuilder,List<PreviousHistoryDetails> lstPreviousHistoryDetails){
    htmlBuilder.append("<table style='border-collapse: collapse;border:none; font-size: 10px;' width=100%>");
    htmlBuilder.append("<tr style='border-top:1px solid black;border-bottom:1px solid black'><th nowrap style='text-align:left'>Previous History</th></tr>");
    for(PreviousHistoryDetails phd:lstPreviousHistoryDetails) {
        htmlBuilder.append("<tr><td nowrap>"+phd.getPrevious_history()+"</td></tr>");
    }
    htmlBuilder.append("</table>");
}
    private static  void printPatientDetails( StringBuilder htmlBuilder,PatientDetails patientDetails,String visitDate,String nextVisit ){
        htmlBuilder.append("<table border=0 style=' font-size: 12px;' width=100%> ");

        htmlBuilder.append("<tr><td nowrap style='width: 100px;'> <b>Patient's Id :</b></td><td  nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+(1000+patientDetails.getId())+"</td> <td nowrap style='width: 100px;'><b>Patient's Name :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 295px;'>"+patientDetails.getFirst_name()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap><b>Age :</b></td><td style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getAge()+"</td><td nowrap><b>Mobile Number :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getMobile_no()+"</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Weight:</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+patientDetails.getWeight()+" KG </td> <td nowrap align='left' ><b>Height :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getHeight()+" CM</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>BP :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getBp()+" mmHg</td><td nowrap align='left' ><b>Pulse :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+patientDetails.getPulse()+" per min</td></tr>");
        htmlBuilder.append("<tr><td nowrap align='left' ><b>Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;'>"+visitDate+"</td> <td nowrap align='left' ><b>Next Visit Date :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 100px;' >"+nextVisit+"</td></tr>");

        htmlBuilder.append("<tr><td nowrap align='left' ><b>Address :</b></td><td nowrap style='border-bottom: 1px solid #C0C0C0; width: 400px;' colspan='3'>"+patientDetails.getAddress()+"</td></tr>");


        htmlBuilder.append("</table>");
    }
    //6 parameters
    public static boolean createPrescription(String visitDate,String nextVisit,PatientDetails patientDetails,List<MedicineDetails> lstMedicineDetails,List<ComplainDetails> lstComplainDetails,List<PreviousHistoryDetails> lstPreviousHistoryDetails,List<FindingsDetails> lstFindingsDetails,List<DiagnosisDetails> lstDiagnosisDetails) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        boolean flag = false;
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Info Center</title></head>");
        htmlBuilder.append("<body width=100% border=0>");
        htmlBuilder.append("<table border=1 style=' font-size: 12px;' width=100%>");
        htmlBuilder.append("<tr><td colspan=4>");
        printPatientDetails(  htmlBuilder, patientDetails, visitDate,nextVisit);

        htmlBuilder.append("</td></tr>");
        htmlBuilder.append("<tr><td colspan=4>");
        ///print rx
        htmlBuilder.append("<img src='rx.png' width=20 height = 20 style='padding-top:50px; padding-bottom:10px'>");
        htmlBuilder.append("</td></tr>");
        htmlBuilder.append("<tr>");

        htmlBuilder.append("<td width=25%>");
        printComplainOf( htmlBuilder, lstComplainDetails);
        htmlBuilder.append("</td>");

        htmlBuilder.append("<td width=25%>");
        printPreviousHistory( htmlBuilder, lstPreviousHistoryDetails);
        htmlBuilder.append("</td>");

        htmlBuilder.append("<td width=25%>");
        printFindings( htmlBuilder, lstFindingsDetails);
        htmlBuilder.append("</td>");

        htmlBuilder.append("<td width=25%>");
        printDiagnosis( htmlBuilder, lstDiagnosisDetails);
        htmlBuilder.append("</td>");

        htmlBuilder.append("</tr>");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td width=100% >");
        printLung(htmlBuilder);
        htmlBuilder.append("</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td width=100% colspan=4>");
        printMedicineDetails( htmlBuilder,lstMedicineDetails);
        htmlBuilder.append("</td>");
        htmlBuilder.append("</tr>");
        htmlBuilder.append("</table>");

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
