package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.ComplainDao;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class ComplainDaoImpl implements ComplainDao {
    private int getLastComplainId() throws Exception {
        String dbsql = "select IFNULL(max(id),0)+1 id from complain;";
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        int complainId=0;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);
            rs = preparedStmt.executeQuery();
            rs.next();
            complainId=rs.getInt("id");

        } catch(Exception e){
            e.printStackTrace();
        }
        return complainId;
    }
    @Override
    public boolean addComplain(ComplainDetails complainDetails) throws Exception {
        String sql = " insert into complain (id,complain) values (?,?)";
        Connection conn=getConnection();
        int id = getLastComplainId();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1,id);
            preparedStmt.setString(2,complainDetails.getComplain());
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            return false;
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
    public List<ComplainDetails> addComplain(String complain) throws Exception {
        String dbsql = "select distinct  id ,complain from complain where previous_history = ?";
        List<ComplainDetails> complainList=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,complain);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                ComplainDetails cd= new ComplainDetails();
                cd.setId(rs.getInt("id"));
                cd.setComplain(rs.getString("complain"));

                complainList.add(cd);
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
        return complainList;
    }
}
