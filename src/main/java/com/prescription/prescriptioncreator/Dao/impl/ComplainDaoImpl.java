package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.ComplainDao;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class ComplainDaoImpl implements ComplainDao {

    @Override
    public long addComplain(String complain) throws Exception {
        String sql = " insert into complain (complain) values (?)";
        Connection conn=getConnection();
        PreparedStatement preparedStmt=null;
        ResultSet generatedKeys=null;
        ResultSet resultSet=null;
        long id=-1;
        try{
             preparedStmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1,complain);
            try {
                preparedStmt.executeUpdate();

                generatedKeys= preparedStmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }
            catch (SQLIntegrityConstraintViolationException e) {

                String selectSql = "SELECT id FROM complain WHERE complain =  ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                selectStatement.setString(1, complain);
                resultSet  = selectStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            }


            System.out.println("complain id="+id);
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
    public List<ComplainDetails> getAutoSuggestComplain() throws Exception {
        String dbsql = "SELECT `id`,`complain` FROM `complain`";
        List<ComplainDetails> lstComplainyDetails = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                ComplainDetails cd= new ComplainDetails();
                cd.setComplain(rs.getString("complain"));
                lstComplainyDetails.add(cd);
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
        return lstComplainyDetails;
    }

    @Override
    public void saveComplainToPrescription(List<ComplainDetails> lstComplainDetails, long visit_id) throws Exception {
        String sql = " insert into p_complain_of (complain_id, visit_id) values (?, ?)";
        //int previous_history_id = getLastPreviousHistoryId();
        Connection conn=getConnection();
        for(ComplainDetails cd:lstComplainDetails) {
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setLong(1, cd.getId());
                preparedStmt.setLong(2, visit_id);
                preparedStmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public  List<ComplainDetails>  getComplainOFDetails(long visitId) throws Exception{
        String dbsql = "SELECT p1.id,p1.complain FROM p_complain_of p, complain p1  where p.visit_id=? and p1.id=p.complain_id";
        List<ComplainDetails> lstComplainyDetails = new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            preparedStmt.setLong(1,visitId);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                ComplainDetails cd= new ComplainDetails();
                cd.setComplain(rs.getString("complain"));
                cd.setId(rs.getInt("id"));
                lstComplainyDetails.add(cd);
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
        return lstComplainyDetails;
    }

}
