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
	
	public String planetToString() {
		return "ID: " + super.getiD()+ "\nNome: " + super.getName()+
		"\nMassa: " + super.getMass() +
		"\nCoordinata X: " + super.getPosition().getX() +
		"\nCoordinata Y: " + super.getPosition().getY();
	}
	
}
