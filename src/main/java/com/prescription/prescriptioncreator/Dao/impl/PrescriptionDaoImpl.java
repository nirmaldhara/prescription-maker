package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;
import com.prescription.prescriptioncreator.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails,int patientId) throws Exception {



        String sql = " insert into prescription (visit_id, patient_id , medicine_name, when_bf_af, no_of_days,dose1,dose2,dose3,dose4,dose5,dose6,note) values (?, ?, ?, ?, ?,?,?,?, ?, ?, ?, ?)";
        Connection conn=getConnection();
        int visit_id=getLastVisitId();
        for(MedicineDetails pd:lstMedicineDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, visit_id);
                preparedStmt.setInt(2,patientId );
                preparedStmt.setString(3, pd.getMedicineName());
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
    }

    @Override
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception {
        String dbsql = "select  DATE_FORMAT(date,'%d/%m/%Y') AS visitdate  from prescription where patient_id=?  group by DATE_FORMAT(date,'%d/%m/%Y')";
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
                pv.setPreviousVisit(rs.getString("visitdate"));
                visitList.add(pv);

            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitList;
    }
}
