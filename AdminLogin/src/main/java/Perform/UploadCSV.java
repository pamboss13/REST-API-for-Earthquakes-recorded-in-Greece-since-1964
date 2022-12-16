package Perform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.Scanner;
import java.util.stream.Stream;
import com.opencsv.CSVReader;

/**
 * Servlet implementation class UploadCSV
 */

@WebServlet("/UploadCSV")
@MultipartConfig(maxFileSize = 16177215)
public class UploadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String jdbcUrl = "jdbc:mysql://localhost:3306/eqdb";
	String username = "root";
	String password="admin";
	String sql = "insert into earthquake(Date,Time,Latitude,Longitude,Depth,Magnitude)values(?,?,?,?,?,?)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //fetch form data
            
        	Part part = request.getPart("csv");
			
			  String fileName =  Paths.get(part.getSubmittedFileName()).getFileName().toString();
			  
			  String path =  getServletContext().getRealPath("/files"+File.separator+fileName);
			 
            //String path = getServletContext().getContextPath();
            InputStream is = part.getInputStream();
            boolean test = uploadFile(is,path);
            if(test){
                out.println("uploaded to: "+path);
            }else{
                out.println("something's wrong");
            }
            
            EditFile(path);
           
        }
		//doGet(request, response);
	}
	public boolean uploadFile(InputStream is, String path){
        boolean test = false;
        try{
            byte[] byt = new byte[is.available()];
            is.read(byt);
            
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            
            
            test = true;
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return test;
    }
	
	public void EditFile(String path) throws IOException {
		Scanner sc = new Scanner(new File(path));
		BufferedWriter writer = new BufferedWriter(new FileWriter("config"));
		String result="";
		while (sc.hasNext()) {
	    	String line = sc.nextLine();
	    	
	    	if (line.length()>=58) {
		    	result += line.substring(0,12) + ",";
		    	result += line.substring(12, 17) + ":";
		    	result += line.substring(17, 20) + ":";
		    	result += line.substring(20,25) + ",";
		    	result += line.substring(25, 33) + ",";
		    	result += line.substring(33, 41) + ",";
		    	result += line.substring(41,46) + ",";
		    	result += line.substring(46, 58) + "\n";
		    	
	    	}
	    	
    	
	    }
		
	    writer.write(result);
	    writer.close();
	    sc.close();
	    System.out.println("created new edited file successfully");
	    UpdateDB("config");
	    
	}
	
	public void UpdateDB(String file) {
		int batchSize = 1000;	//HUGE TABLE SO WE BATCH THE INSERTS (1K ROWS AT A TIME)
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,username,password);
			connection.setAutoCommit(false);
			//String sql = "insert into earthquake(Date,Time,Latitude,Longitude,Depth,Magnitude)values(?,?,?,?,?,?)";
			//in loop//
			
			//for (int i = 0; i < file.size(); i++) {
				PreparedStatement statement = connection.prepareStatement(sql);
				BufferedReader lineReader = new BufferedReader(new FileReader(file));
				String lineText = null;
				int count = 0;
				/*skipping the first two lines*/
				lineReader.readLine();
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
				
			//}
//			String sql2 = "UPDATE `earthquake` SET Depth ='28',Magnitude='3.2' WHERE REPLACE(Magnitude,' ','') = '83.2'";
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate(sql2);
//			connection.commit();
			//connection.close();

			System.out.println("Succesfully inserted tables into DB");
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}

}
