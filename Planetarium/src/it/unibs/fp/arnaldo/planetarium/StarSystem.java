package it.unibs.fp.arnaldo.planetarium;
import java.util.*;

public class StarSystem {
	private Star star = new Star();
	/*abbiammo messo i pianeti e le lune in una matrice di LinkedList
	 * non avendo trovato delle matrici per oggetti abbiamo fatto una LinkedList di una altra LinkedList;
	 * nella prima colonna della matrice ci sono i pianeti, nelle successive le lune;
	 * in ogni riga, al primo membro c'è il pianeta, in quelli successivi ci sono le lune associate a quel pianeta
	 */
	private LinkedList<LinkedList<AstronomicalObject>> matrixOfOb = new LinkedList<LinkedList<AstronomicalObject>>();
	
	
	public StarSystem() {
		this.star = new Star();
		this.matrixOfOb = new LinkedList<LinkedList<AstronomicalObject>>();
	}
	
	public Star getStar() {
		return star;
	}
	

	public void setStar(Star star) {
		this.star = star;
	}
	
	public LinkedList<LinkedList<AstronomicalObject>> getMatrixOfOb() {
		return matrixOfOb;
	}

	public void setMatrixOfOb(LinkedList<LinkedList<AstronomicalObject>> matrixOfOb) {
		this.matrixOfOb = matrixOfOb;
	}
	
	/*temp è una variabile boolena che uso in un if nel main per comunicare se l'aggiunte
	*del pianeta è andata a buon fine. per aggiungere un pianeta innanzitutto creiamo (punto 1) una nuova LinkedList;
	*poi al punto 2 aggiungiamo alla posizione 0 di questa List il pianeta
	*/
	public boolean addPlanet(AstronomicalObject planet) {
		boolean temp;
/*1*/	temp = this.matrixOfOb.add(new LinkedList<AstronomicalObject>());
/*2*/	this.matrixOfOb.getLast().add(0, planet);
		return temp;
	}

	/*per aggiungere una luna ci serve sapere attorno a quale pianeta orbita. Lo scopriamo chiedendolo all'utente 
	 * che ci deve indicare l'indice del pianeta, che rappresenta l'indice di riga della nostra matrice.
	 * quindi puntiamo a questa riga e aggiungiamo la nuova luna
	 */
	public void addMoon(int i, AstronomicalObject moon) {
		this.matrixOfOb.get(i).add(moon);//get(i): mi posiziono sulla riga; add(moon) aggiungo la luna
	}
	
	//rimuovo solo nella posizione 0 perchè tutti i pianeti si trovano nella posizione 0
	public void removePlanet(int t) {
		this.matrixOfOb.get(t).remove(0);
	}
	//a indica l'indice di riga ( i pianeti), b la cella della riga a dove si trova la luna da eliminare
	public void removeMoon(int a, int b) {
		this.matrixOfOb.get(a).remove(b);
	}

	/*fa vedere i pianeti e il relativo indice nella lista. uso un for each
	 * per ogni a riga della matrice mostro il suo indice e il contenuto della cella nella colonna 0
	 * di questa riga, dove si trovano, apppunto, i pianeti
	 */
	public void showPlanets() {
		System.out.println("\nLista dei pianeti disponibili:");
		for(LinkedList<AstronomicalObject> item : matrixOfOb) {
			System.out.println("\nIndex: " + matrixOfOb.indexOf(item));
			System.out.println(item.get(0).astronomicalObjectString());
		}
	}
	
	/*il cilco per mostrare le lune è un po' diverso perchè devo iterare sia le rige che le colonne
	 * e usare 2 for each è risultato scomodo e infruttuoso
	 */
	public void showMoons() {
		System.out.println("\nLista delle lune disponibili:");
		for(int i = 0; i < matrixOfOb.size(); i++) {
			for(int j = 1; j < matrixOfOb.get(i).size(); j++) {
				System.out.println("\nIndex: [" + i + "] [" + j +"]");
				System.out.println(matrixOfOb.get(i).get(j).astronomicalObjectString());
			}
		}
	}
	
	//METODO PER RICEVERE UN ID DIVERSO DAI PRECEDENTI (PER I PIANETI)
	public int givePlanetID() {
		int j = 0;
		/*#######################################################################################################
		 *											BUG
		 * Se inserisco un pianeta poi lo elimino e poi scelgo di reinserire un pianeta si presenta un BUG
		 * riguardante l'indice. l'errore dice che l'indice è fuori dal range di valori consentiti
		  */
		//ciclo che itera per tutta la dimensione della lista per trovare un id libero
		if(matrixOfOb.isEmpty())
			return j;
		else {
		for(j = 0; j < matrixOfOb.size(); j++) {
		AstronomicalObject temp = new AstronomicalObject();
		temp = matrixOfOb.get(j).get(0);
		int tempID = temp.getiD();
		if(j == tempID)
			continue;
		else
			break;
		}
		}
		return j;		
	}
	
	//METODO PER RICEVERE UN ID DIVERSO DAI PRECEDENTI (PER LE LUNE)
	public int giveMoonID() {
		int i = 0;
		int j = 0;
		int k = 0; //costante per confrontare gli id nelle lune, in quanto non ci bastano i 2 contatori perchè si tratta di una matrice
		//ciclo che itera per tutta la dimensione della lista per trovare un id libero
		for(i = 0; i < matrixOfOb.size(); i++) {
			for(j =1; j < matrixOfOb.get(i).size(); j++) {
				AstronomicalObject temp = new AstronomicalObject();
				temp = matrixOfOb.get(j).get(j);
				int tempID = temp.getiD();
				if(k == tempID)
					k++;
			}	
		}
		return k;	
	}
}
