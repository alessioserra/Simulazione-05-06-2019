package it.polito.tdp.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.AsyncHandler;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	EventsDao dao;
	Graph<District, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new EventsDao();
	}
	
	public List<Year> getYears(){
		return dao.getYears();
	}
	
	public void creaGrafo(Year year ) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//Creo vertici
		Graphs.addAllVertices(grafo, dao.getIdDistrict(year));
		
		//Creo Archi
		for (District d1 : grafo.vertexSet()) {
			for (District d2 : grafo.vertexSet()) {
				if (!d1.equals(d2) && !grafo.containsEdge(d1, d2)) {
					
					//Calcolo coordinata
					LatLng coord1 = new LatLng(d1.getAvgLat(), d1.getAvgLon());
					LatLng coord2 = new LatLng(d2.getAvgLat(), d2.getAvgLon());
					
					//Calcolo distanza
					double distanza = LatLngTool.distance(coord1, coord2, LengthUnit.KILOMETER);
					
					//Creo arco
					Graphs.addEdge(grafo, d1, d2, distanza);
				}
			}
		}
		System.out.println("Grafo creato");
		System.out.println("#Nodi= "+grafo.vertexSet().size());
		System.out.println("#Archi= "+grafo.edgeSet().size());
		
	}
	
	public Graph<District, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}

	
	public List<DistrictDistance> getVicini(District d){
		
		List<DistrictDistance> result = new ArrayList<>();
		
		//Ottengo i vicini di quel distretto
		List<District> vicini = Graphs.neighborListOf(grafo, d);
		
		for (District vic : vicini) {
			//Ottengo il peso dell'arco
			double distanzaDa = grafo.getEdgeWeight(grafo.getEdge(d, vic));
			
			//Aggiungo al risultato
			result.add(new DistrictDistance(vic, distanzaDa));		
		}
		
		//Riordino crescente
		Collections.sort(result);
		
		return result;
	}
	
}
