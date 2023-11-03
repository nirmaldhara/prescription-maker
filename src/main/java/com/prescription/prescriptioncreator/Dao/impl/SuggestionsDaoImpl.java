package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.SuggestionsDao;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class SuggestionsDaoImpl implements SuggestionsDao {
    @Override
    public long addSuggestions(String suggestions) throws Exception {
        String sql = " insert into suggestions (suggestions) values (?)";
        Connection conn=getConnection();
        PreparedStatement preparedStmt=null;
        ResultSet generatedKeys=null;
        ResultSet resultSet=null;
        long id=-1;
        try{
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1,suggestions);
            try {
                preparedStmt.executeUpdate();

                generatedKeys= preparedStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }
            catch (SQLIntegrityConstraintViolationException e) {

                String selectSql = "SELECT id FROM suggestions WHERE suggestions =  ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                selectStatement.setString(1, suggestions);
                resultSet  = selectStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            }


            System.out.println("suggestions id="+id);
            return id;
        }
        catch(Exception e){
            return -1;
        }
        finally {
            if  (preparedStmt!=null) preparedStmt.close();
            if  (generatedKeys!=null)generatedKeys.close();
            if  (resultSet!=null)resultSet.close();
        }

    }

    @Override
    public List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception {
        String dbsql = "select `id`,`suggestions` from `suggestions`";
        List<SuggestionsDetails> lstSuggestionsDetails=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                SuggestionsDetails sd = new SuggestionsDetails();
                sd.setSuggestions(rs.getString("suggestions"));
                lstSuggestionsDetails.add(sd);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(preparedStmt!=null)
                preparedStmt.close();
            if(rs!=null)
                rs.close();

        }
        return lstSuggestionsDetails;
    }

    @Override
    public void saveSuggestionsToPrescription(List<SuggestionsDetails> lstSuggestionsDetails, long visit_id) throws Exception {
        String sql = " insert into p_suggestions (suggestions_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        Connection conn=getConnection();
        for(SuggestionsDetails sd:lstSuggestionsDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1, sd.getId());
                preparedStmt.setLong(2, visit_id);
                preparedStmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<SuggestionsDetails> getSuggestionsOFDetails(long visitId) throws Exception {
        String dbsql = "SELECT p1.id,p1.suggestions FROM p_suggestions p, suggestions p1  where p.visit_id=? and p1.id=p.suggestions_id";
        List<SuggestionsDetails> lstSuggestionsDetails = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            preparedStmt.setLong(1,visitId);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                SuggestionsDetails sd= new SuggestionsDetails();
                sd.setSuggestions(rs.getString("suggestions"));
                sd.setId(rs.getInt("id"));
                lstSuggestionsDetails.add(sd);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(preparedStmt!=null)
                preparedStmt.close();
            if(rs!=null)
                rs.close();

        }
        return lstSuggestionsDetails;
    }

}
