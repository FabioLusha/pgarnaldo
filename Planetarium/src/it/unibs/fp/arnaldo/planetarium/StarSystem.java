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

	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public Planet getOrbitatingPlanet(int i) {
		return orbitatingPlanet.get(i);
	}

	public boolean setOrbitatingPlanet(Planet planet) {
		return this.orbitatingPlanet.add(planet);
	}
	
	public void removeOrbitatingPlanet(int i) {
		this.orbitatingPlanet.remove(i);
	}
	
	/*questo metodo serve a verificare che la lista sia vuota; ho predisposto questo metodo
	 *  in quanto e il metodo specifico per le liste .isEmpty() da problemi se riscontra un valore null.
	 *  lo stesso discorso vale per .mySize() riferito a .size()
	 */
	public boolean hasPlanet() {
		if(orbitatingPlanet == null)
			return false;
		else
			return  orbitatingPlanet.isEmpty();
	}
	
	public int mySize() {
		if(orbitatingPlanet == null)
			return 0;
		else
			return orbitatingPlanet.size();
	}
	//fai vedere il pianeta e il relativo indice nella lista
	public void showPlanet() {
		for(Planet item : orbitatingPlanet) {
			System.out.println("Index: " + orbitatingPlanet.indexOf(item));
			System.out.println(item.planetToString());
		}
	}
	
}
