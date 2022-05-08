package entities;

/**
 * Enumerazione che implementa le <b>pietre degli elementi</b>. 
 *
 */

public enum Stones {

	FUOCO,
	TERRA,
	ARIA,
	ACQUA;
	
	
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
	
	
	
	
}
