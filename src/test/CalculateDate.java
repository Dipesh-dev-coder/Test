package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CalculateDate {

	public static void main(String[] args) {
		
//		Instant startDate = java.time.Clock.systemUTC().instant();
//		System.out.println("startDate: " + startDate);
//		
//		ZonedDateTime currentDateTime = ZonedDateTime.now();
//
//        // Subtract 1000 days from current date
//		ZonedDateTime resultDateTime = currentDateTime.minusDays(1000);
//
//        // Define date format pattern to match the desired format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//        // Format the result date into the desired format
//        String resultDateString = resultDateTime.format(formatter);
//
//        System.out.println("Current Date: " + currentDateTime);
//        System.out.println("1000 Days Before: " + resultDateString);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date date = new Date();
        String todate = dateFormat.format(date);
        System.out.println("todate: " + todate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1000);
        Date todate1 = cal.getTime();    
        String fromdate = dateFormat.format(todate1);
        System.out.println("fromdate: " + fromdate);
	}

}
