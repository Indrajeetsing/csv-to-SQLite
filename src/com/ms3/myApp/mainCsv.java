package com.ms3.myApp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
				 Connection conn =  DriverManager.getConnection("JDBC:sqlite:sqlite-tools-win32-x86-3250300\\company.db");
				 return conn;
			 } catch(Exception e) {
				 return null;
			 }
		 }
	private static void loadCsv()
	{

		try (CSVReader reader = new CSVReader(new FileReader("ms3Interview.csv"), ','); )
		{
			Logger logger = Logger.getLogger("MyLog");  
		    FileHandler fh; 
			fh = new FileHandler("MyLogFile.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
				String sql = "Insert into X (A, B, C, D, E, F, G, H, I, J) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = mainCsv.dbConnector().prepareStatement(sql);
				String[] rowData = null;
				int i = 0;
				 
				while((rowData = reader.readNext()) != null)
				{
					int dataIndex = 0;
					for (String data : rowData)
					{
						if (dataIndex < 10) {
							ps.setString((i % 10) + 1, data);
							if (++i % 10 == 0)// adding batch after every 10 c0lumns/ 1 row
								ps.addBatch();

							if (i % 1000 == 0)// inserting when we have 100 rows
								ps.executeBatch();
						} else {
							logger.info("Not found matching column for " + data);  
						}
							
							dataIndex++;
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

