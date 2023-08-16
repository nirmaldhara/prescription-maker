package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
