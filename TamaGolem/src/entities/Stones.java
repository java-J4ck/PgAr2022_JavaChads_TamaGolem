package entities;

/**
 * Enumerazione che implementa le <b>pietre degli elementi</b>. 
 *
 */

public enum Stones {

	RICARDO,    // Element 1: Ricardo Milos
	JUAN,		// Element 2: Juan Dudoso el caballo loco
	UMBERTO,	// Element 3: Vamos Umberto
	GIGACHAD,	// Element 4: Avarage java enjoyer
	HARAMBE,	// Element 5: Rip Harambe
	DOGE,		// Element 6: Invest in DogeCoin
	HASBULLA,   // Element 7: Best mma fighter 
	ARANZULLA;  // Element 8: Our God 
	
	
	private static int sharedStones = 0;   // Numero di pietre in comune ai due giocatori

	
	/**
	 * @return Il numero di pietre in comune ai due giocatori
	 */
	public static int getSharedstones() {
		return sharedStones;
	}

	
	/**
	 * Setter per aggiornare il numero di pietre in comune
	 * @param sharedStones Nuovo numero di pietre
	 */
	public static void setSharedStones(int sharedStones) {
		Stones.sharedStones = sharedStones;
	}
	
	
	
	public static Stones[] getNStones(int n) {
		Stones[] s = new Stones[n];
		for(int i = 0; i<n; i++)
			s[i] = Stones.values()[i];
		return s;
	}
	
	
	
	
}
