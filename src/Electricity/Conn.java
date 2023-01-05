package Electricity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
	Connection conn;
	Statement st;
	
	public Conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///ebs?autoReconnect=true&useSSL=false", "developer", "1234567");
			st = conn.createStatement();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
