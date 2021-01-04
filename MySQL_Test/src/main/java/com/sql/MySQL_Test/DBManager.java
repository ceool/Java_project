package com.sql.MySQL_Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CREATE USER 'ceol'@'localhost' IDENTIFIED BY 'asd1234';
 * GRANT ALL ON test_1.* TO 'ceol'@'localhost' WITH GRANT OPTION;
 * 
 * ID: ceol
 * PW: asd1234
 * 
 * mysql 8.0 이상
 * 드라이버: com.mysql.cj.jdbc.Driver
 * url: 'serverTimezone=UTC' 필수
 * 
 * mysql 8.0 미만
 * 드라이버: com.mysql.jdbc.Driver
 */
public class DBManager {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
	private final String DB_URL = "jdbc:mysql://localhost/test_1?&serverTimezone=UTC&useSSL=false"; //접속할 DB 서버

	private final String USER_NAME = "ceol"; //DB에 접속할 사용자 이름을 상수로 정의
	private final String PASSWORD = "asd1234"; //사용자의 비밀번호를 상수로 정의

	public DBManager() {
		Connection conn = null;
		Statement state = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("[ MYSQL Connection ]\n");
			state = conn.createStatement();

			String sql;
			sql = "SELECT * FROM student";
			ResultSet rs = state.executeQuery(sql);

			while(rs.next()) {
				String number = rs.getString("_number");
				String name = rs.getString("name");
				int kor = Integer.parseInt(rs.getString("kor"));
				int math = Integer.parseInt(rs.getString("math"));
				int eng = Integer.parseInt(rs.getString("eng"));

				System.out.println("Number: " + number + "\nName: " + name + "\nKOR: " + kor);
				System.out.println("MATH: " + math + "\nENG: " + eng);
				System.out.println("평균: " + (float)(math+eng+kor)/3);
				System.out.println("--------------");
			}

			rs.close();
			state.close();
			conn.close();
		}
		catch(Exception e) {
			//예외 처리
			System.out.println("예외1: " + e);
		} 

		finally { //예외가 있든 없든 무조건 실행
			try 
			{
				if(state!=null)
					state.close();
			}
			catch(SQLException ex1)
			{
				System.out.println("예외2: " + ex1);
			}

			try
			{
				if(conn!=null)
					conn.close();
			}
			catch(SQLException ex1)
			{
				System.out.println("예외3: " + ex1);
			}
		}
		System.out.println("MYSQL Close");
	}
}
