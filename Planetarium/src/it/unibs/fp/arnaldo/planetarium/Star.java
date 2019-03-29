package it.unibs.fp.arnaldo.planetarium;

public class Star extends AstronomicalObject {
	
	public Star(int iD, String name, int mass) {
		super(iD,  name,  mass, 0, 0);
	}
	
	public Star() {
		super();
	}
	public Star(String name, int mass) {
		super(name, mass);
	}
}
