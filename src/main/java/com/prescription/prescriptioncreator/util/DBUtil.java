package com.prescription.prescriptioncreator.util;

public class DBUtil {
    private static final String Patient_Details ="SELECT NAME,AGE,SEX,MOBILE,ADDRESS";

    public static void main(String[] args) {
        try{
           Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
       }catch(SQLException se){
           se.printStackTrace();
        }
    }
}
