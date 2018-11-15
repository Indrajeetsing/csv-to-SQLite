package com.ms3.myApp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.opencsv.CSVReader;
/**
 * Author: Indrajeet mandloi
 * **/
public class mainCsv
{
	public static void main(String[] args)
	{
			loadCsv();
	}

	 	Connection conn=null;
	 	// Connection with Database
		public static Connection dbConnector() {
			 try {
				 Class.forName("org.sqlite.JDBC");
				 Connection conn =  DriverManager.getConnection("JDBC:sqlite:C:\\Users\\imandloi\\Desktop\\SQLite\\sqlite-tools-win32-x86-3250300\\company.db");
				 return conn;
			 } catch(Exception e) {
				 return null;
			 }
		 }
	private static void loadCsv()
	{

		try (CSVReader reader = new CSVReader(new FileReader("ms3Interview.csv"), ','); )
		{
				String sql = "Insert into X (A, B, C, D, E, F, G, H, I, J) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = mainCsv.dbConnector().prepareStatement(sql);
				String[] rowData = null;
				int i = 0;
				while((rowData = reader.readNext()) != null)
				{
					System.out.println(rowData);
					for (String data : rowData)
					{
							ps.setString((i % 10) + 1, data);
							System.out.println(i);
							System.out.println(data);
							if (++i % 10 == 0)// adding batch after every 10 c0lumns/ 1 row
									ps.addBatch();

							if (i % 1000 == 0)// inserting when we have 100 rows
									ps.executeBatch();
					}
					
				}
				ps.executeBatch();
				System.out.println("Csv has been copied to Database.");
		}
		catch (Exception e)
		{
				e.printStackTrace();
		}

	}

}

