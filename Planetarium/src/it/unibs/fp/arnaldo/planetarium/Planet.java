package it.unibs.fp.arnaldo.planetarium;

public class Planet extends AstronomicalObject {
	
	public Planet(int iD, String name, int mass, int x, int y, Moon newMoon) {
		super(iD, name, mass, x, y);
	}
	
	public Planet(int iD, String name, int mass, int x, int y) {
		super(iD, name, mass, x, y);
	}
	
	public Planet() {
		super();
	}
	
	
}
