package it.unibs.fp.arnaldo.planetarium;

import it.unibs.fp.mylib.*;

public class PlanetariumMain {
	
	//metodo per prendere l'input di una stella, visto che la stella è una sola l'iD e la posizione la decidiamo noi
	public static Star insertStar(String messaggio, int _id) {
		String name = InputDati.leggiStringaNonVuota(messaggio + " the name of your star: ");
		int mass = InputDati.leggiIntero(messaggio + " the mass: ");
		return new Star(_id, name, mass);
	}
	
	public static Planet insertPlanet(String messaggio, int _id, int x, int y) {
		String name = InputDati.leggiStringaNonVuota(messaggio + " the name of your star: ");
		int mass = InputDati.leggiIntero(messaggio + " the mass: ");
		x = InputDati.leggiIntero(messaggio + " the X coordinate: ");
		y = InputDati.leggiIntero(messaggio + " the y coordinate: ");
		return new Planet(_id, name, mass, x, y);
	}
	
	
	public static void main(String[] args) {
		int i = 0;
		StarSystem alfa = new StarSystem();
		String control1 = "no";
		alfa.setStar(insertStar("Insert ", i));
		control1 = InputDati.leggiStringaNonVuota("Vuoi aggiungere pianeti (yes/no)? ");
		
		if(control1.equalsIgnoreCase("yes")){
			String control2 = "no";
			do {
			 
			}while(control2.equalsIgnoreCase("yes"));
		}
		
	}
}
