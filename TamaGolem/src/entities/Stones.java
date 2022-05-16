package entities;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Enumerazione che implementa le <b>pietre degli elementi</b>. 
 *
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
	
	
	private static Hashtable<Stones, Integer> sharedStones = new Hashtable<>();   // ArrayList di pietre in comune ai due giocatori
	
	
	public static void setSharedStones(int size, int nPerType) {
		for(int i=0; i<size/nPerType; i++) {
			sharedStones.put(Stones.values()[i], nPerType);
		}
		
	}
	
	
	public static Hashtable<Stones, Integer> getSharedStones(){
		return sharedStones;
	}
	
	
	public static void addStone(Stones s) {
		if(sharedStones.replace(s, sharedStones.get(s) + 1) == null) {
			sharedStones.put(s, 1);
		}
	}
	
	
	public static void removeStone(Stones s) {
		sharedStones.replace(s, sharedStones.get(s) - 1);
		if(sharedStones.get(s) == 0) sharedStones.remove(s);
	}
	
	
	
}
