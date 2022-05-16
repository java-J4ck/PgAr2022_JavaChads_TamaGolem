package entities;

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
	
	
	
	
	/**
	 * Metodo che restituisce un array di pietre dei primi n elementi  
	 * 
	 * @param n Dimensione array (corrispondente al numero di elementi, determinato in fase di setup)
	 * @return array di tipo <b>Stones</b> contenete pietre dei primi n elementi
	 */
	public static Stones[] getNStones(int n) {
		Stones[] s = new Stones[n];
		for(int i = 0; i<n; i++)
			s[i] = Stones.values()[i];
		return s;
	}
	
	
	
	
}
