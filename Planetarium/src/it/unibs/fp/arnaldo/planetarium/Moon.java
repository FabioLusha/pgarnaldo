package it.unibs.fp.arnaldo.planetarium;

public class Moon extends AstronomicalObject {
	
	private Planet planetReference;
	public Moon(int iD, String name, int mass, int x, int y, Planet planet) {
		super(iD, name, mass, x, y);
		this.planetReference = planet;
	}
	
}
