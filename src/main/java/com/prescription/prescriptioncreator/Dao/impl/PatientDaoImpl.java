package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PatientDao;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.util.DBConnection;
import com.prescription.prescriptioncreator.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PatientDaoImpl implements PatientDao {

    public List<PatientDetails> searchPatientDetails(String mobile_no, int patient_id) throws Exception {
        String dbsql = "select distinct  id ,first_name, last_name, dob, sex, mobile_no,address from patient where mobile_no = ? or id = ? ";
        List<PatientDetails> paitentList=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,mobile_no);
            preparedStmt.setInt(2,patient_id-1000);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                PatientDetails pd= new PatientDetails();
                pd.setId(rs.getInt("id"));
                pd.setFirst_name(rs.getString("first_name"));
                pd.setLast_name(rs.getString("first_name"));
                pd.setDob(rs.getDate("dob"));
                pd.setAge(DateUtil.getAge(rs.getDate("dob")));
                pd.setSex(rs.getString("sex"));
                pd.setMobile_no(rs.getString("mobile_no"));
                pd.setPatientId(""+(1000+rs.getInt("id")));
                pd.setAddress(rs.getString("address"));
                paitentList.add(pd);
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
        return paitentList;
    }

@Override
    public  boolean addPatient(PatientDetails patientDetails) throws Exception {
        String sql = " insert into patient (first_name, last_name, dob, sex, mobile_no,patient_id,address) values (?, ?, ?, ?, ?,?,?)";
        Connection conn=getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, patientDetails.getFirst_name());
            preparedStmt.setString (2, patientDetails.getLast_name());
            preparedStmt.setDate (3, java.sql.Date.valueOf(patientDetails.getDob().toString()));
            preparedStmt.setString(4, patientDetails.getSex());
            preparedStmt.setString(5, patientDetails.getMobile_no());
            preparedStmt.setString(6, patientDetails.getPatientId());
            preparedStmt.setString(7, patientDetails.getAddress());
             preparedStmt.execute();
             return true;
        } catch (Exception e) {
            return false;

        }

    }
}
