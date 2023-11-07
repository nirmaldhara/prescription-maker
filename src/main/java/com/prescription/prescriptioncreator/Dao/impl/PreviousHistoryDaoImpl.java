package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PreviousHistoryDaoImpl implements PreviousHistoryDao {
    @Override
    public long addPreviousHistory(String previous_history) throws Exception {
        String sql = " insert into previous_history (previous_history) values (?)";
        Connection conn = getConnection();
        PreparedStatement preparedStmt = null;
        ResultSet generatedKeys = null;
        ResultSet resultSet = null;
        long id = -1;
        try {
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, previous_history);
            try {
                preparedStmt.executeUpdate();

                generatedKeys = preparedStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            } catch (SQLIntegrityConstraintViolationException e) {

                String selectSql = "SELECT id FROM previous_history WHERE previous_history =  ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                selectStatement.setString(1, previous_history);
                resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            }


            System.out.println("previous history id=" + id);
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
    public List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception {
        String dbsql = "SELECT `id`,`previous_history` FROM `previous_history`";
        List<PreviousHistoryDetails> lstPreviousHistoryDetails = new ArrayList<>();
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try {
            preparedStmt = conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                PreviousHistoryDetails pd = new PreviousHistoryDetails();
                pd.setPrevious_history(rs.getString("previous_history"));
                lstPreviousHistoryDetails.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStmt != null)
                preparedStmt.close();
            if (rs != null)
                rs.close();

        }
        return lstPreviousHistoryDetails;
    }

    @Override
    public void savePreviousHistoryToPrescription(List<PreviousHistoryDetails> lstPreviousHistoryDetails, long visit_id) throws Exception {
        String sql = " insert into p_previous_history (previous_history_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        Connection conn = getConnection();
        for (PreviousHistoryDetails phd : lstPreviousHistoryDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1, phd.getId());
                preparedStmt.setLong(2, visit_id);
                preparedStmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<PreviousHistoryDetails> getPreviousHistoryOFDetails(long visitId) throws Exception {
        String dbsql = "SELECT p1.id,p1.previous_history FROM p_previous_history p, previous_history p1  where p.visit_id=? and p1.id=p.previous_history_id";
        List<PreviousHistoryDetails> lstPreviousHistoryDetails = new ArrayList<>();//previous_history,previous_history_id,visit_id
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;                                          //previous_history,p_previous_history
        Connection conn = getConnection();
        try {
            preparedStmt = conn.prepareStatement(dbsql);
            preparedStmt.setLong(1, visitId);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                PreviousHistoryDetails phd = new PreviousHistoryDetails();
                phd.setPrevious_history(rs.getString("previous_history"));
                phd.setId(rs.getInt("id"));
                lstPreviousHistoryDetails.add(phd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStmt != null)
                preparedStmt.close();
            if (rs != null)
                rs.close();

        }
        return lstPreviousHistoryDetails;
    }
}
