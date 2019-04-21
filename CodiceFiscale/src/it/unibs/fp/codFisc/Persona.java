package it.unibs.fp.codFisc;

import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Persona {
	private String cognome;
	private String nome;
	private char sesso;
	private int giornoNascita;
	private int meseNascita;
	private int annnoNascita = -1;
	private Comune luogoNascita = new Comune();
	
	static ArrayList<Persona> listaPersone = new ArrayList<Persona>();
	
	public Persona() {
		
	}
	
	public Persona(String cognome, String nome, char sesso, int giornoNascita, int meseNascita, int annnoNascita,
			Comune luogoNascita) {
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = sesso;
		this.giornoNascita = giornoNascita;
		this.meseNascita = meseNascita;
		this.annnoNascita = annnoNascita;
		this.luogoNascita = luogoNascita;
	}
	
	public Persona(String cognome, String nome, char sesso, int giornoNascita, int meseNascita, int annnoNascita, String luogoNascita) {
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = sesso;
		this.giornoNascita = giornoNascita;
		this.meseNascita = meseNascita;
		this.annnoNascita = annnoNascita;
		this.luogoNascita.setNome(luogoNascita);
		this.luogoNascita.setCodice(Comune.getListaComuni().get(this.luogoNascita.trovaCodiceComune()).getCodice());
	}
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public int getGiornoNascita() {
		return giornoNascita;
	}

	public void setGiornoNascita(int giornoNascita) {
		this.giornoNascita = giornoNascita;
	}

	public int getMeseNascita() {
		return meseNascita;
	}

	public void setMeseNascita(int meseNascita) {
		this.meseNascita = meseNascita;
	}

	public int getAnnnoNascita() {
		return annnoNascita;
	}

	public void setAnnnoNascita(int annnoNascita) {
		this.annnoNascita = annnoNascita;
	}
	
	public String getDataNascita() {
		String s = "%02d-%02d-%d";
		return String.format(s, this.giornoNascita, this.meseNascita, this.annnoNascita);
	}

	public Comune getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(Comune luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	public void setLuogoNascita(String nomeLuogoNascita) {
		this.luogoNascita.setNome(nomeLuogoNascita);
		this.luogoNascita.setCodice(Comune.getListaComuni().get(this.luogoNascita.trovaCodiceComune()).getCodice());
	}
	/**
	 * 
	 * @return Restituisce la lista delle persone
	 */
	public static ArrayList<Persona> getListaPersone() {
		return listaPersone;
	}

	public static void leggiPersone() {
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		String filePath = "inputPersone.xml";
			try {
				xmlif = XMLInputFactory.newInstance();
				xmlr = xmlif.createXMLStreamReader(Main.class.getResourceAsStream(filePath));
			}
			catch (Exception e) {
				System.out.println("Errore nell'inizializzazione del reader:");
				System.out.println(e.getMessage());
			}
			try {
				int i = 0;
				Persona tmpPersona = new Persona();
				while(xmlr.hasNext()) {
					switch(xmlr.getEventType()) {
					case XMLStreamConstants.CHARACTERS:
						if (xmlr.getText().trim().length() > 0){
							switch(i) {
								case 0:
									tmpPersona.setNome(xmlr.getText());
									i++;
									break;
								case 1:
									tmpPersona.setCognome(xmlr.getText());
									i++;
									break;
								case 2:
									tmpPersona.setSesso(xmlr.getText().charAt(0));
									i++;
									break;
								case 3:
									tmpPersona.setLuogoNascita(xmlr.getText());
									i++;
									break;
								case 4:
									tmpPersona.setAnnnoNascita(Integer.parseInt(xmlr.getText().substring(0, 4)));
									tmpPersona.setMeseNascita(Integer.parseInt(xmlr.getText().substring(5, 7)));
									tmpPersona.setGiornoNascita(Integer.parseInt(xmlr.getText().substring(8, 10)));
									listaPersone.add(tmpPersona);
									i = 0;
									tmpPersona = new Persona();
									break;
								default:
									break;
								}
								
							}
						break;
					default:
						break;
					}
					xmlr.next();
				}
			}
			catch (Exception e){
				System.out.println("Errore nella lettura delle Persone:");
				System.out.println(e.getMessage());
			}
	}
	
	public String personaToString() {
		return this.nome + ", " + this.cognome + ", Sesso: " + this.sesso + "\nLuogo di nascita: " + this.luogoNascita.getNome() 
			+ ", codice: " + this.luogoNascita.getCodice() + "\nData di nascita: " + this.giornoNascita + "/" + this.meseNascita
			+ "/" + this.annnoNascita;
	}
}
