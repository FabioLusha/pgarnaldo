package it.unibs.fp.arnaldo.planetarium;
import java.util.*;

public class Planet extends AstronomicalObject {

	private LinkedList<Moon> orbitatingMoon = new LinkedList<Moon>();
	
	public Planet(int iD, String name, int mass, int x, int y, Moon newMoon) {
		super(iD, name, mass, x, y);
		orbitatingMoon.add(newMoon);
	}
	
	public Planet(int iD, String name, int mass, int x, int y) {
		super(iD, name, mass, x, y);
	}
	
}
