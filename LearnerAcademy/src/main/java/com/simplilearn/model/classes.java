package com.simplilearn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.model.Student;
import com.simplilearn.util.DbUtils;

public class classes {
	
	private int id;
	private int section;
	private String teacher;
	private String subject;
	private String time;
	
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	public static ArrayList<Integer> c_section = new ArrayList<Integer>();
	public static ArrayList <String>c_teacher = new ArrayList<String>();
	public static ArrayList <String>c_subject = new ArrayList<String>();
	public static ArrayList <String> times = new ArrayList<String>();
	
	private Connection con;
	private PreparedStatement pstmt;
	private  ResultSet res;
	public classes(int id, int section, String teacher, String subject, String time) {
		super();
		this.id = id;
		this.section = section;
		this.teacher = teacher;
		this.subject = subject;
		this.time = time;
	}
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public classes() {
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
			pstmt.setInt(2, this.section);
			pstmt.setString(3, this.teacher);
			pstmt.setString(4, this.subject);
			pstmt.setString(5, this.time);
			
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

	   public ArrayList getClassList() {
		   try {
			    pstmt = con.prepareStatement(DbUtils.fetch_data_query);
			    
			    res = pstmt.executeQuery();
			    
			    Id.clear();
			    c_section.clear();
			    c_teacher.clear();
			    c_subject.clear();
			    times.clear();
			    
			    while(res.next()==true) {
			    	Id.add(res.getInt(1));
			    	c_section.add(res.getInt(2));
			    	c_teacher.add(res.getString(3));
			    	c_subject.add(res.getString(4));
			    	times.add(res.getString(5));
			    }
				
				
					
				
				
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
			return Id; 
		
	}
	   
	   public List<Student> loadClassStudents(int classId){
		   
		   List<Student> students = new ArrayList<>();
		   

			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
		   
		   //Statement myStmt = null;
		// get a connection
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
					
					
				

					// create sql stmt
					String sql = "SELECT * FROM students WHERE class = " + classId;
					myStmt = con.createStatement();

					// execute query
					res = myStmt.executeQuery(sql);

					// process result
					while (res.next()) {

						// retrieve data from result set row
						int id = res.getInt("id");
						String firstName = res.getString("fname");
						String lastName = res.getString("lname");
						int age = res.getInt("age");
						int aclass = res.getInt("class");

						// create new student object
						Student tempStudent = new Student(id, firstName, lastName, age, aclass);
						students.add(tempStudent);

					}
					
					}

				 catch (Exception e) {
					// TODO: handle exception
				} 
				return students;

			}
		   
	   }

	


