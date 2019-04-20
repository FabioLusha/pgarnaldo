package it.unibs.fp.codFisc;

import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Comune {
	String codice;
	String nome;
	
	static ArrayList<Comune> listaComuni = new ArrayList<Comune>();
	
	
	public Comune(String _nome, String _codice) {
		this.nome = _nome;
		this.codice = _codice;
	}
	
	public Comune() {
		this("", "");
	}
	
	//ricordati di quando richiami questo costrutore di implementare un controllo che scarti la
	//persona quando il comune non è presente nella lista, cioè quando il codice del comune è -1
	public Comune(String _nome) {
		this.nome = _nome;
		this.codice = listaComuni.get(trovaCodiceComune()).getCodice();
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Il metodo è reso static perchè non varia al variare delle istanze della classe Comune
	 * @return la lista dei comuni con i rispettivi codici alfanumerici 
	 */
	public static ArrayList<Comune> getListaComuni() {
		return listaComuni;
	}
	
	/**
	 * Il Il funzionamento di questo metodo, oltre quello di leggere i dati dal file,
	 * è quello di inizializzare un ArrayList<<Comune>>, dove in ogni posizionesi trova il 
	 * nome del comune e il rispettivo codice.
	 * Questo metodo dovrebbe essere utilizzato una sola vota per l'inizializzazione della lista
	 * e PRIMA che venga usata qualsiasi istanza della classe Persona, il quale attributo comune
	 * dipende dalla classe in questione. Quindi nel main deve essere uno dei primi metodi a essere
	 * richiamato.
	 * IL metodo è static in quanto la lista NON varia in funzione delle istante della classe.
	 * 
	 */
	public static void leggiComuni() {
		
	XMLInputFactory xmlif = null;
	XMLStreamReader xmlr = null;
	String filePath = "comuni.xml";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(XMLReader.class.getResourceAsStream(filePath));
		}
		catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		try {
			int i = 0;
			Comune tmpComune = new Comune();
			while(xmlr.hasNext()) {
				switch(xmlr.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
					if (xmlr.getText().trim().length() > 0){
						if(i == 0) {
							tmpComune.setNome(xmlr.getText());
							i++;
						}
						else {
							tmpComune.setCodice(xmlr.getText());
							listaComuni.add(tmpComune);
							i = 0;
							tmpComune = new Comune();
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
			System.out.println("Errore nella lettura dei comuni:");
			System.out.println(e.getMessage());
		}
	}
	
	public int trovaCodiceComune() {
		for(int i = 0; i < listaComuni.size(); i++) {
			if(listaComuni.get(i).getNome().equals(nome))
				return i;
		}
		return 0;
	}
	
	
}
