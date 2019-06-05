package it.polito.tdp.model;

import java.time.LocalTime;

public class Agente {
	
	//Stati in cui si possono trovare i vari agenti
	public enum StatoAgenti{
		DISPONIBILE,
		INVIAGGIO,
		SERVIZIO,
		FINITO,
	}
	
	
	private int id;
	private StatoAgenti stato;
	private LocalTime oraAttuale;
	private District distretto;
	
	public Agente(int id, StatoAgenti stato, LocalTime oraAttuale,District distretto) {
	
		this.id = id;
		this.stato = stato;
		this.oraAttuale = oraAttuale;
		this.distretto=distretto;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StatoAgenti getStato() {
		return stato;
	}
	public void setStato(StatoAgenti stato) {
		this.stato = stato;
	}
	public LocalTime getOraAttuale() {
		return oraAttuale;
	}
	public void setOraAttuale(LocalTime oraAttuale) {
		this.oraAttuale = oraAttuale;
	}
	public District getDistretto() {
		return distretto;
	}
	public void setDistretto(District distretto) {
		this.distretto = distretto;
	}
	
}
