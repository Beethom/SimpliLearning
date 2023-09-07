package com.simplilearn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.simplilearn.util.DbUtils;

public class teacher {
	
	private int id;
	private String fname;
	private String lname;
	private int age;
	
	
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	public static ArrayList<String> f_name = new ArrayList<String>();
	public static ArrayList <String>l_name = new ArrayList<String>();
	public static ArrayList <Integer>Age = new ArrayList<Integer>();
	
	private Connection con;
	private PreparedStatement pstmt;
	private  ResultSet res;
	
	public teacher(int id, String fname, String lname, int age) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public teacher() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean insertData() {
		try {
		    pstmt = con.prepareStatement(DbUtils.insert_data_query);
			pstmt.setInt(1, this.id);
			pstmt.setString(2, this.fname);
			pstmt.setString(3, this.lname);
			pstmt.setInt(4, this.age);
		
			int x=pstmt.executeUpdate();
			if(x>0) {
				return true;
			}
			else {
				return false;
			}
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}

	   public ArrayList getTeacherList() {
		   try {
			    pstmt = con.prepareStatement(DbUtils.fetch_data_query);
			    
			    res = pstmt.executeQuery();
			    
			    Id.clear();
			    f_name.clear();
			    l_name.clear();
			    Age.clear();
			  
			    while(res.next()==true) {
			    	Id.add(res.getInt(1));
			    	f_name.add(res.getString(2));
			    	l_name.add(res.getString(3));
			    	Age.add(res.getInt(4));
			    	
			    }
				
				
					
				
				
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
			return Id; 
		
	}


}
