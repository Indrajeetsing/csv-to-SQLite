# csv-to-SQLite
 This application import the CSV file, parse the data and insert to a SQLite In-Memory database.  

# Steps to compile and run the application:

1. In order to run this application you mush have java installed(I'm using 1.8).
2. Clone the project, extract in then go to csv-SQLite -> sqlite-tools-win32-x86-3250300 click on sqlite.exe and install sqlite you can do this from cmd as well.
3. Go to that directory in cmd and run sqlite3 it will start your sqlite.
4  Run the mainCsv.java file. 
5. Check console once data is stored in database it will display 'Csv has been copied to Database.'. Referesh the folder you will also see  MyLogFile.log file which contanis bad-data (the data which doesn't have column values).
6. Go to cmd where your sqlite is running type .open company.db  then .tables to see all the tables you sbould be seeing table X created there.
7. Now enter select * from X; It would display all the data. (Run .mode column to see data in form of columns)

#Tools I have Used to develop this application - 
- Windows 10
- Eclipse 2018-09 - https://www.eclipse.org/downloads/
- JAVA SE-1.8
- Sqlite sqlite-tools-win32-x86-3250300
- Libraries.jars 
  - SQlite-JDBC-3.23 - https://bitbucket.org/xerial/sqlite-jdbc/downloads/, 
  - Opencsv-4.3.2 - https://sourceforge.net/projects/opencsv/files/latest/download, 
  - commons-langs3-3.8 - https://commons.apache.org/proper/commons-lang/download_lang.cgi

