
package com.simplilearn.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.util.DbUtils;

public class studentclass{
	
	private int id;
	private int section;
	public String Student;
	private String subject;
	private String time;
	
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	public static ArrayList<Integer> c_section = new ArrayList<Integer>();
	public static ArrayList <String>c_teacher = new ArrayList<String>();
	public static ArrayList <String>c_subject = new ArrayList<String>();
	public static ArrayList <String> times = new ArrayList<String>();
	
	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
			// create sql stmt
			String sql = "SELECT * FROM students WHERE class = " + classId;
			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");
				int aclass = myRs.getInt("class");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return students;

	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getStudent() {
		return Student;
	}

	public void setStudent(String student) {
		this.Student = student;
	}


}