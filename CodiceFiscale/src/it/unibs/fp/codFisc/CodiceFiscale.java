
package it.unibs.fp.codFisc;

/**
 * Ho pensato questa classe come una costituita solo da metodi per il controllo della validità e la generazione
 * di codici fiscali e da quelli minori necessari per implementare i primi 2. Per questo motivo gli ho resi tutti static.
 * Anche se alcuni metodi avrebbero senso più da private ho preferito lasciarli public nel caso fossero utili in altre
 * situazioni in classi diverse.
 * @author fabiolusha
 *
 */
public class CodiceFiscale {
	
	public static final int COSTANTE_AUMENTO_GIORNO_NASCITA_DONNE = 40;
	public static final int LUNGHEZZA_NOME = 3;
	public static final int LUNGHEZZA_CF = 16;
	public static final int LUNGHEZZA_VALORI_CIN = 26;
	public static final int POSIZIONE_SESSO = 8;
	
	/**
	 * i valori attribuiti ai caratteri dispari del codice fiscale. Ottenuti da Wikipedia.
	 */
	private static final int[] VALORI_CIN_DISPARI = {1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};
	
	/**
	 * metodo che contolla la validità della codifica di un codice fiscale
	 * @param il codice fiscale da controllare
	 * @return true se il CF è codificato correttamente, false altrimenti
	 */
	public static boolean isValid(String cf)
	{
		/**
		 * primo controllo che verifica che il CF sia della giusta dimensione
		 */
		if(cf.length() != 16)
			return false;

		for(int i = 0; i < LUNGHEZZA_CF; i++) {
			/**
			 * controllo sulla corretta posizione delle lettere
			 */
			switch(i) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 11:
				case 15:
					if(!isLetter(cf.charAt(i)))
						return false;
					break;
				/**
				 * controlla che la lettera del mese sia compresa tra quelle ammissibili
				 */
				case 8:
					if(!isLetter(cf.charAt(i)))
						return false;
					else if(!isLetteraMeseAmmissibile(cf.charAt(i)))
						return false;
					break;
				/**
				 * 	controllo sulla corretta posizione dei numeri
				 */
				case 6:
				case 7:
				case 9:
				case 10:
				case 12:
				case 13:
				case 14:
					if(!isNumber(cf.charAt(i)))
						return false;
					break;
				default:
					break;
			}
		}
		
		int giornoNascita = Integer.parseInt(cf.substring(9, 11));
		int annoNascita = Integer.parseInt(cf.substring(6, 8));
		String meseNascita = cf.substring(8, 9);
		
		/**
		 * controllo se l'anno è bisesto, in caso affermativo la durata di febbraio diventa di 29gg
		 */
		Anno.controlloSeBisesto(annoNascita);
		
		/**
		 *con il metodo Anno.valueOf("String") richiamo la costante enum che coincide con la stringa data in input.
		 *Successivamete con .getDurata ottengo la durata del suddetto mese tenendo conto anche della costante di aumento
		 *per le donne, cioè tengo conto che la data del giorno di nascita delle donne è incrementata di 40
		 */
		int durataMese = Anno.valueOf(meseNascita).getDurata();
		if(cf.charAt(POSIZIONE_SESSO) == 'M' && giornoNascita > durataMese) 
				return false;
		else if(cf.charAt(POSIZIONE_SESSO) == 'F' && giornoNascita > (durataMese + COSTANTE_AUMENTO_GIORNO_NASCITA_DONNE) )
			return false;
		else 
			/**
			 * controllo che la codifica del cognome sia stata eseguita correttamente, in caso sffermativo passo
			 * al controllo della codifica del cognome. Se anche quest'ultimo caso è positivo eseguo il controllo
			 * sul caratter di controllo, ovvero il CIN, con il metodo controlloCaratterCIN(char) che ritorna un 
			 * valore booleano
			 */
			if(controlloCodificaNome_Cognome(cf.substring(0, 3)))
				if(controlloCodificaNome_Cognome(cf.substring(3, 6)))
					return controlloCarattereCIN(cf.charAt(15), cf);
		return false;
	}
	
	/**
	 * Controllo se un carattere di input è una lettera dell'alfabeto
	 * @param character
	 * @return true se è si tratta di un carattere alfabetico, false altrimenti
	 */
	public static boolean isLetter(char character)
	{
		if( character > 64 && character < 91)
			return true;
		else 
			return false;
	}
	
	/**
	 * Controllo se un carattere di input è di tipo numerico
	 * @param character
	 * @return true se è si tratta di un carattere numerico, false altrimenti
	 */
	public static boolean isNumber(char character) 
	{
		if( character > 47 && character < 58)
			return true;
		else
			return false;
	}
		
		/**
		 * Controllo se il carattere del codice fiscale che dovrebbe simboleggiare il mese di nasicta sia tra i carattere alfabetici ammissibili
		 * @param carattere alfabetico da verificare
		 * @return True se il caratter alfabetico è tra quelli ammissibili, false altrimenti
		 */
	public static boolean isLetteraMeseAmmissibile(char character)
	{
		String s = String.valueOf(character);
		Anno[] listaMesi = Anno.values();
		for(int i = 0; i < listaMesi.length; i++) {
			if(listaMesi[i].name().equals(s))
				return true;
		}
		return false;
	}
	
	/**
	 * contorllo se un dato carattere è una consonante
	 * @param c
	 * @return true in caso affermativo, false altrimenti
	 */
	public static boolean isConsonante(char c)
	{
		if(isLetter(c) && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U')
			return true;
		else
			return false;
	}
	
	/**
	 * il metodo esegue un controllo sul carattere di controllo semplicemente confrontando qullo presente nel CF
	 * di input con quello generato dall'algoritmo di questa classe. se i 2 valori coincidono ritorno true, altrimenti false
	 * @param ultimoCarattere del CF, coincidente con il caratter di controllo
	 * @param cf, il codice fiscale da testare, in tal modo posso ricostruire il carattere di controllo secondo l'algoritmo sviluppato
	 * da me
	 * @return boolean
	 */
	public static boolean controlloCarattereCIN(char ultimoCarattere, String cf)
	{
		String cfSenzaCIN = cf.substring(0, 15);
		char CIN = generaCIN(cfSenzaCIN).charAt(0);
		if(ultimoCarattere == CIN)
			return true;
		else
			return false;
	}
	
	/**
	 * Controlla che le vocali non compaiano prima delle consonanti
	 * @param nome o cognome
	 * @return true, se il nome o cognome rispetta la regola, false altrimenti
	 */
	public static boolean controlloCodificaNome_Cognome(String nome) {
		if( !isConsonante(nome.charAt(0))  && isConsonante(nome.charAt(1)) )
			return false;
		else if(!isConsonante(nome.charAt(0))  && isConsonante(nome.charAt(2)))
			return false;
		else if(!isConsonante(nome.charAt(1))  && isConsonante(nome.charAt(2)))
			return false;
		else 
			return true;
	}
	
	/**
	 * genero la codifica del cognome prendeno prima le consonanti nell'ordine in cui compaiono.
	 * se quest'ultime sono insufficienti prendo anche le vocali, nell'ordine in cui compaiono.
	 * se il nome non è sufficientemente lungo, cioè minore di 3 caratteri, aggiungo X fino a raggiungere
	 * il numero minimo di caratteri necessari
	 * @param cog_nome
	 * @return
	 */
	public static String codificaCognome(String cog_nome) 
	{
		String cogNome = cog_nome.toUpperCase();
		String cog_nome_codificato = "";
		int j = 0;
		for(int i = 0; i < cogNome.length() && j < LUNGHEZZA_NOME; i++) {
			if(isConsonante(cogNome.charAt(i))) {
				cog_nome_codificato += cogNome.charAt(i);
				j++;
			}
		}

		if(j < LUNGHEZZA_NOME) {
			for( int i = 0 ; i < cogNome.length() && j < LUNGHEZZA_NOME; i++) {
				if(!isConsonante(cogNome.charAt(i))) {
					cog_nome_codificato += cogNome.charAt(i);
					j++;
				}
			}	
		}
		
		if(cogNome.length() < LUNGHEZZA_NOME) 
			for(int i = 0; i < (LUNGHEZZA_NOME - cogNome.length()); i++) {
				cog_nome_codificato += "X";
				j++;
			}
		return cog_nome_codificato;
	}
	
	/**
	 * Se i ln numero di consonanti è uguale o maggiore di 4, il metodo ritorna le consonanti in posizioni 1, 3 e 4.
	 * in caso negativo, si procede come la codifica del cognome
	 * @param nome
	 * @return nome codificato
	 */
	public static String codificaNome(String nome) {
		int numConsonanti = 0;
		char[] consonanti = new char[4];
		for(int i = 0; i < nome.length() && numConsonanti < 4; i++) {
			if(isConsonante(nome.charAt(i))) {
				consonanti[numConsonanti] = nome.charAt(i);
				numConsonanti++;
			}	
		}
		
		if(numConsonanti > 3) {
			String nomeCodificato = String.valueOf(consonanti[0]) + consonanti[2] + consonanti[3];
			return nomeCodificato;
		}
		
		return codificaCognome(nome);
	}
	
	
	/**
	 * ritorna le ulrime 2 cifre dell'anno
	 * @param anno
	 * @return String
	 */
	public static String codificaAnno(int anno) 
	{
		return  String.valueOf(anno).substring(2, 4);
	}
	
	/**
	 * in base al numero del mese lo confronta con l'ordinal della classe anno.
	 * @param mese
	 * @return il mese codificato secondo le norme del codice fiscale
	 */
	public static String codificaMese(int mese)
	{
		Anno[] mesi = Anno.values();
		for( int i = 0; i < mesi.length; i++) {
			/**
			 * il +1 a mesi[i].ordinal() perchè nella classe enum Anno i valori ordinal dei mesi partono
			 * da 0;
			 */
			if(mese  == mesi[i].ordinal() + 1)
				return mesi[i].name();
		}
		return "00";
	}
	
	/**
	 * 
	 * @param giorno
	 * @param sesso, per poter determinare se sommare il giorno di 40 
	 * @return il giorno in 2 cifre
	 */
	public static String codificaGiorno(int giorno, char sesso)
	{
		String tmp = "%02d";
		if(sesso == 'M')
			return String.format(tmp, giorno);
		else
			return String.format(tmp, (giorno + COSTANTE_AUMENTO_GIORNO_NASCITA_DONNE));
	}
	
	/**
	 * algoritmo per generare il carattere di controllo, ovvero il CIN
	 * @param cfNonVerificato, cioè il codice fiscale privo del caratter di controllo
	 * @return il carattere di controllo
	 */
	public static String generaCIN(String cfNonVerificato) 
	{
		int somma = 0;
		int resto = 0;
		/**
		 * nel controllo verifcico che il numero della posizione dei caratteri sia pari.
		 * Tenendo conto, poi, che il numero delle posizioni parte da 1, nel controllo faccio i + 1
		 */
		for(int i = 0; i < LUNGHEZZA_CF - 1; i++)
		{
			if((i + 1) % 2 == 0) {
				//numeri pari
				if(isLetter(cfNonVerificato.charAt(i)))
					somma += (int)cfNonVerificato.charAt(i) - 65;
				else if(isNumber(cfNonVerificato.charAt(i)))
					somma += (int)cfNonVerificato.charAt(i) - 48;
			}
			//numeri dispari
			else {
				if(isLetter(cfNonVerificato.charAt(i)))
					somma += VALORI_CIN_DISPARI[(int)cfNonVerificato.charAt(i) - 65];
				else if(isNumber(cfNonVerificato.charAt(i)))
					somma += VALORI_CIN_DISPARI[(int)cfNonVerificato.charAt(i) - 48];
			}
		}
		resto = somma % LUNGHEZZA_VALORI_CIN;
		return String.valueOf((char)(resto + 65));
	}
	
	public static String generaCF(Persona cittadino)
	{
		String cf = codificaCognome(cittadino.getCognome()) +
				codificaNome(cittadino.getNome()) + codificaAnno(cittadino.getAnnnoNascita())
				+ codificaMese(cittadino.getMeseNascita()) 
				+ codificaGiorno(cittadino.getGiornoNascita(), cittadino.getSesso())
				+ cittadino.getLuogoNascita().getCodice();
		cf = cf + generaCIN(cf);
		return cf;
	}
		
}
