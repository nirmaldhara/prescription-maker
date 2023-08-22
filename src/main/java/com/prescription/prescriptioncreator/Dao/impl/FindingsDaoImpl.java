package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.FindingsDao;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class FindingsDaoImpl implements FindingsDao {
    @Override
    public boolean addFindings(FindingsDetails findingsDetails) throws Exception {
        String sql = " insert into findings (findings) values (?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1,findingsDetails.getFindings());
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<FindingsDetails> getAutoSuggestFindings() throws Exception {
        String dbsql = "select `id`,`findings` from `findings`";
        List<FindingsDetails> lstFindingsDetails=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                FindingsDetails fd = new FindingsDetails();
                fd.setFindings(rs.getString("findings"));
                lstFindingsDetails.add(fd);
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
        return lstFindingsDetails;
    }

    @Override
    public List<FindingsDetails> addFindings(String findings) throws Exception {
        String dbsql = "select distinct  id ,findings from findings where findings = ?";
        List<FindingsDetails> findingsList = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,findings);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                FindingsDetails fd= new FindingsDetails();
                fd.setId(rs.getInt("id"));
                fd.setFindings(rs.getString("findings"));

                findingsList.add(fd);
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
        return findingsList;
    }
}
