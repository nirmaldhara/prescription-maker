package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.P_SuggestionsDao;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class P_SuggestionsDaoImpl implements P_SuggestionsDao {
    private int getLastSuggestionsId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from suggestions;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int suggestionsId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            suggestionsId=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return suggestionsId;
    }

    private int getLastPrescriptionId() throws Exception {
        String dbsql = "select IFNULL(max(id),0) id from prescription;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int visitIdPs=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            visitIdPs=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return visitIdPs;
    }

    @Override
    public void saveP_Suggestions(List<SuggestionsDetails> lstSuggestionsDetails, int suggestionsId) throws Exception {
        String sql = " insert into p_suggestions (suggestions_id, visit_id) values (?, ?)";
        //int suggestions_id = getLastSuggestionsId();
        int visit_id = getLastPrescriptionId();
        Connection conn=getConnection();
        for(SuggestionsDetails sd:lstSuggestionsDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, suggestionsId);
                preparedStmt.setInt(2, visit_id);
                preparedStmt.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
