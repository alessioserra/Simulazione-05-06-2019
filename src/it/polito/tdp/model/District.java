package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;

public class District {

	private int id;
	private double avgLon;
	private double avgLat;
	
	public District(int id, double avgLon, double avgLat) {

		this.id = id;
		this.avgLon = avgLon;
		this.avgLat = avgLat;
	}

	public int getId() {
		return id;
	}

	public double getAvgLon() {
		return avgLon;
	}

	public double getAvgLat() {
		return avgLat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return "ID"+id;
	}
	
}
