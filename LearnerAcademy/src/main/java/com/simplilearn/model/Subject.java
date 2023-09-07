package com.simplilearn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.simplilearn.util.DbUtils;

public class Subject {
	
	private int id;
	private String name;
	private String shortcut;
	
	
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	public static ArrayList<String> s_name = new ArrayList<String>();
	public static ArrayList <String>shortname = new ArrayList<String>();
	
	
	private Connection con;
	private PreparedStatement pstmt;
	private  ResultSet res;
	public Subject(int id, String name, String shortcut ) {
		super();
		this.id = id;
		this.name = name;
		this.shortcut = shortcut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Subject() {
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
			pstmt.setString(2, this.name);
			pstmt.setString(3, this.shortcut);
			
			
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

	   public ArrayList getSubjectList() {
		   try {
			    pstmt = con.prepareStatement(DbUtils.fetch_data_query);
			    
			    res = pstmt.executeQuery();
			    
			    Id.clear();
			    s_name.clear();
			    shortname.clear();
			    
			    
			    while(res.next()==true) {
			    	Id.add(res.getInt(1));
			    	s_name.add(res.getString(2));
			    	shortname.add(res.getString(3));
			    	
			    }
				
				
					
				
				
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
			return Id; 
		
	}

	

}
