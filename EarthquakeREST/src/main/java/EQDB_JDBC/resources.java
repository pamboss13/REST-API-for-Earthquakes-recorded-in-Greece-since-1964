package EQDB_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;

import com.eqrest.EarthquakeREST.model.Earthquake;

public class resources {
	String jdbcUrl = "jdbc:mysql://localhost:3306/eqdb";
	String username = "root";
	String password="admin";
	Connection connection = null;
	
	public resources() {
		try {
			//Class.forName("com.mysql.jdbc.cj.Driver");
			connection = DriverManager.getConnection(jdbcUrl,username,password);
			connection.setAutoCommit(false);
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public List<Earthquake> getEQSbyYear(String year)throws Exception{
		List<Earthquake>eqs = new ArrayList<>();
		String sql = "SELECT * FROM `earthquake` WHERE Date LIKE   '%"+year+"%'";
		//int cnt=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
					//System.out.println(tim);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
					//System.out.println(tim);
				}else {
					tim = rs.getString(2).replaceAll("\\s", "");
					//System.out.println(tim);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				int s = Integer.parseInt(rs.getString(5).trim());
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public Earthquake getEarthquake(String Date, String Time, String Latitude, String Longitude, int Depth, String Magnitude) throws Exception{
		String conv = Integer.toString(Depth);
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','')= '"+Date+"' AND REPLACE(Time,' ','') LIKE '"+Time+"%' AND REPLACE(Latitude,' ','') = '"+Latitude+"' AND REPLACE(Longitude,' ','') ='"+Longitude+"' AND REPLACE(Depth,' ','') = '"+conv+"' AND REPLACE(Magnitude,' ','') = '"+Magnitude+"'";
		Earthquake eq = new Earthquake();
		try {
			Statement stmt= connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			stmt = connection.createStatement();
			if(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				eq.setDate(date4);
				eq.setTime(tm);
				eq.setLatitude(f);
				eq.setLongitude(t);
				eq.setDepth(s);
				eq.setMagnitude(c);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return eq;
	}
	
	
	public List<Earthquake> getEarthquakesbyMagnitude(String Magnitude) throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Magnitude,' ','') LIKE '"+Magnitude+"%'";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			int s;
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				s= Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}

	public List<Earthquake> getEarthquakesbyDepth(int Depth) throws Exception{
		String conv = Integer.toString(Depth);
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Depth,' ','') = '"+conv+"'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
//	
	public List<Earthquake> getApproxCoord(String Latitude, String Longitude) throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Latitude,' ','') LIKE '"+Latitude+"%' AND REPLACE(Longitude,' ','') LIKE '"+Longitude+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			//int cnt=1;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				/*
				 * System.out.println(cnt); cnt++;
				 */
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
//	
	public List<Earthquake> getEQSsByCoordsAndMag(String Latitude, String Longitude, String Magnitude) throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Latitude,' ','') LIKE '"+Latitude+"%' AND REPLACE(Longitude,' ','') LIKE '"+Longitude+"%' AND REPLACE(Magnitude,' ','') LIKE '"+Magnitude+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public List<Earthquake> getEQSDepthMagnitude(int Depth, String Magnitude) throws Exception{
		String conv = Integer.toString(Depth);
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Depth,' ','') = '"+conv+"' AND REPLACE(Magnitude,' ','') LIKE '"+Magnitude+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public List<Earthquake> getEQSDateMagnitude(String Date, String Magnitude)throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') = '"+Date+"' AND REPLACE(Magnitude,' ','') LIKE '"+Magnitude+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
//	
	public List<Earthquake> getEQSYearMonth(String Year, String Month) throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') LIKE '"+(Year+Month)+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public List<Earthquake> getEQSDateTime(String Date, String Time) throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') = '"+Date+"' AND REPLACE(Time,' ','') LIKE '"+Time+"%'";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public List<Earthquake> getAllEQs() throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE 1";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	public List<Earthquake> getEQSByMonth(String Month) throws Exception {
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') LIKE '%"+Month+"%'";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	public List<Earthquake> getEQSByDate(String Date) throws Exception  {
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') = '"+Date+"'";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			
			while(rs.next()) {
				
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return eqs;
	}
	
	public List<Earthquake> getEQSDateCoordsDepthMagnitude(String Date,String Latitude,String Longitude,String Depth, String Magnitude)throws Exception{
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') = '"+Date+"' AND Latitude LIKE '%"+Latitude+"%' AND Longitude LIKE '%"+Longitude+"%' AND Depth LIKE '%"+Depth+"%'  AND REPLACE(Magnitude,' ','') LIKE '"+Magnitude+"%'" ;
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
	public List<Earthquake> getEQSByDateDepth(String Date,String Depth) throws Exception  {
		String sql = "SELECT * FROM `earthquake` WHERE REPLACE(Date,' ','') = '"+Date+"' AND Depth LIKE '%"+Depth+"%'";
		List<Earthquake> eqs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			
			while(rs.next()) {
				
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				int s = Integer.parseInt(rs.getString(5).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return eqs;
	}
	
	public List<Earthquake> getEQSbyYearCoordinates(String year,String latitude,String longitude)throws Exception{
		List<Earthquake>eqs = new ArrayList<>();
		String sql = "SELECT * FROM `earthquake` WHERE Date LIKE   '%"+year+"%' AND Latitude LIKE '%"+latitude+"%'AND Longitude LIKE '%"+longitude+"%' ";
		//int cnt=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
					//System.out.println(tim);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
					//System.out.println(tim);
				}else {
					tim = rs.getString(2).replaceAll("\\s", "");
					//System.out.println(tim);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				int s = Integer.parseInt(rs.getString(5).trim());
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	public List<Earthquake> getEQSbyYearCoordinatesMagnitude(String year,String latitude,String longitude,String magnitude)throws Exception{
		List<Earthquake>eqs = new ArrayList<>();
		String sql = "SELECT * FROM `earthquake` WHERE Date LIKE '%"+year+"%' AND Latitude LIKE '%"+latitude+"%'AND Longitude LIKE '%"+longitude+"%' AND REPLACE(Magnitude,' ','') LIKE '"+magnitude+"%'";
		//int cnt=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
					//System.out.println(tim);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
					//System.out.println(tim);
				}else {
					tim = rs.getString(2).replaceAll("\\s", "");
					//System.out.println(tim);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				int s = Integer.parseInt(rs.getString(5).trim());
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	public List<Earthquake> getEQSbyCoordinatesMagnitude(String latitude,String longitude,String magnitude)throws Exception{
		List<Earthquake>eqs = new ArrayList<>();
		String sql = "SELECT * FROM `earthquake` WHERE Latitude LIKE '%"+latitude+"%'AND Longitude LIKE '%"+longitude+"%' AND REPLACE(Magnitude,' ','') LIKE '"+magnitude+"%'";
		//int cnt=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
					//System.out.println(tim);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
					//System.out.println(tim);
				}else {
					tim = rs.getString(2).replaceAll("\\s", "");
					//System.out.println(tim);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				int s = Integer.parseInt(rs.getString(5).trim());
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	public List<Earthquake> getEQSbyYearMagnitude(String year,String magnitude)throws Exception{
		List<Earthquake>eqs = new ArrayList<>();
		String sql = "SELECT * FROM `earthquake` WHERE Date LIKE '%"+year+"%' AND  REPLACE(Magnitude,' ','') LIKE '"+magnitude+"%'";
		//int cnt=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.setFetchSize(2000);
			while(rs.next()) {
				String d = rs.getString(1).trim();
				String tim = rs.getString(2).replaceAll("\\s", "");
				if(tim.length()==8) {
					char c='0';
					tim = tim.substring(0, 6) + c+c + tim.substring(6);
					//System.out.println(tim);
				}else if(tim.length()==9) {
					char c='0';
					tim = tim.substring(0, 6) + c + tim.substring(6);
					//System.out.println(tim);
				}else {
					tim = rs.getString(2).replaceAll("\\s", "");
					//System.out.println(tim);
				}
				if(Integer.parseInt(tim.substring(6,8))==60)
					tim=tim.replace(tim.substring(6,8),"59");
				SimpleDateFormat formatter4=new SimpleDateFormat("yyyy MMM dd HH:mm:ss.s");
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss.s");
				LocalTime tm = LocalTime.parse(tim);
				Date date4=formatter4.parse(d+" "+tim);
				int s = Integer.parseInt(rs.getString(5).trim());
				float f = Float.parseFloat(rs.getString(3).trim());
				float t = Float.parseFloat(rs.getString(4).trim());
				float c = Float.parseFloat(rs.getString(6).trim());
				Earthquake e = new Earthquake();
				e.setDate(date4);
				e.setTime(tm);
				e.setLatitude(f);
				e.setLongitude(t);
				e.setDepth(s);
				e.setMagnitude(c);
				
				eqs.add(e);
				//System.out.println("Column"+cnt+"returned ");
				//cnt++;
				connection.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return eqs;
	}
	
}
