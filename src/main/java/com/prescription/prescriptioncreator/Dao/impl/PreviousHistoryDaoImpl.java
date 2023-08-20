package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PreviousHistoryDaoImpl implements PreviousHistoryDao {
    @Override
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception {
        String sql = " insert into previous_history (previous_history) values (?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1,previousHistoryDetails.getPrevious_history());
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception {
        String dbsql = "SELECT `id`,`previous_history` FROM `previous_history`";
        List<PreviousHistoryDetails> lstPreviousHistoryDetails=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                PreviousHistoryDetails pd= new PreviousHistoryDetails();
                pd.setPrevious_history(rs.getString("previous_history"));
                lstPreviousHistoryDetails.add(pd);
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
        return lstPreviousHistoryDetails;
    }
}
