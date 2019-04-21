/*
#######################################################################
##																	 ##
##				QUESTA NON È UNA CLASSE FUNZIONALE					 ##
##		ALLA SOLOUZIONE DEL ESERCIZO MA SOLO UNA PSEUDOCLASSE 		 ##
##	DI TEST USATA PER LA VERIFICARE IL FUNZIONAMENTO DEI METODI XML  ##
##																	 ##		
#######################################################################
*/
package it.unibs.fp.codFisc;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class Main {

	private static final String ASSENTE = "ASSENTE";
	public static ArrayList<String> codiciFiscaliImportati = new ArrayList<String>();
	public static ArrayList<String> codiciFiscaliNonValidi = new ArrayList<String>();
	public static ArrayList<String> codiciFiscaliGenerati = new ArrayList<String>();
	public static ArrayList<String> codiciFiscaliPersone = new ArrayList<String>();
	public static ArrayList<String> codiciFiscaliSpaiati = new ArrayList<String>();
		
	public static void main(String args[]) {
		
		Comune.leggiComuni();
		Persona.leggiPersone();
		leggiCodiciFiscali();
		trovaCodiciFiscaliNonValidi();
		generaCodiciFiscaliPersone();
		controlloPresenzaCodiciFiscali();
		generaCodiciFiscaliSpaiati();
		scriviXML();
		
	}
	
	public static void leggiCodiciFiscali()
	{
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		String filePath = "codiciFiscali.xml";
		try 
		{
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(Main.class.getResourceAsStream(filePath));
		}
		catch (Exception e) 
		{	
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try 
		{
			while (xmlr.hasNext()) 
			{
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
					if (xmlr.getText().trim().length() > 0)
						codiciFiscaliImportati.add(xmlr.getText());
					break;
				default:
					break;
				}
				
			xmlr.next();
			}
		}
		catch (Exception e) 
		{
			System.out.println("Errore nella lettura dei codici fiscali:");
			System.out.println(e.getMessage());
		}

	}
	
	public static void trovaCodiciFiscaliNonValidi() 
	{
		for(String cf : codiciFiscaliImportati) 
			if(!CodiceFiscale.isValid(cf)) 
				codiciFiscaliNonValidi.add(cf);
	}
	
	public static void generaCodiciFiscaliPersone() 
	{
		for(Persona tmpPersona : Persona.getListaPersone()) 
			codiciFiscaliGenerati.add(CodiceFiscale.generaCF(tmpPersona));
		
	}
	
	/**
	 * Controllo se i codici fiscali generati dalle persone di inputPersone sono presenti nei CF di codiciFiscali
	 */
	public static void controlloPresenzaCodiciFiscali()
	{
		for(int i = 0; i < codiciFiscaliGenerati.size(); i++) {
			if(codiciFiscaliImportati.contains(codiciFiscaliGenerati.get(i)))
				codiciFiscaliPersone.add(i, codiciFiscaliGenerati.get(i));
			else
				codiciFiscaliPersone.add(i, ASSENTE);
		}
	}
	
	/**
	 * dai CF di codiciFiscali.xml, se il codice non è presente tra quelli invalidi o quelli validi, cha appartengono
	 * a una persona, lo si aggiunge nella lista di CF spaiati
	 */
	public static void generaCodiciFiscaliSpaiati()
	{
		for(String tmpCf: codiciFiscaliImportati){
			if(codiciFiscaliNonValidi.contains(tmpCf)  || codiciFiscaliPersone.contains(tmpCf))
				continue;
			codiciFiscaliSpaiati.add(tmpCf);
		}
	}
	
	public static void scriviXML() {
		XMLOutputFactory xof = null;
		XMLStreamWriter xw = null;
		String fileName = "codiciPersone.xml";
		try 
		{
			xof = XMLOutputFactory.newInstance();
			xw = xof.createXMLStreamWriter(new FileOutputStream(fileName), "utf-8");
			xw.writeStartDocument("utf-8", "1.0");
		} 
		catch (Exception e) 
		{
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
		
		try
		{
			//apertura output
			xw.writeStartElement("output");
				//apertura persone
				xw.writeStartElement("persone");
				xw.writeAttribute("size", String.valueOf(Persona.getListaPersone().size()));
			
					for(int i = 0; i < Persona.getListaPersone().size(); i++)
					{
						xw.writeStartElement("persona");
						xw.writeAttribute("id", String.valueOf(Persona.getListaPersone().indexOf(Persona.getListaPersone().get(i))));
							//cognome
							xw.writeStartElement("cognome");
							xw.writeCharacters(Persona.getListaPersone().get(i).getCognome());
							xw.writeEndElement();
							//nome
							xw.writeStartElement("nome");
							xw.writeCharacters(Persona.getListaPersone().get(i).getNome());
							xw.writeEndElement();
							//sesso
							xw.writeStartElement("sesso");
							xw.writeCharacters(String.valueOf(Persona.getListaPersone().get(i).getSesso()));
							xw.writeEndElement();
							//luogo di nascita
							xw.writeStartElement("comune_di_nascita");
							xw.writeCharacters(Persona.getListaPersone().get(i).getLuogoNascita().getNome());
							xw.writeEndElement();
							//data di nascita
							xw.writeStartElement("data_di_nascita");
							xw.writeCharacters(Persona.getListaPersone().get(i).getDataNascita());
							xw.writeEndElement();
							//codice fiscale
							xw.writeStartElement("codice_fiscale");
							xw.writeCharacters(codiciFiscaliPersone.get(i));
							xw.writeEndElement();
						xw.writeEndElement();
					}
				//chiusura persone
				xw.writeEndElement();
				//apertura codici
				xw.writeStartElement("codici");
					//apertura invalidi
					xw.writeStartElement("invalidi");
					xw.writeAttribute("numero", String.valueOf(codiciFiscaliNonValidi.size()));
					
						for(int i = 0; i < codiciFiscaliNonValidi.size(); i++) {
							xw.writeStartElement("codice_fiscale");
							xw.writeCharacters(codiciFiscaliNonValidi.get(i));
							xw.writeEndElement();
						}
					//chiusura invalidi
					xw.writeEndElement();
					//apertura spaiati
					xw.writeStartElement("spaiati");
					xw.writeAttribute("numero", String.valueOf(codiciFiscaliSpaiati.size()));
						
						for(int i = 0; i < codiciFiscaliSpaiati.size(); i++)
						{
							xw.writeStartElement("codice_fiscale");
							xw.writeCharacters(codiciFiscaliSpaiati.get(i));
							xw.writeEndElement();	
						}
					//chiusura spaiati
					xw.writeEndElement();
				//chiusura codici
				xw.writeEndElement();
			//chiusura output	
			xw.writeEndElement();
			
			xw.writeEndDocument();
			xw.flush();
			xw.close();
		} 
		catch (Exception e)
		{
			System.out.println("Errore nella scrittura");
			System.out.println(e.getMessage());
		}
	}
	
}
