package it.unibs.fp.arnaldo.planetarium;
import java.util.*;

public class StarSystem {
	private Star star = new Star();
	private LinkedList<Planet> orbitatingPlanet = new LinkedList<Planet>();
	
	public StarSystem(Star star, LinkedList<Planet> orbitatingPlanet) {
		this.star = star;
		this.orbitatingPlanet = orbitatingPlanet;
	}
	
	public StarSystem() {
		star = null;
		orbitatingPlanet = null;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public LinkedList<Planet> getOrbitatingPlanet() {
		return orbitatingPlanet;
	}

	public void setOrbitatingPlanet(LinkedList<Planet> orbitatingPlanet) {
		this.orbitatingPlanet = orbitatingPlanet;
	}
	
	
	
	
}
