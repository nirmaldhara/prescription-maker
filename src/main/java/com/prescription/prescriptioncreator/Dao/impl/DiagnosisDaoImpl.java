package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.DiagnosisDao;
import com.prescription.prescriptioncreator.model.DiagnosisDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class DiagnosisDaoImpl implements DiagnosisDao {
    @Override
    public long addDiagnosis(String diagnosis) throws Exception {
        String sql = " insert into diagnosis (diagnosis) values (?)";
        Connection conn = getConnection();
        PreparedStatement preparedStmt = null;
        ResultSet generatedKeys = null;
        ResultSet resultSet = null;
        long id = -1;
        try {
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, diagnosis);
            try {
                preparedStmt.executeUpdate();

                generatedKeys = preparedStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            } catch (SQLIntegrityConstraintViolationException e) {

                String selectSql = "SELECT id FROM diagnosis WHERE diagnosis =  ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                selectStatement.setString(1, diagnosis);
                resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            }


            System.out.println("diagnosis id=" + id);
            return id;
        } catch (Exception e) {
            return -1;
        } finally {
            if (preparedStmt != null) preparedStmt.close();
            if (generatedKeys != null) generatedKeys.close();
            if (resultSet != null) resultSet.close();
        }

    }

    @Override
    public List<DiagnosisDetails> getAutoSuggestDiagnosis() throws Exception {
        String dbsql = "select `id`,`diagnosis` from `diagnosis`";
        List<DiagnosisDetails> lstDiagnosisDetails = new ArrayList<>();
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try {
            preparedStmt = conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                DiagnosisDetails sd = new DiagnosisDetails();
                sd.setDiagnosis(rs.getString("diagnosis"));
                lstDiagnosisDetails.add(sd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStmt != null)
                preparedStmt.close();
            if (rs != null)
                rs.close();

        }
        return lstDiagnosisDetails;
    }

    @Override
    public void saveDiagnosisToPrescription(List<DiagnosisDetails> lstDiagnosisDetails, long visit_id) throws Exception {
        String sql = " insert into p_diagnosis (diagnosis_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        Connection conn = getConnection();
        for (DiagnosisDetails dd : lstDiagnosisDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1, dd.getId());
                preparedStmt.setLong(2, visit_id);
                preparedStmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<DiagnosisDetails> getDiagnosisOFDetails(long visitId) throws Exception {
        String dbsql = "SELECT p1.id,p1.diagnosis FROM p_diagnosis p, diagnosis p1  where p.visit_id=? and p1.id=p.diagnosis_id";
        List<DiagnosisDetails> lstDiagnosisDetails = new ArrayList<>();
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try {
            preparedStmt = conn.prepareStatement(dbsql);
            preparedStmt.setLong(1, visitId);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                DiagnosisDetails dd = new DiagnosisDetails();
                dd.setDiagnosis(rs.getString("diagnosis"));
                dd.setId(rs.getInt("id"));
                lstDiagnosisDetails.add(dd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStmt != null)
                preparedStmt.close();
            if (rs != null)
                rs.close();

        }
        return lstDiagnosisDetails;
    }

}
