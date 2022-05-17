package entities;

import java.util.Arrays;

import utilities.Equilibrio;

/**
 * Classe che implementa il tamagolem
 *
 */

public class TamaGolem {

	private int hp = 100;      // Vita del tamagolem (Default: 100)
	private Stones[] stones;   // Numero di pietre che il tamagolem puo' ingurgitare. 
							   // La dimensione dell'array viene determinata in base alla diffolta' scelta in fase di setup.	
	
	
	
	public TamaGolem() {
		this.hp = Equilibrio.getHp();
		this.stones = new Stones[Equilibrio.getElementNumber()];  
	}	
	
	
	/**
	 * @return Vita del tamagolem
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Decrementa la vita del tamagolem di un certo numero n
	 * @param n 
	 */
	public void decreaseHp(int n) {
		this.hp -= n;
	}
	
	
	/**
	 * @return L'array di pietre ingurgitate dal tamagolem
	 */
	public Stones[] getStones() {
		return stones;
	}
	
	
	/**
	 * Setter per l'array di pietre che il tamagolem deve inghiottire
	 * @param stones
	 */
	public void setStones(Stones[] stones) {
		this.stones = Arrays.copyOf(stones, stones.length);
	}
	
	/**
	 * @return TRUE se il tamagolem e' morto, FALSE altrimenti
	 */
	public boolean isDead() {
		return hp <= 0 ? true : false; 
	}
	
	

	
	
	
	
}
