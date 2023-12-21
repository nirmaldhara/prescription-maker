package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.model.*;
import com.prescription.prescriptioncreator.util.DBConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PrescriptionDaoImpl implements PrescriptionDao {

    private int getLastVisitId() throws Exception {
        String dbsql = "select IFNULL(max(visit_id),0)+1 visit_id from prescription;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int visitId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            visitId=rs.getInt("visit_id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitId;
    }

    @Override
    public long saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails,int patientId) throws Exception {
        String sql = "insert into prescription (visit_id, patient_id , medicine_id, when_bf_af, no_of_days,dose1,dose2,dose3,dose4,dose5,dose6,note) values (?, ?, ?, ?, ?,?,?,?, ?, ?, ?, ?)";
        Connection conn=getConnection();
        int visit_id=getLastVisitId();
        for(MedicineDetails pd:lstMedicineDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, visit_id);
                preparedStmt.setInt(2,patientId );
                preparedStmt.setLong(3, pd.getMedicineID());
                preparedStmt.setString(4, pd.getWhen());
                preparedStmt.setInt(5, pd.getNoOfDays());
                preparedStmt.setString(6,pd.getDose1());
                preparedStmt.setString(7,pd.getDose2());
                preparedStmt.setString(8,pd.getDose3());
                preparedStmt.setString(9,pd.getDose4());
                preparedStmt.setString(10,pd.getDose5());
                preparedStmt.setString(11,pd.getDose6());
                preparedStmt.setString(12, pd.getNote());
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return visit_id;
    }

    @Override
    public List<MedicineDetails> getPrescriptionDetailsByVisitId(Integer visitID) throws Exception {
        String dbsql = "select m.medicine_name,visit_id, patient_id , medicine_id, p.when_bf_af, p.no_of_days,p.dose1,p.dose2,p.dose3,p.dose4,p.dose5,p.dose6,p.note from prescription p, medicine m where visit_id=? and m.id=p.medicine_id";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<MedicineDetails>lstPrescription = new ArrayList<>();

        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            preparedStmt.setInt(1,visitID.intValue());
            rs = preparedStmt.executeQuery();
            while(  rs.next())
            {
                MedicineDetails medicineDetails =  new MedicineDetails();
                medicineDetails.setMedicineID(rs.getLong("medicine_id"));
                medicineDetails.setMedicineName(rs.getString("medicine_name"));
                medicineDetails.setWhen(rs.getString("when_bf_af"));
                medicineDetails.setNoOfDays(rs.getInt("no_of_days"));
                medicineDetails.setDose1(rs.getString("dose1"));
                medicineDetails.setDose2(rs.getString("dose2"));
                medicineDetails.setDose3(rs.getString("dose3"));
                medicineDetails.setDose4(rs.getString("dose4"));
                medicineDetails.setDose5(rs.getString("dose5"));
                medicineDetails.setDose6(rs.getString("dose6"));
                medicineDetails.setNote(rs.getString("note"));
                lstPrescription.add(medicineDetails);

            }


        } catch(Exception e){
            e.printStackTrace();
        }
        return lstPrescription;
    }

    @Override
    public long saveVisitHistory(long patient_id,long visit_id, Date visitDate, Date nextVisitDate, float weight, float height,String bp, float pulse) throws Exception {
         String sql = "insert into visit_history (patient_id,visit_id, visit_date , next_visit, weight, height, bp, pulse) values (?, ?, ?, ?, ?,?,?,?)";
        Connection conn=getConnection();
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1,patient_id);
                preparedStmt.setLong(2, visit_id);
                preparedStmt.setDate(3,visitDate );
                preparedStmt.setDate(4, nextVisitDate);
                preparedStmt.setFloat(5, weight);
                preparedStmt.setFloat(6,height);
                preparedStmt.setString(7,bp);
                preparedStmt.setFloat(8,pulse);
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return 0;
    }

    @Override
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception {
        String dbsql = "select  DATE_FORMAT(visit_date,'%d/%m/%Y') AS visit_date, visit_id, DATE_FORMAT(next_visit,'%d/%m/%Y') AS next_visit, weight, height, bp, pulse from visit_history where patient_id=?";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int visitId=0;
        Connection conn = getConnection();
        List<PreviousVisit> visitList= new ArrayList<>();

        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            preparedStmt.setInt(1,patientId);
            rs = preparedStmt.executeQuery();
            while(rs.next()) {
                PreviousVisit pv= new PreviousVisit();
                pv.setPreviousVisit(rs.getString("visit_date"));
                pv.setNextVisit(rs.getString("next_visit"));
                pv.setWeight(rs.getFloat("weight"));
                pv.setHeight(rs.getFloat("height"));
                pv.setBp(rs.getString("bp"));
                pv.setPulse(rs.getFloat("pulse"));
           //     pv.setId(rs.getInt("id"));
                pv.setVisitId(rs.getInt("visit_id"));
                visitList.add(pv);

            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitList;
    }

    @Override
    public long saveLungsHistory(long visit_id, String lungs_point1, String lungs_point2, String lungs_point3, String lungs_point4, String lungs_point5, String lungs_point6) throws Exception {
        String sql = " insert into lungs (visit_id, lungs_point1, lungs_point2, lungs_point3, lungs_point4, lungs_point5, lungs_point6) values (?, ?, ?, ?, ?,?,?)";
        Connection conn=getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setLong(1, visit_id);
            preparedStmt.setString(2, lungs_point1);
            preparedStmt.setString(3, lungs_point2);
            preparedStmt.setString(4, lungs_point3);
            preparedStmt.setString(5, lungs_point4);
            preparedStmt.setString(6, lungs_point5);
            preparedStmt.setString(7, lungs_point6);
            preparedStmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
   @Override
   public LungsDetails getLungsDetails(int visitId) throws Exception {
       String dbsql = "SELECT `lungs_point1`,`lungs_point2`,`lungs_point3`,`lungs_point4`,`lungs_point5`,`lungs_point6` FROM `lungs` WHERE visit_id=?";
       PreparedStatement preparedStatement = null;
       ResultSet rs = null;
       Connection conn = getConnection();
       LungsDetails lungsDetails = null;
           try {
               preparedStatement= conn.prepareStatement(dbsql);
               preparedStatement.setInt(1,visitId);
               rs = preparedStatement.executeQuery();
               while (rs.next()) {
                   lungsDetails = new LungsDetails();
                   lungsDetails.setLungs_point1(rs.getString("lungs_point1"));
                   lungsDetails.setLungs_point2(rs.getString("lungs_point2"));
                   lungsDetails.setLungs_point3(rs.getString("lungs_point3"));
                   lungsDetails.setLungs_point4(rs.getString("lungs_point4"));
                   lungsDetails.setLungs_point5(rs.getString("lungs_point5"));
                   lungsDetails.setLungs_point6(rs.getString("lungs_point6"));
               }
           }
        catch (Exception e) {
           e.printStackTrace();
           throw e;
       }

       return lungsDetails;
   }

    @Override
    public long saveAbdomenHistory(long visit_id, String abdomen_point1, String abdomen_point2, String abdomen_point3, String abdomen_point4, String abdomen_point5, String abdomen_point6, String abdomen_point7, String abdomen_point8, String abdomen_point9) throws Exception {
        String sql = " insert into abdomen (visit_id,abdomen_point1,abdomen_point2,abdomen_point3,abdomen_point4,abdomen_point5,abdomen_point6,abdomen_point7,abdomen_point8,abdomen_point9) values (?,?,?,?,?,?,?,?,?,?)";
        Connection con = getConnection();
        try{
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setLong(1,visit_id);
            preparedStmt.setString(2,abdomen_point1);
            preparedStmt.setString(3,abdomen_point2);
            preparedStmt.setString(4,abdomen_point3);
            preparedStmt.setString(5,abdomen_point4);
            preparedStmt.setString(6,abdomen_point5);
            preparedStmt.setString(7,abdomen_point6);
            preparedStmt.setString(8,abdomen_point7);
            preparedStmt.setString(9,abdomen_point8);
            preparedStmt.setString(10,abdomen_point9);
            preparedStmt.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public AbdomenDetails getAbdomenDetails(int visitId) throws Exception {
        String dbsql = "SELECT `abdomen_point1`,`abdomen_point2`,`abdomen_point3`,`abdomen_point4`,`abdomen_point5`,`abdomen_point6`,`abdomen_point7`,`abdomen_point8`,`abdomen_point9` FROM `abdomen` WHERE visit_id=?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        AbdomenDetails abdomenDetails = null;
        try {
            preparedStatement= conn.prepareStatement(dbsql);
            preparedStatement.setInt(1,visitId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                abdomenDetails = new AbdomenDetails();
                abdomenDetails.setAbdomen_point1(rs.getString("abdomen_point1"));
                abdomenDetails.setAbdomen_point2(rs.getString("abdomen_point2"));
                abdomenDetails.setAbdomen_point3(rs.getString("abdomen_point3"));
                abdomenDetails.setAbdomen_point4(rs.getString("abdomen_point4"));
                abdomenDetails.setAbdomen_point5(rs.getString("abdomen_point5"));
                abdomenDetails.setAbdomen_point6(rs.getString("abdomen_point6"));
                abdomenDetails.setAbdomen_point7(rs.getString("abdomen_point7"));
                abdomenDetails.setAbdomen_point8(rs.getString("abdomen_point8"));
                abdomenDetails.setAbdomen_point9(rs.getString("abdomen_point9"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return abdomenDetails;
    }

}
