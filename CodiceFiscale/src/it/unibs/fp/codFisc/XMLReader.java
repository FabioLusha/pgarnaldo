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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLReader {
	ArrayList<String> fiscalCodes = new ArrayList<String>();
	public static void main(String args[])  {
		ArrayList<String> cf = new ArrayList<String>();
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		String filePath = "codiciFiscali.xml";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(XMLReader.class.getResourceAsStream(filePath));
		}
		catch (Exception e) {	
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
	}
		
		try {
			while (xmlr.hasNext()) {
				
				/*switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Start Read Doc " + filePath);
				break;
				case XMLStreamConstants.START_ELEMENT: 
				System.out.println("Tag " + xmlr.getLocalName());
				for (int i = 0; i < xmlr.getAttributeCount(); i++)
					System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
				break;
				case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
				System.out.println("END-Tag " + xmlr.getLocalName()); 
				break;
				case XMLStreamConstants.COMMENT:
				System.out.println("// commento " + xmlr.getText());
				break; // commento: ne stampa il contenuto
				case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
				if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
				System.out.println("-> " + xmlr.getText());
				break;
				}
				*/
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
					if (xmlr.getText().trim().length() > 0)
						cf.add(xmlr.getText());
					break;
				default:
					break;
				}
				
				xmlr.next();
				}
		}
		catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
	}
		
	Comune.leggiComuni();
	Persona.leggiPersone();
/*	
	for(int i = 0; i < Comune.getListaComuni().size(); i++) {
		System.out.printf("%s  %s\n",Comune.getListaComuni().get(i).getNome(), Comune.getListaComuni().get(i).getCodice() );
	}
	*/
	int j = 0;
	for(int i = 0; i < Persona.getListaPersone().size(); i++) {
		System.out.println(Persona.getListaPersone().get(i).personaToString());
		if(Persona.getListaPersone().get(i).getLuogoNascita().getCodice().equals("-1"))
			j++;
	}
	System.out.println(j);
}
}
