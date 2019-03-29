package it.unibs.fp.arnaldo.planetarium;

public class AstronomicalObject {
	
	private int iD;
	private String name;
	private int mass;
	//Dichiaro un nuovo oggetto Coordinate usando il costruttore in quanto deriva da una classe
	private Coordinate position = new Coordinate();
	
	public AstronomicalObject(int iD, String name, int mass, int x, int y) {
		this.iD = iD;
		this.name = name;
		this.mass = mass;
		this.position.setX(x);
		this.position.setY(y);
	}
	
	public AstronomicalObject() {
		//facendo this() richiamo il costrutture precedente e gli assegno i valori
		this(0, "alfa", 0, 0, 0 );
	}
	
	public AstronomicalObject(String name, int mass) {
		this(0, name, mass, 0, 0);
	}

	public int getiD() {
		return iD;
	}
	
	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	
	
	
}
