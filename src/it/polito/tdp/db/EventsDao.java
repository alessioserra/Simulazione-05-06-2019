package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import it.polito.tdp.model.District;
import it.polito.tdp.model.Event;


public class EventsDao {
	
	public District getDistrettoLessCrime(Year year, int month, int day) {
		
	String sql="SELECT DISTINCT district_id, AVG(geo_lon) AS avgLon, AVG(geo_lat) AS avgLat, SUM(is_crime) AS criminalita FROM events WHERE Year(reported_date)=? AND Month(reported_date)=? AND Day(reported_date)=? ORDER BY reported_date";
	
	District d= new District(0, 0.0, 0.0);
	
	try {
		Connection conn = DBConnect.getConnection() ;
		PreparedStatement st = conn.prepareStatement(sql) ;
		
		//Set parameter
		st.setInt(1, year.getValue());
		st.setInt(2, month);
		st.setInt(3, day);
		
		ResultSet res = st.executeQuery() ;
		
		//Inizializzo criminalita
		int criminalita = 1000000;
		
		while(res.next()) {

			if ( res.getInt("criminalita") < criminalita ) {
				
				double avgLon = res.getDouble("avgLon");
				double avgLat = res.getDouble("avgLat");		
				int id = res.getInt("district_id");
				
				d= new District(id, avgLon, avgLat);
			}
			
		}
		
		 conn.close();
		 return d ;
	
	   } catch (SQLException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 return null ;
	   }
	
	
	
	}
	public List<Event> listAllEvents(Year year, int month, int day){
		String sql = "SELECT * FROM events WHERE Year(reported_date)=? AND Month(reported_date)=? AND Day(reported_date)=?"  ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			//Set parameter
			st.setInt(1, year.getValue());
			st.setInt(2, month);
			st.setInt(3, day);
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
					
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Year> getYears(){
		String sql = "SELECT DISTINCT Year(reported_date) AS year FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Year> result = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(Year.of(res.getInt("year")));
				} catch (Throwable e) {
					System.out.println(e);
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<District> getIdDistrict(Year year){
		final 
		String sql = "SELECT DISTINCT district_id, AVG(geo_lon) AS avgLon, AVG(geo_lat) AS avgLat FROM events WHERE YEAR(reported_date)=? GROUP BY district_id" ;
		
		List<District> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			//Set parameter
			st.setInt(1, year.getValue());
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					double avgLon = res.getDouble("avgLon");
					double avgLat = res.getDouble("avgLat");		
					int id = res.getInt("district_id");
							
					result.add(new District(id, avgLon, avgLat));
							
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
    }
	
}
