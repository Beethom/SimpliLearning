package com.simplilearn.util;

public class DbUtils {
	
	public static String url ;
	public static String user ;
	public static String pwd ;
	public static String insert_data_query;
	public static String fetch_data_query;
	public static String fetch_specific_data_query;
	
	
static {
	url = "jdbc:mysql://localhost/administrative_portal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	user ="root";
	 pwd ="secretpassword";
	 //insert_data_query ="Insert into employee Values(?,?,?,?)";
	 //fetch_data_query = "Select * from employee";
	 //fetch_specific_data_query ="Select * from employee where empid=?";
}
}
