package entities;

import java.util.ArrayList;
import java.util.Hashtable;


/**
 * Enumerazione che implementa le <b>pietre degli elementi</b>.
 * <p>
 * In base alla difficolta' della partita, i giocatori hanno un diverso "sacco" comune di pietre disponbili,
 * dal quale scegliere le pietre con cui combattere. 
 * Il sacco e' implementato tramite una {@link Hashtable}, che contiene coppie di chiave-valore dove la chiave e'
 * l'elemento della pietra, mentre il valore e' il numero di pietre disponibili di quell'elemento.
 * </p>
 */

public enum Stones {

	RICARDO,    // Element 0: Ricardo Milos
	JUAN,		// Element 1: Juan Dudoso el caballo loco
	UMBERTO,	// Element 2: Vamos Umberto
	GIGACHAD,	// Element 3: Avarage java enjoyer
	HARAMBE,	// Element 4: Rip Harambe
	DOGE,		// Element 5: Invest in DogeCoin
	HASBULLA,   // Element 6: Best mma fighter 
	ARANZULLA;  // Element 7: Our God 
	
	
	private static Hashtable<Stones, Integer> sharedStones = new Hashtable<>();   // HashTable di pietre in comune ai due giocatori
	
	
	/**
	 * Genera l'HashTable di pietre comuni disponibili per la partita
	 * @param size Grandezza dell'HashTable (Numero di elementi che si voglio utilizzare)
	 * @param nPerType Numero di pietre per elemento
	 */
	public static void setSharedStones(int size, int nPerType) {
		for(int i=0; i<size/nPerType; i++) {
			sharedStones.put(Stones.values()[i], nPerType);
		}
		
	}
	
	
	/**
	 * @return L'HashTable di pietre degli elementi in comune
	 */
	public static Hashtable<Stones, Integer> getSharedStones(){
		return sharedStones;
	}
	
	
	/**
	 * Aggiungi una pietra all'HashTable, rendendola quindi disponibile per la selezione
	 * @param s Pietra da aggiungere 
	 */
	public static void addStone(Stones s) {
		if(sharedStones.replace(s, sharedStones.get(s) + 1) == null) {  // Se l'operazione di aggiunta della pietra restituisce null, significa che l'elemento della
			sharedStones.put(s, 1);										// pietra non esiste nell'HashTable e che quindi va aggiunto come nuovo elemento.			
		}
	}
	
	
	/**
	 * Rimuovi una pietra dall'HashTable
	 * @param s Pietra da rimuovere
	 */
	public static void removeStone(Stones s) {
		sharedStones.replace(s, sharedStones.get(s) - 1); // Decrementa di 1 la quantita di pietre disponibili dell'elemento di s
		if(sharedStones.get(s) == 0) sharedStones.remove(s);  // Se la quantita' e' uguale a zero, rimuovi l'elemento
	}
	
	
	
	/**
	 * @return Lista ordinata (secondo l'ordine naturale) delle chiavi dell'HashTable
	 */
	public static ArrayList<Stones> getOrderedKeyList(){
		ArrayList<Stones> orderedList = new ArrayList<>();
		orderedList.addAll(sharedStones.keySet());  // Aggiungi all'ArrayList tutte le chiavi (elementi) dell'HashTable
		orderedList.sort(null);  // Ordina l'ArrayList secondo l'ordine naturale degli elementi (ovvero l'ordine dell'enumerazione)
		return orderedList;
	}
	
}


