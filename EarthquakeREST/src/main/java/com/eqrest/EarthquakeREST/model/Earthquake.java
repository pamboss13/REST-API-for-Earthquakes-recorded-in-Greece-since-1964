package com.eqrest.EarthquakeREST.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Earthquake {
	
	private Date Date;
	private LocalTime Time;
	private Float Longitude;
	private Float Latitude;
	private int Depth;
	private Float Magnitude;
	
	

	public Date getDate() {
		return Date;
	}
	public void setDate(Date date2) {
		Date = date2;
	}
	public LocalTime getTime() {
		return Time;
	}
	public void setTime(LocalTime time2) {
		Time = time2;
	}
	public Float getLongitude() {
		return Longitude;
	}
	public void setLongitude(Float longitude) {
		Longitude = longitude;
	}
	public Float getLatitude() {
		return Latitude;
	}
	public void setLatitude(Float latitude) {
		Latitude = latitude;
	}
	public int getDepth() {
		return Depth;
	}
	public void setDepth(int depth) {
		Depth = depth;
	}
	public Float getMagnitude() {
		return Magnitude;
	}
	public void setMagnitude(Float magnitude) {
		Magnitude = magnitude;
	}
	

//	@Override
//	public int hashCode() {
//		return Objects.hash(Date, Depth, Latitude, Longitude, Magnitude, Time);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Earthquake other = (Earthquake) obj;
//		return Objects.equals(Date, other.Date) && Objects.equals(Depth, other.Depth)
//				&& Objects.equals(Latitude, other.Latitude) && Objects.equals(Longitude, other.Longitude)
//				&& Objects.equals(Magnitude, other.Magnitude) && Objects.equals(Time, other.Time);
//	}
//
	@Override
	public String toString() {
		return "Earthquake [Date=" + Date + ", Time=" + Time + ", Longitude=" + Longitude + ", Latitude=" + Latitude
				+ ", Depth=" + Depth + ", Magnitude=" + Magnitude + "]";
	}
	
	
}
