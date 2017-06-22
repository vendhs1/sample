package com.jdbc.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MyClient {

	Connection con = null;
	ResultSet rs = null;
	Statement st = null;
	
	public void retrieveInfo(int stdId)
	{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/myDB";
			String uname = "userName";
			String pwd = "userPasssword";
			Connection con= DriverManager.getConnection(dbURL,uname,pwd);
		
			st = con.createStatement();
			
			String inputSQL = "SELECT * FROM students WHERE ID = "+stdId;

			// String inputSQL = "SELECT * FROM students";
			
			rs = st.executeQuery(inputSQL);
			
			while(rs.next())
			{
				System.out.print(rs.getInt(1)+"\t"+rs.getString(2));
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(st != null)
					st.close();
				if(con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		MyClient c = new MyClient();
		System.out.println("Enter student Id");
		Scanner sc = new Scanner(System.in);
		int std = sc.nextInt();
		c.retrieveInfo(std);
		System.out.println("<<<<<-------------------->>>>>>");
	}

	
}
