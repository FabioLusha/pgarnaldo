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

	public void setOrbitatingPlanet(Planet orbitatingPlanet) {
		this.orbitatingPlanet.add(orbitatingPlanet);
	}
	
	/*questo metodo serve a verificare che la lista sia vuota; ho predisposto questo metodo
	 *  in qunto avendo dichirato un costrutture che inizialliza null e il metodo specifico 
	 *  per le liste .isEmpty() da problemi se riscontra un valore null
	 */
	public boolean hasNoPlanet() {
		boolean c;
		if(orbitatingPlanet == null)
			return false;
		else
			return  c = orbitatingPlanet.isEmpty();
	}
	
}
