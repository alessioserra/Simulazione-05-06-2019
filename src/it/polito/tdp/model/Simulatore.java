package it.polito.tdp.model;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Evento;
import it.polito.tdp.db.EventsDao;
import it.polito.tdp.model.Agente.StatoAgenti;
import it.polito.tdp.model.Evento.TipoEvento;

public class Simulatore {

	
	   EventsDao dao = new EventsDao();
	
	   //Creo Coda degli eventi
	   private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
	  
		// Modello del Mondo
		private List<Event> eventi;
		private List<Agente> agenti;
		private District distrettoIniziale;
		double velocita = 60.0 ; //    KM/h
		
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
		  agenti = new ArrayList<>();
		  
		  //Prendo distretto iniziale
		  distrettoIniziale = dao.getDistrettoLessCrime(year, month, day);
		  
		  //Inizializzo
		  numero_eventi_mal_gestiti=0;
		  
		  //Setto ora iniziale all'ora del primo evento
		  T_inizio = eventi.get(0).getReported_date().toLocalTime();
		  
		  //Creo gli agenti (Tutti disponibili all'orario iniziale)
		  for (int i=0 ; i<N ; i++) {
			  agenti.add( new Agente(i, StatoAgenti.DISPONIBILE, T_inizio, distrettoIniziale) );
		  }	
		  
		  //Inizializzo coda:
		  for (Event e : this.eventi) {
			  queue.add(new Evento( e.getReported_date().toLocalTime(), TipoEvento.ACCADE, e, null ));
		  }
		
		}
		
		public int run() {
			
                while (!queue.isEmpty()) {
				
				//Estraggo evento dalla coda
				Evento ev = queue.poll();
				
				//Stampo eventi nella console
				System.out.println(ev.toString());
				
				//AgentiLiberi
				int numeroAgentiLiberi=this.agenti.size();
				
				//Gestisco i diversi casi
				switch (ev.getTipo()) {
					
				
				case ACCADE:
					
					//Se vi sono agenti disponibili
					if (agenti.size()>0) {
						
					Agente agente = agenti.get(0);
					
					//Prendo evento tramite idMap
					ev.getEvento().getDistrict_id();
					
					//Calcolo distanza
					
					
					queue.add(new Evento(ev.getOra(),TipoEvento.USCITA,c));
					
					}
					
					break;
					
				case ATTESA:
					
					break;
					
				case TIMEOUT:
					
					numero_eventi_mal_gestiti++;
					break;
					
				case RISOLTO:
					
					break;
				}
		}
                
                return numero_eventi_mal_gestiti;
	
    }
}
