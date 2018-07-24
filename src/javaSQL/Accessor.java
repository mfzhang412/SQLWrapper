package javaSQL;

import java.sql.*;

public class Accessor {
	   private final String DB_URL;
	   
	   private final String USERNAME;
	   private final String PASSWORD;
	   
	   private Connection conn;
	   private Statement stmt;
	   
	   private String jdbcDriver;
	   
	   public Accessor(String dbURL, String user, String pass) {
		   DB_URL = dbURL;
		   USERNAME = user;
		   PASSWORD = pass;
		   
		   this.conn = null;
		   this.stmt = null;
	   }
	   
	   public Accessor(String dbURL) {
		   DB_URL = dbURL;
		   USERNAME = "";
		   PASSWORD = "";
		   
		   this.conn = null;
		   this.stmt = null;
	   }
	   
	   public boolean execute(String command) {
		   try {
			   this.stmt.executeUpdate(command);
			   return true;
		   } catch(SQLException e) {
			   e.printStackTrace();
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
		   return false;
	   }
	   
	   public boolean open() {
		   try {
			   Class.forName(DB_URL);
			   this.conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			   this.stmt = conn.createStatement();
			   return true;
		   } catch(SQLException se) {
			   se.printStackTrace();
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
		   return false;
	   }
	   
	   public boolean close() {
		   try {
			   if (this.stmt != null) conn.close();
		   } catch(SQLException se) {
			   /* do nothing */
		   }
		   try {
			   if (this.conn != null) conn.close();
		   } catch(SQLException se) {
			   se.printStackTrace();
		   }
		   return true;
	   }
	   
	   public void setJdbcDriver(String s) {
		   this.jdbcDriver = s;
	   }
	   public String getJdbcDriver() {
		   return this.jdbcDriver;
	   }
	   public String getDbUrl() {
		   return this.DB_URL;
	   }
}
