package com.simplilearn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.simplilearn.model.Student;
import com.simplilearn.model.Subject;
import com.simplilearn.model.teacher;
import com.simplilearn.util.DbUtils;
import com.simplilearn.model.classes;

public class DbRetrieve {

	private Connection con;
	private PreparedStatement pstmt;
	private  ResultSet res;
	
	public void connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();
		
		

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
			// get a connection
			
			
			

			// create sql stmt
			String sql = "SELECT * FROM students";
			myStmt = con.createStatement();

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

				// add it to the list of students
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

	public List<teacher> getTeachers() {

		List<teacher> teachers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);

			// get a connection
			

			// create sql stmt
			String sql = "SELECT * FROM teachers";
			myStmt = con.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");

				// create new student object
				teacher temp = new teacher(id, firstName, lastName, age);

				// add it to the list of students
				teachers.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return teachers;

	}

	public List<Subject> getSubjects() {

		List<Subject> subjects = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);

			// create sql stmt
			String sql = "SELECT * FROM subjects";
			myStmt = con.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String shortcut = myRs.getString("shortcut");

				// create new student object
				Subject temp = new Subject(id, name,shortcut);

				// add it to the list of students
				subjects.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return subjects;

	}

	public List<classes> getClasses() {

		List<classes> classes = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
			// create sql stmt
			String sql = "SELECT * FROM classes";
			myStmt = con.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				int section = myRs.getInt("section");
				int subject = myRs.getInt("subject");
				int teacher = myRs.getInt("teacher");
				String time = myRs.getString("time");

				teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

				// create new student object
				classes temp = new classes(id, section, teacher_name, tempSubject.getName(), time);

				// add it to the list of students
				classes.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return classes;

	}

	public teacher loadTeacher(int teacherId) {

		teacher theTeacher = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);

			// create sql stmt
			String sql = "SELECT * FROM teachers WHERE id = " + teacherId;
			myStmt = con.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String fname = myRs.getString("fname");
				String lname = myRs.getString("lname");
				int age = myRs.getInt("age");
				theTeacher = new teacher(id, fname, lname, age);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theTeacher;

	}

	public Subject loadSubject(int subjectId) {

		Subject theSubject = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
			// create sql stmt
			String sql = "SELECT * FROM subjects WHERE id = " + subjectId;
			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String shortcut = myRs.getString("shortcut");

				theSubject = new Subject(id, name,shortcut);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theSubject;

	}

	public classes loadClass(int classId) {

		classes theClass = null;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);

			// get a connection
			

			// create sql stmt
			String sql = "SELECT * FROM clasess WHERE id = " + classId;
			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				int section = myRs.getInt("section");
				int subject = myRs.getInt("subject");
				int teacher = myRs.getInt("teacher");
				String time = myRs.getString("time");

				teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return theClass;

	}

	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;


			// get a connection
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				myConn = DriverManager.getConnection(DbUtils.url, DbUtils.user, DbUtils.pwd);
			
			
		

			// create sql stmt
			String sql = "SELECT * FROM students WHERE class = " + classId;
			myStmt = con.createStatement();

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

			}}

		 catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(con, pstmt, res);
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
	}


