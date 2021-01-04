package com.sql.MySQL_Test;

/**
 * CREATE USER 'ceol'@'localhost' IDENTIFIED BY 'asd1234';
 * GRANT ALL ON test_1.* TO 'ceol'@'localhost' WITH GRANT OPTION;
 * 
 * ID: ceol
 * PW: asd1234
 */
public class App {
	public static void main( String[] args ) {	
		DBManager db = new DBManager();
	}
}
