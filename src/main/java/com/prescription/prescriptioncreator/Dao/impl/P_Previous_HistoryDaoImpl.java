package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.P_Previous_HistoryDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class P_Previous_HistoryDaoImpl implements P_Previous_HistoryDao {

    private int getLastPreviousHistoryId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from previous_history;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int previousHistoryId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            previousHistoryId=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return previousHistoryId;
    }
    private int getLastPrescriptionId() throws Exception {
        String dbsql = "select IFNULL(max(id),0)+1 id from prescription;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int visitId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            visitId=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitId;
    }
    @Override
    public void saveP_Previous_HistoryDao(int previousHistoryId, int visitId) throws Exception {
        String sql = " insert into p_previous_history (previous_history_id, visit_id) values (?, ?)";
        int previous_history_id = getLastPreviousHistoryId();
        int visit_id = getLastPrescriptionId();
        Connection conn=getConnection();
        try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, previous_history_id);
                preparedStmt.setInt(2,visit_id );
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
