package it.unibs.fp.arnaldo.planetarium;

import it.unibs.fp.mylib.*;
	
public class PlanetariumMain {
	private static final String MENU = "MENU PRINCIPALE";
	private static final String CHOICES[] = {"Inserire un pianeta", "Inserire una luna","Eliminare un pianeta", "Eliminarea una luna"};
	private static final String WARNING1 = "\nATTENZIONE! Non si può inserire una luna senza aver prima inserito un pianeta\n";
	private static final String WARNING2 = "\nATTENZIONE! Non ci sono %s da eliminare\n";
	private static final String WARNING3 = "\nATTENZIONE! Errore nell'inserimento";
	private static final String SUCCESSFUL ="\nInserimento avvenuto con successo\n";
	
	public static void main(String[] args) {
		StarSystem alfa = new StarSystem();
		System.out.println("Ciao! Iniziamo con l'inserimento dati della stella del vostro Sistema stellare");
		alfa.setStar(insertStar());
		
		//INIZIO MENU - interazione con l'utente
		int control1 = 0; //variabile di controllo per il menu
		MyMenu menu = new MyMenu(MENU, CHOICES);
	
		do {
			control1 = menu.scegli();
			switch (control1) {
			case 1:
				int i = giveID( alfa);
				if(alfa.setOrbitatingPlanet(insertPlanet(i)))
					System.out.println(SUCCESSFUL);
				else
					System.out.println(WARNING3);
				break;
			case 2:
		
				if( alfa.hasPlanet() == false)
					System.out.println(WARNING1);
				else {
					//creare metodo per far vedere i pianeti della lista
				}
			case 3:
				
				if( alfa.hasPlanet() == false)
					System.out.println(String.format(WARNING2, "pianeti"));
				else {
					
				}
				break;
			case 4:
				
				if( alfa.hasPlanet() == false)
					System.out.println(String.format(WARNING2, "lune"));
				else {
					
				}
				break;

			default:
				break;
			}
		}while(control1 != 0);
		
	}
	
	//METODO PER RICEVERE UN ID DIVERSO DAI PRECEDENTI
	private static int giveID(StarSystem alfa) {
		int j = 0;
		/*ciclo che itera per tutta la dimensione della lista, il +1 serve nel caso la lista sia piena e tutti
		 * i primi n numeri ( n = dimensione lista) siano gli id delgi oggetti della lista (sarebbe andato bene
		 * anche un <= ma credo che in questo modo sia più leggibile
		 */
		for(j = 0; j < alfa.mySize(); j++) {
		Planet temp = new Planet();
		temp = alfa.getOrbitatingPlanet(j);
		int tempID = temp.getiD();
		if(j == tempID)
			continue;
		else
			break;
		}
		return j;		
	}
	
	/*metodo per prendere l'input di una stella, visto che la stella è una sola l'iD e le coordinate
	 * sono settate a 0
	 */
	
	public static Star insertStar() {
		String name = InputDati.leggiStringaNonVuota("Inserisci il nome della stella: ");
		int mass = InputDati.leggiIntero("Inserisci la massa della stella: ");
		return new Star(0, name, mass, 0, 0);
	}
	
	public static Planet insertPlanet(int _id) {
		String name = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
		int mass = InputDati.leggiIntero("Inserisci la massa: ");
		int x = InputDati.leggiIntero("Inserisci la coordinta X: ");
		int y = InputDati.leggiIntero("Inserisci la coordinata Y: ");
		return new Planet(_id, name, mass, x, y);
	}
	
	public static Moon insertMoon(int _id) {
		String name = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
		int mass = InputDati.leggiIntero("Inserisci la massa: ");
		int x = InputDati.leggiIntero("Inserisci la coordinta X: ");
		int y = InputDati.leggiIntero("Inserisci la coordinata Y: ");
		return new Moon(_id, name, mass, x, y);
	}
}

