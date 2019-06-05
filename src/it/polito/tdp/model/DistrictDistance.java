package it.polito.tdp.model;

public class DistrictDistance implements Comparable<DistrictDistance>{

	private District distretto;
	private double distanzaDa;
	
	public DistrictDistance(District distretto, double distanzaDa) {
		this.distretto=distretto;
		this.distanzaDa=distanzaDa;
	}

	public District getDistretto() {
		return distretto;
	}

	public double getDistanzaDa() {
		return distanzaDa;
	}

	@Override
	public int compareTo(DistrictDistance o) {
		return (int)(this.distanzaDa-o.distanzaDa);
	}
	
	
	public String toString() {
		return distretto+ " - "+distanzaDa+"KM";
	}
	
}
