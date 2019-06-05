package it.polito.tdp.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import javax.naming.InitialContext;

import it.polito.tdp.db.EventsDao;

public class Simulatore {

	
	   EventsDao dao = new EventsDao();
	
	   //Creo Coda degli eventi
	   private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
	  
		// Modello del Mondo
		private List<Event> eventi;
		private List<Agente> agenti;
		private District distrettoIniziale;
		
		// Parametri di simulazione (Che l'utente inserirà tramite controller)
        private int numeroAgenti; 
		
		// Statistiche da calcolare
		private int numero_eventi_mal_gestiti;
		
		// Variabili interne
		private LocalTime T_inizio;
		
		//Costruttore
		public Simulatore() {
			
			this.eventi = new ArrayList<>();
		}
		
		public void init(int N, Year year, int month,int day) {
		
		  //Carico tutti gli eventi di quel giorno
		  this.eventi = dao.listAllEvents(year, month, day);
		  //Carcico numero agenti immesso dall'utente
		  this.numeroAgenti = N;
		  //Prendo distretto iniziale
		  distrettoIniziale = dao.getDistrettoLessCrime(year, month, day);
		  
		  //Creo gli agenti
		  for (int i=0 ; i<N ; i++) {
			  
			
			  
			  agenti.add(new Agente());
		  
		  
		  
		  }	
		}
		
		public void run() {
			
		}
	
}
