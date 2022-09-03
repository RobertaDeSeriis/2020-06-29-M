package it.polito.tdp.imdb.model;

public class Vicino implements Comparable <Vicino>{
	Director d; 
	int peso;
	
	public Vicino(Director d, int peso) {
		super();
		this.d = d;
		this.peso = peso;
	}
	public Director getD() {
		return d;
	}
	public void setD(Director d) {
		this.d = d;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return  d + ",  Attori condivisi: " + peso;
	}
	@Override
	public int compareTo(Vicino o) {
		return -(this.peso-o.peso);
	} 
	
	

}
