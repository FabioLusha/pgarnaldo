package it.unibs.fp.arnaldo.planetarium;

import it.unibs.fp.mylib.*;
	
public class PlanetariumMain {
	private static final String MENU = "MENU PRINCIPALE";
	private static final String OPTIONS[] = {"Inserire un pianeta", "Inserire una luna","Eliminare un pianeta", "Eliminare una luna"};
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
		MyMenu menu = new MyMenu(MENU, OPTIONS);
		do {
			control1 = menu.scegli();
			switch (control1) {
			case 1:
				if(alfa.addPlanet(insertAstroObject(alfa.givePlanetID()))) {
					System.out.println(SUCCESSFUL);
					alfa.showPlanets();
				}
				else
					System.out.println(WARNING3);
				break;
			case 2:
				if( alfa.getMatrixOfOb().isEmpty())
					System.out.println(WARNING1);
				else {
					alfa.showPlanets();
					int t = InputDati.leggiIntero("\nAttorno a quale pianeta orbita questa luna? (inserisci l'index): ");
					alfa.addMoon(t, insertAstroObject(alfa.giveMoonID()));
				}
				break;
			case 3:
				
				if( alfa.getMatrixOfOb().isEmpty()) {
					System.out.println(String.format(WARNING2, "pianeti"));
				}
				else {
					alfa.showPlanets();
					int p = InputDati.leggiIntero("\nQuale pianeta vuoi eliminare? (inica l'Index)");
					alfa.removePlanet(p);
				}
				break;
			case 4:
				if( alfa.getMatrixOfOb().size() < 1 )
					System.out.println(String.format(WARNING2, "lune"));
				else {
					alfa.showMoons();
					System.out.println("Quale luna vuoi eliminare? (inica l'Index)");
					int a = InputDati.leggiIntero("Index sinistro: ");
					int b = InputDati.leggiIntero("Index destro: ");
					alfa.removeMoon(a, b);
				}
				break;

			default:
				break;
			}
		}while(control1 != 0);
		
	}
	

	
	/*metodo per prendere l'input di una stella, visto che la stella è una sola l'iD e le coordinate
	 * sono settate a 0
	 */
	
	public static Star insertStar() {
		String name = InputDati.leggiStringaNonVuota("Inserisci il nome della stella: ");
		int mass = InputDati.leggiIntero("Inserisci la massa della stella: ");
		return new Star(0, name, mass, 0, 0);
	}
	
	public static AstronomicalObject insertAstroObject(int _id) {
		String name = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
		int mass = InputDati.leggiIntero("Inserisci la massa: ");
		int x = InputDati.leggiIntero("Inserisci la coordinta X (relativa all'oggetto astronomico a cui orbita): ");
		int y = InputDati.leggiIntero("Inserisci la coordinata Y (relativa all'oggetto astronomico a cui orbita): ");
		return new AstronomicalObject(_id, name, mass, x, y);
	
	}
}