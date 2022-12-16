package tst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Optimization {
	private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
}

public static void main(String[] args) throws Exception, IOException{
	String jdbcUrl = "jdbc:mysql://localhost:3306/eqdb";
	String username = "root";
	String password="admin";
	List<String> files = new ArrayList<String>();
	List<String> Year = new ArrayList<String>();
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1964.txt");	//0
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1965.txt");	//1
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1966.txt");	//2
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1967.txt");	//3
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1968.txt");	//4
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1969.txt");	//5
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1970.txt");	//6
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1971.txt");	//7
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1972.txt");	//8
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1973.txt");	//9
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1974.txt");	//10
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1975.txt");	//11
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1976.txt");	//12
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1977.txt");	//13
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1978.txt");	//14
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1979.txt");	//14
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/cat1980.txt");	//15
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1981.TXT");	//16
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1982.TXT");	//17
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1983.TXT");	//18
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1984.TXT");	//19
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1985.TXT");	//20
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1986.TXT");	//21
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1987.TXT");	//22
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1988.TXT");	//23
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1989.TXT");	//24
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1990.TXT");	//25
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1991.TXT");	//26
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1992.TXT");	//27
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1993.TXT");	//28
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1994.TXT");	//29
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1995.TXT");	//30
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1996.TXT");	//31
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1997.TXT");	//32
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1998.TXT");	//33
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT1999.TXT");	//34
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2000.TXT");	//35
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2001.TXT");	//36
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2002.TXT");	//37
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2003.TXT");	//38
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2004.TXT");	//39
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2005.TXT");	//40
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2006.TXT");	//41
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2007.TXT");	//42
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2008.TXT");	//43
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2009.TXT");	//44
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2010.TXT");	//45
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2011.TXT");	//46
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2012.TXT");	//47
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2013.TXT");	//48
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2014.TXT");	//49
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2015.TXT");	//50
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2016.TXT");	//51
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2017.TXT");	//52
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2018.TXT");	//53
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2019.TXT");	//54
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2020.TXT");	//55
	Year.add("https://www.gein.noa.gr/HTML/Noa_cat/CAT2021.TXT");	//56
	
	int batchSize = 25000;	//HUGE TABLE SO WE BATCH THE INSERTS (1K ROWS AT A TIME)
	Connection connection = null;
	
	
	  try {
	  
	  for (int i = 0; i < Year.size(); i++) { downloadUsingNIO(Year.get(i),
	  "config/UNEDITED"+(i+1964)+".csv");
	  //"C:\\Users\\pambo\\Desktop\\ptixiaki\\UNEDITED"+(i+1964)+".csv"
	  
	  } System.out.println("Downloaded files successfully");
	  
	  } catch (IOException e) { e.printStackTrace(); }
	 
    List<String> filez = new ArrayList<String>();
	
	for(int i=0;i<58;i++) {
		filez.add("config/UNEDITED"+(i+1964)+".csv");
		
	}
	
	
	try {
		connection = DriverManager.getConnection(jdbcUrl,username,password);
		connection.setAutoCommit(false);
		String sql = "insert into earthquake(Date,Time,Latitude,Longitude,Depth,Magnitude)values(?,?,?,?,?,?)";
		
		
		for (int i = 0; i < filez.size(); i++) {
			//Scanner sc = new Scanner(new File(filez.get(i)));
			PreparedStatement statement = connection.prepareStatement(sql);
			BufferedReader lineReader = new BufferedReader(new FileReader(filez.get(i)));
			String lineText = null;
			int count = 0;
			//BufferedWriter writer = new BufferedWriter(new FileWriter("config/CAT"+(i+1964)+".csv"));
			String result="";
			lineReader.readLine();
			lineReader.readLine();
			while ((lineText = lineReader.readLine())!=null) {
		    	String line = lineReader.readLine();
		    	
		    	if (lineText.length()>=58) {
			    	result += lineText.substring(0,12) + ",";
			    	result += lineText.substring(12, 17) + ":";
			    	result += lineText.substring(17, 20) + ":";
			    	result += lineText.substring(20,25) + ",";
			    	result += lineText.substring(25, 33) + ",";
			    	result += lineText.substring(33, 41) + ",";
			    	result += lineText.substring(41,46) + ",";
			    	result += lineText.substring(46, 58) + "\n";
			    	//++count;
			    	String[] data = result.split(",");
					String Date = data[0];
					String Time = data[1];
					String Latitude = data[2];
					String Longitude = data[3];
					String Depth = data[4];
					String Magnitude = data[5];
					
					statement.setString(1, Date);
					statement.setString(2, Time);
					statement.setString(3, Latitude);
					statement.setString(4, Longitude);
					statement.setString(5, Depth);
					statement.setString(6, Magnitude);
					statement.addBatch();
					
					if(count%batchSize==0) {
						statement.executeBatch();
						
					
					}
					
					//System.out.println(result);
			    	
		    	}else {
		    		lineReader.readLine();
		    	}
		    	
		    	
		    	
		    	
		    	
				/*
				 * String[] data = result.split(","); String Date = data[0]; String Time =
				 * data[1]; String Latitude = data[2]; String Longitude = data[3]; String Depth
				 * = data[4]; String Magnitude = data[5];
				 * 
				 * statement.setString(1, Date); statement.setString(2, Time);
				 * statement.setString(3, Latitude); statement.setString(4, Longitude);
				 * statement.setString(5, Depth); statement.setString(6, Magnitude);
				 * statement.addBatch();
				 * 
				 * if(count%batchSize==0) { statement.executeBatch();
				 * 
				 * 
				 * }
				 */
		    	
	    	
		    }
			
			
			lineReader.close();
			statement.executeBatch();
			connection.commit();
			
		    //writer.write(result);
		    //writer.close();
		    //sc.close();
	    
	}
		String sql2 = "UPDATE `earthquake` SET Depth ='28',Magnitude='3.2' WHERE REPLACE(Magnitude,' ','') = '83.2'";
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql2);
		connection.commit();
		connection.close();
	
	System.out.println("Files edited successfully");
	}catch(Exception exception) {
		exception.printStackTrace();
	}
	System.out.println("Succesfully inserted tables into DB");
	/*for(int i=0;i<58;i++) {
		files.add("config/CAT"+(i+1964)+".csv");
		//System.out.println(files.get(i));
		
	}
	int batchSize = 1000;	//HUGE TABLE SO WE BATCH THE INSERTS (1K ROWS AT A TIME)
	Connection connection = null;
	try {
		connection = DriverManager.getConnection(jdbcUrl,username,password);
		connection.setAutoCommit(false);
		String sql = "insert into test(Date,Time,Latitude,Longitude,Depth,Magnitude)values(?,?,?,?,?,?)";
		//in loop//
		
		for (int i = 0; i < files.size(); i++) {
			PreparedStatement statement = connection.prepareStatement(sql);
			BufferedReader lineReader = new BufferedReader(new FileReader(files.get(i)));
			String lineText = null;
			int count = 0;
			/*skipping the first two lines*/
			/*lineReader.readLine();
			lineReader.readLine();
			while((lineText = lineReader.readLine())!=null) {
				String[] data = lineText.split(",");
				String Date = data[0];
				String Time = data[1];
				String Latitude = data[2];
				String Longitude = data[3];
				String Depth = data[4];
				String Magnitude = data[5];
				
				statement.setString(1, Date);
				statement.setString(2, Time);
				statement.setString(3, Latitude);
				statement.setString(4, Longitude);
				statement.setString(5, Depth);
				statement.setString(6, Magnitude);
				statement.addBatch();
				
				if(count%batchSize==0) {
					statement.executeBatch();
					
				}
				
			}
			lineReader.close();
			statement.executeBatch();
			connection.commit();
			
		}
		String sql2 = "UPDATE `test` SET Depth ='28',Magnitude='3.2' WHERE REPLACE(Magnitude,' ','') = '83.2'";
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(sql2);
		connection.commit();
		connection.close();

		System.out.println("Succesfully inserted tables into DB");
		
	}catch(Exception exception) {
		exception.printStackTrace();
	}*/
}

}
