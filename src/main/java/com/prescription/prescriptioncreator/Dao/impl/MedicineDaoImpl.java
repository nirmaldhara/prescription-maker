package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.MedicineDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class MedicineDaoImpl implements MedicineDao {
    /* @method addMedicine
     * @param medicineDetails
     * @throws Exception
     * @description Adding medicine details for the patients in medicine table
     * @developer Sukhendu
     */
    public  boolean addMedicine(MedicineDetails medicineDetails) throws Exception {
        String sql = " insert into medicine (medicine_name, dose1, dose2, dose3, dose4, dose5, dose6, when_bf_af, no_of_days, note) values (?, ?, ?, ?, ?,?,?,?,?,?)";
        Connection conn=getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, medicineDetails.getMedicineName());
            preparedStmt.setString (2, medicineDetails.getDose1());
            preparedStmt.setString (3, medicineDetails.getDose2());
            preparedStmt.setString(4,  medicineDetails.getDose3());
            preparedStmt.setString(5,  medicineDetails.getDose4());
            preparedStmt.setString(6,  medicineDetails.getDose5());
            preparedStmt.setString(7,  medicineDetails.getDose6());
            preparedStmt.setString(8,  medicineDetails.getWhen());
            preparedStmt.setInt(9,  medicineDetails.getNoOfDays());
            preparedStmt.setString(10,  medicineDetails.getNote());
            preparedStmt.execute();
            return  true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<MedicineDetails> getAutoSuggestMedicine() throws Exception {

        String dbsql = "SELECT `id`,`medicine_name`,`when_bf_af`,`no_of_days`,`dose1`,`dose2`,`dose3`,`dose4`,`dose5`,`dose6`,`note` FROM `medicine`";
        List<MedicineDetails> lstMedicineDetails=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                MedicineDetails md= new MedicineDetails();
                md.setId(rs.getLong("id"));
                md.setMedicineName(rs.getString("medicine_name"));
                md.setWhen(rs.getString("when_bf_af"));
                md.setNoOfDays(rs.getInt("no_of_days"));
                md.setDose1(rs.getString("dose1"));
                md.setDose2(rs.getString("dose2"));
                md.setDose3(rs.getString("dose3"));
                md.setDose4(rs.getString("dose4"));
                md.setDose5(rs.getString("dose5"));
                md.setDose6(rs.getString("dose6"));
                md.setNote(rs.getString("note"));
                lstMedicineDetails.add(md);
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
        return lstMedicineDetails;
    }
}
