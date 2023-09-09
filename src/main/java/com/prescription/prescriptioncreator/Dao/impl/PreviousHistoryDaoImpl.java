package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PreviousHistoryDaoImpl implements PreviousHistoryDao {
    /* @method addPreviousHistory
     * @param previousHistoryDetails
     * @throws Exception
     * @description add Previous History data
     * @developer Sukhendu
     */

    /* @method getAutoSuggestPreviousHistory
     * @throws Exception
     * @description to get auto suggestion
     * @developer Sukhendu
     */
    /* @method addPreviousHistory
     *@param previous_history
     * @throws Exception
     * @description to get tableview of Previous History
     * @developer Sukhendu
     */
    private int getLastPreviousHistoryId() throws Exception {
        String dbsql = "select IFNULL(max(id),0)+1 id from previous_history;";
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
    @Override
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception {
        String sql = " insert into previous_history (id,previous_history) values (?,?)";
        Connection conn=getConnection();
        int id = getLastPreviousHistoryId();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1,id);
            preparedStmt.setString(2,previousHistoryDetails.getPrevious_history());
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

    @Override
    public List<PreviousHistoryDetails> addPreviousHistory(String previous_history) throws Exception {
        String dbsql = "select distinct  id ,previous_history from previous_history where previous_history = ?";
        List<PreviousHistoryDetails> previousHistoryList=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,previous_history);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                PreviousHistoryDetails pd= new PreviousHistoryDetails();
                pd.setId(rs.getInt("id"));
                pd.setPrevious_history(rs.getString("previous_history"));

                previousHistoryList.add(pd);
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
        return previousHistoryList;
    }
}
