package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.SuggestionsDao;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class SuggestionsDaoImpl implements SuggestionsDao {
    @Override
    public boolean addSuggestions(SuggestionsDetails suggestionsDetails) throws Exception {
        String sql = " insert into suggestions (suggestions) values (?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1,suggestionsDetails.getSuggestions());
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            return false;
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
    public List<SuggestionsDetails> addSuggestions(String suggestions) throws Exception {
        String dbsql = "select distinct  id ,suggestions from suggestions where suggestions = ?";
        List<SuggestionsDetails> suggestionsList = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,suggestions);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                SuggestionsDetails sd= new SuggestionsDetails();
                sd.setId(rs.getInt("id"));
                sd.setSuggestions(rs.getString("suggestions"));

                suggestionsList.add(sd);
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
        return suggestionsList;
    }
}
