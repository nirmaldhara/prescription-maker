package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.FindingsDao;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class FindingsDaoImpl implements FindingsDao {
    @Override
    public long addFindings(String findings) throws Exception {
        String sql = " insert into findings (findings) values (?)";
        Connection conn=getConnection();
        PreparedStatement preparedStmt=null;
        ResultSet generatedKeys=null;
        ResultSet resultSet=null;
        long id=-1;
        try{
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1,findings);
            try {
                preparedStmt.executeUpdate();

                generatedKeys= preparedStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }
            catch (SQLIntegrityConstraintViolationException e) {

                String selectSql = "SELECT id FROM findings WHERE findings =  ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                selectStatement.setString(1, findings);
                resultSet  = selectStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            }


            System.out.println("findings id="+id);
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
    public void saveFindingsToPrescription(List<FindingsDetails> lstFindingsDetails, long visit_id) throws Exception {
        String sql = " insert into p_findings (findings_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        Connection conn=getConnection();
        for(FindingsDetails fd:lstFindingsDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1, fd.getId());
                preparedStmt.setLong(2, visit_id);
                preparedStmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<FindingsDetails> getFindingsOFDetails(long visitId) throws Exception {
        String dbsql = "SELECT p1.id,p1.findings FROM p_findings p, findings p1  where p.visit_id=? and p1.id=p.findings_id";
        List<FindingsDetails> lstFindingsDetails = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            preparedStmt.setLong(1,visitId);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                FindingsDetails fd= new FindingsDetails();
                fd.setFindings(rs.getString("findings"));
                fd.setId(rs.getInt("id"));
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

}
