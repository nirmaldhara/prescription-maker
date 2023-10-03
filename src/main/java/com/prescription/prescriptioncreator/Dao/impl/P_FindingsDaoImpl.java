package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.P_FindingsDao;
import com.prescription.prescriptioncreator.model.FindingsDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class P_FindingsDaoImpl implements P_FindingsDao {
    private int getLastFindingsId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from findings;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int findingsId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            findingsId=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return findingsId;
    }

    private int getLastPrescriptionId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from prescription;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int visitIdP=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            visitIdP=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitIdP;
    }
    @Override
    public void saveP_Findings(List<FindingsDetails> lstFindingsDetails, int findingsId) throws Exception {
        String sql = " insert into p_findings (findings_id, visit_id) values (?, ?)";
        //int findings_id = getLastFindingsId();
        int visit_id = getLastPrescriptionId();
        Connection conn=getConnection();
        for(FindingsDetails fd:lstFindingsDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, findingsId);
                preparedStmt.setInt(2, visit_id);
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
