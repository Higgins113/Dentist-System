package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:pdb;create = true";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection cons = null;
		Class.forName(DRIVER);
		
		cons = DriverManager.getConnection(JDBC_URL);
		cons.createStatement().execute("Drop table Procedures");
		cons.createStatement().execute("Drop table Patients");
		cons.createStatement().execute("create table PATIENTS (patientID INTEGER,Name varchar(20),Address varchar(20),PhoneNo varchar(20))");
		cons.createStatement().execute("create table PROCEDURES (procedureID INTEGER,Name varchar(20),Cost float(20))");

		cons.close();
		
	}
	
	public void insert(String SQL) throws ClassNotFoundException, SQLException
	{
		String s=SQL;
		Connection cons = null;
		Class.forName(DRIVER);
		
		cons = DriverManager.getConnection(JDBC_URL);
		cons.createStatement().execute(SQL);

	}
	
	public void check	(String SQL) throws ClassNotFoundException, SQLException
	{
		String s=SQL;
		Connection cons = null;
		Class.forName(DRIVER);
		
		cons = DriverManager.getConnection(JDBC_URL);
		cons.createStatement().execute(SQL);

	}
	
	public void delete(String SQL) throws ClassNotFoundException, SQLException
	{
		String s=SQL;
		System.out.print(s);
		Connection cons = null;
		Class.forName(DRIVER);
		
		cons = DriverManager.getConnection(JDBC_URL);
		cons.createStatement().execute(SQL);

	}

}
