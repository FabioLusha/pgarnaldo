package it.unibs.fp.arnaldo.planetarium;

public class Star extends AstronomicalObject {
	
	public Star(int iD, String name, int mass, int x, int y) {
		super(iD, name,  mass, x, y);
	}
	
	public Star() {
		super();
	}
	public Star(String name, int mass) {
		super(name, mass);
	}
	
}
