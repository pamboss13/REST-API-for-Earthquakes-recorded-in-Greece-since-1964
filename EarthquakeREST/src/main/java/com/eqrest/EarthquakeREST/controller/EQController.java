package com.eqrest.EarthquakeREST.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eqrest.EarthquakeREST.exception.EQException;
import com.eqrest.EarthquakeREST.model.Earthquake;

import EQDB_JDBC.resources;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import  io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("Earthquakes/")
@Tag(name="Get Methods")
public class EQController {
	
	
	resources repo = new resources();
	
 	@Operation(summary = "Find Earthquakes by specific year", description = "Get results for all the earthquakes that happenned on a specific year ***POSTMAN RECOMMENDED***",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Year")
	public List<Earthquake> getEQSbyYear(@RequestParam(value="year")String year)throws Exception{
		return repo.getEQSbyYear(year);
	}
 	
 	@Operation(summary = "Find Earthquakes by specific year and place", description = "Get results for all the earthquakes that happenned on a specific year and place(1964-2021 if Databse is not updated)",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("YearCity")
	public List<Earthquake> getEQSbyYearCoordinates(@RequestParam(value="year")String year,@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude)throws Exception{
		return repo.getEQSbyYearCoordinates(year,latitude,longitude);
	}
 	
 	@Operation(summary = "Find a single earthquake", description = "Search with all the necessary details (everything except the time must be percise)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("SingleEQ")
	public Earthquake getEarthquake(@RequestParam(value="date")String date,
			@RequestParam(value="time")String time,
			@RequestParam(value="latitude")String latitude,
			@RequestParam(value="longitude")String longitude,
			@RequestParam(value="depth")int depth,
			@RequestParam(value="magnitude")String magnitude) throws Exception{
		
		return repo.getEarthquake(date, time, latitude, longitude, depth, magnitude);
	}
	
 	@Operation(summary = "Find Earthquakes by Magnitude", description = "Magnitude value should be either percise (e.g 1.2) or approximate to get all the values(e.g. 1, this will find all earthquakes varying from magnitude 1.0-1.9) ***POSTMAN RECOMMENDED***"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Magnitude")
	public List<Earthquake> getEQSbyMagnitude(@RequestParam(value="magnitude")String magnitude) throws Exception{
		return repo.getEarthquakesbyMagnitude(magnitude);
	}
	
// 	@Operation(summary = "Find Earthquakes by percise Coordinates", description = "Search with input y.xx(at least 2 numbers after the y. up to 4 maximum)"
// 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
//	@GetMapping("Coordinates")
//	public List<Earthquake> getEQSbyCoordinates(@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude) throws Exception{
//		return repo.getEarthquakesbyCoordinates(latitude,longitude);
//	}
	
 	@Operation(summary = "Find Earthquakes by Coordinates", description = "Search with inputs (x,y) (e.g.: (37,23) -> returns all earthquakes in the coordiantes x=37.0000-37.9999, y =23.0000-23.9999"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Coordinates")
	public List<Earthquake> getEQSbyApproximateCoordinates(@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude) throws Exception{
		return repo.getApproxCoord(latitude,longitude);
	}
 	
 	@Operation(summary = "Find Earthquakes by depth", description = "Search with integer depth number as input e.g.: xy ***POSTMAN RECOMMENDED***",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Depth")
	public List<Earthquake> getEQSbyDepth(@RequestParam(value="depth")int depth) throws Exception{
		return repo.getEarthquakesbyDepth(depth);
	}
	
 	@Operation(summary = "Find Earthquakes by approximate coordinates and specific magnitude", description = "Search with inputs (x,y,z) (e.g.: (37,23,1.9) -> returns all earthquakes in the coordiantes x=37.0000-37.9999, y =23.0000-23.9999 that had an earthquake with a magnitude of 1.9(local magnitude)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("CoordsMagnitude")
	public List<Earthquake> getEQSbyCoorsAndMag(@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude,@RequestParam(value="magnitude")String magnitude) throws Exception{
		return repo.getEQSsByCoordsAndMag(latitude,longitude,magnitude);
	}
// 	
 	@Operation(summary = "Find Earthquakes with the combination of depth and magnitude", description = "Search with inputs (x,y) (e.g.: (13,1.9) -> returns all earthquakes with depth = 13km and magnitude of 1.9(richter magnitude scale)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("DepthMagnitude")
	public List<Earthquake> getEQSbyDepthnMag(@RequestParam(value="depth")int depth,@RequestParam(value="magnitude")String magnitude) throws Exception{
		return repo.getEQSDepthMagnitude(depth,magnitude);
	}
//// 	
 	@Operation(summary = "Find Earthquakes by the combination of date and magnitude", description = "Search with inputs (x,y) (e.g.: (13JUN2021,1.9) -> returns all earthquakes that took place on june 13th of 2021  that had a magnitude of 1.9(richter magnitude scale)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("DateMagnitude")
	public List<Earthquake> getEQSDateMagnitude(@RequestParam(value="date")String date,@RequestParam(value="magnitude")String magnitude)throws Exception{
		
 		return repo.getEQSDateMagnitude(date,magnitude);
		
	}
// 	
 	@Operation(summary = "Find Earthquakes by specific month of a year", description = "Search with inputs (year,month) (e.g.: (2021,DEC) -> returns all earthquakes that took place on the entirety of the month of December of 2021"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("YearMonth")
	public List<Earthquake> getEQSYearMonth(@RequestParam(value="year")String year,@RequestParam(value="month")String month) throws Exception{
		return repo.getEQSYearMonth(year,month);
	}
 	
 	@Operation(summary = "Find Earthquakes by specific date and time", description = "Search with inputs (date,time), time could be percise or just hour or hour and minute (e.g.: (2021DEC31,23:00) -> returns all earthquakes that took place on 31th of December of 2021 at 23:00:(0-59 seconds)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("DateTime")
	public List<Earthquake> getEQSDateTime(@RequestParam(value="date")String date,@RequestParam(value="time")String time) throws Exception{
		return repo.getEQSDateTime(date,time);
	}
 	
 	@Operation(hidden=true,summary = "Find all Earthquakes", description = "return all earthquakes ***POSTMAN RECOMMENDED***",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("EVERYTHING")
	public List<Earthquake> getAllEQs() throws Exception{
		return repo.getAllEQs();
	}
 	
 	@Operation(summary = "Find all earthquakes that took place on a specific month every year", description = "Search earthquakes by giving a month in the format MMM (e.g.: JUN) ***POSTMAN RECOMMENDED***"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Month")
	public List<Earthquake> getEQSByMonth(@RequestParam(value="month")String month)throws Exception {
		return repo.getEQSByMonth(month);
	}
 	@Operation(summary = "Find all earthquakes that took place on a specific Date", description = "Search earthquakes by giving a date in the format YYYYMMMDD (e.g.: 2021JUN11, always capitals for the month)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("Date")
	public List<Earthquake> getEQSByDate(@RequestParam("date") String date) throws Exception{

		return repo.getEQSByDate(date);
	}
 	
 	@Operation(summary = "Find Earthquakes by the combination of date,coordinates,depth and magnitude", description = "Search with inputs (x,y,w,z) (e.g.: (2021DEC31,35.0880,25.2168,13,1.9) -> returns all earthquakes that took place on December 31st of 2021  that had a magnitude of 1.9(richter magnitude scale) and depth 13km in specific coordinates"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("DateCoordsDepthMagnitude")
	public List<Earthquake> getEQSDateCoordsDepthMagnitude(@RequestParam(value="date")String date,@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude,@RequestParam(value="depth")String depth,@RequestParam(value="magnitude")String magnitude)throws Exception{
		
 		return repo.getEQSDateCoordsDepthMagnitude(date,latitude,longitude,depth,magnitude);
		
	}
 	
 	@Operation(summary = "Find all earthquakes that took place on a specific Date", description = "Search earthquakes by giving a date in the format YYYYMMMDD (e.g.: 2021JUN11, always capitals for the month)"
 			,responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("DateDepth")
	public List<Earthquake> getEQSByDateDepth(@RequestParam("date") String date,@RequestParam("depth") String depth) throws Exception{

		return repo.getEQSByDateDepth(date,depth);
	}
 	
 	@Operation(summary = "Find Earthquakes by specific year, place and magnitude size", description = "Get results for all the earthquakes that happenned on a specific year and  place(1964-2021 if Databse is not updated) with approximate magnitude",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("YearCityMagnitude")
	public List<Earthquake> getEQSbyYearCoordinatesMagnitude(@RequestParam(value="year")String year,@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude,@RequestParam(value="magnitude")String magnitude)throws Exception{
		return repo.getEQSbyYearCoordinatesMagnitude(year,latitude,longitude,magnitude);
	}
 	
 	@Operation(summary = "Find Earthquakes by specific city and magnitude size", description = "Get results for all the earthquakes that happenned on a specific year and place(1964-2021 if Databse is not updated)",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("CityMagnitude")
	public List<Earthquake> getEQSbyCoordinatesMagnitude(@RequestParam(value="latitude")String latitude,@RequestParam(value="longitude")String longitude,@RequestParam(value="magnitude")String magnitude)throws Exception{
		return repo.getEQSbyCoordinatesMagnitude(latitude,longitude,magnitude);
	}
 	
 	@Operation(summary = "Find Earthquakes by specific year and magnitude size", description = "Get results for all the earthquakes that happenned on a specific year and  with approximate magnitude",responses = {@ApiResponse(responseCode = "200",content=@Content(schema=@Schema(implementation=Earthquake.class),mediaType=MediaType.APPLICATION_JSON_VALUE),description="Successfull response")})
	@GetMapping("YearMagnitude")
	public List<Earthquake> getEQSbyYearMagnitude(@RequestParam(value="year")String year,@RequestParam(value="magnitude")String magnitude)throws Exception{
		return repo.getEQSbyYearMagnitude(year,magnitude);
	}

	
}
