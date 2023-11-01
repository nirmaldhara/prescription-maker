package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.P_ComplainDao;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class P_ComplainDaoImpl implements P_ComplainDao {
    private int getLastPrescriptionId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from prescription;";
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
    public void saveP_ComplainDao(List<ComplainDetails> lstComplainDetails, int complainId) throws Exception {
        String sql = " insert into p_complain_of (complain_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        int visit_id = getLastPrescriptionId();
        Connection conn=getConnection();
        for(ComplainDetails cd:lstComplainDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, complainId);
                preparedStmt.setInt(2, visit_id);
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
