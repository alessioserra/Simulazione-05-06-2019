package it.polito.tdp.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	//Tipologie di evento che possono accadere
	public enum TipoEvento{
		
		ACCADE, //Evento accade
		ATTESA, //Evento in attesa dell'arrivo di un agente
		TIMEOUT, //Ritardo 15 min -> Evento mal gestito
		RISOLTO //Evento correttamente gestito	
	}
	
	
	private LocalTime ora ; // Timestamp dell'evento
	private TipoEvento tipo ; // Tipologia d evento
	private Event evento;
	private Agente agenteChiamato;
	
	public Evento(LocalTime ora, TipoEvento tipo, Event evento, Agente agenteChiamato) {
		this.ora = ora;
		this.tipo = tipo;
		this.evento = evento;
		this.agenteChiamato = agenteChiamato;
	}

	public LocalTime getOra() {
		return ora;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public Event getEvento() {
		return evento;
	}

	public Agente getAgenteChiamato() {
		return agenteChiamato;
	}

	@Override
	public int compareTo(Evento o) {
		return ora.compareTo(o.ora);
	}
	
	public String toString() {
		return ora+" - "+tipo+" - "+evento+" - "+agenteChiamato;
	}
}
