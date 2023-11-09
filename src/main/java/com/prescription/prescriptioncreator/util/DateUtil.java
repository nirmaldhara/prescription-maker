package com.prescription.prescriptioncreator.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }

    public static final LocalDate NEXT_MONTH_DATE (){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }


    public static final String getAge(Date dob){
        //obtains an instance of LocalDate from a year, month and date
        LocalDate localDob =LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dob) );
//obtains the current date from the system clock
        LocalDate curDate = LocalDate.now();
//calculates the difference between two dates
        Period period = Period.between(localDob, curDate);
        String age=period.getYears() +"Y-"+period.getMonths()+"M-"+ period.getDays()+"D ";
        return age;
    }

}
