package test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regax {

	public static void main(String[] args) {
		//ACI_INSTITUTIONID=FABAE, ACI_CUST_ONBOARDED_DATE="SYSDATE", ACI_CUST_CODE=ACESIDTL111, ACI_LOCATIONID=DEFAULT, ACI_STATUS=1, INSTANCEID=PELICAN1, ACI_CUST_CTRY=INDIA, ACI_CUST_DOB=26-06-1999, ACI_CUST_TYPE=HI, ACI_DATE="SYSTIMESTAMP", ACI_USER_CURR=ADMIN1
	
//		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
//		hashMap.put("ACI_INSTITUTIONID", "FA B AE");
//		hashMap.put("ACI_CUST_ONBOARDED_DATE", "");
//		hashMap.put("ACI_CUST_CODE", "");
//		hashMap.put("ACI_LOCATIONID", "DEFAULT");
//		
//		
//		List<String> names = List.of("dip,roh q", "JAVACRAzy", "LOKESHGUPTAINDIA", "LOKESH123");
		
//		String regex = "\\d{2}-\\d{2}-\\d{4}";
//
//		Pattern pattern = Pattern.compile(regex);

//		for (var entry : hashMap.entrySet()) {
//		  Matcher matcher = pattern.matcher(entry.getValue());
//		  if(matcher.matches()) {
//		  System.out.println(entry.getKey()+" Is true");
//		  }
//		  else {
//			  System.out.println(entry.getKey()+" Is false");
//		  }
//		}
		
		
//		String regex = "[~$#*<>&']";
		//^(\\d{2}-\\d{2}-\\d{4})|(null)$"
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher("dipesh pingale*");
		if(matcher.matches()) {
			  System.out.println("invaild value");
		}
		else {
			System.out.println("vaild value");
		}
	}
}
