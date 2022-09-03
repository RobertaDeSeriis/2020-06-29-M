package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.Adiacenza;
import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	ImdbDAO dao; 
	Graph<Director, DefaultWeightedEdge> grafo; 
	Map<Integer, Director> idMap;
	List<Director> vertici; 
	List<Adiacenza> archi; 
	
	
	public Model() {
		this.dao= new ImdbDAO(); 
	}

	public String creaGrafo(int a) { // no idMap qui, solo nel dao passato come parametro
		this.grafo= new SimpleWeightedGraph<Director, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		idMap= new HashMap<>(); 
		vertici=dao.listAllDirectors(a, idMap); 
		archi= dao.listArchi(a, idMap);
		Graphs.addAllVertices(this.grafo, vertici); 
		for (Adiacenza a1: archi) {
			if(grafo.containsVertex(a1.getD1()) && grafo.containsVertex(a1.getD2())) {
				if(a1.getPeso()>0)
				Graphs.addEdge(this.grafo, a1.getD1(), a1.getD2(), a1.getPeso());
			}
		}
		
		
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}

	public List<Adiacenza> getArchi() {
		return archi;
	}

	public void setArchi(List<Adiacenza> archi) {
		this.archi = archi;
	}

	public List<Director> getVertici() {
		return vertici;
	}
	
	public List<Vicino> getAdiacenti(Director d){
		List<Director> vicini= Graphs.neighborListOf(this.grafo, d);
		List<Vicino> result= new ArrayList<>();
		for (Director d1: vicini) {
			int peso= (int) this.grafo.getEdgeWeight(this.grafo.getEdge(d, d1));
			result.add(new Vicino(d1,peso));
		}
		Collections.sort(result);
		return result;
	}
}
